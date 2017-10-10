package model.state;

import model.BattleShipGame;
import model.DomainException;
/**
 * 
 * @author Dries Janse
 *
 */
public class NewGameState implements GameState {
	private BattleShipGame game;

	public NewGameState(BattleShipGame game) {
		this.game = game;
	}

	@Override
	public void newGame() {
		throw new DomainException("Kan geen nieuw spel starten als nog niet gestart is!");
	}

	@Override
	public void start() {
		if (game.getHumanPlayer().getAantalSchepen() != 5) {
			throw new DomainException("Er moeten 5 schepen geplaatst worden!");
		}
		game.setCurrentGameState(game.getStartedGameState());
	}

}
