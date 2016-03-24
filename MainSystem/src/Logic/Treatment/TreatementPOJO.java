package Logic.Treatment;

public class TreatementPOJO {
	
	private int treatmentID;
	
	private boolean deleted;
	
	private byte[] treatment;
	
	public TreatementPOJO(int treatmentID, byte[] treatment) {
		super();
		this.treatmentID = treatmentID;
		this.treatment = treatment;
		this.deleted = false;
	}
	
	public boolean isDeleted(){
		return deleted;
	}
	
	public void setDeleted(boolean deleted){
		this.deleted = deleted;
	}
	
	public int getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}

	public byte[] getTreatment() {
		return treatment;
	}

	public void setTreatment(byte[] treatment) {
		this.treatment = treatment;
	}	
}
