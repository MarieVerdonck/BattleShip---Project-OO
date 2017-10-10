package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Direction;
import model.ShipType;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
@SuppressWarnings("serial")
public class ShipBoardJPanel extends JPanel {

	private JLabel schepenLabel;
	private JComboBox<Object> mogelijkeSchepenBox;
	private JLabel richtingLabel;
	private JRadioButton vertikaalButton;
	private JRadioButton horizontaalButton;
	private ButtonGroup group;

	public ShipType selectedSchiptype = ShipType.VLIEGDEKSCHIP;
	public Direction schipRichting = Direction.VERTIKAAL;

	public ShipBoardJPanel() {
		this.schepenLabel = new JLabel("Beschikbare schepen:");
		List<ShipType> listSchiptypes = ShipType.getAllSchiptypes();
		this.mogelijkeSchepenBox = new JComboBox<>(listSchiptypes.toArray());
		this.richtingLabel = new JLabel("Richting:");
		this.vertikaalButton = new JRadioButton("Vertikaal", true);
		this.horizontaalButton = new JRadioButton("Horizontaal");

		this.group = new ButtonGroup();
		this.group.add(horizontaalButton);
		this.group.add(vertikaalButton);

		mogelijkeSchepenBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedSchiptype = (ShipType) mogelijkeSchepenBox.getSelectedItem();
			}
		});

		vertikaalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schipRichting = Direction.VERTIKAAL;
			}
		});

		horizontaalButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				schipRichting = Direction.HORIZONTAAL;
			}
		});

		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		this.add(schepenLabel, c);

		c.gridx = 0;
		c.gridy = 1;

		this.add(mogelijkeSchepenBox, c);

		c.gridx = 0;
		c.gridy = 2;
		this.add(this.richtingLabel, c);

		c.gridy = 3;
		this.add(horizontaalButton, c);
		c.gridx = 1;
		c.gridy = 3;
		this.add(vertikaalButton, c);

	}

	/**
	 * Geeft het geselecteerde schiptype terug
	 * 
	 * @return Schiptype selectedSchiptype
	 */
	public ShipType getSelectedSchiptype() {
		return this.selectedSchiptype;
	}

	/**
	 * Geeft de geselecteerde richting terug
	 * 
	 * @return boolean schipHorizontaal
	 */
	public Direction getSelectedRichting() {
		return this.schipRichting;
	}

}
