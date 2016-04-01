package Logic.Application.DataTransfertObjects;

import java.util.List;

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
