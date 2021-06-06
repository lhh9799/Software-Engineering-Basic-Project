import javax.swing.*;
import java.util.Stack;

//패키지의 모든 Activity들을 관리하기 위한 클래스
class MyJPanel extends JFrame {
	private Stack<String> ActivityStack = new Stack<>();	//실행된 화면의 순서를 기록하기 위한 스택
	
	IntroActivity IntroActivity = null;
	GameActivity GameActivity = null;
//	OutroActivity OutroActivity = null;
	
	public MyJPanel() {
		IntroActivity			=	new IntroActivity(this);
		GameActivity			=	new GameActivity(this);
//		OutroActivity			=	new OutroActivity(this);
		
		setTitle("CNU Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
//		setResizable(false);
		setLocationRelativeTo(null);	//화면 가운데 배치
		setVisible(true);
		add(IntroActivity);
//		add(GameActivity);
	}
	
	//화면을 전환시켜주는 메소드
	public void change(String panelName, String caller) {
		ActivityStack.push(caller);				//change 메소드를 부른 액티비티의 이름을 스택에 저장
		System.out.println("(push) " + panelName);
		getContentPane().removeAll();			//컨테이너의 모든 컴포넌트 제거
		switch(panelName) {
		//프로그램이 시작하면 모든 Activity들이 단 한 번 동적할당 되기 때문에(각 Activity들을 재사용 하기 때문에) 추가적인 설정이 필요한 Activity들은 init()메소드를 가지고 있다. 
		
			case "IntroActivity" : 											getContentPane().add(IntroActivity);			break;
			case "GameActivity" : 											getContentPane().add(GameActivity);				break;
//			case "OutroActivity" : 			IntroActivity.init();			getContentPane().add(OutroActivity);			break;
		}
		revalidate();							//배치관리자가 컴포넌트들을 다시 배치하도록 함
		repaint();								//컨테이너를 다시 그림
	}
	
	//이전 화면으로 되돌아가는 메소드 (뒤로가기 기능을 함). 뒤로가기의 경우 로그인정보가 바뀌지 않았으므로 정보를 다시 설정해 줄 필요가 없다.
	public void goback() {
		String panelName = ActivityStack.pop();	//스택에서 이전에 실행한 Activity의 이름을 가져옴
		System.out.println("(pop) " + panelName);
		getContentPane().removeAll();			//컨테이너의 모든 컴포넌트 제거
		switch(panelName) {
			case "IntroActivity" : 	getContentPane().add(IntroActivity);	break;
			case "GameActivity" : 	getContentPane().add(GameActivity);		break;
//			case "OutroActivity" : 	getContentPane().add(OutroActivity);	break;
		}
		revalidate();							//배치관리자가 컴포넌트들을 다시 배치하도록 함
		repaint();								//컨테이너를 다시 그림
	}
}