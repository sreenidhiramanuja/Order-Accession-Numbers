package com.embl.ebi;

public class OrderAccnNumber {
	/**
	 * main method, starts and stops the web server
	 */
	public static void main(String[] args) {
		EmbeddedWebServer webServer = new EmbeddedWebServer();
		webServer.Start();
		webServer.WaitToStop();
	}
}
