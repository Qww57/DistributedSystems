package Logic.Application;

import java.io.Serializable;
import java.util.*;

public class TreatmentDTO implements Serializable {
	
	private static final long serialVersionUID = -8951045129972008754L;

	private int treatmentID;
	
	private byte[] treatment;
	
	public TreatmentDTO(int treatmentID, byte[] treatment) {
		super();
		this.treatmentID = treatmentID;
		this.treatment = treatment;
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
