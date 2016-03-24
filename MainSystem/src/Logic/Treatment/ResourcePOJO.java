package Logic.Treatment;

import java.util.List;

public class ResourcePOJO {
	
	private int resourceId;
	
	private int upperLimite;
	
	private int lowerLimite;
	
	private boolean computationDone;
	
	private boolean deleted;
	
	private List<Integer> primeNbs;
	
	private int treatmentId;
	
	private int assignedSubsystem;
		
	/* Assign value -1 to assigned subsystems when creating the object */
	
	public ResourcePOJO(int treatmentId, int resourceId, 
			int lowerLimite, int upperLimite, 
			boolean computationDone,
			List<Integer> primeNbs, int assignedSubsystem) {
		super();
		this.resourceId = resourceId;
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = computationDone;
		this.primeNbs = primeNbs;
		this.treatmentId = treatmentId;
		this.assignedSubsystem = assignedSubsystem;
		this.deleted = false;
	}

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getAssignedSubsystem() {
		return assignedSubsystem;
	}

	public void setAssignedSubsystem(int assignedSubsystem) {
		this.assignedSubsystem = assignedSubsystem;
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
	
	/* TODO add and create customs exceptions to throw */
	public boolean validateResource(){
		if (this.lowerLimite < this.upperLimite){
			return true;
		}
		return false;
	}
	
	public boolean validateBeforeSending(){
		if (!this.validateResource() || this.assignedSubsystem == -1){
			return false;
		}
		return true;
	}
	
}
