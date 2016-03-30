package Logic.Application;

import java.util.ArrayList;
import java.util.List;

import Logic.Application.utils.IdGenerator;
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
	
	public ResourceDTO(String treatmentId, Integer lowerLimite, Integer upperLimite) {
		super();
		this.treatmentId = treatmentId;
		this.resourceId = IdGenerator.resourceDTOId();
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = Boolean.FALSE;
		this.primeNbs = new ArrayList<Integer>();	
		this.setAssignedSubSystem(null);
	}
	
	public ResourceDTO(String treatmentId, String resourceId, 
			Integer lowerLimite, Integer upperLimite, 
			Boolean computationDone, List<Integer> primeNbs,
			String packageId, Integer assignedSubSystem) {
		super();
		this.resourceId = resourceId;
		this.treatmentId = treatmentId;
		this.setPackageId(packageId);
		this.upperLimite = upperLimite;
		this.lowerLimite = lowerLimite;
		this.computationDone = computationDone;
		this.primeNbs = primeNbs;
		this.setAssignedSubSystem(assignedSubSystem);
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
