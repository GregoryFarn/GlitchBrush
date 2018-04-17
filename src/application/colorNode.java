package application;

import javafx.scene.paint.Color;

public class colorNode {
	@SuppressWarnings("unused")
	private colorNode head;
	private colorNode tail;
	private Color c;

	public colorNode(colorNode head, colorNode tail, Color c) {
		this.head = head;
		this.tail = tail;
		this.c = c;
	}

	public void setHead(colorNode head) {
		this.head = head;
	}

	public void setTail(colorNode tail) {
		this.tail = tail;
	}

	public void add(colorNode x) {
		this.setTail(x);
		x.setHead(this);
	}
	public Color getColor() {
		return this.c;
	}
	public colorNode getTail() {
		return tail;
	}
}
