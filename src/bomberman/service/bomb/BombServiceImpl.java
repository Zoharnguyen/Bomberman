package bomberman.service.bomb;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Item;
import bomberman.model.Wall;
import bomberman.service.box.BoxService;
import bomberman.service.box.BoxServiceImpl;
import sound.GameSound;

public class BombServiceImpl implements BombService {
	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	public final int LEFT = 1;
	public final int RIGHT = 2;
	public final int UP = 3;
	public final int DOWN = 4;
	public BoxService boxService = new BoxServiceImpl();
	private GameSound gameSound = new GameSound();

	@Override
	public void drawBomb(Bomb bomb, Graphics2D g2d) {
		if (bomb.getTimeLine() > 0) {
			g2d.drawImage(bomb.getImg(), bomb.getX(), bomb.getY(), null);
		}
	}

	@Override
	public void putBomb(Bomber bomber, List<Box> boxList, List<Wall> wallList) {

		if (bomber.getBomb().getTimeLinebang() == 0 && bomber.getBomb().getTimeLine() == 0) {
			gameSound.getAudio(gameSound.BOMB).play();
			bomber.getBomb().setTimeLine(150);
			if (bomber.getStatus() == LEFT) {
				bomber.getBomb().setX(bomber.getX() - 50);
				bomber.getBomb().setY(bomber.getY() + 10);
			} else if (bomber.getStatus() == RIGHT) {
				bomber.getBomb().setX(bomber.getX() + 40);
				bomber.getBomb().setY(bomber.getY() + 10);
			} else if (bomber.getStatus() == UP) {
				bomber.getBomb().setX(bomber.getX());
				bomber.getBomb().setY(bomber.getY() - 45);
			} else {
				bomber.getBomb().setX(bomber.getX());
				bomber.getBomb().setY(bomber.getY() + 60);
			}
			if (checkImpactBoxAndWall(bomber.getBomb(), boxList, wallList) == true) {
				bomber.getBomb().setTimeLine(0);
			}
		}
	}

	@Override
	public boolean checkImpactBoxAndWall(Bomb bomb, List<Box> boxList, List<Wall> wallList) {
		for (Box box : boxList) {
			Rectangle rec1 = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
			Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth(), box.getHeight());
			if (rec1.intersects(rec2) == true) {
				return true;
			}
		}
		for (Wall wall : wallList) {
			Rectangle rec1 = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
			Rectangle rec2 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth(), wall.getHeight());
			if (rec1.intersects(rec2) == true) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void drawAllBang(Bomb bomb, Graphics2D g2d) {
		if (bomb.getTimeLinebang() != 0) {
			if (bomb.getTimeLinebang() == 1) {
				gameSound.getAudio(gameSound.BONG_BANG).play();
			}
			for (Bang bang : bomb.getBangList()) {
				g2d.drawImage(bang.getImg(), bang.getX(), bang.getY(), null);
			}
		}
	}

	@Override
	public void destroyBox(List<Box> boxList, Bomb bomb) {
		int[] arr = new int[boxList.size()];
		int k = 0;
		for (int i = 0; i < boxList.size(); i++) {

			if (boxService.checkImpactBang(bomb, boxList.get(i)) == true) {
				arr[k] = i;
				k++;
			}
		}

		for (int i = 0; i < k; i++) {
			boxList.remove(arr[i] - i);
		}
	}
	
	@Override
	public void itemFire(Item item, Bomb bomb, Graphics2D g2d, List<Box> boxList, Bomber bomber) {
		List<Bang> bangList = new ArrayList<Bang>();
		if(item.getTimeline() > 0 && item.getKind() == FIRE && item.getTimelineFire() > 0) {
			Bang bangLeft = new Bang(bomb.getBangList().get(1).getX() - 50, bomb.getBangList().get(1).getY(),  new ImageIcon(getClass().getResource("/images/bang_left.png")).getImage());
			Bang bangRight = new Bang(bomb.getBangList().get(2).getX() + 50, bomb.getBangList().get(2).getY(), new ImageIcon(getClass().getResource("/images/bang_right.png")).getImage());
			Bang bangUp = new Bang(bomb.getBangList().get(3).getX(), bomb.getBangList().get(3).getY() - 45, new ImageIcon(getClass().getResource("/images/bang_up.png")).getImage());
			Bang bangDown = new Bang(bomb.getBangList().get(4).getX(), bomb.getBangList().get(4).getY() + 45, new ImageIcon(getClass().getResource("/images/bang_down.png")).getImage());
			bangList.add(bangLeft);
			bangList.add(bangRight);
			bangList.add(bangUp);
			bangList.add(bangDown);
		}
		if(bangList != null) {
			for(Bang bang : bangList) {
				g2d.drawImage(bang.getImg(), bang.getX(), bang.getY(), null);
			}
			int[] arr = new int[boxList.size()];
			int k = 0;
			for (int i = 0; i < boxList.size(); i++) {

				if (boxService.checkImpactItemFire(bomber, bangList, boxList.get(i)) == true) {
					arr[k] = i;
					k++;
				}
			}

			for (int i = 0; i < k; i++) {
				boxList.remove(arr[i] - i);
			}
		}
	}
	
	@Override
	public List<Bang> getFire(Bomber bomber) {
		Bomb bomb = bomber.getBomb();
		Item item = bomber.getItem();
		List<Bang> bangList = new ArrayList<Bang>();
		if(item.getTimeline() > 0 && item.getKind() == FIRE && item.getTimelineFire() > 0) {
			Bang bangLeft = new Bang(bomb.getBangList().get(1).getX() - 50, bomb.getBangList().get(1).getY(),  new ImageIcon(getClass().getResource("/images/bang_left.png")).getImage());
			Bang bangRight = new Bang(bomb.getBangList().get(2).getX() + 50, bomb.getBangList().get(2).getY(), new ImageIcon(getClass().getResource("/images/bang_right.png")).getImage());
			Bang bangUp = new Bang(bomb.getBangList().get(3).getX(), bomb.getBangList().get(3).getY() - 45, new ImageIcon(getClass().getResource("/images/bang_up.png")).getImage());
			Bang bangDown = new Bang(bomb.getBangList().get(4).getX(), bomb.getBangList().get(4).getY() + 45, new ImageIcon(getClass().getResource("/images/bang_down.png")).getImage());
			bangList.add(bangLeft);
			bangList.add(bangRight);
			bangList.add(bangUp);
			bangList.add(bangDown);
		}
		return bangList;
	}
}
