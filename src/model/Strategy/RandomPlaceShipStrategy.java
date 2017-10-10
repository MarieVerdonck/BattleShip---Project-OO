package model.Strategy;

import java.util.Random;

import model.Direction;
import model.Ship;
import model.ShipType;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class RandomPlaceShipStrategy implements PlaceShipStrategy {

	@Override
	public Ship placeRandomShip() {
		Random random = new Random();
		ShipType schipType = ShipType.values()[random.nextInt(ShipType.values().length)];
		Direction richting = Direction.values()[random.nextInt(Direction.values().length)];
		int beginVakje = (int) (Math.random() * 99);
		return new Ship(schipType, richting, beginVakje);
	}

}
