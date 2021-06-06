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
	private JLabel lbl1, lbl2;							//���� ������ ǥ���� ���̺�
	private JLabel playerNameLabel, enemyNameLabel;		//�÷��̾�, ���� �̸� ���̺�
	private JLabel playerHPLabel, enemyHPLabel;			//�÷��̾�, ���� ü�� ���̺�
	private JButton playerSkillButton[];				//�÷��̾��� ��ų ��ư �迭
	private JPanel playerSkillButtonPanel;				//�÷��̾��� ��ų ��ư���� ��� �г�
	private JPanel playerPanel, enemyPanel;				//�÷��̾�, ���� ������ �����ϴ� ������Ʈ���� ��� �г� 
	private ImageIcon playerImage, enemyImage;			//�÷��̾�, ���� �̹��� ������
	private JLabel playerImageLabel, enemyImageLabel;	//�÷��̾�, ���� �̹��� �������� ��� ���̺�
	private Player player = new Player();				//�÷��̾� �ν��Ͻ� ���� �� ����
	private LinkedList<Enemy> enemyLinkedList = new LinkedList<Enemy>();	//������ ����� ��ũ�� ����Ʈ(Ư¡: ����� ������ ������)
	
	public GameActivity(MyJPanel win) {
		setLayout(null);		//��ġ�����ڸ� null�� ����(��� ������Ʈ�� ��ġ�� ���� ����)
		
		//��(����, �߰�, �⸻) �ν��Ͻ� ���� �� ��ũ�� ����Ʈ�� �߰�
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//���̺�1 ����(���� ���� ǥ��1)
		lbl1 = new JLabel();
		lbl1.setBounds(200, 210, 500, 50);
		lbl1.setText("������ �����մϴ�");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER); //���� ��� ����
		add(lbl1);

		//���̺�2 ����(���� ���� ǥ��2)
		lbl2 = new JLabel();
		lbl2.setBounds(200, 240, 500, 50);
		lbl2.setText(player.name + "�� ü���� " + player.hp + "�Դϴ�");
		lbl2.setHorizontalAlignment(SwingConstants.CENTER); //���� ��� ����
		add(lbl2);
		
		//�÷��̾��� ��ų ��ư�� ������ �� �۵��ϴ� �׼Ǹ�����
		class SkillListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {			//��ư Ŭ�� �� ȣ��Ǵ� �޼ҵ�
				JButton b = (JButton) e.getSource();				//Ŭ���� ��ư�� �˾Ƴ�
				String selectedSkillName = b.getText();				//��ư�� ���ڿ��� selectedSkillName ������ ����
				Enemy currentEnemy = enemyLinkedList.getFirst();	//���� ��ġ�ϴ� ���� ������ currentEnemy ������ ����
				
				for(var it : player.skillSetVect) {					//for-each������ �÷��̾� ��ų ���͸� ��ȸ
					//��ư�� ���� ��ų �̸��� ��ġ�ϴ� �ν��Ͻ� ã�� -> �÷��̾��� ���� �̾���
					if(it.name.equals(selectedSkillName)) {
						if(it.limit > 0) {										//��ų�� ���� ��� Ƚ���� 0���� ũ�ٸ�(��밡���� ���)
							String report = player.attack(currentEnemy, it);	//���� ��ġ�ϴ� ���� ������ ��ų�� ������ ���ڷ� �ѱ�(report: �÷��̾ ���� �����, ���� ���� ü���� ��� ���ڿ�)
							//���� �̹����� �������� 20 �̵�(�ǰ� ����Ʈ)
							enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
							//0.5�� �Ŀ� ��� �������� ����ġ ��Ų��.
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								}
							}, 500);
							lbl1.setText(report);					//�÷��̾ ������ ��ų��� �����, ���� ���� ü���� ���̺� ǥ��
							
							try {
								Thread.sleep(100);					//������ �Ͻ����� 1000ms(=1s)
							}
							catch (InterruptedException exc) {		//�����尡 �ߴܵǾ��� �� �߻��� �� �ִ� ������ ����ó��(������� �����尡 ����� ���� �� �߻�)
								System.out.println(exc.getMessage());
							}
							break;									//���� for���� �����ϴ� ���� �ǳʶ�(�̹� ��ư�� ���ڿ��� ��ġ�ϴ� ��ų������ ã�����Ƿ�)
						}
						else {										//��ų�� ���� ��� Ƚ���� 0�� ���
							System.out.println("��ų ���Ƚ�� ����");		//�ֿܼ� �÷��̾��� ��ų ���Ƚ���� ������ �˸�
							return;									//�Ʒ� �ڵ� ������ �� �ϵ��� ����
						}
					}
				}
				
				//�Ʒ� �ڵ�� �÷��̾��� ��ų�� ���� �� ����(���� ��� Ƚ���� �����Ͽ� attack �Լ��� ������� �ʴ� ��� ������� ����)
				if(currentEnemy.isEnemyAlive()) {					//���� ����ִٸ� -> ���� �ݰ�
					String report = currentEnemy.attack(player);	//���� attack �Լ� ȣ��(report: ���� ���� �����, �÷��̾��� ���� ü���� ��� ���ڿ�)
					lbl2.setText(report);							//���� ����� ��ų��� �����, �÷��̾��� ���� ü���� ���̺� ǥ��
					playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//�÷��̾��� ü�� ���̺� ����
					if(!player.isPlayerAlive()) {					//�÷��̾ ���� ���
						System.out.println("�÷��̾� ����. ���� ��");		//�ֿܼ� �÷��̾ ������ �˸�
						System.exit(0);								//���α׷� ����
					}
				}
				
				//���� ���� ���
				else {
					enemyLinkedList.removeFirst();					//��ũ�� ����Ʈ�� ù ��° ��� ����
					if(enemyLinkedList.isEmpty()) {					//��ũ�� ����Ʈ�� ����ִٸ�(�� �̻� ��Ÿ�� ���� ����)
						System.out.println("�� ��� óġ. ���� ��");		//�ֿܼ� ������ ������� �˸�
						System.exit(0);								//���α׷� ����
					}
				}
				changeEnemy();
				}
		}
		
		//�÷��̾� ��ų ��ư�� ���� �г�
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());			//FlowLayout�� ���(���ʿ��� ������, ������ �Ʒ��� ��ġ)
		playerSkillButtonPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		playerSkillButtonPanel.setBounds(10, 350, 200, 80);
		int numberOfPlayerSkills = player.skillSetVect.size();		//������ ũ�� == �÷��̾� ��ų�� ������
		playerSkillButton = new JButton[numberOfPlayerSkills];		//�÷��̾� ��ų�� ��������ŭ ��ư �迭 �Ҵ�
		
		for(int i=0; i<player.skillSetVect.size(); i++) {			//��ų�� �� �� �ݺ��Ͽ�
			playerSkillButton[i] = new JButton();					//i��° ��ư �ν��Ͻ� ����
			Skill s = player.skillSetVect.elementAt(i);				//�÷��̾� ��ų ������ i��° ���Ҹ� Skill�� ���� s�� ����
			playerSkillButton[i].setText(s.name);					//s���� ��ų���� ������ i��° ��ư�� �ؽ�Ʈ�� ����
			playerSkillButton[i].addActionListener(new SkillListener());	//i��° ��ư�� ������ ���
			playerSkillButtonPanel.add(playerSkillButton[i]);		//�÷��̾� ��ų ��ư�� ��� �гο� ��ư ����
		}
		add(playerSkillButtonPanel);								//�÷��̾� ��ų ��ư�� ��� �г��� GameActivity�гο� �߰�
		
		//=======�÷��̾� �г�=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(10, 150, 150, 150);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(playerPanel);
		//�÷��̾� �гο� �߰��� �̹���
		playerImage = new ImageIcon(player.imgPath);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 100, 100);
		playerImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		playerPanel.add(playerImageLabel);
		//�÷��̾� �гο� �߰��� �̸� ��
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(65, 0, 50, 20);
		playerNameLabel.setText(player.name);
		playerPanel.add(playerNameLabel);
		//�÷��̾� �гο� �߰��� ü�� ��
		playerHPLabel = new JLabel();
		playerHPLabel.setBounds(60, 20, 50, 20);
		playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//�÷��̾��� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		playerPanel.add(playerHPLabel);
		
		summonEnemy(enemyLinkedList.getFirst());	//ù ���� ��(����)�� �� �г� �߰�
	}	//GameActivity �������� ��
	
	//�÷��̾��� �̸��� �����ϴ� �޼ҵ�(IntroActivity���� Ȯ�ι�ư�� ������ ȣ���)
	public void updatePlayerNameLabel(String Username) {
		player.playerNameChange(Username);			//player �ν��Ͻ��� �̸� ����
		playerNameLabel.setText(Username);			//�÷��̾��� �̸� ���̺� ����
	}
	
	//��ũ�� ����Ʈ�� ù ��° ���� �гο� �߰��ϴ� �޼ҵ� 
	public void summonEnemy(Enemy e) {
		//=======�� �г�=======
		enemyPanel = new JPanel();
		enemyPanel.setLayout(null);
		enemyPanel.setBounds(450, 10, 150, 150);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		add(enemyPanel);
		//�� �гο� �߰��� �̹��� - �̹����� ���� ������ ���� �޶���
		enemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 100, 100);
		enemyImageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
		enemyPanel.add(enemyImageLabel);
		//�� �гο� �߰��� �̸� ��
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(65, 0, 50, 20);
		enemyNameLabel.setText(e.name);
		enemyPanel.add(enemyNameLabel);
		//�� �гο� �߰��� ü�� ��
		enemyHPLabel = new JLabel();
		enemyHPLabel.setBounds(60, 20, 50, 20);
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//���� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		enemyPanel.add(enemyHPLabel);
	}
	
	//�� �гο� �ǰݵ� ���� ������ �����ϴ� �޼ҵ�
	public void changeEnemy() {
		//��ũ�� ����Ʈ���� �� ���� ������
		Enemy e = enemyLinkedList.getFirst();
		
		//�̹��� ������Ʈ(���� ����� ��� ���� �ʿ�)
		ImageIcon newEnemyImage = new ImageIcon(e.imgPath);
		enemyImageLabel.setIcon(newEnemyImage);
		//�̸� �� ������Ʈ
		enemyNameLabel.setText(e.name);
		//ü�� �� ������Ʈ
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//���� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
	}
}
