import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.io.File;

//����ȭ��. �÷��̾��� �г����� �Է¹޴� ��Ƽ��Ƽ
public class DefeatEndingActivity extends JPanel {
	private ImageIcon DefeatImageIcon;
	private JLabel DefeatImage;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Defeat_30s.mp3", false);
		Music.start();	//���� ����
		JOptionPane.showMessageDialog(null, "�л��� ������ �����Ͽ����ϴ�.", "���� �а���!", JOptionPane.INFORMATION_MESSAGE);	//���̾�α�
	}
	
	public DefeatEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		DefeatImageIcon = new ImageIcon("newImg/jaesugang.jpeg");
		DefeatImage = new JLabel(DefeatImageIcon);
		DefeatImage.setBounds(25, 40, 600, 400);
		add(DefeatImage);
		
//		init();	//�����ؾ���!
	}
}