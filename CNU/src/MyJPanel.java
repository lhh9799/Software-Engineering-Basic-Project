import javax.swing.*;
import java.util.Stack;

//��Ű���� ��� Activity���� �����ϱ� ���� Ŭ����
class MyJPanel extends JFrame {
	private Stack<String> ActivityStack = new Stack<>();	//����� ȭ���� ������ ����ϱ� ���� ����
	
	IntroActivity IntroActivity = null;
	GameActivity GameActivity = null;
	VictoryEndingActivity VictoryEndingActivity = null;
	DefeatEndingActivity DefeatEndingActivity = null;
	
	public MyJPanel() {
		IntroActivity			=	new IntroActivity(this);
		GameActivity			=	new GameActivity(this);
		VictoryEndingActivity	=	new VictoryEndingActivity(this);
		DefeatEndingActivity	=	new DefeatEndingActivity(this);
		
		setTitle("CNU Life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);	//ȭ�� ��� ��ġ
		setVisible(true);
		add(IntroActivity);
//		add(GameActivity);
//		add(VictoryEndingActivity);
//		add(DefeatEndingActivity);
	}
	
	//ȭ���� ��ȯ�����ִ� �޼ҵ�
	public void change(String panelName, String caller) {
		ActivityStack.push(caller);				//change �޼ҵ带 �θ� ��Ƽ��Ƽ�� �̸��� ���ÿ� ����
		System.out.println("(push) " + panelName);
		getContentPane().removeAll();			//�����̳��� ��� ������Ʈ ����
		switch(panelName) {
		//���α׷��� �����ϸ� ��� Activity���� �� �� �� �����Ҵ� �Ǳ� ������(�� Activity���� ���� �ϱ� ������) �߰����� ������ �ʿ��� Activity���� init()�޼ҵ带 ������ �ִ�. 
		
			case "IntroActivity" : 											getContentPane().add(IntroActivity);			break;
			case "GameActivity" : 			GameActivity.init();			getContentPane().add(GameActivity);				break;
			case "VictoryEndingActivity" :	VictoryEndingActivity.init();	getContentPane().add(VictoryEndingActivity);	break;
			case "DefeatEndingActivity" :	DefeatEndingActivity.init();	getContentPane().add(DefeatEndingActivity);		break;
		}
		revalidate();							//��ġ�����ڰ� ������Ʈ���� �ٽ� ��ġ�ϵ��� ��
		repaint();								//�����̳ʸ� �ٽ� �׸�
	}
	
	//���� ȭ������ �ǵ��ư��� �޼ҵ� (�ڷΰ��� ����� ��). �ڷΰ����� ��� �α��������� �ٲ��� �ʾ����Ƿ� ������ �ٽ� ������ �� �ʿ䰡 ����.
	public void goback() {
		String panelName = ActivityStack.pop();	//���ÿ��� ������ ������ Activity�� �̸��� ������
		System.out.println("(pop) " + panelName);
		getContentPane().removeAll();			//�����̳��� ��� ������Ʈ ����
		switch(panelName) {
			case "IntroActivity" : 	getContentPane().add(IntroActivity);	break;
			case "GameActivity" : 	getContentPane().add(GameActivity);		break;
//			case "OutroActivity" : 	getContentPane().add(OutroActivity);	break;
		}
		revalidate();							//��ġ�����ڰ� ������Ʈ���� �ٽ� ��ġ�ϵ��� ��
		repaint();								//�����̳ʸ� �ٽ� �׸�
	}
}