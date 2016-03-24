package Logic.Persistence;

import java.util.ArrayList;
import java.util.List;

import com.wagnerandade.coollection.query.order.Order;

import static com.wagnerandade.coollection.Coollection.*;

import Logic.Treatment.ResourcePOJO;


public class ResourceRepository {
	
	private static List<ResourcePOJO> resourceDb= new ArrayList<ResourcePOJO>();
	
	public static ResourcePOJO getResourceById(Integer id) throws Exception{
		List<ResourcePOJO> results = from(resourceDb)
				.where("resourceId", eq(id))
				.all();
		
		if (results.size() == 0){
			throw new Exception("No resources found for the id: " + id);
		}
		else if (results.size() > 1){
			throw new Exception("Many resources found for the id: " + id);
		}
		else {
			return results.get(0);
		}
	}
	
	public static List<ResourcePOJO> getAllResourcesByTreatment(Integer treatmentId) throws Exception{
		List<ResourcePOJO> results = from(resourceDb).where("treatmentId", eq(treatmentId))
				.orderBy("lowerLimite", Order.ASC).all();
		if (results.size() == 0){
			throw new Exception("No resources found for the treatment: " + treatmentId);
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
		
			if (results.size() == 0)
				throw new Exception("No resources have already been treated");
		}
		
		return results;
	}

	public void deleteResourcesByTreatment(Integer treatmentId) throws Exception {
		
		List<ResourcePOJO> results = from(resourceDb)
				.where("treatmentId", eq(treatmentId))
				.orderBy("lowerLimite", Order.ASC).all();
		
		for (int i = 0; i < results.size(); i++){
			results.get(i).setDeleted(true);
		}
	}
}
