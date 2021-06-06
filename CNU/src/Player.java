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
	String name = "봉비";	//default 이름
	int hp = 200;
	String imgPath;
	Vector<Skill> skillSetVect = new Vector<Skill>();
//	HashMap<String, Skill> skillSet = new HashMap<String, Skill>();
//	Vector<HashMap> skillVector = new Vector<HashMap>();

	public Player() {
		this.imgPath = "newImg/bongbi.png";
		
		skillSetVect.add(new Skill("출석체크", 1, 3, 20));
		skillSetVect.add(new Skill("과제제출", 3, 5, 10));
		skillSetVect.add(new Skill("벼락치기", 1, 3, 20));
		skillSetVect.add(new Skill("시험보기", 5, 15, 3));
//		//스킬 추가
//		skillSet.put("출석체크", new Skill("출석체크", 1, 3, 20));
//		skillSet.put("과제제출", new Skill("과제제출", 3, 5, 10));
//		skillSet.put("벼락치기", new Skill("벼락치기", 1, 3, 20));
//		skillSet.put("시험보기", new Skill("시험보기", 5, 15, 3));
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
		//스킬 사용횟수 줄이기
		s.limit--;
//		System.out.println(this.name + "의 " + s.name + "을(를) 발동! " + e.name + "은(는) " + actualDamage + "의 피해를 입었습니다!");
		return this.name + "의 " + s.name + "을(를) 발동! " + e.name + "은(는) " + actualDamage + "의 피해를 입었습니다!";
	}
	
	//벡터 이용 공격(폐기)
//	public void attack(Enemy e, Skill s) {
//		int sum = 0, choice = 0;
//		String skill_name = "";
//		int random = (int)(Math.random() * 99 + 1);
//		Vector<Integer> runningTotal = new Vector<Integer>();	//확률 누적용 벡터
//		
//		for(var it : skillSetVect) {
//			System.out.println("it.probability: " + it.probability);
//			sum += it.probability;
//			runningTotal.add(sum);
//			System.out.println("sum: " + sum);
//		}
//		runningTotal.add(0);	//확률 검사를 편하게 하도록 맨 뒤에 0을 추가
//		
//		for(choice=0; choice<runningTotal.size()-1; choice++) {
//			if(random >= runningTotal.get(choice) && random <= runningTotal.get(choice + 1)) {
//				skill_name = skillSetVect.elementAt(choice).name;	//선택한 스킬의 이름 추출
//				System.out.println("skill_name: " + skill_name);
//				break;
//			}
//		}
//		int maxDamage = skillSetVect.elementAt(choice).maxDamage;
//		int minDamage = skillSetVect.elementAt(choice).minDamage;
//		int damageRange = maxDamage - minDamage + 1;			//대미지 폭 계산
//		int actualDamage = (int)(Math.random() * damageRange + minDamage);
//		
//		e.hp -= actualDamage;
//		System.out.println(this.name + "의 " + skill_name + "을(를) 발동!" + e.name + "은(는) " + actualDamage + "의 피해를 입었습니다!");
//	}
	
	//HashMap 이용 공격
//	public void attack(Enemy e) {
//		int sum = 0, choice = 0;
//		String skill_name;
//		Vector<Integer> runningTotal = new Vector<Integer>();	//확률 누적용 벡터
//		for(var it : skillSet.keySet()) {
//			sum += skillSet.get(it).probability;
//			runningTotal.add(sum);
//		}
//		runningTotal.add(0);	//확률 검사를 편하게 하도록 맨 뒤에 0을 추가
//		
//		int random = (int)(Math.random() * 99 + 1);
//		
//		for(choice=0; choice<runningTotal.size(); choice++) {
//			if(random >= runningTotal.get(choice) && random <= runningTotal.get(choice + 1)) {
//				skill_name = skillSet.elementAt(choice).name;	//선택한 스킬의 이름 추출
//				break;
//			}
//		}
//		int maxDamage = skillSet.elementAt(choice).maxDamage;
//		int minDamage = skillSet.elementAt(choice).minDamage;
//		int damageRange = maxDamage - minDamage + 1;			//대미지 폭 계산
//		int actualDamage = (int)(Math.random() * damageRange + minDamage);
//		
//		e.hp -= actualDamage;
//		System.out.println(this.name + "의 " + skill_name + "을(를) 발동!" + e.name + "은(는) " + actualDamage + "의 피해를 입었습니다!");
//	}

//	//플레이어의 공격은 min, max값을 인자로 받아 그 범위의 대미지 출력. attack 이후 isPlayerAlive() 호출하기
//	public void attack(Enemy e, int damage) {
//		e.hp = e.hp - damage;
//		
//		// 원래 그림
//		original = (ImageIcon) s.getIcon();
//
//		// 슬라임이 당하는 그림.
////		ImageIcon bsImg_fire = new ImageIcon(GameActivity.class.getResource("/study/img/slime2(blue).png"));
////		ImageIcon rsImg_fire = new ImageIcon(GameActivity.class.getResource("/study/img/slime2(red).png"));
//		ImageIcon bsImg_fire = new ImageIcon(GameActivity.class.getResource("/img/slime2(blue).png"));
//		ImageIcon rsImg_fire = new ImageIcon(GameActivity.class.getResource("/img/slime2(red).png"));
//
//		//슬라임에 따라 당하는 그림을 다르게 설정.
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
			//이미지 아이콘에서 이미지를 추출
//			Image colorImage = original.getImage();
//			timer1.cancel();//이 코드 생략 시 죽은 후에 46행 실행됨
			
//			if (e == GameActivity.bs1) {
//				GameActivity.btn1.setEnabled(false);
//			} else {
//				GameActivity.btn2.setEnabled(false);
//			}
			
			//흑백으로 변경
//			ImageFilter filter = new GrayFilter(true, 50);
//			ImageProducer producer = new FilteredImageSource(colorImage.getSource(), filter);
//			Image gImage = Toolkit.getDefaultToolkit().createImage(producer);
			
//			s.setIcon(new ImageIcon(gImage));
			
//			GameActivity.lbl.setText(e.name + "는 사망했다");
//			GameActivity.lbl2.setText("");
//
//		} else {
//			GameActivity.lbl.setText(name + "의 공격. " + e.name + "의 체력은 " + e.hp + ".");
//
//		}

}
