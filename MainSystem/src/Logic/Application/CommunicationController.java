package Logic.Application;

import java.util.*;
import java.util.Map.Entry;
import Logic.Application.DataTransfertObjects.AbstractDTO;
import utils.Printer;

/**
 * Controller responsible of instantiating the servers, using {@link ServerTemplate} and managing 
 * the communication to the different subsystems. 
 * 
 * @author Quentin
 *
 */
public class CommunicationController {
	
	/* True if reactive, false if not */
	private static HashMap<Integer, Boolean> subSystems ;	
	static{
		subSystems = new HashMap<Integer, Boolean>();
		subSystems.put(new Integer(0), Boolean.FALSE);
		subSystems.put(new Integer(1), Boolean.FALSE);
    }
	
	private static List<AbstractDTO> sendingQueue = new LinkedList<AbstractDTO>();

/* Managing the waiting queue */
	
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
	
	public HashMap<Integer, Boolean> getSubSystems() {
		return subSystems;
	}

	/**
	 * For testing purposes, reactive subSytems should not be set directly but should be
	 * created from updates from the Ping-Pong kind messages
	 * 
	 * @param reactiveSubSystems
	 */
	public static void setReactiveSubSystems(HashMap<Integer, Boolean> reactiveSubSystems) {
		subSystems = reactiveSubSystems;
	}

	public static void updateStatus(Integer subSystemID, Boolean status){
		if (!status.equals(subSystems.get(subSystemID)))
				Printer.log("Update status of subsystem " + subSystemID.toString() + " to " + status.toString());
		
		subSystems.put(subSystemID, status);
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
}

