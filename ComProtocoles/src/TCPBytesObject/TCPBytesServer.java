package TCPBytesObject;

import java.io.*; 
import java.net.*;

/**
 * Server for TCP bytes which is used with {@link TCPBytesClient} 
 * using bytes
 *
 */

class TCPBytesServer {    
    
	public static void main(String args[]) throws IOException {          
    
		String clientMsg;          
        String capitalizedSentence;
        boolean check = true;
             
        try{   
            @SuppressWarnings("resource")
			ServerSocket welcomeSocket = new ServerSocket(1500);         
            Socket connectionSocket = welcomeSocket.accept();             
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());             
            clientMsg = inFromClient.readLine();
            System.out.println("Received: " + clientMsg);
            outToClient.writeBytes("bs"); //testing with wrong respond
   
            while(check){
            	if (clientMsg.equals("REPT")){               
	                capitalizedSentence = clientMsg.toUpperCase() + '\n';
	                outToClient.writeBytes(capitalizedSentence);
	                System.out.println("We Sent response again! ");
	            }else{
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