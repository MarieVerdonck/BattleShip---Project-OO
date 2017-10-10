package model.Strategy;
/**
 * 
 * @author Dries Janse
 *
 */
public enum PlaceStrategy {
	RANDOM("model.Strategy.RandomPlaceShipStrategy"),
	HARD("model.Strategy.HardPlaceShipStrategy");

	private String fullClassName;

	private PlaceStrategy(String fullClassName) {
		this.fullClassName = fullClassName;
	}

	public String getFullClassName() {
		return this.fullClassName;
	}

}
