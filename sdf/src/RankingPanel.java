import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RankingPanel extends JPanel {

//	private final int RECT_WIDTH = 260;
//	private final int RECT_HEIGHT = 150;
//	
//	private Font defaultFont = new Font("맑은 고딕", Font.BOLD, 15); // 기본 폰트 설정
//	private LineBorder defaultLineBorder = new LineBorder(Color.WHITE, 5);
//	private String difficulty [] = { "Easy", "Normal", "Hard" };
//	private JLabel rankingTextLabel = new JLabel(" ★ Ranking ★ ");
//	private JLabel difficultyLabel [] = new JLabel[3];
//	private JLabel numberLabel [] = new JLabel[3];
//	private JLabel nameScoreLabel = null;
	
	private JLabel titleLabel = new JLabel("*** RANKING ***");
	private ImageIcon rankingBgIcon = new ImageIcon("image/background/랭킹배경.png");
	private Image rankingImg = rankingBgIcon.getImage();
	
	private ImageIcon sangjjiIcon = new ImageIcon("image/character/상찌_small.png");
	private JLabel sangjjiLabel = new JLabel(sangjjiIcon);

	// 생성자
	public RankingPanel() {
		this.setLayout(null); // 컴포넌트 배치 자유롭게
		
		// 랭킹 타이틀
		titleLabel.setBounds(370, 20, 250, 30);
		titleLabel.setForeground(new Color(151,196,69));
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(titleLabel);
		
		// 상찌
		sangjjiLabel.setBounds(800, 500, 250, 200);
		sangjjiLabel.setForeground(new Color(151,196,69));
		sangjjiLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(sangjjiLabel);

		
	}
	
	// 사각형 그리는 메소드
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(rankingImg, 0, 0, this.getWidth(), this.getHeight(), this);

	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setStroke(new BasicStroke(15));
	    Color borderColor = new Color(21, 40, 90);
	    g2d.setColor(borderColor);
	    g2d.drawRect(100, 100, 800, 600);
	}
	
	
//	private void makeBasicLabels() {
//		
//		// Ranking 텍스트
//		rankingTextLabel.setFont(new Font("Jokerman", Font.BOLD, 20));
//		rankingTextLabel.setForeground(Color.WHITE);
//		rankingTextLabel.setSize(220, 45);
//		rankingTextLabel.setLocation(140, 35);
//		rankingTextLabel.setOpaque(true);
//		rankingTextLabel.setBackground(new Color(157, 203, 189));
//		rankingTextLabel.setHorizontalAlignment(JLabel.CENTER);
//		rankingTextLabel.setBorder(defaultLineBorder);
//		add(rankingTextLabel);
//		
//		// 난이도 텍스트
//		for (int i = 0; i<difficultyLabel.length; i++) {
//			difficultyLabel[i] = new JLabel(difficulty[i]);
//			difficultyLabel[i].setFont(defaultFont);
//			difficultyLabel[i].setForeground(Color.WHITE);
//			difficultyLabel[i].setSize(65, 50);
//			difficultyLabel[i].setLocation(140, 115 + (180*i));
//			add(difficultyLabel[i]);
//		}
//		
//		// 숫자 텍스트
//		for (int i = 0; i<numberLabel.length; i++) {
//			for (int j = 0; j<numberLabel.length; j++) {
//			numberLabel[j] = new JLabel(Integer.toString(i+1));
//			numberLabel[j].setFont(defaultFont);
//			numberLabel[j].setForeground(Color.WHITE);
//			numberLabel[j].setSize(15, 50);
//			numberLabel[j].setLocation(215, 115 + (50*i) + (180*j));
//			add(numberLabel[j]);
//			}
//		}
//	}
//	
//	private void addNameScore() {
//		
//		nameScoreLabel = new JLabel(GameManagement.name + " | " + GameManagement.score);
//		nameScoreLabel.setFont(defaultFont);
//		nameScoreLabel.setForeground(Color.WHITE);
//		nameScoreLabel.setSize(100, 50);
//		
//		if (GameManagement.difficulty.equals("Easy"))
//			nameScoreLabel.setLocation(240, 115); // 140, 115 + (180*i)
//		else if (GameManagement.difficulty.equals("Normal"))
//			nameScoreLabel.setLocation(240, 295);
//		else if (GameManagement.difficulty.equals("Normal"))
//			nameScoreLabel.setLocation(240, 475);
//		
//		add(nameScoreLabel);
//		
//		}
	
}
