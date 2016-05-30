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
public class WindowHandlerInterfata3 extends WindowAdapter{
    Interfata interfata;
    public WindowHandlerInterfata3(Interfata interfata){
        this.interfata=interfata;
    }
   public void windowClosing(WindowEvent e){
       interfata.coada.add("QUIT");
       interfata.amCitit=true;
   }
    
}