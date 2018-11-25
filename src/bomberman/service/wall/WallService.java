package bomberman.service.wall;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Wall;

public interface WallService {
	public void drawAllWall(List<Wall> wallList, Graphics2D g2d);
	public List<Wall> getListWall(int round);
}
