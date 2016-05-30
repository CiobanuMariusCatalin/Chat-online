//intre 2-6 marti sala 204,miercuri 2-4 sala 204 
//trimite mail clglavan44@yahoo.com
package Client;

import java.net.*; 
import java.io.*;
import Client.Interfata.*;
class Client{

public static void main(String[] args) throws Exception {


Interfata interfata=new Interfata();
//INTERFATA #1
/*metodele nextString() si nextInt() asteapta ca variabila booleana amCitit sa fie true dupa care intorc
String respectiv intreg , variabila amCitit devine true dupa ce am am apasat un anumit buton din
interfata*/
Socket cs = new Socket(interfata.nextString(), interfata.nextInt() );
interfata.amCitit=false;//variabila aceasta o folosesc sa stiu cand am citit ceva din interfata si trebuie resetata dupa fiecare citire

//schimb interfata
interfata.schimbaInterfata();
//INTERFATA #2

DataOutputStream dos =new DataOutputStream( cs.getOutputStream());
DataInputStream dis=new DataInputStream(cs.getInputStream());


String mesaj="";


boolean nickCorect=false;


while(nickCorect==false){
    mesaj=interfata.nextString();
    interfata.amCitit=false;
    dos.writeUTF(mesaj);//daca ii trimitem QUIT ca si nume atunci servarul se va opri din a comunica cu acest client
    nickCorect=dis.readBoolean();
   if(mesaj.equals("QUIT")){
       dis.close();
       dos.close();
       cs.close();
       System.exit(0);//dupa ce servarul a primit comanda sa inchida threadul terminam si clientul
   }
    if(nickCorect==false) interfata.label2.setText("UserName-ul este deja in folosinta");
}
interfata.getNick(mesaj);
new CititorDeMesaje(dis,dos,cs,interfata).start();//pornescu threadul ce imi citeste mesajele trimise de servar


interfata.schimbaInterfata2();

//INTERFATA #3


String comanda;

boolean vreauSaIes=false;
while(vreauSaIes==false){
    comanda=interfata.nextString();
    interfata.amCitit=false;
    if(comanda.equals("LIST")){
        try{
            dos.writeUTF("trimiteListaDeUseri");
        }
        catch(Exception e){
            System.out.println(e+" 4");
            //in caz ca pica servarul
            dis.close();
            dos.close();
            cs.close();
            System.exit(1);
        }
    }
    if(comanda.equals("MSG")) {
        String nume="";
        nume=interfata.nextString();
        mesaj=interfata.nextString();
        interfata.amCitit=false;
        try{
            dos.writeUTF("TrimiteMesaj");
            dos.writeUTF(nume);
            dos.writeUTF(mesaj);
        }
        catch(Exception e){
            System.out.println(e+" 5");
              //in caz ca pica servarul
              dis.close();
              dos.close();
              cs.close();
             System.exit(1);
        }
    }
    if(comanda.equals("BCAST")){
        mesaj=interfata.nextString();
        interfata.amCitit=false;
        try{
            dos.writeUTF("broadCast");
            dos.writeUTF(mesaj);
        }
        catch(Exception e){
            System.out.println(e+"2");
              //in caz ca pica servarul
            dis.close();
            dos.close();
            cs.close();
             System.exit(1);
        }
    }
    if(comanda.equals("NICK")){
        try{
            String newNick="";
            newNick=interfata.nextString();
            interfata.amCitit=false;
            dos.writeUTF("alegeNickNou");
            dos.writeUTF(newNick);
        }
        catch(Exception e){
            System.out.println(e+" 11");
              //in caz ca pica servarul
            dis.close();
            dos.close();
            cs.close();
             System.exit(1);
        }
    }
    if(comanda.equals("QUIT")){
        vreauSaIes=true;
        try{
            dos.writeUTF("QUIT");
        }
        catch(Exception e){
            System.out.println(e+" 1");
              //in caz ca pica servarul
            dis.close();
            dos.close();
            cs.close();
             System.exit(1);
        }
    }
    }
}
}

