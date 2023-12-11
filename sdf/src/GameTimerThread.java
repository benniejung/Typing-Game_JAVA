import javax.swing.JLabel;
import javax.swing.JPanel;

// 게임 타이머 스레드
public class GameTimerThread extends Thread{
	// 생성자에게 전달 위한 레퍼런스 선언
	private JPanel timePanel = null;
	private JLabel timeLabel = null;
	private JLabel minLabel = null;
	private JLabel divLabel = null;
	private JLabel secLabel = null;
	private ProfileAndScorePanel profileAndScorePanel = null;
	
	// 분, 초
	private int min,sec,t = 0;
	private int totalTimeInSeconds;
	
	
	// 생성자에게 전달 위한 레퍼런스 선언
	private Audio audio = null;
	private GamePanel gamePanel = null;
	//* private JLabel timeLabel = null;
	
	// 게임 시작 후 45초 카운트 다운을 위한 변수
	private int count = 45;

	
	// 게임 일시 중지 확인 Flag 관련 변수 및 메소드
	private boolean stopFlag = false;
	public boolean getStopFlag() { return stopFlag; }
	public void stopTimer() { stopFlag = true; }
	
	// 타이머 이어서 동작하는 메소드
    // 스레드 깨우기
	synchronized public void resumeTimer() { 
		
		stopFlag = false;
		this.notify(); 
		
	}

	// 대기 상태 걸어놓는 메소드
    // 스레드 대기
	synchronized private void waitFlag() {
		
		try { this.wait(); } 
		catch (InterruptedException e) { } 
		
	}
	
	// 생성자
	public GameTimerThread(GamePanel gamePanel, ProfileAndScorePanel profileAndScorePanel,JPanel timePanel,JLabel timeLabel, JLabel minLabel, JLabel divLabel,JLabel secLabel,Audio audio) {
		this.timePanel = timePanel;
		this.gamePanel = gamePanel;
		this.profileAndScorePanel = profileAndScorePanel;
		this.timeLabel = timeLabel;
		this.audio = audio;
		this.minLabel = minLabel;
		this.divLabel = divLabel;
		this.secLabel = secLabel;
		this.totalTimeInSeconds = Integer.parseInt(minLabel.getText()) * 60 + Integer.parseInt(secLabel.getText());
		
	}
	
	// 스레드 코드
	@Override
	public synchronized void run() {
		min = Integer.parseInt(minLabel.getText());
		sec = Integer.parseInt(secLabel.getText());

		//* timeLabel.setText(Integer.toString(count));
		while(true) {
			min = t% (1000*60) / 100 / 60;
			sec = t% (1000*60) / 100 % 60;

			try {
				sleep(10);
				if (stopFlag == true) waitFlag();
				minLabel.setText(String.format("%02d", min));
				secLabel.setText(String.format("%02d", sec));
				
				t++;
//				count--;
//				timeLabel.setText(Integer.toString(count));
				if (GameManagement.life == 0) { 
					timeLabel.setText("게임 종료"); 
					gamePanel.gameEnd(); 
					interrupt();
				}
			} catch (InterruptedException e) { return; }
		}
	}

}
