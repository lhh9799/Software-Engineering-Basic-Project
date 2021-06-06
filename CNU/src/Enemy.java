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
		this.probability = Integer.parseInt(probability.split("%")[0]);	//%를 기준으로 잘라 정수형으로 변환 후 probability 멤버변수에 저장
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
			System.out.println("적이 사망했습니다!");
			return false;
		}
	}
	
	//스킬의 가짓수를 입력해 몇 번 스킬을 사용할지 리턴
	public int skillSelect(int number_of_skills) {
		return (int)(Math.random()*100);
	}
	
//	public void attack(Player p) {
	public String attack(Player p) {
		int random = (int)(Math.random()*99 + 1);
		int choice = (random < skillSet.elementAt(0).probability) ? 0 : 1;	//random이 0번스킬의 확률보다 작으면 0번 크면 1번 스킬 선택
		String skill_name = skillSet.elementAt(choice).name;	//선택한 스킬의 이름 추출
		int maxDamage = skillSet.elementAt(choice).maxDamage;
		int minDamage = skillSet.elementAt(choice).minDamage;
		int damageRange = maxDamage - minDamage + 1;			//대미지 폭 계산
		int actualDamage = (int)(Math.random() * damageRange + minDamage);
		
		p.hp -= actualDamage;
		return this.name + "의 " + skill_name + "을(를) 발동! " + p.name + "은(는) " + actualDamage + "의 피해를 입었습니다!";
//		System.out.println(this.name + "의 " + skill_name + "을(를) 발동! " + p.name + "은(는) " + actualDamage + "의 피해를 입었습니다!");
	}
}

//과제 클래스. 스킬이 없으므로 attack을 Override 하지 않음
class Assignment extends Enemy {
	public Assignment() {
		this.name = "과제";
		this.hp = 10;
		this.imgPath = "newImg/assignment.png";
		
		//스킬 추가
		skillSet.add(new Skill("배운 내용 요약하기", 1, 3, "90%"));
		skillSet.add(new Skill("다음 내용을 구현하기", 3, 5, "10%"));
	}
}

//퀴즈 클래스
class Quiz extends Enemy {
	public Quiz() {
		this.name = "퀴즈";
		this.hp = 20;
		this.imgPath = "newImg/quiz.png";
		
		//스킬 추가
		skillSet.add(new Skill("빈칸에 들어갈 값은?", 3, 5, "80%"));
		skillSet.add(new Skill("다음 내용의 의미를 서술하기", 5, 10, "20%"));
	}
}

//기말고사 클래스
class Exam extends Enemy {
	public Exam() {
		this.name = "시험";
		this.hp = 30;
		this.imgPath = "newImg/exam.png";
		
		//스킬 추가
		skillSet.add(new Skill("위 설명에 대한 값은?", 5, 10, "70%"));
		skillSet.add(new Skill("위 코드의 문제점을 보고 이를 옳게 바꿔 구현하시오.", 10, 15, "30%"));
	}
}