import java.util.Timer;
import java.util.TimerTask;

//=======���� �г�=======
//		assignmentPanel = new JPanel();
//		assignmentPanel.setLayout(null);
//		assignmentPanel.setBounds(10, 10, 150, 150);
//		assignmentPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(assignmentPanel);
//		//���� �гο� �߰��� �̹���
//		ImageIcon assignmentImage = new ImageIcon("img/slime(blue).png");
//		JLabel assignmentImageLabel = new JLabel(assignmentImage);
//		assignmentImageLabel.setBounds(25, 40, 100, 100);
//		assignmentImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		assignmentPanel.add(assignmentImageLabel);
//		//���� �гο� �߰��� �̸� ��
//		assignmentNameLabel = new JLabel();
//		assignmentNameLabel.setBounds(65, 0, 50, 20);		//�̸� �� ��ġ ����
//		assignmentNameLabel.setText(e1.name);
//		assignmentPanel.add(assignmentNameLabel);
//		//���� �гο� �߰��� ü�� ��
//		assignmentHPLabel = new JLabel();			//������ ü�� �� inflate
//		assignmentHPLabel.setBounds(60, 20, 50, 20);		//���� �� ��ġ ����
//		assignmentHPLabel.setText("HP: " + Integer.toString(e1.hp));	//e1�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
//		assignmentPanel.add(assignmentHPLabel);
		
//		//=======�߰���� �г�=======
//		midtermsPanel = new JPanel();
//		midtermsPanel.setLayout(null);
//		midtermsPanel.setBounds(182, 10, 150, 150);
//		midtermsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(midtermsPanel);
//		//�߰���� �гο� �߰��� �̹���
//		ImageIcon midtermsImage = new ImageIcon("img/slime(blue).png");
//		JLabel midtermsImageLabel = new JLabel(midtermsImage);
//		midtermsImageLabel.setBounds(25, 40, 100, 100);
//		midtermsImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		midtermsPanel.add(midtermsImageLabel);
//		//�߰���� �гο� �߰��� �̸� ��
//		midtermsNameLabel = new JLabel();
//		midtermsNameLabel.setBounds(55, 0, 50, 20);		//�̸� �� ��ġ ����
//		midtermsNameLabel.setText(e2.name);
//		midtermsPanel.add(midtermsNameLabel);
//		//�߰���� �гο� �߰��� ü�� ��
//		midtermsHPLabel = new JLabel();					//�߰������ ü�� �� inflate
//		midtermsHPLabel.setBounds(60, 20, 50, 20);		//�߰���� �� ��ġ ����
//		midtermsHPLabel.setText("HP: " + Integer.toString(e2.hp));	//e2�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
//		midtermsPanel.add(midtermsHPLabel);
		
//		//=======�⸻��� �г�=======
//		finalsPanel = new JPanel();
//		finalsPanel.setLayout(null);
//		finalsPanel.setBounds(354, 10, 150, 150);
//		finalsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
//		add(finalsPanel);
//		//�⸻��� �гο� �߰��� �̹���
//		ImageIcon finalsImage = new ImageIcon("img/slime(blue).png");
//		JLabel finalsImageLabel = new JLabel(finalsImage);
//		finalsImageLabel.setBounds(25, 40, 100, 100);
//		finalsImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
//		finalsPanel.add(finalsImageLabel);
//		//�⸻��� �гο� �߰��� �̸� ��
//		finalsNameLabel = new JLabel();
//		finalsNameLabel.setBounds(55, 0, 50, 20);		//�̸� �� ��ġ ����
//		finalsNameLabel.setText(e3.name);
//		finalsPanel.add(finalsNameLabel);
//		//�⸻��� �гο� �߰��� ü�� ��
//		finalsHPLabel = new JLabel();				//�⸻����� ü�� �� inflate
//		finalsHPLabel.setBounds(60, 20, 50, 20);		//�⸻��� �� ��ġ ����
//		finalsHPLabel.setText("HP: " + Integer.toString(e3.hp));	//e3�� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
//		finalsPanel.add(finalsHPLabel);

// �������� �ݰ��ϴ� �Լ�
//		public void counterAttack (Enemy e) {
//			
//			// e�� �����϶�: �⺻���ݸ�
//			if (e instanceof Enemy && !(e instanceof Enemy2) && !(e instanceof Enemy3)) {			
//				e.attack(player);
//				//attack�� ���ϰ��� false�̸� (�÷��̾��� ü���� 1�̸��̶��) ���� �����ٴ� �޽���
//			}
//			// e�� �߰�����϶�: �⺻���ݰ� Ư����ų ��������
//			else if (e instanceof Enemy2) {
//				int randomNum = (int) (Math.random()*4);
//				
//				// randomNum�� 0,1,2�϶�
//				if(randomNum < 3) {
//					e.attack(player);
//				}
//				// randomNum�� 3�϶�
//				else {
//					e.specialSkill(h);
//				}
//			}
//			// e�� �⸻����϶�: �⺻���ݰ� Ư����ų ��������
//			else {
//				int randomNum = (int) (Math.random()*5);
//				
//				// randomNum�� 0,1,2,3�϶�
//				if(randomNum < 4) {
//					e.attack(player);
//				}
//				// randomNum�� 4�϶�
//				else {
//					e.specialSkill(player);
//				}
//			}
//		}
		
		//���� ������ �����ϴ� �Լ� ����
//		public void updateConsole {
//			
//		}
////battle1(�����ϱ�)�� ���������� 20�� ������ ��
//	public void battle1 (Enemy e) {
//		player.attack(e, 20);
//		
//		// �ΰ��� ���� 3�� �� �������� ������ �� �ְ�
//		Timer timer1 = new Timer();
//		TimerTask task1 = new TimerTask() {
//			
//			@Override
//			public void run() {
//			// ���� �ð� �Ŀ� ������ �ڵ�: �������� counterAttack
////				counterAttack(e);
//			}
//		};
//		
//		timer1.schedule(task1, 3000);
//		
//	}
//		
//	//battle2(����ġ��)�� �������� 0,10,100�� ������ ��  
//	public void battle2 (Enemy e) {
//		int randomNum = (int) (Math.random()*5);
//		
//		// ���� ����
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
//		// �ΰ��� ���� 3�� �� �������� ������ �� �ְ�
//		Timer timer1 = new Timer();
//		TimerTask task1 = new TimerTask() {
//				
//			@Override
//			public void run() {
//				// ���� �ð� �Ŀ� ������ �ڵ�: �������� counterAttack
////					counterAttack(e);
//			}
//		};
//				
//		timer1.schedule(task1, 3000);
//		
//	}