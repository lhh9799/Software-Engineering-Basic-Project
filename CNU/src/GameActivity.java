import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;
public class GameActivity extends JPanel {
	private JLabel lbl, lbl2;
	private JLabel console;
	private JButton btn1, btn2, btn3, playerSkillButton[];
	private JLabel playerNameLabel, enemyNameLabel;			//�� ��ü�� �̸� ��
	private JLabel playerHPLabel, enemyHPLabel;					//�� ��ü�� ü�� ��
	private JPanel playerPanel, enemyPanel;				//�׷�ȭ
	private LinkedList<Enemy> enemyLinkedList = new LinkedList<Enemy>();
	private ImageIcon playerImage, enemyImage;
	private JLabel playerImageLabel, enemyImageLabel;
	private JPanel playerSkillButtonPanel;
	private Player player;	//�÷��̾� ����
	private Image colorImage, gImage;
	private ImageProducer producer;
	private ImageFilter filter;
	private Music Music;
	
//	public void updateEnemy() {
//		//��ũ�� ����Ʈ���� �� ���� ������
//		Enemy e = enemyLinkedList.getFirst();
//		
//		//�̹��� ������Ʈ
//		ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
//		enemyImageLabel.setIcon(newEnemyImage);
//		//�̸� �� ������Ʈ
//		enemyNameLabel.setText(e.name);
//		//ü�� �� ������Ʈ
//		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
//	}
		
	public void goGray() {
		System.out.println("goGray ȣ���");
		//��ũ�� ����Ʈ���� �� ���� ������
		Enemy e = enemyLinkedList.getFirst();
		ImageIcon colorImageIcon = new ImageIcon(e.imgPath);
//		ImageIcon colorImageIcon = new ImageIcon("newImg/bongbi.png");
		colorImage = colorImageIcon.getImage();
		
		//������� ����
		ImageFilter filter = new GrayFilter(true, 50);
		ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
		Image gImage = Toolkit.getDefaultToolkit().createImage(producer);
		
		enemyImageLabel.setIcon(new ImageIcon(gImage));
//		enemyImageLabel.setIcon(colorImageIcon);
		try {
			Thread.sleep(400);			//������ �Ͻ����� 1000ms(=1s)
		}
		catch (InterruptedException exc) {			//�����尡 �ߴܵǾ��� �� �߻��ϴ� ����. ������� �����尡 ����� ���� �� �߻�
			System.out.println(exc.getMessage());
		}
	}
	
	public GameActivity(MyJPanel win) {
		System.out.println("���� ��Ƽ��Ƽ ���۵�");
		setLayout(null);

		player = new Player();
		
		//���� ���� ��ũ�� ����Ʈ�� �߰� - �������
		//��(����, �߰�, �⸻), �÷��̾� ���� �� ��ũ�� ����Ʈ�� �߰�
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//�� ����(���� ���� ǥ��)
		lbl = new JLabel();
		lbl.setBounds(200, 210, 500, 50);
		lbl.setText("������ �����մϴ�");
		lbl.setHorizontalAlignment(SwingConstants.CENTER); //���� ��� ����
		add(lbl);

		//��2 ����(���� ���� ǥ��2)
		lbl2 = new JLabel();
		lbl2.setBounds(200, 240, 500, 50);
		lbl2.setText(player.name + "�� ü���� " + player.hp + "�Դϴ�");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER); //���� ��� ����
		add(lbl2);
		
//		//���� ���� ǥ��
//		console = new JLabel("<html>���� ����<br><html/>");
		
//		Music Music = new Music("Pokemon_Battle.mp3", true);
		
		class SkillListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
//				goGray();
//				System.out.println("94��");
					JButton b = (JButton) e.getSource();
					String selectedSkillName = b.getText();
					Enemy currentEnemy = enemyLinkedList.getFirst();
					
					
					for(var it : player.skillSetVect) {
						//��ư�� ���� ��ų �̸��� ��ġ�ϴ� �ν��Ͻ� ã�� -> �÷��̾��� ���� �̾���
						if(it.name.equals(selectedSkillName)) {
							if(it.limit > 0) {	//��ų Ƚ�� ����
//								goGray();
//								System.out.println("105��");
								String ment = player.attack(currentEnemy, it);	//���� �ο�� �ִ� ��븦 �־�� ��
								//�������� 20 �̵�
								enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								//0.5�� �Ŀ� ��� �������� ����ġ ��Ų��.
								new Timer().schedule(new TimerTask() {
									@Override
									public void run() {
										enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
									}
								}, 500);
								lbl.setText(ment);
//								try {
//								Thread.sleep(100);			//������ �Ͻ����� 1000ms(=1s)
//								}
//								catch (InterruptedException exc) {			//�����尡 �ߴܵǾ��� �� �߻��ϴ� ����. ������� �����尡 ����� ���� �� �߻�
//									System.out.println(exc.getMessage());
//								}
								break;
							}
							else {
								System.out.println("�÷��̾� ��ų ���Ƚ�� �� ��");
								JOptionPane.showMessageDialog(null, "���� Ƚ���� �����մϴ�.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
					}
					if(currentEnemy.isEnemyAlive()) {
						String ment = currentEnemy.attack(player);	//���� �ݰ�
						lbl2.setText(ment);
						playerHPLabel.setText("HP: " + Integer.toString(player.hp));
						if(!player.isPlayerAlive()) {
							System.out.println("�÷��̾� ����. ���� ��");
							Music.close();	//���� ����
							win.change("DefeatEndingActivity", "GameActivity");
//							System.exit(0);
						}
					}
					//���� ����
					else {
						//������� ����
						goGray();
						System.out.println("144��");
						enemyLinkedList.removeFirst();	//��ũ�� ����Ʈ ù ��° ��� ����
//						Thread.sleep(400);			//������ �Ͻ����� 1000ms(=1s)
						if(enemyLinkedList.isEmpty()) {
							//���� �� -> ���� ȭ������ �Ѿ��
							System.out.println("�� ��� óġ. ���� ��");
							Music.close();	//���� ����
							win.change("VictoryEndingActivity", "GameActivity");
//							System.exit(0);
						}
					}
					updateEnemy();
				}
//				catch (InterruptedException exc) {			//�����尡 �ߴܵǾ��� �� �߻��ϴ� ����. ������� �����尡 ����� ���� �� �߻�
//					System.out.println(exc.getMessage());
//				}
//			}
		}
		
		//���� ��ų Ƚ�� + ����� JLabel�� ǥ��
		//�� ��ü �۵�����
		//�� ��� ��Ʈ �߰�
		//���� ü�� ������Ʈ �ȵ�
		//�α��� �õ� ���� �Ͼ�� �׼�
//		class SkillListener implements ActionListener {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try {
//					JButton b = (JButton) e.getSource();
//					String selectedSkillName = b.getText();
//					Enemy currentEnemy = enemyLinkedList.getFirst();
//					
//					goGray();
//					
//					for(var it : player.skillSetVect) {
//						//��ư�� ���� ��ų �̸��� ��ġ�ϴ� �ν��Ͻ� ã�� -> �÷��̾��� ���� �̾���
//						if(it.name.equals(selectedSkillName)) {
//							if(it.limit > 0) {	//��ų Ƚ�� ����
//								String ment = player.attack(currentEnemy, it);	//���� �ο�� �ִ� ��븦 �־�� ��
//								//�������� 20 �̵�
//								enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
//								//0.5�� �Ŀ� ��� �������� ����ġ ��Ų��.
//								new Timer().schedule(new TimerTask() {
//									@Override
//									public void run() {
//										enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
//									}
//								}, 500);
//								lbl.setText(ment);
////								Thread.sleep(100);			//������ �Ͻ����� 1000ms(=1s)
//								break;
//							}
//							else {
//								System.out.println("�÷��̾� ��ų ���Ƚ�� �� ��");
//								return;
//							}
//						}
//					}
//					if(currentEnemy.isEnemyAlive()) {
//						String ment = currentEnemy.attack(player);	//���� �ݰ�
//						lbl2.setText(ment);
//						playerHPLabel.setText("HP: " + Integer.toString(player.hp));
//						if(!player.isPlayerAlive()) {
//							System.out.println("�÷��̾� ����. ���� ��");
//							System.exit(0);
//						}
//					}
//					//���� ����
//					else {
//						//������� ����
//						goGray();
//						Thread.sleep(3000);			//������ �Ͻ����� 1000ms(=1s)
//						enemyLinkedList.removeFirst();	//��ũ�� ����Ʈ ù ��° ��� ����
////							Thread.sleep(400);			//������ �Ͻ����� 1000ms(=1s)
//						if(enemyLinkedList.isEmpty()) {
//							//���� �� -> ���� ȭ������ �Ѿ��
//							System.out.println("�� ��� óġ. ���� ��");
//							System.exit(0);
//						}
//					}
//					changeEnemy();
//				}
//				catch (InterruptedException exc) {			//�����尡 �ߴܵǾ��� �� �߻��ϴ� ����. ������� �����尡 ����� ���� �� �߻�
//					System.out.println(exc.getMessage());
//				}
//			}
//		}
		
		//�÷��̾� ��ų Button�� �ؽ�Ʈ ���� �� ��ư �гο� �߰�
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());
		playerSkillButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		playerSkillButtonPanel.setBounds(10, 350, 200, 80);
		int numberOfPlayerSkills = player.skillSetVect.size();	//��ų�� ������
		playerSkillButton = new JButton[numberOfPlayerSkills];
		
		for(int i=0; i<player.skillSetVect.size(); i++) {
			playerSkillButton[i] = new JButton();
			Skill s = player.skillSetVect.elementAt(i);
//			String SkillName = s.name;
//			String SkillLimit = s.limit;
//			String ment = s.name + "\n" + "�ܿ� Ƚ��: " + s.limit;
//			playerSkillButton[i].setText(s.name + "<html><br><html/>" + s.limit);
//			console = new JLabel("<html>���� ����<br><html/>");
			playerSkillButton[i].setText(s.name);
			playerSkillButton[i].addActionListener(new SkillListener());	//������ �����ϱ�
			playerSkillButtonPanel.add(playerSkillButton[i]);
		}
		add(playerSkillButtonPanel);				//��ư���� �гο� �߰�
		
		//=======�÷��̾� �г�=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(10, 150, 150, 150);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(playerPanel);
		//���� �гο� �߰��� �̹��� - �̹����� ���� ������ ���� �޶���
		playerImage = new ImageIcon(player.imgPath);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 100, 100);
		playerImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		playerPanel.add(playerImageLabel);
		//���� �гο� �߰��� �̸� ��
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(65, 0, 50, 20);		//�̸� �� ��ġ ����
		playerNameLabel.setText(player.name);
		playerPanel.add(playerNameLabel);
		//���� �гο� �߰��� ü�� ��
		playerHPLabel = new JLabel();			//������ ü�� �� inflate
		playerHPLabel.setBounds(60, 20, 50, 20);		//���� �� ��ġ ����
		playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//e1�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		playerPanel.add(playerHPLabel);
		
		summonEnemy(enemyLinkedList.getFirst());	//ù ���� ���� ���� �߰�
//		gameStart();
	}

//	public void gameStart() {
//		//���� ��ũ�� ����Ʈ
//		while(!enemyLinkedList.isEmpty()) {
//			Enemy e = enemyLinkedList.getFirst();
//			summonMonster(e);
////			if(!e.isEnemyAlive()) {
////				enemyLinkedList.removeFirst();				
////			}
//			enemyLinkedList.removeFirst();
//			System.out.println("������");
//		}
//	}
	
	public void updatePlayerNameLabel(String Username) {
		player.playerNameChange(Username);
		playerNameLabel.setText(Username);
	}
	
	public void summonEnemy(Enemy e) {
		//=======�� �г� ���ø�=======
		enemyPanel = new JPanel();
		enemyPanel.setLayout(null);
//		enemyPanel.setBounds(10, 10, 150, 150);
		enemyPanel.setBounds(450, 10, 150, 150);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(enemyPanel);
		//���� �гο� �߰��� �̹��� - �̹����� ���� ������ ���� �޶���
		enemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 100, 100);
		enemyImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		enemyPanel.add(enemyImageLabel);
		//���� �гο� �߰��� �̸� ��
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(65, 0, 50, 20);		//�̸� �� ��ġ ����
		enemyNameLabel.setText(e.name);
		enemyPanel.add(enemyNameLabel);
		//���� �гο� �߰��� ü�� ��
		enemyHPLabel = new JLabel();			//������ ü�� �� inflate
		enemyHPLabel.setBounds(60, 20, 50, 20);		//���� �� ��ġ ����
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		enemyPanel.add(enemyHPLabel);
	}
	
	public void updateEnemy() {
		if(!enemyLinkedList.isEmpty()) {
			//��ũ�� ����Ʈ���� �� ���� ������
			Enemy e = enemyLinkedList.getFirst();
			
			//�̹��� ������Ʈ
			ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
			enemyImageLabel.setIcon(newEnemyImage);
			//�̸� �� ������Ʈ
			enemyNameLabel.setText(e.name);
			//ü�� �� ������Ʈ
			enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		}
	}
	public void init() {
		Music = new Music("music/poke.mp3", true);
		Music.start();
	}
}
