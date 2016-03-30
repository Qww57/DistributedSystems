package Logic.Treatment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Logic.Application.PackageDTO;
import Logic.Application.ResourceDTO;
import Logic.Application.TreatmentDTO;
import Logic.Application.Mapping.ClientPojoMapper;
import Logic.Application.Mapping.PojoDtoMapper;
import Logic.Application.utils.IdGenerator;
import Logic.Exposition.ClientDataRequest;
import Logic.Persistence.ResourceRepository;
import Logic.Persistence.TreatmentRepository;
import utils.Couple;

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
	
	/**
	 * Function creating small packages and saving each resource in the list.
	 * Each package of the list is composed of the treatment and 10 small pieces
	 * 
	 * @param clientReq: resources sent by the client
	 * @return list of packages that are composed of the treatment and some shuffled small pieces of resources
	 * @throws Exception: if any problem
	 */
	@SuppressWarnings("static-access")
	public List<PackageDTO> createPackages(ClientDataRequest clientReq) throws Exception{
		
		List<PackageDTO> packages = new ArrayList<PackageDTO>();
		
		Couple couple = clientPojoMapper.createPOJO(clientReq);
		TreatmentPOJO treatment = (TreatmentPOJO) couple.getObj1();
		ResourcePOJO resource = (ResourcePOJO) couple.getObj2();
		
		String treatmentId = treatment.getTreatmentID();
		
		if ((treatmentRepo.getTreatmentById(treatmentId) == null)
			&& (resourceRepo.getAllResourcesByTreatment(treatmentId) == null)){
			
			// Converting and saving the POJO objects in the database
			treatmentRepo.addTreatment(treatment);
			TreatmentDTO treatmentDTO = pojoDtoMapper.treatmentDTO(treatment);
			
			List<ResourcePOJO> resources = splitComputation(resource);			
			List<ResourceDTO> resourcesDTO = new ArrayList<ResourceDTO>();
			
			for (int i = 0; i < resources.size(); i++){
				resourceRepo.add(resources.get(i));
				ResourceDTO res = pojoDtoMapper.resourceDTO(resources.get(i));
				resourcesDTO.add(res);				
			} 
			
			// Creating DTO packages 
			Collections.shuffle(resourcesDTO);
			int index = 0;
			
			// FIXME
			for (int i = 0; i < nbPackages; i++){ 
				List<ResourceDTO> newResource = new ArrayList<ResourceDTO>();
				int j =0;
				
				while(j < nbElements){
					System.out.println("j: " + j);
					System.out.println("index: " + index);
					newResource.add(resourcesDTO.get(index));
					j++;
					index++;
				} 
				
				PackageDTO newPackage = new PackageDTO(newResource, treatmentDTO);
				
				if (!validate(newPackage)){
					throw new Exception("TODO"); //TODO
				}
				packages.add(newPackage);
			}		
		}		
				
		return packages;
	}
	
	/**
	 * Assigning on package to one available subsystem. 
	 * Used by the communication controller
	 * 
	 * @param packages
	 */
	public void assignPackage(PackageDTO packageDTO, Integer subSystem){
		// Get number of reactive packages
		List<ResourceDTO> resources = packageDTO.getResources();
		
	}
	
	// TODO add package ID to resources and treatment 
	
	private int nbElements;
	
	public List<ResourcePOJO> splitComputation(ResourcePOJO input){
		List<ResourcePOJO> output = new ArrayList<ResourcePOJO>();
		
		int start = input.getLowerLimite().intValue();
		int end = input.getUpperLimite().intValue();
		int nbResources = nbPackages * 10;
		
		nbElements = 0;
		if ((end - start) % nbResources == 0)
			nbElements = (end - start) / nbResources;
		else
			nbElements = (end - start) / nbResources + 1;
			
	System.out.println("NB ELEMENTS: " + nbElements);
		
		while(start < end){
			ResourcePOJO newResource = null;
			String resourceId = IdGenerator.resourcePOJOId(); 
			
			if ((start + nbElements) < end){
				newResource = new ResourcePOJO(
					input.getTreatmentId(), 
					resourceId,
					new Integer(start), 
					new Integer(start + nbElements -1),  
					input.isComputationDone(), 
					input.getPrimeNbs(), 
					new Integer(-1));
			}
			else {
				newResource = new ResourcePOJO(
					input.getTreatmentId(), 
					resourceId,
					new Integer(start), 
					new Integer(end),  
					input.isComputationDone(), 
					input.getPrimeNbs(), 
					new Integer(-1));
			}
			output.add(newResource);
			start = start + nbElements;
		}
			
		return output;
	}

	public boolean validate(PackageDTO packageDTO){
		// TODO: check if all numbers are different, has at least one object of each etc
		return true;
	}
}
