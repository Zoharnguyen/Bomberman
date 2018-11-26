package bomberman.service.bomber;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Figure;
import bomberman.model.Item;
import bomberman.model.Monster;
import bomberman.model.Wall;
import bomberman.service.general.GeneralService;
import bomberman.service.general.GeneralServiceImpl;
import sound.GameSound;

public class BomberServiceImpl implements BomberService {
	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	private final int RANDOM = 4;
	public final int LEFT = 1;
	public final int RIGHT = 2;
	public final int UP = 3;
	public final int DOWN = 4;
	public GeneralService generalService = new GeneralServiceImpl();
	private GameSound gameSound = new GameSound();
//	private MonsterService monsterService = new MonsterServiceImpl();

	@Override
	public boolean move(Figure bomber, int status, List<Wall> wallList, List<Box> boxList, Bomb bomb,
			List<Monster> monsterList) {
		// Random move of monster
		if (bomber instanceof Monster) {
			if (((Monster) bomber).getCount() == 700 || ((Monster) bomber).getTimeDie() > 0) {
				bomber.setCount(0);
				return false;
			}
			bomber.setCount(bomber.getCount() + 1);
		}

		switch (status) {
		case LEFT: {
			if (bomber.getX() > 0) {
				if (bomber instanceof Monster) {
					if (checkImpactBomb(bomber, bomb) == 1) {
						bomber.setCount(0);
						return false;
					}
					if (checkImpactMosterVsMonster((Monster) bomber, monsterList) == 1)
						return false;

					if (checkImpactWall(bomber, wallList) == 1)
						return false;
					if (checkImpactBox(bomber, boxList) == 1)
						return false;
				}
				bomber.setX(bomber.getX() - 1);
				bomber.setX(bomber.getX() + checkImpactWall(bomber, wallList));
				bomber.setX(bomber.getX() + checkImpactBox(bomber, boxList));
				bomber.setX(bomber.getX() + checkImpactBomb(bomber, bomb));
				return true;
			}
			break;
		}
		case RIGHT: {
			if (bomber.getX() < 630) {
				if (bomber instanceof Monster) {
					if (checkImpactBomb(bomber, bomb) == 1) {
						bomber.setCount(0);
						return false;
					}
					if (checkImpactMosterVsMonster((Monster) bomber, monsterList) == 1)
						return false;

					if (checkImpactWall(bomber, wallList) == 1)
						return false;
					if (checkImpactBox(bomber, boxList) == 1)
						return false;
				}
				bomber.setX(bomber.getX() + 1);
				bomber.setX(bomber.getX() - checkImpactWall(bomber, wallList));
				bomber.setX(bomber.getX() - checkImpactBox(bomber, boxList));
				bomber.setX(bomber.getX() - checkImpactBomb(bomber, bomb));
				return true;
			}
			break;
		}
		case UP: {
			if (bomber.getY() > 0) {
				if (bomber instanceof Monster) {
					if (checkImpactBomb(bomber, bomb) == 1) {
						bomber.setCount(0);
						return false;
					}
					if (checkImpactMosterVsMonster((Monster) bomber, monsterList) == 1)
						return false;

					if (checkImpactWall(bomber, wallList) == 1)
						return false;
					if (checkImpactBox(bomber, boxList) == 1)
						return false;
				}
				bomber.setY(bomber.getY() - 1);
				bomber.setY(bomber.getY() + checkImpactWall(bomber, wallList));
				bomber.setY(bomber.getY() + checkImpactBox(bomber, boxList));
				bomber.setY(bomber.getY() + checkImpactBomb(bomber, bomb));
				return true;
			}
			break;
		}
		case DOWN: {
			if (bomber.getY() < 550) {				
				if (bomber instanceof Monster) {
					if (checkImpactBomb(bomber, bomb) == 1) {
						bomber.setCount(0);
						return false;
					}
					if (checkImpactMosterVsMonster((Monster) bomber, monsterList) == 1)
						return false;

					if (checkImpactWall(bomber, wallList) == 1)
						return false;
					if (checkImpactBox(bomber, boxList) == 1)
						return false;
				}
				bomber.setY(bomber.getY() + 1);
				bomber.setY(bomber.getY() - checkImpactWall(bomber, wallList));
				bomber.setY(bomber.getY() - checkImpactBox(bomber, boxList));
				bomber.setY(bomber.getY() - checkImpactBomb(bomber, bomb));
				return true;
			}
			break;
		}
		}
		return false;
	}

	@Override
	public void drawBomber(Bomber bomber, Graphics2D g2d) {
		g2d.drawImage(bomber.getImg(), bomber.getX(), bomber.getY(), null);
	}

	@Override
	public int checkImpactWall(Figure bomber, List<Wall> wallList) {
		for (Wall wall : wallList) {
			Rectangle rec1 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth()-5, bomber.getHeight()-5);
			Rectangle rec2 = new Rectangle(wall.getX(), wall.getY(), wall.getWidth()-5, wall.getHeight()-5);
			if (rec1.intersects(rec2) == true) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int checkImpactBomb(Figure bomber, Bomb bomb) {
		if (bomb.getTimeLine() > 0) {
			Rectangle rec1 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
			Rectangle rec2 = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
			if (rec1.intersects(rec2) == true) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int checkImpactBox(Figure bomber, List<Box> boxList) {
		for (Box box : boxList) {
			Rectangle rec1 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth()-5, bomber.getHeight()-5);
			Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth()-5, box.getHeight()-5);
			if (rec1.intersects(rec2) == true) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public boolean checkImpactMonster(Figure bomber, List<Monster> monsterList) {
		for (Monster monster : monsterList) {
			Rectangle rec1 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth()-10, bomber.getHeight()-20);
			Rectangle rec2 = new Rectangle(monster.getX(), monster.getY(), monster.getWidth()-10, monster.getHeight()-20);
			if (rec1.intersects(rec2)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void bomberDie(Bomber bomber, List<String> stringList) {
		int index = generalService.checkScoreToSaveFile(bomber.getScore(), stringList);
		if (index >= -1) {
			String content = bomber.getName() + " : " + bomber.getScore();
			List<String> temp = generalService.addContent(stringList, content, index);
			generalService.writeFile("src/hight_score/hightScore.txt", temp);
		}
		gameSound.getAudio(gameSound.BOMBER_DIE).play();
		bomber.setScore(0);
		bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_die.png")).getImage());
		bomber.setDEAD(1);
		bomber.setHEART(bomber.getHEART() - 1);
	}

	@Override
	public boolean checkImpactBang(Figure figure, Bomb bomb) {
		if (bomb.getTimeLinebang() != 0) {
			for (Bang bang : bomb.getBangList()) {
				Rectangle rec1 = new Rectangle(bang.getX(), bang.getY(), bang.getWidth(), bang.getHeight());
				Rectangle rec2 = new Rectangle(figure.getX(), figure.getY(), figure.getWidth() - 20,
						figure.getHeight() - 20);
				if (rec1.intersects(rec2) == true) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void usesItem(Bomber bomber) {
		Item item = bomber.getItem();
		if (item.getKind() == RANDOM) {
			Random random = new Random();
			int temp = 1 + random.nextInt(2);
			item.setKind(temp);
		}
		if (item.getTimeline() == 1) {
			switch (item.getKind()) {
			case HEART: {
				bomber.setHEART(bomber.getHEART() + 1);
				break;
			}
			case SCORE: {
				bomber.setScore(bomber.getScore() + 1);
				break;
			}
			default:
				break;
			}
		}
	}

	@Override
	public int checkImpactMosterVsMonster(Monster monster, List<Monster> monsterList) {
		int j = monsterList.indexOf(monster);
		for (int i = 0; i < monsterList.size(); i++) {
			if (i != j) {
				Rectangle rec1 = new Rectangle(monsterList.get(i).getX(), monsterList.get(i).getY(),
						monsterList.get(i).getWidth(), monsterList.get(i).getHeight());
				Rectangle rec2 = new Rectangle(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
				if (rec1.intersects(rec2) == true) {
					return 1;
				}
			}
		}
		return 0;
	}
}
