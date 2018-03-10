package com.maxDeVos;

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
		if(a.getName().contains("Chairman")) {
			return 42;
		}
		if(a.getName().contains("Regional Winner")) {
			return 10;
		}
		if(a.getName().contains("Regional Finalist")) {
			return 0;
		}
		if(a.getName().contains("Rookie All Star")) {
			return 20;
		}
		if(a.getName().contains("Rookie Inspiration")) {
			return 15;
		}
		if(a.getName().contains("Quality") || a.getName().contains("Control") || a.getName().contains("Excellence") || a.getName().contains("Creativity") ) {
			return 15;
		}
		if(a.getName().contains("Dean's")) {
			return 4;
		}
		if(a.getName().contains("Woodie")) {
			return 8;
		}
		if(a.getName().contains("Regional Engineering Inspiration")) {
			return 36;
		}
		return 2;
	}
	
}
