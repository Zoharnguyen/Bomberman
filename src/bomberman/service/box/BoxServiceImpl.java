package bomberman.service.box;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import bomberman.model.Bang;
import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;

public class BoxServiceImpl implements BoxService{
	
	@Override
	public void drawAllBox(List<Box> boxList, Graphics2D g2d) {
		for(Box box : boxList) {
			g2d.drawImage(box.getImg(), box.getX(), box.getY(), null);
		}
	}
	
	@Override
	public List<Box> getListBox(int round) {
		List<Box> boxList = new ArrayList<Box>();
		String path =""; 
		if(round == 1) {
			path = "src/maps/box.txt";
		} else {
			path = "src/maps/box2.txt";
		}
		try {
			FileReader file = new FileReader(path);
			BufferedReader input = new BufferedReader(file);
			String line;
			while ((line = input.readLine()) != null) {
				String str[] = line.split(":");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				String imagePath = str[2];
				Box box = new Box(x,y, new ImageIcon(getClass().getResource(imagePath)).getImage());
				boxList.add(box);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return boxList;
	}
	
	@Override
	public boolean checkImpactBang(Bomb bomb, Box box) {
		if (bomb.getTimeLinebang() > 0) {
			for (Bang bang : bomb.getBangList()) {
				Rectangle rec1 = new Rectangle(bang.getX(), bang.getY(), bang.getWidth(), bang.getHeight());
				Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth() - 20, box.getHeight() - 20);
				if (rec1.intersects(rec2) == true) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean checkImpactItemFire(Bomber bomber, List<Bang> bangList, Box box) {
		if (bomber.getItem().getTimelineFire() > 0) {
			for (Bang bang : bangList) {
				Rectangle rec1 = new Rectangle(bang.getX(), bang.getY(), bang.getWidth(), bang.getHeight());
				Rectangle rec2 = new Rectangle(box.getX(), box.getY(), box.getWidth() - 20, box.getHeight() - 20);
				if (rec1.intersects(rec2) == true) {
					return true;
				}
			}
		}
		return false;
	}
}
