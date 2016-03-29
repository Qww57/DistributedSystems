package Logic.Persistence;

import java.util.ArrayList;
import java.util.List;

import com.wagnerandade.coollection.query.order.Order;

import static com.wagnerandade.coollection.Coollection.*;

import Logic.Treatment.ResourcePOJO;

/**
 * 
 * @author Quentin
 *
 */

@SuppressWarnings("boxing")
public class ResourceRepository {
	
	private static List<ResourcePOJO> resourceDb= new ArrayList<ResourcePOJO>();
	
	public void add(ResourcePOJO resource){
		int id = resource.getResourceId();
		
		if (getResourceById(id) == null){
			System.out.println("Warning: resource " + id + " is already in the database");
		}
		
		resourceDb.get(id);
	}
	
	public static ResourcePOJO getResourceById(Integer id){
		List<ResourcePOJO> results = from(resourceDb)
				.where("resourceId", eq(id))
				.all();
		
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
	
	public static List<ResourcePOJO> getAllResourcesByTreatment(Integer treatmentId){
		List<ResourcePOJO> results = from(resourceDb).where("treatmentId", eq(treatmentId))
				.orderBy("lowerLimite", Order.ASC).all();
		if (results.size() == 0){
			System.out.println("Warning: no resources found for the treatment: " + treatmentId);
			return null;
		}
		return results;
	}
	
	public static List<ResourcePOJO> getAllResourcesNotTreated (Integer treatmentId) throws Exception{
		
		List<ResourcePOJO> results = new ArrayList<ResourcePOJO>();
		
		if (treatmentId != null){
			results = from(resourceDb).where("computationDone", eq(Boolean.FALSE))
					.and("treatmentId", eq(treatmentId))
					.orderBy("lowerLimite", Order.ASC).all();
			
			if (results.size() == 0)
				throw new Exception("All resources have been treated for: " + treatmentId);
			
		}else {
			results = from(resourceDb).where("computationDone", eq(Boolean.FALSE))
					.orderBy("lowerLimite", Order.ASC).all();
		
			if (results.size() == 0)
				throw new Exception("No resources have already been treated");
		}
		
		return results;
	}
	
	public List<ResourcePOJO> getAllResourcesTreated (Integer treatmentId) throws Exception{
			
		List<ResourcePOJO> results = new ArrayList<ResourcePOJO>();
		
		if (treatmentId != null){
			results = from(resourceDb).where("computationDone", eq(Boolean.TRUE))
					.and("treatmentId", eq(treatmentId))
					.orderBy("lowerLimite", Order.ASC).all();
			
			if (results.size() == 0)
				throw new Exception("No resources have already been treated for: " + treatmentId);
			
		}else {
			results = from(resourceDb).where("computationDone", eq(Boolean.TRUE))
					.orderBy("lowerLimite", Order.ASC).all();
		
			if (results.size() == 0){
				System.out.println("No resources have already been treated");
				return null;
			}
		}
		
		return results;
	}

	public void deleteResourcesByTreatment(Integer treatmentId){
		
		List<ResourcePOJO> results = from(resourceDb)
				.where("treatmentId", eq(treatmentId))
				.orderBy("lowerLimite", Order.ASC).all();
		
		if (results == null){
			System.out.println("Nothing to delete");
		}
		else {
			for (int i = 0; i < results.size(); i++){
				results.get(i).setDeleted(true);
			}
		}
	}
}
