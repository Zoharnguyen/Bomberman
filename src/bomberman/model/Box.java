package bomberman.model;

import java.awt.Image;

public class Box extends Figure{
	public Box(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.height = img.getHeight(null);
		this.width = img.getWidth(null);
	}
}
