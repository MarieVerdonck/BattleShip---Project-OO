package Controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import model.BattleShipGameModel;
import model.DomainException;
import view.BattleShipGameView;
/**
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 */
public class GameController {
	private BattleShipGameModel model;
	private BattleShipGameView view;

	public GameController() {
		this.setUpGame();
	}

	public void setUpGame() {
		view = new BattleShipGameView();
		model = new BattleShipGameModel(view.getPlayerName());
		view.getGameBoardPanel1().addMouseClickListener(new MouseClickHandler());
		view.getGameFrame().addMouseClickListenerToStartButton(new StartButtonHandler());
		view.getGameFrame().addMouseClickListenerToInstellingenButton(new InstellingenHandler());
	}

	private class InstellingenHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isInstellingenButtonEnabled()) {
				view.disableInstellingenButton();
				view.openInstellingenJFrame();
			}
		}
	}

	private class StartButtonHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isStartButtonEnabled()) {
				try {
					model.startGame();
					model.readHitStrategyFromProp();
					model.readPlacesShipStrategyFromProp();
					computerGenerateShips();
					if (view.getInstellingenFrame() != null && view.moetenSchepenzichtbaar()) {
						showGeneratedShips();
					}
					view.disableStartButton();
					view.disableGameBoardJPanel1();
					view.getGameBoardPanel2().addMouseClickListener(new ShootClickHandler());
					updateNameFieldComputer();
					updateNamefieldHuman();
					if (view.isInstellingenButtonEnabled()) {
						view.disableInstellingenButton();
					}
				} catch (Exception e) {
					view.showError(e.getMessage());
				}
			}
		}
	}

	private class ShootClickHandler extends MouseAdapter {

		ArrayList<Integer> schepenCijfersComputer = (ArrayList<Integer>) model.getAllComputerShipNummers();
		ArrayList<Integer> schepenCijfersHumanPlayer = (ArrayList<Integer>) model.getAllHumanPlayerShipNummers();

		public void mouseClicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			int beginNummer = -1;

			for (int i = 0; i < view.getGameBoard2Size(); i++) {
				if (view.isAangekliktInGameboard2(i, x, y)) {
					beginNummer = i;
					if (!view.getVierkantBezetGameBoardPanel2(beginNummer)) {
						view.setVierkantGameBoardPanel2Bezet(beginNummer);
						if (schepenCijfersComputer.contains(beginNummer)) {
							if (model.addHitNumberToComputerShip(beginNummer)) {
								for (Integer integer : model.allNumbersfDestroyedShipsOfComputer()) {
									view.kleurShipGameBoardPanel2(integer, Color.RED);
								}
							} else {
								view.kleurShipGameBoardPanel2(beginNummer, Color.YELLOW);
							}

						} else {
							view.kleurShipGameBoardPanel2(beginNummer, Color.BLUE);
						}
						updateNamefieldHuman();
						if (model.getIfGameOverComputer()) {
							view.showMessage("Game over!\n" + model.getHumanPlayerName() + " won met "
									+ model.getHumanPlayerScore() + " punten...");
							endGame();
						} else {
							this.computerShoots();
						}
						break;
					}
				}
			}

		}

		public void computerShoots() {
			int shot = model.getComputerShot();

			if (schepenCijfersHumanPlayer.contains(shot)) {
				if (model.addHitNumberToHumanPlayerShip(shot)) {
					for (Integer integer : model.allNumbersfDestroyedShipsOfHumanPlayer()) {
						view.kleurShipGameBoardPanel1(integer, Color.RED);
					}
				} else {
					view.kleurShipGameBoardPanel1(shot, Color.YELLOW);
				}
			} else {
				view.kleurShipGameBoardPanel1(shot, Color.BLUE);
			}
			updateNameFieldComputer();
			if (model.getIfGameOverHumanPlayer()) {
				view.showMessage("Game over!\n" + model.getComputerPlayerName() + " won met "
						+ model.getComputerPlayerName() + " punten...");
				endGame();
			}
		}

	}

	public void updateNamefieldHuman() {
		view.updateNameFieldHuman(model.getHumanPlayerName() + " (" + model.getHumanPlayerScore() + "):");
	}

	public void updateNameFieldComputer() {
		view.updateNameFieldComputer(model.getComputerPlayerName() + " (" + model.getComputerPlayerScore() + "):");
	}

	public void endGame() {
		view.closeApplication();
		this.setUpGame();
	}

	private class MouseClickHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (view.isGameBoardJPanel1Enabled()) {

				int x = event.getX();
				int y = event.getY();
				int beginNummer = -1;
				for (int i = 0; i < view.getGameBoard1Size(); i++) {
					if (view.isAangekliktInGameboard1(i, x, y)) {
						beginNummer = i;
						try {
							model.addShipToHumanPlayer(view.getSelectedShipType(), view.getSelectedRichting(),
									beginNummer);
							for (Integer schipnummer : model.getLastAddedShipToHumanPlayer().getSchipNummers()) {
								view.kleurShipGameBoardPanel1(schipnummer, Color.WHITE);
							}
						} catch (DomainException e) {
							view.showError(e.getMessage());
						}
						break;
					}
				}
			}
		}
	}

	private void computerGenerateShips() {
		model.computerGenerateShips();
	}

	private void showGeneratedShips() {
		for (Integer nummer : model.getAllComputerShipNummers()) {
			view.kleurShipGameBoardPanel2(nummer, Color.WHITE);
		}
	}

}
