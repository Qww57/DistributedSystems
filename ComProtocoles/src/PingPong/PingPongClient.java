package PingPong;

import java.net.*;
import java.io.*;
/**
 *  Client that should be used with {@link PingPongServer}
 * @author Ehsan
 */

public class PingPongClient {
        
    public static void main(String[] args) throws ClassNotFoundException {
        
        System.out.println("Starting Client...");
        Socket s = null;
        boolean check = true;
        
        
            try{
                int serverPort = 1234;
                s = new Socket("localhost", serverPort);
                DataOutputStream out= new DataOutputStream(s.getOutputStream());
                out.flush();
                DataInputStream in = new DataInputStream(s.getInputStream());
                
            while(check){   
 
                in.readUTF();
                System.out.println("Recieved a PING from server");
                
                out.writeUTF("PONG");
                System.out.println("We have sent response");
            }
                
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
