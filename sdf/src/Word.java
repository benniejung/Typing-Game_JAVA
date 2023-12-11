import javax.swing.JLabel;

public class Word {
	
	// 멤버 변수 선언
	private String name;
    private double x;
    private double y;
    private JLabel label;
    private double speed;
    private int ability; // 색상별 아이템
    
    // 생성자
    public Word(String word, int x, int y, double speed) {
    	
        this.name = word;
        this.x = x;
        this.y = y;
        
        label = new JLabel(word);
        this.speed = speed;
        
        this.ability = Math.random() < 0.7 ? 0 : -1; // 70% 검정색 단어 출력, 30% 다른 색상 단어 출력
	      if (ability == 0) {
		         ability = 0; // color: black
		      }
		      else if (ability == -1) {
		         double percent = Math.random();
		         // 3가지 능력
		         if (percent < 0.33) { // color: red
		            ability = 1;
		         } else if (percent > 0.33 && percent < 0.66) { // color: blue
		            ability = 2;
		         } else if (percent < 1 && percent > 0.66) { // color: pink
		            ability = 3;
		         }
		      }
        
        
    }

    public String getName() { return name; }

    public double getX() { return x; }

    public double getY() { return y; }

    public double getSpeed() { return speed; }

    public JLabel getLabel() { return label; }

    public void setY(double y) { this.y = y; }
    
    public int getAbility() {return ability;}
    
}