import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Vector;

public class GamePanel extends JPanel {
		
    private static int MAX_WORDS = 30;
    private final int LABEL_WIDTH = 150;
    private final int LABEL_HEIGHT = 40;

    private ImageIcon bgIcon1;
    private ImageIcon bgIcon2;
    private ImageIcon sangjjiIcon;
    private ImageIcon attackerIcon;
    
    private Font defaultFont = new Font("맑은 고딕", Font.BOLD, 15);
    private JTextField inputField = new JTextField(MAX_WORDS);
    private JLabel timeLabel = new JLabel("3");
    private JLabel sangjjiLabel;
    
    // 생성자에게 전달 위한 레퍼런스 선언
    private GameFrame gameFrame = null;
    private WordList wordList = null;
    private ProfileAndScorePanel profileAndScorePanel = null;
    private Audio audio = null;
    private PlayBottomField playBottomField = null;
    
    private JPanel timePanel;
	private JLabel minLabel = new JLabel("00");
	private JLabel secLabel = new JLabel("00");
	private JLabel divLabel = new JLabel(" : ");

    private MainPlayPanel MainPlayPanel = new MainPlayPanel();
    
    // 단어 저장을 위한 벡터 생성
    private Vector<Word> currentWords = new Vector<>(MAX_WORDS);
    
    // 스레드 실행을 위한 레퍼런스 선언
    private BeforeGameStartThread beforeGameStartThread = null;
    private GameTimerThread gameTimerThread = null;
    private GameThread gameThread = null;
    private CharacMoveThread characMoveThread = null;
    
    // 랭킹 확인 프레임을 생성을 위한 레퍼런스 선언
    private RankingFrame RankingFrame;
    
    // 점수 기록 파일
    private File file = new File("record/record.txt");	
	
    // 생성자
    public GamePanel(GameFrame gameFrame, WordList wordList, ProfileAndScorePanel profileAndScorePanel, Audio audio) {
        
    	this.gameFrame = gameFrame;
        this.wordList = wordList;
        this.profileAndScorePanel = profileAndScorePanel;
        this.audio = audio;

        setLayout(new BorderLayout());
        
        makeMainPlayPanel(); // 플레이 영역 만들기
        makeTimePanel(); // 시간 영역 만들기
        playBottomField = new PlayBottomField(this);
        add(playBottomField, BorderLayout.SOUTH);
        // makeInputPanel(); // 입력 영역 만들기
        addStartActionListener(); // 액션 리스너 등록
   
    }
    
    // 플레이 영역을 가운데에 부착
    private void makeMainPlayPanel() { add(MainPlayPanel, BorderLayout.CENTER); }
    
    // 시간 영역을 위쪽에 부착
    private void makeTimePanel() {
    	
    	timePanel = new JPanel();
        timePanel.setBackground(Color.white);
        Color borderColor = new Color(21,40,90);
        timePanel.setBorder(BorderFactory.createLineBorder(borderColor, 5));
        timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        timePanel.add(timeLabel);
        add(timePanel, BorderLayout.NORTH);
        
    }
    
    // 입력칸과 캐릭터패널을 게임화면 아래쪽에 부착
    public class PlayBottomField extends JPanel{
    	private GamePanel gamePanel;
    	private ImageIcon goatIcon = new ImageIcon("image/character/염소1.png");
    	private JLabel goatLabel = new JLabel(goatIcon); // 염소이미지 레이블을 만든다
    	private int x = 50,y = 0; 
    	private int setX = 0; // 현재 염소의 위치
    	
    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		bgIcon2 = new ImageIcon("image/background/bg2.png");
    		
    		g.drawImage(bgIcon2.getImage(), 0, 0, getWidth(), getHeight(), this);
    	}

    	public PlayBottomField(GamePanel gamePanel) {
    		this.gamePanel = gamePanel;
    		this.setLayout(null);
	       	Color backgroundColor = new Color(221, 176, 141);
	        this.setBackground(backgroundColor);
	 	    Dimension playBottomPanelSize = new Dimension(this.getWidth(), 170); // 플레이 패널 하단패널 사이즈 설정
	 	    this.setPreferredSize(playBottomPanelSize);
	 	    // 상찌 레이블
		    sangjjiIcon = new ImageIcon("image/character/상찌_theSmall.png");
		    sangjjiLabel = new JLabel(sangjjiIcon);
		    sangjjiLabel.setSize(80,80);
		    sangjjiLabel.setLocation(580, 0);
		    this.add(sangjjiLabel);
		    // 입력필드
		    inputField.setSize(230, 30);
		    inputField.setLocation(250, 100);
	        this.add(inputField);
	        // 염소 레이블
    		goatLabel.setSize(80,80);
    		goatLabel.setLocation(0,y);
    		this.add(goatLabel);

    	}
    	// 염소 움직이게 하는 메소드
    	public void moveGoat() {
    		goatLabel.setSize(80,80);
    		goatLabel.setLocation(setX+x,y);
    		setX +=x;
    		this.add(goatLabel);
    	}
    	public int getGoatLocation() {
    		return setX;
    	}
    	public void setGoatLocation() {
    		setX = 50;
    		goatLabel.setLocation(setX, y);
    		this.add(goatLabel);
    	}
    }
    // 액션 리스너 등록
    private void addStartActionListener() {
    	
    	inputField.addActionListener(new ActionListener() {
    		
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField) e.getSource(); // 입력받은 것을 전달받는다
                boolean isCorrect = false;
                // 비교 시작
                for (Word word : currentWords) {
                    if (textField.getText().equals(word.getName())) { // 단어가 일치한다면
                    	isCorrect = true;
                    	if(word.getAbility() == 1) { // 빨간색 단어를 입력하면 
                    		if(GameManagement.life <5) {
                    			profileAndScorePanel.lifeIncrease(); // 생명이 5미만일 떄만 생명을 증가시킨다	
                    		}
                    	}
                    	else if(word.getAbility() == 2) { // 파란색 단어를 입력하면
                    		playBottomField.setGoatLocation(); // 염소를 처음 위치로 다시 옮긴다
                    	}
                    	else if(word.getAbility() == 3) { // 핑크색 단어를 입력하면
                    		profileAndScorePanel.scoreIncrease(); // 점수를 증가시킨다
                    	}

                    	profileAndScorePanel.scoreIncrease(); // 점수 증가
                        word.setY(MainPlayPanel.getHeight()); // 단어를 아래에 배치
                        profileAndScorePanel.setNormalProfileImage(); // 표정 바꾸기
                        Audio playcorrect = new Audio(); // 효과음을 나타내기 위해 오디오 객체 생성
                        playcorrect.playAudio("correct"); // 효과음 발생
                        break;
                    }
                }
                if (!isCorrect) { // 단어가 일치하지 않는다면
                	profileAndScorePanel.scoreDecrease(); // 점수 감소
                	profileAndScorePanel.setSadProfileImage(); // 표정 바꾸기
                	Audio playwrong = new Audio(); // 효과음을 나타내기 위해 오디오 객체 생성
                	playwrong.playAudio("wrong"); // 효과음 발생
                	
                } 
                textField.setText(""); // 텍스트 필드 초기화
            }
        });
    	
    }
   
    // 3초 카운트 다운 메소드
    public void startCountDown() {
    	if (beforeGameStartThread == null) {
    		beforeGameStartThread = new BeforeGameStartThread(this, timeLabel, audio);
    		beforeGameStartThread.start();
    	}
    }
    
    // 게임 시작 메소드
    public void startGame() {
        if (gameThread == null) {
        	gameThread = new GameThread(this);
        	characMoveThread = new CharacMoveThread(this, MainPlayPanel);
        	gameThread.start();
        	characMoveThread.start();
        }
    }
	// 타이머 그리는 메소드
	public void timerUI() {
		timePanel.remove(timeLabel);
		minLabel.setBounds(200, 10, 300, 30); // 분
		minLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		
		divLabel.setBounds(250, 10, 300, 30); // :
		divLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		
		secLabel.setBounds(300, 10, 300, 30); // 초
		secLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 30));

        timePanel.add(minLabel);
        timePanel.add(divLabel);
        timePanel.add(secLabel);
        
        repaint();
	}
    
    // 게임 타이머 시작 메소드
    public void startGameTimerThread() {
    	if (gameTimerThread == null) {
    		gameTimerThread = new GameTimerThread(this, profileAndScorePanel,timePanel, timeLabel, minLabel, divLabel,secLabel, audio);
    		gameTimerThread.start();
    	}
    }
    
    // 게임 종료 메소드
    public void gameEnd() {
    	
		if (gameThread != null) {
    		gameThread.interrupt(); // 게임 스레드 종료
    		this.removeAll(); // 화면 정지
    		audio.closeAudio("gameBackground"); 
    		//JOptionPane.showMessageDialog(gameFrame, "Game Over", "Game Over", JOptionPane.ERROR_MESSAGE); // 팝업 띄우기
    		GameResultModal gameResultModal = new GameResultModal(minLabel.getText(), secLabel.getText());
    		//gameFrame.dispose(); // gameFrame 종료
    		GameManagement.score = profileAndScorePanel.getScore();
    		recordScore(); // 이름, 점수, 시간 기록
    		RankingFrame = new RankingFrame(profileAndScorePanel);// 랭킹 확인 프레임 띄우기
    		audio.playAudio("gameEnded"); // 게임 종료 배경 음악 시작
		}
		
	}
    // 점수 기록 메소드
	private void recordScore() {
		try {
			//파일에 문자열을 쓴다
			FileWriter fw = new FileWriter(file, true);
			fw.write(GameManagement.name +"/"+ Integer.toString(GameManagement.score) + "/" + minLabel.getText() + "/" + secLabel.getText() + "/" + "\r\n");
            fw.close();
			System.out.println("점수 저장 완료.");
		} catch (Exception e) {
			System.out.println("파일 오퓨 ");
		}
	}


    // 게임 중지 메소드
    public void stopGame() {
    	
    	if(gameThread.getStopFlag() == false) {
    		gameThread.stopGame();
    		inputField.setEditable(false);
    	}
    	if(gameTimerThread.getStopFlag() == false) {
    		gameTimerThread.stopTimer();
    	}
    	
    }
    
    // 게임 재개 메소드
    public void resumeGame() {
    	
    	if(gameThread.getStopFlag() == true) {
    		gameThread.resumeGame();
    		inputField.setEditable(true);
    	}
    	if(gameTimerThread.getStopFlag() == true) {
    		gameTimerThread.resumeTimer();
    	}
    	
    }
    
    // 단어 추가 메소드
    public void addWord() {
        int x = (int) (Math.random() * (MainPlayPanel.getWidth() - LABEL_WIDTH / 2));
        Word word = new Word(wordList.getWord(), x, 0, Math.random() / 10+ 0.1);
        currentWords.add(word);
        addLabel(word);
    }

    // 레이블을 패널에 부착하는 메소드
    private void addLabel(Word word) {
    
        JLabel label = word.getLabel();
        label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        label.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        label.setLocation(getX(), 0);
        // 색상 단어 생성
        if(word.getAbility() == 0) {
        	label.setForeground(Color.black);
        }
        else if(word.getAbility() == 1) {
        	label.setForeground(Color.red);
        }
        else if(word.getAbility() == 2) {
        	label.setForeground(Color.blue);
        }
        else if(word.getAbility() == 3) {
        	label.setForeground(Color.pink);
        }
        MainPlayPanel.add(label);
        
    }

    // 단어 세팅 메소드
    public void setWords() {
    	
        Iterator<Word> iterator = currentWords.iterator(); // currentWords의 요소를 순차 검색할 Iterator 객체 리턴
        while (iterator.hasNext()) { // 방문할 요소가 남아있다면
            Word word = iterator.next(); // iterator가 가리키는 요소 리턴
            word.setY(word.getY() + word.getSpeed()); // Y 좌표 설정
            JLabel label = word.getLabel(); // 단어 받아오기
            label.setLocation((int) (word.getX()), (int) word.getY()); // 레이블 위치 지정
            if (label.getY() >= MainPlayPanel.getHeight()) { // 영역을 벗어나면
            	playBottomField.moveGoat();
            	if(playBottomField.getGoatLocation() > 570) {
            		playBottomField.setGoatLocation();
            		profileAndScorePanel.lifeDecrease();
            	}
                MainPlayPanel.remove(label); // 패널에서 레이블 제거
                iterator.remove(); // iterator에서 제거
            }
        }
        
    }
    
    public class CharacMoveThread extends Thread{
    	private JLabel sangjjiLabel;
    	private GamePanel gamePanel;
    	private MainPlayPanel mainPlayPanel;
    	public CharacMoveThread(GamePanel gamePanel, MainPlayPanel mainPlayPanel) {
    		
    	}
    	public void run() {
    		
    	}
    }

    class MainPlayPanel extends JPanel {
    	// 게임 배경 화면 그리는 메소드
    	@Override
    	public void paintComponent(Graphics g) {
    		super.paintComponent(g);
    		bgIcon1 = new ImageIcon("image/background/bg.png");
    		
    		g.drawImage(bgIcon1.getImage(), 0, 0, getWidth(), getHeight(), this);
    	}
    	
    	// 생성자
        public MainPlayPanel() { 
        }
               
    }

}