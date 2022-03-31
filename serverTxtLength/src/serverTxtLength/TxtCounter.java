package serverTxtLength;


/**
 * This class is used to calculate the lenght of the text.
 * 
 * by Tan Wei Ming
 *
 */

public class TxtCounter {
	
	/**
	 * 
	 * This is count the length of text
	 * 
	 */
	
	public int getTextLength(String txt){
		
		int textLength = txt.replace(" ", "").length();
		
		return textLength;
		
	}
}
