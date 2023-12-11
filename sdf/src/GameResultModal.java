import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GameResultModal extends JDialog{
	// 생성자에 넘겨받을 레이블
	private JLabel minLabel, secLabel;
	// 게임결과화면에 필요한 레이블 추가 
	private JLabel titleLabel = new JLabel("**GAME OVER**");
	private JLabel userNameTxtLabel = new JLabel("USERNAME:");
	private JLabel scoreTxtLabel = new JLabel("SCORE:");
	private JLabel timeTxtLabel = new JLabel("TIME:");
	
	private JLabel userNameLabel = new JLabel(GameManagement.name);
	private JLabel scoreLabel = new JLabel(Integer.toString(GameManagement.score));
	
	// 버튼
	private JButton confirmBtn = new JButton("확인");
	
	// 컨테이너
	private Container c;
	
	// 폰트
	private Font titleTextFont = new Font("맑은 고딕", Font.BOLD, 40);
	private Font subTitleFont = new Font("맑은 고딕", Font.BOLD, 30);
	private Font contentTextFont = new Font("맑은 고딕", Font.PLAIN, 30);
	
	public GameResultModal(String min, String sec) {
		this.minLabel = new JLabel(min+"분");
		this.secLabel = new JLabel(sec+"초");
		
		setTitle("Game Result");
		setSize(500,500);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		
		
		makeGameResultContent();
		addEvent();
	}
	public void makeGameResultContent() {
		c = getContentPane();
		c.setLayout(null);
		
		// 타이틀
		titleLabel.setBounds(75,20,350,50);
		titleLabel.setFont(titleTextFont);
		titleLabel.setForeground(Color.red);
		c.add(titleLabel);
		
		// 사용자 이름
		userNameTxtLabel.setBounds(20,80,300,50);
		userNameTxtLabel.setFont(subTitleFont);
		c.add(userNameTxtLabel);
		
		userNameLabel.setBounds(200,85,150,30);
		userNameLabel.setFont(contentTextFont);
		c.add(userNameLabel);
		
		// 스코어
		scoreTxtLabel.setBounds(20,150,300,50);
		scoreTxtLabel.setFont(subTitleFont);
		c.add(scoreTxtLabel);
		
		scoreLabel.setBounds(140,160,150,30);
		scoreLabel.setFont(contentTextFont);
		c.add(scoreLabel);
		
		// 시간
		timeTxtLabel.setBounds(20,220,150,50);
		timeTxtLabel.setFont(subTitleFont);
		c.add(timeTxtLabel);
		
		minLabel.setBounds(120,225,150,30);
		minLabel.setFont(contentTextFont);
		c.add(minLabel);

		secLabel.setBounds(180,225,150,30);
		secLabel.setFont(contentTextFont);
		c.add(secLabel);
		
		// 확인 버튼
		confirmBtn.setBounds(150,320,150,50);
		confirmBtn.setFont(subTitleFont);
		c.add(confirmBtn);

		// 테두리선 그리기
		Color borderColor = new Color(21,40,90);
        getRootPane().setBorder(new LineBorder(borderColor, 30));
	}
	public void addEvent() {
		// 확인 버튼을 클릭했을 때 이벤트 발생
		confirmBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});

	}
	
}
