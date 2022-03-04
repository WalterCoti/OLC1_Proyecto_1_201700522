
package structs;
/**
 *
 * @author GustavC
 */
public class TErrores {
    String lexema, tipo, descript;
    int line, col;
    
    public TErrores(String lex_,int line_, int col_,String tipo_,String descript_){
        this.lexema = lex_;
        this.line = line_;
        this.col = col_;
        this.tipo = tipo_;
        this.descript = descript_;  
        
    }
    
    public String getlexema(){
        return this.lexema;
    }
    public String gettipo(){
        return this.tipo;
    }
    
    public String getDescript(){
        return  this.descript;
    }
    
    public int getLine(){
        return this.line;
    }
    
    public int getCol(){
        return this.col;
    }
}
