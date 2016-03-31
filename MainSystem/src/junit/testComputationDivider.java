package junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Logic.Application.PackageDTO;
import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.ComputationDivider;
import Logic.Treatment.ResourcePOJO;
import utils.Printer;

public class testComputationDivider {

	//@Test
	public void testComputation(){
		ResourcePOJO pojo = new ResourcePOJO( 
				"Treatment",
				new Integer(15), 
				new Integer(1500));
		
		int numberOfElements = 12;
		int resourcePerPackage = 15;
		ComputationDivider comp = new ComputationDivider(numberOfElements, resourcePerPackage);
		
		List<ResourcePOJO> resources = comp.splitComputation(pojo);
		
		System.out.println("SIZE:" + resources.size());
		
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
		
		System.out.println("New test: ");
		
		numberOfElements = 10;
		resourcePerPackage = 10;
		comp = new ComputationDivider(numberOfElements, resourcePerPackage);
		
		resources = comp.splitComputation(pojo);
		
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
		
		System.out.println("SIZE:" + resources.size());
	}
	
	@Test
	public void testDivide(){
		ClientDataRequest clientData = new ClientDataRequest(null, new Integer(0), new Integer(500000000));
		
		int numberOfPackages = 5000;
		int resourcePerPackage = 15;
		
		long startTime = System.currentTimeMillis();
		
		ComputationDivider comp = new ComputationDivider(numberOfPackages, resourcePerPackage);
		try {
			List<PackageDTO> packages = comp.createPackages(clientData);
			assertEquals(numberOfPackages, packages.size());	
			for(int i = 0; i < packages.size() - 1; i++){
				assertEquals(resourcePerPackage, packages.get(i).getResources().size());
				// Printer.print(packages.get(i));
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Should not raised exception");
		}
		
		long endTime = System.currentTimeMillis();
		long duration = (endTime - startTime);
		System.out.println(duration + " ns ");
	}
}
