package SendBytes;

import java.io.*;
import java.net.*;

import utils.ConvertBytes;

public class Server {
	public static void main (String args[]){
		try {
			int serverPort = 7000;
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				SObjectThread c = new SObjectThread(clientSocket);
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
}

class SObjectThread extends Thread{
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
			System.out.println("EchoThread: " + e.getMessage());
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