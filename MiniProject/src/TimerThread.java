import java.awt.Font;

import javax.swing.JLabel;

public class TimerThread extends Thread{
	// 생성자에게 전달 위한 레퍼런스 선언
	private GamePanel gamePanel = null;
	private ProfileAndItemPanel profileAndItemPanel = null;
	private JLabel timeLabel = null;
	private JLabel minLabel = null;
	private JLabel divLabel = null;
	private JLabel secLabel = null;
	
	// 분, 초
	private int min,sec,t = 0;
	

	public TimerThread(GamePanel gamePanel, ProfileAndItemPanel profileAndItemPanel, JLabel timeLabel, JLabel minLabel, JLabel divLabel, JLabel secLabel) {
		this.gamePanel = gamePanel;
		this.profileAndItemPanel = profileAndItemPanel;
		this.timeLabel = timeLabel;
		this.minLabel = minLabel;
		this.divLabel = divLabel;
		this.secLabel = secLabel;
		
	}
	public void run() {
		min = Integer.parseInt(minLabel.getText());
		sec = Integer.parseInt(secLabel.getText());
		
		while(true) {
			min = t% (1000*60) / 100 / 60;
			sec = t% (1000*60) / 100 % 60;
			
			try {
				sleep(10);
				minLabel.setText(String.format("%02d", min));
				secLabel.setText(String.format("%02d", sec));
				
				t++;
			} catch(InterruptedException e) {}
			
		}
		
		
	}
}
