/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armadopalletv1;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author Fredy
 */
public class ArmadoPalletV1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //---- DESCOMENTARIAR EN CASO DE QUERER USAR EL JFRAME
//       File f = new File("D:/Proyectos java/Lisanti version1.0/DatosPrueba.xlsx");
//          JFramePallet jFramePallet= new JFramePallet(f);
//      jFramePallet.setVisible(true);
        
//----------------------DESCOMETAREAR ESTO---------------------
//  JFramePruebas1 jFramePruebas1 = new JFramePruebas1();
//  jFramePruebas1.setVisible(true);

//---------------------- COMENTAREO ANTES DE CERRAR -----------------
    TimerTask finTarea;
    Timer timer;
      int timeFinish=5000;
     Inicio inicio = new Inicio();
    inicio.setVisible(true); 
    JFrameDays jFrameDays = new JFrameDays();
    Calendar c = new GregorianCalendar();
  //  JDialogIncio jDialogIncio = new JDialogIncio(jFrameDays, true);
    
// ----- EN 5 SEGUNDOS CERRAMOS NUESTRA TABLA DE PRESENTACION ----------
    finTarea = new TimerTask() {
            @Override
            public void run() {
                
                System.out.println("FINALIZANDO TAREA");
               inicio.dispose(); 
               inicio.setVisible(false);
               // jDialogIncio.setVisible(true);
               String anio = Integer.toString(c.get(Calendar.YEAR));
                System.out.println(anio);
                int licencia = Integer.parseInt(anio);
                if(licencia >= 2019){
                 jFrameDays.setVisible(true);
                }
                else{
                JOptionPane.showMessageDialog(null,"ERROR");
                }
                
            }
        };
    
      timer = new Timer();  
      timer.schedule(finTarea, timeFinish);     
    }
}
