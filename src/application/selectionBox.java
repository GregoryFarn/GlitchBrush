package application;


import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*selectionBox is only a visual lined box that defines the outline of the selected area,
 * does not have any filtering capabilities.
 */
public class selectionBox {
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private double topY;
	private double topX;
	private double bottomX;
	private double bottomY;
	// filterType 0 = None, 1 = Selection Sort, 2 = HeapSort, 3 = Scramble, 4 =
	// VertFlip, 5 = HorizFlip
	private int filterType;
	private Canvas cs;
	private Rectangle rect;
	private photo p;

	public selectionBox(StackPane root, Scene scene, photo p) {
		this.p = p;
		filterType = 0;
		rect = new Rectangle(0, 0, 0, 0);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.TRANSPARENT);
		rect.setStrokeWidth(1);
		rect.getStrokeDashArray().addAll(2d);
		cs = new Canvas(p.getWidth(), p.getHeight());
		cs.setManaged(false);
		cs.setLayoutX(p.getX());
		cs.setLayoutY(p.getY());
		rect.setManaged(false);
		root.getChildren().add(rect);
		root.getChildren().add(cs);
		endX = -1;
		endY = -1;
		cs.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
				rect.setLayoutX(startX + p.getX());
				rect.setLayoutY(startY + p.getY());
				rect.setHeight(0);
				rect.setWidth(0);
				rect.setStroke(Color.GREY);
			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				double x = e.getX();
				double y = e.getY();
				double pw = p.getWidth();
				double ph = p.getHeight();
				if (x < 0) {
					if (y < 0) {
						updateSize(0,0);
					}
					else if(y>ph) {
						updateSize(0,ph);
					}else {
						updateSize(0,y);
					}
				}else if(x>pw) {
					if (y < 0) {
						updateSize(pw,0);
					}
					else if(y>ph) {
						updateSize(pw,ph);
					}else {
						updateSize(pw,y);
					}
				}else {
					if (y < 0) {
						updateSize(x,0);
					}
					else if(y>ph) {
						updateSize(x,ph);
					}else {
						updateSize(x,y);
					}
				}
			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				pixelSort();
			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
		            public void handle(MouseEvent e) {
		            }
		        });

	}

	public void updateSize(double x, double y) {
		if (startX > x) {
			if (startY > y) {
				topX = x;
				topY = y;
				bottomX = startX;
				bottomY = startY;
				rect.setLayoutX(x + p.getX());
				rect.setLayoutY(y + p.getY());
				rect.setWidth(startX - x);
				rect.setHeight(startY - y);
				endX = startX;
				endY = startY;
			} else {
				topX = x;
				topY = startY;
				bottomX = startX;
				bottomY = y;
				rect.setLayoutX(x + p.getX());
				rect.setWidth(startX - x);
				rect.setHeight(y - startY);
				endX = startX;
				endY = y;

			}
		} else {
			if (startY > y) {
				topX = startX;
				topY = y;
				bottomX = x;
				bottomY = startY;
				rect.setLayoutY(y + p.getY());
				rect.setWidth(x - startX);
				rect.setHeight(startY - y);
				endY = startY;
				endX = x;
			} else {
				topX = startX;
				topY = startY;
				bottomX = x;
				bottomY = y;
				rect.setLayoutX(startX + p.getX());
				rect.setLayoutY(startY + p.getY());
				rect.setHeight(y - startY);
				rect.setWidth(x - startX);
				endX = x;
				endY = y;
			}
		}
	}

	public double getStartX() {
		return startX;
	}

	public double getStartY() {
		return startY;
	}

	public Canvas getCanvas() {
		return cs;
	}

	public double getEndX() {
		return endX;
	}

	public double getEndY() {

		return endY;
	}

	public double getTopX() {

		return topX;
	}

	public double getTopY() {

		return topY;
	}

	public double getBottomX() {

		return bottomX;
	}

	public double getBottomY() {

		return bottomY;
	}

	// filterType 0 = None, 1 = Selection Sort, 2 = HeapSort, 3 = Scramble, 4 =
	// VertFlip, 5 = HorizFlip
	public void setType(int type) {
		this.filterType = type;
	}

	public void pixelSort() {
		if (filterType == 1) {
			pixelSort.pSort(this, p);
		} else if (filterType == 2) {
			pixelSort.colorHeap(this, p);
		} else if (filterType == 3) {
			pixelSort.colorLinked(this, p);
		} else if (filterType == 4) {
			pixelSort.colorFlipsUp(this, p);
		} else if (filterType == 5) {
			pixelSort.colorFlipsSide(this, p);
		} else if (filterType == 6) {
			pixelSort.colorHash(this, p);
		}
		else {
		}
	}
	public void toFront() {
		rect.toFront();
		cs.toFront();
	}
}
