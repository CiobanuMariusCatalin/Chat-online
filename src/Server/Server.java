
package Server;
import java.io.*;

import java.net.*;

import java.util.*;

import Server.Interfata.*;

class Server {



public static void main(String[] arg) throws Exception {

 //Creez interfata grafica a servarului   
 Interfata interfata=new Interfata();
 //Astept sa introduca portul in interfata grafica
 interfata.ServerStart();
 
 
 /*pun interfata servarului ca un camp static pentru clasa de mai jos a.i. orice obiect ar apela functia
  de afisat va afisa pe acelasi ecran si sincronizat sa nu se suprapuna scrisul*/
 //Singuru motiv pentru care creez un obiect de tipul AfisPeEcran este sa ii apelez constructorul
 new AfisPeEcran(interfata);
 
 ServerSocket ss = null; 
 Socket cs = null;
 //In sockete vom stoca DataOutPutStream-ul fiecarui client , numele lui si un id unic dat la creare. 
 ArrayList<ClientData> sockete;

sockete=new ArrayList<>();

//pornesc servarul cu portul luat din interfata
ss = new ServerSocket( interfata.nextInt() );

//Lista de userii conectati pe moment
Conexiune.nickNameList=new ArrayList<String>();

//id este un id unic pentru fiecare client
int id=0;
while (true) {
//servarul accepta toti clientii
cs = ss.accept();

//adaug in ArrayList un nou client doar cu DataOutputStream-ul si id-ul ,username-ul va fii ales mai in fata
sockete.add(new ClientData(new DataOutputStream(cs.getOutputStream()),id));
//pornesc un nou thread pentru clientul curent, acest thread este doar pentru client-ul respectiv preluand comenzi doar de la el
new Conexiune(cs,sockete,id);
id++;
}

}

}
