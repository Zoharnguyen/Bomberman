package bomberman.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.service.general.GeneralService;
import bomberman.service.general.GeneralServiceImpl;

public class HightScore extends JPanel implements ActionListener{
	
	public Container container;
	public GUI gui;
	public JButton jbBack;
	public JLabel jlBackground;
	public JLabel jlHightScore;
	public GeneralService generalService = new GeneralServiceImpl();
	
	public HightScore(Container container) {
		this.container = container;
		this.gui = container.getGui();
		setLayout(null);
		innitBack();
		initComps(gui);		
	}
	
	public void initComps(GUI gui) {
		jlBackground = new JLabel();
		jlHightScore = new JLabel();
		jlBackground.setBounds(0, 0, gui.getWidth(), gui.getHeight());
		jlBackground.setIcon(new ImageIcon(getClass().getResource("/images/background_hightScore.jpg")));
		jlHightScore.setBounds((gui.getWidth()-150)/2-30, (gui.getHeight()-30)/2-400, 500, 700);
		jlHightScore.setFont(new Font("Arial", Font.BOLD, 30));
		jlHightScore.setText(generalService.convertStandardLable(generalService.readFile("src/hight_score/hightScore.txt")));
		add(jlHightScore);
		add(jlBackground);
	}
	
	public void innitBack() {
		jbBack = new JButton();
		jbBack.setBounds(100, 520, 80, 42);
		jbBack.setIcon(new ImageIcon(getClass().getResource("/images/button_back.png")));
		jbBack.addActionListener(this);
		add(jbBack);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == jbBack) {
			container.setShowMenu();
		}
	}
}
