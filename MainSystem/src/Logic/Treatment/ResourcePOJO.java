package Logic.Treatment;

import java.util.ArrayList;
import java.util.List;

import utils.IdGenerator;

public class ResourcePOJO {
	
	private String resourceId;
	
	private Integer upperLimite;
	
	private Integer lowerLimite;
	
	private Boolean computationDone;
	
	private Boolean deleted;
	
	private List<Integer> primeNbs;
	
	private String treatmentId;
	
	private Integer assignedSubsystem;
	
	private String packageId;
		
	/* Assign value -1 to assigned subsystems when creating the object */
	
	public ResourcePOJO(String treatmentId, 
			Integer lowerLimite, Integer upperLimite) {
		super();
		this.resourceId = IdGenerator.resourcePOJOId();
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = Boolean.FALSE;
		this.primeNbs = new ArrayList<Integer>();
		this.treatmentId = treatmentId;
		this.assignedSubsystem = null;
		this.deleted = Boolean.FALSE;
	}
	
	public ResourcePOJO(String treatmentId, String resourceId, 
			Integer lowerLimite, Integer upperLimite, 
			Boolean computationDone,
			List<Integer> primeNbs, Integer assignedSubsystem) {
		super();
		this.resourceId = resourceId;
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = computationDone;
		this.primeNbs = primeNbs;
		this.treatmentId = treatmentId;
		this.assignedSubsystem = assignedSubsystem;
		this.deleted = Boolean.FALSE;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getAssignedSubsystem() {
		return assignedSubsystem;
	}

	public void setAssignedSubsystem(Integer assignedSubsystem) {
		this.assignedSubsystem = assignedSubsystem;
	}

	public String getResourceId(){
		return resourceId;
	}
	
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Integer getUpperLimite(){
		return upperLimite;
	}
	
	public void setUpperLimite(Integer upperLimite) {
		this.upperLimite = upperLimite;
	}

	public Integer getLowerLimite() {
		return lowerLimite;
	}

	public void setLowerLimite(Integer lowerLimite) {
		this.lowerLimite = lowerLimite;
	}

	public Boolean isComputationDone() {
		return computationDone;
	}

	public void setComputationDone(Boolean computationDone) {
		this.computationDone = computationDone;
	}

	public List<Integer> getPrimeNbs() {
		return primeNbs;
	}

	public void setPrimeNbs(List<Integer> primeNbs) {
		this.primeNbs = primeNbs;
	}

	public String getTreatmentId() {
		return treatmentId;
	}

	public void setTreatmentId(String treatmentId) {
		this.treatmentId = treatmentId;
	}
	
	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	
	/* TODO add and create customs exceptions to throw */
	public Boolean validateResource(){
		if (this.lowerLimite.intValue() < this.upperLimite.intValue()){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public Boolean validateBeforeSending(){
		if (!this.validateResource().equals(Boolean.TRUE) || this.assignedSubsystem.intValue() == -1){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	public void printResource(){
		System.out.println("Resource " + this.resourceId 
				+ " - Treatment " + this.treatmentId 
				+ " from " + this.lowerLimite + " to " + this.upperLimite);
	}
}
