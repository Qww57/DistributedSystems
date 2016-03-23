package SendBytes;

import java.io.*;
import java.net.*;

import utils.ConvertBytes;

/**
 * Example of sending and receiving of serializable objects using bytes
 * 
 * @author Quentin Tresontani
 *
 */

public class TCPServer {
	public static void main (String args[]){
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				EchoThread c = new EchoThread(clientSocket);
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
}

class EchoThread extends Thread{
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	
	public EchoThread(Socket aClientSocket){
		try{
			System.out.println("NEW SERVER THREAD");
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
			// Reading 
			int len = in.readInt();
		    byte[] data = new byte[len];
		    if (len > 0) {
		        in.readFully(data);
		    }
			
			SObject sobject = (SObject) ConvertBytes.ConvertFromBytes(data);			
			System.out.println(sobject.getName());
			
			// Sending 
			int len2 = data.length;
			out.writeInt(len2);
			if (len2 > 0) {
		        out.write(data, 0, len2);
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