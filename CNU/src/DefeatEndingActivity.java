import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.io.File;

//시작화면. 플레이어의 닉네임을 입력받는 액티비티
public class DefeatEndingActivity extends JPanel {
	private ImageIcon DefeatImageIcon;
	private JLabel DefeatImage;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Defeat_30s.mp3", false);
		Music.start();	//음악 시작
		JOptionPane.showMessageDialog(null, "학사경고 메일이 도착하였습니다.", "내가 학고라니!", JOptionPane.INFORMATION_MESSAGE);	//다이얼로그
	}
	
	public DefeatEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		DefeatImageIcon = new ImageIcon("newImg/jaesugang.jpeg");
		DefeatImage = new JLabel(DefeatImageIcon);
		DefeatImage.setBounds(25, 40, 600, 400);
		add(DefeatImage);
		
//		init();	//삭제해야함!
	}
}