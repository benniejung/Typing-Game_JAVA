import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileAndItemPanel extends JPanel{
	private JLabel timeLabel = new JLabel("3"); // 3초 타이머
	
	private JLabel minLabel = new JLabel("00");
	private JLabel secLabel = new JLabel("00");
	private JLabel divLabel = new JLabel(" : ");
	

	// 스레드
	private StartCountThread startCountThread = null;
	private TimerThread timerThread = new TimerThread(this,timeLabel, minLabel, divLabel, secLabel);
	private GameThread gameThread = null;
	
	// 생성자
	public ProfileAndItemPanel() {
		
		setOpaque(true); 
		this.setLayout(null); // 컴포넌트 배치 자유롭게
		timeLabel.setBounds(140, 20, 300, 30); // 타이머 위치 설정
		timeLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		this.add(timeLabel);
		setBackground(Color.gray);
	} 
//	// 이 패널에도 gamePanel을 가질 수 있도록
//	public void setGamePanel(GamePanel gamePanel) {
//        this.gamePanel = gamePanel;
//    }
	// 3초 카운트 세는 메소드
	public void startCountDown() {
		startCountThread = new StartCountThread(this, timeLabel);
		startCountThread.start();
	}
	
	// 타이머 그리는 메소드
	public void timerUI() {
		remove(timeLabel);
		minLabel.setBounds(90, 20, 300, 30); // 분
		minLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		divLabel.setBounds(140, 20, 300, 30); // :
		divLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
		
		secLabel.setBounds(190, 20, 300, 30); // 초
		secLabel.setFont(new Font("Dialog", Font.PLAIN, 30));

        add(minLabel);
        add(secLabel);
        add(divLabel);
        
        repaint();
	}
	// 타이머 메소드
	public void timerStart() {
		timerThread.start();
	}


}
