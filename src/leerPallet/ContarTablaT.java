/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerPallet;

import javax.swing.JLabel;
import javax.swing.JTable;


/**
 *
 * @author Fredy
 */
public class ContarTablaT {
    
     String cajasString;
     int cajas;
    
  public void contador (JTable miTabla, JLabel labelTotal){
      
      int productos = miTabla.getRowCount();
      
      int cajasT=0;
      for (int i = 0; i < productos; i++) {
          
          cajasString = (String)miTabla.getValueAt(i, 1);
          cajas = Integer.parseInt(cajasString);
          cajasT= cajas + cajasT;         
          
      }
       System.err.println("TOTAL CAJAS = "+ cajasT);
              
       labelTotal.setText(String.valueOf(cajasT));
  }
  
  public void contarSeleccion(JTable miTabla, JLabel labelTotal){
  
      int i = miTabla.getSelectedRow();
      System.err.println("filaNo "+i);
  }
        
}
