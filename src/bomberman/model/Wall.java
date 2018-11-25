package bomberman.model;

import java.awt.Image;

public class Wall extends Figure{

	public Wall(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.height = img.getHeight(null);
		this.width = img.getWidth(null);
	}
	
}
