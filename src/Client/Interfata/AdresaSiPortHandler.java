
package Client.Interfata;

import java.awt.event.*;

public class AdresaSiPortHandler implements ActionListener{
Interfata i;
    AdresaSiPortHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        //Adresa
        i.coada.add(i.text1.getText());
        //Port
        i.coada.add(i.text2.getText());
        i.text1.setText("Conectare Esuata");
        i.text2.setText("Restartati clientul");
        i.amCitit=true;
    }
}