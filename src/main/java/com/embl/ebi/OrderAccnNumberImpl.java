package com.embl.ebi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 
 * This class sorts the entered Accession Numbers using Java 8 Stream APIs
 * Groups the accessions numbers if they are in the range and prints them
 *
 */
public class OrderAccnNumberImpl {
	private String prevAlpha;
	private String prevNum;
	private String currAlpha;
	private String currNum;
	private Boolean range = false;
	private String response;

	private ArrayList<String> input;

	public OrderAccnNumberImpl() {
		input = new ArrayList<String>();
	}
	
	// insert clean arg strings to input
	// Since parallel stream will not yield good performance for small collection its not utilized
	public void addStrings(String args) {
		// remove "," and spaces
		Stream<String> cleanArg = Stream.of(args.split(",[ ]*"));
		cleanArg.filter(s -> s.trim().matches("[a-zA-Z0-9]*"))
		.forEach(s -> input.add(s.trim()));
	}
	
	private void printAccnNumber(String acnStr) {
		if (range) {
			range = false;
			response = response.concat("-" + prevAlpha + prevNum);
			response = response.concat(", " + acnStr);
		} else {
			response = response.concat(", " + acnStr);
		}
		prevAlpha = currAlpha;
		prevNum = currNum;
	}

	public List<String> Parse(String str) {
		List<String> output = new ArrayList<String>();
		Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]+").matcher(str);
		while (match.find()) {
			output.add(match.group());
		}
		return output;
	}
	
	void doProcessing(String acnStr) {
		List<String> split = Parse(acnStr);
		
		// first entry
		if (prevAlpha == null) {
			// Include 1st element			
			response = acnStr;
			
			// get the alpha and numeric portion for the first sorting string			
			prevAlpha = split.get(0);
			prevNum = split.get(1);
			range = false;
		} else {
			currAlpha = split.get(0);
			currNum = split.get(1);

			// same alpha group?
			if (prevAlpha.compareTo(currAlpha) != 0) {
				printAccnNumber(acnStr);
				return;
			}

			// + same num length
			// Ex: 0090 and 00091 are not in same group
			if (prevNum.length() != currNum.length()) {
				printAccnNumber(acnStr);
				return;
			}

			// get the last digit
			Integer prevInt = Integer.parseInt(prevNum.substring(prevNum.length() - 1));
			Integer currInt = Integer.parseInt(currNum.substring(currNum.length() - 1));

			// + not consecutive number, its consecutive digits
			// Ex: Even 0099, 0100 are in different group
			if ( (prevInt + 1) != currInt) {
				printAccnNumber(acnStr);
				return;
			}
			
			// Both are in same group; dont print curr
			range = true;
			prevAlpha = currAlpha;
			prevNum = currNum;
		}
		
	}
	
	public void printOrderedList() {
		// sequential is mandated so that it can never be parallel
		input.stream().sequential().sorted()
		.forEach(acn -> doProcessing(acn));
	}
	

	public String orderAccn(String args) {
		addStrings(args);
		printOrderedList();
		return response;
	}

}
