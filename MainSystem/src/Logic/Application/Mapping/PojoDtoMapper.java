package Logic.Application.Mapping;

import Logic.Application.ResourceDTO;
import Logic.Application.TreatmentDTO;
import Logic.Treatment.ResourcePOJO;
import Logic.Treatment.TreatmentPOJO;

public class PojoDtoMapper {

	public static ResourceDTO resourceDTO(ResourcePOJO input){
		ResourceDTO output = new ResourceDTO(
				input.getTreatmentId(), 
				input.getResourceId(),
				input.getLowerLimite(), 
				input.getUpperLimite(),  
				input.isComputationDone(), 
				input.getPrimeNbs(),
				input.getPackageId(),
				input.getAssignedSubsystem());
		return output;
	}
	
	public static ResourcePOJO resourcePOJO(ResourceDTO input){
		ResourcePOJO output = new ResourcePOJO(
				input.getTreatmentId(), 
				input.getResourceId(),
				input.getLowerLimite(), 
				input.getUpperLimite(),  
				input.isComputationDone(), 
				input.getPrimeNbs(), 
				new Integer(-1));
		return output;
	}
	
	public static TreatmentPOJO treatmentPOJO(TreatmentDTO input){
		TreatmentPOJO output = new TreatmentPOJO(
				input.getTreatmentID(), 
				input.getTreatment(),
				input.getPackageIds());
		return output;
	}
	
	public static TreatmentDTO treatmentDTO(TreatmentPOJO input){
		TreatmentDTO output = new TreatmentDTO(
				input.getTreatmentID(),
				input.getTreatment(),
				input.getPackagesId());
		return output;
	}
}