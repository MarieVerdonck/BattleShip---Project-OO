package model.Strategy;
/**
 * 
 * @author  Bram Van Asschodt
 *
 */
public class OrderedHitStrategy implements HitShipStrategy {

	private int lastInteger = -1;

	@Override
	public int hitShip() {
		if (lastInteger < 99) {
			lastInteger++;
		}
		return lastInteger;

	}

}
