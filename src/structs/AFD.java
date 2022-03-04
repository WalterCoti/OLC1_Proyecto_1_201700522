package structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GustavC
 */
public class AFD {
    String nameAFD;
    NHoja raiz;
    Map<String,String> conjuntos = new HashMap<>();
    
    
    public AFD(String nombreAFD_, Map<String,String> conjuntos_,NHoja raiz_){
        this.nameAFD=nombreAFD_;
        this.conjuntos.putAll(conjuntos_);
        this.raiz = raiz_;
    }
}
