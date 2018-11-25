package bomberman.model;

import java.awt.Image;

public class Figure {
	protected int x, y, width, height, count;
	protected Image img;
	
	public Figure(int x, int y, int count, int width, int height, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.count = count;
		this.width = width;
		this.height = height;
		this.img = img;
	}

	public Figure() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
