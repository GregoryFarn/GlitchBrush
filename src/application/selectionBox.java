package application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
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
	private Canvas cs;
	private Rectangle rect;
	private photo p;

	public selectionBox(Group root, Scene scene, photo p) {
		this.p = p;
		rect = new Rectangle(0, 0, 0, 0);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.TRANSPARENT);
		rect.setStrokeWidth(1);
		rect.getStrokeDashArray().addAll(2d);
		cs = new Canvas(p.getWidth(), p.getHeight());
		cs.setLayoutX(p.getX());
		cs.setLayoutY(p.getY());

		root.getChildren().add(rect);
		root.getChildren().add(cs);
		endX = -1;
		endY = -1;
		cs.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				startX = e.getX();
				startY = e.getY();
				rect.setX(startX + p.getX());
				rect.setY(startY + p.getY());
				rect.setHeight(0);
				rect.setWidth(0);
				rect.setStroke(Color.GREY);
			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				updateSize(e.getX(), e.getY());
			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				pixelSort();
			}
		});

	}

	public void updateSize(double x, double y) {
		if (startX > x) {
			if (startY > y) {
				topX=x;
				topY= y;
				bottomX = startX;
				bottomY = startY;
				rect.setX(x + p.getX());
				rect.setY(y + p.getY());
				rect.setWidth(startX - x);
				rect.setHeight(startY - y);
				endX = startX;
				endY = startY;

			} else {
				topX=x;
				topY= startY;
				bottomX = startX;
				bottomY = y;
				rect.setX(x + p.getX());
				rect.setWidth(startX - x);
				rect.setHeight(y - startY);
				endX = startX;
				endY = y;
			}
		} else {
			if (startY > y) {
				topX=startX;
				topY= y;
				bottomX = x;
				bottomY = startY;
				rect.setY(y + p.getY());
				rect.setWidth(x - startX);
				rect.setHeight(startY - y);
				endY = startY;
				endX = x;
			} else {
				topX=startX;
				topY= startY;
				bottomX = x;
				bottomY = y;
				rect.setX(startX + p.getX());
				rect.setY(startY + p.getY());
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
	

	public Canvas getCanvas() {
		return cs;
	}

	public void pixelSort() {
		pixelSort.pSort(this, p);
	}
}
