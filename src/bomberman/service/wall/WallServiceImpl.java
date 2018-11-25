package bomberman.service.wall;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import bomberman.model.Wall;

public class WallServiceImpl implements WallService{
	@Override
	public void drawAllWall(List<Wall> wallList, Graphics2D g2d) {
		for(Wall wall : wallList) {
			g2d.drawImage(wall.getImg(), wall.getX(), wall.getY(), null);
		}
	}
	
	@Override
	public List<Wall> getListWall(int round) {
		List<Wall> wallList = new ArrayList<Wall>();
		String path = "";
		if(round == 1) {
			path = "src/maps/wall.txt";
		} else {
			path = "src/maps/wall2.txt";
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
				Wall wall = new Wall(x,y, new ImageIcon(getClass().getResource(imagePath)).getImage());
				wallList.add(wall);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wallList;
	}
}
