import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//����ȭ��. �÷��̾��� �г����� �Է¹޴� ��Ƽ��Ƽ
public class IntroActivity extends JPanel {
	private JLabel UsernameLabel;			//�г��� �Է��� ��û�ϴ� ���̺�
	private JTextField UsernameField;		//�г����� �Է��� �ؽ�Ʈ�ʵ�
	private String Username;				//�г����� �����ϴ� ���ڿ� ����
	private Image sizeChangedImage;			//���� JPanel�� ũ�⿡ �°� ũ�Ⱑ ����� �̹���
	private JLabel backgroundImageLabel;	//��� �̹��� ���̺�
	private ImageIcon backgroundImageIcon = new ImageIcon("newImg/CNU_Background.jpg");	//��� �̹��� ������
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);					//��ġ�����ڸ� null�� ����(��� ������Ʈ�� ��ġ�� ���� ����)		
				
		//���� ��Ƽ��Ƽ�� ��ȯ�����ϵ��� �ϴ� �׼Ǹ�����
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();					//���̺��� �ؽ�Ʈ�� ������ ������̸� ������ ����
				//����ڰ� �г����� �Է����� �ʾҰų� �����̶��
				if(Username.equals("") || Username.equals("�г����� �Է��ϼ���")) {
					Username = "����";								//"����"�� ����
				}
				win.gameActivity.updatePlayerNameLabel(Username);	//player �ν��Ͻ��� ���̺��� ������� �г������� ����
				win.change("gameActivity");							//GameActivity�� ��ȯ
			}
		}
		
		//�г��� �ؽ�Ʈ�ʵ�
		UsernameField = new JTextField();
		UsernameField.setBounds(200, 240, 116, 21);
		UsernameField.setColumns(12);								//�ؽ�Ʈ�ʵ��� ũ�� ����(12��)
		UsernameField.setText("�г����� �Է��ϼ���");
		UsernameField.setFont(new Font("�������", Font.BOLD, 12));
		add(UsernameField);
		//���͸� �Է��ص� �������� ������ �� �ֵ��� �ؽ�Ʈ�ʵ忡 ������ ���
		UsernameField.addActionListener(new ConfirmListener());		//���͸� ������ ���� ��Ƽ��Ƽ�� ��ȯ �����ϵ���
		//�ؽ�Ʈ�ʵ带 Ŭ���ϸ� ���̵� �ؽ�Ʈ ����
		UsernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UsernameField.setText("");	//�ؽ�Ʈ �ʵ� �ʱ�ȭ
			}
		});
		
		//�÷��̾� �г��� �Է� �� ���� ��Ƽ��Ƽ�� �Ѿ�� ���� Ȯ�� ��ư
		JButton ConfirmButton = new JButton("Ȯ��");
		ConfirmButton.setBounds(220, 270, 80, 20);
		add(ConfirmButton);
		ConfirmButton.addActionListener(new ConfirmListener());
		
		//��� �̹���
		int backgroundImageWidth = win.getViewportWidth();			//���� ������ ǥ�õǴ� ������ �ʺ�
		int backgroundImageHeight = win.getViewportHeight();		//���� ������ ǥ�õǴ� ������ ����
		Image img = backgroundImageIcon.getImage();					//���� �̹����������� ������
		sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);	//������� (Image.SCALE_SMOOTH: �ӵ����� �̹����� ǰ�� �켱)
		backgroundImageIcon = new ImageIcon(sizeChangedImage);
		backgroundImageLabel = new JLabel(backgroundImageIcon);		//��� �̹��� ���̺�
		backgroundImageLabel.setBounds(0, 0, backgroundImageWidth, backgroundImageHeight);
		backgroundImageLabel.setOpaque(false);						//������ó�� �Ͽ� �ٸ� ������Ʈ���� ���� ���̵��� ��
		add(backgroundImageLabel);
	}
}