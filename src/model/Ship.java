package model;

import java.util.ArrayList;
/**
 * 
 * @author Dries Janse
 *
 */
public class Ship {

	private ArrayList<Integer> schipNummers = new ArrayList<Integer>();
	private ArrayList<Integer> schipNummershit = new ArrayList<Integer>();
	private Direction richting;
	private ShipType schipType;

	public Ship(ShipType schipType, Direction richting, int beginVakje) {
		this.setRichting(richting);
		this.setSchipType(schipType);
		this.setAllShipNumber(beginVakje);
	}

	private void setAllShipNumber(int beginVakje) {
		if (this.getRichting().equals(Direction.HORIZONTAAL)) {
			int eindvakje = beginVakje + (10 * (schipType.getAantalVakjes() - 1));
			if (eindvakje < 100) {
				for (int i = beginVakje; i <= eindvakje; i += 10) {
					schipNummers.add(i);
				}
			} else {
				throw new DomainException("Schip kan zo niet horizontaal geplaatst worden!");
			}
		} else {
			int eindvakje = beginVakje + schipType.getAantalVakjes() - 1;
			if ((eindvakje % 10) > (beginVakje % 10)) {
				for (int i = beginVakje; i <= eindvakje; i++) {
					schipNummers.add(i);
				}
			} else {
				throw new DomainException("Schip kan zo niet vertikaal geplaatst worden!");
			}
		}
	}

	public ArrayList<Integer> getNummersRondomSchip() {
		ArrayList<Integer> nummersRondom = new ArrayList<Integer>();

		int eersteNummer = this.getSchipNummers().get(0);
		int laatsteNummer = this.getSchipNummers().get(this.getSchipNummers().size() - 1);

		if (this.getRichting().equals(Direction.HORIZONTAAL)) {
			for (Integer integer : this.getSchipNummers()) {
				if (integer == eersteNummer) {
					if ((integer % 10) != 0) {
						nummersRondom.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						nummersRondom.add(integer + 1);
					}
					if (integer - 10 > 0) {
						nummersRondom.add(integer - 10);
					}
					if ((integer - 10) > 0 && (integer % 10) != 0) {
						nummersRondom.add(integer - 11);
					}
					if ((integer - 10) > 0 && (integer % 10) != 9) {
						nummersRondom.add(integer - 9);
					}
				} else if (integer == laatsteNummer) {
					if ((integer + 10) < 100) {
						nummersRondom.add(integer + 10);
					}
					if ((integer % 10) != 0) {
						nummersRondom.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						nummersRondom.add(integer + 1);
					}
					if ((integer + 10) < 100 && (integer % 10) != 0) {
						nummersRondom.add(integer + 9);
					}
					if ((integer + 10) < 100 && (integer % 10) != 9) {
						nummersRondom.add(integer + 11);
					}
				} else {
					if ((integer % 10) != 0) {
						nummersRondom.add(integer - 1);
					}
					if ((integer % 10) != 9) {
						nummersRondom.add(integer + 1);
					}
				}
			}
		} else {
			for (Integer integer : this.getSchipNummers()) {
				if (integer == eersteNummer) {
					if ((integer % 10) != 0) {
						nummersRondom.add(integer - 1);
					}
					if ((integer + 10) < 100) {
						nummersRondom.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						nummersRondom.add(integer - 10);
					}
					if ((integer % 10) != 0 && (integer - 10) > 0) {
						nummersRondom.add(integer - 11);
					}
					if ((integer % 10) != 0 && (integer + 10) < 100) {
						nummersRondom.add(integer + 9);
					}

				} else if (integer == laatsteNummer) {
					if ((integer + 10) < 100) {
						nummersRondom.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						nummersRondom.add(integer - 10);
					}
					if ((integer % 10) != 9) {
						nummersRondom.add(integer + 1);
					}
					if ((integer % 10) != 9 && (integer - 10) > 0) {
						nummersRondom.add(integer - 9);
					}
					if ((integer % 10) != 9 && (integer + 10) < 100) {
						nummersRondom.add(integer + 11);
					}
				} else {
					if ((integer + 10) < 100) {
						nummersRondom.add(integer + 10);
					}
					if ((integer - 10) > 0) {
						nummersRondom.add(integer - 10);
					}
				}
			}
		}
		return nummersRondom;
	}

	public void addNummerHit(int nummer) {
		if (!this.getSchipNummers().contains(nummer)) {
			throw new DomainException("Kan niet raken want is geen nummer van schip (Ship klasse)");
		}
		this.getSchipNummershit().add(nummer);
	}

	private void setRichting(Direction richting) {
		this.richting = richting;
	}

	private void setSchipType(ShipType schipType) {
		this.schipType = schipType;
	}

	public ArrayList<Integer> getSchipNummers() {
		return schipNummers;
	}

	public ArrayList<Integer> getSchipNummershit() {
		return schipNummershit;
	}

	public Direction getRichting() {
		return richting;
	}

	public ShipType getSchipType() {
		return schipType;
	}

}
