package application;

import java.util.Comparator;

import javafx.scene.paint.Color;

public class colorSort implements Comparator<Color> {
	@Override
	public int compare(Color c1, Color c2) {
		if (c1.getHue() > c1.getHue()) {
			return 1;
		} else if (c1.getHue() < c1.getHue()) {
			return -1;
		} else {
			return 0;
		}
	}

}
