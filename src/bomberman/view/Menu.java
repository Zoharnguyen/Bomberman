package bomberman.view;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sound.GameSound;


public class Menu extends JPanel{
	private int padding = 15;
	private GUI gui;
	private Container Container;
	private JLabel lbbackground;
	private JLabel lbPlayGame;
	private JLabel lbHigthScore;
	private JLabel lbExit;
	private ImageIcon backgroundIcon;
	private GameSound gameSound = new GameSound();
	
	public Menu(Container Container){
		this.Container = Container;
		this.gui = Container.getGui();
		setLayout(null);
		initComps(gui);
		initbackground();
	}
	
	public void initComps(GUI gui){
		lbPlayGame = setLabel((gui.getWidth()-150)/2-30, (gui.getHeight()-30)/2-100, "/images/button_play.png");
		lbHigthScore = setLabel(lbPlayGame.getX(),lbPlayGame.getY() + lbPlayGame.getHeight()+padding, "/images/button_hightScore.png");
		lbExit = setLabel(lbHigthScore.getX(),lbHigthScore.getY() + lbHigthScore.getHeight()+padding, "/images/button_exit.png");
		
		lbPlayGame.addMouseListener(mouseAdapter);
		lbHigthScore.addMouseListener(mouseAdapter);
		lbExit.addMouseListener(mouseAdapter);
		
		add(lbPlayGame);
		add(lbHigthScore);
		add(lbExit);
	}
	
	public void initbackground(){
		lbbackground = new JLabel();
		lbbackground.setBounds(0, 0, gui.getWidth(), gui.getHeight());
		backgroundIcon = new ImageIcon(getClass().getResource("/images/background_menu.png"));
		lbbackground.setIcon(backgroundIcon);
		add(lbbackground);
	}
	
	public JLabel setLabel(int x, int y, String ImageIcon){
		JLabel label = new JLabel();
		ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
		label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
		label.setIcon(Icon);
		return label;
	}
	
	private MouseAdapter mouseAdapter = new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource()==lbPlayGame){
				ImageIcon playIcon = new ImageIcon(getClass().getResource("/images/button_play2.png"));
				lbPlayGame.setIcon(playIcon);
			}
			if(e.getSource()==lbHigthScore){
				ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/images/button_hightScore2.png"));
				lbHigthScore.setIcon(hightScoreIcon);
			}
			if(e.getSource()==lbExit){
				ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/button_exit2.png"));
				lbExit.setIcon(exitIcon);
			}
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource()==lbPlayGame){
				ImageIcon playIcon = new ImageIcon(getClass().getResource("/images/button_play.png"));
				lbPlayGame.setIcon(playIcon);
			}
			if(e.getSource()==lbHigthScore){
				ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/images/button_hightScore.png"));
				lbHigthScore.setIcon(hightScoreIcon);
			}
			if(e.getSource()==lbExit){
				ImageIcon exitIcon = new ImageIcon(getClass().getResource("/images/button_exit.png"));
				lbExit.setIcon(exitIcon);
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getSource()==lbExit){
				Container.exit();
			}
			if(e.getSource()==lbPlayGame){
				Container.setShowPlay();
			}
			if(e.getSource()==lbHigthScore){
				Container.setShowHighScore();
			}
		}
	};
}
