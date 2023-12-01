import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	// 시작화면 버튼들
	private JButton startBtn = new JButton("게임시작");
	private JButton lankingBtn = new JButton("랭킹보기");
	private JButton addWordBtn = new JButton("단어편집");
	private JButton helpBtn = new JButton("도움말");
	// 타이틀 
	private JLabel titleLabel = new JLabel("상찌를 구해줘!");
	
	private ImageIcon startBgIcon = new ImageIcon("greenBg.jpg");
	private ImageIcon characIcon1 = new ImageIcon("상찌.png");
	
	public void paintComponent(Graphics g) {
		Image startBgImg = startBgIcon.getImage();
		g.drawImage(startBgImg, 0, 0, this.getWidth(), this.getHeight(), null);
		setOpaque(true);
		
		Image characImg1 = characIcon1.getImage();
		g.drawImage(characImg1, 10, 10, null);
		
	}
	public StartPanel() {
		setLayout(null); // 컴포넌트 배치 자유롭게 하기 위한 설정
		// 게임타이틀
		titleLabel.setFont(new Font("휴먼엑스포", Font.BOLD, 50));
		titleLabel.setSize(400,100);
		titleLabel.setLocation(400,10);
		add(titleLabel);
		
		// 시작하기버튼
		startBtn.setSize(200,50);
		startBtn.setFont(new Font("휴먼엑스포",Font.PLAIN, 20));
		startBtn.setLocation(400, 500);
		add(startBtn);
		// 랭킹보기버튼
		lankingBtn.setSize(200,50);
		lankingBtn.setFont(new Font("휴먼엑스포",Font.PLAIN, 20));
		lankingBtn.setLocation(400, 560);
		add(lankingBtn);
		
		// 단어편집버튼
		addWordBtn.setSize(200,50);
		addWordBtn.setFont(new Font("휴먼엑스포",Font.PLAIN, 20));
		addWordBtn.setLocation(400, 620);
		add(addWordBtn);
		
		// 도움말
		helpBtn.setSize(200,50);
		helpBtn.setFont(new Font("휴먼엑스포",Font.PLAIN, 20));
		helpBtn.setLocation(400, 680);
		add(helpBtn);
		
		
	}

}
