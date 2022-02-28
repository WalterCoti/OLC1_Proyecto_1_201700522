package structs;

/**
 *
 * @author GustavC
 */
public class NHoja {
    int Idnodo;
    String valor;
    String tipo;
    NHoja HojaR;
    NHoja HojaL;
    boolean anulable;
    String primeros;
    String ultimos;
    
    public NHoja(String valor_,String tipo_,NHoja HojaR_,NHoja HojaL_){
        this.valor = valor_;
        this.tipo = tipo_;
        this.HojaR = HojaR_;
        this.primeros = "";
        this.ultimos = "";
        esAnulable( tipo_,HojaR, HojaL);
        
    } 
    
    public void esAnulable(String tipo_,NHoja HojaR,NHoja HojaL){
        switch(tipo_){
            case "h":
                this.anulable = false;
                break;
            case ".":
                this.anulable = HojaR.anulable && HojaL.anulable;
                break;
            case "|":
                this.anulable = HojaR.anulable || HojaL.anulable;
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

