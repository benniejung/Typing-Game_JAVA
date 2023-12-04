import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LifePanel extends JPanel{
	private ImageIcon lifeIcon = new ImageIcon("images/생명.png");
	private Image lifeImg = lifeIcon.getImage();
	
	// 생성자
	public LifePanel() {
		
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(lifeImg, 0, 0, this.getWidth(), this.getHeight(), this);
		g.setColor(Color.white);
		
	}
	
	

}
