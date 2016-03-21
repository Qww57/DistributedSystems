package Poll;

import java.io.*;
import java.net.*;
import java.util.*;

/*
 * Server receiving the poll request and answering correctly
 */
public class PollServer{
	
	private static int portNumber = 9000;
	
	private static ServerSocket listenSocket = null;
	
	private static Map<String, Integer> sequenceNumbers = new HashMap<String, Integer>();;
		
	public static void main (String args[]){
		try {
			int serverPort = portNumber;
			listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				EchoThread c = new EchoThread(clientSocket);
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
	
	public static int getPortNumber(){
		/* Only for development purposes */
		return portNumber;
	}
	
	public static void closeListenSocket(){
		try {
			listenSocket.close();
		} catch(Exception e){
			System.out.println("Close: " + e.getMessage());
		}
	}
}

class EchoThread extends Thread{
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	
	public EchoThread(Socket aClientSocket){
		try{
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e){
			System.out.println("EchoThread: " + e.getMessage());
		}
	}
	
	public void run(){
		try{
			String data = in.readUTF();
					
			if (data.contains("POLL")){
				/* TODO: add function checking for opened port number 
				 * and returning a new port number */
				String newPortNumber = "newPort";
				out.writeUTF(newPortNumber);
			}
			if (data.contains("REPT")){
				/* TODO: add function checking for opened port number 
				 * and returning a new port number */
				String newPortNumber = "8005";
				out.writeUTF(newPortNumber);
			}
			
		} catch (EOFException e){
			System.out.println("EOFException: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IOException: " + e.getMessage());
		 } /* finally {
			closeServer();
		} */
	}
	
	public void closeClientServer(){
		try{ 
			clientSocket.close();
			System.out.println("Closing");
		} catch (IOException e){
			// Close failed 
		}
	}
}
