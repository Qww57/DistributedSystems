package Logic.Application.utils;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;

import Logic.Application.ResourceDTO;

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
	
	@Test
	public void testConvertBytes(){
		ResourceDTO resourceDTO = new ResourceDTO(0, 0, 20, 50, false, null);
	
		byte[] bytes = this.ConvertToBytes(resourceDTO);
		System.out.println(bytes);
		
		Object object = this.ConvertFromBytes(bytes);
		ResourceDTO resource = (ResourceDTO) object;
		System.out.println(resource);

		assertEquals(50, resource.getUpperLimite());
	}
}

