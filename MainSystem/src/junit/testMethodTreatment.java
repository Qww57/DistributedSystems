package junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.*;

import Logic.Application.utils.ConvertBytes;
import Logic.Exposition.TreatmentMethod;

/**
 * Method used in order to test the wrapping and unwrapping of a function as bytes.
 * Working when declared in the same project for wrapping and unwrapping, but not if
 * the sender and receiver are in different projects. 
 * We aimed to unwrap it in the subsystem project (later on, client software) but we 
 * were getting class not found exceptions. This design idea has then been given up 
 * accordingly to researches we made on the Internet on pushing computation treatment
 * in grid computing.
 * 
 * @author Quentin
 *
 */

public class testMethodTreatment {

	byte[] treatment = null;
	
	@Before
	public void Initialize(){
		TreatmentMethod treatmentExample = new TreatmentMethod();
		treatment = ConvertBytes.ConvertToBytes(treatmentExample);
	}
	
	@Test
	@SuppressWarnings("boxing")
	public void test(){
		Object treatmentObject = ConvertBytes.ConvertFromBytes(treatment);
	
		try {
			Method[] methods = treatmentObject.getClass().getMethods();	
			Object[] args = {15, 57};
			
			System.out.println(methods[0].invoke(treatmentObject, args));
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
