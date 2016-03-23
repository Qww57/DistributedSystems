package SendBytes;

import java.io.Serializable;

public class SObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SObject(String name) {
		super();
		this.name = name;
	}		
}