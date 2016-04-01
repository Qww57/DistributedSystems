package Logic.Persistence;

import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;

import java.util.ArrayList;
import java.util.List;

import Logic.Treatment.Objects.TreatmentPOJO;
import utils.Printer;

public class TreatmentRepository {

	private static List<TreatmentPOJO> treatmentDb= new ArrayList<TreatmentPOJO>();
	
	public static void addTreatment(TreatmentPOJO treatment){
		String id = treatment.getTreatmentID();
		
		if (getTreatmentById(id) != null){
			System.out.println("Warning: treatment " + id + " is already in the database");
		}
		treatmentDb.add(treatment);
	}

	public static TreatmentPOJO getTreatmentById(String id){
		List<TreatmentPOJO> results = from(treatmentDb)
				.where("getTreatmentID", eq(id))
				.and("isDeleted", eq(Boolean.FALSE)).all();
		
		if (results.size() == 0){
			System.out.println("Warning: treatment " + id + " is not in the database");
			return null;
		}
		else if (results.size() > 1){
			System.out.println("Warning: many treatments with " + id + " are found in the database");
			return results.get(0);
		}
		else {
			return results.get(0);
		}
	}
		
	public static void print(){
		System.out.println("");
		System.out.println("TreatmentRepository: ");
		for (int i = 0; i < treatmentDb.size(); i++){
			Printer.print(treatmentDb.get(i));
		}
	}
}
