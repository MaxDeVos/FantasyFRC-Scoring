package com.maxDeVos;

import java.io.IOException;
import java.util.ArrayList;

import com.cpjd.main.TBA;

public class Person {

	TBA tba;
	ArrayList<Integer> teams = new ArrayList<Integer>();
	String teamName;
	
	public Person(String name) {
		teamName = name;
		tba = Main.tba;
	}
	
	public void setTeams(int a, int b, int c, int d, int e) {
		
		teams.add(a);
		teams.add(b);
		teams.add(c);
		teams.add(d);
		teams.add(e);
		
	}
	
	public int getTeamScore(int team) throws IOException {
		Team t = new Team(teams.get(team - 1));
		return t.totalScore;
	}
	
	public void printDataSheets() throws IOException {
		System.out.println("Total Score: " + getTotalScore());
		System.out.println("");
		for(Integer a:teams) {
			Team t = new Team(a);
			System.out.println("");
			System.out.println("Team: " + t.number);
			t.printDataSheet();
		}
		
	
	}
	
	public int getTotalScore() throws IOException {
		int score = 0;
		for(Integer a:teams) {
			Team t = new Team(a);
			score += t.totalScore;
		}
		return score;
	}
	
	
	
}
