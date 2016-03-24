package Logic.Application;

import java.io.Serializable;
import java.util.*;

/**
 * Pacakage to send to the subsystems
 * 
 * @author Quentin
 *
 */
public class PackageDTO extends AbstractDTO {

	private static final long serialVersionUID = -3431115936869450584L;
	
	private List<ResourceDTO> resources = new ArrayList<>();
	
	private TreatmentDTO treatment;

	public PackageDTO(List<ResourceDTO> resources, TreatmentDTO treatment) {
		super();
		this.resources = resources;
		this.treatment = treatment;
	}
	
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
}
