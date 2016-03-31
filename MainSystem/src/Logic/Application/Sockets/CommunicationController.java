package Logic.Application.Sockets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import Logic.Application.AbstractDTO;

/**
 * Controler responsible of instanciating the servers and managing 
 * the communication to the different subsystems
 * 
 * @author Quentin
 *
 */
public class CommunicationController {
	
	/* True if reactive, false if not */
	private static HashMap<Integer, Boolean> subSystems = new HashMap<Integer, Boolean>();
	
	private static List<AbstractDTO> sendingQueue = new LinkedList<AbstractDTO>();

/* Managing the waiting queue */
	
	/* TODO Waiting queue for the messages, provided by the Computation 
	 * divider. Should also be used in the DTOSenderThread
	 */
	
	public static List<AbstractDTO> getSendingQueue() {
		return sendingQueue;
	}

	public static void addToSendingQueue(AbstractDTO newDTO) {
		sendingQueue.add(newDTO);
	}
	
	public static AbstractDTO getNextDTO(){
		AbstractDTO toSend = sendingQueue.get(0);
		sendingQueue.remove(0);
		return toSend;
	}

/* Managing the available subSystems */
	
	/* TODO Count the number of clients and deal with a ping pong to know
	 * if they are still all there
	 */
	
	public HashMap<Integer, Boolean> getSubSystems() {
		return subSystems;
	}

	/**
	 * For testing purposes, should not be like this
	 * 
	 * @param reactiveSubSystems
	 */
	public void setReactiveSubSystems(HashMap<Integer, Boolean> reactiveSubSystems) {
		subSystems = reactiveSubSystems;
	}

	public void updateStatus(Integer subSystem, Boolean status){
		subSystems.put(subSystem, status);
	}
	
	/**
	 * 
	 * @return list of reactive subSystems
	 */
	public static List<Integer> getReactiveSubSystems(){
		List<Integer> reactiveSubSystems = new ArrayList<Integer>();
		
		Iterator<Entry<Integer, Boolean>> it = subSystems.entrySet().iterator();
	    while (it.hasNext()) {
	        Entry<Integer, Boolean> pair = it.next();
	        if(pair.getValue().equals(Boolean.TRUE)){
	        	reactiveSubSystems.add(pair.getKey());
	        }
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    
		return reactiveSubSystems;
	}
	
/* Sending messages to clients */
	
	/* TODO Send messages to clients, intialize Servers etc
	 */		
}
