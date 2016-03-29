package SendBytes;

import java.io.*;
import java.net.*;

import SendBytes.Templates.Threads.SObjectThread;

public class PollServer {
	public static void main (String args[]){
		try {
			int serverPort = 7000;
			@SuppressWarnings("resource")
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				@SuppressWarnings("unused")
				SObjectThread c = new SObjectThread(clientSocket);
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
}

