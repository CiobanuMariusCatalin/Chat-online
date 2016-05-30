package Server;

import java.io.*;
public class ClientData {
   private DataOutputStream clientDataOutputStream;
   private String userName;
   private int id;
   public ClientData(DataOutputStream clientDataOutputStream,int id){
       this.clientDataOutputStream=clientDataOutputStream;
       this.id=id;
   }
   public void setUserName(String userName){
       this.userName=new String(userName);
   }
   public DataOutputStream getDataOutputStream(){
       return clientDataOutputStream;
   }
   public int getId(){
       return id;
   }
   String getUserName(){
       return userName;
   }
}
