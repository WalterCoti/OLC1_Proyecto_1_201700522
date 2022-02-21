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
    public static List<TErrores> LError = new ArrayList();
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
%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 
/* EXPRESIONES REGULARES */
COMENTARIOS = ("//".*[^\r\n])|([<!]([^!\>]|[^!]\>|\![^\>])*\!*\!\>)
SALTOLINEA = [\r|\n]
BLANCOS=[ \r\t]+
NUMERO = [0-9]*
LETRA = [A-Za-zÑñ]
ESPECIAL = (\\n|\\\'|\\\")
SIGNO = [\!-\/\:-\@\[-\`%\{-\}]
IDENT = [A-Za-zÑñ]([A-Za-zÑñ]|[0-9]|_)*
CADENA = [\"][^\r\n]*[\"]


%% 

{COMENTARIOS} {}

<YYINITIAL> "CONJ" {System.out.println("Lexico "+yytext()+" CONJ"); return new Symbol(sym.CONJ,yycolumn,yyline, yytext());}
<YYINITIAL> "%%" {System.out.println("Lexico "+yytext()+" SEPA");return new Symbol(sym.SEPA,yycolumn,yyline, yytext());}
<YYINITIAL> "->" {System.out.println("Lexico "+yytext()+" ASIGN"); return new Symbol(sym.ASIGN,yycolumn,yyline, yytext());}
<YYINITIAL> ";" {System.out.println("Lexico "+yytext()+" PTCOMA"); return new Symbol(sym.PTCOMA,yycolumn,yyline, yytext());}
<YYINITIAL> ":" {System.out.println("Lexico "+yytext()+" DDOT"); return new Symbol(sym.DDOT,yycolumn,yyline, yytext());}
<YYINITIAL> "." {System.out.println("Lexico "+yytext()+" DOT"); return new Symbol(sym.DOT,yycolumn,yyline, yytext());}
<YYINITIAL> "," {System.out.println("Lexico "+yytext()+" COMA"); return new Symbol(sym.COMA,yycolumn,yyline, yytext());}
<YYINITIAL> "{" {System.out.println("Lexico "+yytext()+" LLAVEL"); return new Symbol(sym.LLAVEL,yycolumn,yyline, yytext());} 
<YYINITIAL> "}" {System.out.println("Lexico "+yytext()+" LLAVER"); return new Symbol(sym.LLAVER,yycolumn,yyline, yytext());}
<YYINITIAL> "|" {System.out.println("Lexico "+yytext()+" DISYUN"); return new Symbol(sym.DISYUN,yycolumn,yyline, yytext());}
<YYINITIAL> "?" {System.out.println("Lexico "+yytext()+" SINTER"); return new Symbol(sym.SINTER,yycolumn,yyline, yytext());}
<YYINITIAL> "+" {System.out.println("Lexico "+yytext()+" MAS"); return new Symbol(sym.MAS,yycolumn,yyline, yytext());}
<YYINITIAL> "*" {System.out.println("Lexico "+yytext()+" POR"); return new Symbol(sym.POR,yycolumn,yyline, yytext());}
<YYINITIAL> "~" {System.out.println("Lexico "+yytext()+" VIRGU"); return new Symbol(sym.VIRGU,yycolumn,yyline, yytext());}



 {NUMERO} {System.out.println("Lexico "+yytext()+" NUMB"); return new Symbol(sym.NUMB,yycolumn,yyline, yytext());}
 {SIGNO} {System.out.println("Lexico "+yytext()+" SIGNO"); return new Symbol(sym.SIGNO,yycolumn,yyline, yytext());}
 {LETRA} {System.out.println("Lexico "+yytext()+" LETRA"); return new Symbol(sym.LETRA,yycolumn,yyline, yytext());}
 {ESPECIAL} {System.out.println("Lexico "+yytext()+" ESPE"); return new Symbol(sym.ESPE,yycolumn,yyline, yytext());}
 {IDENT} {System.out.println("Lexico "+yytext()+" ID"); return new Symbol(sym.ID,yycolumn,yyline, yytext());}
 {CADENA} {System.out.println("Lexico "+yytext()+" CADENA"); return new Symbol(sym.CADENA,yycolumn,yyline, yytext());}
{BLANCOS} {}
{SALTOLINEA} {}


. {
    System.out.println("Error Lexico: "+yytext()+" linea: "+ yyline + " columna " + yycolumn);
    TErrores nwError = new TErrores(yytext(),yyline,yycolumn,"Error Lexico","El simbolo: "+ yytext() +" no pertenece al lenguaje");
    LError.add(nwError);
}
