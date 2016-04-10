package Logic.Application.Sockets.Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class PingPongThread extends Thread {
    
    
     DataInputStream in;
     DataOutputStream out;
     Socket clientSocket;
    
     
     public PingPongThread(Socket aClientSocket) throws IOException{
         clientSocket=aClientSocket;
         in = new DataInputStream(clientSocket.getInputStream());
         out = new DataOutputStream(clientSocket.getOutputStream());
         this.start(); 
     }
 
     public void run(){
            
            try {
                //HashMap<String, Boolean> SubSystems = new HashMap<>();
                //Objects ping = new Objects("PING", "1");
                
                    
                while(true){ 
                    Thread.sleep(5000);
                    out.writeUTF("PING");
                    System.out.println("We have sent out a PING message to client");
                    clientSocket.setSoTimeout(2000);
                    in.readUTF();
                    //TCP.Objects o = (TCP.Objects) in.readObject();
                    System.out.println ("We recieved a response from cilent");

                    System.out.println(Thread.currentThread().getState());
                    
                    
                } 
         } catch (IOException ex) {
             System.out.println("IOException: "+ ex.getMessage());
         } catch (InterruptedException ex) {
             System.out.println("InterruptedException: "+ ex.getMessage());
         }
         try{
             clientSocket.close();
         }catch(IOException e){
        	 System.out.println("IOException: "+ e.getMessage());
         }
     }
}