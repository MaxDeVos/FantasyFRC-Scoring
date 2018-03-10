package com.maxDeVos;

import java.io.IOException;

import com.cpjd.main.TBA;

public class Main {
	
	public static int currentWeek = 1;
	public static int currentYear = 2018;
	
	public static void main(String args[]) throws IOException {

		TBA.setAuthToken("uJvxP1GDDT7O6Y0Ys7xrGhVUdntYErICAtz9uDc74LcpxPFz3vqcpMrHG94MuMPC");
		TBA tba = new TBA();
		Team team1 = new Team(1311, tba);
		//System.out.println(team1.totalScore);
		
	}
}
	
