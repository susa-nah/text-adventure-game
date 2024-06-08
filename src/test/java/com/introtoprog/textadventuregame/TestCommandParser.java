package com.introtoprog.textadventuregame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

class TestCommandParser {
	
	CommandParser parser = new CommandParser(new String[] {"GO"}, new String[] {"LOOK INSIDE"});

	@Test
	void testCommandParser() {
		CommandParser p = new CommandParser();
		String testString = "Go to Left";
		assertEquals("GO", p.getCommand(testString));
		assertEquals("LEFT", p.getObject(testString));
	}

	@Test
	void testCommandParserStringArrayStringArray() {
		String testString = "Go Left";
		assertEquals("LEFT", parser.getCommand(testString));
		assertEquals(null, parser.getObject(testString));
	}

	@Test
	void testCommandParserArrayListOfStringArrayListOfString() {
		ArrayList<String> prefixes = new ArrayList<>();
		prefixes.add("GO");
		ArrayList<String> specialCommands = new ArrayList<>();
		specialCommands.add("LOOK INSIDE");
		CommandParser p = new CommandParser(prefixes, specialCommands);
		String testString = "Look inside that barrel";
		assertEquals("LOOK INSIDE", p.getCommand(testString));
		assertEquals("BARREL", p.getObject(testString));
	}
	
	@ParameterizedTest
	@CsvSource({"Look inside barrel,LOOK INSIDE", 
		"Examine inside barrel,EXAMINE", 
		"X barrel,X", 
		"go up,UP",
		"go left,LEFT",
		"go left right,LEFT",
		"d,D",
		"down,DOWN",
		"u,U",
		"look at barrel,LOOK",
		",",
		" ,",
		"xyz,XYZ",
		"help,HELP",
		"x white shirt,X",})
	void testgetCommand(String input, String expected) {
		String outcome = parser.getCommand(input);
		assertEquals(outcome, expected);
	}
	
	@ParameterizedTest
	@CsvSource({"Look inside barrel,BARREL", 
		"Examine inside barrel,BARREL", 
		"X barrel,BARREL", 
		"go north, ",
		"go east, ",
		"go west east,EAST",
		"x white shirt,WHITE SHIRT",
		"n,",
		"look at barrel,BARREL",
		",",
		" ,",
		"xyz,",
	"help me,ME"})
	void testGetObject(String input, String expected) {
		String output = parser.getObject(input);
		assertEquals(expected, output);
	}

}
