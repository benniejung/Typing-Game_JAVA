import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;

public class StartFrame extends JFrame{
	private StartPanel startPanel = new StartPanel();
	public StartFrame() {
		setTitle("게임이름!!"); // !! 나중에 변경해야함 !!
		setSize(1000,800);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 컨텐트팬 크기 조절 못하게
		
		Container c = getContentPane(); // 컴포넌트를 붙이기위한 컨텐트팬 생성
		c.add(startPanel);
	}


}
