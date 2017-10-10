package model.Strategy;

import model.Direction;
import model.Ship;
import model.ShipType;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Marie Verdonck
 *
 */
public class HardPlaceShipStrategy implements PlaceShipStrategy {

	@Override
	public Ship placeRandomShip() {
		Random random = new Random();
		ShipType schipType = this.getType(random);
		Direction richting = this.getDirection(random);
		int beginVakje = this.getSquare();
		return new Ship(schipType, richting, beginVakje);
	}

	private ShipType getType(Random random) {
		ShipType schipType;
		if (random.nextInt(7) == 0) {
			// 20% chance of bigger ship (vliegdekschip = 0, slagschip = 1)
			schipType = ShipType.values()[random.nextInt(1)];
		} else {
			// 80% chance of smaller ship
			// To get randint between min=3 and max=length: random.nextInt(max -
			// min + 1) + min
			schipType = ShipType.values()[random.nextInt(ShipType.values().length - 2 + 1) + 2];
		}
		return schipType;
	}

	private Direction getDirection(Random random) {
		Direction richting;
		if (random.nextInt(5) == 0) {
			// 60% chance of vertical
			richting = Direction.values()[0];
		} else {
			// else horizontal
			richting = Direction.values()[1];
		}
		return richting;
	}

	private int getSquare() {
		int beginVakje = (int) (Math.random() * 99);
		// try to avoid column 1, 5 and 10
		ArrayList<Integer> squaresToAvoid = this.getSquaresToAvoid();
		boolean optimalsquare = false;
		while (optimalsquare != true) {
			beginVakje = (int) (Math.random() * 99);
			if (!squaresToAvoid.contains(beginVakje)) {
				optimalsquare = true;
			}
		}
		return beginVakje;
	}

	private ArrayList<Integer> getSquaresToAvoid() {
		ArrayList<Integer> squaresToAvoid = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			squaresToAvoid.add(i); // kolom 1
			squaresToAvoid.add(i + 40); // kolom 5
			squaresToAvoid.add(i + 90); // kolom 10
		}
		return squaresToAvoid;
	}

}