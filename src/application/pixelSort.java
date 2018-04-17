package application;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;
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

	public static void colorHash(selectionBox sb, photo p) {
		for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
			colorHashTable cht = new colorHashTable((int) sb.getBottomY() - (int) sb.getTopY());
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				cht.put(j *13 , p.getPR().getColor(i, j));
			}
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				p.getWI().getPixelWriter().setColor(i, j, cht.get(j - (int) sb.getTopY()));
			}

		}

		p.resetReader();

	}

	public static void colorFlipsUp(selectionBox sb, photo p) {
		for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
			Stack<Color> sc= new Stack<Color>();
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				sc.push(p.getPR().getColor(i, j));
			}
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				p.getWI().getPixelWriter().setColor(i, j, sc.pop());
			}

		}

		p.resetReader();

	}
	public static void colorFlipsSide(selectionBox sb, photo p) {
		for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
			Stack<Color> sc= new Stack<Color>();
			for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
				sc.push(p.getPR().getColor(i, j));
			}
			for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
				p.getWI().getPixelWriter().setColor(i,j, sc.pop());
			}

		}

		p.resetReader();

	}

	public static void colorLinked(selectionBox sb, photo p) {
		colorNode head = null;
		colorNode first=null;
		colorNode tail=null;
		for (int i = (int) sb.getTopX(); i < (int) sb.getBottomX(); i++) {
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				if(j ==(int) sb.getTopY()) {
					first = new colorNode(null,null,p.getPR().getColor(i,j));
					head = first;
				}
				else if(j ==(int) sb.getBottomY()-1) {
					tail = new colorNode(null,null,p.getPR().getColor(i,j));
					head.add(tail);
				}
				else {
					colorNode temp =new colorNode(null,null,p.getPR().getColor(i,j));
					head.add(temp);
					head =temp;
				}
			}
			Random rand = new Random();
			int a = rand.nextInt((int)sb.getBottomY()-(int)sb.getTopY());
			head = first;
			for (int j =0; j<a; j++) {
				head=head.getTail();
			}
			tail.add(first);
			
			for (int j = (int) sb.getTopY(); j < (int) sb.getBottomY(); j++) {
				p.getWI().getPixelWriter().setColor(i, j,head.getColor() );
				head = head.getTail();
			}

		}

		p.resetReader();
	}
}
