package bomberman.model;

import javax.swing.ImageIcon;

public class Bomber extends Figure {

	private int HEART = 3;
	private int DEAD;
	private int LOST = 0;
	private int WIN = 0;
	private int ROUND = 1;
	private int timelineRound = 0;
	private int timelineFinish = 0;
	private Bomb bomb;
	private int status;
	private Item item;
	private int score = 7;
	private String name = "Peter";

	public Bomber(int x, int y, int DEAD, Bomb bomb, String namePlayer) {
		super();
		this.x = x;
		this.y = y;
		this.DEAD = DEAD;
		this.img = new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage();
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.bomb = bomb;
		this.item = new Item(-100, 700, 4);
		this.name = namePlayer;
	}

	public int getHEART() {
		return HEART;
	}

	public void setHEART(int hEART) {
		HEART = hEART;
	}

	public int getDEAD() {
		return DEAD;
	}

	public void setDEAD(int dEAD) {
		DEAD = dEAD;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLOST() {
		return LOST;
	}

	public void setLOST(int lOST) {
		LOST = lOST;
	}

	public int getWIN() {
		return WIN;
	}

	public void setWIN(int wIN) {
		WIN = wIN;
	}

	public int getROUND() {
		return ROUND;
	}

	public void setROUND(int rOUND) {
		ROUND = rOUND;
	}

	public int getTimelineRound() {
		return timelineRound;
	}

	public void setTimelineRound(int timelineRound) {
		this.timelineRound = timelineRound;
	}

	public int getTimelineFinish() {
		return timelineFinish;
	}

	public void setTimelineFinish(int timelineFinish) {
		this.timelineFinish = timelineFinish;
	}

}
