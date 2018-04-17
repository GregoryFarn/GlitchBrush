package application;

import javafx.scene.paint.Color;
public class colorHashTable {
	private Color[] vc;
	private int size;
	public colorHashTable(int size) {
		this.size = size;
		vc = new Color[size];
		for(int i =0; i<size; i++) {
			vc[i] = Color.WHITE;
		}
	}
	public void put(int i, Color c) {
		if(vc[i%(size)].equals(Color.WHITE)) {
		vc[i%(size)] = c;
		}
		else {
			int x =i+1;
			while(!vc[x%(size-1)].equals(Color.WHITE)) {
				x++;
			}
			vc[x%(size)] = c;
		}
	}
	public Color get(int i) {
		return vc[i];
	}
}
