import javax.swing.*;

//��Ű���� ��� Activity���� �����ϱ� ���� Ŭ����
class MyJPanel extends JFrame {
	IntroActivity introActivity = null;
	GameActivity gameActivity = null;
	VictoryEndingActivity victoryEndingActivity = null;
	DefeatEndingActivity defeatEndingActivity = null;
	
	public MyJPanel() {
		setTitle("������ ������");			//â�� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� ������ ���α׷� ����
		setSize(1000, 600);				//â�� �ʺ�, ���� ����
		setLocationRelativeTo(null);	//ȭ�� ��� ��ġ
		setVisible(true);				//ȭ�鿡 â�� ������(â�� ũ�� ������ �������� ���� �ߺ� ȣ��)
		this.setResizable(false);		//ũ�� ���� �Ұ�
		
		introActivity			=	new IntroActivity(this);
		gameActivity			=	new GameActivity(this);
		victoryEndingActivity	=	new VictoryEndingActivity(this);
		defeatEndingActivity	=	new DefeatEndingActivity(this);
		
		add(introActivity);				//�ֻ��� �����̳ʿ� introActivity ����
		setVisible(true);				//ȭ�鿡 â�� ������
	}
	
	//ȭ���� ��ȯ�����ִ� �޼ҵ�
	public void change(String panelName) {
		getContentPane().removeAll();	//�����̳��� ��� ������Ʈ ����
		switch(panelName) {
			//���α׷��� �����ϸ� ��� Activity���� �� �� �� �����Ҵ� �Ǳ� ������(�� Activity���� ���� �ϱ� ������) �߰����� ������ �ʿ��� Activity���� init()�޼ҵ带 ������ �ִ�. 
			case "introActivity" : 											getContentPane().add(introActivity);			break;
			case "gameActivity" : 			gameActivity.init();			getContentPane().add(gameActivity);				break;
			case "victoryEndingActivity" :	victoryEndingActivity.init();	getContentPane().add(victoryEndingActivity);	break;
			case "defeatEndingActivity" :	defeatEndingActivity.init();	getContentPane().add(defeatEndingActivity);		break;
		}
		revalidate();					//��ġ�����ڰ� ������Ʈ���� �ٽ� ��ġ�ϵ��� ��
		repaint();						//�����̳ʸ� �ٽ� �׸�
	}
	
	//���� ������ ǥ�õǴ� ���� ����(Ÿ��Ʋ��, ��輱 ����)
	public int getViewportHeight() {
		return this.getContentPane().getSize().height;
	}
	
	public int getViewportWidth() {
		return this.getContentPane().getSize().width;
	}
}