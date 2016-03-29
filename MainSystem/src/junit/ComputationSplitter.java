package junit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import Logic.Treatment.ComputationDivider;
import Logic.Treatment.ResourcePOJO;

public class ComputationSplitter {

	@Test
	public void testComputation(){
		ResourcePOJO pojo = new ResourcePOJO(
				0, 
				0,
				0, 
				1000,  
				false, 
				null, 
				-1);
		
		int numberOfElements = 12;
		ComputationDivider comp = new ComputationDivider(numberOfElements);
		
		List<ResourcePOJO> resources = comp.splitComputation(pojo);
		
		assertTrue(numberOfElements <= resources.size());
		
		for (int i = 0; i < resources.size(); i++){
			resources.get(i).printResource();
		}
	}
}
