package controller;

import java.awt.Desktop;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.opencsv.CSVReader;

public class InsertPolarity {

	public static void main(String[] args)
			throws IOException, ClassNotFoundException, SQLException, URISyntaxException {
		CSVReader csvReader = new CSVReader(new FileReader("NYDataset.csv"));
		ArrayList<String> tweetList = new ArrayList<String>();
		String[] row;
		System.out.println("Reading Tweets...");
		while ((row = csvReader.readNext()) != null) {
			tweetList.add(row[2]);
		}

		ArrayList<Integer> calculatedPolarity = new ArrayList<>();
		 for (int i = 0; i < tweetList.size(); i++) {
		 System.out.println(tweetList.get(i));
		 }
		/*
		 * converting all the characters to lowercase and using trim operation
		 * to remove any excess spacing
		 */
		PolarityCalc pc = new PolarityCalc();
		System.out.println("Loading subjectivity lexicon...");
		pc.loadWordListData();
		Word word = new Word();
		ArrayList<Integer> twtPolarity = new ArrayList<>();
		int overallPolarity = 0;
		System.out.println("Computing polarity for each tweet...");
		for (int i = 1; i < tweetList.size(); i++) {
			twtPolarity = word.wordCounter(tweetList.get(i).toLowerCase().trim());
		}
		System.out.println("Comupting Overall polarity...");
		for (int i = 0; i < twtPolarity.size(); i++) {
			overallPolarity = overallPolarity + twtPolarity.get(i);
		}
		if (overallPolarity > 0) {
			Desktop.getDesktop().browse(new URI("http://maralivinayak.wix.com/rstockpredictor"));
		} else {
			Desktop.getDesktop().browse(new URI("http://maralivinayak.wix.com/fstockpredictor"));
		}
	}
}
