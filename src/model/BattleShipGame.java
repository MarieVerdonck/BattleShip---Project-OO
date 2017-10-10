package model;

import model.Observer.BerekenScore;
import model.state.GameState;
import model.state.NewGameState;
import model.state.StartedGameState;

/**
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 */
public class BattleShipGame {
	private HumanPlayer humanPlayer;
	private ComputerPlayer computerPlayer;

	private BerekenScore scoreHumanPlayer;
	private BerekenScore scoreComputerPlayer;

	private GameState newGameState = new NewGameState(this);
	private GameState StartedGameState = new StartedGameState(this);
	private GameState currentGameState = newGameState;

	public BattleShipGame(String naam) {
		humanPlayer = new HumanPlayer(naam);
		computerPlayer = new ComputerPlayer();
		scoreHumanPlayer = new BerekenScore(humanPlayer);
		scoreComputerPlayer = new BerekenScore(computerPlayer);
	}

	public GameState getCurrentGameState() {
		return this.currentGameState;
	}

	public void setCurrentGameState(GameState gameState) {
		this.currentGameState = gameState;
	}

	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}

	public ComputerPlayer getComputerPlayer() {
		return computerPlayer;
	}

	public GameState getNewGameState() {
		return newGameState;
	}

	public GameState getStartedGameState() {
		return StartedGameState;
	}

	public void start() {
		this.currentGameState.start();
	}

	public void newGame() {
		this.currentGameState.newGame();
	}

	public BerekenScore getScoreHumanPlayer() {
		return this.scoreHumanPlayer;
	}

	public BerekenScore getScoreComputerPlayer() {
		return this.scoreComputerPlayer;
	}

}
