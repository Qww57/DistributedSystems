package SendBytes.Templates.Tests;

import org.junit.Test;

import SendBytes.SObject;
import SendBytes.Templates.Client;

public class testClient {

	@Test
	public void test(){
		try {	
			Client client = new Client(0, "localhost", 7000, SObject.class);		
			SObject sobject = (SObject) client.sendPollRequest();	
			System.out.println(sobject.getName());
			System.out.println(client.getObject());	
			
			client.closeClient();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
