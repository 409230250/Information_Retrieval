package ir.assignments.two.c;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;
import ir.assignments.two.b.WordFrequencyComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */
	private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		// TODO Write body!
      List<Frequency> answer = new ArrayList<Frequency>();                 // initialize a output list called answer
      if (words == null){                                                  // if the input arraylist is empty, then done
         return answer;
      }      
      
      HashMap<String,Integer> wordCount = new HashMap<String, Integer>();  // create a map data structure for organizing the amount of string
      String text;                                                         // create a string for combining two words
      for (int i=0; i<(words.size()-1); i++){                              // for each word inside the input list
         text = (words.get(i)+" "+words.get(i+1));                         // add the word and its neighbor to text
         Integer count = wordCount.get(text);                              // count the amount of text in the map
         wordCount.put(text, (count == null) ? 1 : count+1);               // if map has the text, then increase 1 for that key
      }                                                                    // otherwise, add the text to map and set its value be 1;   
                  
      for (String k : wordCount.keySet()){                                 // for each pair <key, value> in map, convert it to a Frequency object
         Frequency f = new Frequency(k, wordCount.get(k));                 
         answer.add(f);                                                    // then, add the Frequency object to the output list
      }

      WordFrequencyComparator comparator = new WordFrequencyComparator();  // Create a comparator, using the same one as part b
      Collections.sort(answer, comparator);                                // use the comparator to sort the output list      
      
		return answer;
	}
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
   
		File file = new File(args[0]);   
		//File file = new File("data/testC.txt");
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		Utilities.printFrequencies(frequencies);
      
	}
}
