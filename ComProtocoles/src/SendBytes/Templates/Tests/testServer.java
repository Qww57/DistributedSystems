package SendBytes.Templates.Tests;

import SendBytes.Templates.Server;
import SendBytes.Templates.Server.ThreadType;

public class testServer {

	public static void main(String[] args){
		Server server = new Server(0, "localhost", 7000, ThreadType.SOBJECT);
		server.startServer();
	}
}
