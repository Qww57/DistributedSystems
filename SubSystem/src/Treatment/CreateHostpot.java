package Treatment;

import java.io.IOException;

import org.junit.Test;

/**
 * Class used in order to but create a Hostpot using command lines. 
 * FIXME Not working since it should be done using the command console
 * as an administrator and we have not been able to spend enought time
 * to fix it.
 * 
 * @author Quentin
 *
 */
public class CreateHostpot {
	
	private String osName = System.getProperty("os.name");
	
	@Test
	public void createHostpot(){
		System.out.println(osName);
		if (osName.contains("Windows")){
			System.out.println("System is windows");
			createHotspotWindows();
		}
	}
	
	@SuppressWarnings("unused")
	private static void createHotspotWindows(){
		Runtime rt = Runtime.getRuntime();
		try {
			String path = "C:\\Users\\Quentin\\Documents\\GitHub\\DistributedSystems\\SubSystem\\src\\Treatment\\cmd\\wifi.exe";
			path = path.replace("\\", "/"); 
			Process process = Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void createHotspotMac(){
		// TODO Ali
	}
}
