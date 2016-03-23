package Logic.Application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreatmentMethod implements Serializable {
		
	private static final long serialVersionUID = 857218763908397452L;

	@SuppressWarnings("boxing")
	public List<Integer> computation(int lower, int upper){
		List<Integer> primeNumbers = new ArrayList<Integer>();
		for (int i = lower; i < upper; i++){
			if (isPremier(i)){
				primeNumbers.add(i);
			}
		}
		return primeNumbers;
	}
	
	private static boolean isPremier(int n) {
		boolean isPremier = true;
		if (n < 0) {
			isPremier = false;
		} else if (n != 0 && n != 1) {
			for (int i = 2; i <= n/2; i++) {
				if (n != i && n % i == 0) {
					isPremier = false;
					break;
				}
			}
		}
		return isPremier;
	}
}
