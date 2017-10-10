package model;

import model.Strategy.HitShipStrategy;
import model.Strategy.PlaceShipStrategy;
import model.factory.HitShipFactory;
import model.factory.PlaceShipFactory;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class ComputerPlayer extends HumanPlayer {

	public PlaceShipStrategy schipStrategy;
	public HitShipStrategy hitShipStrategy;

	public ComputerPlayer() {
		super("Computer");
		this.readHitStrategyFromProp();
		this.readPlaceShipFromProp();
	}

	public void setShipsFromStrategy() {
		while (this.getSchepen().size() != MAX_SCHEPEN) {
			try {
				Ship generatedShip = schipStrategy.placeRandomShip();
				this.addShip(generatedShip);
			} catch (Exception e) {
			}
		}
	}

	public int hitShip() {
		return hitShipStrategy.hitShip();
	}

	public void readHitStrategyFromProp() {
		hitShipStrategy = new HitShipFactory().getHitShipStrategy();
	}

	public void readPlaceShipFromProp() {
		schipStrategy = new PlaceShipFactory().getPlaceShipStrategy();
	}

}
