package bomberman.view;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class GUI extends JFrame {
	public static final int WIDTHJF = 905;
	public static final int HEIGHTJF = 640;
	private int round = 1;
	private String namePlayer;

	public GUI() {
		setSize(WIDTHJF, HEIGHTJF);
		setLayout(new CardLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Container container = new Container(this);
		add(container);
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void setNamePlayer(String namePlayer) {
		this.namePlayer = namePlayer;
	}

}
