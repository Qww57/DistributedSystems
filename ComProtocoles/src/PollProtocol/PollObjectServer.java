package PollProtocol;

import java.net.*;
import java.io.*;
import utils.SObject;
/**
 * Server class which is used with {@link PollObjectClient}
 * 
 * @author Ehsan
 */
public class PollObjectServer {
    public static void main(String[] args) {
        try{
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true){
                  Socket clientSocket = listenSocket.accept();
                  System.out.println("We have recieved a message from client!" );
                  System.out.println("We sent back echo message to the client!");
            }
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }
    
}

class PollObjectThread extends Thread{
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket clientSocket;
        
        
        public PollObjectThread(Socket aClientSocket) throws IOException{
            clientSocket=aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.start(); 
        }
        
        public void run(){
            try {               
                Object o = in.readObject();
                System.out.println("Received object of type: " + o.getClass());
                
                SObject object = new SObject("Name", "ID");
                out.writeObject(object);
                
            } catch (IOException ex) {
                System.out.println("IOException: "+ ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException: "+ ex.getMessage());
            }
            try{
                clientSocket.close();
            }catch(IOException e){
            	System.out.println("IOException: "+ e.getMessage());
            }
        }
}
