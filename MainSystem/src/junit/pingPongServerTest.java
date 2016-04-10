package junit;

import org.junit.Test;

import Logic.Application.Sockets.AddressBook;
import Logic.Application.Sockets.Addresses;
import Logic.Application.Sockets.ServerTemplate;
import Logic.Application.Sockets.ServerTemplate.ThreadType;

public class pingPongServerTest {
              	
	@Test
	public void testPingPongServer() {
		System.out.println("Server started");
		
		// Get address of subSystem 0
		Addresses clientAddress = AddressBook.getSubSystem(new Integer(0));
		ServerTemplate server = new ServerTemplate(0, "localhost", 6000, ThreadType.PingPongThread);
		server.setSubSystemAddresses(clientAddress);
		server.startServer();	
	}        
}