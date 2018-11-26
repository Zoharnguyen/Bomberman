package bomberman.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NamePlayer extends JPanel implements ActionListener {
	private Container container;
	private GUI gui;
	private JLabel jlBackground;
	private JLabel jlYourName;
	private JButton jbNext;
	private JTextField jtNamePlayer;

	public NamePlayer(Container Container) {
		this.container = Container;
		this.gui = Container.getGui();
		setLayout(null);
		initCompts();
	}

	public void initCompts() {
		jlBackground = new JLabel();
		jbNext = new JButton();
		jtNamePlayer = new JTextField();
		jlYourName = new JLabel();
		jtNamePlayer.setBounds(380, 200, 200, 30);
		jbNext.setText("NEXT");
		jbNext.setBounds(440, 250, 100, 30);
		jbNext.addActionListener(this);
		jlYourName.setBounds(280, 100, 417, 121);
		jlYourName.setIcon(new ImageIcon(getClass().getResource("/images/yourname.png")));
		jlBackground.setBounds(0, 0, 1000, 630);
		jlBackground.setIcon(new ImageIcon(getClass().getResource("/images/background_nameplayer.jpg")));
		add(jbNext);
		add(jtNamePlayer);
		add(jlYourName);
		add(jlBackground);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == jbNext) {
			container.getGui().setNamePlayer(jtNamePlayer.getText());
			container.setShowMenu();
		}
	}
}
