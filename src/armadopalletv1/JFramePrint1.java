/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armadopalletv1;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Fredy
 */
public class JFramePrint1 extends javax.swing.JFrame implements Printable
{
DefaultTableModel tablaProducto;

 int filasExcel=34;
 File fileName = new File("D:/Proyectos java/Lisanti version1.0/JFrameRutas.xlsx");
    /**
     * Creates new form JFramePrint1
     */
    public JFramePrint1() {
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

    public void obtener(List cellDataList){
       //CREO MI MATRIZ
        String [][] matriz = null;
           String[]casa= new String[4];
           String [] casa2= new String[3];
           String columna0,columna1,columna2,columna3;
           String meterDatos[]= new String[4];
          String datosTabla[][]={};
          String titulosTabla[]={"DESCRIPTION","QTY","LOCATION"};  //Determino el tamaño
          tablaProducto= new DefaultTableModel(datosTabla, titulosTabla);
          DefaultTableModel TamanoCol= (DefaultTableModel)jtableProductos.getModel();

//      table.getColumnModel().getColumn(0).setPreferredWidth(27);
         
          
          jtableProductos.setModel(tablaProducto);
          
          //-------- TAMAÑO DE LAS CELDAS --------------------
          ModificarTabla();
           
         //---------CENTRAR TEXTO ------------

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

                   System.err.println("TAMAÑO DE: "+filtro.length());
                             
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
           System.err.println("nuevo valor= "+nuevoValor);          
           
           return nuevoValor;       
       }
     
     public void ModificarTabla(){
     DefaultTableCellRenderer alinearCentro;    
      alinearCentro = new DefaultTableCellRenderer();
      alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
     //---------------- TAMAÑO CELDAS ------------------------------
     jtableProductos.getColumnModel().getColumn(0).setPreferredWidth(200); //FUNCIONA
     jtableProductos.getColumnModel().getColumn(1).setPreferredWidth(10);     
     jtableProductos.getColumnModel().getColumn(2).setPreferredWidth(20); 
     
     jtableProductos.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
     
     //NOTA: LOS VALORES DEL TAMAÑO DE MI JFRAMEN ES DE 584 PX DEFAULT
     
     //---------------- AJUSTAR EN VENTANA -------------------------
    
     
     }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        miniPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtableProductos = new javax.swing.JTable();
        BtPrint = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout miniPanelLayout = new javax.swing.GroupLayout(miniPanel);
        miniPanel.setLayout(miniPanelLayout);
        miniPanelLayout.setHorizontalGroup(
            miniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );
        miniPanelLayout.setVerticalGroup(
            miniPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Titulo.setText("PRUEBA IMPRESION 1");

        jtableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Description", "QTY", "SLOW", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtableProductos);
        if (jtableProductos.getColumnModel().getColumnCount() > 0) {
            jtableProductos.getColumnModel().getColumn(1).setMaxWidth(45);
        }

        BtPrint.setText("PRINTER");
        BtPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtPrint))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(Titulo))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtPrint)
                .addGap(428, 428, 428))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(272, 272, 272)
                    .addComponent(miniPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(273, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(163, 163, 163)
                    .addComponent(miniPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(163, 163, 163)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtPrintActionPerformed
      
        
    //-------PRINTER BASIC WORKING ---------------    
//        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPrintable(this);
//        boolean doPrint = job.printDialog ();
//        
//        if (doPrint) {
//    try {
//        job.print();
//    } catch (PrinterException e) {
//        // The job did not successfully
//        // complete
//    }
//}
        
        //------------- FUNTION PRINTER WORKING-----------------
       try {
             PrinterJob printer = PrinterJob.getPrinterJob(); 
            
               //Set 1/2 " margins and orientation 
               
              
               
           
              PageFormat pf = printer.defaultPage(); 
              pf.setOrientation(PageFormat.PORTRAIT); 
             Paper paper = new Paper(); 
             double margin = 36; // half inch 
              paper.setImageableArea(margin, margin, paper.getWidth() - margin * 2, paper.getHeight() - margin * 2); 
             pf.setPaper(paper); 
             
             Book printJob = new Book(); 
            
//             for (JTable t : getAllTables()) 
          
           printJob.append(jtableProductos.getPrintable(JTable.PrintMode.NORMAL, null, null), pf,6);           
           
             printer.setPageable(printJob); 
             System.out.println(printJob.getNumberOfPages());
             if (printer.printDialog()) 
             printer.print();
             
        } catch (Exception e) {
             e.printStackTrace(); 
        } 
      
    }//GEN-LAST:event_BtPrintActionPerformed

public void print() {
    final PrinterJob printJob = PrinterJob.getPrinterJob();
    final PageFormat pageFormat = printJob.defaultPage();
    final Book book = new Book();
    book.append(this, pageFormat);
    printJob.setPageable(book);

    if (printJob.printDialog()) {
        try {
            printJob.print();
        }
        catch (final PrinterException e) {
            throw new RuntimeException("Error Printing", e);
        }
    }
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
            java.util.logging.Logger.getLogger(JFramePrint1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFramePrint1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFramePrint1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFramePrint1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFramePrint1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtPrint;
    private javax.swing.JLabel Titulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtableProductos;
    private javax.swing.JPanel miniPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
        System.err.println("PAGINA ="+pageIndex);
      
        
        if(pageIndex>0){
        
            return NO_SUCH_PAGE;
        }
       
        Graphics2D hub = (Graphics2D) graphics; //ACEDEMOS A LAS PROPIEDADES DE NUESTRO OBJETO
        hub.translate(pageFormat.getImageableX()+0,pageFormat.getImageableY()+0); //CENTRAMOS
        hub.scale(1.0, 1.0); //ESCALA DE NUESTRO ARCHIVO
        
        String hola= "ALOHA";
  
        
//        jPanel1.printAll(graphics);
        
        Titulo.print(graphics);
       
//        jtableProductos.print(graphics);
//        miniPanel.print(graphics);
//       
//        Titulo.print(graphics);
     
//         jtableProductos.print(graphics);
        
        return PAGE_EXISTS;
    }
}




