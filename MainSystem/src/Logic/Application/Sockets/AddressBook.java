package Logic.Application.Sockets;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores the addresses of the sub systems linked to our main server
 * 
 */
public class AddressBook {
	
	private static Map<Integer, Address> subSystems;
	static{
		subSystems = new HashMap<Integer, Address>();
		subSystems.put(new Integer(0), new Address("localhost", 7000, 6000));
		subSystems.put(new Integer(1), new Address("localhost", 7001, 6001));
    }
	
	public static Address getSubSystem(Integer integer) {
		return subSystems.get(integer);
	}
	
	public static Integer getSubSystemID(Address address){
		Integer id = null;
		for (Map.Entry<Integer, Address> e : subSystems.entrySet()) {
			Integer key = e.getKey();
			Address value = e.getValue();
			if (value == address){
				id = key;
			}
		} 
		return id;
	}
}