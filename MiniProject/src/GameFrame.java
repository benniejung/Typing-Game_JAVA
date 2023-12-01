import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		setTitle("게임이름!!"); // !! 나중에 변경해야함 !!
		setSize(1000,800);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 컨텐트팬 크기 조절 못하게
	}
	
	
}