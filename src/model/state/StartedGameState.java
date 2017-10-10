package model.state;

import model.BattleShipGame;
import model.DomainException;
/**
 * 
 * @author Marie Verdonck, Bram Van Asschodt
 *
 */
public class StartedGameState implements GameState {
	private BattleShipGame game;

	public StartedGameState(BattleShipGame game) {
		this.game = game;
	}

	@Override
	public void newGame() {
		this.game.setCurrentGameState(this.game.getNewGameState());
	}

	@Override
	public void start() {
		throw new DomainException("Er is al een spel bezig!");
	}

}
