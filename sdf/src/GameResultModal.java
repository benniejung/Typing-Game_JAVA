import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class GameResultModal extends JDialog{
	// 생성자에 넘겨받을 레이블
	private JLabel minLabel, secLabel;
	// 게임결과화면에 필요한 레이블 추가 
	private JLabel titleLabel = new JLabel("GAME OVER");
	private JLabel userNameLabel = new JLabel("USERNAME:");
	private JLabel scoreLabel = new JLabel("SCORE:");
	private JLabel timeLabel = new JLabel("TIME:");
	
	// 컨테이너
	private Container c;
	
	// 폰트
	private Font defaultFont = new Font("맑은 고딕", Font.BOLD, 30);
	
	public GameResultModal(JLabel minLabel, JLabel secLabel) {
		this.minLabel = minLabel;
		this.secLabel = secLabel;
		
		setTitle("Game Result");
		setSize(500,400);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null); // 화면 가운데 출력시키기
		
		makeGameResultContent();
	}
	public void makeGameResultContent() {
		c = getContentPane();
		c.setLayout(null);
		
		// 타이틀
		titleLabel.setBounds(250,50,150,30);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		c.add(titleLabel);

	}

}
