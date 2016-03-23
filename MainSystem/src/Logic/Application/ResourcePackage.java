package Logic.Application;

import java.io.Serializable;
import java.util.*;

public class ResourcePackage implements Serializable {

	private static final long serialVersionUID = -3431115936869450584L;
	
	private List<ResourceDTO> resources = new ArrayList<>();

	public List<ResourceDTO> getResources() {
		return resources;
	}

	public void setResources(List<ResourceDTO> resources) {
		this.resources = resources;
	}
}
