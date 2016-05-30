
package Server.Interfata;

import java.awt.event.*;
public class TextHandler implements ActionListener{
Interfata i;
  TextHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        //punem in variabila interfetei "port" valoarea din JTextField
        i.port=Integer.parseInt(i.text1.getText());
        //faceam ca JTextField-ul sa numai poata fii modificat
        i.text1.setEditable(false);
        i.serverStart=true;
    }    
}