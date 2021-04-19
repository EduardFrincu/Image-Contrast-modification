import java.awt.Color;

public class Buffer {
	
	private Color colors[][] = new Color [Main.myImage.getHeight()][Main.myImage.getWidth()];
	private boolean available = false;
	
	//metoda folosita pentru a prelua din Buffer in Consumer
	public synchronized Color get(int i, int j){
		while (!available ) {
			try {
				wait ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		
		available = false;
		notifyAll();
		return colors[i][j];

}
	//metoda folosita pentru a transfera continut din Producer in Buffer
	public synchronized void put(Color c, int i, int j){
		while ( available ) {
			try {
				wait ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		
		this.colors[i][j] = c;
		available = true;
		notifyAll();
	}
	

}
