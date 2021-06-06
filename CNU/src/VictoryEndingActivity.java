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
		JOptionPane.showMessageDialog(null, "����~ȣ~ ������ 2�г� �� �б⸦ �ϼ��Ͽ���.", "�׸�ŭ �ų��ô� ����~", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public VictoryEndingActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		class HiddenEvent extends MouseAdapter {
			@Override
			public void mousePressed(MouseEvent e) {
			//�̹��� ����
				if(firstImage) {
					VictoryImage[0].setVisible(false);	//ù ��° �̹��� �Ⱥ��̵���
					VictoryImage[1].setVisible(true);	//ù ��° �̹��� �Ⱥ��̵���
					firstImage = false;
				}
				else {
					VictoryImage[0].setVisible(true);	//ù ��° �̹��� �Ⱥ��̵���
					VictoryImage[1].setVisible(false);	//ù ��° �̹��� �Ⱥ��̵���
					firstImage = true;
				}
			}
		}
		
		for(int i=0; i<VictoryImagePath.length; i++) {
			VictoryImageIcon[i] = new ImageIcon(VictoryImagePath[i]);
			VictoryImage[i] = new JLabel(VictoryImageIcon[i]);
			VictoryImage[i].setBounds(25, 40, 600, 400);
			VictoryImage[i].setVisible(false);	//�⺻������ �Ⱥ��̵���
			VictoryImage[i].addMouseListener(new HiddenEvent());	//������ ���
			add(VictoryImage[i]);
		}
		VictoryImage[0].setVisible(true);	//ù ��° �̹����� ����
		
//		init();	//��������!
	}
}