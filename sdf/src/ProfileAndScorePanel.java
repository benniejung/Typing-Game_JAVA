import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ProfileAndScorePanel extends JPanel {

	private Font defaultFont = new Font("맑은 고딕", Font.BOLD, 30); 

	public static int score = 0;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel lifeLabel;
	
	private ImageIcon profileIcon; 
	private JLabel profileLabel;
	
	private ImageIcon normalProfileImage; // 기본이미지
	private ImageIcon wrongProfileImage; // 단어를 잘못입력했을 때 이미지
	
	// 생성자
	public ProfileAndScorePanel() {
		
		setOpaque(true); 
		setBackground(Color.white);
		
		setLayout(null);
        Color borderColor = new Color(21,40,90);
        this.setBorder(BorderFactory.createLineBorder(borderColor, 5));

		makeProfileLabel(); // 프로필 레이블 부착
		makeScoreLabel(); // 점수 레이블 부착
		makeLifeLabel(); // 생명 레이블 부착
	}
		
	public void makeProfileLabel() {
		
		profileIcon = new ImageIcon("image/profile/부기얼굴.png");

		this.normalProfileImage = profileIcon;
		this.wrongProfileImage = new ImageIcon("image/profile/부기_X.png");
		profileLabel = new JLabel(profileIcon);
		profileLabel.setSize(200, 200);
		profileLabel.setLocation(45, 300);
		this.add(profileLabel);
		
	}
	
	public void makeScoreLabel() {
		
		JLabel scoreLabelText = new JLabel("Score");
		scoreLabelText.setFont(defaultFont);
		scoreLabelText.setSize(100, 30);
		scoreLabelText.setLocation(100 ,20);
		scoreLabelText.setHorizontalAlignment(JLabel.CENTER);
		this.add(scoreLabelText);
		
		scoreLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		scoreLabel.setSize(100, 30);
		scoreLabel.setLocation(100 ,60);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);

		this.add(scoreLabel);
		
	}
	public void makeLifeLabel() {
		JLabel lifeLabelText = new JLabel("Life");
		lifeLabelText.setFont(defaultFont);
		lifeLabelText.setSize(100, 30);
		lifeLabelText.setLocation(100 ,110);
		lifeLabelText.setHorizontalAlignment(JLabel.CENTER);
		this.add(lifeLabelText);
		
		ImageIcon lifeIcon = new ImageIcon("image/character/상찌얼굴.png");
		JLabel lifeImageLabel = new JLabel(lifeIcon);
		lifeImageLabel.setSize(100, 50);
		lifeImageLabel.setLocation(70 ,150);
		lifeImageLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(lifeImageLabel);
		
		JLabel xLabel = new JLabel("X");
		xLabel.setFont(defaultFont);
		xLabel.setSize(100, 30);
		xLabel.setLocation(120 ,160);
		xLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(xLabel);

		lifeLabel = new JLabel(Integer.toString(GameManagement.life));
		lifeLabel.setFont(defaultFont);
		lifeLabel.setSize(100, 30);
		lifeLabel.setLocation(150 ,160);
		lifeLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(lifeLabel);
		
	}
	// 단어가 일치할 때 호출되는 메소드
	public void setNormalProfileImage() { profileLabel.setIcon(normalProfileImage); }
	
	// 단어가 일치하지 않을 때 호출되는 메소드
	public void setSadProfileImage() { profileLabel.setIcon(wrongProfileImage); }
	
	// 점수 증가 메소드
	public void scoreIncrease() { 
		if (GameManagement.difficulty.equals("Normal")) {
			score += 15;
			scoreLabel.setText(Integer.toString(getScore())); 
		}
		
	}
	
	// 점수 감소 메소드
	public void scoreDecrease() { 
		if (GameManagement.difficulty.equals("Normal")) {
			score -= 10;
			scoreLabel.setText(Integer.toString(getScore())); 
		}
		
	}
	// 생명 감소 메소드
	public void lifeDecrease() {
		GameManagement.life-=1;
		lifeLabel.setText(Integer.toString(getLife()));
		
	}
	// 생명 증가 메소드
	public void lifeIncrease() {
		GameManagement.life++;
		lifeLabel.setText(Integer.toString(getLife()));

	}
	
	// 생명 리턴 메소드
	public int getLife() {return GameManagement.life;}
	// 점수 리턴 메소드
	public int getScore() { return score; }
	
}