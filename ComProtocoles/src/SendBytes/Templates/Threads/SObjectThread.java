package SendBytes.Templates.Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import utils.SObject;
import utils.ConvertBytes;

/**
 * Implementation for SObject Thread which converts objects to bytes
 * and send them back in case it receives a poll request
 *
 */

public class SObjectThread extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	
	public SObjectThread(Socket aClientSocket){
		try{
			System.out.println("NEW SERVER THREAD");
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e){
			System.out.println("SObjectThread: " + e.getMessage());
		}
	}
	
	public void run(){
		try{
			String data = in.readUTF();
			if (data.equals("POLL")){
				SObject sobject = new SObject("NEWOBJECT");
				byte[] bytes = ConvertBytes.ConvertToBytes(sobject);
								
				// Sending 
				int len2 = bytes.length;
				out.writeInt(len2);
				if (len2 > 0) {
			        out.write(bytes, 0, len2);
			    }
			}		
						
		} catch (EOFException e){
			System.out.println("EOFException: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IOException: " + e.getMessage());
		} finally {
			try{ 
				clientSocket.close();
			} catch (IOException e){
				/* Close failed */
			}
		}
	}
}
