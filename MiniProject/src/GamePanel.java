import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel{
	// 상수
	private final int labelWidth = 80;
	private final int labelHeight = 50;
	
	
	
	private GameFrame gameFrame = null;
	private PauseAndBackPanel pauseAndBackPanel = null;
	private ProfileAndItemPanel profileAndItemPanel = null;
	private LifePanel lifePanel = null;
	private JPanel playPanel = null;
	private JPanel inputWordPanel = null;
	
	// 이미지
	private ImageIcon lifeIcon = new ImageIcon("생명.png");
	private ImageIcon gameBgIcon = new ImageIcon("images/gameBg3.jpg");
	
	Image lifeImg = lifeIcon.getImage();
	Image gameBgImg = gameBgIcon.getImage();
	
	// 레이블
	private JLabel lifeLabel = new JLabel("생명");
	private JLabel scoreLabel = new JLabel();
	private JLabel timeLabel;
	
	private JTextField inputField;
	
	// 스레드
	private GameThread gameThread = null;
	
	// 단어파일 저장 vector
	private Vector<String> wordList = new Vector<String>();
	// 단어파일 vector에서 랜덤으로 선택된 단어 저장 vector
	private Vector<Word> chosenWords = new Vector<Word>();
	
	
	// 생성자
	public GamePanel(GameFrame gameFrame, PauseAndBackPanel pauseAndBackPanel, ProfileAndItemPanel profileAndItemPanel) {
		this.gameFrame = gameFrame;
		this.pauseAndBackPanel = pauseAndBackPanel;
		this.profileAndItemPanel = profileAndItemPanel;
		
		
		setLayout(new BorderLayout()); // 게임화면 영역 나누기
		
		makeLifePanel();
		makeMainPlayPanel();
		makeInputWordPanel();
		
		saveWordToVector(); // 단어 벡터에 저장
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
	
	// 선택한 단어파일 벡터에 저장하는 메소드
	public void saveWordToVector() {
		try {FileReader fin=new FileReader(GameManagement.pathName);
		BufferedReader bf=new BufferedReader(fin);
		String line="";
		while((line=bf.readLine())!=null) {
			wordList.add(line);
		}}catch(IOException e) {System.out.println("파일이 없습니다.");};
	}
	// wordList에서 랜덤으로 단어 한 개 불러오기
	public String getWord()  {
		int r = (int)(Math.random()* wordList.size());
		return wordList.get(r);
	}
	// 선택된 단어 저장
	public void chooseWord() {
		int x = (int)(Math.random()*500);
		Word word = new Word(getWord(), x, 0);
		chosenWords.add(word);
		addLabel(word);
	}
	// 선택된 단어 레이블을 패널에 부착하는 메소드
	public void addLabel(Word word) {
		JLabel wordLabel = new JLabel(word.getWordStr());
		wordLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
		wordLabel.setSize(labelWidth,labelHeight);
		wordLabel.setLocation(word.getX(), 0);
		playPanel.add(wordLabel);
	}
	// 게임시작 메소드
	public void startGame() {
		gameThread = new GameThread();
		gameThread.start();
	}

	
}
