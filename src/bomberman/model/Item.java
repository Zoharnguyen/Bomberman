package bomberman.model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Item extends Figure {

	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	private final int RANDOM = 4;
	private int timeline = 1;
	private int timelineFire = 0;
	private int kind = 0;

	public Item(int x, int y, int kind) {
		this.x = x;
		this.y = y;
		this.kind = kind;
		switch (kind) {
		case HEART: {
			this.img = new ImageIcon(getClass().getResource("/images/item_heart.png")).getImage();
			break;
		}
		case SCORE: {
			this.img = new ImageIcon(getClass().getResource("/images/item_score.png")).getImage();
			break;
		}
		case FIRE: {
			this.img = new ImageIcon(getClass().getResource("/images/item_fire.png")).getImage();
			break;
		}
		case RANDOM: {
			this.img = new ImageIcon(getClass().getResource("/images/item_random.png")).getImage();
			break;
		}
		}
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public int getTimeline() {
		return timeline;
	}

	public void setTimeline(int timeline) {
		this.timeline = timeline;
	}

	public int getKind() {
		return kind;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public int getTimelineFire() {
		return timelineFire;
	}

	public void setTimelineFire(int timelineFire) {
		this.timelineFire = timelineFire;
	}

}
