package Logic.Application.Sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Logic.Application.utils.ConvertBytes;
import Logic.Application.utils.TimeLimitedCodeBlock;

/**
 * Class implementing a template for a TCP Client with POLL protocol
 * 
 * @author Quentin
 *
 */
@SuppressWarnings({"hiding", "boxing", "static-access"})
public class ObjectClientTemplate {

	private int id;
	private int serverPort;
	private String host = null;
	private static int msTimeOut = 5000;
	private Socket s = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private static boolean keepGoing = true;
	
	// Objects to send and receive
	Object object;
	protected Class<?> expectedClass;
	
	public ObjectClientTemplate(int id, String host, int port) throws Exception {
		this.id = id;
		this.serverPort = port;
		this.host = host;
		initialize();
	}
	
	/* Getters and setters */

	public int getId(){
		return id;
	}
	
	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	public void setTimeOut(int ms){
		this.msTimeOut = ms;
	}
	
	/* Public methods */
	
	public Object sendPollRequest() throws Exception{
		
		this.object = null;
		
		this.keepGoing = true;
		
		sendPoll();
		
		while (keepGoing){
			Object data = in.readObject();
			System.out.println("Received: "+ data);
			
			final long startTime = System.currentTimeMillis();
			long finalTime = startTime;
			
			/* Setting the loop with time out */
		    System.out.println("calling runWithTimeout! " + startTime);
		    try {
		    	// Putting a time out
		    	TimeLimitedCodeBlock.runWithTimeout(new Runnable() {
			        @Override
			        public void run() {
			        	// TODO verification for REPT request
			        	object = data;
			        	keepGoing = false;
			        	
				    }}, msTimeOut, TimeUnit.MILLISECONDS);
			}catch (TimeoutException e) {
				finalTime = (System.currentTimeMillis() - startTime);
				print(finalTime, "got timeout! (ms)");
			} 
		    finalTime = (System.currentTimeMillis() - startTime);
		    print(finalTime, "end of main method! (ms)");
		}
		return object;
	}
	
	public void sendObject() throws Exception{
		byte[] bytes = ConvertBytes.ConvertToBytes(object);
		
		int len = bytes.length;
		out.writeInt(len);
		if (len > 0) {
	        out.write(bytes, 0, len);
	    }
	}
	
	public void closeClient(){
		if (s != null){
			try {
				s.close();
			} catch (IOException e) {
				System.out.println("close: " + e.getMessage());
			}
		}
	}

	/* Private methods */
	
	private void initialize() throws Exception {
		s = new Socket(host, serverPort);
		out= new ObjectOutputStream(s.getOutputStream());
        out.flush();
        in = new ObjectInputStream(s.getInputStream());
	}
	
	private void sendPoll() throws Exception{
		System.out.println("Sending poll request");
		out.writeObject("POLL");
	}
	
	private void print(Object object, String string){
		System.out.println("Client " + this.id + ": " + string + " " + (long) object);
	}
		
}
