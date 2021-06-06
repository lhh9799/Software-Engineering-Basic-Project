import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//승리했을 때 창
public class VictoryEndingActivity extends JPanel {
	private ImageIcon VictoryImageIcon;
	private JLabel VictoryImage;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Victory_30s.mp3", false);
		Music.start();	//음악 시작
		JOptionPane.showMessageDialog(null, "무사히 2학년 한 학기를 완수하였다.", "승리",  JOptionPane.INFORMATION_MESSAGE);
	}
	
	public VictoryEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		int backgroundImageWidth = win.getViewportWidth() - 10;			//실제 내용이 표시되는 영역의 너비 - 10픽셀
		int backgroundImageHeight = win.getViewportHeight() - 10;		//실제 내용이 표시되는 영역의 높이 - 10픽셀
		
		VictoryImageIcon = new ImageIcon("newImg/jongkang2.png");
		Image img = VictoryImageIcon.getImage();
		Image sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);
		VictoryImage = new JLabel(new ImageIcon(sizeChangedImage));
		VictoryImage.setBounds(5, 5, backgroundImageWidth, backgroundImageHeight);
		add(VictoryImage);
	}
}