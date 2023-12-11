import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RankingFrame extends JFrame {
	private ProfileAndScorePanel profileAndScorePanel = null;
	private Container c = getContentPane();
	private RankingPanel rankingPanel;
	
	// 생성자
	public RankingFrame() {
		rankingPanel = new RankingPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 프레임 기본 설정
		setTitle("RANKING");
		setSize(1000,800);
		setResizable(false);
		setVisible(true);
		add(rankingPanel);
	}
	
	
}