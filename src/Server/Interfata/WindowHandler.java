
package Server.Interfata;


import java.awt.event.*;
public class WindowHandler extends WindowAdapter{
    Interfata interfata;
    public WindowHandler(Interfata interfata){
        this.interfata=interfata;
    }
   public void windowClosing(WindowEvent e){
   System.exit(0);
   }
    
}