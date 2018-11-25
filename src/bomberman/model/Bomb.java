package bomberman.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Bomb extends Figure {
	
	private int timeLinebang = 0;
	private int timeLine = 0;
	private List<Bang> bangList = new ArrayList<Bang>();
	
	public Bomb() {
		this.x = -100;
		this.y = 650;
		this.img = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		Bang bang = new Bang(x, y , new ImageIcon(getClass().getResource("/images/bang.png")).getImage());
		Bang bangLeft = new Bang(x - 50, y, new ImageIcon(getClass().getResource("/images/bang_left.png")).getImage());
		Bang bangRight = new Bang(x + 50, y, new ImageIcon(getClass().getResource("/images/bang_right.png")).getImage());
		Bang bangUp = new Bang(x, y - 45, new ImageIcon(getClass().getResource("/images/bang_up.png")).getImage());
		Bang bangDown = new Bang(x, y + 45, new ImageIcon(getClass().getResource("/images/bang_down.png")).getImage());
		this.bangList.add(bang);
		this.bangList.add(bangLeft);
		this.bangList.add(bangRight);
		this.bangList.add(bangUp);
		this.bangList.add(bangDown);
	}

	public Bomb(int x, int y, int width, int height, Image img) {
		this.x = x;
		this.y = y;
		this.img = new ImageIcon(getClass().getResource("/images/bomb.png")).getImage();
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	@Override
	public void setX(int x) {
		this.x = x;
		bangList.get(0).setX(x);
		bangList.get(1).setX(x - 50);
		bangList.get(2).setX(x + 50);
		bangList.get(3).setX(x);
		bangList.get(4).setX(x);
	}
	
	@Override
	public void setY(int y) {
		this.y = y;
		bangList.get(0).setY(y);
		bangList.get(1).setY(y);
		bangList.get(2).setY(y);
		bangList.get(3).setY(y - 45);
		bangList.get(4).setY(y + 45);
	}
	
	public int getTimeLine() {
		return timeLine;
	}

	public void setTimeLine(int timeLine) {
		this.timeLine = timeLine;
	}

	public List<Bang> getBangList() {
		return bangList;
	}

	public void setBangList(List<Bang> bangList) {
		this.bangList = bangList;
	}

	public int getTimeLinebang() {
		return timeLinebang;
	}

	public void setTimeLinebang(int timeLinebang) {
		this.timeLinebang = timeLinebang;
	}
	
}
