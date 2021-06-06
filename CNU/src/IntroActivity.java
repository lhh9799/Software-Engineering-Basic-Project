import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.io.File;

//시작화면. 플레이어의 닉네임을 입력받는 액티비티
public class IntroActivity extends JPanel {
	private JLabel UsernameLabel;
	private JTextField UsernameField;
	private String Username;
	
	public IntroActivity(MyJPanel win) {
		setLayout(null);
		setBackground(new Color(183, 215, 216));
		setBorder(new TitledBorder(new LineBorder(Color.BLACK, 5), ""));
		
		//닉네임 레이블
		UsernameLabel = new JLabel("닉네임을 입력하세요");
		UsernameLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		UsernameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		UsernameLabel.setBounds(300, 50, 100, 80);
		add(UsernameLabel);
		
		//다음 액티비티로 전환가능하도록 하는 ActionListener
		class ConfirmListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Username = UsernameField.getText();				//레이블에서 텍스트를 가져와 사용자이름 변수에 저장
				if(Username.equals("")) {
					Username = "봉비";
				}
				win.GameActivity.updatePlayerNameLabel(Username);
				win.change("GameActivity", "IntroActivity");
			}
		}
		
		//아이디 텍스트필드
		UsernameField = new JTextField();
		UsernameField.setBounds(290, 200, 116, 21);
		UsernameField.setColumns(12);
		add(UsernameField);
		//엔터 쳐도 다음으로 진행하도록 리스너 등록
		UsernameField.addActionListener(new ConfirmListener());	//엔터를 누르면 다음 액티비티로 전환 가능하도록
		//아이디 텍스트필드로 포커싱
		UsernameField.requestFocus();
		win.addWindowListener(new WindowAdapter() { 
			public void windowOpened( WindowEvent e ) { 
				UsernameField.requestFocus(); 
			} 
		});
		
		//플레이어 닉네임 입력 후 다음 액티비티로 넘어가기 위한 확인 버튼
		JButton ConfirmButton = new JButton("확인");
//		LogoutButton.setFont(new Font("맑은 고딕", Font.BOLD, 11));
		ConfirmButton.setBounds(310, 300, 80, 20);
//		ConfirmButton.setBackground(new Color(255, 198, 168));
		add(ConfirmButton);
		ConfirmButton.addActionListener(new ConfirmListener());
	}
}