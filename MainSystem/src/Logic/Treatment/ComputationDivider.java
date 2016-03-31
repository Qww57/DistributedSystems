package Logic.Treatment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Logic.Application.PackageDTO;
import Logic.Application.ResourceDTO;
import Logic.Application.TreatmentDTO;
import Logic.Application.Mapping.ClientPojoMapper;
import Logic.Application.Mapping.PojoDtoMapper;
import Logic.Exposition.ClientDataRequest;
import Logic.Persistence.ResourceRepository;
import Logic.Persistence.TreatmentRepository;
import utils.Couple;
import utils.IdGenerator;

/**
 * Class used to split computations and assign each one to one subsystem
 * 
 * @author Quentin
 *
 */
public class ComputationDivider {
	
	private int nbPackages;
	
	private int nbElementsPerPackage;
	
	public ComputationDivider(int packages, int elementsPerPackage){
		nbPackages = packages;
		nbElementsPerPackage = elementsPerPackage;
	}
	
	/**
	 * Function creating small packages and saving each resource in the list.
	 * Each package of the list is composed of the treatment and 10 small pieces
	 * 
	 * @param clientReq: resources sent by the client
	 * @return list of packages that are composed of the treatment and some shuffled small pieces of resources
	 * @throws Exception: if any problem
	 */
	public List<PackageDTO> createPackages(ClientDataRequest clientReq) throws Exception{
		
		List<PackageDTO> packages = new ArrayList<PackageDTO>();
		
		// Converting the clients data into POJO objects
		Couple couple = ClientPojoMapper.createPOJO(clientReq);
		TreatmentPOJO treatment = (TreatmentPOJO) couple.getObj1();
		ResourcePOJO resource = (ResourcePOJO) couple.getObj2();
		
		if ((TreatmentRepository.getTreatmentById(treatment.getTreatmentID()) == null)
			&& (ResourceRepository.getAllResourcesByTreatment(treatment.getTreatmentID()) == null)){
			
			// Splitting the POJO resources
			List<ResourcePOJO> resources = splitComputation(resource);			
			Collections.shuffle(resources);
			
			// Dispatching DTO resources into packages
			int index = 0;
			int nbElements = 0;
			List<String> packagesId = new ArrayList<String>();
			
			if (resources.size() % nbPackages == 0)
				nbElements = resources.size() / nbPackages;
			else
				nbElements = resources.size() / nbPackages + 1;
			
			for (int i = 0; i < nbPackages; i++){ 
				PackageDTO newPackage = new PackageDTO();
				packagesId.add(newPackage.getPackageId());
				
				List<ResourceDTO> newResources = new ArrayList<ResourceDTO>();
				
				int j =0;
				while(j < nbElements){
					if (index < resources.size()){
						// Setting the package ID and adding the resource to the repository
						ResourcePOJO resPOJO = resources.get(index);
						resPOJO.setPackageId(newPackage.getPackageId());
						ResourceRepository.createUpdate(resPOJO);
						
						// Converting the POJO to a DTO resource
						ResourceDTO resDTO = PojoDtoMapper.resourceDTO(resPOJO);
						newResources.add(resDTO);
					}
					j++;
					index++;
				} 
				
				// Adding elements to the package, validating it and adding it to the output
				newPackage.setResources(newResources);
				
				packages.add(newPackage);
			}	
			
			// Setting the packageIds to the POJO and saving it
			treatment.setPackagesId(packagesId);
			TreatmentRepository.addTreatment(treatment);
			
			// Creating the DTO and adding them to the packages
			for (int i = 0; i < packages.size(); i++){	
				TreatmentDTO treatmentDTO = PojoDtoMapper.treatmentDTO(treatment);
				packages.get(i).setTreatment(treatmentDTO);	
				
				if (!validate(packages.get(i))){
					throw new Exception("Cannot validate the package: " + packages.get(i).getPackageId());
				}
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
		ResourcePOJO res = null;
		
		for (int i = 0; i < resources.size(); i++){
			// System.out.println("Log: assigning resource " + resources.get(i).getResourceId());
			resources.get(i).setAssignedSubSystem(subSystem);
			res = PojoDtoMapper.resourcePOJO(resources.get(i));
			res.printResource();
			ResourceRepository.createUpdate(res); // FIXME
		}
	}

	/**
	 * TODO add functions to allow choosing two of them: nbResource, nbElements or nbPackages
	 * @param input
	 * @return
	 */
	public List<ResourcePOJO> splitComputation(ResourcePOJO input){
		List<ResourcePOJO> output = new ArrayList<ResourcePOJO>();
		
		int start = input.getLowerLimite().intValue();
		int end = input.getUpperLimite().intValue();
		int nbResources = nbPackages * nbElementsPerPackage;
		
		int nbElements = 0;
		if ((end - start) % nbResources == 0)
			nbElements = (end - start) / nbResources;
		else
			nbElements = (end - start) / nbResources + 1;
		
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
					null, null, null);
			}else {
				newResource = new ResourcePOJO(
					input.getTreatmentId(), 
					resourceId,
					new Integer(start), 
					new Integer(end),  
					input.isComputationDone(), 
					null, null, null);
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
