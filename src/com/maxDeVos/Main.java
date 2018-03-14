package com.maxDeVos;

import java.io.IOException;
import com.cpjd.main.TBA;

public class Main {

	public static int currentWeek = 1;
	public static int currentYear = 2018;
	public static TBA tba;

	public static void main(String args[]) throws IOException {

		TBA.setAuthToken("uJvxP1GDDT7O6Y0Ys7xrGhVUdntYErICAtz9uDc74LcpxPFz3vqcpMrHG94MuMPC");
		tba = new TBA();

		Person max = new Person(tba);
		max.setTeams(148,359,2834,610,2363);
		max.printDataSheets();

	}
}
