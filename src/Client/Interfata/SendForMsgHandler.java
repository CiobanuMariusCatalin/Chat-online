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
public class SendForMsgHandler implements ActionListener {
    Interfata i;
    SendForMsgHandler(Interfata i){
        this.i=i;
    }
     public void actionPerformed(ActionEvent e){
         //Fac butonul invizibil
         i.button1.setVisible(false);
         
         //Afisez si Clientului curent ce mesaj a transmis el
         i.add("Mesaj trimis catre  "+i.text1.getText()+" :"+i.text2.getText());
         
         
         i.coada.add(i.text1.getText());
         i.coada.add(i.text2.getText());
         
         i.amCitit=true;
         
         //Scot actionListenrul de pe butonul Send
         i.button1.removeActionListener(this);
         i.contentPane.remove(i.jpanel4);
         
         
         
         i.button2.setVisible(true);
         i.button3.setVisible(true);
         i.button4.setVisible(true);
         i.button5.setVisible(true);
         
         
         i.frame.pack();
    }    
}
