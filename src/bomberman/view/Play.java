package bomberman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bomberman.model.Bomb;
import bomberman.model.Bomber;
import bomberman.model.Box;
import bomberman.model.Item;
import bomberman.model.Monster;
import bomberman.model.Wall;
import bomberman.service.bomb.BombService;
import bomberman.service.bomb.BombServiceImpl;
import bomberman.service.bomber.BomberService;
import bomberman.service.bomber.BomberServiceImpl;
import bomberman.service.box.BoxService;
import bomberman.service.box.BoxServiceImpl;
import bomberman.service.general.GeneralService;
import bomberman.service.general.GeneralServiceImpl;
import bomberman.service.item.ItemService;
import bomberman.service.item.ItemServiceImpl;
import bomberman.service.monster.MonsterService;
import bomberman.service.monster.MonsterServiceImpl;
import bomberman.service.wall.WallService;
import bomberman.service.wall.WallServiceImpl;
import sound.GameSound;

public class Play extends JPanel implements Runnable, ActionListener {
	private final int HEART = 1;
	private final int SCORE = 2;
	private final int FIRE = 3;
	private final int RANDOM = 4;
	public final int LEFT = 1;
	public final int RIGHT = 2;
	public final int UP = 3;
	public final int DOWN = 4;
	public static boolean IS_RUNNING = true;
	private JButton jbBack;
	private BitSet traceKey = new BitSet();
	private Container container;
	private BomberService bomberService = new BomberServiceImpl();
	private MonsterService monsterService = new MonsterServiceImpl();
	private WallService wallService = new WallServiceImpl();
	private BoxService boxService = new BoxServiceImpl();
	private GeneralService generalService = new GeneralServiceImpl();
	private BombService bombService = new BombServiceImpl();
	private ItemService itemService = new ItemServiceImpl();
	private int round = 1;
	private List<Box> boxList;
	private List<Wall> wallList;
	private List<Monster> monsterList;
	private List<Item> itemList;
	private List<String> hightScore = generalService.readFile("src/hight_score/hightScore.txt");
	private Bomber bomber;

	public Play(Container container) {
		this.container = container;
		bomber = new Bomber(0, 545, 0, new Bomb(), container.getGui().getNamePlayer());
		round = this.container.getGui().getRound();
//		round = 2;
//		bomber.setROUND(2);
		boxList = boxService.getListBox(round);
		wallList = wallService.getListWall(round);
		monsterList = monsterService.getAllMonster(round);
		itemList = itemService.getListItem(round);
		setBackground(Color.WHITE);
		setLayout(null);
		setFocusable(true);
		addKeyListener(keyAdapter);
		Thread mytheard = new Thread(this);
		mytheard.start();
		innitBack();
	}

	public void innitBack() {
		jbBack = new JButton();
		jbBack.setBounds(753, 530, 80, 42);
		jbBack.setIcon(new ImageIcon(getClass().getResource("/images/button_back.png")));
		jbBack.addActionListener(this);
		add(jbBack);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new java.awt.BasicStroke(2));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		generalService.drawBackground(g2d);
		bomberService.drawBomber(bomber, g2d);
		itemService.drawItem(itemList, g2d);
		bombService.drawBomb(bomber.getBomb(), g2d);		
		wallService.drawAllWall(wallList, g2d);
		boxService.drawAllBox(boxList, g2d);
		monsterService.drawAllMonters(monsterList, g2d);		
		bombService.drawAllBang(bomber.getBomb(), g2d);
		bombService.itemFire(bomber.getItem(), bomber.getBomb(), g2d, boxList, bomber);
		generalService.drawWin(g2d, bomber);
		generalService.drawInfo(g2d, bomber);
		generalService.drawRound2(g2d, bomber);
		generalService.drawTimeLineRound2(g2d, bomber);
		generalService.drawLost(g2d, bomber);
	}

	private KeyAdapter keyAdapter = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			traceKey.set(e.getKeyCode());
		}

		@Override
		public void keyReleased(KeyEvent e) {
			traceKey.clear(e.getKeyCode());
		}
	};

	@Override
	public void run() {
		while (IS_RUNNING) {
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (bomber.getName() == null) {
				bomber.setName(container.getGui().getNamePlayer());
			}
			if (bomber.getROUND() == 1 && bomber.getTimelineRound() == 0 && monsterList.size() == 0) {
				bomber.setTimelineRound(500);
				bomber.setROUND(2);
			}
			if (bomber.getDEAD() == 1) {
				try {
					Thread.sleep(1000);
					bomber.setDEAD(0);
					bomber.setX(0);
					bomber.setY(545);
					bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (traceKey.get(KeyEvent.VK_LEFT)) {
				bomber.setStatus(LEFT);
				bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_left.png")).getImage());
				bomberService.move(bomber, LEFT, wallList, boxList, bomber.getBomb(), monsterList);
			}
			if (traceKey.get(KeyEvent.VK_RIGHT)) {
				bomber.setStatus(RIGHT);
				bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_right.png")).getImage());
				bomberService.move(bomber, RIGHT, wallList, boxList, bomber.getBomb(), monsterList);
			}
			if (traceKey.get(KeyEvent.VK_UP)) {
				bomber.setStatus(UP);
				bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_up.png")).getImage());
				bomberService.move(bomber, UP, wallList, boxList, bomber.getBomb(), monsterList);
			}
			if (traceKey.get(KeyEvent.VK_DOWN)) {
				bomber.setStatus(DOWN);
				bomber.setImg(new ImageIcon(getClass().getResource("/images/bomber_down.png")).getImage());
				bomberService.move(bomber, DOWN, wallList, boxList, bomber.getBomb(), monsterList);
			}
			if (traceKey.get(KeyEvent.VK_SPACE)) {
				bombService.putBomb(bomber, boxList, wallList);
			}
			monsterService.move(monsterList, wallList, boxList, bomber.getBomb(), bombService.getFire(bomber), bomber);
			monsterService.monsterDie(monsterList, bomber);
			itemService.checkImpactBomberVsItem(itemList, bomber);
			if (bomberService.checkImpactMonster(bomber, monsterList) == true
					|| bomberService.checkImpactBang(bomber, bomber.getBomb()) == true) {
				bomberService.bomberDie(bomber, hightScore);
			}
			if (bomber.getBomb().getTimeLine() > 0) {
				bomber.getBomb().setTimeLine(bomber.getBomb().getTimeLine() - 1);
				if (bomber.getBomb().getTimeLine() == 0) {
					bomber.getBomb().setTimeLinebang(100);
					if (bomber.getItem().getKind() == FIRE) {
						bomber.getItem().setTimelineFire(100);
					}
				}
			}
			if (bomber.getItem().getTimelineFire() > 0) {
				bomber.getItem().setTimelineFire(bomber.getItem().getTimelineFire() - 1);
				if (bomber.getItem().getTimelineFire() == 0)
					bomber.getItem().setKind(0);
			}
			if (bomber.getBomb().getTimeLinebang() > 0) {
				bombService.destroyBox(boxList, bomber.getBomb());
				bomber.getBomb().setTimeLinebang(bomber.getBomb().getTimeLinebang() - 1);
			}
			if (bomber.getItem().getTimeline() > 0) {
				bomberService.usesItem(bomber);
				if (bomber.getItem().getTimeline() == 1) {
					bomber.setItem(new Item(-100, 700, 4));
				}
				bomber.getItem().setTimeline(bomber.getItem().getTimeline() - 1);
			}

			if (bomber.getHEART() > 0 && bomber.getTimelineFinish() == 0 && bomber.getROUND() == 2
					&& monsterList.size() == 0 && bomber.getTimelineRound() == 0) {
				bomber.setWIN(1);
				bomber.setTimelineFinish(500);
			}

			if (bomber.getTimelineRound() > 0) {
				bomber.setTimelineRound(bomber.getTimelineRound() - 1);
				if (bomber.getTimelineRound() == 0) {
					container.getGui().setRound(2);
					boxList = boxService.getListBox(2);
					wallList = wallService.getListWall(2);
					monsterList = monsterService.getAllMonster(2);
					itemList = itemService.getListItem(2);
					bomber.setROUND(2);
					bomber.setDeadlineRound2(4000);
				}
			}
			if (bomber.getTimelineFinish() > 0) {
				bomber.setTimelineFinish(bomber.getTimelineFinish() - 1);
				if (bomber.getTimelineFinish() == 0 || bomber.getHEART() <= 0) {
					bomber = new Bomber(0, 545, 0, new Bomb(), container.getGui().getNamePlayer());
					container.getGui().setRound(1);
					boxList = boxService.getListBox(1);
					wallList = wallService.getListWall(1);
					monsterList = monsterService.getAllMonster(1);
					itemList = itemService.getListItem(1);
					int index = generalService.checkScoreToSaveFile(bomber.getScore(), hightScore);
					if (index >= -1) {
						String content = bomber.getName() + " : " + bomber.getScore();
						List<String> temp = generalService.addContent(hightScore, content, index);
						generalService.writeFile("src/hight_score/hightScore.txt", temp);
					}
					container.setShowMenu();
				}
			} 
			if (bomber.getDeadlineRound2() > 0) {
				bomber.setDeadlineRound2(bomber.getDeadlineRound2() - 1);
				if (bomber.getDeadlineRound2() == 0) {
					bomber.setLOST(1);
					bomber.setTimelineFinish(500);
				}
			}			
			if (bomber.getHEART() <= 0 && bomber.getTimelineFinish() == 0 && monsterList.size() > 0) {
				bomber.setLOST(1);
				bomber.setTimelineFinish(500);
			}
			repaint();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == jbBack) {
			container.setShowMenu();
		}
	}

}
