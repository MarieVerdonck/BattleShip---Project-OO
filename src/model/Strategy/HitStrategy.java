package model.Strategy;
/**
 * 
 * @author Dries Janse
 *
 */
public enum HitStrategy {
	RANDOM("model.Strategy.RandomHitShipStrategy"), ORDERED("model.Strategy.OrderedHitStrategy");

	private String fullClassName;

	private HitStrategy(String fullClassName) {
		this.fullClassName = fullClassName;
	}

	public String getFullClassName() {
		return this.fullClassName;
	}

}
