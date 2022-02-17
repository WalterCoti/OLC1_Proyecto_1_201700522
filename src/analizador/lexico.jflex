/*------ Area 1 ------*/
//pauetes e importaciones
package analizador;
import java_cup.runtime.Symbol; 
import structs.TErrores;

import java.util.ArrayList;
import java.util.List;

/* Area 2 : Opcions y Declaraciones */

%% 

%{
    public static List<Error> LError = new ArrayList();
%}

 
%class Lexico
%public
%cup
%char
%line
%column
%full
%ignorecase     
%unicode

/* EXPRESIONES REGULARES */
BLANCOS=[ \r\t]+
DIGITO = [0-9]
LETRA = [A-Za-zÑñ]
SIGNO = [\!-\/\:-\@\[-\`%\{-\}]
ESPECIAL = (\\n|\\\'|\\\")
COMENTARIOS = (\/\/.*[^\r\n])|(\<\!([^!\>]|[^!]\>|\![^\>])*\!*\!\>)
IDENT = LETRA(LETRA|DIGITO|_)*
CADENA = \"[^\"\r\n]*\"
COMENTARIOS = (\/\/.*[^\r\n])

%% 

{COMENTARIOS} {}
<YYINITIAL> "CONJ" {return new Symbol(sym.CONJ,yycolumn,yyline, yytext());}
<YYINITIAL> "%%" {return new Symbol(sym.SEPA,yycolumn,yyline, yytext());}
<YYINITIAL> "->" {return new Symbol(sym.ASIGN,yycolumn,yyline, yytext());}
<YYINITIAL> ";" {return new Symbol(sym.PTCOMA,yycolumn,yyline, yytext());}
<YYINITIAL> ":" {return new Symbol(sym.DDOT,yycolumn,yyline, yytext());}
<YYINITIAL> "." {return new Symbol(sym.DOT,yycolumn,yyline, yytext());}
<YYINITIAL> "," {return new Symbol(sym.COMA,yycolumn,yyline, yytext());}
<YYINITIAL> "{" {return new Symbol(sym.LLAVEL,yycolumn,yyline, yytext());} 
<YYINITIAL> "}" {return new Symbol(sym.LLAVER,yycolumn,yyline, yytext());}
<YYINITIAL> "|" {return new Symbol(sym.DISYUN,yycolumn,yyline, yytext());}
<YYINITIAL> "?" {return new Symbol(sym.SINTER,yycolumn,yyline, yytext());}
<YYINITIAL> "+" {return new Symbol(sym.MAS,yycolumn,yyline, yytext());}
<YYINITIAL> "*" {return new Symbol(sym.POR,yycolumn,yyline, yytext());}
<YYINITIAL> "~" {return new Symbol(sym.VIRGU,yycolumn,yyline, yytext());}



 {DIGITO} {return new Symbol(sym.DIGITO,yycolumn,yyline, yytext());}
 {SIGNO} {return new Symbol(sym.SIGNO,yycolumn,yyline, yytext());}
 {LETRA} {return new Symbol(sym.LETRA,yycolumn,yyline, yytext());}
 {ESPECIAL} {return new Symbol(sym.ESPECIAL,yycolumn,yyline, yytext());}
 {IDENT} {return new Symbol(sym.ID,yycolumn,yyline, yytext());}
 {CADENA} {return new Symbol(sym.CADENA,yycolumn,yyline, yytext());}
{BLANCOS} {}


. {
    Error nwError = new Error(yytext(),yyline,yycolumn,"Error Lexico","El simbolo: "+ yytext() +" no pertenece al lenguaje");
    LError.add(nwError);
}
