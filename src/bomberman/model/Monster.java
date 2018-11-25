package bomberman.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Monster extends Figure {
	private int status;
	private int count;
	private int timeDie = 0;

	public Monster(int x, int y, int status, int count,Image img) {
		this.x = x;
		this.y = y;
		this.status = status;
		this.img = img;
		this.count = count;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTimeDie() {
		return timeDie;
	}

	public void setTimeDie(int timeDie) {
		this.timeDie = timeDie;
	}

}
