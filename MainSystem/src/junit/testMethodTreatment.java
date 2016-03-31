package junit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.*;

import Logic.Application.utils.ConvertBytes;

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
