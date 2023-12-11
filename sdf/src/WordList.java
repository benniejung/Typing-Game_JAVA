import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class WordList {

	private Vector<String> wordVector = new Vector<String>();
	
	public WordList() {
		
		try {FileReader fin=new FileReader(GameManagement.pathName);
		BufferedReader bf=new BufferedReader(fin);
		String line="";
		while((line=bf.readLine())!=null) {
			wordVector.add(line);
		}}catch(IOException e) {System.out.println("파일이 없습니다.");};

	}
	
	public String getWord() { // wordVector 중 하나를 random하게 리턴
		
		int index = (int)(Math.random()*wordVector.size());
		return wordVector.get(index);
		
	}
}
