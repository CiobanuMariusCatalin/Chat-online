/*Clasa aceasta nu stie nimic despre client sau server pentru ea exista doar un inputStream si o interfata(outputStreamul
  si socketul le-am introdus doar sa le pot inchide mai tz)
 * Pentru fiecare comanda data de client servarului , servarul transmite la randul lui un mesaj inapoi
clientului, acest mesaj este preluat de acest thread ce citeste mesajele primite in paralel
* cu clientul ce transmite mesaje.De fiecare cand threadul citeste ceva avem in capatul inputStreamului
* o comanda unica transmisa de servar ca noi sa stim ce operatii trebuie sa facem(de exemplu daca am primit
* un mesaj,daca schimbarea numelui a fost cu succes ,sau daca trebuie sa ne deconectam de la servar)
 */

package Client;
import java.io.*;
import java.net.*;
import Client.Interfata.*;
public class CititorDeMesaje extends Thread {
    DataInputStream dis;
    /*DataOutputStream si Socket le iau din clasa client doar ca sa la inchid */
    DataOutputStream dos;
    Socket cs;
    Interfata interfata;
    CititorDeMesaje(DataInputStream dis,DataOutputStream dos,Socket cs,Interfata interfata){
        this.dis=dis;
        this.dos=dos;
        this.cs=cs;
        this.interfata=interfata;
    }
    public void run(){
     try{//mesajele primite de la server sunt prelualte de threadul asta
         boolean socketOpen=true;//daca nu am dat comanda QUIT continuam clientul
         while(socketOpen==true){
                 String aTrimisMesajul=dis.readUTF();
                 //Daca am dat comanda LIST rezultatul primit de la servar este preluat aici
                 if(aTrimisMesajul.equals("amPrimitListaDeUseri")){
                     interfata.add("Useri conectati momentan sunt: ");
                     int dim=dis.readInt();
                     for(int i=0;i<dim;i++){
                          interfata.add(dis.readUTF());
                     }
                 }
                 //Mesajele primite de la alti useri prin MSG sau BCAST sunt preluate aici
                 if(aTrimisMesajul.equals("amPrimitMesaj"))
                     interfata.add(dis.readUTF()+": "+dis.readUTF());
                 //Mesaj de eroare pentru functia MSG
                 if(aTrimisMesajul.equals("errorMSG")){
                  interfata.add("Userul "+"\""+dis.readUTF()+"\""+" nu exista ca sa ii putem trimite un mesaj");
                 }
                 //Mesaj de eroare pentru functia BCAST
                 if(aTrimisMesajul.equals("errorBroadCastNumaiSuntUseriConectati")){
                     interfata.add("Esti singurul user online nu ai cui sa ii trimiti mesajul");
                 }                 
                 //daca am dat comanda NICK(rename) servarului raspunsul lui este preluat aici
                 if(aTrimisMesajul.equals("amIncercatSaSchimbamNickName-ul")) {
                     boolean renameSucceed=dis.readBoolean();
                     String newNick=new String(dis.readUTF());
                     if(renameSucceed==true){
                         interfata.add("Schimbarea nickului in "+newNick+ " a fost cu succes");
                         interfata.label3.setText("Esti logat ca si:  "+newNick);
                     }  
                     else    interfata.add("Schimbarea nickului "+newNick+" a rezultat in esec");    
                 }
                 //daca am dat comanda QUIT vrem sa numai preluam mesaje
                 if(aTrimisMesajul.equals("Deconecteaza-te")){
                     socketOpen=false;
                     dis.close();
                     dos.close();
                     cs.close();
                     System.exit(0);
                 }
         }
         
         }
     catch(Exception e){
         System.out.println(e+"3");
     }
    }
}

