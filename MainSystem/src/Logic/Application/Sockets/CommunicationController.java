package Logic.Application.Sockets;

import java.util.LinkedList;
import java.util.List;

import Logic.Application.AbstractDTO;

/**
 * Controler responsible of instanciating the servers and managing 
 * the communication to the different subsystems
 * 
 * @author Quentin
 *
 */
public class CommunicationController {

	/* TODO Waiting queue for the messages, provided by the Computation 
	 * divider. Should also be used in the DTOSenderThread
	 */
	
	/* TODO Count the number of clients and deal with a ping pong to know
	 * if they are still all there
	 */
	
	/* TODO Send messages to clients
	 */
	
	private static List<AbstractDTO> sendingQueue = new LinkedList<AbstractDTO>();

	public static List<AbstractDTO> getSendingQueue() {
		return sendingQueue;
	}

	public void addToSendingQueue(AbstractDTO newDTO) {
		sendingQueue.add(newDTO);
	}
	
	public static AbstractDTO getNextDTO(){
		AbstractDTO toSend = sendingQueue.get(0);
		sendingQueue.remove(0);
		return toSend;
	}
		
}
