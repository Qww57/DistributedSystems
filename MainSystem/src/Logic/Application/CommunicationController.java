package Logic.Application;

import java.util.*;
import java.util.Map.Entry;
import Logic.Application.DataTransfertObjects.AbstractDTO;
import java.io.*;
import java.net.*;

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
	 * For testing purposes, reactive subSytems should not be set directly but should be
	 * created from updates from the Ping-Pong kind messages
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
	
    public static void main(String[] args) {
        
        
        try{
                System.out.println("Starting Server...");
                int serverPort = 1234;
                ServerSocket listenSocket = new ServerSocket(serverPort);
                
                while(true){
                      Socket clientSocket = listenSocket.accept();
                      PingPongThread c = new PingPongThread(clientSocket);
                }
                
        }catch(IOException e){System.out.println("Listen:"+ e.getMessage());}
    }		
}

    class PingPongThread extends Thread{
            DataInputStream in;
            DataOutputStream out;
            Socket clientSocket;


            public PingPongThread(Socket aClientSocket) throws IOException{
                clientSocket=aClientSocket;
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                this.start(); 
            }
        
            public void run(){

                try {
                        //HashMap<String, Boolean> SubSystems = new HashMap<>();
                        //Objects ping = new Objects("PING", "1");
                        Thread.sleep(5000);
                        out.writeUTF("PING");
                        System.out.println("We have sent out a PING message to client");

                        in.readUTF();
                        //TCP.Objects o = (TCP.Objects) in.readObject();
                        System.out.println ("We recieved a response from cilent");

                        System.out.println(Thread.currentThread().getState());
                        /*
                        if(o.getName().equals("PONG"))
                            SubSystems.put(o.getID(), true);
                        else
                            SubSystems.put(o.getID(), false);
                        */
                        //System.out.println(SubSystems.get("1"));
                        //System.out.println (o.getClass());
                } catch (IOException ex) {
                    System.out.println("IOException: "+ ex.getMessage());
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException: "+ ex.getMessage());
                }
                try{
                    clientSocket.close();
                }catch(IOException e){System.out.println("IOException: "+ e.getMessage());}
            }
}
