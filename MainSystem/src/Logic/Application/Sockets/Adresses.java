package Logic.Application.Sockets;

import utils.Couple;

public class Adresses {
	
	private Couple subSystem_0 = new Couple("localhost", new Integer(7000));
	
	private Couple subSystem_1 = new Couple("localhost", new Integer(7001));

	public Couple getSubSystem_0() {
		return subSystem_0;
	}

	public Couple getSubSystem_1() {
		return subSystem_1;
	}
}
