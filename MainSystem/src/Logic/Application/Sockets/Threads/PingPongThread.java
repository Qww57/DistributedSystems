package Logic.Application.Sockets.Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import Logic.Application.CommunicationController;
import Logic.Application.Sockets.AddressBook;
import Logic.Application.Sockets.Address;
import Logic.Application.utils.TimeLimitedCodeBlock;

public class PingPongThread extends Thread {
    
     DataInputStream in;
     DataOutputStream out;
     Socket clientSocket;
     Integer clientID;
         
     public PingPongThread(Socket aClientSocket, Address address) throws IOException{
         clientSocket = aClientSocket;
         in = new DataInputStream(clientSocket.getInputStream());
         out = new DataOutputStream(clientSocket.getOutputStream());
         clientID = AddressBook.getSubSystemID(address);        
         System.out.println("New thread created for " + clientID +": " + address.getHost() + "/" + address.getPINGPONG_port());
         this.start(); 
     }
 
     public void run(){   	 
    	 int i = 0;
    	 boolean check = true;
    	 while(check){    
    		 try {  
    			 int msTimeOut = 2000;
    			 Thread.sleep(5000);                  			 
    			 out.writeUTF("PING");
	             System.out.println("We have sent out a PING message to client: " + clientSocket.getLocalAddress() + " : " + i);
    			 
    			 TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
 			        @Override
 			        public void run() {
 			        	try {
 			        		String data = in.readUTF();
 			        		if (data.equals("PONG"))
 			        			CommunicationController.updateStatus(clientID, Boolean.TRUE);			        		
 			        		out.writeUTF("PING");
 			        		System.out.println ("We received a response from client " + clientSocket.getLocalAddress());
 						} catch (Exception e) {
 							System.out.println("Exception: " + e.getMessage());
 						}
 				    }}, msTimeOut, TimeUnit.MILLISECONDS);
	                          
    		 } catch (IOException ex) {
    			 System.out.println("IOException: "+ ex.getMessage() + " - Closing the thread");
                 CommunicationController.updateStatus(clientID, Boolean.FALSE);
                 check = false;
                 
            } catch (InterruptedException ex) {
                 System.out.println("InterruptedException: "+ ex.getMessage() + " - Closing the thread");
                 CommunicationController.updateStatus(clientID, Boolean.FALSE);
                 check = false;
                 
    		} catch (Exception ex) {
    			System.out.println("Client is not responding");
    			CommunicationController.updateStatus(clientID, Boolean.FALSE);
             	continue;
			}
    		i++;
    	 }   
     }
}