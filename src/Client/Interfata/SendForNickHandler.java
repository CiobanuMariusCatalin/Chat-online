/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Interfata;

import java.awt.event.*;

/**
 *
 * @author PokeMaster
 */
public class SendForNickHandler implements ActionListener {
    Interfata i;
    SendForNickHandler(Interfata i){
        this.i=i;
    }
     public void actionPerformed(ActionEvent e){
        //Fac butonul send invizibil
         i.button1.setVisible(false);
         
         i.coada.add(i.text1.getText());
         
         i.amCitit=true;
         
         i.button1.removeActionListener(this);
         
         i.contentPane.remove(i.jpanel4);
         
         i.button2.setVisible(true);
         i.button3.setVisible(true);
         i.button4.setVisible(true);
         i.button5.setVisible(true);
         
         i.frame.pack();
    }    
}
