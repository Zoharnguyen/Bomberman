package bomberman.service.general;

import java.awt.Graphics2D;
import java.util.List;

import bomberman.model.Bomber;

public interface GeneralService {
	public void drawBackground(Graphics2D g2d);

	public void drawInfo(Graphics2D g2d, Bomber bomber);

	public List<String> readFile(String path);

	public void writeFile(String path, List<String> stringList);

	public String convertStandardLable(List<String> stringList);

	public int checkScoreToSaveFile(int score, List<String> stringList);

	public List<String> addContent(List<String> stringList, String content, int index);

	public void drawRound2(Graphics2D g2d, Bomber bomber);

	public void drawWin(Graphics2D g2d, Bomber bomber);

	public void drawLost(Graphics2D g2d, Bomber bomber);

	public void drawTimeLineRound2(Graphics2D g2d, Bomber bomber);
}
