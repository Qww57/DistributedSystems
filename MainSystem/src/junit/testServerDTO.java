package junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Logic.Application.CommunicationController;
import Logic.Application.DataTransfertObjects.PackageDTO;
import Logic.Exposition.ClientDataRequest;
import Logic.Exposition.TreatmentMethod;
import Logic.Treatment.ComputationDivider;
import Logic.Application.Sockets.ServerTemplate;
import Logic.Application.Sockets.ServerTemplate.ThreadType;
import Logic.Application.utils.ConvertBytes;
import utils.Printer;

/**
 * Test generating a DTO sending server, based on the thread {@link DTOSenderObjectThread} or
 * {@link DTOSenderByteThread}. This server reflects also partially what the behaviour of the 
 * {@link CommunicationController} should be, it should initialize a {@link ComputationDivider}, 
 * use it in order to split resources between different subsystems -which will save them in the 
 * data base using the repository and add them to the waiting queue. This tests then take the 
 * first element of the queue in order to send it.
 * 
 * @author Quentin
 *
 */
public class testServerDTO{
	
	byte[] treatment;
	
	@Before
	public void Initialize(){
		TreatmentMethod treatmentMethod = new TreatmentMethod();
		treatment = ConvertBytes.ConvertToBytes(treatmentMethod);
	}
	
	@Test
	public void testSend(){
		
		ClientDataRequest clientData = new ClientDataRequest(treatment, new Integer(0), new Integer(50));
		
		int numberOfPackages = 1;
		int resourcePerPackage = 5;
		
		ComputationDivider comp = new ComputationDivider(numberOfPackages, resourcePerPackage);
		try {
			List<PackageDTO> packages = comp.createPackages(clientData);
			assertEquals(numberOfPackages, packages.size());	
			for(int i = 0; i < packages.size() - 1; i++){
				assertEquals(resourcePerPackage, packages.get(i).getResources().size());
				Printer.print(packages.get(i));
			}
			
			// Assign it to the subsystem 1
			Integer subSystem = new Integer(1);	
			comp.assignPackage(packages.get(0), subSystem);
			Printer.print(packages.get(0));
			
			CommunicationController.addToSendingQueue(packages.get(0));
			
			ServerTemplate server = new ServerTemplate(0, "localhost", 7000, ThreadType.DTOObjectSender);
			server.startServer();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Should not raised exception");
		} 
	}
}
