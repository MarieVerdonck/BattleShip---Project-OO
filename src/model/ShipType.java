package model;

import java.util.ArrayList;
/**
 * 
 * @author Bram Van Asschodt
 *
 */
public enum ShipType {
    VLIEGDEKSCHIP	(5, 1),
    SLAGSCHIP 		(4, 2),
    ONDERZEEER		(3, 3),
    TORPEDOBOOTJAGER(3, 3),
    PATROUILLESCHIP	(2, 4);

	private final int aantalVakjes;
	private final int aantalToegelatenSchepen;

	private ShipType(int aantalVakjes, int aantalToegelatenSchepen) {
		this.aantalVakjes = aantalVakjes;
		this.aantalToegelatenSchepen = aantalToegelatenSchepen;
	}

	public int getAantalVakjes() {
		return aantalVakjes;
	}

	public int getAantalToegelatenSchepen() {
		return aantalToegelatenSchepen;
	}

	public static ArrayList<ShipType> getAllSchiptypes() {
		ArrayList<ShipType> allSchiptypes = new ArrayList<ShipType>();
		for (ShipType schiptype : ShipType.values()) {
			allSchiptypes.add(schiptype);
		}
		return allSchiptypes;
	}

}
