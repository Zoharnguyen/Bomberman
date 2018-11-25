package bomberman.service.general;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

import bomberman.model.Bomber;
import bomberman.service.box.BoxService;
import bomberman.service.box.BoxServiceImpl;
import bomberman.service.item.ItemService;
import bomberman.service.item.ItemServiceImpl;
import bomberman.service.monster.MonsterService;
import bomberman.service.monster.MonsterServiceImpl;
import bomberman.service.wall.WallService;
import bomberman.service.wall.WallServiceImpl;

public class GeneralServiceImpl implements GeneralService {

	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	private final int RANDOM = 4;	

	@Override
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(new ImageIcon(getClass().getResource("/images/background.jpg")).getImage(), 0, -300, null);
	}
	
	@Override
	public void drawRound2(Graphics2D g2d, Bomber bomber) {
		if(bomber.getROUND() == 2 && bomber.getTimelineRound() > 0) {
			g2d.drawImage(new ImageIcon(getClass().getResource("/images/round_2.png")).getImage(), 200, 200, null);
		}
	}
	
	@Override
	public void drawWin(Graphics2D g2d, Bomber bomber) {
		if(bomber.getWIN() == 1 && bomber.getTimelineFinish() > 0) {
			g2d.drawImage(new ImageIcon(getClass().getResource("/images/win.jpg")).getImage(), -150, 50, null);	
		}
	}
	
	@Override
	public void drawLost(Graphics2D g2d, Bomber bomber) {
		if(bomber.getLOST() == 1) {
			g2d.drawImage(new ImageIcon(getClass().getResource("/images/gameover.jpg")).getImage(), 100, 100, null);		
		}
	}
	
	@Override
	public void drawInfo(Graphics2D g2d, Bomber bomber) {
		Image imgInfor = new ImageIcon(getClass().getResource("/images/background_info.jpg")).getImage();
		if (bomber.getItem().getKind() == RANDOM) {
			Random random = new Random();
			int temp = 1 + random.nextInt(3);
			bomber.getItem().setKind(temp);
		}
		g2d.setFont(new Font("Arial", Font.BOLD, 20));
		g2d.setColor(Color.RED);
		g2d.drawImage(imgInfor, 675, 0, null);
		g2d.drawString("HEART", 755, 100);
		g2d.drawString("SCORE", 755, 200);
		g2d.drawString("ITEM USING", 740, 300);
		g2d.drawString("TIMELINE ITEM", 730, 400);
		if (bomber.getItem().getTimelineFire() > 0) {
			g2d.drawString("TIMELINE FIRE", 730, 500);
		}
		g2d.setColor(Color.black);
		g2d.drawString("" + bomber.getScore(), 785, 235);
		g2d.drawString("" + bomber.getHEART(), 775, 135);
		if (bomber.getItem().getTimeline() > 0) {
			switch (bomber.getItem().getKind()) {
			case HEART: {
				g2d.drawString("ADD 1 HEART", 730, 335);
				break;
			}
			case SCORE: {
				g2d.drawString("ADD 1 SCORE", 730, 335);
				break;
			}
			case FIRE: {
				g2d.drawString("SCALE BOOM", 730, 335);
				break;
			}
			default:
				break;
			}
		} else {
			g2d.drawString("EMPTY", 765, 335);
		}
		g2d.drawString("" + bomber.getItem().getTimeline(), 785, 435);
		if (bomber.getItem().getTimelineFire() > 0) {
			g2d.drawString("" + bomber.getItem().getTimelineFire(), 785, 525);
		}
	}

	@Override
	public List<String> readFile(String path) {
		List<String> stringList = new ArrayList<String>();
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String line;
			String content = "";
			try {
				while ((line = br.readLine()) != null) {
					stringList.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringList;
	}

	@Override
	public void writeFile(String path, List<String> stringList) {
		FileWriter fr = null;
		try {
			fr = new FileWriter(path);
			for (int i = 0; i < stringList.size() -1; i++) {
				fr.write(stringList.get(i)+ "\n");
			}
			fr.write(stringList.get(stringList.size() - 1));

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String convertStandardLable(List<String> stringList) {
		String content = "<html> ";
		int index = 0;
		while (index < stringList.size() - 1) {
			content = content + stringList.get(index) + "<br>";
			index++;
		}
		content = content + stringList.get(stringList.size() - 1) + "<html>";
		return content;
	}

	@Override
	public int checkScoreToSaveFile(int score, List<String> stringList) {
		int[] arr = new int[stringList.size()];
		int k = 0, j = 0;
		for (int i = stringList.size() - 1; i >= 0; i--) {
			String[] nameScore = stringList.get(i).split(": ");
			arr[k] = Integer.parseInt(nameScore[1]);
			k++;
		}
		while(j < k) {
			if(score < arr[j]) {
				break;
			} else {
				j++;
			}
		}
		return stringList.size() -1 - j;
	}

	@Override
	public List<String> addContent(List<String> stringList, String content, int index) {
		List<String> temp = new ArrayList<String>();
		if(index == -1) {
			temp.add(content);
		}
		for (int i = 0; i < stringList.size() - 1; i++) {
			temp.add(stringList.get(i));
			if (i == index) {
				temp.add(content);
			}
		}
		return temp;
	}
	
}
