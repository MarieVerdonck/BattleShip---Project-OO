package model.Observer;

import java.util.Observable;
import java.util.Observer;

import model.ComputerPlayer;
import model.HumanPlayer;

/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class BerekenScore implements Observer {
	private int score = 0;
	@SuppressWarnings("unused")
	private Observable observable;

	public BerekenScore(Observable observable) {
		this.setScore(19);
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof HumanPlayer || o instanceof ComputerPlayer) {
			this.setScore(score - 1);
		}

	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
