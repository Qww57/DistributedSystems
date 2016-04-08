package TCP;

import java.net.*;
import java.io.*;
import java.util.HashMap;

/**
 *
 * @author Ehsan
 */
public class PingPongServer {
    
    public static void main(String[] args) {
        
        
        try{
            System.out.println("Starting Server...");
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            //System.out.println("we are here");
            while(true){
                  Socket clientSocket = listenSocket.accept();
                  //System.out.println("we are here 2");
                  PingPongThread c = new PingPongThread(clientSocket);
                  //System.out.println("We sent a Ping...");
            }
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }
    
}

class PingPongThread extends Thread{
        DataInputStream in;
        DataOutputStream out;
        Socket clientSocket;
        //byte[] buffer=new byte[1000];
        
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
                Thread.sleep(5000);
                out.writeUTF("PING");
                System.out.println("We have sent out a PING message to client");
                
                in.readUTF();
                //TCP.Objects o = (TCP.Objects) in.readObject();
                System.out.println ("We recieved a response from cilent");
                
                System.out.println(Thread.currentThread().getState());
                /*
                if(o.getName().equals("PONG"))
                    SubSystems.put(o.getID(), true);
                else
                    SubSystems.put(o.getID(), false);
                */
                //System.out.println(SubSystems.get("1"));
                //System.out.println (o.getClass());
            } catch (IOException ex) {
                System.out.println("IOException: "+ ex.getMessage());
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException: "+ ex.getMessage());
            }
            try{
                clientSocket.close();
            }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}
        }
}
