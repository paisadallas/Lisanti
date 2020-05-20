/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armadopalletv1;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Fredy
 */
public class JFramePallet extends javax.swing.JFrame {

    String titulos[]={"LOCACION","CODIGO","DESCRIPCION","SIZE","QTY"};
    String datos[][]={};
    DefaultTableModel md;
    DefaultTableModel tablaProducto;
    int filasExcel=35;  
    String [] ArrayLocacion;
    String [] ArrayCajas;
    public JFramePallet(File fileName) {
        initComponents();
        
         //INICIO LEER EXCEL
        List cellData = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XSSFWorkbook workBook = new XSSFWorkbook (fileInputStream);
            XSSFSheet hssfSheet= workBook.getSheetAt(0); //OBTENEMOS LA PRIMERA HOJA
                      
            Iterator rowIterator = hssfSheet.rowIterator(); //OBTENEMOS LAS FILAS
            
            while (rowIterator.hasNext()) {         
                
                XSSFRow hssfRow = (XSSFRow)rowIterator.next(); //Obtenemos datos celda 0
                Iterator iterator = hssfRow.cellIterator();
                List cellTemp= new ArrayList();
                
                while (iterator.hasNext()) {                    //RECORREMOS LAS CELDAS DE CADA FILA
                    XSSFCell hssfCell = (XSSFCell) iterator.next();
                    cellTemp.add(hssfCell);
                }
                cellData.add(cellTemp);
            } //HASTA AQUI VA EL CONSTRUCTOR
        } catch (Exception e) {     //FIN TRY
            e.printStackTrace();
        }
        obtener(cellData);
        
    }
    
    //Importo los datos cargados
       public void obtener(List cellDataList){
       //CREO MI MATRIZ
        String [][] matriz = null;
           String[]casa= new String[4];
           String [] casa2= new String[3];
           String columna0,columna1,columna2,columna3;
           String meterDatos[]= new String[4];
          String datosTabla[][]={};
          String titulosTabla[]={"DESCRIPTION","QUALITY","LOCATION"};  //Determino el tamaño
          tablaProducto= new DefaultTableModel(datosTabla, titulosTabla);
          jtableProductos.setModel(tablaProducto);
           
           Object [][]miobjeto = null;
//        for(int i=0; i<cellDataList.size();i++){  //DATOS METIDOS POR CONSOLA USUARIO!!!
          for(int i=0; i<filasExcel;i++){
            List cellTempList= (List) cellDataList.get(i);
 
            for (int j=0; j< cellTempList.size(); j++){
              matriz= new String[cellDataList.size()][cellTempList.size()];
               miobjeto= new Object[cellDataList.size()][cellTempList.size()];
               
               
                XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
                String stringCellValue = hssfCell.toString(); //OBTENEMOS EL VALOR Y LO PASAMOS A STRING
              //  System.out.print(stringCellValue+" "); //IMPRIMIMOS
             
              matriz[i][j]= stringCellValue; //SEGUN J Organizo la columna
              casa[j]=stringCellValue;
              if(j==0){
              columna0=stringCellValue;
              meterDatos[0]=stringCellValue;
              }
               if(j==1){
             columna1=stringCellValue;
             
             String filtro= columna1;
             
             filtro= FiltroNo(filtro);
               meterDatos[1]=filtro;

                  
                             
              }
                if(j==2){
             columna2=stringCellValue;                                 
             String filtro = null;
             filtro= columna2.replaceAll("O","0");
             filtro= filtro.replace(" ","");
           
             meterDatos[2]=filtro;
              }
                 if(j==3){
              columna3=stringCellValue;
              meterDatos[3]=stringCellValue;
            
              }

             // tablaProducto.addRow(casa);
            } // fin J
          tablaProducto.addRow(meterDatos);   //INGRESO LOS DATOS WINNER WINNER CHICKEN DINNER
          
        } //FIN I

    }
       
   
       public void validarDatos (){
           //DECLARO VARIABLES
           int NoDatos = jtableProductos.getRowCount(); //OBTENGO LA CANTIDAD DE FILAS            
         
           ArrayLocacion= new  String[NoDatos];
           ArrayCajas = new String [NoDatos];
           int decremento=0;
           int moverVector=0;
           int []posicionBorrar= new int[20];
           int cajasActuales=0;
           int cajasRepetidas=0;
           int cajasRepetidasAux=0;
           int cajasTotales=0;
           int posicionSumar=0;
           String cadena1Locacion;
           String cadena1Producto;
           char [] caracteres1;
        for (int j= 0; j <(NoDatos); j++) {   //DEBE IR HASTA NoDatos                              01-25/ 5:25 AM 
            
            //---------CREAMOS NUESTRAS VARIABLES ------------         
            String  cadena2Locacion;
            String cajasCadena2;
            String cadena2Producto;   
            
            // -------- INICIALIZAMOS  VARIABLES --------------
            cadena2Locacion= (String) jtableProductos.getValueAt(j, 2); 
            cadena2Producto = (String) jtableProductos.getValueAt(j, 0); 
            cajasCadena2 = (String) jtableProductos.getValueAt(j, 1); 
            cajasRepetidas=0;
            
            char [] caracteres2 = cadena2Producto.toCharArray();  //FILTRO POR CARACTERES
            
        //------ SEGUNDO CICLO FOR ---------
        for (int i = (NoDatos-1); i > j; i--) {   
        //------------ INICIALIZAMOS VARIABLES -----------------
       cadena1Locacion= (String) jtableProductos.getValueAt(i, 2);
       cadena1Producto = (String) jtableProductos.getValueAt(i, 0);
        String cajasCadena1Locacion = (String) jtableProductos.getValueAt(i, 1);
        cajasTotales=0;  //ACTUALIZO LOS VALORES
        cajasActuales =   Integer.parseInt(cajasCadena2); //CUENTO MIS CAJAS ACTUALES
        caracteres1 = cadena1Producto.toCharArray();      //FILTRO POR CARACTERES
        
        //-------------- CICLO COMPARAR IGUALDADES ---------------------------
                    if(cadena1Locacion.equals(cadena2Locacion) &&       //TRES NIVELES DE FILTROS SIEMPRE
                       caracteres1[0]==caracteres2[0]          &&
                       caracteres1[1]==caracteres2[1]          &&
                       caracteres1[3]==caracteres2[3]                                                     
                        ){
                        
                       if(cadena1Locacion.equals("FA54B") ||
                          cadena1Locacion.equals("FC18A") ||     
                          cadena1Locacion.equals("FE16B") ||    
                          cadena1Locacion.equals("FE42B") ||    
                          cadena1Locacion.equals("FF47B")                                                    
                          )
                       {
                           System.err.println("Cadena 1 = "+cadena1Producto);
                           System.err.println("Cadena 2 = "+cadena2Producto);
                             int tamano1= NoLetras(cadena1Producto);
                             int tamano2= NoLetras(cadena2Producto);
                             
                             System.err.println("tamano 1= "+tamano1);
                             System.err.println("tamano 2= "+tamano2);
                           
                           if(tamano1==tamano2){
                               // -------------------ULTIMO FILTRO -----------------------                      NOTA!
                               if(cadena1Locacion.equals("FE16B") || cadena1Locacion.equals("FE42B")){   //DEBO CORREGIR EN FUTURO------------
                                   System.err.println("ENTRANDO OTIS O STUFER");  
                                     if(cadena1Producto.equals(cadena2Producto)){
                                             System.err.println("igualitos");
                                         //-------- ALGORITMO SUPER FILTRADO -----------
                               
                                         cajasRepetidasAux = Integer.parseInt(cajasCadena1Locacion);   //OBTENGO EL NUMERO DE CAJAS DEL ELEMENTO REPETIDO
                            
                                         //--------ALGORITMO--------------
                                          cajasRepetidas= cajasRepetidas + cajasRepetidasAux; //OBTENGO EL TOTAL DE CAJAS REPETIDOS                   
                                          tablaProducto.removeRow(i);     //BORRO LA FILA DE LA POSICION i 
                                           decremento= decremento+1;         // VALIDO NUEVO TAMAÑO DE TABLA
                                           NoDatos = jtableProductos.getRowCount(); // OBTENGO EL NUEVO TAMAÑO DE LOS DATOS EN TABLA
                                     }else{
                                   System.err.println("NADA");                          
                               }
                               
                               }else{
                               }
                       
                               //-------- ALGORITMO SUPER FILTRADO -----------
                               
                                cajasRepetidasAux = Integer.parseInt(cajasCadena1Locacion);   //OBTENGO EL NUMERO DE CAJAS DEL ELEMENTO REPETIDO
                            
                                //--------ALGORITMO--------------
                                 cajasRepetidas= cajasRepetidas + cajasRepetidasAux; //OBTENGO EL TOTAL DE CAJAS REPETIDOS                   
                                 tablaProducto.removeRow(i);     //BORRO LA FILA DE LA POSICION i 
                                 decremento= decremento+1;         // VALIDO NUEVO TAMAÑO DE TABLA
                                 NoDatos = jtableProductos.getRowCount(); // OBTENGO EL NUEVO TAMAÑO DE LOS DATOS EN TABLA
                               
                           }else{
                           
                               System.err.println("estuvistes cerca");
                           }

                       }
                       else{
                       cajasRepetidasAux = Integer.parseInt(cajasCadena1Locacion);   //OBTENGO EL NUMERO DE CAJAS DEL ELEMENTO REPETIDO
                            
                                //--------ALGORITMO--------------
                        cajasRepetidas= cajasRepetidas + cajasRepetidasAux; //OBTENGO EL TOTAL DE CAJAS REPETIDOS                   
                        tablaProducto.removeRow(i);     //BORRO LA FILA DE LA POSICION i 
                        decremento= decremento+1;         // VALIDO NUEVO TAMAÑO DE TABLA
                        NoDatos = jtableProductos.getRowCount(); // OBTENGO EL NUEVO TAMAÑO DE LOS DATOS EN TABLA
                       
                       } //FIN ELSE

                  
                    }                
                   
            }//FIN SEGUNDO CICLO
        //----------- VALIDACION ULTIMO DATO (NECESARIO) -------------------
            if(j==(NoDatos-1)){
               cajasCadena2 = (String) jtableProductos.getValueAt(j, 1);
              cajasActuales =   Integer.parseInt(cajasCadena2);
           }
           cajasTotales=cajasRepetidas+cajasActuales;
           tablaProducto.setValueAt(cajasTotales,j,1);//SOLO SI SE ELIMINA SUMO LAS CAJAS,DONDE ESTA EL CERO VA LA J

       }//FIN PRIMER CICLO

       }
       
   int NoLetras(String miTexto){
   
       miTexto= miTexto.replaceAll(" ", "");
       return miTexto.length();
   }
       
       public void OrganizarDatos(){
       
       }
      
       void ActualizarArray(int datos){
          
           for (int llenarArray = 0; llenarArray < datos; llenarArray++) {
            String caja;
            ArrayLocacion[llenarArray]= (String) jtableProductos.getValueAt(llenarArray, 2);
            ArrayCajas[llenarArray] = (String) jtableProductos.getValueAt(llenarArray, 1);  
       }
       
       }
 
       public String FiltroNo(String valor){
        
           String nuevoValor=valor;
          nuevoValor = nuevoValor.replace(" ","");
           char arrayValor0,arrayValor1,arrayValor2;//CREO MI ARRAY  
           int tamanoValor= valor.length();     // OBTENGO EL TAMAÑO      
//           arrayValor= valor.charAt(0); // PASO STRING A CHAR
           if(tamanoValor==3){
                 arrayValor0= valor.charAt(0);
                 nuevoValor= ""+arrayValor0;
           }
           if(tamanoValor==4){
            arrayValor0= valor.charAt(0);
            arrayValor1= valor.charAt(1);
            nuevoValor= ""+arrayValor0+arrayValor1;
           }
           
           if(tamanoValor==5){
           arrayValor0= valor.charAt(0);
            arrayValor1= valor.charAt(1);
            arrayValor2= valor.charAt(2);
            nuevoValor= ""+arrayValor0+arrayValor1+arrayValor2;            
           }
                   
           
           return nuevoValor;       
       }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtableProductos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jtableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtableProductos);

        jButton1.setText("GO!(2)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ORDENAR(1)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        validarDatos();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    
        Ordenar();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    void Ordenar(){
            int []array = new int[5];
        array[0]=55;
        array[1]=86;
        array [2]=48;
        array[3]=16;
        array[4]=82;
        int aux;
        
        //----------- CODIGO DE ORDENAMIETO BURBUJA ---------
        for (int i = 0; i <= 4; i++) {
            
            for (int j = 4; j >=1; j--) {
                
//                System.err.println("anterior valor="+array[j]);
                if(array[j]<array[j-1]){
                    aux= array[j-1];
                    array[j-1]=array[j];
                    array[j]=aux;
                }            
            }           
        }
      //---------- IMPRIMIR ARRAY ---------
//        System.err.println("Posiciones");
        for (int ver = 0; ver < 5; ver++) {
            
            System.err.println(array[ver]);
        }
        
      //---------- HEXADECIMAL -----------
    String x= "0x"; 
    String hexString= "FF";
    String hex= x+hexString;
    
    String hex2= "0xF9";
    int dec = (Integer.decode(hex)).intValue();
        System.err.println("hex= "+dec);
        
  //--------- CODIGO ORDENAMIENTO JTABLES ----------------------------
 int datosTabla= jtableProductos.getRowCount();
  
  
  String []arrayPosiciones= new String[datosTabla];  //LLENAMOS EL ARREGLO
        for (int llenar = 0; llenar < datosTabla; llenar++) {
         arrayPosiciones[llenar]= (String) jtableProductos.getValueAt(llenar, 2); 
        }
        
         for (int i2 = 0; i2 < datosTabla; i2++) {
            
            for (int j2 = (datosTabla-1); j2 >=1; j2--) {
                String valor = arrayPosiciones[j2];
                String valorMenos= arrayPosiciones[j2-1];
                valor= x+valor;
                valorMenos = x+valorMenos;
                int pos = (Integer.decode(valor)).intValue();               
                int posMenos= (Integer.decode(valorMenos)).intValue(); 
//                System.err.println("anterior valor="+array[j]);
                if(pos < posMenos){
                   String stringAux= arrayPosiciones[j2-1];
                    arrayPosiciones[j2-1]=arrayPosiciones[j2];
                    arrayPosiciones[j2]=stringAux;
                    
                    //--------AQUI INTERCAMBIAMOS VALORES---------
                    IntercambioTabla(j2);
                }            
            }           
        }
        System.err.println("datos Tabla= "+datosTabla);
       
    }
    
    void IntercambioTabla(int j2){
    
           String auxDes= (String) jtableProductos.getValueAt(j2-1, 0);
                    String auxCan=(String) jtableProductos.getValueAt(j2-1, 1);
                    String auxLoca=(String) jtableProductos.getValueAt(j2-1, 2);
                    tablaProducto.setValueAt((String) jtableProductos.getValueAt(j2, 0),(j2-1),0);
                    tablaProducto.setValueAt((String) jtableProductos.getValueAt(j2, 1),(j2-1),1);
                    tablaProducto.setValueAt((String) jtableProductos.getValueAt(j2, 2),(j2-1),2);
                    tablaProducto.setValueAt(auxDes, j2, 0);
                    tablaProducto.setValueAt(auxCan, j2, 1);
                    tablaProducto.setValueAt(auxLoca, j2, 2);
    
    }
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
            java.util.logging.Logger.getLogger(JFramePallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
File f = new File("D:/Proyectos java/Lisanti version1.0/DatosPrueba.xlsx");
        if(f.exists()){
         JFramePallet obj = new JFramePallet(f);
         // filas = 26, columnas = 4
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFramePallet(f).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableProductos;
    // End of variables declaration//GEN-END:variables
}
