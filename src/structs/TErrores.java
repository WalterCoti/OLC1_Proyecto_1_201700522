
package structs;
/**
 *
 * @author GustavC
 */
public class TErrores {
    String lexema, tipo, descript;
    int line, col;
    
    public TErrores(String lex_,int line_, int col_,String tipo_,String descript_){
        lexema = lex_;
        line = line_;
        col = col_;
        tipo = tipo_;
        descript = descript_;  
        
    }
     
}
