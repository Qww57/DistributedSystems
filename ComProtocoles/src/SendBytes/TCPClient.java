package SendBytes;

import java.net.*;

import utils.ConvertBytes;

import java.io.*;
import utils.SObject;

/**
 * Example of Client for sending and receiving of serializable objects by converting 
 * to bytes which is used with {@link TCPServer}
 * 
 * @author Quentin Tresontani
 *
 */

public class TCPClient {
		
	public static void main(String args[]){
		Socket s = null;
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			
			SObject sobject = new SObject("NAME");
			
			byte[] bytes = ConvertBytes.ConvertToBytes(sobject);
			
			// Sending
			int len = bytes.length;
			out.writeInt(len);
			if (len > 0) {
		        out.write(bytes, 0, len);
		    }
			
			// Reading
			int len2 = in.readInt();
		    byte[] data = new byte[len2];
		    if (len2 > 0) {
		        in.readFully(data);
		    }
			System.out.println("Received: "+ data);
			
			SObject sobjectReceived = (SObject) ConvertBytes.ConvertFromBytes(data);			
			System.out.println(sobjectReceived.getName());
			
		} catch(SocketException e){
			System.out.println("Socket: "+ e.getMessage());
		} catch(IOException e){
			System.out.println("IO: "+ e.getMessage());
		} finally{
			if (s != null){
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close: " + e.getMessage());
				}
			}
		}
	}
}
