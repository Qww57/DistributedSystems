package utils;

import java.io.*;

/** Class that converts objects to bytes 
 *
 * @author Ehsan
 */

public class ConvertBytes {
	
	public static byte[] ConvertToBytes(Object object){
		
		byte[] yourBytes = null;
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		
		try {
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(object);
				yourBytes = bos.toByteArray();
			} catch (IOException e1) {
				e1.printStackTrace();
			}     
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				bos.close();
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		
		return yourBytes;
	}
	
	public static Object ConvertFromBytes(byte[] bytes){
		
		Object o = new Object();
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInput in = null;
		
		try {
			try {
				in = new ObjectInputStream(bis);
				o = in.readObject(); 
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} finally {
			try {
				bis.close();
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		
		return o;
	}
}

