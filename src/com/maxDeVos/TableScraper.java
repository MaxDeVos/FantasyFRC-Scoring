package com.maxDeVos;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cpjd.models.standard.Event;

public class TableScraper {

	public static ArrayList<ArrayList<Integer>> scrapePicks(Event e) throws IOException {
		int i = 0;
		ArrayList<Integer> captains = new ArrayList<Integer>();
		ArrayList<Integer> picks = new ArrayList<Integer>();
		ArrayList<String> splitted = new ArrayList<String>();

		Document doc = Jsoup.connect("https://www.thebluealliance.com/event/" + e.getKey()).get();
		Element table = doc.getElementsByClass("table table-condensed table-striped table-center").first();
		Elements columns = table.select("tr");
		for (Element column : columns) {
			int badBot = -1;
			Elements cells = column.select("td");
			if (!cells.text().isEmpty()) {
				splitted.clear();
				for (String s : cells.text().split("\\s")) {
					splitted.add(s);
				}
				splitted.remove(0);
				splitted.remove(0);
				for (String s : splitted) {
					if (s.contains("(")) {
						badBot = splitted.indexOf(s) - 1;
						s = s.replace("(".toString(), "");
						s = s.replace(")".toString(), "");
						splitted.set(badBot++, s);
					}
				}
				if (badBot != -1) {
					splitted.remove(badBot);
				}
				captains.add(Integer.parseInt(splitted.get(0)));
				picks.add(i, Integer.parseInt(splitted.get(1)));
				picks.add(picks.size() - i, Integer.parseInt(splitted.get(2)));
				i++;
			}
		}

		ArrayList<ArrayList<Integer>> returnArray = new ArrayList<ArrayList<Integer>>();

		returnArray.add(captains);
		returnArray.add(picks);
		
		return returnArray;

	}
	
	static void scrapeHighScoreFromTable(int team, Event e) {
		
	}
	
	public static int scrapeHighScore(int team, Event e) throws IOException {
		
		int highScore;
		Document doc = Jsoup.connect("https://www.thebluealliance.com/event/" + e.getKey() + "#event-insights").get();
		Element table = doc.getElementsByClass("table table-condensed table-striped text-right").get(4);
		Elements columns = table.select("tr");
		String[] x = columns.get(0).select("td").text().split(" ");
		highScore = Integer.parseInt(x[2]);
		
		
		
		System.out.println(highScore);
		
		Elements a = table.select("a");
		for(Element l: a) {
			System.out.println(l.attr("href"));
		}
		
		return 0;

	}
}
