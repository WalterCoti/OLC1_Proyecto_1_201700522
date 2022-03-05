package structs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    int id,aux=1;
    List<String> alfabeto = new ArrayList<>();
    Map<String,String> conjuntos = new HashMap<>();
    Map<String, String> Lalfabeto = new HashMap<>();
    Map<Integer, String[]> LSiguientes = new HashMap<>();
    
    
    public AFD(String nombreAFD_, Map<String,String> conjuntos_,NHoja raiz_){
        this.nameAFD=nombreAFD_;
        this.conjuntos.putAll(conjuntos_);
        this.raiz = raiz_;
        try {
            generarDatos();
        } catch (Exception e) {
            //System.out.println(e+"Es aquihdspm");
        }
        
    }
    
    
    public void generarDatos(){
        this.raiz = genData(this.raiz);
    }
    
    public NHoja genData(NHoja node){
        
        if (node.HojaL!=null){
            node.HojaL = genData(node.HojaL);
        }
        
        if(node.HojaR !=null){
            node.HojaR = genData(node.HojaR);
        }
        
        if("h".equalsIgnoreCase(node.tipo)){
            node.primeros = this.id +"";
            node.ultimos = this.id +"";
            node.ID_nodo = this.id;
            if(Lalfabeto.get(node.valor)==null && !node.valor.equals("#")){
                Lalfabeto.put(node.valor, node.valor);
                this.alfabeto.add(node.valor);
            }
            String[] datos = {node.valor, this.id+"", ""};
            this.LSiguientes.put(this.id,datos);
            this.id++;
        }
        else{
            node = genTipo(node);
        }
        if(".".equals(node.tipo) || "*".equals(node.tipo) || "+".equals(node.tipo)){
            genSiguientes(node);
        }
        return node;
    }
    
    public NHoja genTipo(NHoja node){
        switch(node.tipo){
            case ".":
                if (node.HojaL.anulable) {
                    node.primeros = node.HojaL.primeros+","+node.HojaR.primeros;
                }else{
                    node.primeros = node.HojaL.primeros;
                }
                if (node.HojaR.anulable) {
                    node.ultimos = node.HojaL.ultimos+","+node.HojaR.ultimos;
                }else{
                    node.ultimos = node.HojaR.ultimos;
                }
                break;
            case "|":
                node.primeros = node.HojaL.primeros + "," + node.HojaR.primeros;
                node.ultimos = node.HojaL.ultimos + "," + node.HojaR.ultimos;
                break;
            case "*":
                node.primeros = node.HojaL.primeros;
                node.ultimos = node.HojaL.ultimos;
                break;
            case "+":
                node.primeros = node.HojaL.primeros;
                node.ultimos = node.HojaL.ultimos;
                break;
        default:
            node.primeros = node.HojaL.primeros;
            node.ultimos = node.HojaL.ultimos;
            break;
        }
        return node;
    }

    public void genSiguientes(NHoja node){
        String[] anteriores;
        anteriores = node.HojaL.ultimos.split(",");
        switch(node.tipo){
            case ".":
                for (String before: anteriores){
                    String [] lista = this.LSiguientes.get(Integer.parseInt(before));
                    if(lista[2].isEmpty()){
                        lista[2] = node.HojaR.primeros;
                    }else{
                        lista[2] = lista[2]+","+node.HojaR.primeros;
                    }
                    this.LSiguientes.replace(Integer.parseInt(before), lista);    
                }
                break;
            default:
                for (String before: anteriores){
                    String [] lista = this.LSiguientes.get(Integer.parseInt(before));
                    if(lista[2].isEmpty()){
                        lista[2] = node.HojaL.primeros;
                    }else{
                        lista[2] = lista[2]+","+node.HojaL.primeros;
                    }
                    this.LSiguientes.replace(Integer.parseInt(before), lista);
                }
                break;
        }
    }
    
    public void GraficarArbol(){
        String pathx="./Reportes/Arboles_201700522";
        File directorio = new File(pathx);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        FileWriter fichero;
        PrintWriter escritor;
        try
        {
            fichero = new FileWriter(pathx+"/"+nameAFD+".dot");
            escritor = new PrintWriter(fichero);
            escritor.print("digraph arbol{\n"
                + "rankdir=TB;\n"
                + "forcelabels= true;\n"
                + "node [shape = plaintext];\n");
            escritor.print(graphArbol(this.raiz));
            escritor.print("\n}");
            fichero.close();
            Runtime rt = Runtime.getRuntime();
            rt.exec( "dot -Tjpg -o "+pathx+"/"+nameAFD+".jpg graf "+pathx+"/"+nameAFD+".dot");
        }catch(IOException e){
            System.out.println("Error al crear arbol");
        } 
    }
    
    public String graphArbol(NHoja nodo){
        int cod = this.aux;
        int cod2 = 0;
        int cod3 = 0;
        String anulable = (nodo.anulable)?"V":"F";
        String Tablanodo = "<<table border = '0' cellboder = '1' CELLSPACIONG='0'>\n"+
                "<tr>\n"
                + "<td></td>\n"
                + "<td>"+anulable+"</td>\n"
                + "<td></td>\n"
                + "</tr>\n"+
                "<tr>\n"
                + "<td>"+nodo.primeros+"</td>\n"
                + "<td border='1'>"+nodo.valor+"</td>\n"
                + "<td>"+nodo.ultimos+"</td>"
                + "</tr>\n"+
                "<tr>\n"
                + "<td></td>\n"
                + "<td>"+nodo.ID_nodo+"</td>\n"
                + "<td></td>\n"
                + "</tr>\n"
                + "</table>>";
        String text = "nodo"+this.aux+" [label = "+Tablanodo+"];\n";
        this.aux++;
        if(nodo.HojaL!=null){
            cod2 = this.aux;
            text+= graphArbol(nodo.HojaL);
            this.aux++;
        }
        if(nodo.HojaR!=null){
            cod3 = this.aux;
            text+= graphArbol(nodo.HojaR);
            this.aux++;
        }
        if(!"hoja".equalsIgnoreCase(nodo.tipo)){
            if(cod2!=0){
                text+= "nodo"+cod+"->"+"nodo"+(cod2)+"\n";
            }
            if(cod3!=0){
                text+= "nodo"+cod+"->"+"nodo"+(cod3)+"\n";
            }
        }
        return text;
    }
    
}
