
package tcpserver;
import java.io.*; 
import java.net.*;

class TCPServer 
{    
    public static void main(String args[]) throws IOException        
    {          
        String clientMsg;          
        String capitalizedSentence;          
        ServerSocket welcomeSocket = new ServerSocket(1234);     
        
        while(true)          
        {             
            Socket connectionSocket = welcomeSocket.accept();             
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());             
            clientMsg = inFromClient.readLine();             
            System.out.println("Received: " + clientMsg);             
            capitalizedSentence = clientMsg.toUpperCase() + '\n';
            System.out.println("Sent back response! ");
            outToClient.writeBytes(capitalizedSentence);          
        }       
    } 
}
