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
public class SendForBcastHandler implements ActionListener {
    Interfata i;
    SendForBcastHandler (Interfata i){
        this.i=i;
    }
     public void actionPerformed(ActionEvent e){
         //Afisez ce mesaj am trimis cu BCAST
          i.add("Ai trimis prin BCAST mesajul :"+i.text2.getText());
           i.button1.setVisible(false);
           
           i.coada.add(i.text2.getText());
           
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
