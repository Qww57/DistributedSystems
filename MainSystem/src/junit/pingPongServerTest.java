package junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import Logic.Application.Sockets.ServerTemplate;
import Logic.Application.Sockets.ServerTemplate.ThreadType;
import Logic.Application.Sockets.Threads.PingPongThread;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import Logic.Application.Sockets.Threads.PingPongThread.*;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;

public class pingPongServerTest {

	//@Test
	public void testNewThread() {
		
        try{
            System.out.println("Starting Server...");
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("we are here" + listenSocket);
            Socket clientSocket = listenSocket.accept();
            System.out.println("we are here2" );
            PingPongThread c = new PingPongThread(clientSocket);
            System.out.println("We created new thread...");
            
            
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}		
	}
        
        @Test
        public void testRun() throws IOException{
                        
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            System.out.println("we are here" + listenSocket);
            Socket clientSocket = listenSocket.accept();
            
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            
            
            try {          
                    
                while(true){ 
                    Thread.sleep(5000);
                    out.writeUTF("PING");
                    System.out.println("We have sent out a PING message to client");
                    clientSocket.setSoTimeout(2000);
                    in.readUTF();
                    //TCP.Objects o = (TCP.Objects) in.readObject();
                    System.out.println ("We recieved a response from cilent till " + time());

                    System.out.println(Thread.currentThread().getState());
                    
                    
                }    
                    
            } catch (IOException ex) {
                System.out.println("IOException: "+ ex.getMessage() + " Subsystem is down at "+ time());
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException: "+ ex.getMessage());
            }
            try{
                clientSocket.close();
            }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}
        }
            
        
        
        
        
	
	/**
	 * How it should be used: Test using the server template
	 * 
	 * TODO uncomment here and test it 
	 */
	//@Test
	public void testPingPongServer() {
		ServerTemplate server = new ServerTemplate(0, "localhost", 6000, ThreadType.PingPongThread);
		server.startServer();	
		System.out.println("Server started");
		
		assertTrue(true);
	}        

}
