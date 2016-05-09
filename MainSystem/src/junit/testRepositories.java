package junit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Logic.Persistence.ResourceRepository;
import Logic.Treatment.Objects.ResourcePOJO;

/**
 * Unit tests for the resource repository, by testing the creation and update of resources.
 * 
 * @author Quentin
 *
 */

public class testRepositories {

	@Test
	public void testResourceUpdate(){
		// Testing the create method
		ResourcePOJO resource = new ResourcePOJO("TreatmentId", new Integer(50), new Integer(500));
		ResourceRepository.createUpdate(resource);
		ResourcePOJO getRes = ResourceRepository.getResourceById(resource.getResourceId());
		assertEquals(resource, getRes);
		ResourceRepository.print();
		
		// Testing the update method
		resource.setLowerLimite(new Integer(0));
		ResourceRepository.createUpdate(resource);
		getRes = ResourceRepository.getResourceById(resource.getResourceId());
		assertEquals(0, getRes.getLowerLimite().intValue());
		ResourceRepository.print();
	}
}
