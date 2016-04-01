package Logic.Application.DataTransfertObjects;

import java.util.*;

/**
 * Package to send to the subsystems
 * 
 * @author Quentin
 *
 */
public class PackageDTO extends AbstractDTO {

	private static final long serialVersionUID = -3431115936869450584L;
	
	private List<ResourceDTO> resources = new ArrayList<>();
	
	private TreatmentDTO treatment;
	
	private String packageId;
	
	public TreatmentDTO getTreatment() {
		return treatment;
	}
	
	public void setTreatment(TreatmentDTO treatment) {
		this.treatment = treatment;
	}

	public List<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDTO> resources) {
		this.resources = resources;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
}
