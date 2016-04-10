package Logic.Application.Sockets;

public class Addresses {
		
	public Addresses(String hostLocation, int DTO_Port, int PINGPONG_port) {
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
