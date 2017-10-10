package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private GameBoardJPanel panel1;
	private GameBoardJPanel panel2;
	private JPanel naamPanel1 = new JPanel();
	private JPanel naamPanel2 = new JPanel();
	private ShipBoardJPanel schipBordJPanel;
	private JButton startButton;

	private JButton instellingenButton;

	private JLabel naam1Label;
	private JLabel naam2Label;

	public final static int HEIGHT_FRAME = 500; // At least 400
	public final static int WIDTH_FRAME = (int) (HEIGHT_FRAME * 2.5);
	public final static int PANEL_SIZE = (int) (HEIGHT_FRAME * 4 / 5);
	public final static int AANTAL_RIJEN = 10;
	public boolean tryToStart = false;

	public GameFrame() {
		super();
	}

	public void launch(String naam) {
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Zeeslag");

		this.completeSettingsShipBordJPanel();

		this.completeSettingsNaamPanel1(naam);
		this.completeSettingsNaamPanel2();

		this.completeSettingsSpelBordPanel1();
		this.completeSettingsSpelBordPanel2();

		this.setStartButton();
		this.setInstellingenButton();
	}

	private void setInstellingenButton() {
		this.instellingenButton = new JButton("Instellingen");
		this.instellingenButton.setLocation(25, PANEL_SIZE - 10);
		this.instellingenButton.setSize(new Dimension(350, 40));
		this.add(this.instellingenButton);
	}

	private void setStartButton() {
		this.startButton = new JButton("Start");
		this.startButton.setLocation(25, PANEL_SIZE - 50);
		this.startButton.setSize(new Dimension(350, 40));
		this.add(startButton);

	}

	public void addMouseClickListenerToStartButton(MouseListener listener) {
		this.startButton.addMouseListener(listener);
	}

	public void addMouseClickListenerToInstellingenButton(MouseListener listener) {
		this.instellingenButton.addMouseListener(listener);
	}

	private void completeSettingsShipBordJPanel() {
		this.schipBordJPanel = new ShipBoardJPanel();
		this.schipBordJPanel.setSize(new Dimension(PANEL_SIZE - 25, PANEL_SIZE / 2));
		this.schipBordJPanel.setLocation(25, 50);
		this.add(schipBordJPanel);
	}

	private void completeSettingsSpelBordPanel1() {
		panel1 = new GameBoardJPanel((PANEL_SIZE / AANTAL_RIJEN), AANTAL_RIJEN);
		panel1.setBackground(Color.GRAY);
		panel1.setSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		panel1.setLocation(PANEL_SIZE, 50);
		panel1.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(panel1);
	}


	private void completeSettingsSpelBordPanel2() {
		panel2 = new GameBoardJPanel((PANEL_SIZE / AANTAL_RIJEN), AANTAL_RIJEN);
		panel2.setBackground(Color.GRAY);
		panel2.setSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
		panel2.setLocation(2 * PANEL_SIZE + 25, 50);
		panel2.setBorder(BorderFactory.createLoweredBevelBorder());
		this.add(panel2);
	}


	public GameBoardJPanel getSpelBordJPanel1() {
		return panel1;
	}


	public GameBoardJPanel getSpelBordJPanel2() {
		return panel2;
	}


	public ShipBoardJPanel getSchipBordJPanel() {
		return schipBordJPanel;
	}


	public void completeSettingsNaamPanel1(String naam) {
		String name = naam;
		if (naam == null || naam.isEmpty()) {
			name = "Player1";
		}
		this.naam1Label = new JLabel(name + ":", SwingConstants.LEFT);
		naamPanel1.add(naam1Label);
		naamPanel1.setSize(new Dimension(100, 25));
		naamPanel1.setLocation(PANEL_SIZE, 25);
		this.add(naamPanel1);
	}

	public void updateNameFieldHuman(String n) {
		this.naam1Label.setText(n);
	}

	public void updateNameFieldComputer(String n) {
		this.naam2Label.setText(n);
	}

	public void completeSettingsNaamPanel2() {
		this.naam2Label = new JLabel("Computer:");
		naamPanel2.add(naam2Label);
		naamPanel2.setSize(new Dimension(100, 25));
		naamPanel2.setLocation(2 * PANEL_SIZE + 25, 25);

		this.add(naamPanel2);
	}

	public void disableStartButton() {
		this.startButton.setEnabled(false);
	}

	public void disableInstellingenButton() {
		this.instellingenButton.setEnabled(false);
	}

	public boolean isStartButtonEnabled() {
		return this.startButton.isEnabled();
	}

	public boolean isInstellingenButtonEnabled() {
		return this.instellingenButton.isEnabled();
	}

	public void disableGameBoardJPanel1() {
		this.getSpelBordJPanel1().setEnabled(false);
	}

	public boolean isGameBoardJPanel1Enabled() {
		return this.getSpelBordJPanel1().isEnabled();
	}

	public void closeApplication() {
		setVisible(false);
		dispose();
	}

}
