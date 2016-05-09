package Logic.Application.DataTransfertObjects;

import java.util.List;

/**
 * Class that is supposed to contain a treatment method store as bytes. 
 * This class has to be used with {@link ConvertBytes}. However, due to unwrapping
 * problems when the class unwrapping it is not in the same project as the one where 
 * the method is declared, we have not been able to use it. We were getting java class
 * unknown exceptions. This class remains here in case a solution can be found.
 * 
 * @author Quentin
 *
 */
public class TreatmentDTO extends AbstractDTO {
	
	private static final long serialVersionUID = -8951045129972008754L;

	private String treatmentID;
	
	private byte[] treatment;
	
	private List<String> packageIds;
	
	public String getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(String treatmentID) {
		this.treatmentID = treatmentID;
	}

	public byte[] getTreatment() {
		return treatment;
	}

	public void setTreatment(byte[] treatment) {
		this.treatment = treatment;
	}

	public List<String> getPackageIds() {
		return packageIds;
	}

	public void setPackageIds(List<String> packageIds) {
		this.packageIds = packageIds;
	}
}
