/*Clientul transmite threadului o comanda care poate fii pentru una dintre functiile acestea:LIST,MSG,
 BCAST,NICK,QUIT comanda in sine se numeste altfel,ea este unica si folosita ca sa stie servarul
 ce sa faca mai departe.Servarul transmite la randul sau clientului o comanda unica astfel incat 
 clientul sa stie ce functie a efectuat servarul si alte date folositoare clientului (cum ar fii un mesaj
 ce trebuie afisat, daca schimbare numelui a fost facuta cu succes, daca am iesit de pe servar etc) */
package Server;
import java.io.*;
import java.net.*;
import java.util.*;
//Fiecare thread lucreaza cu un singur client
public class Conexiune extends Thread {
private String nickName;//numele clientului
private Socket cs = null; 
private DataInputStream is = null;
private ArrayList<ClientData> sockete;//Array-ul de DataOutputStream-ul si nume al fiecarui client
//Array-ul contine numele fiecarui client,l-am creat doar pentru a fii mai clar cand vreau sa caut doar numele
public static ArrayList<String> nickNameList;
private int id;//id-ul clientului

public String getNickName(){
    return nickName;
}
public Socket getSocket(){
    return cs;
}
public DataInputStream getDataInputStream(){
    return is;
}
public ArrayList<ClientData> getSockete(){
    return sockete;
}
public static ArrayList<String> getNickNameList(){
    return nickNameList;
}
public int getIdul(){//exista deja o functie cu getId 
    return  id;
}
public void setNickName(String nick){
    nickName=new String(nick);
}
//constructor
public Conexiune(Socket client,ArrayList<ClientData> sockete,int id)

throws Exception {
this.id=id;
cs = client; 

is =new DataInputStream(cs.getInputStream());


this.sockete=sockete;
new SetNickName(this);//pun pe client sa isi aleaga un nickname , el alege un nickname pana servarul este deacord cu el

//threadul conexiunii il pornesc din interiorul clasei SetNickName dupa ce userul si-a ales un nume valid
}
public void run() {

try {
    String comanda="";
    boolean t=true;
    while(t==true){
        //Clientul trimite o comanda ce spune servarului ce sa faca:sa trimita un mesaj, sa ii schimba numele, sa iasa etc.
        comanda=is.readUTF();
        //intram pe ramura aceasta daca primim comanda LIST
        if(comanda.equals("trimiteListaDeUseri")){
            for(int i=0;i<sockete.size();i++)
                //Cautam clientul asociat threadului curent
                if(sockete.get(i).getUserName().equals(nickName)){
                    //ii trimitem o comanda clientului ca sa stie ce date urmeaza sa primeasca si ce inseamna ele
                    sockete.get(i).getDataOutputStream().writeUTF("amPrimitListaDeUseri");
                    //Trimitem dimensiunea vectorlui de useri logati
                    sockete.get(i).getDataOutputStream().writeInt(nickNameList.size());
                    for(int j=0;j<nickNameList.size();j++)
                        //Trimitem userii 1 cate 1
                        sockete.get(i).getDataOutputStream().writeUTF(nickNameList.get(j)); 
                }
        }   
        //intram pe ramura asta daca primim comanda MSG
        if(comanda.equals("TrimiteMesaj")){
            //Citim numele utilizatorului cui vrem sa ii trimitem mesajul
            String nume=is.readUTF();
            //Citim mesajul
            String mesaj=is.readUTF();
            //verificam daca exista userul caruia vrem sa ii trimitem mesaj
            boolean userulExista=false;
            for(int i=0;i<sockete.size();i++)
                //Cautam userul cui vrem sa ii trimitem mesajul
                if(sockete.get(i).getUserName().equals(nume)){
                    //Daca am gasit userul vrem sa luam la cunostinta asta prin schimbarea valorii booleanului
                    userulExista=true;
                    //Ii spunem acelui user ca a primit un mesaj
                    sockete.get(i).getDataOutputStream().writeUTF("amPrimitMesaj");
                    //Ii spunem de la cine a venit mesajul
                    sockete.get(i).getDataOutputStream().writeUTF(nickName);
                    //Spunem si ce mesaj ia transmis
                    sockete.get(i).getDataOutputStream().writeUTF(mesaj);
                }
            /*Daca nu am gasit userul ce vroiam sa ii transmitem un mesaj trebuie sa ii spunem
             trebuie sa anuntam clientul asociat threadului curent ca transmiterea mesajului a
              rezultat in eroare
             */
            if(userulExista==false){
                for(int i=0;i<sockete.size();i++)
                    //Cautam userul asociat threadului curent
                if(sockete.get(i).getUserName().equals(nickName)){
                    //ii transmitem un cod ce el va sti sa il interpreteze ca o eroare de transmitere de mesaj
                    sockete.get(i).getDataOutputStream().writeUTF("errorMSG");  
                    //ii spunem si numele userului ce nu la putut gasi
                    sockete.get(i).getDataOutputStream().writeUTF(nume);
                }
             }
        }
        //intram pe ramura aceasta daca primim comanda BCAST
         if(comanda.equals("broadCast")){
             /*Daca exista doar un user conectat pe servar inseamna ca Clientul ce vrea sa transmita BCAST
              este singurul conectat si nu are rost sa folosim functia asa ca ii transmitem un mesaj de eroare
              */
            if(nickNameList.size()==1){
                //trebuie sa scot mesajul din inputstream sa nu ma incurce desi nu o vom folosi la nimic
               String mesaj=is.readUTF();
               for(int i=0;i<sockete.size();i++){
                     if(sockete.get(i).getUserName().equals(nickName))
                         //Transmitem eroarea gasita clientului asociat threadului curent
                        sockete.get(i).getDataOutputStream().writeUTF("errorBroadCastNumaiSuntUseriConectati");
                         }
            }
            //Daca 2 sau mai multi useri atunci transmitem fiecaruia mesajul
            else{    
             //Preluam mesajul ce trebuie transmis tuturor userilor   
             String mesaj=is.readUTF();
                for(int i=0;i<sockete.size();i++){
                       /*Threadul transmite mesajul tuturor userilor conectatii inafara de cel ce a transmis mesajul
                        si din perspectiva clientilor ei au primit un MSG dupa cum se vede e acelasi cod transmis
                        clientilor ca si cel de la MSG*/
                     if(!sockete.get(i).getUserName().equals(nickName)){
                         sockete.get(i).getDataOutputStream().writeUTF("amPrimitMesaj");
                         sockete.get(i).getDataOutputStream().writeUTF(nickName);
                         sockete.get(i).getDataOutputStream().writeUTF(mesaj);  
                }
                }
         }
         }
         //intram pe ramura aceasta daca primim comanda NICK
         if(comanda.equals("alegeNickNou")){
             String newNick=is.readUTF();
             //schimbam nickname-ul daca se poate in aceasi clasa in care alegem pentru inceput nickname-ul ca sa o putem face sincronizat
             new SetNickName(this,true,newNick);
         }
         //intram pe ramura aceasta daca primim comanda QUIT
         if(comanda.equals("QUIT")){
             //inchidem threadul curent,adica bucla while
             t=false;
             //Scriem pe interfata servarului ca clientul a iesit
            AfisPeEcran.Mesajul("Userul "+nickName+" a iesit ");
             for(int i=0;i<sockete.size();i++){
                 if(sockete.get(i).getUserName().equals(nickName)){
                     //Ii spunem clientului sa inchida si el
                     sockete.get(i).getDataOutputStream().writeUTF("Deconecteaza-te");
                     //Inchidem DataOutPutStreamul
                    sockete.get(i).getDataOutputStream().close();
                    //Scoatem clientul din array-ul sockete
                    sockete.remove(i);
                 }
                 for(int j=0;j<nickNameList.size();j++)
                     //Scoatem numele clientului din array-ul nickNameList ce retine numele tuturor clientilor
                     if(nickNameList.get(j).equals(nickName))
                         nickNameList.remove(j);
                  }
             //inchidem inputStreamul si socketul
                 is.close();
                cs.close();
         }
    }
}
catch(Exception e){
System.out.println(e+" 6");
}

}

}