package Logic.Application.Sockets.Threads;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Logic.Application.CommunicationController;
import Logic.Application.DataTransfertObjects.AbstractDTO;

/**
 * Thread used in order to send DTO objects
 * 
 * @author Ali, Quentin
 *
 */
public class DTOSenderObjectThread extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	
	public DTOSenderObjectThread(Socket aClientSocket){		
        try {
        	System.out.println("NEW DTO SENDER SERVER THREAD");
        	clientSocket = aClientSocket;
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.start(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			Object data = in.readObject();
			
			if (data instanceof String){
                AbstractDTO objectDTO = CommunicationController.getNextDTO();
                out.writeObject(objectDTO);           
			}		
		} catch (EOFException e){
			System.out.println("EOFException: " + e.getMessage());
		} catch (IOException e){
			System.out.println("IOException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: " + e.getMessage());
			e.printStackTrace();
		}
		try{
            clientSocket.close();
        }catch(IOException e1){
        	System.out.println("IOException: "+ e1.getMessage());
        }
	}
}
