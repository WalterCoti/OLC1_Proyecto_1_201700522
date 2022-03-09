package structs;

/**
 *
 * @author GustavC
 */
public class NHoja {
    public int ID_nodo;
    public String valor;
    public String tipo;
    public NHoja HojaR;
    public NHoja HojaL;
    public boolean anulable;
    public String primeros;
    public String ultimos;
    
    public NHoja(String valor_,String tipo_,NHoja HojaL_,NHoja HojaR_){
        this.valor = valor_;
        this.tipo = tipo_;
        this.HojaR = HojaR_;
        this.HojaL = HojaL_;
        this.primeros = "";
        this.ultimos = "";
        try{
            esAnulable( tipo_,HojaL_, HojaR_);
        }catch(Exception e){
            System.out.println("");//evitar los null en las hojas finales
        }
        
        
        
    } 
    
    public void esAnulable(String tipo_,NHoja HojaL_,NHoja HojaR_){
        switch(tipo_){
            case "h":
                this.anulable = false;
                break;
            case ".":
                this.anulable = HojaL_.anulable && HojaR_.anulable;
                break;
            case "|":
                this.anulable = HojaL_.anulable || HojaR_.anulable;
                break;
            case "?":
                this.anulable = true;
                break;
            case "*":
                this.anulable = true;
                break;
            case "+":
                this.anulable = false;
                break;
        default:
                this.anulable = false;
            break;
        }
        
    }
    
}

