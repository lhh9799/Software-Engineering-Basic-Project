import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//�й����� �� â
public class DefeatEndingActivity extends JPanel {
	private ImageIcon DefeatImageIcon;
	private JLabel DefeatImage;
	private Music Music;
	
	public void init() {
		JOptionPane.showMessageDialog(null, "�л��� �޾ҽ��ϴ�!", "�й�", JOptionPane.INFORMATION_MESSAGE);	//���̾�α�
		Music = new Music("music/Overwatch_Defeat_30s.mp3", false);
		Music.start();	//���� ����
	}
	
	public DefeatEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		int backgroundImageWidth = win.getViewportWidth() - 10;			//���� ������ ǥ�õǴ� ������ �ʺ� - 10�ȼ�
		int backgroundImageHeight = win.getViewportHeight() - 10;		//���� ������ ǥ�õǴ� ������ ���� - 10�ȼ�
		
		DefeatImageIcon = new ImageIcon("newImg/jaesugang.jpeg");
		Image img = DefeatImageIcon.getImage();
		Image sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);
		DefeatImage = new JLabel(new ImageIcon(sizeChangedImage));
		DefeatImage.setBounds(5, 5, backgroundImageWidth, backgroundImageHeight);
		add(DefeatImage);
	}
}