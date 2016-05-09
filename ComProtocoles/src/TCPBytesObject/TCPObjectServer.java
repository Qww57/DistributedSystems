package TCPBytesObject;

import java.net.*;
import java.io.*;
import utils.SObject;

/** Server implementation to send and receive objects 
 * which is used with {@link TCPObjectClient}
 *
 * @author Ehsan
 */

public class TCPObjectServer {
    public static void main(String[] args) {
        try{
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true){
                  Socket clientSocket = listenSocket.accept();
                  System.out.println("We have recieved a message from client!" );
                  EchoThread c = new EchoThread(clientSocket);
                  System.out.println("We sent back echo message to the client!");
            }
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }
    
}

class EchoThread extends Thread{
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket clientSocket;
        byte[] buffer=new byte[100];
        
        public EchoThread(Socket aClientSocket) throws IOException{
            clientSocket=aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.start(); 
        }
        
        public void run(){
            try {
                
                SObject o = (SObject) in.readObject();
                System.out.println (o.getName() + o.getID());
                
                out.writeObject(o);
                System.out.println (o.getClass());
            } catch (IOException ex) {
                System.out.println("IOException: "+ ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException: "+ ex.getMessage());
            }
            try{
                clientSocket.close();
            }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}
        }
}
