//Interfata Clientului este o implementare a acestei clase 
package Client.Interfata;

import javax.swing.*;
import java.awt.*;
import java.util.*;
//Toate componentele sunt de tipul Swing
public class Interfata {
    String nickInterfata;//ce nume are Clientul cu interfata aceasta
    JFrame frame;
    Container contentPane;
    BorderLayout layout;
    JPanel jpanel1;
    JPanel jpanel2;
    JPanel jpanel3;
    JPanel jpanel4;
    JLabel label1;
    public JLabel label2 ;
    public JLabel label3;//o folosesc pentru a afisa in interfata #3 cu ce nume sunt logat
    JTextField text1;
    JTextField text2;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JTextArea textArea;
    GridBagConstraints c;
    /*Textul luat din JTextFields sunt bagati in coada si trimisi catre coada si Clientul citeste
     datele primite din interfata prin intermediul unor metode ce intorc varful cozii*/
    ArrayList<String> coada;
    /*amCitit devine true cand pot sa ii transmit clientului toate datele de ce are nevoie pentru operatia curenta
    el este modificat si din interiorul Clientului*/
    public  boolean amCitit;
    /*declar Listenerii aici sa ii pot scoate ulterior pentru ca interfetele sunt in metode diferite
     si am nevoie de acces la toti listenerii de oriunde din clasa
     */
    AdresaSiPortHandler adresaSiPortActionListener;
    UserNameHandler userNameActionListener;
    SendForMsgHandler sendForMsgHandler;
    WindowHandlerInterfata1 windowListener1;
    WindowHandlerInterfata2 windowListener2;
    WindowHandlerInterfata3 windowListener3;
       //INTERFATA #1
      public Interfata(){
          coada=new ArrayList<>();
          amCitit=false;
          frame=new JFrame("Client");
          contentPane = frame.getContentPane();
          layout = new BorderLayout();
          frame.setLocation(483,184);
          frame.setPreferredSize(new Dimension(300,300));
          frame.setResizable(false);
          frame.setLayout(layout);
          jpanel1=new JPanel(new GridBagLayout());
          jpanel3=new JPanel(new FlowLayout());
          
          
          
          c = new GridBagConstraints();
          c.gridwidth = GridBagConstraints.RELATIVE;
          label1=new JLabel("Adresa: ");
          jpanel1.add(label1,c);
          
          
          
          c.gridwidth = GridBagConstraints.REMAINDER;
          text1=new JTextField(10);
          jpanel1.add(text1,c);
          
          
          
          c.gridwidth = GridBagConstraints.RELATIVE;
          label2=new JLabel("Port");
          jpanel1.add(label2,c);
          
          
          
          c.gridwidth = GridBagConstraints.REMAINDER;
          text2=new JTextField(10);
          jpanel1.add(text2,c);
          
          
          
          button1=new JButton("Log In");
          jpanel3.add(button1);
          
          
          
          
          
          //ActionListener pentru butonul Connect
          adresaSiPortActionListener=  new AdresaSiPortHandler(this);
          button1.addActionListener(adresaSiPortActionListener);
          
          
          
          contentPane.add(jpanel1,BorderLayout.CENTER);
          contentPane.add(jpanel3,BorderLayout.PAGE_END);
          
       
       
       //WINDOWLISTENER(AdresaSiPortHandler)
       windowListener1 = new WindowHandlerInterfata1(this);
       frame.addWindowListener(windowListener1);
       
    
       frame.pack();//imi pune frameu la preferd size
       frame.setVisible(true);
        
    }
   //INTERFATA #2
   public void schimbaInterfata(){

         
       //Scot WindowListener-ul pentru interfata #1 si il pun pentru interfata#2
       frame.removeWindowListener(windowListener1);
       windowListener2 = new WindowHandlerInterfata2(this);
       frame.addWindowListener(windowListener2);
       
      //daca nu stergeam tot panel-ul imi ramaneau vechile componente chiar daca le-am sters 
      contentPane.remove(jpanel1);
      
      jpanel1=new JPanel(new GridBagLayout());
      contentPane.add(jpanel1);
      
      c = new GridBagConstraints();
      
      
      c.gridwidth = GridBagConstraints.REMAINDER;
      label2=new JLabel("");
      jpanel1.add(label2,c);
       
       
       
      c.gridwidth = GridBagConstraints.RELATIVE;
      label1=new JLabel("UserName: ");
      jpanel1.add(label1,c);
      
      c.gridwidth = GridBagConstraints.REMAINDER; 
      text1=new JTextField(10);
      jpanel1.add(text1,c);
      
     //Scot ActionListener-ul pentru buttonul connect 
     button1.removeActionListener(adresaSiPortActionListener); 
     
     //Adaug ActionListener-ul pentru butonul Log In(UserNameHandler)
     userNameActionListener=new UserNameHandler(this);
     button1.addActionListener(userNameActionListener);

      frame.pack();
   }
   //INTERFATA #3
   public void schimbaInterfata2(){
     //renuntam la preferdsized din interfata #1 si #2
       frame.setPreferredSize(null);
       
       //Sa nu putem vedea butonul send doar dupa ce am dat o comanda
        button1.setVisible(false);
       
       //WINDOWLISTENER
       frame.removeWindowListener(windowListener2);
       windowListener3 = new WindowHandlerInterfata3(this);
       frame.addWindowListener(windowListener3);
       
       
       
       
       button1.removeActionListener(userNameActionListener);
       
       contentPane.remove(jpanel1);
       
       
       
       JPanel jpanel5=new JPanel(new FlowLayout());
       jpanel1=new JPanel(new GridBagLayout());
       jpanel2=new JPanel(new FlowLayout());
       
       
       
       contentPane.add(jpanel5,BorderLayout.NORTH);//Imi spune cu nickName sunt logat
       contentPane.add(jpanel1,BorderLayout.WEST);//Imi afisa butoanele ce le pot apasa
       contentPane.add(jpanel2,BorderLayout.CENTER);//Imi arata mesajele primite pana acum
       /*Mai avem- jpanel3(SOUTH) care in interfata #3 este un singur buton numit Send care apare
        * doar dupa ce am apasat o comanda din partea stanga a interfetei
                 - jpanel4(EAST) ce apare doar cand apasam un buton din stanga interfetei dupa care
                 dispare iar*/
       
       
       label3=new JLabel("Esti logat ca si:  "+nickInterfata);
       jpanel5.add(label3);
       
       
       
       
       c = new GridBagConstraints();
       c.gridwidth = GridBagConstraints.REMAINDER;
       
       
       
       
       button1.setText("Send ");
       button2=new JButton("LIST ");
       button3=new JButton("BCAST");
       button4=new JButton("MSG  ");
       button5=new JButton("NICK ");
       
       
       
       
       button2.setPreferredSize(new Dimension(100,50));
       button3.setPreferredSize(new Dimension(100,50));
       button4.setPreferredSize(new Dimension(100,50));
       button5.setPreferredSize(new Dimension(100,50));
       
       
       
       
       jpanel1.add(button2,c);
       jpanel1.add(button3,c);
       jpanel1.add(button4,c);
       jpanel1.add(button5,c);
       
       
       /*Butoanele de mai jos imi modifica interfata la apasarea lor a.i.
        apare un nou panel in care sa pot scrie mesajul ce vreau transmis 
        catre servar ,si panelul cu butoane este scos temporar ca sa nu pot apasa un buton dupa
        ce altul a fost deja apasat*/
       button2.addActionListener(new ListHandler(this));
       button3.addActionListener(new BcastHandler(this));
       button4.addActionListener(new MsgHandler(this));
       button5.addActionListener(new NickHandler(this));
       
       
       
       
       textArea=new JTextArea(25,25);
       textArea.setLineWrap(true);//cand se loveste de capatul stang sa treaca pe randul urmator
       textArea.setEditable(false);
       jpanel2.add(textArea);
       
       JScrollPane  jScrollPane1 = new JScrollPane(textArea);
       jpanel2.add(jScrollPane1);
       
      
       
       frame.pack();
   }
  
public int nextInt(){
    while(amCitit==false){
        System.out.print("");
}
    return Integer.parseInt(coada.remove(0));
}
public String nextString(){
        while(amCitit==false){
        System.out.print("");
}
    return coada.remove(0);
}
public void setAmCitit(boolean bool){
    amCitit=bool;
}
public void add(String textDeAdaugat){
    //sa nu adaug 2 spatii goale la inceput
   if(!textArea.getText().equals("")) textArea.setText(textArea.getText()+"\n\n"+textDeAdaugat);
   else  textArea.setText(textDeAdaugat);
}
public void getNick(String nickClient){
    nickInterfata=new String(nickClient);
}
 }



