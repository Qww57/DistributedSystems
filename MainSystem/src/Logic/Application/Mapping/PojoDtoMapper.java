package Logic.Application.Mapping;

import Logic.Application.DataTransfertObjects.ResourceDTO;
import Logic.Application.DataTransfertObjects.TreatmentDTO;
import Logic.Treatment.Objects.ResourcePOJO;
import Logic.Treatment.Objects.TreatmentPOJO;

/**
 * Class used to map POJO objects as {@link TreatmentPOJO} and {@link ResourcePOJO}
 * into {@link TreatmentDTO} and {@link ResourceDTO}, and the other way around.
 * 
 * @author Quentin
 *
 */
public class PojoDtoMapper {

	/**
	 * Convert a {@link ResourcePOJO} in a {@link ResourceDTO}.
	 * 
	 * @param input resourcePOJO to convert
	 * @return corresponding resourceDTO
	 */
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
	
	/**
	 * Convert a {@link ResourceDTO} in a {@link ResourcePOJO}.
	 * 
	 * @param input ResourceDTO to convert
	 * @return corresponding ResourcePOJO
	 */
	public static ResourcePOJO resourcePOJO(ResourceDTO input){
		ResourcePOJO output = new ResourcePOJO(
				input.getTreatmentId(), 
				input.getResourceId(),
				input.getLowerLimite(), 
				input.getUpperLimite(),  
				input.isComputationDone(), 
				input.getPrimeNbs(), 
				input.getAssignedSubSystem(),
				input.getPackageId());
		return output;
	}
	
	/**
	 * Convert a {@link TreatmentDTO} in a {@link TreatmentPOJO}.
	 * 
	 * @param input TreatmentDTO to convert
	 * @return corresponding TreatmentPOJO
	 */
	public static TreatmentPOJO treatmentPOJO(TreatmentDTO input){
		TreatmentPOJO output = new TreatmentPOJO(
				input.getTreatmentID(), 
				input.getTreatment(),
				input.getPackageIds());
		return output;
	}
	
	/**
	 * Convert a {@link TreatmentPOJO} in a {@link TreatmentDTO}.
	 * 
	 * @param input TreatmentPOJO to convert
	 * @return corresponding TreatmentDTO
	 */
	public static TreatmentDTO treatmentDTO(TreatmentPOJO input){
		TreatmentDTO output = new TreatmentDTO(
				input.getTreatmentID(),
				input.getTreatment(),
				input.getPackagesId());
		return output;
	}
}