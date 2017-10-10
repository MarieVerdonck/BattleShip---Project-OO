package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class HumanPlayer extends Observable {
	private String naam;
	private ArrayList<Ship> schepen = new ArrayList<Ship>();
	public static final int MAX_SCHEPEN = 5;
	
	public HumanPlayer() {
		this("defaultName");
	}

	public HumanPlayer(String naam) {
		this.setNaam(naam);
	}



	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty()) {
			this.naam = "Player1";
		} else {
			this.naam = naam;
		}
	}

	public Ship getShipContainsNumber(int nummer) {
		for (Ship ship : this.getSchepen()) {
			if (ship.getSchipNummers().contains(nummer)) {
				return ship;
			}
		}
		return null;
	}

	public boolean addHitToShip(int nummer) {
		// returns if ship was destroyed
		Ship ship = this.getShipContainsNumber(nummer);
		if (ship == null) {
			throw new DomainException("Schip werd niet gevonden!");
		}
		ship.addNummerHit(nummer);
		boolean destroyed = false;
		if (ship.getSchipNummershit().containsAll(ship.getSchipNummers())) {
			destroyed = true;
		}
		this.setChanged();
		this.notifyObservers();

		return destroyed;
	}

	public ArrayList<Ship> getAllDestroyedShips() {
		ArrayList<Ship> destroyedShips = new ArrayList<Ship>();
		for (Ship ship : this.getSchepen()) {
			if (ship.getSchipNummershit().containsAll(ship.getSchipNummers())) {
				destroyedShips.add(ship);
			}
		}
		return destroyedShips;
	}

	public boolean isGameOver() {
		boolean gameOver = false;
		if (this.getAllDestroyedShips().containsAll(this.getSchepen())) {
			gameOver = true;
		}
		return gameOver;
	}

	public ArrayList<Integer> allNumbersOfDestroyedShips() {
		ArrayList<Integer> destroyedNumbers = new ArrayList<Integer>();
		for (Ship s : this.getAllDestroyedShips()) {
			destroyedNumbers.addAll(s.getSchipNummers());
		}
		return destroyedNumbers;
	}

	public void addHitToShip(int nummer, Ship schiep) {
		Ship ship = schiep;
		if (ship == null) {
			throw new DomainException("Schip werd niet gevonden!");
		}
		ship.addNummerHit(nummer);
	}

	public ArrayList<Ship> getSchepen() {
		return schepen;
	}

	public int getAantalSchepen() {
		return this.getSchepen().size();
	}

	public List<Integer> getAllShipNumbers() {
		List<Integer> schipnummers = new ArrayList<Integer>();
		for (Ship ship : schepen) {
			schipnummers.addAll(ship.getSchipNummers());
		}
		return schipnummers;
	}

	public void addShip(Ship ship) {
		if (maxAantalSchepen()) {
			throw new DomainException("Je kan niet meer dan 5 schepen plaatsen!");
		}
		if (!this.maxAantalSchepenType(ship)) {
			throw new DomainException("Van dit type kan men niet meer schepen plaatsen!");
		}
		if (!this.overlaptNietMetAnderSchip(ship)) {
			throw new DomainException("Dit schipt is te dicht bij een ander schip geplaatst!");
		}
		this.schepen.add(ship);
	}

	public void addShip(ShipType schipType, Direction richting, int beginVakje) {
		Ship ship = new Ship(schipType, richting, beginVakje);
		this.addShip(ship);
	}

	public Ship getlastAddedShip() {
		return schepen.get(schepen.size() - 1);
	}

	private boolean maxAantalSchepen() {
		return schepen.size() == MAX_SCHEPEN;
	}

	private boolean maxAantalSchepenType(Ship ship) {
		int aantal = 0;
		for (Ship s : this.getSchepen()) {
			if (s.getSchipType().equals(ship.getSchipType())) {
				aantal++;
			}
		}
		// true als schip mag toevoegen
		return ship.getSchipType().getAantalToegelatenSchepen() > aantal;
	}

	private boolean overlaptNietMetAnderSchip(Ship ship) {
		for (Ship s : this.getSchepen()) {
			for (Integer i : ship.getSchipNummers()) {
				if (s.getNummersRondomSchip().contains(i) || s.getSchipNummers().contains(i)) {
					return false;
				}
			}
		}
		return true;
	}

}
