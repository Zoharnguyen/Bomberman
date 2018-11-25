package bomberman.view;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

import bomberman.service.general.GeneralService;
import bomberman.service.general.GeneralServiceImpl;
import sound.GameSound;

public class Container extends JPanel {
	private static CardLayout cardLayout;
	private GUI gui;
	private Play play;
	private Menu menu;
	private HightScore hightScore;
	private GeneralService generalService = new GeneralServiceImpl();
	private GameSound gameSound = new GameSound();
	
	public Container(GUI gui) {
		this.gui = gui;
		setBackground(Color.WHITE);
		cardLayout = new CardLayout();
		setLayout(cardLayout);
		menu = new Menu(this);
		add(menu, "MENU");
		play = new Play(this);
		add(play, "PLAY");
		hightScore = new HightScore(this);
		add(hightScore, "HIGHTSCORE");
		setShowMenu();
	}

	public void setShowPlay() {
		cardLayout.show(this, "PLAY");
		play.requestFocus();
		gameSound.getAudio(gameSound.MENU).stop();
		gameSound.getAudio(gameSound.PLAYGAME).loop();
	}


	public void setShowMenu() {
		cardLayout.show(this, "MENU");
		menu.requestFocus();
		gameSound.stop();
		gameSound.getAudio(gameSound.MENU).loop();
	}
	public void setShowHighScore() {
		cardLayout.show(this, "HIGHTSCORE");
		hightScore.jlHightScore.setText(generalService.convertStandardLable(generalService.readFile("s"
				+ "rc/hight_score/hightScore.txt")));
		hightScore.requestFocus();
	}

	public GUI getGui() {
		return gui;
	}

	public void setGui(GUI gui) {
		this.gui = gui;
	}
}
