package bomberman.service.bomb;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Item;
import bomberman.model.Wall;

public interface BombService {
	public void drawBomb(Bomb bomb, Graphics2D g2d);

	public void drawAllBang(Bomb bomb, Graphics2D g2d);

	public void putBomb(Bomber bomber, List<Box> boxList, List<Wall> wallList);

	public boolean checkImpactBoxAndWall(Bomb bomb, List<Box> boxList, List<Wall> wallList);

	public void destroyBox(List<Box> boxList, Bomb bomb);
	
	public void itemFire(Item item, Bomb bomb, Graphics2D g2d, List<Box> boxList, Bomber bomber);
	
	public List<Bang> getFire(Bomber bomber);

}
