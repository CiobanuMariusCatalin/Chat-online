
package Client.Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BcastHandler implements ActionListener{
Interfata i;
    BcastHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        i.button1.setVisible(true);
        
        i.button2.setVisible(false);
        i.button3.setVisible(false);
        i.button4.setVisible(false);
        i.button5.setVisible(false);
        
        
        i.jpanel4=new JPanel(new GridBagLayout());
        
        
        GridBagConstraints c=new GridBagConstraints();
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.RELATIVE;
        
        
        i.label2=new JLabel("Mesajul: ");
        i.jpanel4.add(i.label2,c);
        
        i.button2.setVisible(false);
        c.gridwidth = GridBagConstraints.REMAINDER;
        
        
        i.text2=new JTextField(10);
        i.jpanel4.add(i.text2,c);
        
        i.button1.addActionListener(new SendForBcastHandler(i));
        
        
        
        
        i. contentPane.add(i.jpanel4,BorderLayout.EAST);
        
        
        i.coada.add("BCAST");
        i.amCitit=true;
        i.frame.pack();
    }   
}
