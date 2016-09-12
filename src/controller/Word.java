package controller;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 * Authors: Vinayak Marali
 * 		    Chinmay Ladage
 * 		    Surabhi Marathe
 */

import java.util.HashMap;
import java.util.Scanner;

import model.WordPOJO;

/**
 * This program is used to display a list of words and their respective
 * occurrences in a string
 *
 */
public class Word {

	/**
	 * This method iterates through the string and extracts words from it. It
	 * maintains a hash map of words and their respective counts
	 * 
	 * @param sentence
	 *            input string entered by user
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Integer> wordCounter(String sentence) throws ClassNotFoundException, SQLException {

		WordPOJO pojo = new WordPOJO(new HashMap<String, Integer>(), false, new String(), 0);

		for (int i = 0; i < sentence.length(); i++) {

			/*
			 * iterating through the string and checking for a blank space
			 * character while ignoring non-alphabet characters
			 */
			while (i < sentence.length() && sentence.charAt(i) != ' ') {
				if ((int) sentence.charAt(i) >= 97 && (int) sentence.charAt(i) <= 122) {
					// appending characters to a string to form a word
					pojo.setWord(pojo.getWord() + sentence.charAt(i));
				}
				i++;
			}

			for (int j = 0; j < pojo.getWordMap().size(); j++) {

				/*
				 * checking whether an entry for the word already exists in the
				 * hash map. If yes, then the count variable is incremented
				 */

				if (pojo.getWordMap().get(pojo.getWord()) != null) {
					pojo.setCount(pojo.getWordMap().get(pojo.getWord()));
					pojo.setCount(pojo.getCount() + 1);
					pojo.getWordMap().put(pojo.getWord(), pojo.getCount());
					pojo.getWordMap().put(pojo.getWord(), pojo.getCount());
					pojo.setFlag(true);
					break;
				}
			}
			// inserting word for the first time in the hash map
			if (!pojo.isFlag()) {
				pojo.getWordMap().put(pojo.getWord(), 1);
			}
			pojo.setWord("");
			pojo.setFlag(false);
		}

		System.out.println(pojo.getWordMap());
		PolarityCalc pc = new PolarityCalc();
		ArrayList<Integer> twtPolarity = new ArrayList<>();
		twtPolarity = pc.iterateTweets(pojo.getWordMap());
		return twtPolarity;
	}

	/**
	 * Taking input string from user and passing it to the function f
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter tweet");
		/*
		 * converting all the characters to lowercase and using trim operation
		 * to remove any excess spacing
		 */
		Word word = new Word();
		word.wordCounter(userInput.nextLine().toLowerCase().trim());
		// userInput.close();

	}

}
