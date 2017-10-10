package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
@SuppressWarnings("serial")
public class GameBoardJPanel extends JPanel {
	private List<Vierkant> vierkanten = new ArrayList<Vierkant>();

	public GameBoardJPanel(int zijde, int aantal) {
		int y = 0;
		int x = 0;
		for (int i = 0; i < aantal; i++) {
			for (int j = 0; j < aantal; j++) {
				vierkanten.add(new Vierkant(x + zijde * i, y + zijde * j, zijde, Color.LIGHT_GRAY));
			}
		}

	}

	public void addMouseClickListener(MouseListener listener) {
		this.addMouseListener(listener);
	}

	public List<Vierkant> getVierkanten() {
		return vierkanten;
	}

	public void setKleur(int nr, Color kleur) {
		vierkanten.get(nr).setBorderColor(kleur);
		vierkanten.get(nr).setKleur(kleur);
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		for (Vierkant vierkant : vierkanten) {
			vierkant.paint(g);
		}
	}
}
