/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumaProductos;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fredy
 */
public class SumarPallets {
    
    int totalCajas;
    String sTotalCajas;
    
    public SumarPallets(DefaultTableModel tabla){
           
        int productos = tabla.getRowCount();
            
        int qtyProductos=0;
        
        for (int i = 0; i < productos; i++) {
            
            int contadorAux=Integer.parseInt((String) tabla.getValueAt(i, 1));
            
           
            qtyProductos= contadorAux + qtyProductos;
                        
        }
        
       //  System.err.println("totalProductos= "+ qtyProductos);
         totalCajas= qtyProductos;
    }
    
    public int obtenerCajas(){
    
    return totalCajas;
    }
    
    public String SObtenerCajas(){
        
        return String.valueOf(totalCajas);
    
    }
    
}
