import java.net.*;
import java.io.*;
/**
 *
 * @author Ehsan
 */
public class TCPServer1 {
    public static void main(String[] args) {
        try{
                int serverPort = 1234;
                ServerSocket listenSocket = new ServerSocket(serverPort);
                while(true){
                      Socket clientSocket = listenSocket.accept();
                      System.out.println("We have recieved a message from client!");
                      EchoThread c = new EchoThread(clientSocket);
                      System.out.println("We sent back echo message to the client!");
                }
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }
    
}
