package tp_01;

//Class de TraceLibre servant de class abstraite pour le dessin
//Les autre class de forme seront extends de celle-ci et n'attriburont uniquement le type de forme.

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

public abstract class TraceLibre {
	
	private Vector<Point> TabPoints;
	private int taille;
	private Color couleur;
	private int formeType;
	
	public TraceLibre()
	{
		TabPoints = new Vector <Point> ();	
	}
	
	public void ajoutPoint(Point point , int t , Color c)
	{
		TabPoints.add(point);
		taille = t;
		couleur = c;
	}

	public Vector<Point> getTabPoints() {
		return TabPoints;
	}

	public void setTabPoints(Vector<Point> tabPoints) {
		TabPoints = tabPoints;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getFormeType() {
		return formeType;
	}

	public void setFormeType(int formeType) {
		this.formeType = formeType;
	}
	

	

	
	
}
