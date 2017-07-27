package com.embl.ebi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * This class sort the entered Accession Numbers.
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
	public void addStrings(String args){
		// remove "," and spaces
		String[] cleanArg = args.split(",[ ]*");
		for (int i = 0; i < cleanArg.length; i++) {
			if (!cleanArg[i].trim().matches("[a-zA-Z0-9]*")) {
				continue;
			}
			input.add(cleanArg[i].trim());
		}
		Collections.sort(input);
	}

	private void printAccnNumber(Integer index) {
		if (range) {
			range = false;
			response = response.concat("-" + input.get(index - 1));
			response = response.concat(", " + input.get(index));
		} else {
			response = response.concat(", " + input.get(index));
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

	public void printOrderedList() {
		// print 1st element
		response = input.get(0);

		// get the alpha and numberic portion for the first sorting string
		List<String> split = Parse(input.get(0));
		prevAlpha = split.get(0);
		prevNum = split.get(1);

		range = false;
		// loop from 2nd to last element in the input
		for (int index = 1; index < input.size(); index++) {
			split = Parse(input.get(index));

			currAlpha = split.get(0);
			currNum = split.get(1);

			// same alpha group?
			if (prevAlpha.compareTo(currAlpha) != 0) {
				printAccnNumber(index);
				continue;
			}

			// + same num length
			// Ex: 0090 and 00091 are not in same group
			if (prevNum.length() != currNum.length()) {
				printAccnNumber(index);
				continue;
			}

			// get the last digit
			Integer prevInt = Integer.parseInt(prevNum.substring(prevNum.length() - 1));
			Integer currInt = Integer.parseInt(currNum.substring(currNum.length() - 1));

			// + not consecutive number, its consecutive digits
			// Ex: Even 0099, 0100 are in different group
			if ( (prevInt + 1) != currInt) {
				printAccnNumber(index);
				continue;
			}

			// Both are in same group; dont print curr
			range = true;
			prevAlpha = currAlpha;
			prevNum = currNum;
		}// end for

	}	

	public String orderAccn(String args) {
		addStrings(args);
		printOrderedList();
		return response;
	}

}
