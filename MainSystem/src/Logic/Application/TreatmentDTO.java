package Logic.Application;

import java.io.Serializable;
import java.util.*;

public class TreatmentDTO implements Serializable {
	
	private static final long serialVersionUID = -8951045129972008754L;

	private int treatmentID;
	
	private byte[] treatment;
	
	public TreatmentDTO(int treatmentID) {
		super();
		this.treatmentID = treatmentID;
	}
	
	public int getTreatmentID() {
		return treatmentID;
	}

	public void setTreatmentID(int treatmentID) {
		this.treatmentID = treatmentID;
	}
}
