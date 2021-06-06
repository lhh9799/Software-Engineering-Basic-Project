import java.util.Vector;

//스킬정보(스킬명, 최소 대미지, 최대 대미지, 확률, 스킬의 남은 사용횟수)를 저장하는 클래스
class Skill {	
	String name;			//스킬명 변수
	int minDamage, maxDamage, probability, limit;	//순서대로 최소 대미지, 최대 대미지, 확률, 스킬의 남은 사용 횟수
	
	//사용자의 스킬 인스턴스를 만들 때 호출되는 생성자(스킬명, 최소 대미지, 최대 대미지, 스킬의 남은 사용 횟수를 초기화)
	public Skill(String name, int minDamage, int maxDamage, int limit) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.limit = limit;
	}
	//적의 스킬 인스턴스를 만들 때 호출되는 생성자(스킬명, 최소 대미지, 최대 대미지, 발동 확률을 초기화)
	public Skill(String name, int minDamage, int maxDamage, String probability) {
		this.name = name;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.probability = Integer.parseInt(probability.split("%")[0]);	//%를 기준으로 잘라 정수형으로 변환 후 probability 멤버변수에 저장
	}
}

//과제, 퀴즈, 시험클래스의 부모 클래스(객체의 이름, 이미지 경로, 체력, 스킬정보를 저장한다.)
class Enemy {
	String name, imgPath;	//순서대로 객체의 이름, 이미지 경로
	int hp;					//체력 변수
	Vector<Skill> skillSet = new Vector<Skill>();	//스킬정보를 저장하는 벡터
	
	//적이 살아있는지 여부를 리턴하는 메소드
	public boolean isEnemyAlive() {
		if(this.hp > 0)		//호출한 인스턴스의 멤버변수 hp가 0 이상이라면
			return true;	//true 리턴
		else {				//아니라면
			return false;	//false 리턴
		}
	}
	
	//적이 플레이어를 공격하는 함수
	public String attack(Player p) {
		int random = (int)(Math.random()*99 + 1);							//1부터 100까지의 랜덤한 숫자를 뽑음
		int choice = (random < skillSet.elementAt(0).probability) ? 0 : 1;	//random이 0번스킬의 확률보다 작으면 0번 크면 1번 스킬 선택
		String skill_name = skillSet.elementAt(choice).name;				//선택한 스킬의 이름 추출
		int maxDamage = skillSet.elementAt(choice).maxDamage;				//선택한 스킬의 최대 대미지를 maxDamage 변수에 저장
		int minDamage = skillSet.elementAt(choice).minDamage;				//선택한 스킬의 최소 대미지를 minDamage 변수에 저장
		int damageRange = maxDamage - minDamage + 1;						//대미지 폭(max - min + 1) 계산
		int actualDamage = (int)(Math.random() * damageRange + minDamage);	//적이 플레이어에게 줄 대미지 계산
		
		p.hp -= actualDamage;												//플레이어의 체력 -= 적이 가한 대미지
		//JLabel에 표시할 전투 정보를 리턴
		return this.name + "의 " + skill_name + "을(를) 발동! " + p.name + "은(는) " + actualDamage + "의 피해를 입었습니다!";
	}
}

//과제 클래스
class Assignment extends Enemy {
	//기본 생성자(과제 클래스의 인스턴스의 이름, 체력, 이미지 경로를 초기화)
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
	//기본 생성자(퀴즈 클래스의 인스턴스의 이름, 체력, 이미지 경로를 초기화)
	public Quiz() {
		this.name = "퀴즈";
		this.hp = 20;
		this.imgPath = "newImg/quiz_trans.png";
		
		//스킬 추가
		skillSet.add(new Skill("빈칸에 들어갈 값은?", 3, 5, "80%"));
		skillSet.add(new Skill("다음 내용의 의미를 서술하기", 5, 10, "20%"));
	}
}

//기말고사 클래스
class Exam extends Enemy {
	//기본 생성자(시험 클래스의 인스턴스의 이름, 체력, 이미지 경로를 초기화)
	public Exam() {
		this.name = "시험";
		this.hp = 30;
		this.imgPath = "newImg/exam.png";
		
		//스킬 추가
		skillSet.add(new Skill("위 설명에 대한 값은?", 5, 10, "70%"));
		skillSet.add(new Skill("위 코드를 옳게 바꿔 구현하시오.", 10, 15, "30%"));
	}
}