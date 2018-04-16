package application;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;
import javafx.scene.paint.Color;

public class pixelSort {

	// SELECTION SORT the PIXELS
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

	// SELECTION SORT METHOD
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

	// COMPARATOR FOR PRIORITY QUEUE
	public static Comparator<Color> cc = new Comparator<Color>() {
		public int compare(Color c1, Color c2) {
			if (c1.getBrightness() > c2.getBrightness()) {
				return 1;
			} else if (c1.getBrightness() < c2.getBrightness()) {
				return -1;
			} else {
				return 0;
			}
		}
	};

	// PRIORITY QUEUE SORT THE PIXELS
	public static void colorHeap(selectionBox sb, photo p) {
		PriorityQueue<Color> pq = new PriorityQueue<Color>(1, cc);
		for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				pq.add(p.getPR().getColor(i, j));
			}
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				p.getWI().getPixelWriter().setColor(i, j, pq.poll());
			}
		}
		p.resetReader();
	}

}
