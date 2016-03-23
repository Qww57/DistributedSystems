package SendBytes;

import java.io.*;
import java.net.*;

import SendBytes.Templates.Threads.SObjectThread;
import utils.ConvertBytes;

public class PollServer {
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

