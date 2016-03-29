package Logic.Application.Mapping;

import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.ResourcePOJO;
import Logic.Treatment.TreatmentPOJO;

/**
 * Class used to map the Data from client into a treatment object
 * 
 * @author Quentin
 *
 */
public class ClientPojoMapper {
	
	public static TreatmentPOJO treatmentPOJO(ClientDataRequest clientReq){
		//TODO
		TreatmentPOJO output = new TreatmentPOJO(0, null);
		return output;
	}
	
	public static ResourcePOJO treatmentDTO(ClientDataRequest clientReq){
		//TODO
		ResourcePOJO output = new ResourcePOJO(0, 0, 0, 0, false, null, 0);
		return output;
	}
}
