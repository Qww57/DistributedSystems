package utils;

import java.io.Serializable;

/** Constructor Class for SObject with getters and setters
 *
 * @author Ehsan
 */

public class SObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
        
        private String ID;

	public String getName() {
		return name;
	}
        
        public String getID() {
		return ID;
	}
        
	public void setName(String name) {
		this.name = name;
	}

	public SObject(String name) {
		super();
		this.name = name;
	}	
        
        public SObject(String name, String ID) {
		super();
		this.name = name;
                this.ID = ID;
	}
}