import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

//시작화면. 플레이어의 닉네임을 입력받는 액티비티
public class IntroActivity extends JPanel {
	private JTextField UsernameField;		//닉네임을 입력할 텍스트필드
	private String Username;				//닉네임을 저장하는 문자열 변수
	private Image sizeChangedImage;			//실제 JPanel의 크기에 맞게 크기가 변경될 이미지
	private JLabel backgroundImageLabel;	//배경 이미지 레이블
	private ImageIcon backgroundImageIcon = new ImageIcon("newImg/CNU_Background.jpg");	//배경 이미지 아이콘
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);					//배치관리자를 null로 지정(모든 컴포넌트의 위치를 직접 지정)		
				
		//다음 액티비티로 전환가능하도록 하는 액션리스너
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();					//레이블에서 텍스트를 가져와 사용자이름 변수에 저장
				//사용자가 닉네임을 입력하지 않았거나 공백이라면
				if(Username.equals("") || Username.equals("닉네임을 입력하세요")) {
					Username = "봉비";								//"봉비"로 설정
				}
				win.gameActivity.updatePlayerNameLabel(Username);	//player 인스턴스와 레이블을 사용자의 닉네임으로 변경
				win.change("gameActivity");							//GameActivity로 전환
			}
		}
		
		//닉네임 텍스트필드
		UsernameField = new JTextField();
		UsernameField.setBounds(200, 240, 116, 21);
		UsernameField.setColumns(12);								//텍스트필드의 크기 설정(12자)
		UsernameField.setText("닉네임을 입력하세요");
		UsernameField.setFont(new Font("나눔고딕", Font.BOLD, 12));
		add(UsernameField);
		//엔터를 입력해도 다음으로 진행할 수 있도록 텍스트필드에 리스너 등록
		UsernameField.addActionListener(new ConfirmListener());		//엔터를 누르면 다음 액티비티로 전환 가능하도록
		//텍스트필드를 클릭하면 가이드 텍스트 삭제
		UsernameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UsernameField.setText("");	//텍스트 필드 초기화
			}
		});
		
		//플레이어 닉네임 입력 후 다음 액티비티로 넘어가기 위한 확인 버튼
		JButton ConfirmButton = new JButton("확인");
		ConfirmButton.setBounds(220, 270, 80, 20);
		ConfirmButton.setBackground(Color.WHITE);		//버튼에 색 설정
		ConfirmButton.addActionListener(new ConfirmListener());
		add(ConfirmButton);
		
		//배경 이미지
		int backgroundImageWidth = win.getViewportWidth();			//실제 내용이 표시되는 영역의 너비
		int backgroundImageHeight = win.getViewportHeight();		//실제 내용이 표시되는 영역의 높이
		Image img = backgroundImageIcon.getImage();					//기존 이미지아이콘을 가져옴
		sizeChangedImage = img.getScaledInstance(backgroundImageWidth, backgroundImageHeight, Image.SCALE_SMOOTH);	//사이즈변경 (Image.SCALE_SMOOTH: 속도보다 이미지의 품질 우선)
		backgroundImageIcon = new ImageIcon(sizeChangedImage);
		backgroundImageLabel = new JLabel(backgroundImageIcon);		//배경 이미지 레이블
		backgroundImageLabel.setBounds(0, 0, backgroundImageWidth, backgroundImageHeight);
		backgroundImageLabel.setOpaque(false);						//불투명처리 하여 다른 컴포넌트들이 위에 보이도록 함
		add(backgroundImageLabel);
	}
}