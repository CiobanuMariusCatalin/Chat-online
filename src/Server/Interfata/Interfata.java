/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server.Interfata;

import javax.swing.*;
import java.awt.*;

public class Interfata {
        int port;//salveza valoarea portului
        JFrame frame;//pe frame punem toate componentele interfetei grafice
        JTextField text1;//introducem portul aici
        JTextArea textArea1;//Ne arata cand un user se conecteaza,isi schimba numele si iese de pe servar
        JLabel label1;//Este asesat langa TextField si ne indica date am dori sa intram in TextField
        JPanel panel1;//Pe acest panel avem JLabel-ul si JTextField-ul
        JPanel panel2;//Pe acest panel avem JTextArea
        GridBagConstraints c;//Conditii de asesare in pagina pentru panelele noastre
        TextHandler textHandler;//ActionListener pentru TextField-ul nostru
        boolean serverStart;//ia valoarea true cand vrem sa dam start servarului 
  public Interfata(){
        serverStart=false;
        frame=new JFrame("Server");
        Container ContentPane=frame.getContentPane();
        frame.setLayout(new BorderLayout());
        frame.setLocation(483,184);
        frame.setResizable(false);
        
        panel1=new JPanel(new GridBagLayout());
        panel2=new JPanel(new GridBagLayout());
        
        
        c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.RELATIVE;
        
        label1=new JLabel("Port");
        panel1.add(label1,c);
        
        c.gridwidth = GridBagConstraints.REMAINDER;
        text1=new JTextField(10);
        panel1.add(text1);
        
        //TEXT HANDLER(ACTION LISTENER)
        textHandler=new TextHandler(this);
        text1.addActionListener(textHandler);
          
          
        textArea1=new JTextArea(25,25);
        textArea1.setLineWrap(true);//cand se loveste de capatul stang sa treaca pe randul urmator
        textArea1.setEditable(false);
        panel2.add(textArea1);
        JScrollPane  jScrollPane1 = new JScrollPane(textArea1);//ne adauga un Scroll pentru JTextArea
        panel2.add(jScrollPane1);
        
        
        

       ContentPane.add(panel1,BorderLayout.NORTH);//pozitionam primul panel in partea nordica a frameului
       ContentPane.add(panel2,BorderLayout.CENTER);//pozitionam panel-ul 2 in centrul frameului
       
            //WINDOWHANDLER(WINDOW ADAPTER)
       frame.addWindowListener(new WindowHandler(this));
       
        frame.pack();
        frame.setVisible(true);
    }
  //functia o folosim o singura data sa oferim servarului portul luat din interfata
    public int nextInt(){
        return port;
    }
    //adaugam text in TextArea 
    public void add(String textDeAdaugat){
    textArea1.setText(textArea1.getText()+"\n"+textDeAdaugat);
}
    //Dam drumul la servar cand apelam actionlistener-ul JTextFieldului
    public void ServerStart(){
        while(serverStart==false){
            System.out.print("");
        }
    }
}
