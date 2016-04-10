package junit;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import Logic.Application.Sockets.AddressBook;

public class pingPongClientTest {

	/**
	 *  Client running with the {@link pingPongServerTest}
	 */
	@Test
	public void test() throws InterruptedException {		
		System.out.println("Starting Client...");
        Socket s = null;
             
        try{
        	int serverPort = AddressBook.getSubSystem(new Integer(0)).getPINGPONG_port();                
            s = new Socket("localhost", serverPort);
            DataOutputStream out= new DataOutputStream(s.getOutputStream());
            out.flush();
            DataInputStream in = new DataInputStream(s.getInputStream());
               
            for(int i = 0; i < 5; i++){
            	try{
			        String data = in.readUTF();		
			        System.out.println("Received a " + data + " from server " + i);
			        if(i == 1){
			        	System.out.println("We put the subSystem on hold in this round intentionally: " + i);
			            Thread.sleep(10000);
			            out.writeUTF("PONG");
			            System.out.println("We have sent response: " + i);
			        }else{
			            out.writeUTF("PONG");
			            System.out.println("We have sent response: " + i);
			        }
            	}catch(Exception e){
            		System.out.println("Could not read the message from server: " + i);
            		continue;
            	}
		    }         
        }catch(UnknownHostException e){
        	System.out.println("Sock: "+ e.getMessage());   
        }catch(EOFException e){
        	System.out.println("EOF: "+ e.getMessage());
        	e.printStackTrace();
        } catch (IOException e) {
        	System.out.println("IOE: "+ e.getMessage());
		}finally{
            if(s!=null){ 
                try{
                	s.close();
                }catch(IOException e){
                	System.out.println("IO: "+ e.getMessage());
                }
            } 
        }
        System.out.println("Class to the end");
    }           
}