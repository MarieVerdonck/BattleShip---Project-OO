package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Direction;
import model.ShipType;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class BattleShipGameView {
	private GameFrame gameFrame;
	private InstellingenJFrame instellingenJFrame;
	private String playerName;

	public BattleShipGameView() {
		gameFrame = new GameFrame();
		gameFrame.launch(this.askPlayerName());
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	public String askPlayerName() {
		playerName = JOptionPane.showInputDialog("Wat uw naam?");

		return playerName;
	}

	public void openInstellingenJFrame() {
		instellingenJFrame = new InstellingenJFrame();
		instellingenJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instellingenJFrame.setVisible(true);
	}

	public String getPlayerName() {
		return playerName;
	}

	public GameBoardJPanel getGameBoardPanel1() {
		return this.getGameFrame().getSpelBordJPanel1();
	}

	public GameBoardJPanel getGameBoardPanel2() {
		return this.getGameFrame().getSpelBordJPanel2();
	}

	public int getGameBoard1Size() {
		return this.getGameBoardPanel1().getVierkanten().size();
	}

	public int getGameBoard2Size() {
		return this.getGameBoardPanel2().getVierkanten().size();
	}

	public boolean isAangekliktInGameboard2(int i, int x, int y) {
		return this.getGameBoardPanel2().getVierkanten().get(i).isAangeklikt(x, y);
	}

	public boolean isAangekliktInGameboard1(int i, int x, int y) {
		return this.getGameBoardPanel1().getVierkanten().get(i).isAangeklikt(x, y);
	}

	public void kleurShipGameBoardPanel1(int nummer, Color kleur) {
		this.getGameBoardPanel1().setKleur(nummer, kleur);
	}

	public void kleurShipGameBoardPanel2(int nummer, Color kleur) {
		this.getGameBoardPanel2().setKleur(nummer, kleur);
	}

	public boolean getVierkantBezetGameBoardPanel1(int nummer) {
		return this.getGameBoardPanel1().getVierkanten().get(nummer).getBezet();
	}

	public boolean getVierkantBezetGameBoardPanel2(int nummer) {
		return this.getGameBoardPanel2().getVierkanten().get(nummer).getBezet();
	}

	public void setVierkantGameBoardPanel2Bezet(int nummer) {
		this.getGameBoardPanel2().getVierkanten().get(nummer).setBezet();
	}

	public ShipType getSelectedShipType() {
		return this.getGameFrame().getSchipBordJPanel().getSelectedSchiptype();
	}

	public Direction getSelectedRichting() {
		return this.getGameFrame().getSchipBordJPanel().getSelectedRichting();
	}

	public GameFrame getGameFrame() {
		return gameFrame;
	}

	public void showError(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void disableStartButton() {
		this.getGameFrame().disableStartButton();
	}

	public boolean isStartButtonEnabled() {
		return this.getGameFrame().isStartButtonEnabled();
	}

	public void disableInstellingenButton() {
		this.getGameFrame().disableInstellingenButton();
	}

	public boolean isInstellingenButtonEnabled() {
		return this.getGameFrame().isInstellingenButtonEnabled();
	}

	public void disableGameBoardJPanel1() {
		this.getGameFrame().disableGameBoardJPanel1();
	}

	public boolean isGameBoardJPanel1Enabled() {
		return this.getGameFrame().isGameBoardJPanel1Enabled();
	}

	public void updateNameFieldHuman(String n) {
		this.getGameFrame().updateNameFieldHuman(n);
	}

	public void updateNameFieldComputer(String n) {
		this.getGameFrame().updateNameFieldComputer(n);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void closeApplication() {
		this.getGameFrame().closeApplication();
	}

	public boolean moetenSchepenzichtbaar() {
		return this.instellingenJFrame.schepenZichtbaar();
	}

	public InstellingenJFrame getInstellingenFrame() {
		return this.instellingenJFrame;
	}

}
