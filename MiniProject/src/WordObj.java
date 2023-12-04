import javax.swing.JLabel;

public class WordObj {
	private String word;
	private int x, y;
	private JLabel label;
	
	// 생성자
	public WordObj(String word, int x, int y) {
		this.word = word;
		this.x = x;
		this.y = y;
		
		label = new JLabel(word);
	}
	public JLabel getLabel() {
		return label;
	}
	public String getWordStr() {
		return word;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setY(int n) {
		y +=n;
		
	}
	
}
