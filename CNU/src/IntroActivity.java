import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//����ȭ��. �÷��̾��� �г����� �Է¹޴� ��Ƽ��Ƽ
public class IntroActivity extends JPanel {
	private JLabel UsernameLabel;		//�г��� �Է��� ��û�ϴ� ���̺�
	private JTextField UsernameField;	//�г����� �Է��� �ؽ�Ʈ�ʵ�
	private String Username;			//�г����� �����ϴ� ���ڿ� ����
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);				//��ġ�����ڸ� null�� ����(��� ������Ʈ�� ��ġ�� ���� ����)
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		//�г��� ��û ���̺�
		UsernameLabel = new JLabel("�г����� �Է��ϼ���");
		UsernameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		UsernameLabel.setFont(new Font("���� ���", Font.BOLD, 10));
		UsernameLabel.setBounds(300, 50, 100, 80);
		add(UsernameLabel);
		
		//���� ��Ƽ��Ƽ�� ��ȯ�����ϵ��� �ϴ� �׼Ǹ�����
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();					//���̺��� �ؽ�Ʈ�� ������ ������̸� ������ ����
				if(Username.equals("")) {							//����ڰ� �г����� �Է����� �ʾҴٸ�
					Username = "����";								//"����"�� ����
				}
				win.gameActivity.updatePlayerNameLabel(Username);	//player �ν��Ͻ��� ���̺��� ������� �г������� ����
				win.change("gameActivity");		//GameActivity�� ��ȯ
			}
		}
		
		//�г��� �ؽ�Ʈ�ʵ�
		UsernameField = new JTextField();
		UsernameField.setBounds(290, 200, 116, 21);
		UsernameField.setColumns(12);							//�ؽ�Ʈ�ʵ��� ũ�� ����(12��)
		add(UsernameField);
		//���͸� �Է��ص� �������� ������ �� �ֵ��� �ؽ�Ʈ�ʵ忡 ������ ���
		UsernameField.addActionListener(new ConfirmListener());	//���͸� ������ ���� ��Ƽ��Ƽ�� ��ȯ �����ϵ���
		//�г��� �ؽ�Ʈ�ʵ�� ��Ŀ��
		UsernameField.requestFocus();
		win.addWindowListener(new WindowAdapter() { 
			public void windowOpened(WindowEvent e) { 
				UsernameField.requestFocus(); 
			} 
		});
		
		//�÷��̾� �г��� �Է� �� ���� ��Ƽ��Ƽ�� �Ѿ�� ���� Ȯ�� ��ư
		JButton ConfirmButton = new JButton("Ȯ��");
		ConfirmButton.setBounds(310, 300, 80, 20);
		add(ConfirmButton);
		ConfirmButton.addActionListener(new ConfirmListener());
	}
}