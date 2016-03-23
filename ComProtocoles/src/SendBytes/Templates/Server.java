package SendBytes.Templates;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import SendBytes.Templates.Threads.*;

/**
 * Class defining a server. In order to use it, a thread should be
 * added.
 * When creating a new thread, the thread should be added into the 
 * enum and switch case of startServer of this file.
 * 
 * @author Quentin
 *
 */
public class Server {
	
	public enum ThreadType {
		SOBJECT, 
		ECHO
	}

	private int id;
	private int serverPort;
	private String hostAddress;
	private ServerSocket listenSocket = null;
	private ThreadType threadType;
	
	public Server(int id, String hostAddress, int serverPort, ThreadType thread) {
		super();
		this.id = id;
		this.serverPort = serverPort;
		this.hostAddress = hostAddress;
		this.threadType = thread;
	}
	
	public int getId() {
		return id;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public String getHostAddress() {
		return hostAddress;
	}
	
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	
	public ThreadType getThreadType() {
		return threadType;
	}
	
	public void setThreadType(ThreadType thread) {
		this.threadType = thread;
	}
	
	@SuppressWarnings("unused")
	public void startServer(){
		try {
			listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				switch (threadType){
				case SOBJECT: 
					SObjectThread c0 = new SObjectThread(clientSocket);
					break;
				case ECHO:
					EchoThread c1 = new EchoThread(clientSocket);
					break;
				default:
					break;
				}
			}
		} catch (IOException e){
			System.out.println("Listen: " + e.getMessage());
		}
	}
	
	public void closeServer(){
		try{ 
			listenSocket.close();
		} catch (IOException e){
			/* Close failed */
		}
	}
}
