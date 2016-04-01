package Logic.Application.DataTransfertObjects;

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

	private String resourceId;
	
	private String treatmentId;
	
	private String packageId;
	
	private Integer upperLimite;
	
	private Integer lowerLimite;

	private Boolean computationDone;
	
	private List<Integer> primeNbs;
	
	private Integer assignedSubSystem;
		
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

	public Integer getAssignedSubSystem() {
		return assignedSubSystem;
	}

	public void setAssignedSubSystem(Integer assignedSubSystem) {
		this.assignedSubSystem = assignedSubSystem;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
}
