package com.embl.ebi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * 
 * This class handles the post request from the REST client
 *
 */
public class Handler {

	public static class orderAccnHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange exchange) throws IOException {
			InputStreamReader inputReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			BufferedReader bufReader = new BufferedReader(inputReader);
			String unordered = bufReader.readLine();
			
			OrderAccnNumberImpl ebiAccNum = new OrderAccnNumberImpl();
			String outOrdered = ebiAccNum.orderAccn(unordered);

			int rCode = 200;
			exchange.sendResponseHeaders(rCode, outOrdered.length());
			OutputStream outStream = exchange.getResponseBody();
			outStream.write(outOrdered.toString().getBytes());
			outStream.close();		
		}
		
	}
}
