package utils;

import Logic.Application.PackageDTO;
import Logic.Application.ResourceDTO;
import Logic.Application.TreatmentDTO;

public class Printer {

	public static void print(Object object){
		
		if (object instanceof PackageDTO){
			System.out.println(" ");
			PackageDTO packagedto = (PackageDTO) object;
			
			System.out.println("Package: " + packagedto.getPackageId() 
				+ " - " + packagedto.getResources().size() + " elements");
			
			for (int i = 0; i < packagedto.getResources().size(); i++){
				print(packagedto.getResources().get(i));
			}
		}
		else if (object instanceof ResourceDTO){
			ResourceDTO resourceDTO = (ResourceDTO) object;
			
			System.out.println("Resource: " + resourceDTO.getResourceId()
				+ " for treatment " + resourceDTO.getTreatmentId()
				+ " from " + resourceDTO.getLowerLimite()
				+ " to " +resourceDTO.getUpperLimite()
				+ " assigned to " + resourceDTO.getAssignedSubSystem()
				+ " in package " + resourceDTO.getPackageId());	
		}
		else if (object instanceof TreatmentDTO){
			TreatmentDTO treatment = (TreatmentDTO) object;
			
			System.out.println("Resource: " + treatment.getTreatmentID()
				+ " split in packages: " + treatment.getPackageIds());
		}
		else{
			System.out.println("No printer defined for object of type: " + object.getClass());
		}
	}
}
