import Logic.Application.Sockets.Address;
import Logic.Application.Sockets.AddressBook;
import Logic.Application.Sockets.ServerTemplate;
import Logic.Application.Sockets.ServerTemplate.ThreadType;
import Presentation.MainController;
import utils.Printer;

public class freeWifi {

	@SuppressWarnings("unused")
	public final static void main(String[] strings) {
		
		System.out.println("Starting freeWifi main server");
		
		// Creating the two ping pong servers for our two subsystems 		
		Address clientAddress1 = AddressBook.getSubSystem(new Integer(0));
		ServerTemplate ppserver1 = new ServerTemplate(0, "localhost", 4000, ThreadType.PingPongThread);
		ppserver1.setSubSystemAddresses(clientAddress1);
		ppserver1.startServer(); // FIXME when starting one server, main thread remains stuck here
		
		Printer.log("Ping pong server for " + clientAddress1.toString() + " created.");
		
		Address clientAddress2 = AddressBook.getSubSystem(new Integer(1));
		ServerTemplate ppserver2 = new ServerTemplate(0, "localhost", 4001, ThreadType.PingPongThread);
		ppserver2.setSubSystemAddresses(clientAddress2);
		ppserver2.startServer();
	
		Printer.log("Ping pong server for " + clientAddress2.toString() + " created.");
		
		// Creating the server receiving requests from clients */		
		MainController mainController = new MainController();
		
		Printer.log("Poll server for client requests created.");
		
		//Creating the server sending DTO messages from the sending queue to subsystems
		ServerTemplate dtoserver = new ServerTemplate(0, "localhost", 5000, ThreadType.DTOObjectSender);
		dtoserver.startServer();
		
		Printer.log("Server for sending DTO objects created.");
		
		System.out.println("Closing freeWifi main server");
	}
}