package Logic.Application.Sockets;

import java.util.HashMap;
import java.util.Map;

public class AddressBook {
	
	private static Map<Integer, Addresses> subSystems;
	static{
		subSystems = new HashMap<Integer, Addresses>();
		subSystems.put(new Integer(0), new Addresses("localhost", 7000, 6000));
		subSystems.put(new Integer(1), new Addresses("localhost", 7001, 6001));
    }
	
	public static Addresses getSubSystem(Integer integer) {
		return subSystems.get(integer);
	}
	
	public static Integer getSubSystemID(Addresses address){
		Integer id = null;
		for (Map.Entry<Integer, Addresses> e : subSystems.entrySet()) {
			Integer key = e.getKey();
			Addresses value = e.getValue();
			if (value == address){
				id = key;
			}
		} 
		return id;
	}
}