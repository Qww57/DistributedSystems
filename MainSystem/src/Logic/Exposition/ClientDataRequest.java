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
	
	private byte[] resources;

	public byte[] getTreatment() {
		return treatment;
	}

	public void setTreatment(byte[] treatment) {
		this.treatment = treatment;
	}

	public byte[] getResources() {
		return resources;
	}

	public void setResources(byte[] resources) {
		this.resources = resources;
	}
}
