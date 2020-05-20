/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerPallet;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fredy
 */

//Esta clase importamos nuestra tabla desde un excel tenemos dos metodos el
//metodo Creando el cual le da forma a mi tabla con sus respectivos titulos
// el metodo llenar con el cual llenamos nuestra Tabla.
public class CrearTabal {
    
    String datosTabla[][]={};
    String titulosTabla[]={"DESCRIPTION","QTY","SLOT"};
    DefaultTableModel miTabla;
    ImportarExcel importarExcel = new ImportarExcel();
       
    public void Creando(JTable tabla){
    miTabla =  new DefaultTableModel(datosTabla, titulosTabla);
    tabla.setModel(miTabla);
    }
    
    public void Llenar(){
    // DEBO OBTENER EL MODELO DE MI TABLA ESTA EN miTabla de la clase crearTabla
    importarExcel.modelo= miTabla;  //cambiamos el valor DefaultTableModelo de la claseImportarExcel
    importarExcel.metodoUno();      // llamamos al metodo encargado de rellenar nuestra Tabla 
    }
    
    
}
