import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class StartFrame extends JFrame {
	
	private StartPanel startPanel = new StartPanel();	
	private Container c;
	public Audio audio = new Audio(); 

	// 처음 실행되는 것인지 확인하는 변수
	private static boolean isFirstExecute = true; 
	
	// 생성자
	public StartFrame() {
		
		if (isFirstExecute) { // 처음 실행 된다면 StartFrame 출력
			
			setTitle("Typing Game"); // Title 설정
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			c = getContentPane();
			c.add(startPanel);
				
			setSize(1000,800);
			setVisible(true); 
			setResizable(false); // 컨텐트팬 크기 조절 못하게
		
			audio.playAudio("startFrame");
		
			isFirstExecute = false;
			
		}
		
	} 
	
//	private void makeMenu() {
//		
//		JMenuBar menuBar = new JMenuBar();
//		this.setJMenuBar(menuBar);
//		
//		// 메뉴
//		JMenu fileMenu = new JMenu("File");
//		JMenu soundMenu = new JMenu("Sound");
//		
//		// 메뉴를 메뉴바에 부착
//		menuBar.add(fileMenu); 
//		menuBar.add(soundMenu);
//		
//		// 메뉴 아이템
//		JMenuItem wordsItem = new JMenuItem("words.txt");
//		JMenuItem toeicwordsItem = new JMenuItem("toeicwords.txt");
//		
//		JMenuItem soundOnItem = new JMenuItem("sound on");
//		JMenuItem soundOffItem = new JMenuItem("sound off");
//		
//		// 메뉴 아이템을 메뉴에 부착
//		fileMenu.add(wordsItem);
//		fileMenu.addSeparator();
//		fileMenu.add(toeicwordsItem);
//		soundMenu.add(soundOnItem);
//		soundMenu.addSeparator(); 
//		soundMenu.add(soundOffItem);
//
//		wordsItem.addActionListener(new ItemActionListener());
//		toeicwordsItem.addActionListener(new ItemActionListener());
//		soundOnItem.addActionListener(new ItemActionListener());
//		soundOffItem.addActionListener(new ItemActionListener());
//		
//	}
//	
//	class ItemActionListener implements ActionListener {
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			
//			String cmd = e.getActionCommand(); // 메뉴 아이템의 문자열 리턴
//			
//			switch(cmd) {
//			case "words.txt":
//				GameManagement.fileName = ("words.txt");
//				JOptionPane.showMessageDialog(c, GameManagement.fileName + " 선택됨",
//					"Message", JOptionPane.ERROR_MESSAGE);
//				break;
//			case "toeicwords.txt":
//				GameManagement.fileName = ("toeicwords.txt");
//				JOptionPane.showMessageDialog(c, GameManagement.fileName + " 선택됨",
//						"Message", JOptionPane.ERROR_MESSAGE);
//				break;
//			case "sound on":
//				audio.playAudio("startFrame");
//				JOptionPane.showMessageDialog(c, "배경음악 시작",
//						"Message", JOptionPane.ERROR_MESSAGE);
//				break;
//			case "sound off":
//				audio.stopAudio("startFrame");
//				JOptionPane.showMessageDialog(c, "배경음악 중지",
//						"Message", JOptionPane.ERROR_MESSAGE);
//				break;
//	
//			}
//		}
//		
//	}
	
//	private void makePanels() {
//		
//		startFrameNorthPanel = new StartFrameNorthPanel();
//		startFrameCenterPanel = new StartFrameCenterPanel(); 
//		startFrameSouthPanel = new StartFrameSouthPanel(this, audio);
//		
//		contentPane.add(startFrameNorthPanel, BorderLayout.NORTH);
//		contentPane.add(startFrameCenterPanel, BorderLayout.CENTER);
//		contentPane.add(startFrameSouthPanel, BorderLayout.SOUTH);
//		
//	}
	
}
