import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{
	private GameFrame gameFrame = null;
	private PauseAndBackPanel pauseAndBackPanel = null;
	private ProfileAndItemPanel profileAndItemPanel = null;
	private LifePanel lifePanel = null;
	private JPanel playPanel = null;
	private JPanel inputWordPanel = null;
	
	// 이미지
	private ImageIcon lifeIcon = new ImageIcon("생명.png");
	private ImageIcon gameBgIcon = new ImageIcon("gameBg2.png");
	
	Image lifeImg = lifeIcon.getImage();
	Image gameBgImg = gameBgIcon.getImage();
	
	// 라벨
	private JLabel lifeLabel = new JLabel("생명");
	private JLabel scoreLabel = new JLabel();
	private JLabel timeLabel;
	
	private JTextField inputField;
	
	
	// 생성자
	public GamePanel(GameFrame gameFrame, PauseAndBackPanel pauseAndBackPanel, ProfileAndItemPanel profileAndItemPanel) {
		this.gameFrame = gameFrame;
		this.pauseAndBackPanel = pauseAndBackPanel;
		this.profileAndItemPanel = profileAndItemPanel;
		
		
		setLayout(new BorderLayout()); // 게임화면 영역 나누기
		
		makeLifePanel();
		makeMainPlayPanel();
		makeInputWordPanel();
	}
			
	// 생명 패널을 위쪽에 부착
	public void makeLifePanel() {
		lifePanel = new LifePanel();
//		lifeLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
//		lifeLabel.setLocation(300,10);
//		lifePanel.add(lifeLabel);
		lifePanel.setBackground(Color.yellow);
	    Dimension lifePanelSize = new Dimension(this.getWidth(), 60); // 생명패널 크기 설정
	    lifePanel.setPreferredSize(lifePanelSize);

		add(lifePanel, BorderLayout.NORTH);
		
	}
	// 게임 실행 패널을 중간에 부착
	public void makeMainPlayPanel() {
		playPanel = new MainPlayPanel();
		add(playPanel, BorderLayout.CENTER);
	}
	// 단어입력 패널을 아래쪽에 부착
	public void makeInputWordPanel() {
		inputWordPanel = new InputWordPanel();
		add(inputWordPanel, BorderLayout.SOUTH);
	}
	
	class LifePanel extends JPanel {
		// 생명 이미지 그리는 메소드
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(lifeImg, 600,10, 50, 50, this);
		}
		
		public LifePanel() {}

	}
	class MainPlayPanel extends JPanel {

		// 게임 배경 화면 그리는 메소드
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(gameBgImg, 0, 0, getWidth(), getHeight(), this);
		}

		// 생성자
		public MainPlayPanel() {}

	}
	class InputWordPanel extends JPanel {
		public InputWordPanel() {
			this.setBackground(Color.blue);
			inputField = new JTextField(30);
		    Dimension fieldSize = new Dimension(500, 35); // 입력창 크기 조절을 위한 설정
		    inputField.setPreferredSize(fieldSize);
			add(inputField);
			
		}
	}
	// 3초 카운트 세는 메소드
	public void startCountDown() {
		
	}
}
