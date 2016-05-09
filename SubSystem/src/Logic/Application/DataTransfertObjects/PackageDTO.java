package Logic.Application.DataTransfertObjects;

import java.util.*;

/**
 * Package to send to the subsystems containing the computations. 
 * It can also contain a treatment method in case of a dynamic transmission of 
 * the treatment functions. However due to technological limitations on grid 
 * computing we have not been able to find a way to unwrap these computation 
 * properly on the server side without getting java unknown class exceptions.
 * The private field treatment is so actually not used, but remains here in case 
 * a solution could be found.
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
