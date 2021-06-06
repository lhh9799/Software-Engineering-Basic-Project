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
	private Music Music;								//���� BGM ��ü
	
	public GameActivity(MyJPanel win) {
		setLayout(null);								//��ġ�����ڸ� null�� ����(��� ������Ʈ�� ��ġ�� ���� ����)
		setBackground(Color.decode("#fad0c4"));			//JFrame�� ���� ����
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5)));//�׵θ� ����
		
		//��(����, �߰�, �⸻) �ν��Ͻ� ���� �� ��ũ�� ����Ʈ�� �߰�
		enemyLinkedList.add(new Assignment());
		enemyLinkedList.add(new Quiz());
		enemyLinkedList.add(new Exam());

		//���̺�1 ����(���� ���� ǥ��1)
		lbl1 = new JLabel();
		lbl1.setBounds(240, 410, 700, 50);
		lbl1.setHorizontalAlignment(JLabel.CENTER);
		lbl1.setFont(new Font("���� ���", Font.BOLD, 15));
		lbl1.setText("������ �����մϴ�");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER); //���� ��� ����
		add(lbl1);

		//���̺�2 ����(���� ���� ǥ��2)
		lbl2 = new JLabel();
		lbl2.setBounds(240, 450, 700, 50);
		lbl2.setHorizontalAlignment(JLabel.CENTER);
		lbl2.setFont(new Font("���� ���", Font.BOLD, 15));
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
					//��ư���� ������ ���ڿ��� �÷��̾��� ��ų���� �����ϴ� ��� -> �÷��̾��� ���� �̾���
					if(selectedSkillName.contains(it.name)) {
						if(it.limit > 0) {										//��ų�� ���� ��� Ƚ���� 0���� ũ�ٸ�(��밡���� ���)
							String report = player.attack(currentEnemy, it);	//���� ��ġ�ϴ� ���� ������ ��ų�� ������ ���ڷ� �ѱ�(report: �÷��̾ ���� �����, ���� ���� ü���� ��� ���ڿ�)
							//���� �̹����� �������� 20 �̵�(�ǰ� ����Ʈ)
							b.setText("<html> <center>" + it.name + "<br>" + it.limit + "</center> </html>");	//���ŵ� ���� Ƚ���� ��ư�� �ؽ�Ʈ�� �ݿ�
							enemyImageLabel.setBounds(enemyImageLabel.getX() - 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
							//0.5�� �Ŀ� ���� ����ġ ��Ų��.
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									enemyImageLabel.setBounds(enemyImageLabel.getX() + 20, enemyImageLabel.getY(), enemyImageLabel.getWidth(), enemyImageLabel.getHeight());
								}
							}, 500);
							lbl1.setText(report);					//�÷��̾ ������ ��ų��� �����, ���� ���� ü���� ���̺� ǥ��
							break;									//���� for���� �����ϴ� ���� �ǳʶ�(�̹� ��ư�� ���ڿ��� ��ġ�ϴ� ��ų������ ã�����Ƿ�)
						}
						else {										//��ų�� ���� ��� Ƚ���� 0�� ���
							//��ȭ���ڷ� �÷��̾��� ��ų ���Ƚ���� ������ �˸�
							JOptionPane.showMessageDialog(null, "���� Ƚ���� �����մϴ�.", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
							return;									//�Ʒ� �ڵ� ������ �� �ϵ��� ����
						}
					}
				}
				
				//�Ʒ� �ڵ�� �÷��̾��� ��ų�� ���� �� ����(���� ��� Ƚ���� �����Ͽ� attack �Լ��� ������� �ʴ� ��� ������� ����)
				if(currentEnemy.isEnemyAlive()) {					//���� ����ִٸ� -> ���� �ݰ�
					skillButtonDisable();							//�÷��̾� ��ų ��ư ��Ȱ��ȭ(1.2�� �� Ȱ��ȭ ��)
					//0.5�� �Ŀ� ��� �÷��̾ ����ġ ��Ų��.
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							String report = currentEnemy.attack(player);	//���� attack �Լ� ȣ��(report: ���� ���� �����, �÷��̾��� ���� ü���� ��� ���ڿ�)
							playerImageLabel.setBounds(playerImageLabel.getX() - 20, playerImageLabel.getY(), playerImageLabel.getWidth(), playerImageLabel.getHeight());
							new Timer().schedule(new TimerTask() {
								@Override
								public void run() {
									playerImageLabel.setBounds(playerImageLabel.getX() + 20, playerImageLabel.getY(), playerImageLabel.getWidth(), playerImageLabel.getHeight());
								}
							}, 500);
							
							lbl2.setText(report);							//���� ����� ��ų��� �����, �÷��̾��� ���� ü���� ���̺� ǥ��
							playerHPLabel.setText("HP: " + Integer.toString(player.hp));	//�÷��̾��� ü�� ���̺� ����
							if(!player.isPlayerAlive()) {					//�÷��̾ ���� ���
								Music.close();								//���� ����
								win.change("defeatEndingActivity");			//�й� �������� â ��ȯ
							}
							skillButtonEnable();							//�÷��̾� ��ų ��ư Ȱ��ȭ
						}
					}, 1200);
				}
				
				//���� ���� ���
				else {
					enemyLinkedList.removeFirst();					//��ũ�� ����Ʈ�� ù ��° ��� ����
					if(enemyLinkedList.isEmpty()) {					//��ũ�� ����Ʈ�� ����ִٸ�(�� �̻� ��Ÿ�� ���� ����)
						Music.close();								//���� ����
						win.change("victoryEndingActivity");		//�¸� �������� â ��ȯ
					}
				}
				updateEnemy();										//�� �г� ������Ʈ
				}
		}
		
		//�÷��̾� ��ų ��ư�� ���� �г�
		playerSkillButtonPanel = new JPanel();
		playerSkillButtonPanel.setLayout(new FlowLayout());			//FlowLayout�� ���(���ʿ��� ������, ������ �Ʒ��� ��ġ)
		playerSkillButtonPanel.setBorder(new LineBorder(Color.BLACK, 1));
		playerSkillButtonPanel.setBounds(50, 400, 230, 125);
		playerSkillButtonPanel.setBackground(Color.WHITE);
		int numberOfPlayerSkills = player.skillSetVect.size();		//������ ũ�� == �÷��̾� ��ų�� ������
		playerSkillButton = new JButton[numberOfPlayerSkills];		//�÷��̾� ��ų�� ��������ŭ ��ư �迭 �Ҵ�
		
		for(int i=0; i<player.skillSetVect.size(); i++) {			//��ų�� �� �� �ݺ��Ͽ�
			playerSkillButton[i] = new JButton();					//i��° ��ư �ν��Ͻ� ����
			Skill s = player.skillSetVect.elementAt(i);				//�÷��̾� ��ų ������ i��° ���Ҹ� Skill�� ���� s�� ����
			playerSkillButton[i].setText("<html> <center>" + s.name + "<br>" + s.limit + "</center> </html>");	//s���� ��ų��� ���� Ƚ���� ������ i��° ��ư�� �ؽ�Ʈ�� ����
			playerSkillButton[i].addActionListener(new SkillListener());	//i��° ��ư�� ������ ���
			playerSkillButton[i].setBackground(Color.WHITE);		//��ư�� �� ����
			playerSkillButton[i].setFont(new Font("���� ���", Font.BOLD, 15));
			playerSkillButtonPanel.add(playerSkillButton[i]);		//�÷��̾� ��ų ��ư�� ��� �гο� ��ư ����
		}
		add(playerSkillButtonPanel);								//�÷��̾� ��ų ��ư�� ��� �г��� GameActivity�гο� �߰�
		
		//=======�÷��̾� �г�=======
		playerPanel = new JPanel();
		playerPanel.setLayout(null);
		playerPanel.setBounds(65, 180, 200, 200);
		playerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		playerPanel.setBackground(Color.WHITE);
		add(playerPanel);
		//�÷��̾� �гο� �߰��� �̹���
		playerImage = new ImageIcon(player.imgPath);
		Image img = playerImage.getImage();							//���� �̹����������� ������
		int aimHeight = 140;										//������ ���α���: 140�ȼ�
		double ratio = (double) aimHeight / img.getHeight(null);	//��� ����: �̹����� ���α��� / 140�ȼ�
		double aimWidth_d = ratio * img.getWidth(null);				//������ ���α��� ���(double): �̹����� ���� ���� * ratio
		int aimWidth = (int) aimWidth_d;							//getScaledInstance�� ���ڴ� int���̾�� �ϹǷ� int�� Ÿ��ĳ����
		Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);	//������� (Image.SCALE_SMOOTH: �ӵ����� �̹����� ǰ�� �켱)
		playerImage = new ImageIcon(sizeChangedImage);
		playerImageLabel = new JLabel(playerImage);
		playerImageLabel.setBounds(25, 40, 150, 150);
		playerPanel.add(playerImageLabel);
		//�÷��̾� �гο� �߰��� �̸� ��
		playerNameLabel = new JLabel();
		playerNameLabel.setBounds(0, 0, 200, 20);
		playerNameLabel.setHorizontalAlignment(JLabel.CENTER);
		playerNameLabel.setText(player.name);
		playerNameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		playerPanel.add(playerNameLabel);
		//�÷��̾� �гο� �߰��� ü�� ��
		playerHPLabel = new JLabel();
		playerHPLabel.setBounds(0, 20, 200, 20);
		playerHPLabel.setHorizontalAlignment(JLabel.CENTER);
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
		enemyPanel.setBounds(650, 50, 200, 200);
		enemyPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		enemyPanel.setBackground(Color.WHITE);
		add(enemyPanel);
		//�� �гο� �߰��� �̹��� - �̹����� ���� ������ ���� �޶���
		enemyImage = new ImageIcon(e.imgPath);
		Image img = enemyImage.getImage();
		int aimHeight = 140;										//������ ���α���: 140�ȼ�
		double ratio = (double) aimHeight / img.getHeight(null);	//��� ����: �̹����� ���α��� / 140�ȼ�
		double aimWidth_d = ratio * img.getWidth(null);				//������ ���α��� ���(double): �̹����� ���� ���� * ratio
		int aimWidth = (int) aimWidth_d;							//getScaledInstance�� ���ڴ� int���̾�� �ϹǷ� int�� Ÿ��ĳ����
		Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);
		enemyImage = new ImageIcon(sizeChangedImage);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel = new JLabel(enemyImage);
		enemyImageLabel.setBounds(25, 40, 150, 150);
		enemyPanel.add(enemyImageLabel);
		//�� �гο� �߰��� �̸� ��
		enemyNameLabel = new JLabel();
		enemyNameLabel.setBounds(0, 0, 200, 20);
		enemyNameLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyNameLabel.setText(e.name);
		enemyNameLabel.setFont(new Font("���� ���", Font.BOLD, 15));
		enemyPanel.add(enemyNameLabel);
		//�� �гο� �߰��� ü�� ��
		enemyHPLabel = new JLabel();
		enemyHPLabel.setBounds(0, 20, 200, 20);
		enemyHPLabel.setHorizontalAlignment(JLabel.CENTER);
		enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//���� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		enemyHPLabel.setFont(new Font("���� ���", Font.BOLD, 12));
		enemyPanel.add(enemyHPLabel);
	}
	
	//�� �гο� �ǰݵ� ���� ������ �����ϴ� �޼ҵ�
	public void updateEnemy() {
		//��ũ�� ����Ʈ�� ������� �ʴٸ�(���� �� �ִٸ�) ��ũ�� ����Ʈ���� �� ���� ������
		if(!enemyLinkedList.isEmpty()) {
			Enemy e = enemyLinkedList.getFirst();
			
			//�̹��� ������Ʈ(���� ����� ��� ���� �ʿ�)
			ImageIcon originalImageIcon = new ImageIcon(e.imgPath);
			Image img = originalImageIcon.getImage();
			int aimHeight = 140;										//������ ���α���: 100�ȼ�
			double ratio = (double) aimHeight / img.getHeight(null);	//��� ����: �̹����� ���α��� / 100�ȼ�
			double aimWidth_d = ratio * img.getWidth(null);				//������ ���α��� ���(double): �̹����� ���� ���� * ratio
			int aimWidth = (int) aimWidth_d;							//getScaledInstance�� ���ڴ� int���̾�� �ϹǷ� int�� Ÿ��ĳ����
			Image sizeChangedImage = img.getScaledInstance(aimWidth, aimHeight, Image.SCALE_SMOOTH);
			ImageIcon newEnemyImageIcon = new ImageIcon(sizeChangedImage);
			enemyImageLabel.setIcon(newEnemyImageIcon);
			//�̸� �� ������Ʈ
			enemyNameLabel.setText(e.name);
			//ü�� �� ������Ʈ
			enemyHPLabel.setText("HP: " + Integer.toString(e.hp));	//���� ü���� ���ڿ��� ��ȯ �� Label�� ������Ʈ
		}
	}
	
	//����� ��ų ��ư Ȱ��ȭ
	public void skillButtonEnable() {
		for(var it : playerSkillButton) {
			it.setEnabled(true);
		}
	}
	
	//����� ��ų ��ư ��Ȱ��ȭ
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
