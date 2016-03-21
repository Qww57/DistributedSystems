import java.io.*; 
import java.net.*;

class TCPServer 
{    
    public static void main(String args[]) throws IOException        
    {          
        String clientMsg;          
        String capitalizedSentence;
        boolean check =true;
             
    try{   
            ServerSocket welcomeSocket = new ServerSocket(1234);         
            Socket connectionSocket = welcomeSocket.accept();             
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());             
            clientMsg = inFromClient.readLine();
            System.out.println("Received: " + clientMsg);
            outToClient.writeBytes("bs"); //testing with wrong respond
    while(check)          
        {
            if (clientMsg.equals("REPT")){
                
                capitalizedSentence = clientMsg.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
                System.out.println("We Sent response again! ");
            }
            else{
                capitalizedSentence = clientMsg.toUpperCase() + '\n';
                outToClient.writeBytes(capitalizedSentence);
                System.out.println("We Sent response! ");
                check =false;
            }
              
        }
        
         }catch (IOException ex) {
            System.out.println("IO Exception: "+ ex.getMessage() );
    } 
        
  }
        
 }
