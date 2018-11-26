package bomberman.service.monster;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Monster;
import bomberman.model.Wall;
import bomberman.service.bomber.BomberService;
import bomberman.service.bomber.BomberServiceImpl;
import sound.GameSound;

public class MonsterServiceImpl implements MonsterService {
	private BomberService bomberService = new BomberServiceImpl();
	private GameSound gameSound = new GameSound();

	@Override
	public void drawAllMonters(List<Monster> monsterList, Graphics2D g2d) {
		for (Monster monster : monsterList) {
			g2d.drawImage(monster.getImg(), monster.getX(), monster.getY(), null);
		}
	}

	@Override
	public List<Monster> getAllMonster(int round) {
		List<Monster> monsterList = new ArrayList<Monster>();
		String path = "";
		if(round == 1) {
			path = "src/maps/monster.txt";
		} else {
			path = "src/maps/monster2.txt";
		}
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int status = Integer.parseInt(str[2]);
				int count = Integer.parseInt(str[3]);
				String imagePath = str[4];
				Monster monster = new Monster(x, y, status, count,
						new ImageIcon(getClass().getResource(imagePath)).getImage());
				monsterList.add(monster);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return monsterList;
	}

	@Override
	public boolean checkImpactMonsterVsBang(Monster monster, List<Bang> bangList) {
		for (Bang bang : bangList) {
			Rectangle rec1 = new Rectangle(bang.getX(), bang.getY(), bang.getWidth(), bang.getHeight());
			Rectangle rec2 = new Rectangle(monster.getX(), monster.getY(), monster.getWidth() - 20,
					monster.getHeight() - 20);
			if (rec1.intersects(rec2) == true) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void move(List<Monster> monsterList, List<Wall> wallList, List<Box> boxList, Bomb bomb, List<Bang> bangList,
			Bomber bomber) {
		if (monsterList.size() > 0) {
			int[] arr = new int[monsterList.size()];
			int k = 0;
			for (int i = 0; i < monsterList.size(); i++) {
				if (bomberService.checkImpactBang(monsterList.get(i), bomb) == false) {
					if (bomber.getItem().getTimelineFire() > 0) {
						if (checkImpactMonsterVsBang(monsterList.get(i), bangList) == true) {
							arr[k] = i;
							k++;
						}
					}
					if (bomberService.move(monsterList.get(i), monsterList.get(i).getStatus(), wallList, boxList,
							bomb, monsterList) == false) {
						
						int status = 0;
						if(bomber.getROUND() == 1) {
							Random random = new Random();
							status = 1 + random.nextInt(2);	
						} else {
							Random random = new Random();
							status = 1 + random.nextInt(4);
						}
						monsterList.get(i).setStatus(status);
					}
				} else {
					arr[k] = i;
					k++;
				}
			}
			for (int i = 0; i < k; i++) {
				monsterList.get(arr[i])
						.setImg(new ImageIcon(getClass().getResource("/images/monster_die.png")).getImage());
				monsterList.get(arr[i]).setTimeDie(100);
			}
		}
	}

	@Override
	public void monsterDie(List<Monster> monsterList, Bomber bomber) {
		if (monsterList.size() > 0) {
			int[] arr = new int[monsterList.size()];
			int k = 0;
			for (int i = 0; i < monsterList.size(); i++) {
				if (monsterList.get(i).getTimeDie() > 0) {
					monsterList.get(i).setTimeDie(monsterList.get(i).getTimeDie() - 1);
					if (monsterList.get(i).getTimeDie() == 0) {
						gameSound.getAudio(gameSound.MONSTER_DIE).play();
						arr[k] = i;
						k++;
					}
				}
			}
			for (int j = 0; j < k; j++) {
				monsterList.remove(arr[j] - j);
				bomber.setScore(bomber.getScore() + 1);
			}
		}
	}
	

}
