package junit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import Logic.Application.Sockets.ServerTemplate;
import Logic.Application.Sockets.ServerTemplate.ThreadType;
import Logic.Application.Sockets.Threads.PingPongThread;

public class pingPongServerTest {

	@Test
	public void test() {
		
        try{
            System.out.println("Starting Server...");
            int serverPort = 1234;
            ServerSocket listenSocket = new ServerSocket(serverPort);
           
            long startTime = System.currentTimeMillis();
            
            while(true){
            	System.out.println("New thread created: " + (System.currentTimeMillis() - startTime) + " seconds");
                  Socket clientSocket = listenSocket.accept();
                  PingPongThread c = new PingPongThread(clientSocket);
            }
                
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}		
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
