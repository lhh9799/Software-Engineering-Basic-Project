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
	private JLabel playerNameLabel, enemyNameLabel;			//각 객체의 이름 라벨
	private JLabel playerHPLabel, enemyHPLabel;					//각 객체의 체력 라벨
	private JPanel playerPanel, enemyPanel;				//그룹화
	private LinkedList<Enemy> enemyLinkedList = new LinkedList<Enemy>();
	private ImageIcon playerImage, enemyImage;
	private JLabel playerImageLabel, enemyImageLabel;
	private JPanel playerSkillButtonPanel;
	private Player player;	//플레이어 선언
	private Image colorImage, gImage;
	private ImageProducer producer;
	private ImageFilter filter;
	private Music Music;
	
//	public void updateEnemy() {
//		//링크드 리스트에서 적 정보 가져옴
//		Enemy e = enemyLinkedList.getFirst();
//		
//		//이미지 업데이트
//		ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
//		enemyImageLabel.setIcon(newEnemyImage);
//		//이름 라벨 업데이트
//		enemyNameLabel.setText(e.name);
//		//체력 라벨 업데이트
//		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1의 체력을 문자열로 변환 후 Label에 업데이트
//	}
		
	public void goGray() {
		System.out.println("goGray 호출됨");
		//링크드 리스트에서 적 정보 가져옴
		Enemy e = enemyLinkedList.getFirst();
		ImageIcon colorImageIcon = new ImageIcon(e.imgPath);
//		ImageIcon colorImageIcon = new ImageIcon("newImg/bongbi.png");
		colorImage = colorImageIcon.getImage();
		
		//흑백으로 변경
		ImageFilter filter = new GrayFilter(true, 50);
		ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
		Image gImage = Toolkit.getDefaultToolkit().createImage(producer);
		
		enemyImageLabel.setIcon(new ImageIcon(gImage));
//		enemyImageLabel.setIcon(colorImageIcon);
		try {
			Thread.sleep(400);			//스레드 일시중지 1000ms(=1s)
		}
		catch (InterruptedException exc) {			//스레드가 중단되었을 때 발생하는 오류. 대기중인 스레드가 깨어나지 못할 때 발생
			System.out.println(exc.getMessage());
		}
	}
	
	public GameActivity(MyJPanel win) {
		System.out.println("게임 액티비티 시작됨");
		setLayout(null);

		player = new Player();
		
		//적의 정보 링크드 리스트에 추가 - 삭제대기
		//적(과제, 중간, 기말), 플레이어 선언 및 링크드 리스트에 추가
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//라벨 설정(전투 정보 표시)
		lbl = new JLabel();
		lbl.setBounds(200, 210, 500, 50);
		lbl.setText("게임을 시작합니다");
		lbl.setHorizontalAlignment(SwingConstants.CENTER); //수평 가운데 정렬
		add(lbl);

		//라벨2 설정(전투 정보 표시2)
		lbl2 = new JLabel();
		lbl2.setBounds(200, 240, 500, 50);
		lbl2.setText(player.name + "의 체력은 " + player.hp + "입니다");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER); //수평 가운데 정렬
		add(lbl2);
		
//		//전투 정보 표시
//		console = new JLabel("<html>전투 정보<br><html/>");
		
//		Music Music = new Music("Pokemon_Battle.mp3", true);
		
		class SkillListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
//				try {
//				goGray();
//				System.out.println("94번");
					JButton b = (JButton) e.getSource();
					String selectedSkillName = b.getText();
					Enemy currentEnemy = enemyLinkedList.getFirst();
					
					
					for(var it : player.skillSetVect) {
						//버튼에 적힌 스킬 이름과 일치하는 인스턴스 찾음 -> 플레이어의 공격 이어짐
						if(it.name.equals(selectedSkillName)) {
							if(it.limit > 0) {	//스킬 횟수 남음
//								goGray();
//								System.out.println("105번");
								String ment = player.attack(currentEnemy, it);	//지금 싸우고 있는 상대를 넣어야 함
								//왼쪽으로 20 이동
								enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								//0.5초 후에 대상 슬라임을 원위치 시킨다.
								new Timer().schedule(new TimerTask() {
									@Override
									public void run() {
										enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
									}
								}, 500);
								lbl.setText(ment);
//								try {
//								Thread.sleep(100);			//스레드 일시중지 1000ms(=1s)
//								}
//								catch (InterruptedException exc) {			//스레드가 중단되었을 때 발생하는 오류. 대기중인 스레드가 깨어나지 못할 때 발생
//									System.out.println(exc.getMessage());
//								}
								break;
							}
							else {
								System.out.println("플레이어 스킬 사용횟수 다 됨");
								JOptionPane.showMessageDialog(null, "공격 횟수가 부족합니다.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						}
					}
					if(currentEnemy.isEnemyAlive()) {
						String ment = currentEnemy.attack(player);	//적의 반격
						lbl2.setText(ment);
						playerHPLabel.setText("HP: " + Integer.toString(player.hp));
						if(!player.isPlayerAlive()) {
							System.out.println("플레이어 죽음. 게임 끝");
							Music.close();	//음악 종료
							win.change("DefeatEndingActivity", "GameActivity");
//							System.exit(0);
						}
					}
					//적이 죽음
					else {
						//흑백으로 변경
						goGray();
						System.out.println("144번");
						enemyLinkedList.removeFirst();	//링크드 리스트 첫 번째 노드 삭제
//						Thread.sleep(400);			//스레드 일시중지 1000ms(=1s)
						if(enemyLinkedList.isEmpty()) {
							//게임 끝 -> 다음 화면으로 넘어가기
							System.out.println("적 모두 처치. 게임 끝");
							Music.close();	//음악 종료
							win.change("VictoryEndingActivity", "GameActivity");
//							System.exit(0);
						}
					}
					updateEnemy();
				}
//				catch (InterruptedException exc) {			//스레드가 중단되었을 때 발생하는 오류. 대기중인 스레드가 깨어나지 못할 때 발생
//					System.out.println(exc.getMessage());
//				}
//			}
		}
		
		//남은 스킬 횟수 + 대미지 JLabel에 표기
		//적 교체 작동안함
		//적 사망 멘트 추가
		//봉비 체력 업데이트 안됨
		//로그인 시도 이후 일어나는 액션
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
//						//버튼에 적힌 스킬 이름과 일치하는 인스턴스 찾음 -> 플레이어의 공격 이어짐
//						if(it.name.equals(selectedSkillName)) {
//							if(it.limit > 0) {	//스킬 횟수 남음
//								String ment = player.attack(currentEnemy, it);	//지금 싸우고 있는 상대를 넣어야 함
//								//왼쪽으로 20 이동
//								enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
//								//0.5초 후에 대상 슬라임을 원위치 시킨다.
//								new Timer().schedule(new TimerTask() {
//									@Override
//									public void run() {
//										enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
//									}
//								}, 500);
//								lbl.setText(ment);
////								Thread.sleep(100);			//스레드 일시중지 1000ms(=1s)
//								break;
//							}
//							else {
//								System.out.println("플레이어 스킬 사용횟수 다 됨");
//								return;
//							}
//						}
//					}
//					if(currentEnemy.isEnemyAlive()) {
//						String ment = currentEnemy.attack(player);	//적의 반격
//						lbl2.setText(ment);
//						playerHPLabel.setText("HP: " + Integer.toString(player.hp));
//						if(!player.isPlayerAlive()) {
//							System.out.println("플레이어 죽음. 게임 끝");
//							System.exit(0);
//						}
//					}
//					//적이 죽음
//					else {
//						//흑백으로 변경
//						goGray();
//						Thread.sleep(3000);			//스레드 일시중지 1000ms(=1s)
//						enemyLinkedList.removeFirst();	//링크드 리스트 첫 번째 노드 삭제
////							Thread.sleep(400);			//스레드 일시중지 1000ms(=1s)
//						if(enemyLinkedList.isEmpty()) {
//							//게임 끝 -> 다음 화면으로 넘어가기
//							System.out.println("적 모두 처치. 게임 끝");
//							System.exit(0);
//						}
//					}
//					changeEnemy();
//				}
//				catch (InterruptedException exc) {			//스레드가 중단되었을 때 발생하는 오류. 대기중인 스레드가 깨어나지 못할 때 발생
//					System.out.println(exc.getMessage());
//				}
//			}
//		}
		
		//플레이어 스킬 Button에 텍스트 지정 및 버튼 패널에 추가
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());
		playerSkillButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		playerSkillButtonPanel.setBounds(10, 350, 200, 80);
		int numberOfPlayerSkills = player.skillSetVect.size();	//스킬의 가짓수
		playerSkillButton = new JButton[numberOfPlayerSkills];
		
		for(int i=0; i<player.skillSetVect.size(); i++) {
			playerSkillButton[i] = new JButton();
			Skill s = player.skillSetVect.elementAt(i);
//			String SkillName = s.name;
//			String SkillLimit = s.limit;
//			String ment = s.name + "\n" + "잔여 횟수: " + s.limit;
//			playerSkillButton[i].setText(s.name + "<html><br><html/>" + s.limit);
//			console = new JLabel("<html>전투 정보<br><html/>");
			playerSkillButton[i].setText(s.name);
			playerSkillButton[i].addActionListener(new SkillListener());	//리스너 구현하기
			playerSkillButtonPanel.add(playerSkillButton[i]);
		}
		add(playerSkillButtonPanel);				//버튼섬을 패널에 추가
		
		//=======플레이어 패널=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(10, 150, 150, 150);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(playerPanel);
		//과제 패널에 추가할 이미지 - 이미지는 적의 종류에 따라 달라짐
		playerImage = new ImageIcon(player.imgPath);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 100, 100);
		playerImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		playerPanel.add(playerImageLabel);
		//과제 패널에 추가할 이름 라벨
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(65, 0, 50, 20);		//이름 라벨 위치 설정
		playerNameLabel.setText(player.name);
		playerPanel.add(playerNameLabel);
		//과제 패널에 추가할 체력 라벨
		playerHPLabel = new JLabel();			//과제의 체력 라벨 inflate
		playerHPLabel.setBounds(60, 20, 50, 20);		//과제 라벨 위치 설정
		playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//e1의 체력을 문자열로 변환 후 Label에 업데이트
		playerPanel.add(playerHPLabel);
		
		summonEnemy(enemyLinkedList.getFirst());	//첫 번재 몬스터 과제 추가
//		gameStart();
	}

//	public void gameStart() {
//		//적의 링크드 리스트
//		while(!enemyLinkedList.isEmpty()) {
//			Enemy e = enemyLinkedList.getFirst();
//			summonMonster(e);
////			if(!e.isEnemyAlive()) {
////				enemyLinkedList.removeFirst();				
////			}
//			enemyLinkedList.removeFirst();
//			System.out.println("실행중");
//		}
//	}
	
	public void updatePlayerNameLabel(String Username) {
		player.playerNameChange(Username);
		playerNameLabel.setText(Username);
	}
	
	public void summonEnemy(Enemy e) {
		//=======적 패널 템플릿=======
		enemyPanel = new JPanel();
		enemyPanel.setLayout(null);
//		enemyPanel.setBounds(10, 10, 150, 150);
		enemyPanel.setBounds(450, 10, 150, 150);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(enemyPanel);
		//과제 패널에 추가할 이미지 - 이미지는 적의 종류에 따라 달라짐
		enemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 100, 100);
		enemyImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		enemyPanel.add(enemyImageLabel);
		//과제 패널에 추가할 이름 라벨
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(65, 0, 50, 20);		//이름 라벨 위치 설정
		enemyNameLabel.setText(e.name);
		enemyPanel.add(enemyNameLabel);
		//과제 패널에 추가할 체력 라벨
		enemyHPLabel = new JLabel();			//과제의 체력 라벨 inflate
		enemyHPLabel.setBounds(60, 20, 50, 20);		//과제 라벨 위치 설정
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1의 체력을 문자열로 변환 후 Label에 업데이트
		enemyPanel.add(enemyHPLabel);
	}
	
	public void updateEnemy() {
		if(!enemyLinkedList.isEmpty()) {
			//링크드 리스트에서 적 정보 가져옴
			Enemy e = enemyLinkedList.getFirst();
			
			//이미지 업데이트
			ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
			enemyImageLabel.setIcon(newEnemyImage);
			//이름 라벨 업데이트
			enemyNameLabel.setText(e.name);
			//체력 라벨 업데이트
			enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//e1의 체력을 문자열로 변환 후 Label에 업데이트
		}
	}
	public void init() {
		Music = new Music("music/poke.mp3", true);
		Music.start();
	}
}
