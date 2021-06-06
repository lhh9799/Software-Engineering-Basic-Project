import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//�¸����� �� â
public class VictoryEndingActivity extends JPanel {
	private ImageIcon VictoryImageIcon;
	private JLabel VictoryImage;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Victory_30s.mp3", false);
		Music.start();	//���� ����
		JOptionPane.showMessageDialog(null, "������ 2�г� �� �б⸦ �ϼ��Ͽ���.", "�¸�",  JOptionPane.INFORMATION_MESSAGE);
	}
	
	public VictoryEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		int backgroundImageWidth = win.getViewportWidth() - 10;			//���� ������ ǥ�õǴ� ������ �ʺ� - 10�ȼ�
		int backgroundImageHeight = win.getViewportHeight() - 10;		//���� ������ ǥ�õǴ� ������ ���� - 10�ȼ�
		
		VictoryImageIcon = new ImageIcon("newImg/jongkang2.png");
		Image img = VictoryImageIcon.getImage();
		Image sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);
		VictoryImage = new JLabel(new ImageIcon(sizeChangedImage));
		VictoryImage.setBounds(5, 5, backgroundImageWidth, backgroundImageHeight);
		add(VictoryImage);
	}
}