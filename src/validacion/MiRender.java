/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacion;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.Component;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Fredy
 */
public class MiRender extends JTable{
    
    public Component prepareRender(TableCellRenderer renderer, int row, int column){
        
        Component component = super.prepareRenderer(renderer, row, column);
        component.setBackground(Color.RED);
        component.setForeground(Color.RED);
           
                                            
        return component;
    
    }
    
}
  
    

