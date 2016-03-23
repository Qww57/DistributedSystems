package SendBytes.Templates.Threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import SendBytes.SObject;
import utils.ConvertBytes;

public class EchoThread extends Thread{
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