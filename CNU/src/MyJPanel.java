import javax.swing.*;

//��Ű���� ��� Activity���� �����ϱ� ���� Ŭ����
class MyJPanel extends JFrame {
	IntroActivity introActivity = null;
	GameActivity gameActivity = null;
	
	public MyJPanel() {
		introActivity	=	new IntroActivity(this);
		gameActivity	=	new GameActivity(this);
		
		setTitle("������ ������");			//â�� ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�������� ������ ���α׷� ����
		setSize(700, 500);				//â�� �ʺ�, ���� ����
		setLocationRelativeTo(null);	//ȭ�� ��� ��ġ
		setVisible(true);				//ȭ�鿡 â�� ������
		add(introActivity);				//�ֻ��� �����̳ʿ� introActivity ����
	}
	
	//ȭ���� ��ȯ�����ִ� �޼ҵ�
	public void change(String panelName) {
		getContentPane().removeAll();	//�����̳��� ��� ������Ʈ ����
		switch(panelName) {
			case "introActivity" : 	getContentPane().add(introActivity);			break;
			case "gameActivity" : 	getContentPane().add(gameActivity);				break;
		}
		revalidate();					//��ġ�����ڰ� ������Ʈ���� �ٽ� ��ġ�ϵ��� ��
		repaint();						//������Ʈ���� �ٽ� �׸�
	}
}