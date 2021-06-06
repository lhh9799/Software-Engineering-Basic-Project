import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.io.File;

//����ȭ��. �÷��̾��� �г����� �Է¹޴� ��Ƽ��Ƽ
public class IntroActivity extends JPanel {
	private JLabel UsernameLabel;
	private JTextField UsernameField;
	private String Username;
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		//�г��� ���̺�
		UsernameLabel = new JLabel("�г����� �Է��ϼ���");
		UsernameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		UsernameLabel.setFont(new Font("���� ���", Font.BOLD, 10));
		UsernameLabel.setBounds(300, 50, 100, 80);
		add(UsernameLabel);
		
		//���� ��Ƽ��Ƽ�� ��ȯ�����ϵ��� �ϴ� ActionListener
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();				//���̺��� �ؽ�Ʈ�� ������ ������̸� ������ ����
				if(Username.equals("")) {
					Username = "����";
				}
				win.GameActivity.updatePlayerNameLabel(Username);
				win.change("GameActivity", "IntroActivity");
			}
		}
		
		//���̵� �ؽ�Ʈ�ʵ�
		UsernameField = new JTextField();
		UsernameField.setBounds(290, 200, 116, 21);
		UsernameField.setColumns(12);
		add(UsernameField);
		//���� �ĵ� �������� �����ϵ��� ������ ���
		UsernameField.addActionListener(new ConfirmListener());	//���͸� ������ ���� ��Ƽ��Ƽ�� ��ȯ �����ϵ���
		//���̵� �ؽ�Ʈ�ʵ�� ��Ŀ��
		UsernameField.requestFocus();
		win.addWindowListener(new WindowAdapter() { 
			public void windowOpened( WindowEvent e ) { 
				UsernameField.requestFocus(); 
			} 
		});
		
		//�÷��̾� �г��� �Է� �� ���� ��Ƽ��Ƽ�� �Ѿ�� ���� Ȯ�� ��ư
		JButton ConfirmButton = new JButton("Ȯ��");
//		LogoutButton.setFont(new Font("���� ���", Font.BOLD, 11));
		ConfirmButton.setBounds(310, 300, 80, 20);
//		ConfirmButton.setBackground(new Color(255, 198, 168));
		add(ConfirmButton);
		ConfirmButton.addActionListener(new ConfirmListener());
	}
}