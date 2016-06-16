/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ir.assignments.three.b.WordFrequencyCounter;

public class computeFrequency {
	public static int globalMax = 0;
	public static String getLargestLength(File file) throws IOException{
		BufferedReader bufRead = new BufferedReader(new FileReader(file));
		int max = 0;
		String nextLine = "";
		String id = "";
		String split[];
		while( (nextLine = bufRead.readLine()) != null){
			split = nextLine.split(",");
			int newNum = Integer.parseInt(split[1]);
			if(max < newNum){
			    id = split[0];
				max = newNum;
			}
			
		}
		globalMax = max;
		return id;
	}

	public static String getUrlFromId(File idURL, String ID) throws IOException{
		BufferedReader bufRead = new BufferedReader(new FileReader(idURL));
		String nextLine = "";
		String toReturn = "";
		while( (nextLine = bufRead.readLine()) != null)
		{
			String [] split = nextLine.split(",");
			if(split[0].equals(ID)){
				return split[1];
			}
		}
		// if doesn't exist return empty string
		// might indicate that there's an error
		return toReturn;
			
	}
	public static void writeResultToFile(String result, int length) throws IOException{
		FileWriter writer = new FileWriter("data/longestPage.txt");
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write("The page with the largest text length");
		bw.newLine();
		bw.write(String.format("%-"+result.length()+"s\t"+"%-5s\n", "URL", "LENGTH"));
		bw.newLine();
		bw.write(String.format("%-5s\t%-5d\n", result , length));
		bw.newLine();
		bw.close();
	}
	public static void main(String[] args){
		   try{
			   	// The file with the longest length
	             String largestID = computeFrequency.getLargestLength(fileHelper.idTextLength);
	             String urlWithLargestContent =  computeFrequency.getUrlFromId(fileHelper.idURL, largestID);
	             computeFrequency.writeResultToFile(urlWithLargestContent, computeFrequency.globalMax);
	             System.out.println(largestID);
	             System.out.println(urlWithLargestContent);
	             
	             
	         }catch(Exception e){
	        	 
	         
	         }

	}
}
