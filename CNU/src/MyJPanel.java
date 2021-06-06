import javax.swing.*;

import java.awt.Dimension;
import java.util.Stack;

//패키지의 모든 Activity들을 관리하기 위한 클래스
class MyJPanel extends JFrame {
	IntroActivity introActivity = null;
	GameActivity gameActivity = null;
	VictoryEndingActivity victoryEndingActivity = null;
	DefeatEndingActivity defeatEndingActivity = null;
	
	public MyJPanel() {
//		introActivity			=	new IntroActivity(this);
//		gameActivity			=	new GameActivity(this);
//		victoryEndingActivity	=	new VictoryEndingActivity(this);
//		defeatEndingActivity	=	new DefeatEndingActivity(this);
		
		setTitle("전남대 라이프");			//창의 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//프레임을 닫으면 프로그램 종료
		setSize(700, 500);				//창의 너비, 높이 설정
		setLocationRelativeTo(null);	//화면 가운데 배치
		setVisible(true);				//화면에 창을 보여줌
//		add(introActivity);				//최상위 컨테이너에 introActivity 부착
//		add(GameActivity);
//		add(VictoryEndingActivity);
//		add(DefeatEndingActivity);
		
		introActivity			=	new IntroActivity(this);
		gameActivity			=	new GameActivity(this);
		victoryEndingActivity	=	new VictoryEndingActivity(this);
		defeatEndingActivity	=	new DefeatEndingActivity(this);
		
		add(introActivity);				//최상위 컨테이너에 introActivity 부착
	}
	
	//화면을 전환시켜주는 메소드
	public void change(String panelName) {
		getContentPane().removeAll();			//컨테이너의 모든 컴포넌트 제거
		switch(panelName) {
			//프로그램이 시작하면 모든 Activity들이 단 한 번 동적할당 되기 때문에(각 Activity들을 재사용 하기 때문에) 추가적인 설정이 필요한 Activity들은 init()메소드를 가지고 있다. 
			case "introActivity" : 											getContentPane().add(introActivity);			break;
			case "gameActivity" : 			gameActivity.init();			getContentPane().add(gameActivity);				break;
			case "victoryEndingActivity" :	victoryEndingActivity.init();	getContentPane().add(victoryEndingActivity);	break;
			case "defeatEndingActivity" :	defeatEndingActivity.init();	getContentPane().add(defeatEndingActivity);		break;
		}
		revalidate();							//배치관리자가 컴포넌트들을 다시 배치하도록 함
		repaint();								//컨테이너를 다시 그림
	}
	
//	//double 배열 리턴, 아니면 pair
//	public Dimension getSize() {
//		return this.getSize();
//	}
}