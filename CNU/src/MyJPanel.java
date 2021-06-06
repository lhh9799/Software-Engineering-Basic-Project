import javax.swing.*;

import java.awt.Dimension;
import java.util.Stack;

//��Ű���� ��� Activity���� �����ϱ� ���� Ŭ����
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
		
		setTitle("������ ������");			//â�� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� ������ ���α׷� ����
		setSize(700, 500);				//â�� �ʺ�, ���� ����
		setLocationRelativeTo(null);	//ȭ�� ��� ��ġ
		setVisible(true);				//ȭ�鿡 â�� ������
//		add(introActivity);				//�ֻ��� �����̳ʿ� introActivity ����
//		add(GameActivity);
//		add(VictoryEndingActivity);
//		add(DefeatEndingActivity);
		
		introActivity			=	new IntroActivity(this);
		gameActivity			=	new GameActivity(this);
		victoryEndingActivity	=	new VictoryEndingActivity(this);
		defeatEndingActivity	=	new DefeatEndingActivity(this);
		
		add(introActivity);				//�ֻ��� �����̳ʿ� introActivity ����
	}
	
	//ȭ���� ��ȯ�����ִ� �޼ҵ�
	public void change(String panelName) {
		getContentPane().removeAll();			//�����̳��� ��� ������Ʈ ����
		switch(panelName) {
			//���α׷��� �����ϸ� ��� Activity���� �� �� �� �����Ҵ� �Ǳ� ������(�� Activity���� ���� �ϱ� ������) �߰����� ������ �ʿ��� Activity���� init()�޼ҵ带 ������ �ִ�. 
			case "introActivity" : 											getContentPane().add(introActivity);			break;
			case "gameActivity" : 			gameActivity.init();			getContentPane().add(gameActivity);				break;
			case "victoryEndingActivity" :	victoryEndingActivity.init();	getContentPane().add(victoryEndingActivity);	break;
			case "defeatEndingActivity" :	defeatEndingActivity.init();	getContentPane().add(defeatEndingActivity);		break;
		}
		revalidate();							//��ġ�����ڰ� ������Ʈ���� �ٽ� ��ġ�ϵ��� ��
		repaint();								//�����̳ʸ� �ٽ� �׸�
	}
	
//	//double �迭 ����, �ƴϸ� pair
//	public Dimension getSize() {
//		return this.getSize();
//	}
}