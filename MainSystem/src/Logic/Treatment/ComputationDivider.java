package Logic.Treatment;

import java.util.ArrayList;
import java.util.List;

import Logic.Application.PackageDTO;
import Logic.Application.ResourceDTO;
import Logic.Application.TreatmentDTO;
import Logic.Application.Mapping.ClientPojoMapper;
import Logic.Application.Mapping.PojoDtoMapper;
import Logic.Application.Sockets.CommunicationController;
import Logic.Exposition.ClientDataRequest;
import Logic.Persistence.ResourceRepository;
import Logic.Persistence.TreatmentRepository;

/**
 * Class used to split computations and assign each one to one subsystem
 * 
 * @author Quentin
 *
 */
public class ComputationDivider {
	
	// Number of packages we want to have at the end
	private int nbPackages;
	
	private static ClientPojoMapper clientPojoMapper;
	private static PojoDtoMapper pojoDtoMapper;
	private static TreatmentRepository treatmentRepo;
	private static ResourceRepository resourceRepo;
	
	public ComputationDivider(int packages){
		nbPackages = packages;
		clientPojoMapper = new ClientPojoMapper();
		pojoDtoMapper = new PojoDtoMapper();
		treatmentRepo = new TreatmentRepository();
		resourceRepo = new ResourceRepository();
	}
	
	@SuppressWarnings({ "static-access", "boxing" })
	public PackageDTO divide(ClientDataRequest clientReq) throws Exception{
		
		PackageDTO packagedto = null;
		TreatmentPOJO treatment = clientPojoMapper.treatmentPOJO(clientReq);
		ResourcePOJO resource = clientPojoMapper.treatmentDTO(clientReq);
		
		int treatmentId = treatment.getTreatmentID();
		
		if ((treatmentRepo.getTreatmentById(treatmentId) == null)
			&& (resourceRepo.getAllResourcesByTreatment(treatmentId) == null)){
			
			treatmentRepo.addTreatment(treatment);
			TreatmentDTO treatmentDTO = pojoDtoMapper.treatmentDTO(treatment);
			
			List<ResourcePOJO> resources = splitComputation(resource);			
			List<ResourceDTO> resourcesDTO = new ArrayList<ResourceDTO>();
			
			for (int i = 0; i < resources.size(); i++){
				resourceRepo.add(resources.get(i));
				ResourceDTO res = pojoDtoMapper.resourceDTO(resources.get(i));
				resourcesDTO.add(res);				
			}
					
			packagedto = new PackageDTO(resourcesDTO, treatmentDTO);			
		}		
		
		if (!validate(packagedto)){
			throw new Exception("TODO");
		}
		
		CommunicationController.addToSendingQueue(packagedto);
		
		return packagedto;
	}
	
	public List<ResourcePOJO> splitComputation(ResourcePOJO input){
		List<ResourcePOJO> output = new ArrayList<ResourcePOJO>();
		
		int start = input.getLowerLimite();
		int end = input.getUpperLimite();
		int nbElements = (end - start) / nbPackages;
		int resourceId = 0;
		
		while(start < end){
			
			ResourcePOJO newResource = null;
			
			if ((start + nbElements) < end){
				newResource = new ResourcePOJO(
					input.getTreatmentId(), 
					resourceId,
					start, 
					start + nbElements -1,  
					input.isComputationDone(), 
					input.getPrimeNbs(), 
					-1);
			}
			else {
				newResource = new ResourcePOJO(
					input.getTreatmentId(), 
					resourceId,
					start, 
					end,  
					input.isComputationDone(), 
					input.getPrimeNbs(), 
					-1);
			}
			output.add(newResource);
			start = start + nbElements;
			resourceId++;
		}
			
		return output;
	}

	public boolean validate(PackageDTO packageDTO){
		// TODO: check if all numbers are different, has at least one object of each etc
		return true;
	}
}
