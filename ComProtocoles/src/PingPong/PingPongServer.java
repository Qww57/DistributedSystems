package PingPong;

import java.net.*;
import java.io.*;

/**
 * Server that should be used with {@link PingPongClient}
 * This class has a PingPongThread which extends Thread that 
 * and checks availability of clients by sending messages and waiting reply
 * 
 * @author Ehsan
 */

public class PingPongServer {
    public static boolean check= true;
    
    public static void main(String[] args) {
        
        
        try{
            System.out.println("Starting Server...");
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("we are here");
            
                      Socket clientSocket = listenSocket.accept();
                      PingPongThread c = new PingPongThread(clientSocket);
                      System.out.println("We sent a Ping...");
            
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }
}

class PingPongThread extends Thread{
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
                    
                while(true){ 
                    Thread.sleep(5000);
                    out.writeUTF("PING");
                    System.out.println("We have sent out a PING message to client");
                    clientSocket.setSoTimeout(2000);
                    in.readUTF();
                    
                    System.out.println ("We recieved a response from cilent");
                    System.out.println(Thread.currentThread().getState());
                    
                }    
                    
            } catch (IOException ex) {
                System.out.println("IOException: "+ ex.getMessage() + " Subsystem is down");
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException: "+ ex.getMessage());
            }
            try{
                clientSocket.close();
            }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}
        }
}
