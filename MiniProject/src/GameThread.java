
public class GameThread extends Thread{
	private GamePanel gamePanel;
	// 생성자
	public GameThread() {
	}
	@Override
	public void run() {
		int count = 0;
		while(true) {
        	try {
        		if (count % 1000 == 0) gamePanel.chooseWord();
        		//gamePanel.setWords();
        		count++;
        		sleep(1);
        	} catch (InterruptedException e) { return; }
		}
	}
}
