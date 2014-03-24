/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.*;

public class HangmanLexicon {

	public HangmanLexicon() {
		
		/*adaptep from Eric Robert's java txtbook */
		lineList = new ArrayList<String>();
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				lineList.add(line);
			}
			rd.close();
		} catch (IOException ex){
			throw new ErrorException(ex);
		}
		String[] result = new String[lineList.size()];
		for (int i = 0; i< result.length; i++) {
			result[i] = lineList.get(i);
		}
	}

/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return lineList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		return lineList.get(index);
	}

	
	/* instance variables */
	private ArrayList<String> lineList;
}




