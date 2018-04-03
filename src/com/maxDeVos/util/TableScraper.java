package com.maxDeVos.util;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.cpjd.models.standard.Event;
import com.cpjd.models.standard.Match;
import com.maxDeVos.Main;

public class TableScraper {

	public static ArrayList<ArrayList<Integer>> scrapePicks(Event e) throws IOException {
		
		try {

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

		} catch (Exception z) {
			return null;
		}

	}

	static ArrayList<Integer> scrapeWinnerFromMatch(String matchID) throws IOException {

		ArrayList<Integer> returnArray = new ArrayList<Integer>();
		Match m = Main.tba.getMatch(matchID);
		String w = DataHandler.getMatchWinner(m);
		if (w.equals("red")) {
			for (String k : m.getRed().getTeamKeys()) {
				returnArray.add(Integer.parseInt(k.substring(3)));
			}
		} else if (w.equals("blue")) {
			for (String k : m.getBlue().getTeamKeys()) {
				returnArray.add(Integer.parseInt(k.substring(3)));
			}
		} else if (w.equals("tie")) {
			for (String k : m.getRed().getTeamKeys()) {
				returnArray.add(Integer.parseInt(k.substring(3)));
			}
			for (String k : m.getBlue().getTeamKeys()) {
				returnArray.add(Integer.parseInt(k.substring(3)));
			}
		}

		return returnArray;
	}

	static ArrayList<String> scrapeHighScoreFromTable(int tableId, Event e) throws IOException {

		Document doc = Jsoup.connect("https://www.thebluealliance.com/event/" + e.getKey() + "#event-insights").get();
		Element table = doc.getElementsByClass("table table-condensed table-striped text-right").get(tableId);
		Elements columns = table.select("tr");
		String[] x = columns.get(0).select("td").text().split(" ");
		ArrayList<String> returnArray = new ArrayList<String>();
		returnArray.add(x[2]);

		Elements a = table.select("a");
		for (Element l : a) {
			returnArray.add(l.attr("href"));
		}

		return returnArray;

	}

	public static int scrapeHighScore(int team, Event e) throws IOException {

		String highScoreMatch = "";
		ArrayList<String> score4 = new ArrayList<String>();
		score4 = scrapeHighScoreFromTable(4, e);
		ArrayList<String> score1 = new ArrayList<String>();
		score1 = scrapeHighScoreFromTable(1, e);

		if (Integer.parseInt(score4.get(0)) > Integer.parseInt(score1.get(0))) {
			highScoreMatch = score4.get(1).substring(7);
		} else if (Integer.parseInt(score4.get(0)) < Integer.parseInt(score1.get(0))) {
			highScoreMatch = score1.get(1).substring(7);
		}

		for (Integer a : scrapeWinnerFromMatch(highScoreMatch)) {
			if (a == team) {
				return 5;
			}
		}

		return 0;

	}
}
