import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

//시작화면. 플레이어의 닉네임을 입력받는 액티비티
public class IntroActivity extends JPanel {
	private JLabel UsernameLabel;			//닉네임 입력을 요청하는 레이블
	private JTextField UsernameField;		//닉네임을 입력할 텍스트필드
	private String Username;				//닉네임을 저장하는 문자열 변수
	private ImageIcon backgroundImageIcon;	//배경 이미지 아이콘
	private JLabel backgroundImage;			//배경 이미지 레이블
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);				//배치관리자를 null로 지정(모든 컴포넌트의 위치를 직접 지정)
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		//닉네임 요청 레이블
		UsernameLabel = new JLabel("닉네임을 입력하세요");
		UsernameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		UsernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		UsernameLabel.setBounds(300, 50, 100, 80);
		add(UsernameLabel);
		
		//다음 액티비티로 전환가능하도록 하는 액션리스너
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();					//레이블에서 텍스트를 가져와 사용자이름 변수에 저장
				if(Username.equals("")) {							//사용자가 닉네임을 입력하지 않았다면
					Username = "봉비";								//"봉비"로 설정
				}
				win.gameActivity.updatePlayerNameLabel(Username);	//player 인스턴스와 레이블을 사용자의 닉네임으로 변경
				win.change("gameActivity");		//GameActivity로 전환
			}
		}
		
		//닉네임 텍스트필드
		UsernameField = new JTextField();
		UsernameField.setBounds(290, 200, 116, 21);
		UsernameField.setColumns(12);							//텍스트필드의 크기 설정(12자)
		add(UsernameField);
		//엔터를 입력해도 다음으로 진행할 수 있도록 텍스트필드에 리스너 등록
		UsernameField.addActionListener(new ConfirmListener());	//엔터를 누르면 다음 액티비티로 전환 가능하도록
		//닉네임 텍스트필드로 포커싱
		UsernameField.requestFocus();
		win.addWindowListener(new WindowAdapter() { 
			public void windowOpened(WindowEvent e) { 
				UsernameField.requestFocus(); 
			} 
		});
		
		//플레이어 닉네임 입력 후 다음 액티비티로 넘어가기 위한 확인 버튼
		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.setBounds(310, 300, 80, 20);
		add(ConfirmButton);
		ConfirmButton.addActionListener(new ConfirmListener());
		
		//배경 이미지
//		backgroundImage.setBounds(20, 20, 20, 20);
//		backgroundImage.setBounds(20, 20, backgroundImage.getWidth(), backgroundImage.getHeight());
//		ImageIcon backgroundImageIcon = new ImageIcon("newImg/CNU_Background.jpg");
		
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		double width = screenSize.getWidth();
//		double height = screenSize.getHeight();
		
//		Dimension dim = win.getSize();
//		int height = dim.height;
//		double width = dim.getWidth();
//		System.out.println("width: " + width + " height: " + height);
//		System.out.println("height: " + height);
		
//		backgroundImage.setSize(WIDTH, HEIGHT);
//		backgroundImage = new JLabel(backgroundImageIcon);		//배경 이미지 레이블
//		ImageIcon sizeChangedImage = backgroundImageIcon.getImage();	//기존 이미지아이콘을 가져와서
//		sizeChangedImage.getIconHeight();
//		sizeChangedImage.getIconWidth();
//		backgroundImage.setBounds(20, 20, backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());
//		backgroundImage.setOpaque(false);	//불투명
//		add(backgroundImage);
	}
}