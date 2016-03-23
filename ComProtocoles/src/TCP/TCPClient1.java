package TCP;

import java.net.*;
import java.io.*;
/**
 *
 * @author Ehsan
 */
public class TCPClient1 {
    public static void main(String[] args) {
        Socket s=null;
        boolean check=true;
        while(check)
            try{
                    int serverPort = 1234;
                    String message= "hey you!";
                    s= new Socket("localhost", serverPort);
                    DataInputStream in= new DataInputStream(s.getInputStream());
                    DataOutputStream out= new DataOutputStream(s.getOutputStream());
                    out.writeUTF(message);
                    System.out.println("We have sent a message: "+message);
                    String data = in.readUTF();
                    System.out.println("Recieved: "+ data);
                        if(data.equals(message))
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