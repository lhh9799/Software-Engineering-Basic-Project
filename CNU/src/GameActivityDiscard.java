import java.util.Timer;
import java.util.TimerTask;

//=======과제 패널=======
//		assignmentPanel = new JPanel();
//		assignmentPanel.setLayout(null);
//		assignmentPanel.setBounds(10, 10, 150, 150);
//		assignmentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(assignmentPanel);
//		//과제 패널에 추가할 이미지
//		ImageIcon assignmentImage = new ImageIcon("img/slime(blue).png");
//		JLabel assignmentImageLabel = new JLabel(assignmentImage);
//		assignmentImageLabel.setBounds(25, 40, 100, 100);
//		assignmentImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		assignmentPanel.add(assignmentImageLabel);
//		//과제 패널에 추가할 이름 라벨
//		assignmentNameLabel = new JLabel();
//		assignmentNameLabel.setBounds(65, 0, 50, 20);		//이름 라벨 위치 설정
//		assignmentNameLabel.setText(e1.name);
//		assignmentPanel.add(assignmentNameLabel);
//		//과제 패널에 추가할 체력 라벨
//		assignmentHPLabel = new JLabel();			//과제의 체력 라벨 inflate
//		assignmentHPLabel.setBounds(60, 20, 50, 20);		//과제 라벨 위치 설정
//		assignmentHPLabel.setText("HP: " + Integer.toString(e1.hp));	//e1의 체력을 문자열로 변환 후 Label에 업데이트
//		assignmentPanel.add(assignmentHPLabel);
		
//		//=======중간고사 패널=======
//		midtermsPanel = new JPanel();
//		midtermsPanel.setLayout(null);
//		midtermsPanel.setBounds(182, 10, 150, 150);
//		midtermsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(midtermsPanel);
//		//중간고사 패널에 추가할 이미지
//		ImageIcon midtermsImage = new ImageIcon("img/slime(blue).png");
//		JLabel midtermsImageLabel = new JLabel(midtermsImage);
//		midtermsImageLabel.setBounds(25, 40, 100, 100);
//		midtermsImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		midtermsPanel.add(midtermsImageLabel);
//		//중간고사 패널에 추가할 이름 라벨
//		midtermsNameLabel = new JLabel();
//		midtermsNameLabel.setBounds(55, 0, 50, 20);		//이름 라벨 위치 설정
//		midtermsNameLabel.setText(e2.name);
//		midtermsPanel.add(midtermsNameLabel);
//		//중간고사 패널에 추가할 체력 라벨
//		midtermsHPLabel = new JLabel();					//중간고사의 체력 라벨 inflate
//		midtermsHPLabel.setBounds(60, 20, 50, 20);		//중간고사 라벨 위치 설정
//		midtermsHPLabel.setText("HP: " + Integer.toString(e2.hp));	//e2의 체력을 문자열로 변환 후 Label에 업데이트
//		midtermsPanel.add(midtermsHPLabel);
		
//		//=======기말고사 패널=======
//		finalsPanel = new JPanel();
//		finalsPanel.setLayout(null);
//		finalsPanel.setBounds(354, 10, 150, 150);
//		finalsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(finalsPanel);
//		//기말고사 패널에 추가할 이미지
//		ImageIcon finalsImage = new ImageIcon("img/slime(blue).png");
//		JLabel finalsImageLabel = new JLabel(finalsImage);
//		finalsImageLabel.setBounds(25, 40, 100, 100);
//		finalsImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		finalsPanel.add(finalsImageLabel);
//		//기말고사 패널에 추가할 이름 라벨
//		finalsNameLabel = new JLabel();
//		finalsNameLabel.setBounds(55, 0, 50, 20);		//이름 라벨 위치 설정
//		finalsNameLabel.setText(e3.name);
//		finalsPanel.add(finalsNameLabel);
//		//기말고사 패널에 추가할 체력 라벨
//		finalsHPLabel = new JLabel();				//기말고사의 체력 라벨 inflate
//		finalsHPLabel.setBounds(60, 20, 50, 20);		//기말고사 라벨 위치 설정
//		finalsHPLabel.setText("HP: " + Integer.toString(e3.hp));	//e3의 체력을 문자열로 변환 후 Label에 업데이트
//		finalsPanel.add(finalsHPLabel);

// 슬라임이 반격하는 함수
//		public void counterAttack (Enemy e) {
//			
//			// e가 과제일때: 기본공격만
//			if (e instanceof Enemy && !(e instanceof Enemy2) && !(e instanceof Enemy3)) {			
//				e.attack(player);
//				//attack의 리턴값이 false이면 (플레이어의 체력이 1미만이라면) 게임 끝났다는 메시지
//			}
//			// e가 중간고사일때: 기본공격과 특수스킬 랜덤공격
//			else if (e instanceof Enemy2) {
//				int randomNum = (int) (Math.random()*4);
//				
//				// randomNum이 0,1,2일때
//				if(randomNum < 3) {
//					e.attack(player);
//				}
//				// randomNum이 3일때
//				else {
//					e.specialSkill(h);
//				}
//			}
//			// e가 기말고사일때: 기본공격과 특수스킬 랜덤공격
//			else {
//				int randomNum = (int) (Math.random()*5);
//				
//				// randomNum이 0,1,2,3일때
//				if(randomNum < 4) {
//					e.attack(player);
//				}
//				// randomNum이 4일때
//				else {
//					e.specialSkill(player);
//				}
//			}
//		}
		
		//전투 정보를 갱신하는 함수 정의
//		public void updateConsole {
//			
//		}
////battle1(공부하기)은 지속적으로 20의 공격을 함
//	public void battle1 (Enemy e) {
//		player.attack(e, 20);
//		
//		// 인간의 공격 3초 뒤 슬라임이 공격할 수 있게
//		Timer timer1 = new Timer();
//		TimerTask task1 = new TimerTask() {
//			
//			@Override
//			public void run() {
//			// 일정 시간 후에 수행할 코드: 슬라임의 counterAttack
////				counterAttack(e);
//			}
//		};
//		
//		timer1.schedule(task1, 3000);
//		
//	}
//		
//	//battle2(벼락치기)는 랜덤으로 0,10,100의 공격을 함  
//	public void battle2 (Enemy e) {
//		int randomNum = (int) (Math.random()*5);
//		
//		// 랜덤 공격
//		if(randomNum == 0 || randomNum == 1) {			
//			player.attack(e, 0);
//		}
//		else if(randomNum == 2 || randomNum == 3) {			
//			player.attack(e, 10);			
//		}
//		else {
//			player.attack(e, 100);
//		}
//		
//		// 인간의 공격 3초 뒤 슬라임이 공격할 수 있게
//		Timer timer1 = new Timer();
//		TimerTask task1 = new TimerTask() {
//				
//			@Override
//			public void run() {
//				// 일정 시간 후에 수행할 코드: 슬라임의 counterAttack
////					counterAttack(e);
//			}
//		};
//				
//		timer1.schedule(task1, 3000);
//		
//	}