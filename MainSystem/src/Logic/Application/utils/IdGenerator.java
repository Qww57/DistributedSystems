package Logic.Application.utils;

@SuppressWarnings("boxing")
public class IdGenerator {

	private static Integer resourcePOJO = 0;
	
	private static Integer treatmentPOJO = 0;
	
	private static Integer resourceDTO = 0;
	
	private static Integer treatmentDTO = 0;
	
	private static Integer packageDTO = 0;
		
	public static String resourceDTOId(){
		resourceDTO++;
		String id = "rDTO_" + resourceDTO.toString();
		return id;
	}
	
	public static String packageDTOId(){
		packageDTO++;
		String id = "pDTO_" + packageDTO.toString();
		return id;
	}
	
	public static String treatmentDTOId(){
		treatmentDTO++;
		String id = "tDTO_" + treatmentDTO.toString();
		return id;
	}
	
	public static String resourcePOJOId(){
		resourcePOJO++;
		String id = "rPOJO_" + resourcePOJO.toString();
		return id;
	}
	
	public static String treatmentPOJOId(){
		treatmentPOJO++;
		String id = "tPOJO_" + treatmentPOJO.toString();
		return id;
	}
}
