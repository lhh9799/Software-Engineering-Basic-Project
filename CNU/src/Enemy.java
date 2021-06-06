import java.util.Vector;

class Skill {	
	String name;
	int minDamage, maxDamage, probability, limit;
	
	public Skill(String name, int minDamage, int maxDamage, int limit) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.limit = limit;
	}
	public Skill(String name, int minDamage, int maxDamage, String probability) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.probability = Integer.parseInt(probability.split("%")[0]);	//%�� �������� �߶� ���������� ��ȯ �� probability ��������� ����
	}
}

class Enemy {
	String name, imgPath;
	int hp;
	Vector<Skill> skillSet = new Vector<Skill>();
	
	public boolean isEnemyAlive() {
		if(this.hp > 0)
			return true;
		else {
			System.out.println("���� ����߽��ϴ�!");
			return false;
		}
	}
	
	//��ų�� �������� �Է��� �� �� ��ų�� ������� ����
	public int skillSelect(int number_of_skills) {
		return (int)(Math.random()*100);
	}
	
//	public void attack(Player p) {
	public String attack(Player p) {
		int random = (int)(Math.random()*99 + 1);
		int choice = (random < skillSet.elementAt(0).probability) ? 0 : 1;	//random�� 0����ų�� Ȯ������ ������ 0�� ũ�� 1�� ��ų ����
		String skill_name = skillSet.elementAt(choice).name;	//������ ��ų�� �̸� ����
		int maxDamage = skillSet.elementAt(choice).maxDamage;
		int minDamage = skillSet.elementAt(choice).minDamage;
		int damageRange = maxDamage - minDamage + 1;			//����� �� ���
		int actualDamage = (int)(Math.random() * damageRange + minDamage);
		
		p.hp -= actualDamage;
		return this.name + "�� " + skill_name + "��(��) �ߵ�! " + p.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!";
//		System.out.println(this.name + "�� " + skill_name + "��(��) �ߵ�! " + p.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!");
	}
}

//���� Ŭ����. ��ų�� �����Ƿ� attack�� Override ���� ����
class Assignment extends Enemy {
	public Assignment() {
		this.name = "����";
		this.hp = 10;
		this.imgPath = "newImg/assignment.png";
		
		//��ų �߰�
		skillSet.add(new Skill("��� ���� ����ϱ�", 1, 3, "90%"));
		skillSet.add(new Skill("���� ������ �����ϱ�", 3, 5, "10%"));
	}
}

//���� Ŭ����
class Quiz extends Enemy {
	public Quiz() {
		this.name = "����";
		this.hp = 20;
		this.imgPath = "newImg/quiz.png";
		
		//��ų �߰�
		skillSet.add(new Skill("��ĭ�� �� ����?", 3, 5, "80%"));
		skillSet.add(new Skill("���� ������ �ǹ̸� �����ϱ�", 5, 10, "20%"));
	}
}

//�⸻��� Ŭ����
class Exam extends Enemy {
	public Exam() {
		this.name = "����";
		this.hp = 30;
		this.imgPath = "newImg/exam.png";
		
		//��ų �߰�
		skillSet.add(new Skill("�� ���� ���� ����?", 5, 10, "70%"));
		skillSet.add(new Skill("�� �ڵ��� �������� ���� �̸� �ǰ� �ٲ� �����Ͻÿ�.", 10, 15, "30%"));
	}
}