package structs;

/**
 *
 * @author GustavC
 */
public class Conjunto {
    String name;
    String conjunto;
    public Conjunto(String name_,String conjunto_){
        this.name = name_;
        this.conjunto = conjunto_;
    }
    public String getNameConj(){
        return this.name;
    }
    
    public String getConjunto(){
        return this.conjunto;
    }
}
