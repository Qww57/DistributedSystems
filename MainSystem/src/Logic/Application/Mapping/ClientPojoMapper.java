package Logic.Application.Mapping;

import Logic.Application.utils.ConvertBytes;
import Logic.Exposition.ClientDataRequest;
import Logic.Treatment.Objects.ResourcePOJO;
import Logic.Treatment.Objects.TreatmentPOJO;
import utils.Couple;

/**
 * Class used to map the Data from client, {@link ClientDataRequest}, into POJO object, 
 * a {@link TreatmentPOJO} and a list of {@link ResourcePOJO}.
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
