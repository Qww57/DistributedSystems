package junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Logic.Application.DataTransfertObjects.PackageDTO;
import Logic.Application.DataTransfertObjects.ResourceDTO;
import Logic.Exposition.ClientDataRequest;
import Logic.Persistence.ResourceRepository;
import Logic.Persistence.TreatmentRepository;
import Logic.Treatment.ComputationDivider;
import Logic.Treatment.Objects.ResourcePOJO;
import utils.Printer;

/**
 * This test focuses on the {@link ComputationDivider} class. These tests try to reflect 
 * what the behaviour of the computation divider should be. It should split computation 
 * received from client in a given number of packages, save them in the data base using 
 * the repository, put them in the waiting queue and finally assign them to subsystems.
 * Please note that some of these functions are achieved with help of the communication 
 * controller.
 * 
 * @author Quentin
 *
 */
@SuppressWarnings({ "boxing", "unused" })
public class testComputationDivider {
	
	/* Test created in order to use the splitComputation function that 
	 * should take a resource POJO object and split it in different ones
	 * 
	 * Finally not used.
	 */
	//@Test
	public void testComputation(){
		
		// Creating a new POJO resource
		ResourcePOJO pojo = new ResourcePOJO( 
				"Treatment",
				new Integer(15), 
				new Integer(1500));
		
		// Initializing the computation divider
		int numberOfElements = 12;
		int resourcePerPackage = 15;
		ComputationDivider comp = new ComputationDivider(numberOfElements, resourcePerPackage);
		
		// Creating the list of split resources
		List<ResourcePOJO> resources = comp.splitComputation(pojo);
		
		// Printing the results
		System.out.println("SIZE:" + resources.size());
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
		
		// Starting a second test
		System.out.println("New test: ");
		
		// Changing parameters of the computation divider
		numberOfElements = 10;
		resourcePerPackage = 10;
		comp = new ComputationDivider(numberOfElements, resourcePerPackage);
		
		// Creating the new list of split resources
		resources = comp.splitComputation(pojo);
		
		// Printing the results
		System.out.println("SIZE:" + resources.size());
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
	}
	
	@Test
	public void testDivide(){
		ClientDataRequest clientData = new ClientDataRequest(null, new Integer(0), new Integer(50));
		
		int numberOfPackages = 5;
		int resourcePerPackage = 2;
		
		long startTime = System.currentTimeMillis();
		
		ComputationDivider comp = new ComputationDivider(numberOfPackages, resourcePerPackage);
		try {
			List<PackageDTO> packages = comp.createPackages(clientData);
			assertEquals(numberOfPackages, packages.size());	
			for(int i = 0; i < packages.size() - 1; i++){
				assertEquals(resourcePerPackage, packages.get(i).getResources().size());
				Printer.print(packages.get(i));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Should not raised exception");
		}
		
		ResourceRepository.print();
		TreatmentRepository.print();
		
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println(duration + " ns ");
	}

	@Test
	public void testAssign(){
		ClientDataRequest clientData = new ClientDataRequest(null, new Integer(0), new Integer(10));
		
		int numberOfPackages = 1;
		int resourcePerPackage = 5;
		int subSystem = 10;
		
		long startTime = System.currentTimeMillis();
		
		ComputationDivider comp = new ComputationDivider(numberOfPackages, resourcePerPackage);
		try {
			List<PackageDTO> packages = comp.createPackages(clientData);		
			comp.assignPackage(packages.get(0), subSystem); // should be done by
			Printer.print(packages.get(0));
			
			List<ResourceDTO> resources = packages.get(0).getResources();
			for (int i = 0; i < resources.size(); i++){
				ResourcePOJO res = ResourceRepository.getResourceById(resources.get(i).getResourceId());
				assertEquals(subSystem, res.getAssignedSubsystem().intValue());
			}
			ResourceRepository.print();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
