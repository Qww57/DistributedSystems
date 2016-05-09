package SendBytes;

import java.io.*;
import java.net.*;

import SendBytes.Templates.Threads.EchoThread;
/**
 * Server which is used with {@link TCPClient}
 * 
 * @author Quentin Tresontani
 *
 */

public class TCPServer {
	
	public static void main (String args[]){
		try {
			int serverPort = 7896;
			@SuppressWarnings("resource")
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				@SuppressWarnings("unused")
				EchoThread c = new EchoThread(clientSocket);
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
}