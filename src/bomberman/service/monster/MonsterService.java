package bomberman.service.monster;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Monster;
import bomberman.model.Wall;

public interface MonsterService {
	public void drawAllMonters(List<Monster> monsterList, Graphics2D g2d);

	public List<Monster> getAllMonster(int round);

	public void move(List<Monster> monsterList, List<Wall> wallList, List<Box> boxList, Bomb bomb, List<Bang> bangList,
			Bomber bomber);

	public boolean checkImpactMonsterVsBang(Monster monster, List<Bang> bangList);

	public void monsterDie(List<Monster> monsterList, Bomber bomber);

}
