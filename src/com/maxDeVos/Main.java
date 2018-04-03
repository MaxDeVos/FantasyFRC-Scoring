package com.maxDeVos;

import java.io.IOException;
import com.cpjd.main.TBA;

public class Main {
	
	public static int currentWeek = 5;
	public static int currentYear = 2018;
	public static TBA tba;

	/*
1477,340,1311,3478,2590
	 */
	
	public static void main(String args[]) throws IOException {

		TBA.setAuthToken("uJvxP1GDDT7O6Y0Ys7xrGhVUdntYErICAtz9uDc74LcpxPFz3vqcpMrHG94MuMPC");
		tba = new TBA();

			Person bezek = new Person("Bill Jenkins");
			bezek.setTeams(1477,340,1311,3478,2590);
		
		bezek.printDataSheets();

	}
}
