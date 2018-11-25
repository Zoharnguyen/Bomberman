package bomberman.service.item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Item;
import sound.GameSound;

public class ItemServiceImpl implements ItemService {
	
	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	private final int RANDOM = 4;
	private GameSound gameSound = new GameSound();
	
	@Override
	public void drawItem(List<Item> itemList, Graphics2D g2d) {
		for(Item item : itemList) {
			g2d.drawImage(item.getImg(), item.getX(), item.getY(), null);
		}
	}
	
	@Override
	public List<Item> getListItem(int round) {
		List<Item> itemList = new ArrayList<Item>();
		String path = "";
		if(round == 1) {
			path = "src/maps/item.txt";
		} else {
			path = "src/maps/item2.txt";
		}
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				int kind = Integer.parseInt(str[2]);
				Item item = new Item(x,y, kind);
				itemList.add(item);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return itemList;
	}

	@Override
	public void checkImpactBomberVsItem(List<Item> itemList, Bomber bomber) {
		int i = 0;
		for (i = 0; i < itemList.size(); i++ ) {
			Item item = itemList.get(i);
			Rectangle rec1 = new Rectangle(bomber.getX(), bomber.getY(), bomber.getWidth(), bomber.getHeight());
			Rectangle rec2 = new Rectangle(item.getX(), item.getY(), item.getWidth(), item.getHeight());
			if (rec1.intersects(rec2) == true) {
				gameSound.getAudio(gameSound.ITEM).play();
				item.setTimeline(1000);
				bomber.setItem(item);
				break;
			}
		}
		if(i != itemList.size()) {
			itemList.remove(i);
		}
	}
}
