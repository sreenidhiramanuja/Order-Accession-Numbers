package com.embl.ebi;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

/**
 * 
 * This class creates and closes the webserver
 *
 */
public class EmbeddedWebServer {
	private HttpServer server;
	private int port;
	
	public EmbeddedWebServer() {
		port = 8000;
	}
	
	public void Start() {
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("Embedded Web Server started. Port number: " + port);
			System.out.println("Click enter to stop the Web Server");
			server.createContext("/OrderAccn", new Handler.orderAccnHandler());
			server.setExecutor(null);
			server.start();				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void WaitToStop() {
		try {
			System.in.read();
			server.stop(0);
			System.out.println("Embedded Web Server stopped.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
