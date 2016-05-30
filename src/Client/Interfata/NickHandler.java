
package Client.Interfata;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class NickHandler implements ActionListener{
Interfata i;
   NickHandler(Interfata i){
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
        
        
        i.label1=new JLabel("Noul NickName: ");
        i.jpanel4.add(i.label1,c);
        
        
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        i.text1=new JTextField(10);
        i.jpanel4.add(i.text1,c);
      
      
      
    
        
       i.button1.addActionListener(new SendForNickHandler(i));
       
       
       
       
       i. contentPane.add(i.jpanel4,BorderLayout.EAST);
       
       
       
       
       i.coada.add("NICK");
       i.amCitit=true;
       i.frame.pack();
    }    
}
