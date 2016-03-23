package SendBytes;

import org.junit.Test;

public class testClient {

	@Test
	public void test(){
		try {
			Client client = new Client(0, "localhost", 7000, SObject.class);		
			SObject sobject = (SObject) client.sendPollRequest();	
			
			System.out.println(sobject.getName());
			
			client.closeClient();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
