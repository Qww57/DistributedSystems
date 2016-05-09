package Presentation;

import java.util.List;

import Logic.Application.CommunicationController;
import Logic.Application.DataTransfertObjects.PackageDTO;
import Logic.Application.utils.ConvertBytes;
import Logic.Exposition.ClientDataRequest;
import Logic.Exposition.TreatmentMethod;
import Logic.Treatment.ComputationDivider;

/**
 * This class is implemented now in order to model the behavior that should 
 * have server thread when receiving information from the client. It converts
 * the request as DTO object, then creates a list of packages and put them in 
 * waiting queue.
 * 
 * @author Quentin
 *
 */
public class MainController {
	
	public MainController(){
	
		// Creating a client request -- TODO should be a real one received through server threads
		TreatmentMethod treatmentMethod = new TreatmentMethod();
		byte[] treatment = ConvertBytes.ConvertToBytes(treatmentMethod);
		ClientDataRequest clientData = new ClientDataRequest(treatment, new Integer(0), new Integer(50));
		
		// Initializing a computation divider dedicated for the new computation
		int numberOfPackages = 1;
		int resourcePerPackage = 5;		
		ComputationDivider comp = new ComputationDivider(numberOfPackages, resourcePerPackage);
	
		try {
			// Creating the list of packages from the client request
			List<PackageDTO> packages = comp.createPackages(clientData);
			
			// Putting them in the waiting queue
			for (int i = 0; i < packages.size(); i++){
				CommunicationController.addToSendingQueue(packages.get(i));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
}
