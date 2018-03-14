package com.maxDeVos;

import java.io.IOException;
import java.util.ArrayList;

import com.cpjd.main.TBA;
import com.cpjd.models.other.Award;
import com.cpjd.models.standard.Event;
import com.cpjd.models.standard.Match;

public class Team {

	int number;
	TBA tba;
	Event event;
	int wonMatches;
	int tiedMatches;
	int playedMatches;
	double winPercent;
	int allWonMatches;
	int allTiedMatches;
	int allPlayedMatches;
	double allWinPercent;
	int compPlacement;
	int compPlacementScore;
	int awardScore;
	int matchScore;
	int allMatchScore;
	int totalScore;
	int highScorePoints;
	ArrayList<Integer> captains = new ArrayList<Integer>();
	ArrayList<Integer> picks = new ArrayList<Integer>();
	int rankPickScore = 0;

	public Team(int number_, TBA tba_) throws IOException {

		try {
			number = number_;
			tba = tba_;
			getWeekEvent();
			getAllMatchScore();
			getQualMatchScore();
			getPlacementScore();
			getAwardScore();
			getRankPickScore();
			getHighScorePoints();
			getTotalScore();
		}
		catch(Exception e) {
		}
	}

	void printDataSheet() {
		System.out.println("Qual Match Score: "  + matchScore);
		System.out.println("Placement Score: "  + compPlacementScore);
		System.out.println("Award Score: " + awardScore);
		System.out.println("Rank-Pick Score: " + rankPickScore);
		System.out.println("High Score Points: " + highScorePoints);
		System.out.println("Total Points: " + totalScore);
	}
	
	void getWeekEvent() {
		for (Event e : tba.getEvents(number, Main.currentYear)) {
			if (e.getWeek() == Main.currentWeek - 1) {
				if(!e.getName().contains("Week 0")) {
					event = e;
				}
			}
		}
	}

	String getTeamColor(Match m) {
		for (String t : m.getBlue().getTeamKeys()) {
			// Get Alliance Color
			if (t.equals("frc" + number)) {
				return "blue";
			}
		}
		return "red";
	}

	void getPlacementScore() {
		if (compPlacement == 1) {
			compPlacementScore = 4;
		} else if (compPlacement == 2) {
			compPlacementScore = 10;
		} else if (compPlacement == 3) {
			compPlacementScore = 20;
		}

	}

	void getAllMatchScore() {

		int score;
		String alliance = "";
		String winner = "";

		for (Match m : tba.getTeamEventMatches(number, event.getKey())) {

			alliance = getTeamColor(m);

			// Get Match Winner
			winner = DataHandler.getMatchWinner(m);
			// Determine Points
			if (!winner.isEmpty()) {
				allPlayedMatches++;
			}
			if (winner == alliance) {
				allWonMatches++;
			} else if (winner == "tie") {
				allTiedMatches++;
			}

			if (m.getCompLevel().equals("f")) {
				compPlacement = 3;
			} else if (m.getCompLevel().equals("sf")) {

				if (compPlacement < 2) {
					compPlacement = 2;
				}

			} else if (m.getCompLevel().equals("qf")) {

				if (compPlacement < 1) {
					compPlacement = 1;
				}

			} else {

				if (compPlacement < 0) {
					compPlacement = 0;
				}

			}

		}

		score = (allWonMatches * 2) + (allTiedMatches);
		allWinPercent = (double) allWonMatches / (double) allPlayedMatches;
		allMatchScore = score;
	}

	void getQualMatchScore() {

		int score;
		String alliance = "";
		String winner = "";

		for (Match m : tba.getTeamEventMatches(number, event.getKey())) {
			if (m.getCompLevel().contains("qm")) {
				alliance = getTeamColor(m);

				// Get Match Winner
				winner = DataHandler.getMatchWinner(m);
				// Determine Points
				if (!winner.isEmpty()) {
					playedMatches++;
				}
				if (winner == alliance) {
					wonMatches++;
				} else if (winner == "tie") {
					tiedMatches++;
				}
			}
		}

		score = (wonMatches * 2) + (tiedMatches);
		winPercent = (double) wonMatches / (double) playedMatches;
		matchScore = score;
	}

	void getAwardScore() {

		int score = 0;

		for (Award a : tba.getTeamEventAwards(number, event.getKey())) {
			score += DataHandler.awardToPoints(a);
		}

		awardScore = score;

	}

	void getHighScorePoints() throws IOException {
		highScorePoints = TableScraper.scrapeHighScore(number, event);
	}

	void getRankPickScore() throws IOException {
		try {
			ArrayList<ArrayList<Integer>> table = TableScraper.scrapePicks(event);
			captains = table.get(0);
			picks = table.get(1);
			for (int t : captains) {
				if (t == number) {
					rankPickScore = 16 - captains.indexOf(t);
					break;
				}
			}
			if (rankPickScore == 0) {
				for (int t : picks) {
					if (t == number) {
						rankPickScore = 16 - picks.indexOf(t);
						break;
					}
				}
			}
		} catch (Exception e) {
			rankPickScore = 0;
		}
	}

	void getTotalScore() {
		totalScore = awardScore + matchScore + rankPickScore + compPlacementScore + highScorePoints;
	}

}