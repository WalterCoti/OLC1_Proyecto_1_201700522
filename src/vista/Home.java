/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;
/**
 *
 * @author GustavC
 */
import analizador.Lexico;
import analizador.sintactico;
import structs.TErrores;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import structs.AFD;
import structs.NValidacion;


        
        
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    String pathFile = "";
    String[] names;
    int cont = 0;
    RSyntaxTextArea textArea = new RSyntaxTextArea();
    RTextScrollPane scrpanel = new RTextScrollPane(textArea);
    public List<TErrores> LGlobalErroreses = new ArrayList<TErrores>();
    public List<NValidacion> LstValidacion = new ArrayList<NValidacion>();
    public static Map<String, AFD> arboles = new HashMap<>();
    DefaultListModel<String> model = new DefaultListModel<>();
        
    public Home() {
        //compilador lexico y sintactico
        try{
            String ruta  = "src/analizador/";
            String opcFLex[] = {ruta+"lexico.jflex","-d",ruta};
            jflex.Main.generate(opcFLex);
            String opcCup[] = {"-destdir",ruta,"-parser","sintactico",ruta+"sintactico.cup"};
            java_cup.Main.main(opcCup);
        } catch(Exception e){
            System.out.println(e);
        }
        
        initComponents();
        jPcode.add(scrpanel);
        redirectSystemStreams(); //Capturar lo que se imprima en consola
        configTExtArea();
        ShowImagenes("","");
 
    }

   
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem3 = new javax.swing.JMenuItem();
        jPcode = new javax.swing.JPanel();
        BtnGen_Automata = new javax.swing.JButton();
        BtnAnalisis = new javax.swing.JButton();
        JcomboGraph = new javax.swing.JComboBox<>();
        BtnBack = new javax.swing.JButton();
        BtnNext = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Consola = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        JListGraph = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        JLImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        BtnOpenFile = new javax.swing.JMenuItem();
        BtnSaveFile = new javax.swing.JMenuItem();
        BtnSaveAsFile = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        GenerateJson = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemJSON = new javax.swing.JMenuItem();
        itemErrores = new javax.swing.JMenuItem();

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPcode.setLayout(new java.awt.CardLayout());

        BtnGen_Automata.setText("Generar Automata");
        BtnGen_Automata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGen_AutomataActionPerformed(evt);
            }
        });

        BtnAnalisis.setText("Analizar Entradas");
        BtnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAnalisisActionPerformed(evt);
            }
        });

        JcomboGraph.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arboles", "Siguientes", "Transiciones", "Automatas" }));
        JcomboGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcomboGraphActionPerformed(evt);
            }
        });

        BtnBack.setText("Anterior");
        BtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackActionPerformed(evt);
            }
        });

        BtnNext.setText("Siguiente");
        BtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNextActionPerformed(evt);
            }
        });

        Consola.setEditable(false);
        Consola.setColumns(20);
        Consola.setRows(5);
        jScrollPane2.setViewportView(Consola);

        JListGraph.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JListGraphValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(JListGraph);

        jScrollPane3.setViewportView(JLImagen);

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        BtnOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        BtnOpenFile.setText("Abrir");
        BtnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOpenFileActionPerformed(evt);
            }
        });
        jMenu1.add(BtnOpenFile);

        BtnSaveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        BtnSaveFile.setText("Guardar");
        BtnSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveFileActionPerformed(evt);
            }
        });
        jMenu1.add(BtnSaveFile);

        BtnSaveAsFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        BtnSaveAsFile.setText("Guardar Como");
        BtnSaveAsFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSaveAsFileActionPerformed(evt);
            }
        });
        jMenu1.add(BtnSaveAsFile);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem2.setText("Generar Automata");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        GenerateJson.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        GenerateJson.setText("Generar JSON ");
        GenerateJson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateJsonActionPerformed(evt);
            }
        });
        jMenu1.add(GenerateJson);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reportes");

        itemJSON.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemJSON.setText("JSON");
        itemJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemJSONActionPerformed(evt);
            }
        });
        jMenu2.add(itemJSON);

        itemErrores.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemErrores.setText("Errores");
        itemErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemErroresActionPerformed(evt);
            }
        });
        jMenu2.add(itemErrores);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPcode, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(BtnGen_Automata, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(BtnAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(JcomboGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JcomboGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPcode, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BtnGen_Automata, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(BtnAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(BtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(BtnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void configTExtArea(){
        try {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CLOJURE);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
     //Codigo copiado de esta pagina no me saquen de la U ^_^
 /*---------------------- https://billwaa.wordpress.com/2011/11/14/java-gui-console-output/ ----------------------*/
  private void updateTextArea(final String text) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Consola.append(text);
      }
    });
  }

  private void redirectSystemStreams() {
    OutputStream out = new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        updateTextArea(String.valueOf((char) b));
      }
 
      @Override
      public void write(byte[] b, int off, int len) throws IOException {
        updateTextArea(new String(b, off, len));
      }
 
      @Override
      public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
      }
    };
 
    System.setOut(new PrintStream(out, true));
    System.setErr(new PrintStream(out, true));
  }
    /*---------------------- https://billwaa.wordpress.com/2011/11/14/java-gui-console-output/ ----------------------*/
    
    private void opnFile()  throws IOException {
        JFileChooser jfilech = new JFileChooser("./..");
        jfilech.setFileFilter(new FileNameExtensionFilter("Archivos Expresiones .exp", "exp"));
        int open = jfilech.showDialog(null, "Open");
        if (open == JFileChooser.APPROVE_OPTION) {
            FileReader fileRead = null;
            BufferedReader bufferRead = null;
            try {
                File archivo = jfilech.getSelectedFile();
                String rut = jfilech.getSelectedFile().getAbsolutePath();
                if (rut.endsWith(".exp")|| rut.endsWith(".EXP")) {
                    fileRead = new FileReader(archivo);
                    bufferRead = new BufferedReader(fileRead);
                    pathFile = archivo.getAbsolutePath();
                    String linea;
                    
                    while ((linea = bufferRead.readLine()) != null) {
                        textArea.setText(textArea.getText()+linea+"\n");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Archivo invalido", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (null != fileRead) {
                        fileRead.close();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    
    public void SaveAsFile(){
            try
            {
             JFileChooser file = new JFileChooser();
             file.showSaveDialog(this);
             File filesave = file.getSelectedFile();

             if(filesave != null)
             {
               FileWriter  save = new FileWriter(filesave+".exp");
               save.write(textArea.getText());
               save.close();
               JOptionPane.showMessageDialog(null,"Arrchivo guardado exitosamente","Informaci??n",JOptionPane.INFORMATION_MESSAGE);
               }
            }
             catch(IOException ex)
             {
              JOptionPane.showMessageDialog(null,"Archivo no guardado","Advertencia",JOptionPane.WARNING_MESSAGE);
             }
 }
        
    
    public void ShowImagenes(String grafica,String nombre_){  
        String path="";
        if(!grafica.isEmpty()){
            switch(grafica){
                case "Arboles":
                    path = "./Reportes/Arboles_201700522/"+nombre_+".jpg";
                    break;
                case "Siguientes":
                    path = "./Reportes/Siguientes_201700522/"+nombre_+".jpg";
                    break;
                case "Transiciones":
                    path = "./Reportes/Transiciones_201700522/"+nombre_+".jpg";
                    break;
                case "Automatas":
                    path = "./Reportes/AFD_201700522/"+nombre_+".jpg";
                    break;           
            }
            try {

                ImageIcon graph = new ImageIcon(path);
                JLImagen.setIcon(graph);
            } catch (Exception e) {
            }
        }
    }    
    
    private void BtnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOpenFileActionPerformed
      
      try {
            opnFile();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex+"" +"\nArchivo no encontrado","ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BtnOpenFileActionPerformed

    //GUARDAR
    private void BtnSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveFileActionPerformed
        try
        {
        if(pathFile != ""){
            File tmpfile = new File(pathFile);
            FileWriter  save = new FileWriter(tmpfile);
            save.write(textArea.getText());
            save.close();
            System.out.println("Cambios Guardados");            
        }else{
            SaveAsFile();
        }
        }
        catch(IOException ex)
        {
         JOptionPane.showMessageDialog(null,"Archivo no guardado","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_BtnSaveFileActionPerformed

    //GUARDAR COMO
    private void BtnSaveAsFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSaveAsFileActionPerformed
        SaveAsFile();
    }//GEN-LAST:event_BtnSaveAsFileActionPerformed

    private void BtnGen_AutomataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGen_AutomataActionPerformed
        GenerateAutomatas();
    }//GEN-LAST:event_BtnGen_AutomataActionPerformed

    private void BtnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAnalisisActionPerformed
        analizarContent(textArea.getText());
    }//GEN-LAST:event_BtnAnalisisActionPerformed

    private void BtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackActionPerformed
        if(JListGraph.getSelectedIndex()!=0){
            JListGraph.setSelectedIndex(JListGraph.getSelectedIndex()-1);
        }
               
                
    }//GEN-LAST:event_BtnBackActionPerformed

    //GENERAR AUTOMATA
    private void GenerateJsonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateJsonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerateJsonActionPerformed
        //NewFIle
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        pathFile = "";
        textArea.setText("");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void JcomboGraphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcomboGraphActionPerformed
        // TODO add your handling code here:
        ShowImagenes(JcomboGraph.getSelectedItem().toString(), JListGraph.getSelectedValue());
    }//GEN-LAST:event_JcomboGraphActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        GenerateAutomatas();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void JListGraphValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JListGraphValueChanged
        ShowImagenes(JcomboGraph.getSelectedItem().toString(), JListGraph.getSelectedValue());
    }//GEN-LAST:event_JListGraphValueChanged

    private void itemJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemJSONActionPerformed
        FileSystem fs = FileSystems.getDefault();//Creamos un File System para poder manejar ficheros
        Path pathfil=fs.getPath("./Reportes/Salidas_201700522/");
        try {
            Process p = new ProcessBuilder("explorer.exe","/select,"+pathfil.toAbsolutePath()).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_itemJSONActionPerformed

    private void BtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNextActionPerformed
       if(JListGraph.getSelectedIndex()!=(JListGraph.getModel().getSize()-1)){
            JListGraph.setSelectedIndex(JListGraph.getSelectedIndex()+1);
        }
    }//GEN-LAST:event_BtnNextActionPerformed

    private void itemErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemErroresActionPerformed
        FileSystem fs = FileSystems.getDefault();//Creamos un File System para poder manejar ficheros
        Path pathfil=fs.getPath("./Reportes/Errores_201700522/");
        try {
            Process p = new ProcessBuilder("explorer.exe","/select,"+pathfil.toAbsolutePath()).start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }//GEN-LAST:event_itemErroresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    
    public void GenerateAutomatas(){
        JListGraph.setModel(model);
        try {
            
            for(AFD arbol: arboles.values()){//recorrer la lista de arboles
                arbol.GenerarAutomatas();
                model.add(cont,arbol.nameAFD);
            }
            JListGraph.setSelectedIndex(0);
            ShowImagenes(JcomboGraph.getSelectedItem().toString(), JListGraph.getSelectedValue());
             
        } catch (Exception ex) {
            System.out.println("Error analisis de entrada");
        } 
    }
    
    public void analizarContent(String texto){
        
        try {
            Lexico nlex = new Lexico(new StringReader(textArea.getText()));
            sintactico sintact_ = new sintactico(nlex);
            sintact_.parse();
            arboles.putAll(sintact_.Lexpresiones);
            
            //System.out.println("No. de Arboles "+arboles.size());
            LGlobalErroreses.addAll(nlex.LError);
            LGlobalErroreses.addAll(sintact_.LErrSintact);
            LstValidacion.addAll(sintact_.Lvalidar);
            for(NValidacion nodo: LstValidacion){
                if(arboles.containsKey(nodo.nameAFD)){
                    if(arboles.get(nodo.nameAFD).ValidarCadena(nodo.cadena)){
                        System.out.println("La cadena : " + nodo.cadena +" ES valida con la Expresion Reg: "+ nodo.nameAFD);
                        nodo.salida = "Cadena Valida";
                    }else{
                        System.out.println("La cadena : " + nodo.cadena +" NO es valida con la Expresion Reg: "+ nodo.nameAFD);
                        nodo.salida = "Cadena Invalida";
                    }
                     
                }else{
                    System.out.println("No existe ninguna Expresion Regular con este Nombre " + nodo.nameAFD);
                }
            }
            crearJson();
            if(LGlobalErroreses.size()!=0){
                ReporteErrores();
            }
           // System.out.println("Tama??o lista Errores Lista Global "+ LGlobalErroreses.size());
        } catch (Exception e) {
            System.out.println("");
        }
        
    
        
   
    }
    
    public void crearJson(){
        String path = "./Reportes/Salidas_201700522/";
        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String nameFile = "Salida" + dtf5.format(LocalDateTime.now());
        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        FileWriter file_json;
        PrintWriter writefil;
       try {
           
            file_json= new FileWriter(path+nameFile+".json");
            writefil = new PrintWriter(file_json);
            JSONArray list = new JSONArray();
            for(NValidacion n: LstValidacion){
            JSONObject obj = new JSONObject();
            obj.put("Valor", n.cadena);
            obj.put("ExpresionRegular", n.nameAFD);
            obj.put("Resultado", n.salida);
            list.add(obj);
            }
            writefil.print(list.toJSONString());
            file_json.close();
            System.out.println("JSON Creado Exitosamente");
        } catch (IOException e) {
           System.out.println("nel no deja v,:");
        }
        
        
    }

    public void ReporteErrores(){
        int cont = 1;
        String path = "./Reportes/Errores_201700522/";
        DateTimeFormatter dtf5 = DateTimeFormatter.ofPattern("yyyyMMddhhmm");
        String nameFile = "errores" + dtf5.format(LocalDateTime.now());
        File directorio = new File(path);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        FileWriter file_html;
        PrintWriter writefil;
       try {
           
            file_html= new FileWriter(path+nameFile+".html");
            writefil = new PrintWriter(file_html);
            writefil.print("<html>\n" +
                            "<head>\n" +
                            "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n" +
                            "    <title>Reporte de Errores</title>\n" +
                            "</head>\n" +
                            "<body style=\"text-align:center\">\n" +
                            "    <h1>Reporte de Errores</h1>\n" +
                            "    <table class=\"table table-dark table-striped\" style=\"text-align:center\">\n" +
                            "        <thead>\n" +
                            "            <tr>\n" +
                            "              <th scope=\"col\">#</th>\n" +
                            "              <th scope=\"col\">Lexema</th>\n" +
                            "              <th scope=\"col\">Tipo</th>\n" +
                            "              <th scope=\"col\">Linea</th>\n" +
                            "              <th scope=\"col\">Columna</th>\n" +
                            "              <th scope=\"col\">Descripcion</th>\n" +
                            "            </tr>\n" +
                            "          </thead>\n" +
                            "          <tbody>");
            for(TErrores E: LGlobalErroreses){
                writefil.print( "          <tr>\n"+
                                "              <th scope=\"row\">"+cont+"</th>\n"+
                                "              <th>"+E.lexema+"</th>\n"+
                                "              <th>"+E.tipo+"</th>\n"+
                                "              <th>"+E.line+"</th>\n"+
                                "              <th>"+E.col+"</th>\n"+
                                "              <th>"+E.descript+"</th>\n"+
                                "          </tr>\n");
                cont++;
            }
            writefil.print( "          </tbody>\n" +
                            "      </table>\n" +
                            "</body>\n" +
                            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\n" +
                            "</html>");
            file_html.close();
            System.out.println("Reporte de Errores Generado");
        } catch (IOException e) {
           System.out.println("");
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAnalisis;
    private javax.swing.JButton BtnBack;
    private javax.swing.JButton BtnGen_Automata;
    private javax.swing.JButton BtnNext;
    private javax.swing.JMenuItem BtnOpenFile;
    private javax.swing.JMenuItem BtnSaveAsFile;
    private javax.swing.JMenuItem BtnSaveFile;
    private javax.swing.JTextArea Consola;
    private javax.swing.JMenuItem GenerateJson;
    private javax.swing.JLabel JLImagen;
    private javax.swing.JList<String> JListGraph;
    private javax.swing.JComboBox<String> JcomboGraph;
    private javax.swing.JMenuItem itemErrores;
    private javax.swing.JMenuItem itemJSON;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPcode;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
