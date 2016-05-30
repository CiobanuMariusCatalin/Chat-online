/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Interfata;

/**
 *
 * @author PokeMaster
 */
import java.awt.event.*;
public class WindowHandlerInterfata1 extends WindowAdapter{
    Interfata interfata;
    public WindowHandlerInterfata1(Interfata interfata){
        this.interfata=interfata;
    }
   public void windowClosing(WindowEvent e){
   System.exit(0);
   }
    
}