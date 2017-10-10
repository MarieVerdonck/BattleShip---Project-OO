package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * 
 * @author Dries Janse, Marie Verdonck, Bram Van Asschodt
 *
 */
public class Vierkant {

	private int x, y, zijde;
	private Color kleur;
	public boolean bezet;
	private Color borderColor;

	public Vierkant(int x, int y, int zijde, Color kleur) {
		super();
		this.x = x;
		this.y = y;
		this.zijde = zijde;
		this.kleur = kleur;
		bezet = false;
		borderColor = Color.BLACK;
	}

	public Color getKleur() {
		return kleur;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public void setKleur(Color kleur) {
		this.kleur = kleur;
	}

	public boolean getBezet() {
		return bezet;
	}

	public void paint(Graphics g) {
		g.setColor(kleur);
		g.fillRect(x, y, zijde, zijde);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		g.setColor(borderColor);
		g.drawRect(x, y, zijde, zijde);
	}

	public boolean isAangeklikt(int x, int y) {
		boolean aangeklikt = this.x <= x && this.x + zijde >= x && this.y <= y && this.y + zijde >= y;
		return aangeklikt;
	}

	public void setBezet() {
		this.bezet = true;
	}

}

