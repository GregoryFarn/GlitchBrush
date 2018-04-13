package application;

import java.util.Vector;

import javafx.scene.paint.Color;

public class pixelSort {

	public static void pSort(selectionBox sb, photo p) {
		for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
			Vector<Color> vc = new Vector<Color>();
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				vc.add(p.getPR().getColor(i, j));
			}
			sortVC(vc);
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				p.getWI().getPixelWriter().setColor(i, j, vc.get(j - (int) sb.getTopY()));
			}
		}
		p.resetReader();
	}

	// TODO IMPLEMENT MERGE SORT
	public static void sortVC(Vector<Color> vc) {
		int min;
		for (int i = 0; i < vc.size(); i++) {
			min = i;
			for (int j = i; j < vc.size(); j++) {
				if (vc.get(j).getBrightness() < vc.get(min).getBrightness()) {
					min = j;
				}
			}
			Color temp = vc.get(min);
			vc.set(min, vc.get(i));
			vc.set(i, temp);
		}
	}

}
