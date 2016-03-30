package Logic.Application.Mapping;

import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.ResourcePOJO;
import Logic.Treatment.TreatmentPOJO;
import utils.Couple;

/**
 * Class used to map the Data from client into a treatment object
 * 
 * @author Quentin
 *
 */
public class ClientPojoMapper {
	
	public static Couple createPOJO(ClientDataRequest clientReq){
		TreatmentPOJO treatment = new TreatmentPOJO(clientReq.getTreatment());
		ResourcePOJO resource = new ResourcePOJO(treatment.getTreatmentID(), 
				clientReq.getMinResources(), 
				clientReq.getMaxResources());
		
		Couple output = new Couple(treatment, resource);
		
		return output;
	}
}
