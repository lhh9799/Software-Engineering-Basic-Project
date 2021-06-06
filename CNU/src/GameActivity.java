import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GameActivity extends JPanel {
	private JLabel lbl1, lbl2;							//전투 정보를 표시할 레이블
	private JLabel playerNameLabel, enemyNameLabel;		//플레이어, 적의 이름 레이블
	private JLabel playerHPLabel, enemyHPLabel;			//플레이어, 적의 체력 레이블
	private JButton playerSkillButton[];				//플레이어의 스킬 버튼 배열
	private JPanel playerSkillButtonPanel;				//플레이어의 스킬 버튼들을 담는 패널
	private JPanel playerPanel, enemyPanel;				//플레이어, 적의 정보를 저장하는 컴포넌트들을 담는 패널 
	private ImageIcon playerImage, enemyImage;			//플레이어, 적의 이미지 아이콘
	private JLabel playerImageLabel, enemyImageLabel;	//플레이어, 적의 이미지 아이콘이 담길 레이블
	private Player player = new Player();				//플레이어 인스턴스 선언 및 생성
	private LinkedList<Enemy> enemyLinkedList = new LinkedList<Enemy>();	//적들이 저장될 링크드 리스트(특징: 노드의 삭제가 용이함)
	
	public GameActivity(MyJPanel win) {
		setLayout(null);		//배치관리자를 null로 지정(모든 컴포넌트의 위치를 직접 지정)
		
		//적(과제, 중간, 기말) 인스턴스 생성 및 링크드 리스트에 추가
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//레이블1 설정(전투 정보 표시1)
		lbl1 = new JLabel();
		lbl1.setBounds(200, 210, 500, 50);
		lbl1.setText("게임을 시작합니다");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER); //수평 가운데 정렬
		add(lbl1);

		//레이블2 설정(전투 정보 표시2)
		lbl2 = new JLabel();
		lbl2.setBounds(200, 240, 500, 50);
		lbl2.setText(player.name + "의 체력은 " + player.hp + "입니다");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER); //수평 가운데 정렬
		add(lbl2);
		
		//플레이어의 스킬 버튼이 눌렸을 때 작동하는 액션리스너
		class SkillListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {			//버튼 클릭 시 호출되는 메소드
				JButton b = (JButton) e.getSource();				//클릭된 버튼을 알아냄
				String selectedSkillName = b.getText();				//버튼의 문자열을 selectedSkillName 변수에 저장
				Enemy currentEnemy = enemyLinkedList.getFirst();	//현재 대치하는 적의 정보를 currentEnemy 변수에 저장
				
				for(var it : player.skillSetVect) {					//for-each문으로 플레이어 스킬 벡터를 순회
					//버튼에 적힌 스킬 이름과 일치하는 인스턴스 찾음 -> 플레이어의 공격 이어짐
					if(it.name.equals(selectedSkillName)) {
						if(it.limit > 0) {										//스킬의 남은 사용 횟수가 0보다 크다면(사용가능한 경우)
							String report = player.attack(currentEnemy, it);	//현재 대치하는 적의 정보와 스킬의 정보를 인자로 넘김(report: 플레이어가 가한 대미지, 적의 남은 체력이 담긴 문자열)
							//적의 이미지를 왼쪽으로 20 이동(피격 이펙트)
							enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
							//0.5초 후에 대상 슬라임을 원위치 시킨다.
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								}
							}, 500);
							lbl1.setText(report);					//플레이어가 선택한 스킬명과 대미지, 적의 남은 체력을 레이블에 표시
							
							try {
								Thread.sleep(100);					//스레드 일시중지 1000ms(=1s)
							}
							catch (InterruptedException exc) {		//스레드가 중단되었을 때 발생할 수 있는 오류의 예외처리(대기중인 스레드가 깨어나지 못할 때 발생)
								System.out.println(exc.getMessage());
							}
							break;									//다음 for문을 실행하는 것을 건너뜀(이미 버튼의 문자열과 일치하는 스킬정보를 찾았으므로)
						}
						else {										//스킬의 남은 사용 횟수가 0인 경우
							System.out.println("스킬 사용횟수 부족");		//콘솔에 플레이어의 스킬 사용횟수가 없음을 알림
							return;									//아래 코드 실행을 못 하도록 리턴
						}
					}
				}
				
				//아래 코드는 플레이어의 스킬이 사용된 후 실행(남은 사용 횟수가 부족하여 attack 함수가 실행되지 않는 경우 실행되지 않음)
				if(currentEnemy.isEnemyAlive()) {					//적이 살아있다면 -> 적의 반격
					String report = currentEnemy.attack(player);	//적의 attack 함수 호출(report: 적이 가한 대미지, 플레이어의 남은 체력이 담긴 문자열)
					lbl2.setText(report);							//적이 사용한 스킬명과 대미지, 플레이어의 남은 체력을 레이블에 표시
					playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//플레이어의 체력 레이블 갱신
					if(!player.isPlayerAlive()) {					//플레이어가 죽은 경우
						System.out.println("플레이어 죽음. 게임 끝");		//콘솔에 플레이어가 죽음을 알림
						System.exit(0);								//프로그램 종료
					}
				}
				
				//적이 죽은 경우
				else {
					enemyLinkedList.removeFirst();					//링크드 리스트의 첫 번째 노드 삭제
					if(enemyLinkedList.isEmpty()) {					//링크드 리스트가 비어있다면(더 이상 나타날 적이 없음)
						System.out.println("적 모두 처치. 게임 끝");		//콘솔에 게임이 종료됨을 알림
						System.exit(0);								//프로그램 종료
					}
				}
				changeEnemy();
				}
		}
		
		//플레이어 스킬 버튼을 담을 패널
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());			//FlowLayout을 사용(왼쪽에서 오른쪽, 위에서 아래로 배치)
		playerSkillButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		playerSkillButtonPanel.setBounds(10, 350, 200, 80);
		int numberOfPlayerSkills = player.skillSetVect.size();		//벡터의 크기 == 플레이어 스킬의 가짓수
		playerSkillButton = new JButton[numberOfPlayerSkills];		//플레이어 스킬의 가짓수만큼 버튼 배열 할당
		
		for(int i=0; i<player.skillSetVect.size(); i++) {			//스킬의 수 번 반복하여
			playerSkillButton[i] = new JButton();					//i번째 버튼 인스턴스 생성
			Skill s = player.skillSetVect.elementAt(i);				//플레이어 스킬 벡터의 i번째 원소를 Skill형 변수 s에 저장
			playerSkillButton[i].setText(s.name);					//s에서 스킬명을 추출해 i번째 버튼의 텍스트로 지정
			playerSkillButton[i].addActionListener(new SkillListener());	//i번째 버튼에 리스너 등록
			playerSkillButtonPanel.add(playerSkillButton[i]);		//플레이어 스킬 버튼을 담는 패널에 버튼 부착
		}
		add(playerSkillButtonPanel);								//플레이어 스킬 버튼을 담는 패널을 GameActivity패널에 추가
		
		//=======플레이어 패널=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(10, 150, 150, 150);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(playerPanel);
		//플레이어 패널에 추가할 이미지
		playerImage = new ImageIcon(player.imgPath);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 100, 100);
		playerImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		playerPanel.add(playerImageLabel);
		//플레이어 패널에 추가할 이름 라벨
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(65, 0, 50, 20);
		playerNameLabel.setText(player.name);
		playerPanel.add(playerNameLabel);
		//플레이어 패널에 추가할 체력 라벨
		playerHPLabel = new JLabel();
		playerHPLabel.setBounds(60, 20, 50, 20);
		playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//플레이어의 체력을 문자열로 변환 후 Label에 업데이트
		playerPanel.add(playerHPLabel);
		
		summonEnemy(enemyLinkedList.getFirst());	//첫 번재 적(과제)을 적 패널 추가
	}	//GameActivity 생성자의 끝
	
	//플레이어의 이름을 변경하는 메소드(IntroActivity에서 확인버튼을 누르면 호출됨)
	public void updatePlayerNameLabel(String Username) {
		player.playerNameChange(Username);			//player 인스턴스의 이름 변경
		playerNameLabel.setText(Username);			//플레이어의 이름 레이블 변경
	}
	
	//링크드 리스트의 첫 번째 적을 패널에 추가하는 메소드 
	public void summonEnemy(Enemy e) {
		//=======적 패널=======
		enemyPanel = new JPanel();
		enemyPanel.setLayout(null);
		enemyPanel.setBounds(450, 10, 150, 150);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(enemyPanel);
		//적 패널에 추가할 이미지 - 이미지는 적의 종류에 따라 달라짐
		enemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 100, 100);
		enemyImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		enemyPanel.add(enemyImageLabel);
		//적 패널에 추가할 이름 라벨
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(65, 0, 50, 20);
		enemyNameLabel.setText(e.name);
		enemyPanel.add(enemyNameLabel);
		//적 패널에 추가할 체력 라벨
		enemyHPLabel = new JLabel();
		enemyHPLabel.setBounds(60, 20, 50, 20);
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//적의 체력을 문자열로 변환 후 Label에 업데이트
		enemyPanel.add(enemyHPLabel);
	}
	
	//적 패널에 피격된 적의 정보를 갱신하는 메소드
	public void changeEnemy() {
		//링크드 리스트에서 적 정보 가져옴
		Enemy e = enemyLinkedList.getFirst();
		
		//이미지 업데이트(적이 사망한 경우 갱신 필요)
		ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel.setIcon(newEnemyImage);
		//이름 라벨 업데이트
		enemyNameLabel.setText(e.name);
		//체력 라벨 업데이트
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//적의 체력을 문자열로 변환 후 Label에 업데이트
	}
}
