package Logic.Persistence;

import java.util.ArrayList;
import java.util.List;

import com.wagnerandade.coollection.query.order.Order;

import static com.wagnerandade.coollection.Coollection.*;

import Logic.Treatment.ResourcePOJO;
import utils.Printer;

/**
 * 
 * @author Quentin
 *
 */

public class ResourceRepository {
	
	private static List<ResourcePOJO> resourceDb = new ArrayList<ResourcePOJO>();
	
	public static void createUpdate(ResourcePOJO resource){
		ResourcePOJO resDb = getResourceById(resource.getResourceId());
		if (resDb != null){
			resourceDb.remove(resDb);
		}
		resourceDb.add(resource);
	}
	
	public static ResourcePOJO getResourceById(String id){
		List<ResourcePOJO> results = from(resourceDb)
				.where("getResourceId", eq(id)).all();
		
		if (results.size() == 0){
			System.out.println("Warning: no resource found for the id: " + id);
			return null;
		}
		else if (results.size() > 1){
			System.out.println("Warning: many resources found for the id: " + id);
			return results.get(0);
		}
		else {
			return results.get(0);
		}
	}
	
	public static List<ResourcePOJO> getAllResourcesByTreatment(String treatmentId){
		List<ResourcePOJO> results = from(resourceDb).where("getTreatmentId", eq(treatmentId))
				.orderBy("getLowerLimite", Order.ASC).all();
		if (results.size() == 0){
			System.out.println("Warning: no resources found for the treatment: " + treatmentId);
			return null;
		}
		return results;
	}
	
	public static void print(){
		System.out.println("");
		System.out.println("ResourceRepository: ");
		for (int i = 0; i < resourceDb.size(); i++){
			Printer.print(resourceDb.get(i));
		}
	}
}
