package Logic.Application.Mapping;

import Logic.Application.utils.ConvertBytes;
import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.Objects.ResourcePOJO;
import Logic.Treatment.Objects.TreatmentPOJO;
import utils.Couple;

/**
 * Class used to map the Data from client into a treatment object
 * 
 * @author Quentin
 *
 */
public class ClientPojoMapper {
	
	public static Couple createPOJO(ClientDataRequest clientReq){
		TreatmentPOJO treatment = new TreatmentPOJO(ConvertBytes.ConvertToBytes(clientReq.getTreatment()));
		ResourcePOJO resource = new ResourcePOJO(treatment.getTreatmentID(), 
				clientReq.getMinResources(), 
				clientReq.getMaxResources());
		
		Couple output = new Couple(treatment, resource);
		
		return output;
	}
}
