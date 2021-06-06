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
public class VictoryEndingActivity extends JPanel {
	private String VictoryImagePath[] = {"newImg/seasonalSemester.png", "newImg/nonsan.jpg"};
//	private String VictoryImagePath[] = {"newImg/bongbi.png", "newImg/nonsan.jpg", "newImg/seasonalSemester.png"};
	private ImageIcon VictoryImageIcon[] = new ImageIcon[VictoryImagePath.length];
	private JLabel VictoryImage[] = new JLabel[VictoryImagePath.length];
	private boolean firstImage = true;
	private Music Music;
	
	public void init() {
		Music = new Music("music/Overwatch_Victory_30s.mp3", false);
		Music.start();
		JOptionPane.showMessageDialog(null, "무야~호~ 무사히 2학년 한 학기를 완수하였다.", "그만큼 신나시는 거지~", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public VictoryEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		class HiddenEvent extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
			//이미지 변경
				if(firstImage) {
					VictoryImage[0].setVisible(false);	//첫 번째 이미지 안보이도록
					VictoryImage[1].setVisible(true);	//첫 번째 이미지 안보이도록
					firstImage = false;
				}
				else {
					VictoryImage[0].setVisible(true);	//첫 번째 이미지 안보이도록
					VictoryImage[1].setVisible(false);	//첫 번째 이미지 안보이도록
					firstImage = true;
				}
			}
		}
		
		for(int i=0; i<VictoryImagePath.length; i++) {
			VictoryImageIcon[i] = new ImageIcon(VictoryImagePath[i]);
			VictoryImage[i] = new JLabel(VictoryImageIcon[i]);
			VictoryImage[i].setBounds(25, 40, 600, 400);
			VictoryImage[i].setVisible(false);	//기본적으로 안보이도록
			VictoryImage[i].addMouseListener(new HiddenEvent());	//리스너 등록
			add(VictoryImage[i]);
		}
		VictoryImage[0].setVisible(true);	//첫 번째 이미지만 보임
		
//		init();	//지워야함!
	}
}