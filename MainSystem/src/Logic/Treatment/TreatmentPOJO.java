package Logic.Treatment;

import java.util.ArrayList;
import java.util.List;

import Logic.Application.utils.IdGenerator;

public class TreatmentPOJO {
	
	private String treatmentID;
	
	private Boolean deleted;
	
	private byte[] treatment;
	
	private List<String> packagesId;
	
	public TreatmentPOJO(byte[] treatment) {
		super();
		this.treatmentID = IdGenerator.treatmentPOJOId();
		this.treatment = treatment;
		this.deleted = Boolean.FALSE;
		this.packagesId = new ArrayList<String>();
	}
	
	public TreatmentPOJO(String treatmentID, byte[] treatment, List<String> packageIds) {
		super();
		this.treatmentID = treatmentID;
		this.treatment = treatment;
		this.deleted = Boolean.FALSE;
		this.packagesId = packageIds;
	}
	
	public Boolean isDeleted(){
		return deleted;
	}
	
	public void setDeleted(Boolean deleted){
		this.deleted = deleted;
	}
	
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

	public List<String> getPackagesId() {
		return packagesId;
	}

	public void setPackagesId(List<String> packagesId) {
		this.packagesId = packagesId;
	}	
	
	public void addPackageId(String packageId){
		this.packagesId.add(packageId);
	}
}
