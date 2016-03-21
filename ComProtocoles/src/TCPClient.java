
package tcpclient;

import java.io.*; 
import java.net.*;

class TCPClient 
{  
    public static void main(String args[]) 
  {   
        try {
            String input;
            String outgoing;
            BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
            input = inFromUser.readLine();
            
            Socket clientSocket = new Socket("localhost", 1234);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(input + '\n');
            
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //for timeout
            clientSocket.setSoTimeout(1000);
            outgoing = inFromServer.readLine();
            System.out.println("FROM SERVER: " + outgoing);
            clientSocket.close();
        } catch (IOException ex) {
            System.out.println("IO Exception: "+ ex.getMessage() );
        }
  } 
}