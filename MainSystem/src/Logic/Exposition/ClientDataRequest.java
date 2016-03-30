package Logic.Exposition;
/**
 * Class containing the information sent by the client:
 * - resources
 * - treatment
 * - results?
 * 
 * This class has to be mapped in order to be handled
 * 
 * @author Quentin
 *
 */
public class ClientDataRequest {

	private byte[] treatment;
	
	private Integer minRresources;
	
	private Integer maxResources;
	
	public ClientDataRequest(byte[] treatment, Integer minRresources, Integer maxResources) {
		super();
		this.treatment = treatment;
		this.minRresources = minRresources;
		this.maxResources = maxResources;
	}

	public byte[] getTreatment() {
		return treatment;
	}

	public Integer getMinResources() {
		return minRresources;
	}
	
	public Integer getMaxResources() {
		return maxResources;
	}
}
