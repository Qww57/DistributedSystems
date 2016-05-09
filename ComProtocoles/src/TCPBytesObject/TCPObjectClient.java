package TCPBytesObject;

import java.net.*;
import java.io.*;
import utils.SObject;

/** Client implementation to send and receive objects 
 * which is used with {@link TCPObjectServer}
 *
 * @author Ehsan
 */
public class TCPObjectClient {
        
    public static void main(String[] args) throws ClassNotFoundException {
        SObject one = new SObject("Name", "ID");
        
        Socket s = null;
        boolean check = true;
        
         while(check)
            try{
                int serverPort = 1234;
                
                s = new Socket("localhost", serverPort);
                ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                
                out.writeObject(one);
                System.out.println("We have sent a message: "+ one.getName()+one.getID() );
                SObject o = (SObject) in.readObject();
                
                System.out.println("Recieved: "+ o.getName() + o.getID());
                if(one.getID().equals(o.getID()))
                    check =false;
                    
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
