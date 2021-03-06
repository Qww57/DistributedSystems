package Logic.Treatment.Objects;

import java.util.ArrayList;
import java.util.List;

import utils.IdGenerator;

/** 
 * POJO object representing a resource. This one is specifically designed for
 * prime number checking as it is the case for {@link ResourceDTO}
 * 
 * @author Quentin
 *
 */
public class ResourcePOJO {
	
	private String resourceId;
	
	private String treatmentId;
	
	private String packageId;
	
	private Integer upperLimite;
	
	private Integer lowerLimite;
	
	private Boolean computationDone;
	
	private Boolean deleted;
	
	private List<Integer> primeNbs;
	
	private Integer assignedSubsystem;
		
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
			List<Integer> primeNbs, Integer assignedSubsystem,
			String packageId) {
		super();
		this.resourceId = resourceId;
		this.treatmentId = treatmentId;
		this.packageId = packageId;
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = computationDone;
		this.assignedSubsystem = assignedSubsystem;
		this.primeNbs = primeNbs;
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
		System.out.println("Resource: " + this.getResourceId()
			+ " for treatment " + this.getTreatmentId()
			+ " from " + this.getLowerLimite()
			+ " to " +this.getUpperLimite()
			+ " assigned to " + this.getAssignedSubsystem()
			+ " in package " + this.getPackageId());
	}
}
