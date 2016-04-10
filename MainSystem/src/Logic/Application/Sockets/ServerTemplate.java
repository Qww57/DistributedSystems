package Logic.Application.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Logic.Application.Sockets.Threads.DTOSenderByteThread;
import Logic.Application.Sockets.Threads.DTOSenderObjectThread;
import Logic.Application.Sockets.Threads.PingPongThread;

/**
 * Class defining a server. In order to use it, a thread should be
 * added.
 * When creating a new thread, the thread should be added into the 
 * enum and switch case of startServer of this file.
 * 
 * @author Quentin
 *
 */
public class ServerTemplate {
	
	public enum ThreadType {
		DTOByteSender,
		DTOObjectSender,
		PingPongThread
	}

	private int id;
	private int serverPort;
	private String hostAddress;
	private ServerSocket listenSocket = null;
	private ThreadType threadType;
	private Addresses subSystemAddress;
	
	public ServerTemplate(int id, String hostAddress, int serverPort, ThreadType thread) {
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
	
	public Addresses getSubSystemAddresses() {
		return subSystemAddress;
	}

	public void setSubSystemAddresses(Addresses subSystemAddresses) {
		this.subSystemAddress = subSystemAddresses;
	}
	
	@SuppressWarnings("unused")
	public void startServer(){
		try {
			listenSocket = new ServerSocket(serverPort);
			while(true){
				Socket clientSocket = listenSocket.accept();
				switch (threadType){
				case DTOByteSender: 
					DTOSenderByteThread c0 = new DTOSenderByteThread(clientSocket);
					break;
				case DTOObjectSender: 
					DTOSenderObjectThread c1 = new DTOSenderObjectThread(clientSocket);
					break;
				case PingPongThread:
					PingPongThread c2 = new PingPongThread(clientSocket, subSystemAddress);
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
