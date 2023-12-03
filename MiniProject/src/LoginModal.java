import java.awt.Container;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class NameAlertModal extends JOptionPane{
	public NameAlertModal() {
		JOptionPane.showMessageDialog(this, "이름을 입력하세요!", "Message",INFORMATION_MESSAGE);
	}
}
class LanguageAlertModal extends JOptionPane{
	public LanguageAlertModal() {
		JOptionPane.showMessageDialog(this, "언어를 선택하세요!", "Message",INFORMATION_MESSAGE);
	}
}

public class LoginModal extends JDialog{
	private Container c;
	// 이름과 단어 라벨 생성
	private JLabel nameLabel = new JLabel("Your name: ");
	private JLabel chooseLanguageLabel = new JLabel("Choose Language: ");
	// 이름 입력필드와 단어 선택라디오버튼 생성
	private JTextField inputNameField = new JTextField(20);
	private ButtonGroup g;
	private JRadioButton hangeulBtn = new JRadioButton("Korean");
	private JRadioButton engBtn = new JRadioButton("English");
	// 확인 버튼
	private JButton confirmBtn = new JButton("확인");
	// StartFrame 생성
	private StartFrame startFrame;
	
	// 생성자
	public LoginModal(StartFrame startFrame) {
		this.startFrame = startFrame;
		setTitle("Login");
		setSize(400,300);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		
		allContentLogin(); // 모든 컴포넌트 부착
		allEventListener(); // 모든 이벤트 부착
		
	}
	public void allContentLogin() {
		c = getContentPane();
		c.setLayout(null);
		
		// 이름 입력 크기, 위치, 폰트 설정
		nameLabel.setBounds(20,40,150,30);
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		c.add(nameLabel);
		
		inputNameField.setBounds(150,45,160,30);
		c.add(inputNameField);
		
		// 언어 선택 크기, 위치, 폰트 설정
		chooseLanguageLabel.setBounds(20,90,200,30);
		chooseLanguageLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		c.add(chooseLanguageLabel);
		
		// 언어 그룹화
		g = new ButtonGroup();
		g.add(hangeulBtn);
		g.add(engBtn);
		
		// 언어 선택버튼 크기,위치, 폰트 설정
		hangeulBtn.setBounds(20,130,150,30);
		hangeulBtn.setFont(new Font("Dialog", Font.PLAIN, 20));
		c.add(hangeulBtn);
		
		engBtn.setBounds(170,130,150,30);
		engBtn.setFont(new Font("Dialog", Font.PLAIN, 20));
		c.add(engBtn);
		
		// 확인 버튼 크기, 위치, 폰트 설정
		confirmBtn.setBounds(150, 200, 100, 30);
		confirmBtn.setFont(new Font("Dialog", Font.BOLD, 20));
		c.add(confirmBtn);
		
	}
	private void allEventListener() {
		// 확인 버튼을 클릭했을 때 이벤트 발생
		confirmBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String name = inputNameField.getText(); // 입력된 이름을 name변수에 담는다
				
				if(name.equals("") || name == null) { // 이름이 입력되지 않은 경우
					NameAlertModal nameAlertModal = new NameAlertModal(); // NameAlertModal이 뜨도록
				}
				else if(!hangeulBtn.isSelected() && !engBtn.isSelected()) { // 언어 선택하지 않은 경우
					LanguageAlertModal languageAlertModal = new LanguageAlertModal();
				}
				else { // 모두 입력이 되었을 경우
					dispose();
					startFrame.setVisible(false);
					GameApp.run();
				}
			}
			
		});
	}
};
