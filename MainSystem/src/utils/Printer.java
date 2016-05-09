package utils;

import Logic.Application.DataTransfertObjects.PackageDTO;
import Logic.Application.DataTransfertObjects.ResourceDTO;
import Logic.Application.DataTransfertObjects.TreatmentDTO;
import Logic.Treatment.Objects.ResourcePOJO;
import Logic.Treatment.Objects.TreatmentPOJO;

/**
 * Class used in order to deal with different levels of console printing.
 * 
 * @author Quentin
 *
 */
public class Printer {
	
	private static boolean log = true;
	private static boolean debug = false;
	
	public static void log(String text){
		if (log)
			System.out.println(text);
	}
	
	public static void debug(String text){
		if (debug)
			System.out.println(text);
	}

	public void setDebug(boolean debug) {
		Printer.debug = log;
	}

	public void setLog(boolean log) {
		Printer.log = log;
	}

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
		
		else if (object instanceof ResourcePOJO){
			ResourcePOJO resourcePOJO = (ResourcePOJO) object;
			resourcePOJO.printResource();
		}
		
		else if (object instanceof TreatmentPOJO){
			TreatmentPOJO treatmentPOJO = (TreatmentPOJO) object;
			treatmentPOJO.printTreatment();
		}
		else{
			System.out.println("No printer defined for object of type: " + object.getClass());
		}
	}
}
