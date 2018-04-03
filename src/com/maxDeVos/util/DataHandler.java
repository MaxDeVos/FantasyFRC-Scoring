package com.maxDeVos.util;

import com.cpjd.models.other.Award;
import com.cpjd.models.standard.Match;

public class DataHandler {

	public static String getMatchWinner(Match m) {
		
		//Match Not Yet Played
		if(m.getBlue() == null) {
			return "";
		}
		//Blue Win
		if(m.getBlue().getScore() > m.getRed().getScore()) {
			return "blue";
		}
		//Red Win
		if(m.getBlue().getScore() < m.getRed().getScore()) {
			return "red";
		}
		if(m.getBlue().getScore() < m.getRed().getScore()){
			return "tie";
		}
		return "error";
	}
	
	public static int awardToPoints(Award a) {
		
		if(a.getName().contains("Chairman's Award Finalist")) {
			return 50;
		}
		else if(a.getName().contains("Chairman")) {
			return 42;
		}
		else if(a.getName().contains("Rookie All Star")) {
			return 20;
		}
		else if(a.getName().contains("Rookie Inspiration")) {
			return 15;
		}
		else if(a.getName().contains("Quality") || a.getName().contains("Control") || a.getName().contains("Excellence") || a.getName().contains("Creativity") || a.getName().contains("Industrial")) {
			return 15;
		}
		else if(a.getName().contains("Dean")) {
			return 4;
		}
		else if(a.getName().contains("Woodie")) {
			return 8;
		}
		else if(a.getName().contains("Engineering Inspiration")) {
			return 36;
		}
		else if(a.getName().contains("Winner")) {
			return 10;
		}
		else if(a.getName().contains("Finalist")) {
			return 0;
		}
		return 2;
	}
	
}
