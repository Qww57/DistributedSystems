package Logic.Application.Mapping;

import Logic.Application.ResourceDTO;
import Logic.Treatment.ResourcePOJO;

public class ResourcePOJOtoDTOMapping {

	public static ResourceDTO map(ResourcePOJO input){
		ResourceDTO output = new ResourceDTO(
				input.getTreatmentId(), 
				input.getResourceId(),
				input.getLowerLimite(), 
				input.getUpperLimite(),  
				input.isComputationDone(), 
				input.getPrimeNbs());
		return output;
	}
}