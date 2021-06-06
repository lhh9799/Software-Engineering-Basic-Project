import java.util.Vector;

class Player {
	String name = "봉비";						//플레이어 이름 변수
	int hp = 100;							//체력 변수 200으로 초기화
	String imgPath = "newImg/bongbi.png";	//이미지 경로 초기화
	Vector<Skill> skillSetVect = new Vector<Skill>();	//스킬 정보를 저장하는 벡터

	public Player() {
		//스킬 추가
		skillSetVect.add(new Skill("출석체크", 1, 3, 20));
		skillSetVect.add(new Skill("과제제출", 3, 5, 10));
		skillSetVect.add(new Skill("벼락치기", 1, 3, 20));
		skillSetVect.add(new Skill("시험보기", 5, 15, 3));
	}
	
	//플레이어가 살아있는지 여부를 리턴하는 메소드
	public boolean isPlayerAlive() {
		if(this.hp > 0)
			return true;
		return false;
	}
	
	//이미 생성한 플레이어 인스턴스의 이름을 변경하는 메소드
	public void playerNameChange(String name) {
		this.name = name;
	}
	
	//플레이어가 적을 공격하는 함수
	public String attack(Enemy e, Skill s) {
		int maxDamage = s.maxDamage;					//선택한 스킬의 최대 대미지를 maxDamage 변수에 저장
		int minDamage = s.minDamage;					//선택한 스킬의 최소 대미지를 minDamage 변수에 저장
		int damageRange = maxDamage - minDamage + 1;	//대미지 폭(max - min + 1) 계산
		int actualDamage = (int)(Math.random() * damageRange + minDamage);	//플레이어가 적에게 줄 대미지 계산
		
		e.hp -= actualDamage;							//적의 체력 -= 플레이어가 가한 대미지
		s.limit--;										//플레이어가 사용한 스킬의 사용횟수를 줄임
		//JLabel에 표시할 전투 정보를 리턴
		return this.name + "의 " + s.name + "을(를) 발동! " + e.name + "은(는) " + actualDamage + "의 피해를 입었습니다!";
	}
}
