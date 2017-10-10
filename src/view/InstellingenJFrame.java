package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.jar.JarOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.DomainException;
import model.Strategy.HitStrategy;
import model.Strategy.PlaceStrategy;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
@SuppressWarnings("serial")
public class InstellingenJFrame extends JFrame {

	private JButton confirmButton;

	private JComboBox<HitStrategy> hitComboBox;
	private JComboBox<PlaceStrategy> placeComboBox;
	private JComboBox<String> schepenZichtbaarBox;

	private JLabel hitLabel;
	private JLabel placeLabel;
	private JLabel schepenZichtbaarLabel;

	private boolean schepenzichtbaar = false;

	public final static int HEIGHT_FRAME = 500;
	public final static int WIDTH_FRAME = 500;

	public InstellingenJFrame() {
		super();
		this.launch();
	}

	public void launch() {
		this.DefaultWaardenNaarProperties();
		this.setSize(WIDTH_FRAME, HEIGHT_FRAME);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("Instellingen");

		this.completeLabelHitStrategy();
		this.completeBoxHitStrategy();
		this.completeConfirmButton();

		this.completeBoxPlaceStrategy();
		this.completeLabelPlaceStrategy();

		this.completeSchepenZichtbaarLabel();
		this.completeSchepenZichbaarBox();

		this.addActionListenerToHitComboBox();
		this.addActionListenerToPlaceCombobox();
		this.addActionListenerToZichtbaarheidComboBox();
		this.addActionListenerTopConfirmButton();
	}

	private void completeConfirmButton() {
		this.confirmButton = new JButton("Bevestig");
		this.confirmButton.setLocation(25, 400);
		this.confirmButton.setSize(new Dimension(450, 40));
		this.add(confirmButton);
	}

	private void completeBoxHitStrategy() {
		this.hitComboBox = new JComboBox<>(HitStrategy.values());
		this.hitComboBox.setLocation(25, 120);
		this.hitComboBox.setSize(new Dimension(450, 40));
		this.add(hitComboBox);
	}

	private void completeLabelHitStrategy() {
		this.hitLabel = new JLabel("Kies een schiet strategie:");
		this.hitLabel.setLocation(25, 75);
		this.hitLabel.setSize(new Dimension(450, 40));
		this.add(hitLabel);
	}

	private void addActionListenerToHitComboBox() {
		this.hitComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writeHitToProperties(((HitStrategy) hitComboBox.getSelectedItem()).getFullClassName());
			}
		});
	}

	private void completeBoxPlaceStrategy() {
		this.placeComboBox = new JComboBox<>(PlaceStrategy.values());
		this.placeComboBox.setLocation(25, 200);
		this.placeComboBox.setSize(new Dimension(450, 40));
		this.add(placeComboBox);
	}

	private void completeLabelPlaceStrategy() {
		this.placeLabel = new JLabel("Kies een plaats schepen strategie:");
		this.placeLabel.setLocation(25, 160);
		this.placeLabel.setSize(new Dimension(450, 40));
		this.add(placeLabel);
	}

	private void addActionListenerToPlaceCombobox() {
		this.placeComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				writePlaceToProperties(((PlaceStrategy) placeComboBox.getSelectedItem()).getFullClassName());
			}
		});
	}

	private void addActionListenerTopConfirmButton() {
		this.confirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
	}

	private void completeSchepenZichtbaarLabel() {
		this.schepenZichtbaarLabel = new JLabel("Moeten de computer schepen zichtbaar zijn?:");
		this.schepenZichtbaarLabel.setLocation(25, 250);
		this.schepenZichtbaarLabel.setSize(new Dimension(450, 40));
		this.add(schepenZichtbaarLabel);
	}

	private void completeSchepenZichbaarBox() {
		String[] mogelijkheden = { "NEEN", "JA" };
		this.schepenZichtbaarBox = new JComboBox<>(mogelijkheden);
		this.schepenZichtbaarBox.setLocation(25, 290);
		this.schepenZichtbaarBox.setSize(new Dimension(450, 40));
		this.add(schepenZichtbaarBox);
	}

	private void addActionListenerToZichtbaarheidComboBox() {
		this.schepenZichtbaarBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = (String) schepenZichtbaarBox.getSelectedItem();
				if (selected.equals("JA")) {
					schepenzichtbaar = true;
				} else {
					schepenzichtbaar = false;
				}
			}
		});
	}

	public boolean schepenZichtbaar() {
		return this.schepenzichtbaar;
	}

	private void DefaultWaardenNaarProperties() {
		this.writeHitToProperties(HitStrategy.RANDOM.getFullClassName());
		this.writePlaceToProperties(PlaceStrategy.RANDOM.getFullClassName());
	}

	public void writeHitToProperties(String hitStrategy) {
		FileInputStream in = null;
		try {
			in = new FileInputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties niet gevonden! (Instellingen)");
		}
		Properties props = new Properties();
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Kon properties niet laden (Instellingen)");
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties niet gevonden! (Instellingen)");
		}
		props.setProperty("hitShipStrategy", hitStrategy);
		try {
			props.store(out, null);
			out.close();
		} catch (IOException e) {
			System.out.println("Kon properties niet opslaan (Instellingen)");
		}

	}

	public void writePlaceToProperties(String placeShipStrategy) {
		FileInputStream in = null;
		try {
			in = new FileInputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties niet gevonden! (Instellingen)");
		}
		Properties props = new Properties();
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			System.out.println("Kon properties niet laden (Instellingen)");
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream("src/StrategyProperties.properties");
		} catch (FileNotFoundException e) {
			System.out.println("Properties niet gevonden! (Instellingen)");
		}
		props.setProperty("placeShipStrategy", placeShipStrategy);
		try {
			props.store(out, null);
			out.close();
		} catch (IOException e) {
			System.out.println("Kon properties niet opslaan (Instellingen)");
		}
	}

}
