import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
	private Music Music;								//전투 BGM 객체
	
	public GameActivity(MyJPanel win) {
		setLayout(null);								//배치관리자를 null로 지정(모든 컴포넌트의 위치를 직접 지정)
		setBackground(Color.decode("#fad0c4"));			//JFrame의 배경색 설정
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5)));//테두리 설정
		
		//적(과제, 중간, 기말) 인스턴스 생성 및 링크드 리스트에 추가
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//레이블1 설정(전투 정보 표시1)
		lbl1 = new JLabel();
		lbl1.setBounds(240, 410, 700, 50);
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbl1.setText("게임을 시작합니다");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER); //수평 가운데 정렬
		add(lbl1);

		//레이블2 설정(전투 정보 표시2)
		lbl2 = new JLabel();
		lbl2.setBounds(240, 450, 700, 50);
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
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
					//버튼에서 추출한 문자열이 플레이어의 스킬명을 포함하는 경우 -> 플레이어의 공격 이어짐
					if(selectedSkillName.contains(it.name)) {
						if(it.limit > 0) {										//스킬의 남은 사용 횟수가 0보다 크다면(사용가능한 경우)
							String report = player.attack(currentEnemy, it);	//현재 대치하는 적의 정보와 스킬의 정보를 인자로 넘김(report: 플레이어가 가한 대미지, 적의 남은 체력이 담긴 문자열)
							//적의 이미지를 왼쪽으로 20 이동(피격 이펙트)
							b.setText("<html> <center>" + it.name + "<br>" + it.limit + "</center> </html>");	//갱신된 남은 횟수를 버튼의 텍스트에 반영
							enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
							//0.5초 후에 적을 원위치 시킨다.
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								}
							}, 500);
							lbl1.setText(report);					//플레이어가 선택한 스킬명과 대미지, 적의 남은 체력을 레이블에 표시
							break;									//다음 for문을 실행하는 것을 건너뜀(이미 버튼의 문자열과 일치하는 스킬정보를 찾았으므로)
						}
						else {										//스킬의 남은 사용 횟수가 0인 경우
							//대화상자로 플레이어의 스킬 사용횟수가 없음을 알림
							JOptionPane.showMessageDialog(null, "공격 횟수가 부족합니다.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
							return;									//아래 코드 실행을 못 하도록 리턴
						}
					}
				}
				
				//아래 코드는 플레이어의 스킬이 사용된 후 실행(남은 사용 횟수가 부족하여 attack 함수가 실행되지 않는 경우 실행되지 않음)
				if(currentEnemy.isEnemyAlive()) {					//적이 살아있다면 -> 적의 반격
					skillButtonDisable();							//플레이어 스킬 버튼 비활성화(1.2초 뒤 활성화 됨)
					//0.5초 후에 대상 플레이어를 원위치 시킨다.
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							String report = currentEnemy.attack(player);	//적의 attack 함수 호출(report: 적이 가한 대미지, 플레이어의 남은 체력이 담긴 문자열)
							playerImageLabel.setBounds(playerImageLabel.getX() - 20, playerImageLabel.getY(), playerImageLabel.getWidth(), playerImageLabel.getHeight());
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									playerImageLabel.setBounds(playerImageLabel.getX() + 20, playerImageLabel.getY(), playerImageLabel.getWidth(), playerImageLabel.getHeight());
								}
							}, 500);
							
							lbl2.setText(report);							//적이 사용한 스킬명과 대미지, 플레이어의 남은 체력을 레이블에 표시
							playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//플레이어의 체력 레이블 갱신
							if(!player.isPlayerAlive()) {					//플레이어가 죽은 경우
								Music.close();								//음악 종료
								win.change("defeatEndingActivity");			//패배 엔딩으로 창 전환
							}
							skillButtonEnable();							//플레이어 스킬 버튼 활성화
						}
					}, 1200);
				}
				
				//적이 죽은 경우
				else {
					enemyLinkedList.removeFirst();					//링크드 리스트의 첫 번째 노드 삭제
					if(enemyLinkedList.isEmpty()) {					//링크드 리스트가 비어있다면(더 이상 나타날 적이 없음)
						Music.close();								//음악 종료
						win.change("victoryEndingActivity");		//승리 엔딩으로 창 전환
					}
				}
				updateEnemy();										//적 패널 업데이트
				}
		}
		
		//플레이어 스킬 버튼을 담을 패널
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());			//FlowLayout을 사용(왼쪽에서 오른쪽, 위에서 아래로 배치)
		playerSkillButtonPanel.setBorder(new LineBorder(Color.BLACK, 1));
		playerSkillButtonPanel.setBounds(50, 400, 230, 125);
		playerSkillButtonPanel.setBackground(Color.WHITE);
		int numberOfPlayerSkills = player.skillSetVect.size();		//벡터의 크기 == 플레이어 스킬의 가짓수
		playerSkillButton = new JButton[numberOfPlayerSkills];		//플레이어 스킬의 가짓수만큼 버튼 배열 할당
		
		for(int i=0; i<player.skillSetVect.size(); i++) {			//스킬의 수 번 반복하여
			playerSkillButton[i] = new JButton();					//i번째 버튼 인스턴스 생성
			Skill s = player.skillSetVect.elementAt(i);				//플레이어 스킬 벡터의 i번째 원소를 Skill형 변수 s에 저장
			playerSkillButton[i].setText("<html> <center>" + s.name + "<br>" + s.limit + "</center> </html>");	//s에서 스킬명과 남은 횟수을 추출해 i번째 버튼의 텍스트로 지정
			playerSkillButton[i].addActionListener(new SkillListener());	//i번째 버튼에 리스너 등록
			playerSkillButton[i].setBackground(Color.WHITE);		//버튼에 색 설정
			playerSkillButton[i].setFont(new Font("맑은 고딕", Font.BOLD, 15));
			playerSkillButtonPanel.add(playerSkillButton[i]);		//플레이어 스킬 버튼을 담는 패널에 버튼 부착
		}
		add(playerSkillButtonPanel);								//플레이어 스킬 버튼을 담는 패널을 GameActivity패널에 추가
		
		//=======플레이어 패널=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(65, 180, 200, 200);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		playerPanel.setBackground(Color.WHITE);
		add(playerPanel);
		//플레이어 패널에 추가할 이미지
		playerImage = new ImageIcon(player.imgPath);
		Image img = playerImage.getImage();							//기존 이미지아이콘을 가져옴
		int aimHeight = 140;										//수정할 세로길이: 140픽셀
		double ratio = (double) aimHeight / img.getHeight(null);	//축소 비율: 이미지의 세로길이 / 140픽셀
		double aimWidth_d = ratio * img.getWidth(null);				//수정할 가로길이 계산(double): 이미지의 가로 길이 * ratio
		int aimWidth = (int) aimWidth_d;							//getScaledInstance의 인자는 int형이어야 하므로 int로 타입캐스팅
		Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);	//사이즈변경 (Image.SCALE_SMOOTH: 속도보다 이미지의 품질 우선)
		playerImage = new ImageIcon(sizeChangedImage);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 150, 150);
		playerPanel.add(playerImageLabel);
		//플레이어 패널에 추가할 이름 라벨
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(0, 0, 200, 20);
		playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
		playerNameLabel.setText(player.name);
		playerNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		playerPanel.add(playerNameLabel);
		//플레이어 패널에 추가할 체력 라벨
		playerHPLabel = new JLabel();
		playerHPLabel.setBounds(0, 20, 200, 20);
		playerHPLabel.setHorizontalAlignment(JLabel.CENTER);
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
		enemyPanel.setBounds(650, 50, 200, 200);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		enemyPanel.setBackground(Color.WHITE);
		add(enemyPanel);
		//적 패널에 추가할 이미지 - 이미지는 적의 종류에 따라 달라짐
		enemyImage = new ImageIcon(e.imgPath);
		Image img = enemyImage.getImage();
		int aimHeight = 140;										//수정할 세로길이: 140픽셀
		double ratio = (double) aimHeight / img.getHeight(null);	//축소 비율: 이미지의 세로길이 / 140픽셀
		double aimWidth_d = ratio * img.getWidth(null);				//수정할 가로길이 계산(double): 이미지의 가로 길이 * ratio
		int aimWidth = (int) aimWidth_d;							//getScaledInstance의 인자는 int형이어야 하므로 int로 타입캐스팅
		Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);
		enemyImage = new ImageIcon(sizeChangedImage);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 150, 150);
		enemyPanel.add(enemyImageLabel);
		//적 패널에 추가할 이름 라벨
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(0, 0, 200, 20);
		enemyNameLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyNameLabel.setText(e.name);
		enemyNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		enemyPanel.add(enemyNameLabel);
		//적 패널에 추가할 체력 라벨
		enemyHPLabel = new JLabel();
		enemyHPLabel.setBounds(0, 20, 200, 20);
		enemyHPLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//적의 체력을 문자열로 변환 후 Label에 업데이트
		enemyHPLabel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		enemyPanel.add(enemyHPLabel);
	}
	
	//적 패널에 피격된 적의 정보를 갱신하는 메소드
	public void updateEnemy() {
		//링크드 리스트가 비어있지 않다면(적이 더 있다면) 링크드 리스트에서 적 정보 가져옴
		if(!enemyLinkedList.isEmpty()) {
			Enemy e = enemyLinkedList.getFirst();
			
			//이미지 업데이트(적이 사망한 경우 갱신 필요)
			ImageIcon originalImageIcon = new ImageIcon(e.imgPath);
			Image img = originalImageIcon.getImage();
			int aimHeight = 140;										//수정할 세로길이: 100픽셀
			double ratio = (double) aimHeight / img.getHeight(null);	//축소 비율: 이미지의 세로길이 / 100픽셀
			double aimWidth_d = ratio * img.getWidth(null);				//수정할 가로길이 계산(double): 이미지의 가로 길이 * ratio
			int aimWidth = (int) aimWidth_d;							//getScaledInstance의 인자는 int형이어야 하므로 int로 타입캐스팅
			Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);
			ImageIcon newEnemyImageIcon = new ImageIcon(sizeChangedImage);
			enemyImageLabel.setIcon(newEnemyImageIcon);
			//이름 라벨 업데이트
			enemyNameLabel.setText(e.name);
			//체력 라벨 업데이트
			enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//적의 체력을 문자열로 변환 후 Label에 업데이트
		}
	}
	
	//사용자 스킬 버튼 활성화
	public void skillButtonEnable() {
		for(var it : playerSkillButton) {
			it.setEnabled(true);
		}
	}
	
	//사용자 스킬 버튼 비활성화
	public void skillButtonDisable() {
		for(var it : playerSkillButton) {
			it.setEnabled(false);
		}
	}
	
	public void init() {
		Music = new Music("music/poke.mp3", true);
		Music.start();
	}
}
