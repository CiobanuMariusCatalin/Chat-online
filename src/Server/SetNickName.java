
package Server;
//Folosim aceasta clasa sa putem sincroniza alegerea nickNameului si redenumirea lui
public class SetNickName extends Thread{
  //trimitem acestei clase threadul ce la apelat sa stim unde trebuie sa modificam date  
  private Conexiune conexiune;
  //variabila boolean ne spune daca redenumim un nickname curent cu altul, sau introducem nickname-ul pentru un client nou
  private boolean rename=false;
  //newNick este folosit doar in cazul in care vrem sa redenumim numele un client deja creat 
  private String newNick;
  //Avem 2 constructori unul pentru un client nou si celalalt pentru un client ce vrea sa isi schimbe numele
 
  
  //Constructorul pentru clientii ce vor sa isi schimbe numele
  SetNickName(Conexiune conexiune,boolean rename,String newNick){
      this.conexiune=conexiune;
      this.rename=rename;
      this.newNick=new String(newNick);
      start();
  }
  
  //Constructorul pentru clinetii noi
  SetNickName(Conexiune conexiune){  
 this.conexiune=conexiune;
 start();
}

    //functia imi returneaza false daca nu pot sa adaug nickname-ul altfel il adauga si functia returneaza true
synchronized static boolean potAdaugaNick(String nick){
    /*nickName-ul QUIT este singurul nickname pe care un user nu il poate folosi deoarece
     programul il foloseste ca sa isi dea seama cand interfata clientului a fost inchisa la momentul
     alegerii nickname-ului prin trimiterea nicknameului ca si QUIT si rupanu-se legaturile*/
    if(Conexiune.getNickNameList().contains(nick) || nick.equals("QUIT")) return false;
    Conexiune.getNickNameList().add(nick);
    return true;
}  
  
    public void run() {
if(rename==false){//ALEGEREA NICKNAMEULUI INAINTE DE CONECTARE
   try {
        boolean nickCorect=false;
        String newNick="";
        //daca clientul a trimis servarului nick-ul ca si QUIT inseamna ca el a iesit deci trebuie sa inchidem
        //si threadul contruit pentru clientul respectiv
        while(nickCorect==false && !newNick.equals("QUIT")){
            newNick=conexiune.getDataInputStream().readUTF();
            nickCorect=potAdaugaNick(newNick);
            /*In lista de ClientData nu stim pe ce pozitie se afla DataOutputStreamul 
             * clientului nostru asa ca am adaugat un id in ClientData si un id in Threadul pentru clientul
             * nostru care sunt egale si numai sunt alte id-uri egale cu ei*/
            for(int i=0;i<conexiune.getSockete().size();i++){
                //exista deja o functie pt thread numita getId asa ca a trebuit sa ii schimb numele
             if(conexiune.getSockete().get(i).getId()==conexiune.getIdul())
            conexiune.getSockete().get(i).getDataOutputStream().writeBoolean(nickCorect);
        }
        }
        /*daca noi in interfata #2 anume cand cerem un username am inchis clientul vrem sa numai continum 
         programul nici in server asa ca oprim threadul servarului pentru clientul respectiv 
         defapt el nici nu este pornit el pornit doar daca numele este diferit de QUIT*/
        if(!newNick.equals("QUIT")){
        conexiune.setNickName(newNick);
        for(int i=0;i<conexiune.getSockete().size();i++)
             if(conexiune.getSockete().get(i).getId()==conexiune.getIdul())
                 conexiune.getSockete().get(i).setUserName(conexiune.getNickName());
        AfisPeEcran.Mesajul("Client nou cu numele : "+conexiune.getNickName());
        conexiune.start();
    }else{
            //daca numele este QUIT trebuie sa scoate ClientData-ul din vectorul getSockete
             for(int i=0;i<conexiune.getSockete().size();i++){   
             if(conexiune.getSockete().get(i).getId()==conexiune.getIdul()){
                conexiune.getSockete().get(i).getDataOutputStream().close();//inchidem streamul de iesire
                 conexiune.getSockete().remove(i);
                 conexiune.getDataInputStream().close();
                 conexiune.getDataInputStream().close();
             }
        }
    }
    }
    catch(Exception e) {
        System.out.println(e+" 7");}  
}
else{//COMANDA NICK(rename)
    try{
    boolean nickCorect=false;
    String oldNick=conexiune.getNickName();
    /*verifica daca newNick este in Lista de NickNameuri si daca nu, il adauga*/
    nickCorect=potAdaugaNick(newNick);
    for(int i=0;i<conexiune.getSockete().size();i++)
             if(conexiune.getSockete().get(i).getId()==conexiune.getIdul()){
                 //Ii transmit clientului ce comanda sa executat si ce inseamna datele ce ii sunt transmise
                 conexiune.getSockete().get(i).getDataOutputStream().writeUTF("amIncercatSaSchimbamNickName-ul");
                 conexiune.getSockete().get(i).getDataOutputStream().writeBoolean(nickCorect);   
                 conexiune.getSockete().get(i).getDataOutputStream().writeUTF(newNick);//trimitem si nickul pnetru ca sa il putem schimba in interfata   
             if(nickCorect==true)
                 //Daca am reusit sa schimbam numele modificam si in ArrayListul ClientData
                  conexiune.getSockete().get(i).setUserName(newNick);
             }
    if(nickCorect==true){
        //schimbam si numele salvat pentru client in threadul conexiune
        conexiune.setNickName(newNick);
        for(int i=0;i<conexiune.getNickNameList().size();i++){
            //scoate numele din array-ul de username
            if(conexiune.getNickNameList().get(i).equals(oldNick))
                conexiune.getNickNameList().remove(i);
        }
          AfisPeEcran.Mesajul(oldNick+" si-a schimbat numele in: "+conexiune.getNickName());
    }   
    }
    catch(Exception e){
        System.out.println(e+" 10");
    }
}

    }

 public String getNick(){
            return conexiune.getNickName();
        }
}
