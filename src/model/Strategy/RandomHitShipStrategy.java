package model.Strategy;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Marie Verdonck, Bram Van Asschodt
 *
 */
public class RandomHitShipStrategy implements HitShipStrategy {

	private List<Integer> alreadyHit = new ArrayList<Integer>();

	@Override
	public int hitShip() {
		int result = -1;

		while (result == -1) {
			int temp = (int) (Math.random() * 100);
			if (alreadyHit.size() == 100) {
				result = 99;
			}
			if (!(alreadyHit.contains(temp))) {
				alreadyHit.add(temp);
				result = temp;
			}
		}
		return result;
	}

}
