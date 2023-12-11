import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class StartPanel extends JPanel{
	// 시작화면 버튼들
	private JButton startBtn = new JButton(new ImageIcon("image/button/시작버튼.png"));
	private JButton rankingBtn = new JButton("랭킹보기");
	private JButton addWordBtn = new JButton("단어편집");
	private JButton helpBtn = new JButton("도움말");
	// 타이틀 
	private JLabel titleLabel1 = new JLabel("상찌를");
	private JLabel titleLabel2 = new JLabel("구해줘!");
	
	private ImageIcon startBgIcon = new ImageIcon("image/background/시작화면.png");
	private ImageIcon characIcon1 = new ImageIcon("images/상찌.png");

	
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
		titleLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 32));
		titleLabel1.setSize(400,100);
		titleLabel1.setLocation(100,220);
		add(titleLabel1);
		
		titleLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 50));
		titleLabel2.setSize(400,100);
		titleLabel2.setLocation(100,290);
		add(titleLabel2);
	
		// 시작하기버튼
		startBtn.setSize(200,110);
		startBtn.setBorderPainted(false); // 버튼의 외관선을 제거해준다
		startBtn.setContentAreaFilled(false); // 내용영역 채우지 않는다
		startBtn.setFocusPainted(false); // 테두리 선 제거해준다
		startBtn.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
		startBtn.setLocation(150, 400);
		add(startBtn);
		// 랭킹보기버튼
		rankingBtn.setSize(200,50);
		rankingBtn.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
		rankingBtn.setLocation(400, 560);
		add(rankingBtn);
		
		// 단어편집버튼
		addWordBtn.setSize(200,50);
		addWordBtn.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
		addWordBtn.setLocation(400, 620);
		add(addWordBtn);
		
		// 도움말
		helpBtn.setSize(200,50);
		helpBtn.setFont(new Font("맑은 고딕",Font.PLAIN, 20));
		helpBtn.setLocation(400, 680);
		add(helpBtn);
		
		startBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				LoginModal loginModal = new LoginModal(StartPanel.this.getStartFrame());
			}
			
		});
		
		
		
		
	}
    public StartFrame getStartFrame() {
        return (StartFrame) SwingUtilities.getWindowAncestor(this);
    }

	

}
