/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armadopalletv1;

/**
 *
 * @author Fredy
 */
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

   public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{
   
          @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
     
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if(row == 3){
        
            cell.setBackground(Color.red);
        }
        
        /*
 //establecemos el fondo blanco o vacío
        setBackground(null);
        
         //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
         //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddRow = (row % 2 == 0);
        
        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(245, 245, 240);
        
          //Si las filas son pares, se cambia el color a gris
        if (oddRow) {
            
            setBackground(c);
        }
        
        //------------- MODIFICAMOS LOS TAMAÑOS DE LAS COLUMNAS
//      DefaultTableCellRenderer alinearCentro;    
//      alinearCentro = new DefaultTableCellRenderer();
//      alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
     //---------------- TAMAÑO CELDAS ------------------------------
     table.getColumnModel().getColumn(0).setPreferredWidth(200); //FUNCIONA
     table.getColumnModel().getColumn(1).setPreferredWidth(10);     
     table.getColumnModel().getColumn(2).setPreferredWidth(20); 
     
//     table.getColumnModel().getColumn(1).setCellRenderer(alinearCentro);
     //--------------- MODIFICAMOS LA ALINEACION ----------------
     
       */ 
       
     return cell;
    }
   }