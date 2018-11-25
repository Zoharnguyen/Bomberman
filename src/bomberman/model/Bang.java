package bomberman.model;

import java.awt.Image;

public class Bang extends Figure {

	private int timeline = 0;
	private int BUM = 0;

	public Bang(int x, int y, Image img) {
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public int getTimeline() {
		return timeline;
	}

	public void setTimeline(int timeline) {
		this.timeline = timeline;
	}

	public int getBUM() {
		return BUM;
	}

	public void setBUM(int bUM) {
		BUM = bUM;
	}

}
