import javax.swing.JLabel;

public class StartCountThread extends Thread{
	
	// 생성자에게 전달 위한 레퍼런스 선언
	private ProfileAndItemPanel profileAndItemPanel = null;
	private JLabel timeLabel = null;
	private GamePanel gamePanel = null;
	
	// 카운트 변수
	private int count = 3;
	
	// 생성자
	public StartCountThread(ProfileAndItemPanel profileAndItemPanel, JLabel timeLabel) {
		this.profileAndItemPanel = profileAndItemPanel;
		this.timeLabel = timeLabel;

		
	}
	
	@Override
	public void run() {
		while(true) {
			try { // 3초 카운트 다운
				sleep(1000);
				count--;
				timeLabel.setText(Integer.toString(count));
				if (count == 0) { 
					timeLabel.setText("게임 시작!");
					interrupt(); 
				}
			}
			catch (InterruptedException e) { // interrupt 받으면
				// profileAndItemPanel.startGame(); // 게임 시작
				profileAndItemPanel.timerUI(); // 게임 타이머 그리기
				profileAndItemPanel.timerStart(); // 게임 타이머 스레드 시작
				
				
				return;
			}
		}
	}
}
