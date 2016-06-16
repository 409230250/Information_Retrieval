package searchEnginePackage;

/* 
 * Assignment 3
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;


public class InputSearch {

	private String inputLine;
	private ArrayList<String> inputStrings;
	Scanner scanner;
	
	public InputSearch(){
		inputLine = "";
		inputStrings = new ArrayList<String>();
	}

	public InputSearch(String inputLine){
		this.inputLine = inputLine;
	}
	
	public void readInput(){
		System.out.print("Enter text for your search: ");
		scanner = new Scanner(System.in);
		inputLine = scanner.nextLine();
//		System.out.println("Searching " + inputLine + "...");
		generateInput(inputLine);
	}
	
	public void generateInput(String input){
		inputStrings = Utilities.tokenizeFile(input);
//		System.out.println("inputList: " + inputStrings.toString());
	}
	
	public String getInputLine() {
		return inputLine;
	}

	public ArrayList<String> getInputStrings() {
		return inputStrings;
	}

}
