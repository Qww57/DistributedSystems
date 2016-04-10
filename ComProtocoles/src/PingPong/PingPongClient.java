package PingPong;

import java.net.*;
import java.io.*;
/**
 *
 * @author Ehsan
 */
/*
 class Objects implements Serializable{
        String name;
        String ID;
        
    
       public Objects(String name, String ID){
           this.name = name;
           this.ID = ID;
           
       }
     
        public String getName(){
            return this.name;
        }
        
        public String getID(){
            return this.ID;
        }
}
*/
public class PingPongClient {
        
    public static void main(String[] args) throws ClassNotFoundException {
        
        //Objects one = new Objects("PONG","1");
        System.out.println("Starting Client...");
        Socket s = null;
        boolean check = true;
        
        
            try{
                int serverPort = 1234;
                
                s = new Socket("localhost", serverPort);
                //System.out.println("we are here 2");
                DataOutputStream out= new DataOutputStream(s.getOutputStream());
                out.flush();
                //System.out.println("we are here 3");
                DataInputStream in = new DataInputStream(s.getInputStream());
                //System.out.println("we are here 4");
                
            while(check){   
                //TCP.Objects o = (TCP.Objects) in.readObject();
                in.readUTF();
                
                System.out.println("Recieved a PING from server");
                
                //if(o.getName().equals("PING"))
                out.writeUTF("PONG");
                
                System.out.println("We have sent response");
            }
                /*
                
                if(one.getID().equals(o.getID()))
                    check =false;
                */
            }catch(UnknownHostException e){
            	System.out.println("Sock: "+ e.getMessage());   
            }catch(EOFException e){
            	System.out.println("EOF: "+ e.getMessage());
            }catch(IOException e){
            	System.out.println("IO: "+ e.getMessage());    
            }finally{
                if(s!=null) 
                    try{
                            s.close();
                    }catch(IOException e){System.out.println("IO: "+ e.getMessage());}}                          

    } 
}
