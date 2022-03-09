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
    public String nameAFD;
    NHoja raiz;
    int id = 1;
    int aux =  1;
    List<String> alfabeto = new ArrayList<>();
    List<String[]> transiciones = new ArrayList<>();   
    List<String> terminales = new ArrayList<>();
    Map<String,String> conjuntos = new HashMap<>();
    Map<String, String> Lalfabeto = new HashMap<>();
    Map<Integer, String[]> LSiguientes = new HashMap<>();
    Map<String, String[]> LTransiciones = new HashMap<>();
    
    public AFD(String nombreAFD_, Map<String,String> conjuntos_,NHoja raiz_){
        this.nameAFD=nombreAFD_;
        this.conjuntos.putAll(conjuntos_);
        this.raiz = raiz_;
        try {
            generarDatos();
            CrearTransiciones();
          
        } catch (Exception e) {
            //System.out.println(e+"Es aquihdspm");
        }
        
    }
    
    public void GenerarAutomatas(){
        GraficarArbol();
        GraficarSiguientes();
        GraficarTransiciones();
        GraficarAFD();
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
        
        if("h".equals(node.tipo)){
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
            node = damePri_Ult(node);
        }
        if(".".equals(node.tipo) || "*".equals(node.tipo) || "+".equals(node.tipo)){
            genSiguientes(node);
        }
        return node;
    }
    
    public NHoja damePri_Ult(NHoja node){
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
        String pathx="./Reportes/Arboles_201700522/";
        CrearDirectorio(pathx);
        FileWriter file_dot;
        PrintWriter writefil;
        try
        {
            file_dot = new FileWriter(pathx+nameAFD+".dot");
            writefil = new PrintWriter(file_dot);
            writefil.println("digraph arbol{\n"
                + "rankdir=TB;\n"
                + "forcelabels= true;\n"
                + "node [shape = plaintext];\n");
            writefil.println(graphArbol(this.raiz));
            writefil.println("\n}");
            file_dot.close();
            Runtime rt = Runtime.getRuntime();
            rt.exec( "dot -Tjpg -o "+pathx+nameAFD+".jpg graf "+pathx+nameAFD+".dot");
            System.out.println("Arbol \"" + nameAFD + "\" creado exitosamente");
        }catch(IOException e){
            System.out.println("Error al crear arbol");
        } 
    }
    
    public String graphArbol(NHoja node){
        int padre = this.aux;
        int hijoL = 0;
        int hijoR = 0;
        String anulable = (node.anulable)?"V":"F";
        String Tablanodo = "<<table border = '0'>\n"+
                "<tr>\n"
                + "\t<td></td>\n"
                + "\t<td>"+anulable+"</td>\n"
                + "\t<td></td>\n"
                + "</tr>\n"+
                "<tr>\n"
                + "\t<td>"+node.primeros+"</td>\n"
                + "\t<td border='1'>"+node.valor+"</td>\n"
                + "\t<td>"+node.ultimos+"</td>"
                + "</tr>\n"+
                "<tr>\n"
                + "\t<td></td>\n"
                + "\t<td>"+getID(node.ID_nodo)+"</td>\n"
                + "\t<td></td>\n"
                + "\t</tr>\n"
                + "</table>>";
        String text = "hoja"+this.aux+" [label = "+Tablanodo+"];\n";
        this.aux++;
        if(node.HojaL!=null){
            hijoL = this.aux;
            text+= graphArbol(node.HojaL);
            this.aux++;
        }
        if(node.HojaR!=null){
            hijoR = this.aux;
            text+= graphArbol(node.HojaR);
            this.aux++;
        }
        if(!"h".equals(node.tipo)){
            if(hijoL!=0){
                text+= "hoja"+padre+"->"+"hoja"+(hijoL)+"\n";
            }
            if(hijoR!=0){
                text+= "hoja"+padre+"->"+"hoja"+(hijoR)+"\n";
            }
        }
        return text;
    }
    public String getID(int valor){
        if(valor==0){
            return "";
        }
        return Integer.toString(valor);
    }
    
     public void GraficarSiguientes(){
        String pathx="./Reportes/Siguientes_201700522/";
        CrearDirectorio(pathx);
        FileWriter file_dot;
        PrintWriter writefil;
        try
        {
            file_dot = new FileWriter(pathx+nameAFD+".dot");
            writefil = new PrintWriter(file_dot);
            writefil.println("digraph grphsiguientes{\n"
                + "rankdir=LR;\n"
                + "forcelabels= true;\n"
                + "node [shape = plain];\n");
            String td = "";
            for(String[] dato: this.LSiguientes.values()){
                td+="<tr>\n"
                    + "\t<td>"+dato[0]+"</td>\n"
                    + "\t<td>"+dato[1]+"</td>\n"
                    + "\t<td>"+dato[2]+"</td>\n"
                    + "</tr>\n";
            }
            String tabla = "<<table border = '1' cellboder = '1' cellspacing='0' cellpadding='10'>\n"
                    + "<tr>\n"
                    + "\t<td COLSPAN='2'>HOJA</td>\n"
                    + "\t<td>Siguientes</td>\n"
                    + "</tr>\n"
                    + td
                    + "</table>>";
            String text = "nodo"+this.aux+" [label = "+tabla+"];\n";
            writefil.println(text);
            writefil.println("\n}");//fin
            writefil.close();
            Runtime rt = Runtime.getRuntime();
            rt.exec( "dot -Tjpg -o " +pathx+nameAFD+".jpg graf "+pathx+nameAFD+".dot");
            System.out.println("Tabla de Siguientes  \"" + nameAFD + "\" creado exitosamente");
        }catch(IOException e){
            System.out.println("Error al graficar los Siguientes");
        }  
    }
    
   
    public void CrearTransiciones(){
        String [] datos = {"S0",this.raiz.primeros};
        this.LTransiciones.put(this.raiz.primeros,datos);
        createTransition(this.LTransiciones.get(this.raiz.primeros));
    }
    
    public void createTransition(String[] Tra){
        boolean ter = false;
        List<String[]> Ltmptransiciones = new ArrayList<>();
        for(String y: this.alfabeto){
            Map<String, String> Ltmptrans = new HashMap<>();
            String trans = "";
            for(String k: Tra[1].split(",")){
                String[] sig = this.LSiguientes.get(Integer.parseInt(k));
                if(y.equals(sig[0])){
                    for(String g: sig[2].split(",")){
                        if(!Ltmptrans.containsKey(g)){
                            if(Integer.parseInt(g) == id-1){
                                ter = true;
                            }
                            Ltmptrans.put(g, g);
                            if(trans.isEmpty()){
                                trans = g;
                            }else{ trans+= ","+g;}
                        }
                    }
                }
            }
            if(!this.LTransiciones.containsKey(trans) && !trans.isEmpty()){
                String[] D = {"S"+this.LTransiciones.size(), trans};
                String[] dd = {Tra[0],D[0],y};
                transiciones.add(dd);
                Ltmptransiciones.add(D);
                if(ter){terminales.add(D[0]);}
                this.LTransiciones.put(trans,D);
            }else{
                if(!trans.isEmpty()){
                    String [] N = this.LTransiciones.get(trans);
                    String [] dd = {Tra[0],N[0],y};
                    transiciones.add(dd);
                }
            }
        }
        for(String[] G: Ltmptransiciones){
            createTransition(G);
        }
    }
    
     public void GraficarTransiciones(){
        String pathx="./Reportes/Transiciones_201700522/";
        CrearDirectorio(pathx);
        FileWriter file_dot;
        PrintWriter writefil;
        try
        {
            file_dot = new FileWriter(pathx+nameAFD+".dot");
            writefil = new PrintWriter(file_dot);
            writefil.println("digraph Transiciones{\n"
                + "rankdir=LR;\n"
                + "forcelabels= true;\n"
                + "node [shape = plain];\n");
            String td = "";
            for(String[] y: LTransiciones.values()){
                td+="<tr>\n";
                td+="<td> "+y[0]+" {"+y[1]+"} </td>\n";
                for (String x: alfabeto) {
                    boolean encontrado = false;
                    for(String[] dato: this.transiciones){
                        if (dato[2].equals(x) && y[0].equals(dato[0])) {
                            td+="<td> "+dato[1]+" </td>\n";
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        td+="<td> -- </td>\n";
                    }
                }
                td+= "</tr>\n";
            }
            String tabla = "<<table border = '1' cellboder = '1' cellspacing='0' cellpadding='10'>\n"
                    + "<tr>\n"
                    + "<td>ESTADO</td>\n";
            for (String x: alfabeto) {
                tabla+="<td>"+x+"</td>\n";
            }
            tabla += "</tr>\n"
                    + td
                    + "</table>>";
            String text = "nodo"+this.aux+" [label = "+tabla+"];\n";
            writefil.println(text);
            writefil.println("\n}");
            file_dot.close();
            Runtime rt = Runtime.getRuntime();
            rt.exec( "dot -Tjpg -o " + pathx + nameAFD+".jpg graf " + pathx + nameAFD + ".dot");
            System.out.println("Transiciones de \"" + nameAFD + "\" creado exitosamente");
        }catch(IOException e){
            System.out.println("Error al graficar la tabla de transiciones");
        }
    }
    
    
    public void GraficarAFD(){
        String pathx="./Reportes/AFD_201700522/";
        CrearDirectorio(pathx);
        FileWriter file_dot;
        PrintWriter writefil;
        try
        {
            file_dot = new FileWriter(pathx+nameAFD+".dot");
            writefil = new PrintWriter(file_dot);
            writefil.println("digraph GraphAFD{\n"
                + "rankdir=LR;\n"
                + "forcelabels= true;\n"
                + "node [shape = circle];\n");
            for(int x=0; x<this.LTransiciones.size();x++){
                if (terminales.contains("S"+x)){
                    writefil.println("S"+x+" [label = \""+"S"+x+"\", shape = doublecircle];\n");
                }else{
                    writefil.println("S"+x+" [label = \""+"S"+x+"\"];\n");
                }
            }
            for(String[]k: transiciones){
                String dat = k[2];
                if("\\n".equals(k[2])){
                    dat = "\\\\n";
                }
                if(" ".equals(k[2])){
                    dat = "\\\" \\\"";
                }
                writefil.println(k[0]+"->"+k[1]+"[label=\""+dat+"\"]\n");
            }
            writefil.print("\n}");
            file_dot.close();
            Runtime rt = Runtime.getRuntime();
            
            rt.exec( "dot -Tjpg -o " + pathx + nameAFD+".jpg graf " + pathx + nameAFD + ".dot");
            System.out.println("Grafo " + nameAFD + " creado exitosamente");
        }catch(IOException e){
            System.out.println("Error al crear AFD");
        }
    }
    
    public void CrearDirectorio(String path){
        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
    }
    
    public boolean ValidarCadena(String cadena){
        String actual = "S0";
        boolean respuesta = false;
        boolean existe = false;
        for(int x=0; x<cadena.length(); x++){
            respuesta = false;
            existe = false;
            char carAct = cadena.charAt(x);
            for(String[] estado: this.transiciones){
                if(carAct=='\\'){
                    if((x+1)<cadena.length()){
                        if(cadena.charAt(x+1)=='\''){
                            carAct = '\'';
                            x++;
                        }else if(cadena.charAt(x+1)=='n'){
                            carAct = '\n';
                            x++;
                        }else if(cadena.charAt(x+1)=='\"'){
                            carAct = '\"';
                            x++;
                        }
                    }
                }
                if(estado[0].equals(actual)){
                    String alfabeto = estado[2]; 
                    
                    if(conjuntos.get(estado[2])!=null){
                        
                        respuesta = ValidarConjunto((int)carAct, conjuntos.get(estado[2]));
                        if (respuesta){
                            actual = estado[1];
                            existe=true;
                        }
                    }
                    else{
                        char alfa = estado[2].charAt(0);
                        if(estado[2].length()==2 && alfa=='\\'){
                            if(estado[2].charAt(1)=='\''){
                                alfa = '\'';
                            }else if(estado[2].charAt(1)=='n'){
                                alfa = '\n';
                            }else if(estado[2].charAt(1)=='\"'){
                                alfa = '\"';
                            }
                        }
                        if((int)carAct == (int)alfa){
                            actual = estado[1];
                            respuesta = true;
                        }else{respuesta = false;}
                    }
                }
            }
        }
        if(terminales.contains(actual) && existe){
            respuesta = true;
        }else{
            respuesta = false;
        }
        return respuesta;
    }
    
     public boolean ValidarConjunto(int caracter, String x){
        if(x.split("~").length==2){//si es un rango
            int min = x.charAt(0);
            int max = x.charAt(2);
            
            if(min>max){
                int aux = max;
                min = max;
                max = aux;
            }
            if((int)caracter >= min && (int)caracter<=max){
                return true;
            }
            return false;
        }
        else{
            for(String chaar: x.split(",")){    // si es una serie
                int val = (int)chaar.charAt(0);
                if(caracter == val){
                    return true;
                }
            }
            return false;
        }
    }
    
  
}
