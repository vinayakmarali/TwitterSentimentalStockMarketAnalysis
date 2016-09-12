package model;
/*
 * Authors: Vinayak Marali
 * 		    Chinmay Ladage
 * 		    Surabhi Marathe
 */

import java.util.HashMap;

public class WordPOJO {

	private HashMap<String, Integer> wordMap;
	private boolean flag;
	private String word;
	private int count;

	public WordPOJO(HashMap<String, Integer> wordMap, boolean flag, String word, int count) {
		super();
		this.wordMap = wordMap;
		this.flag = flag;
		this.word = word;
		this.count = count;
	}

	public WordPOJO() {

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public HashMap<String, Integer> getWordMap() {
		return wordMap;
	}

	public void setWordMap(HashMap<String, Integer> wordMap) {
		this.wordMap = wordMap;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
