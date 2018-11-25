package bomberman.service.item;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bomber;
import bomberman.model.Item;

public interface ItemService {
	public void drawItem(List<Item> itemList, Graphics2D g2d);
	public List<Item> getListItem(int round);
	public void checkImpactBomberVsItem(List<Item> itemList, Bomber bomber);
}
