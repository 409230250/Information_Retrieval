package ir.assignments.two.d;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

public class PalindromeFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private PalindromeFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique palindrome found in the original list. The frequency of each palindrome
	 * is equal to the number of times that palindrome occurs in the original list.
	 * 
	 * Palindromes can span sequential words in the input list.
	 * 
	 * The returned list is ordered by decreasing size, with tied palindromes sorted
	 * by frequency and further tied palindromes sorted alphabetically. 
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["do", "geese", "see", "god", "abba", "bat", "tab"]
	 * 
	 * The output list of palindromes should be 
	 * ["do geese see god:1", "bat tab:1", "abba:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of palindrome frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computePalindromeFrequencies(ArrayList<String> words) {
		// TODO Write body!
		// You will likely want to create helper methods / classes to help implement this functionality
      
      List<Frequency> answer = new ArrayList<Frequency>();                    // create an output list called answer
      if (words == null){                                                     // if the input list is empty, then done.
         return answer;
      }
      
      HashMap<String, Integer> Palindrome = new HashMap<String, Integer>();   // create a map data structure for organizing the amount of string
      for (int i=0; i<words.size(); i++){                                     // for each word from input list
         String goingToCheck = "";                                            // initialize a string called goingToCheck
         for (int j=i; j<words.size(); j++) {                                 // for each word from the input list    
            goingToCheck += " "+words.get(j);                                 // add the next word to goingToCheck (I add " " for make sure the two words are separated)   
            goingToCheck = goingToCheck.trim();                //It returns a copy of this string with leading and trailing white space removed
            if (istPalindrom(goingToCheck)){                                  // check if goingToCheck is palindrom
               int currentText = Palindrome.containsKey(goingToCheck) ? Palindrome.get(goingToCheck) : 0;   // if so, add it to the map
               Palindrome.put(goingToCheck, currentText+1);                   // if the string exits in the keySet, then add 1 to the value. otherwise, set value as 0       
            }                                                                 // then add key and value to map
         }
      }
      
      for (String k : Palindrome.keySet()){                                   // for each pair <key, value> in map, convert it to a Frequency object
         Frequency f = new Frequency(k, Palindrome.get(k));
         answer.add(f);                                                       // then, add the Frequency object to the output list
      }
      
      PalindromeFrequencyComparator comparator = new PalindromeFrequencyComparator();  // Create a comparator by making a new comparator, differnt one from part b and c
      Collections.sort(answer, comparator);                                            // use the comparator to sort the output list   
      
		return answer;
	}
   
   /* Helper Function: 
   *  Function istPalindrom: check if a string is palindrom.
   *  If so, return True; otherwise, return false;
   */
   public static boolean istPalindrom(String words){   
      String wordWithoutSpace = "";                                               // create and initialize a empty string, wordWithoutSpace
      for (int i = 0; i < words.length(); i++) {                                  // for i=0 to the length of the input string  
         char indexString = words.charAt(i);                                      // let indexString be the each character of the input string  
         if (indexString != ' '){                                                 // if indexString is a space,   
            wordWithoutSpace += indexString;                                      // then skip the one and add the char that is not a space to wordWithoutSpace   
         }
      }
         
      for (int i = 0; i < Math.floor(wordWithoutSpace.length() / 2); i++) {       // for i = 0 to half of the word length
         if (wordWithoutSpace.charAt(i) != wordWithoutSpace.charAt(wordWithoutSpace.length() - i - 1))  // Check if string's first half is the same as second half
            return false;                                                         // if not, return false
      }
      return true;                                                                // after go through all the char of the string, then return true for matching.
   }   
   
   
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
   
		File file = new File(args[0]);   
		//File file = new File("data/testD4.txt");
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computePalindromeFrequencies(words);
		Utilities.printFrequencies(frequencies);
      
	}
}
