import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//패배했을 때 창
public class DefeatEndingActivity extends JPanel {
	private ImageIcon DefeatImageIcon;
	private JLabel DefeatImage;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Defeat_30s.mp3", false);
		Music.start();	//음악 시작
		JOptionPane.showMessageDialog(null, "학사경고를 받았습니다!", "패배", JOptionPane.INFORMATION_MESSAGE);	//다이얼로그
	}
	
	public DefeatEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		int backgroundImageWidth = win.getViewportWidth() - 10;			//실제 내용이 표시되는 영역의 너비 - 10픽셀
		int backgroundImageHeight = win.getViewportHeight() - 10;		//실제 내용이 표시되는 영역의 높이 - 10픽셀
		
		DefeatImageIcon = new ImageIcon("newImg/jaesugang.jpeg");
		Image img = DefeatImageIcon.getImage();
		Image sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);
		DefeatImage = new JLabel(new ImageIcon(sizeChangedImage));
		DefeatImage.setBounds(5, 5, backgroundImageWidth, backgroundImageHeight);
		add(DefeatImage);
	}
}