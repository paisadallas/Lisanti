/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerPallet;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Fredy
 */
public class ImportarExcel {
    File f = new File("C:/Users/Fredy/Documents/Lisanti/rutasMacro.xlsm");
    public DefaultTableModel modelo;  //Debo acceder a  esta variable para poder modificar
    
    public void metodoUno(){
    List cellData = new ArrayList();
        try {
            FileInputStream fileInputStream = new FileInputStream(f);
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
        } 
     
         catch (Exception e) {     //FIN TRY
            e.printStackTrace();
        }
        metodoDos(cellData);
    }
    
    public void metodoDos(List cellDataList){
    
         String [][] matriz = null;
        int Nofilas= 15;
     
           String[]casa= new String[4];
           String [] casa2= new String[3];
           String columna0,columna1,columna2,columna3;
           String meterDatos[]= new String[4];
          String datosTabla[][]={};
          String titulosTabla[]={"DESCRIPTION","QUALITY","LOCATION"};  //Determino el tamaño
//          tablaProducto4= new DefaultTableModel(datosTabla, titulosTabla);
//          jtableProductos4.setModel(tablaProducto4);
         
     
         
                Object [][]miobjeto = null;
//        for(int i=0; i<cellDataList.size();i++){  //DATOS METIDOS POR CONSOLA USUARIO!!!
          for(int i=0; i<Nofilas;i++){
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
            
             String filtro=columna1;
            filtro = FiltroNo(filtro);
            
              //meterDatos[1]=stringCellValue;
              meterDatos[1]= filtro;//METODO PARA FILTRAR DATOS
                 //  System.err.println(columna1.replaceAll(punto,espacio));            
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
          modelo.addRow(meterDatos);   //INGRESO LOS DATOS WINNER WINNER CHICKEN DINNER

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
}
