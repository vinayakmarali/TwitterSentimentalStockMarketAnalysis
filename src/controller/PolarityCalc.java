/*
 * Authors: Vinayak Marali
 * 		    Chinmay Ladage
 * 		    Surabhi Marathe
 */

package controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * This class is used to calculate the polarities of each tweet with the help of
 * the words.polarity table
 *
 */
public class PolarityCalc {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	static int tweetPolarity = 0;
	static ArrayList<Integer> polarityList = new ArrayList<>();
	static HashMap<String, String> subjectivityList = new HashMap<>();
	static int count = 0;


	/**
	 * This class reads the word from words.polarity table and it passes the
	 * result set to calcPolarity function
	 */

	public void loadWordListData() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/words?user=root&password=&useSSL=false");
		ps = conn.prepareStatement("select * from words.polarity;");
		rs = ps.executeQuery();
		subjectivityList = new HashMap<>();
		
		while (rs.next()) {
			subjectivityList.put(rs.getString("word"), rs.getString("polarity") + rs.getString("type"));
		}
		conn.close();
	}

	private void readWord(String tweetWord) throws SQLException {
		if (subjectivityList.containsKey(tweetWord)) {
			calcPolarity(subjectivityList.get(tweetWord));
		}
	}

	/**
	 * This class calculates the polarity based on a decision tree. If the word
	 * is strongly positive then it carries 10 points, if it is weakly positive,
	 * then it carries 5 points, if it is neutral it carries 0 points, if it is
	 * weakly negative then it carries -5 points and if it is strongly negative
	 * then it carries -10 points
	 */
	public void calcPolarity(String polarity) throws SQLException {
		if (polarity.equals("positivestrongsubj")) {
			tweetPolarity = tweetPolarity + 10;
		} else if (polarity.equals("positiveweaksubj")) {
			tweetPolarity = tweetPolarity + 5;
		} else if (polarity.equals("negativestrongsubj")) {
			tweetPolarity = tweetPolarity - 10;
		} else if (polarity.equals("negativeweaksubj")) {
			tweetPolarity = tweetPolarity - 5;
		}
	}

	/**
	 * This function uses the hashmap and iterates through them inorder to
	 * compute their polarities
	 */
	public ArrayList<Integer> iterateTweets(HashMap<String, Integer> hashMap)
			throws ClassNotFoundException, SQLException {
		String tweet;
		int wordCount;
		PolarityCalc pc = new PolarityCalc();
		tweetPolarity = 0;
		Iterator iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry kv = (Entry) iterator.next();
			tweet = (String) kv.getKey();
			wordCount = (int) kv.getValue();
			if (wordCount > 1) {
				while (wordCount != 0) {
					pc.readWord(tweet);
					wordCount--;
				}
			} else {
				pc.readWord(tweet);
			}

		}
		count++;
		polarityList.add(tweetPolarity);
		return polarityList;
	}

}
