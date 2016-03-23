package TCP;

import java.io.*;
import java.net.*;
/**
 *
 * @author Ehsan
 */
class EchoThread extends Thread{
        DataInputStream in;
        DataOutputStream out;
        Socket clientSocket;
        
        public EchoThread(Socket aClientSocket){
            try{
                    clientSocket=aClientSocket;
                    in= new DataInputStream(clientSocket.getInputStream());
                    out= new DataOutputStream(clientSocket.getOutputStream());
                    this.start();
                    
                }catch(IOException e){System.out.println("EchoThread: "+ e.getMessage());} 
        }
        
        public void run(){
            try{
                    String data=in.readUTF();
                    out.writeUTF(data);
                    
            }catch(EOFException e){System.out.println("EOF: "+e.getMessage());
            }catch(IOException e){System.out.println("IO: "+e.getMessage());
            }finally{
                    try{
                        clientSocket.close();
                    }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}}
        }
}
