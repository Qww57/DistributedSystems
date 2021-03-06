package PollProtocol;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import utils.TimeLimitedCodeBlock;

/* 
 *  PollPort Client which is used with {@link PollPortServer}
 */ 
@SuppressWarnings("boxing")
public class PollPortClient {
	
	/* Port number used to call the server */
	private static int portNumber;
	
	/* Fields used to create the sockets */
	private static Socket s = null;
	private static DataInputStream in = null;
	private static DataOutputStream out = null;
	
	/* Fields linked to the POLL protocol */
	private static boolean keepGoing = true;
	private static int msTimeOut = 5000;
	
	public static void main(String args[]){
		
		/* Only for development purposes */
		portNumber = PollPortServer.getPortNumber();
		
		try{
			sendPoll();
			while (keepGoing){ 
				loop();
			}			
		} catch(SocketException e){
			System.out.println("Socket: "+ e.getMessage());
		} catch(IOException e){
			System.out.println("IO: "+ e.getMessage());
		} catch(Exception e){
			System.out.println("Exception: "+ e.getMessage());
		} finally{
			if (s != null){
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close: " + e.getMessage());
				}
			}
		}
	}
	
	/*
	 * Loop sending the POLL and REPT requests
	 */
	private static void loop() throws IOException, Exception {
		String data = in.readUTF();
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
						verification(data);
					} catch (IOException e) {
						System.out.println("close: " + e.getMessage());
					}
			    }}, msTimeOut, TimeUnit.MILLISECONDS);
		}catch (TimeoutException e) {
			finalTime = (System.currentTimeMillis() - startTime);
			print(finalTime, "got timeout! (ms)");
		} 
	    finalTime = (System.currentTimeMillis() - startTime);
	    print(finalTime, "end of main method! (ms)");	
	}
	
	/*
	 * Verification of the message received from the server
	 */
	private static void verification(String data) throws IOException{
		if (data.matches("[0-9]+") && data.length() > 2) {
			keepGoing = false;
			System.out.println("Port number received: " 
					+ Integer.parseInt(data));
		} 
		else {
			sendRept();
			System.out.println("REPT sent");
			data = in.readUTF();
			System.out.println(data);
		}
	}
	
	private static void print(Object object, String string){
		System.out.println(string + " " + (long) object );
	}
		
	private static void sendPoll() throws IOException{
		s = new Socket("localhost", portNumber);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("POLL");
	}
	
	private static void sendRept() throws IOException{
		s = new Socket("localhost", portNumber);
		in = new DataInputStream(s.getInputStream());
		out = new DataOutputStream(s.getOutputStream());
		out.writeUTF("REPT");
	}
}
