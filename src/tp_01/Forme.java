package tp_01;

import java.util.Vector;

public class Forme{
	
	private Vector <TraceLibre> forme;
	
	public Forme()
	{
		forme = new Vector <TraceLibre> ();
		
	}
	
	public void ajoutForme(TraceLibre vectorPoints)
	{
		forme.add(vectorPoints);
		
		
	}

	public Vector<TraceLibre> getForme() {
		return forme;
	}

	public void setForme(Vector<TraceLibre> forme) {
		this.forme = forme;
	}


	
}