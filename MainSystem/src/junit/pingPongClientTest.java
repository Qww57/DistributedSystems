package junit;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import static org.junit.Assert.*;

import org.junit.Test;

public class pingPongClientTest {

	/**
	 *  Create the client to run with the {@link pingPongServerTest} here
	 */
	@Test
	public void test() throws InterruptedException {
		
            System.out.println("Starting Client...");
        Socket s = null;
        boolean check = true;
        
        
            try{
                int serverPort = 1234;
                
                s = new Socket("localhost", serverPort);
                DataOutputStream out= new DataOutputStream(s.getOutputStream());
                out.flush();
                DataInputStream in = new DataInputStream(s.getInputStream());

                
            for(int i=1; i<5; i++){   
                    //TCP.Objects o = (TCP.Objects) in.readObject();
                    in.readUTF();

                    System.out.println("Recieved a PING from server " + i);
                        if(i==3){
                                Thread.sleep(10000);
                                System.out.println("We put the subSystem on hold in this round intentionally");
                        }else{
                                out.writeUTF("PONG");
                                System.out.println("We have sent response");
                            }
            }
                /*
                
                if(one.getID().equals(o.getID()))
                    check =false;
                */
            }catch(UnknownHostException e){
            	System.out.println("Sock: "+ e.getMessage());   
            }catch(EOFException e){
            	System.out.println("EOF: "+ e.getMessage());
            }catch(IOException e){
            	System.out.println("IO: "+ e.getMessage());    
            }finally{
                if(s!=null) 
                    try{
                            s.close();
                    }catch(IOException e){System.out.println("IO: "+ e.getMessage());}}                          

    } 
            
}


