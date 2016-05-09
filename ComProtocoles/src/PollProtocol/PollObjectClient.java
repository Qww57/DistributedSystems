package PollProtocol;

import java.net.*;
import java.io.*;
import utils.SObject;
/**
 * Implementation for poll protocol done with objects instead of bytes
 * Client is used with {@link PollObjectServer}
 * @author Ehsan
 */

public class PollObjectClient {
        
    public static void main(String[] args) throws ClassNotFoundException {
    	String poll = "POLL";
        
        Socket s = null;
        boolean check = true;
        
         while(check)
            try{
                int serverPort = 1234;
                
                s = new Socket("localhost", serverPort);
                ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                
                out.writeObject(poll);
                System.out.println("We have sent a poll message");
                
                Object o = in.readObject();
                
                if (o instanceof SObject){
                	SObject object1 = (SObject) o;
                    
                    System.out.println("Recieved: "+ object1.getName() + object1.getID());
                    check =false;
                }
                
                s.setSoTimeout(1000);
                
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
