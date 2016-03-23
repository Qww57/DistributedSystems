package SendBytes;

import java.io.*;
import java.net.*;

import SendBytes.Templates.Threads.EchoThread;
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