package SendBytes.Templates;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import utils.ConvertBytes;
import utils.TimeLimitedCodeBlock;

/**
 * Class implementing a template for a TCP Client with POLL protocol
 *  which is used with  {@link Server}
 * 
 * @author Quentin
 *
 */
@SuppressWarnings({"hiding", "boxing", "static-access"})
public class Client {

	private int id;
	private int serverPort;
	private String host = null;
	private static int msTimeOut = 5000;
	private Socket s = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	private static boolean keepGoing = true;
	
	// Objects to send and receive
	Object object;
	protected Class<?> expectedClass;
	
	public Client(int id, String host, int port, Class<?> expectedObject) throws Exception {
		this.id = id;
		this.serverPort = port;
		this.host = host;
		this.expectedClass = expectedObject;
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
			Object data = this.readObject();
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
			        	try {
							if (verification(data)){
								object = data;
							}
						} catch (Exception e) {
							System.out.println("Exception: " + e.getMessage());
						}
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
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
	}
	
	private boolean verification(Object object) throws Exception {
				
		if (object.getClass().equals(expectedClass)) {
			this.keepGoing = false;
			return true;
		}
		sendRept();
		System.out.println("REPT sent");
		object = this.readObject();
		return false;
	}
	
	private Object readObject() throws IOException {
		try{
			int len = in.readInt();
		    byte[] data = new byte[len];
		    if (len > 0) {
		        in.readFully(data);
		    }	    
		    Object object = ConvertBytes.ConvertFromBytes(data);
		    return object;
		}catch (Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
	}
	
	private void sendRept() throws Exception{
		initialize();
		out.writeUTF("REPT");
	}
	
	private void sendPoll() throws Exception{
		out.writeUTF("POLL");
	}
	
	private void print(Object object, String string){
		System.out.println("Client " + this.id + ": " + string + " " + (long) object);
	}
		
}
