package POLLObject;

import java.net.*;
import java.io.*;
/**
 *
 * @author Ehsan
 */

 class Objects implements Serializable{
        String name;
        String ID;
    
       public Objects(String name, String id){
           this.name = name;
           this.ID = id;
       }
     
        public String getName(){
            return this.name;
        }
        
        public String getID(){
            return this.ID;
        } 
}

public class POLLObjectClient {
        
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
                
                if (o instanceof Objects){
                	Objects object1 = (Objects) o;
                    
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
