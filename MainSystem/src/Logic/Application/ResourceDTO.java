package Logic.Application;

import java.io.Serializable;
import java.util.List;
/**
 * DTO object to be send to the subsystems using TCP
 * 
 * Only to use for test purposes while checking for prime numbers
 * 
 * @author Quentin
 *
 */
public class ResourceDTO extends AbstractDTO {

	private static final long serialVersionUID = 487595575463452951L;

	private int resourceId;
	
	private int treatmentId;
	
	private int upperLimite;
	
	private int lowerLimite;

	private boolean computationDone;
	
	private List<Integer> primeNbs;
	
	public ResourceDTO(int treatmentId, int resourceId, int lowerLimite, int upperLimite, boolean computationDone,
			List<Integer> primeNbs) {
		super();
		this.resourceId = resourceId;
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = computationDone;
		this.primeNbs = primeNbs;
		this.treatmentId = treatmentId;
	}
	
	public int getResourceId(){
		return resourceId;
	}
	
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getUpperLimite(){
		return upperLimite;
	}
	
	public void setUpperLimite(int upperLimite) {
		this.upperLimite = upperLimite;
	}

	public int getLowerLimite() {
		return lowerLimite;
	}

	public void setLowerLimite(int lowerLimite) {
		this.lowerLimite = lowerLimite;
	}

	public boolean isComputationDone() {
		return computationDone;
	}

	public void setComputationDone(boolean computationDone) {
		this.computationDone = computationDone;
	}

	public List<Integer> getPrimeNbs() {
		return primeNbs;
	}

	public void setPrimeNbs(List<Integer> primeNbs) {
		this.primeNbs = primeNbs;
	}

	public int getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(int treatmentId) {
		this.treatmentId = treatmentId;
	}
}
