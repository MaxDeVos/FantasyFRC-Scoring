package com.maxDeVos;

import java.util.ArrayList;

import com.cpjd.main.TBA;

public class Person {

	TBA tba;
	ArrayList<Integer> teams = new ArrayList<Integer>();
	
	public Person(TBA tba_) {
		tba = tba_;
	}
	
	public void setTeams(int a, int b, int c, int d, int e) {
		
		teams.add(a);
		teams.add(b);
		teams.add(c);
		teams.add(d);
		teams.add(e);
		
	}
	
	public void getTeamScore(int team) {
		teams.get(team + 1);
	}
	
	
	
}
