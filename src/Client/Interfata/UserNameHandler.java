
package Client.Interfata;

import java.awt.event.*;


class UserNameHandler implements ActionListener{
Interfata i;
    UserNameHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        //UserName
        i.coada.add(i.text1.getText());
        i.text1.setText("");
        i.amCitit=true;
    }
}