import java.util.Vector;

//��ų����(��ų��, �ּ� �����, �ִ� �����, Ȯ��, ��ų�� ���� ���Ƚ��)�� �����ϴ� Ŭ����
class Skill {	
	String name;			//��ų�� ����
	int minDamage, maxDamage, probability, limit;	//������� �ּ� �����, �ִ� �����, Ȯ��, ��ų�� ���� ��� Ƚ��
	
	//������� ��ų �ν��Ͻ��� ���� �� ȣ��Ǵ� ������(��ų��, �ּ� �����, �ִ� �����, ��ų�� ���� ��� Ƚ���� �ʱ�ȭ)
	public Skill(String name, int minDamage, int maxDamage, int limit) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.limit = limit;
	}
	//���� ��ų �ν��Ͻ��� ���� �� ȣ��Ǵ� ������(��ų��, �ּ� �����, �ִ� �����, �ߵ� Ȯ���� �ʱ�ȭ)
	public Skill(String name, int minDamage, int maxDamage, String probability) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.probability = Integer.parseInt(probability.split("%")[0]);	//%�� �������� �߶� ���������� ��ȯ �� probability ��������� ����
	}
}

//����, ����, ����Ŭ������ �θ� Ŭ����(��ü�� �̸�, �̹��� ���, ü��, ��ų������ �����Ѵ�.)
class Enemy {
	String name, imgPath;	//������� ��ü�� �̸�, �̹��� ���
	int hp;					//ü�� ����
	Vector<Skill> skillSet = new Vector<Skill>();	//��ų������ �����ϴ� ����
	
	//���� ����ִ��� ���θ� �����ϴ� �޼ҵ�
	public boolean isEnemyAlive() {
		if(this.hp > 0)		//ȣ���� �ν��Ͻ��� ������� hp�� 0 �̻��̶��
			return true;	//true ����
		else {				//�ƴ϶��
			return false;	//false ����
		}
	}
	
	//���� �÷��̾ �����ϴ� �Լ�
	public String attack(Player p) {
		int random = (int)(Math.random()*99 + 1);							//1���� 100������ ������ ���ڸ� ����
		int choice = (random < skillSet.elementAt(0).probability) ? 0 : 1;	//random�� 0����ų�� Ȯ������ ������ 0�� ũ�� 1�� ��ų ����
		String skill_name = skillSet.elementAt(choice).name;				//������ ��ų�� �̸� ����
		int maxDamage = skillSet.elementAt(choice).maxDamage;				//������ ��ų�� �ִ� ������� maxDamage ������ ����
		int minDamage = skillSet.elementAt(choice).minDamage;				//������ ��ų�� �ּ� ������� minDamage ������ ����
		int damageRange = maxDamage - minDamage + 1;						//����� ��(max - min + 1) ���
		int actualDamage = (int)(Math.random() * damageRange + minDamage);	//���� �÷��̾�� �� ����� ���
		
		p.hp -= actualDamage;												//�÷��̾��� ü�� -= ���� ���� �����
		//JLabel�� ǥ���� ���� ������ ����
		return this.name + "�� " + skill_name + "��(��) �ߵ�! " + p.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!";
	}
}

//���� Ŭ����
class Assignment extends Enemy {
	//�⺻ ������(���� Ŭ������ �ν��Ͻ��� �̸�, ü��, �̹��� ��θ� �ʱ�ȭ)
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
	//�⺻ ������(���� Ŭ������ �ν��Ͻ��� �̸�, ü��, �̹��� ��θ� �ʱ�ȭ)
	public Quiz() {
		this.name = "����";
		this.hp = 20;
		this.imgPath = "newImg/quiz_trans.png";
		
		//��ų �߰�
		skillSet.add(new Skill("��ĭ�� �� ����?", 3, 5, "80%"));
		skillSet.add(new Skill("���� ������ �ǹ̸� �����ϱ�", 5, 10, "20%"));
	}
}

//�⸻��� Ŭ����
class Exam extends Enemy {
	//�⺻ ������(���� Ŭ������ �ν��Ͻ��� �̸�, ü��, �̹��� ��θ� �ʱ�ȭ)
	public Exam() {
		this.name = "����";
		this.hp = 30;
		this.imgPath = "newImg/exam.png";
		
		//��ų �߰�
		skillSet.add(new Skill("�� ���� ���� ����?", 5, 10, "70%"));
		skillSet.add(new Skill("�� �ڵ带 �ǰ� �ٲ� �����Ͻÿ�.", 10, 15, "30%"));
	}
}