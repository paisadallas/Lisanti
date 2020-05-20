/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacion;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Fredy
 */
public class Organizar {
    
    public Organizar(DefaultTableModel tabla){ // CONSTRUCTOR
               
        int productos = tabla.getRowCount();
        
        String location="";
        String description="";
        for (int i = 0; i < productos; i++) {

          location= tabla.getValueAt(i, 2).toString();
          description= tabla.getValueAt(i, 0).toString();          
          description= description.toUpperCase();
          
          //Version Lisanti FoodService
          
          location= location.replace("S","5");
          location= location.replace("Z","2");
          location= location.replace("O","0");
          location= location.replace("G","6");
          location= location.replace("I","1");
            
       
          tabla.setValueAt(location, i, 2);
          tabla.setValueAt(description, i, 0);
          char pos3 = location.charAt(3);
          char pos2 = location.charAt(2);
          
          if(pos2 == 'B' || pos2 == 'Q'|| pos2 == 'E' || pos2 == 'V'||
             pos3 == 'B' || pos3 == 'Q'|| pos3 == 'E' || pos3 == 'V'){
              
              tabla.setValueAt(location+"*", i, 2);
          
          }

            }
                             
        }
    
    }
    
    

