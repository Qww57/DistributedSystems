package TCP;

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

public class TCPClient1 {
        
    public static void main(String[] args) throws ClassNotFoundException {
        Objects one = new Objects("Name", "ID");
        
        Socket s=null;
        boolean check=true;
        
         while(check)
            try{
                    int serverPort = 1234;
                    
                    s= new Socket("localhost", serverPort);
                    ObjectOutputStream out= new ObjectOutputStream(s.getOutputStream());
                    out.flush();
                    ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                    
                    out.writeObject(one);
                    System.out.println("We have sent a message: "+ one.getName()+one.getID() );
                    TCP.Objects o = (TCP.Objects) in.readObject();
                    
                    System.out.println("Recieved: "+ o.getName() + o.getID());
                        if(one.getID().equals(o.getID()))
                            check =false;
                    s.setSoTimeout(1000);
            }catch(UnknownHostException e){System.out.println("Sock: "+ e.getMessage());   
            }catch(EOFException e){System.out.println("EOF: "+ e.getMessage());
            }catch(IOException e){System.out.println("IO: "+ e.getMessage());    
            }finally{
                if(s!=null) 
                    try{
                            s.close();
                    }catch(IOException e){System.out.println("IO: "+ e.getMessage());}}                          

    } 
}
