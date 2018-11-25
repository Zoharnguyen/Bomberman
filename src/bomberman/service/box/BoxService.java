package bomberman.service.box;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;

public interface BoxService {
	public void drawAllBox(List<Box> boxList, Graphics2D g2d);
	public List<Box> getListBox(int round);
	public boolean checkImpactBang(Bomb bomb, Box box);
	public boolean checkImpactItemFire(Bomber bomber, List<Bang> bangList, Box box);
}
