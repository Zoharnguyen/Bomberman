package bomberman.service.bomber;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Figure;
import bomberman.model.Monster;
import bomberman.model.Wall;

public interface BomberService {
	public boolean move(Figure figure, int status, List<Wall> wallList, List<Box> boxList, Bomb bomb, List<Monster> monsterList);

	public void drawBomber(Bomber bomber, Graphics2D g2d);

	public int checkImpactWall(Figure bomber, List<Wall> wallList);

	public int checkImpactBox(Figure bomber, List<Box> boxList);

	public boolean checkImpactMonster(Figure bomber, List<Monster> monsterList);
	
	public int checkImpactBomb(Figure bomber, Bomb bomb);
	
	public boolean checkImpactBang(Figure figure, Bomb bomb);

	public void bomberDie(Bomber bomber, List<String> stringList);
	
	public void usesItem(Bomber bomber);
	
	public int checkImpactMosterVsMonster(Monster monster, List<Monster> monsterList);
	
}
