import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;

class Player {
	String name = "����";	//default �̸�
	int hp = 200;
	String imgPath;
	Vector<Skill> skillSetVect = new Vector<Skill>();
//	HashMap<String, Skill> skillSet = new HashMap<String, Skill>();
//	Vector<HashMap> skillVector = new Vector<HashMap>();

	public Player() {
		this.imgPath = "newImg/bongbi.png";
		
		skillSetVect.add(new Skill("�⼮üũ", 1, 3, 20));
		skillSetVect.add(new Skill("��������", 3, 5, 10));
		skillSetVect.add(new Skill("����ġ��", 1, 3, 20));
		skillSetVect.add(new Skill("���躸��", 5, 15, 3));
//		//��ų �߰�
//		skillSet.put("�⼮üũ", new Skill("�⼮üũ", 1, 3, 20));
//		skillSet.put("��������", new Skill("��������", 3, 5, 10));
//		skillSet.put("����ġ��", new Skill("����ġ��", 1, 3, 20));
//		skillSet.put("���躸��", new Skill("���躸��", 5, 15, 3));
	}
	
	public boolean isPlayerAlive() {
		if(this.hp > 0)
			return true;
		return false;
	}
	
	public void playerNameChange(String name) {
		this.name = name;
	}
	
//	public void attack(Enemy e, Skill s) {
	public String attack(Enemy e, Skill s) {
		int maxDamage = s.maxDamage;
		int minDamage = s.minDamage;
		int damageRange = maxDamage - minDamage + 1;
		int actualDamage = (int)(Math.random() * damageRange + minDamage);
		e.hp -= actualDamage;
		//��ų ���Ƚ�� ���̱�
		s.limit--;
//		System.out.println(this.name + "�� " + s.name + "��(��) �ߵ�! " + e.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!");
		return this.name + "�� " + s.name + "��(��) �ߵ�! " + e.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!";
	}
	
	//���� �̿� ����(���)
//	public void attack(Enemy e, Skill s) {
//		int sum = 0, choice = 0;
//		String skill_name = "";
//		int random = (int)(Math.random() * 99 + 1);
//		Vector<Integer> runningTotal = new Vector<Integer>();	//Ȯ�� ������ ����
//		
//		for(var it : skillSetVect) {
//			System.out.println("it.probability: " + it.probability);
//			sum += it.probability;
//			runningTotal.add(sum);
//			System.out.println("sum: " + sum);
//		}
//		runningTotal.add(0);	//Ȯ�� �˻縦 ���ϰ� �ϵ��� �� �ڿ� 0�� �߰�
//		
//		for(choice=0; choice<runningTotal.size()-1; choice++) {
//			if(random >= runningTotal.get(choice) && random <= runningTotal.get(choice + 1)) {
//				skill_name = skillSetVect.elementAt(choice).name;	//������ ��ų�� �̸� ����
//				System.out.println("skill_name: " + skill_name);
//				break;
//			}
//		}
//		int maxDamage = skillSetVect.elementAt(choice).maxDamage;
//		int minDamage = skillSetVect.elementAt(choice).minDamage;
//		int damageRange = maxDamage - minDamage + 1;			//����� �� ���
//		int actualDamage = (int)(Math.random() * damageRange + minDamage);
//		
//		e.hp -= actualDamage;
//		System.out.println(this.name + "�� " + skill_name + "��(��) �ߵ�!" + e.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!");
//	}
	
	//HashMap �̿� ����
//	public void attack(Enemy e) {
//		int sum = 0, choice = 0;
//		String skill_name;
//		Vector<Integer> runningTotal = new Vector<Integer>();	//Ȯ�� ������ ����
//		for(var it : skillSet.keySet()) {
//			sum += skillSet.get(it).probability;
//			runningTotal.add(sum);
//		}
//		runningTotal.add(0);	//Ȯ�� �˻縦 ���ϰ� �ϵ��� �� �ڿ� 0�� �߰�
//		
//		int random = (int)(Math.random() * 99 + 1);
//		
//		for(choice=0; choice<runningTotal.size(); choice++) {
//			if(random >= runningTotal.get(choice) && random <= runningTotal.get(choice + 1)) {
//				skill_name = skillSet.elementAt(choice).name;	//������ ��ų�� �̸� ����
//				break;
//			}
//		}
//		int maxDamage = skillSet.elementAt(choice).maxDamage;
//		int minDamage = skillSet.elementAt(choice).minDamage;
//		int damageRange = maxDamage - minDamage + 1;			//����� �� ���
//		int actualDamage = (int)(Math.random() * damageRange + minDamage);
//		
//		e.hp -= actualDamage;
//		System.out.println(this.name + "�� " + skill_name + "��(��) �ߵ�!" + e.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!");
//	}

//	//�÷��̾��� ������ min, max���� ���ڷ� �޾� �� ������ ����� ���. attack ���� isPlayerAlive() ȣ���ϱ�
//	public void attack(Enemy e, int damage) {
//		e.hp = e.hp - damage;
//		
//		// ���� �׸�
//		original = (ImageIcon) s.getIcon();
//
//		// �������� ���ϴ� �׸�.
////		ImageIcon bsImg_fire = new ImageIcon(GameActivity.class.getResource("/study/img/slime2(blue).png"));
////		ImageIcon rsImg_fire = new ImageIcon(GameActivity.class.getResource("/study/img/slime2(red).png"));
//		ImageIcon bsImg_fire = new ImageIcon(GameActivity.class.getResource("/img/slime2(blue).png"));
//		ImageIcon rsImg_fire = new ImageIcon(GameActivity.class.getResource("/img/slime2(red).png"));
//
//		//�����ӿ� ���� ���ϴ� �׸��� �ٸ��� ����.
//		if (s == GameActivity.bs1) {
//			s.setIcon(bsImg_fire);
//		} else {
//			s.setIcon(rsImg_fire);
//		}
//
//		Timer timer1 = new Timer();
//		TimerTask task1 = new TimerTask() {
//
//			@Override
//			public void run() {
//				s.setIcon(original);
//			}
//
//		};
//
//		timer1.schedule(task1, 500);
//
//		s.hp = s.hp - 30;
//
//		if (e.hp < 1) {
			//�̹��� �����ܿ��� �̹����� ����
//			Image colorImage = original.getImage();
//			timer1.cancel();//�� �ڵ� ���� �� ���� �Ŀ� 46�� �����
			
//			if (e == GameActivity.bs1) {
//				GameActivity.btn1.setEnabled(false);
//			} else {
//				GameActivity.btn2.setEnabled(false);
//			}
			
			//������� ����
//			ImageFilter filter = new GrayFilter(true, 50);
//			ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
//			Image gImage = Toolkit.getDefaultToolkit().createImage(producer);
			
//			s.setIcon(new ImageIcon(gImage));
			
//			GameActivity.lbl.setText(e.name + "�� ����ߴ�");
//			GameActivity.lbl2.setText("");
//
//		} else {
//			GameActivity.lbl.setText(name + "�� ����. " + e.name + "�� ü���� " + e.hp + ".");
//
//		}

}
