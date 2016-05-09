package junit;
import org.junit.Test;

import Logic.Application.DataTransfertObjects.PackageDTO;
import Logic.Application.Sockets.ObjectClientTemplate;

public class testDTOReceiver {

	@Test
	public void testSubSystem(){
		try {
			System.out.println("Starting");
			ObjectClientTemplate client = new ObjectClientTemplate(0, "localhost", 7000);
			System.out.println("Client created => Send poll request");
			Object o = client.sendPollRequest();
			System.out.println(o.getClass());
			
			if (o instanceof PackageDTO){
				PackageDTO packageDTO = (PackageDTO) o;
				System.out.println(packageDTO.getPackageId());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
}
