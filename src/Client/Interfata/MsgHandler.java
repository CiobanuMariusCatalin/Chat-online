/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Interfata;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MsgHandler implements ActionListener{
Interfata i;
  MsgHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        i.button1.setVisible(true);
        //Sa nu putem apasa pe ele
        i.button2.setVisible(false);
        i.button3.setVisible(false);
        i.button4.setVisible(false);
        i.button5.setVisible(false);
        i.jpanel4=new JPanel(new GridBagLayout());
        
        
        
        GridBagConstraints c=new GridBagConstraints();
        c = new GridBagConstraints();
        
        c.gridwidth = GridBagConstraints.RELATIVE;
        i.label1=new JLabel("Mesaj catre: ");
        i.jpanel4.add(i.label1,c);
        
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        i.text1=new JTextField(10);
        i.jpanel4.add(i.text1,c);
        
        
        
        
        c.gridwidth = GridBagConstraints.RELATIVE;
        i.label2=new JLabel("Mesajul: ");
        i.jpanel4.add(i.label2,c);
        
        
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        i.text2=new JTextField(10);
        i.jpanel4.add(i.text2,c);
        
        
        i.button1.addActionListener(new SendForMsgHandler(i));
        
        
        
        i. contentPane.add(i.jpanel4,BorderLayout.EAST);
        
        
        
        i.coada.add("MSG");
        i.amCitit=true;
        i.frame.pack();
    }    
}
