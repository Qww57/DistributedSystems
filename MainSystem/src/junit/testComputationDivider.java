package junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Logic.Application.PackageDTO;
import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.ComputationDivider;
import Logic.Treatment.ResourcePOJO;

public class testComputationDivider {

	//@Test
	public void testComputation(){
		ResourcePOJO pojo = new ResourcePOJO( 
				"Treatment",
				new Integer(15), 
				new Integer(1500));
		
		int numberOfElements = 12;
		ComputationDivider comp = new ComputationDivider(numberOfElements);
		
		List<ResourcePOJO> resources = comp.splitComputation(pojo);
		
		System.out.println("SIZE:" + resources.size());
		
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
		
		System.out.println("New test: ");
		
		numberOfElements = 10;
		comp = new ComputationDivider(numberOfElements);
		
		resources = comp.splitComputation(pojo);
		
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
		
		System.out.println("SIZE:" + resources.size());
	}
	
	@Test
	public void testDivide(){
		ClientDataRequest clientData = new ClientDataRequest(null, new Integer(0), new Integer(10));
		
		int numberOfPackages = 2;
		ComputationDivider comp = new ComputationDivider(numberOfPackages);
		try {
			List<PackageDTO> packages = comp.createPackages(clientData);
			assertEquals(numberOfPackages, packages.size());	
			for(int i = 0; i< packages.size(); i++){
				printPackage(packages.get(i));
			}
			assertTrue(true);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			fail("Should not raised exception");
		}
	}
	
	private void printPackage(PackageDTO packagedto){
		System.out.println("Package: " + packagedto.getPackageId() + " - " + packagedto.getResources().size());
		for (int i = 0; i < packagedto.getResources().size(); i++){
			System.out.println("Id: " + packagedto.getResources().get(i).getResourceId()
				+ " - Min: " + packagedto.getResources().get(i).getLowerLimite()
				+ " - Max: " + packagedto.getResources().get(i).getUpperLimite());
		}
		System.out.println("-----------");
	}
}
