import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RankingPanel extends JPanel {
	// 타이틀, 서브 타이틀 레이블
	private JLabel titleLabel = new JLabel("*** RANKING ***");
	private JLabel username = new JLabel("USERNAME");
	private JLabel score = new JLabel("SCORE");
	private JLabel time = new JLabel("TIME");
	
	// 이미지
	private ImageIcon rankingBgIcon = new ImageIcon("image/background/랭킹배경.png");
	private Image rankingImg = rankingBgIcon.getImage();
	private ImageIcon sangjjiIcon = new ImageIcon("image/character/상찌.png");
	private JLabel sangjjiLabel = new JLabel(sangjjiIcon);

	// 생성자
	public RankingPanel() {
		this.setLayout(null); // 컴포넌트 배치 자유롭게
		
		// 랭킹 타이틀
		titleLabel.setBounds(370, 20, 250, 30);
		titleLabel.setForeground(new Color(151,196,69));
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(titleLabel);
		
		username.setBounds(340, 115, 200, 30);
		username.setForeground(new Color(151,196,69));
		username.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(username);
	
		score.setBounds(500, 115, 200, 30);
		score.setForeground(new Color(151,196,69));
		score.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(score);

		time.setBounds(600, 115, 200, 30);
		time.setForeground(new Color(151,196,69));
		time.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		add(time);

		
		// 상찌
		sangjjiLabel.setBounds(770, 520, 200, 210);
		sangjjiLabel.setForeground(new Color(151,196,69));
		sangjjiLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		add(sangjjiLabel);

        List<Record> records = readRecord("record/record.txt");

        if (records.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        Collections.sort(records, Comparator.reverseOrder());
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            JLabel usernameLabel = new JLabel(i+1 + ":       " +  record.getUsername());
            JLabel scoreLabel = new JLabel(Integer.toString(record.getScore()));
            JLabel minLabel = new JLabel(Integer.toString(record.getMinutes())+"분");
            JLabel secLael = new JLabel(Integer.toString(record.getSeconds())+"초");

            usernameLabel.setBounds(310, 200+i*60, 200, 30);
            usernameLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    		add(usernameLabel);

    		scoreLabel.setBounds(510, 200+i*60, 100, 30);
    		scoreLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    		add(scoreLabel);

    		minLabel.setBounds(590, 200+i*60, 100, 30);
    		minLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    		add(minLabel);

    		secLael.setBounds(630, 200+i*60, 100, 30);
    		secLael.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
    		add(secLael);

            
            System.out.printf("%d. %s - Score: %d, Time: %02d:%02d%n",
                    i + 1, record.getUsername(), record.getScore(), record.getMinutes(), record.getSeconds());
        }

	}
	
	// 사각형 그리는 메소드
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(rankingImg, 0, 0, this.getWidth(), this.getHeight(), this);

	    Graphics2D g2d = (Graphics2D) g;

	    g2d.setStroke(new BasicStroke(15)); // 테두리 굵기 설정
	    Color borderColor = new Color(21, 40, 90);
	    g2d.setColor(borderColor);
	    g2d.drawRect(100, 100, 800, 600);
	}
	private static List<Record> readRecord(String filename) {
		List<Record> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("/");
                if (parts.length == 4) {
                    String username = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    int minutes = Integer.parseInt(parts[2]);
                    int seconds = Integer.parseInt(parts[3]);
                    records.add(new Record(username, score, minutes, seconds));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return records;	
    }

	
}
class Record implements Comparable<Record> {
    private String username;
    private int score;
    private int min;
    private int sec;

    public Record(String username, int score, int min, int sec) {
        this.username = username;
        this.score = score;
        this.min = min;
        this.sec = sec;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    public int getMinutes() {
        return min;
    }

    public int getSeconds() {
        return sec;
    }

    @Override
    public int compareTo(Record other) {
        if (this.score != other.score) {
            return Integer.compare(this.score, other.score);
        } else if (this.min != other.min) {
            return Integer.compare(this.min, other.min);
        } else {
            return Integer.compare(this.sec, other.sec);
        }
    }
}