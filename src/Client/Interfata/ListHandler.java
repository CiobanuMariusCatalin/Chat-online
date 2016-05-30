/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.Interfata;

import java.awt.event.*;
public class ListHandler implements ActionListener{
Interfata i;
   ListHandler(Interfata i){
        this.i=i;
    }
    public void actionPerformed(ActionEvent e){
        i.coada.add("LIST");
        i.amCitit=true;
    }    
}
