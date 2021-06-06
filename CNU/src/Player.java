import java.util.Vector;

class Player {
	String name;							//�÷��̾� �̸� ����
	int hp = 200;							//ü�� ���� 200���� �ʱ�ȭ
	String imgPath = "newImg/bongbi.png";	//�̹��� ��� �ʱ�ȭ
	Vector<Skill> skillSetVect = new Vector<Skill>();	//��ų ������ �����ϴ� ����

	public Player() {
		//��ų �߰�
		skillSetVect.add(new Skill("�⼮üũ", 1, 3, 20));
		skillSetVect.add(new Skill("��������", 3, 5, 10));
		skillSetVect.add(new Skill("����ġ��", 1, 3, 20));
		skillSetVect.add(new Skill("���躸��", 5, 15, 3));
	}
	
	//�÷��̾ ����ִ��� ���θ� �����ϴ� �޼ҵ�
	public boolean isPlayerAlive() {
		if(this.hp > 0)
			return true;
		return false;
	}
	
	//�̹� ������ �÷��̾� �ν��Ͻ��� �̸��� �����ϴ� �޼ҵ�
	public void playerNameChange(String name) {
		this.name = name;
	}
	
	//�÷��̾ ���� �����ϴ� �Լ�
	public String attack(Enemy e, Skill s) {
		int maxDamage = s.maxDamage;					//������ ��ų�� �ִ� ������� maxDamage ������ ����
		int minDamage = s.minDamage;					//������ ��ų�� �ּ� ������� minDamage ������ ����
		int damageRange = maxDamage - minDamage + 1;	//����� ��(max - min + 1) ���
		int actualDamage = (int)(Math.random() * damageRange + minDamage);	//�÷��̾ ������ �� ����� ���
		
		e.hp -= actualDamage;							//���� ü�� -= �÷��̾ ���� �����
		s.limit--;										//�÷��̾ ����� ��ų ���Ƚ���� ����
		//JLabel�� ǥ���� ���� ������ ����
		return this.name + "�� " + s.name + "��(��) �ߵ�! " + e.name + "��(��) " + actualDamage + "�� ���ظ� �Ծ����ϴ�!";
	}
}
