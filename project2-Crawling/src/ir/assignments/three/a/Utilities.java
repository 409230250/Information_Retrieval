/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */


package ir.assignments.three.a;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;


/**
 * A collection of utility methods for text processing.
 */
public class Utilities {
   
   public static ArrayList<String> stopW(File input) {
      ArrayList<String> aList = new ArrayList<String>();             // create a ArrayList for return
      Scanner scanner;                                               // create a scanner 
      try {                                                       
         if (input.exists()){							                     // if the file exists
   			scanner = new Scanner(input);			                     // 		if so, initialize or start scan 
   		}
   		else{										                           // 		otherwise, throw IOException 
   			throw new IOException("Input file does not exist.");
         }
         String line;                                                // create a string line, for scanning each line
         String[] sArray = {};                                       // create a empty String array sArray to get each word after spliting from a line
         
         while (scanner.hasNext()){                                  // while scanner has not gotten to the last line of the text file
            line = scanner.nextLine();                               // let line be the current line that scanner read
               // String replaceAll() Method: replaces each substring of this string that matches the given regular expression with the given replacement.
               sArray = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" ");    // replace character inside a string that doesnt satisfy the expression by empty string

               for (String s: sArray){                               // for each string in the sArray               
                  if(s != null && !s.isEmpty()){                     //    if it is not empty or null
				         aList.add(s);                                   //    then add it to the aList, return Arraylist   
                  }
			      }
		   }
         scanner.close();                                            // Close the scanner;
         
      } catch (IOException e) {                                      // catch IO Exception and print out error messae if so      
					System.err.println("IOException: "+e.getMessage());
		} catch (Exception e)     {                                    // catch all other Exception and print out error message.
               System.err.println("Error: " + e.getMessage());
      } finally {return aList;}         
	}
   
	/**
	 * Reads the input text file and splits it into alphanumeric tokens.
	 * Returns an ArrayList of these tokens, ordered according to their
	 * occurrence in the original text file.
	 * 
	 * Non-alphanumeric characters delineate tokens, and are discarded.
	 *
	 * Words are also normalized to lower case. 
	 * 
	 * Example:
	 * 
	 * Given this input string
	 * "An input string, this is! (or is it?)"
	 * 
	 * The output list of strings should be
	 * ["an", "input", "string", "this", "is", "or", "is", "it"]
	 * 
	 * @param input The file to read in and tokenize.
	 * @return The list of tokens (words) from the input file, ordered by occurrence.
	 */
    
    
    /*
    * In the case that the input string is "I'm Junjie Lin."
    * The output I guess is ["Im", "Junjie", "Lin"]
    * 
    * Explanation: This function would read the input text file and splits it into alphanumeric tokens.
    * It returns a list of strings that is made by a-zA-Z0-9 only. (Note: no space, empty string, or any punctuation)    
    */
	public static ArrayList<String> tokenizeFile(File input, List<String> stopWords,String split) {
		// TODO Write body!
      ArrayList<String> aList = new ArrayList<String>();             // create a ArrayList for return
      Scanner scanner;                                               // create a scanner 
      try {                                                       
         if (input.exists()){							                     // if the file exists
   			scanner = new Scanner(input);			                     // 		if so, initialize or start scan 
   		}
   		else{										                           // 		otherwise, throw IOException 
   			throw new IOException("Input file does not exist.");
         }
         
         String line;                                                // create a string line, for scanning each line
         String[] sArray = {};                                       // create a empty String array sArray to get each word after spliting from a line
         
         while (scanner.hasNext()){                                  // while scanner has not gotten to the last line of the text file
            line = scanner.nextLine();                               // let line be the current line that scanner read
               // String replaceAll() Method: replaces each substring of this string that matches the given regular expression with the given replacement.
               sArray = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(split);    // replace character inside a string that doesnt satisfy the expression by empty string
                                                                                          // after splitting the string by an space, assign it to sArray                   
               // Alternative way:                   
//                line = line.toLowerCase();
//                String delims = "[^a-zA-Z0-9']+";
//                sArray = line.split(delims);
               
               for (String s: sArray){                               // for each string in the sArray               
                  if(s != null && !s.isEmpty()){                     //    if it is not empty or null
                     if ((!stopWords.contains(s)) && (s.length()>1)){
                        if (!isNumeric(s)){
                           aList.add(s);
                        }
                     }
				                                            //    then add it to the aList, return Arraylist   
                  }
			      }
		   }
         scanner.close();                                            // Close the scanner;
         
      } catch (IOException e) {                                      // catch IO Exception and print out error messae if so      
					System.err.println("IOException: "+e.getMessage()+" - Skipping file: "+input);
		} catch (Exception e)     {                                    // catch all other Exception and print out error message.
               System.err.println("Error: " + e.getMessage());
      } finally {return aList;}         
	}
	
   
   public static boolean isNumeric(String str)  
   {  
     try  
     {  
       double d = Double.parseDouble(str);  
     }  
     catch(NumberFormatException nfe)  
     {  
       return false;  
     }  
     return true;  
   }
   
	/**
	 * Takes a list of {@link Frequency}s and prints it to standard out. It also
	 * prints out the total number of items, and the total number of unique items.
	 * 
	 * Example one:
	 * 
	 * Given the input list of word frequencies
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total item count: 6
	 * Unique item count: 5
	 * 
	 * sentence	2
	 * the		1
	 * this		1
	 * repeats	1
	 * word		1
	 * 
	 * 
	 * Example two:
	 * 
	 * Given the input list of 2-gram frequencies
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 * 
	 * The following should be printed to standard out
	 * 
	 * Total 2-gram count: 6
	 * Unique 2-gram count: 5
	 * 
	 * you think	2
	 * how you		1
	 * know how		1
	 * think you	1
	 * you know		1
	 * 
	 * @param frequencies A list of frequencies.
	 */
	public static void printFrequencies(List<Frequency> frequencies) {
		// TODO Write body!
      
      int totalItemCount = 0;                                           // initalize totalItemCount 0
      int uniqueItemCount = frequencies.size();                         // let the uniqueItemCount be the size of the input list
      int textSize = 0;                                                 // let textSize be 0      
      String item = "item";                                             // set item be "item" by defaut
      
      if (frequencies.size() > 0){                                      // if input List has Frequency object, otherwise, just output would be 0.
         textSize = frequencies.get(0).getText().split(" ").length;     // let the textSize be the text's length of the Frequency
         
		   int longestTextLength = frequencies.get(0).getText().length();
         for (int i=0; i<frequencies.size(); i++){                      // for each Frequency in the List
            totalItemCount += frequencies.get(i).getFrequency();        // let totalItemCount be the sum of all frequency
			   if (longestTextLength < frequencies.get(i).getText().length()){
				   longestTextLength = frequencies.get(i).getText().length();
			   }
   		}
      
         int wordSize = frequencies.get(0).getText().length() + 20;      					      // create wordSize to make sure how many space to give to the text

         if (textSize > 1){                                             // if textSize is greater than 1,
            item = Integer.toString(textSize)+"-gram";                  // which means it contains more than 1 single word
																		// update item and let it to be n-gram based on the textSize 
         }
         
         String space = Integer.toString(wordSize);                     // create and initialize space by converting wordSize from int to string
         
      try {
         File file = new File("data/CommonWords.txt");
         
         if (!file.exists()) {
				file.createNewFile();
			}
   		FileWriter fw = new FileWriter(file.getAbsoluteFile());
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write("Question 5: What are the 500 most common words in this domain?");
         bw.newLine();
         int counter = 1;
        		for (int i=0; i<frequencies.size(); i++){
               if (counter<=500){
      			   bw.write(String.format("%-5d",counter)+String.format("%-"+space+"s%-5d\n", frequencies.get(i).getText(), frequencies.get(i).getFrequency()));
      			   bw.newLine();
      			   		System.out.format("%-5d",counter);
      			   		System.out.format("%-"+space+"s%-5d\n", frequencies.get(i).getText(), frequencies.get(i).getFrequency());
      			   		
               }
               else{break;}
               counter++;
               
            }
            bw.close();

         }catch (IOException E) {System.err.println("IOException: "+E.getMessage());}
      
      }
      else{                                                             // print out ItemCount 0 for input list is empty.
         System.out.println("Total "+item+" count: "+totalItemCount);
		   System.out.println("Unique "+item+" count: "+uniqueItemCount);
      }
	}

}
