package Logic.Application.Sockets;

/**
 * Class used in order to store the sub systems informations. For each subsystem,
 * it is composed of:
 * - its host location, 
 * - its port used to send DTO 
 * - its PingPong port to check his availability
 *
 *	@author Quentin
 */
public class Address {
		
	public Address(String hostLocation, int DTO_Port, int PINGPONG_port) {
		super();
		this.DTO_Port = DTO_Port;
		this.PINGPONG_port = PINGPONG_port;
		this.hostLocation = hostLocation;
	}

	private int DTO_Port;
	private int PINGPONG_port ;
	private String hostLocation;

	public int getDTO_port() {
		return DTO_Port;
	}

	public int getPINGPONG_port() {
		return PINGPONG_port;
	}
	
	public String getHost(){
		return hostLocation;
	}
	
}
