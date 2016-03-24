package Logic.Persistence;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

import java.util.ArrayList;
import java.util.List;

import Logic.Treatment.TreatementPOJO;

public class TreatmentRepository {
private static List<TreatementPOJO> treatmentDb= new ArrayList<TreatementPOJO>();
	
	public static TreatementPOJO getTreatmentById(Integer id) throws Exception{
		List<TreatementPOJO> results = from(treatmentDb)
				.where("treatmentID", eq(id))
				.and("deleted", eq(Boolean.FALSE)).all();
		
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
	
	public static void deleteTreatmentById(Integer id) throws Exception{
		try {
			TreatementPOJO treatment = getTreatmentById(id);
			treatment.setDeleted(true);
		} catch (Exception e) {
			throw new Exception("Unable to deleted :" + e.getMessage());
		}
	}
}
