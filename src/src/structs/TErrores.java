
package structs;
/**
 *
 * @author GustavC
 */
public class TErrores {
    public String lexema, tipo, descript;
    public int line, col;
    
    public TErrores(String lex_,int line_, int col_,String tipo_,String descript_){
        this.lexema = lex_;
        this.line = line_;
        this.col = col_;
        this.tipo = tipo_;
        this.descript = descript_;  
        
    }
    
}
