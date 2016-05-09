package utils;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

/** Class that checks and gets free ports
 *
 * @author Ehsan
 */

public class PortScanner {

	private static List<Integer> freePorts = new ArrayList<>();
	
	public static List<Integer> getFreePorts(int start, int end){
		scanFreePorts(start, end);
		return freePorts;
	}
	
	public static Integer getOneFreePort(int start, int end){
		scanFreePorts(start, end);
		return freePorts.get(0);
	}
	
	public static void main(String args[]){
		long begin = System.currentTimeMillis();
		
		scanFreePorts(8000, 8050);
		
		long end = System.currentTimeMillis();
		float time = (end-begin) / 1000f;
		System.out.println("Test executed in " + time + " seconds");
	}
	
	@SuppressWarnings("boxing")
	private static void scanFreePorts(int start, int end){
			
		for (int port = start; port < end; port++){
			try {
				Socket socket = new Socket();
		        socket.connect(new InetSocketAddress("localhost", port));
		        socket.close();

		        // It works, it means that the port is used
		        System.out.println("Port " + port + " of localhost is not free");
			} catch (Exception ex) {	
				// If we cannot close the port, the port is not open
				freePorts.add(port);
				System.out.println("Port " + port + " of localhost is free");
		    }
		}	
	}
}
