package Logic.Application.Mapping;

import Logic.Application.ResourceDTO;
import Logic.Treatment.ResourcePOJO;

public class ResourceDTOtoPOJOMapping {

	public static ResourcePOJO map(ResourceDTO input){
		ResourcePOJO output = new ResourcePOJO(
				input.getTreatmentId(), 
				input.getResourceId(),
				input.getLowerLimite(), 
				input.getUpperLimite(),  
				input.isComputationDone(), 
				input.getPrimeNbs(),
				-1);
		return output;
	}
	
}
