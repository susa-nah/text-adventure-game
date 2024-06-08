package com.introtoprog.textadventuregame;

import java.util.ArrayList;
import java.util.Collections;

public class CommandParser {
	private ArrayList<String> prefixes;
	private ArrayList<String> specialCases;
	
	public CommandParser() {
		this.prefixes = new ArrayList<>();
		this.specialCases = new ArrayList<>();
	}
	
	public CommandParser(String[] prefixes, String[] specialCases) {
		this.prefixes = new ArrayList<>();
		Collections.addAll(this.prefixes, prefixes);
		this.specialCases = new ArrayList<>();
		Collections.addAll(this.specialCases, specialCases);
	}
	
	public CommandParser(ArrayList<String> prefixes, ArrayList<String> specialCases) {
		this.prefixes = new ArrayList<>();
		this.prefixes.addAll(prefixes);
		this.specialCases = new ArrayList<>();
		this.specialCases.addAll(specialCases);
	}
	
	/*
    Gets the command part by using split index.
    Assumes first word / special command is a command.
    */
    public String getCommand(String input) {
    	// Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }
        
    	// Normalise input to Uppercase
        input = input.toUpperCase();
        
        // Check for special cases
        for (String c: specialCases) {
        	if (input.startsWith(c)) {
        		return c;
        	}
        }
        
        // Strip prefixes
        input = stripPrefix(input);
        
        // Use getSplitIndex() to determine start of command.
        int splitIndex = getSplitIndex(input);
        
        // When the splitIndex would be length of string, return string.
        if (input.length() == splitIndex) {
        	return input;
        }
        
        // If the split index is valid, get the substring and return.
        if (splitIndex > 0) {
            input = input.substring(0,splitIndex);
            return input;
        }
        
        return null;
    }
	
	/*
    Get object part of string and strips small words.
    */
    public String getObject(String input) {
        // Avoid NullPointerException
        if (input == null || input.isEmpty() || input.isBlank()) {
            return null;
        }
        
        // Get the command
        String command = getCommand(input);
        
        // Check if command is whole string
        
        // Normalise to uppercase.
        input = input.toUpperCase();
 
        // Strip prefixes
        input = stripPrefix(input);
        
        // Return null for command only entries.
        if (command.length() == input.length()) {
            return null;
        }
        
        // Get substring and trim whitespace
        input = input.replace(command + " ", "");
        
        // Get rid of common leading text
        String[] nonfiling = new String[] {"THE ","A ", "AN ","THAT ","THIS ",
            "INSIDE ", "AT ", "TO "};
        for (String text : nonfiling) {
            if (input.startsWith(text)) {
                input = input.replace(text, "");
            }
        }
        
        return input;
    }
	
	/*
    Figure out where the command ends and the object name begins
    */
    private int getSplitIndex(String input) {
        // Check for space and return first index of or length
        if (input.contains(" ")) {
            return input.indexOf(" ");
        } else {
            return input.length();
        }
    }
    
    /*
     * Strip prefixes
     */
    private String stripPrefix(String input) {
    	for (String prefix: prefixes) {
    		if (input.startsWith(prefix)) {
    			return input.replace(prefix + " ", "");
    		}
    	}
    	return input;
    }
}
