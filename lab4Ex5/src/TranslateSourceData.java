import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class is to create a data source for the translate
 * function in server side
 * @author WM TAN
 *
 */
public class TranslateSourceData {
	
	public static void main(String[] args) throws IOException {
		
		// Declare output file
		String outFile = "translateDatabase.txt";
		
		// Data declaration of multilingual text
		String[] multiText = {"Good morning","Selamat pagi","صباح الخير", "좋은 아침",
				"Good night","Selamat malam","مساء الخير","안녕히 주무세요",
				"How are you?","Apa khabar?","كيف حالك؟","어떻게 지내세요?",
				"Thank you","Terima kasih","شكرا لك","감사합니다","Goodbye",
				"Selamat tinggal","مع السلامة","안녕","What’s up?","Ada apa?",
				"ما أخبارك؟","뭐야?"};
		
		// Create stream to read data
		try {
			PrintWriter writeFile = new PrintWriter(new FileWriter(outFile));
			for (int i = 0; i<multiText.length;i++) {
				writeFile.write(multiText[i] +"\n");
				writeFile.flush();
			}
			
			// close the stream
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Indicate end of program - Could be successful
		System.out.println("End of program. Check out " + outFile); 
	}
}
