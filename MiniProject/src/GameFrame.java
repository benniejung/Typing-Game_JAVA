import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class GameFrame extends JFrame {
	private Container c;
	private PauseAndBackPanel pauseAndBackPanel = new PauseAndBackPanel();
	private ProfileAndItemPanel profileAndItemPanel = new ProfileAndItemPanel(); 
	private GamePanel gamePanel = new GamePanel(this, pauseAndBackPanel, profileAndItemPanel);
	
	public GameFrame() {
		setTitle("게임이름!!"); // !! 나중에 변경해야함 !!
		setSize(1000,800);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 컨텐트팬 크기 조절 못하게
		
		c = getContentPane();
		
		splitPane();        
        
		gamePanel.startCountDown();
	}
	
	// 스플릿팬 생성 함수(영역 나누기)
	private void splitPane() {
		JSplitPane hPane = new JSplitPane();
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT); // 수평으로 분할
		hPane.setDividerLocation(700); // 디바이더 초기 위치 설정
		hPane.setEnabled(false); // 나눠진 패널들의 크기를 고정
		hPane.setDividerSize(0); // 스플릿팬 선 안보이게
		c.add(hPane, BorderLayout.CENTER);
		
		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT); // 수직으로 분할
		vPane.setDividerLocation(600);
		vPane.setEnabled(false); // 나눠진 패널들의 크기를 고정
		vPane.setDividerSize(0); // 스플릿팬 선 안보이게
		hPane.setLeftComponent(gamePanel);
		hPane.setRightComponent(vPane);
		
		vPane.setTopComponent(profileAndItemPanel);
		vPane.setBottomComponent(pauseAndBackPanel);
				
		
		
	}
	
}