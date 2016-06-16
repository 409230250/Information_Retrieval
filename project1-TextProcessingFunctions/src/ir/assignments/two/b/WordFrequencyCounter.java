package ir.assignments.two.b;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

import java.io.File;
import java.util.List;

/**
 * Counts the total number of words and their frequencies in a text file.
 */
public final class WordFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private WordFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique word in the original list. The frequency of each word
	 * is equal to the number of times that word occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied words sorted
	 * alphabetically.
	 * 
	 * The original list is not modified.
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["this", "sentence", "repeats", "the", "word", "sentence"]
	 * 
	 * The output list of frequencies should be 
	 * ["sentence:2", "the:1", "this:1", "repeats:1",  "word:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of word frequencies, ordered by decreasing frequency.
	 */
	public static List<Frequency> computeWordFrequencies(List<String> words) {
		// TODO Write body!
      List<Frequency> Alist = new ArrayList<Frequency>();                  // create and initialize a output list
      if (words == null){                                                  // if the input list is then, then return, done
         return Alist;  
      }
                                                                           // I get this idea from some posts on piazza
      HashMap<String,Integer> wordCount = new HashMap<String, Integer>();  // create a map data structure for organizing the amount of string
      for (String word: words){                                            // for each word from the input list
            Integer count = wordCount.get(word);                           // count the amount of one key      
            wordCount.put(word, (count == null) ? 1 : count+1);            // if it exists in the map, then its value will increase by 1
      }                                                                    // if it doesnt exist in the map, then add the key to the map and set its value be 1
      for (String k : wordCount.keySet()){                                 // for key in the map.keyset
         Frequency f = new Frequency(k, wordCount.get(k));                 // convert the key to a Frequency object
         Alist.add(f);                                                     // then add the Frequency object to the return list
      }
      
      // My own sorting function, O(n^2). But I found a better algorithm from piazza, using Comparator
      /*////////////////////////////////////////////
      ArrayList<Frequency> copy = new ArrayList<Frequency>();	
		while (Alist.size()>0){											
			Frequency first = Alist.get(0);
			for (Frequency m: Alist){									
				if (first.getFrequency() < (m.getFrequency())){	
					first = m;										
				}
            if (first.getFrequency() == (m.getFrequency())){	
               if (first.getText().compareTo(m.getText()) > 0){
					   first = m;									
				   }
            }
			}
			copy.add(first);							
			Alist.remove(first);						
		}
      Alist = copy;
      
      /*///////////////////////////////////////////
      
      WordFrequencyComparator comparator = new WordFrequencyComparator();     // Based on how people explain how Comparator words, create a comparator 
      Collections.sort(Alist, comparator);                                    // Using the comparator to sort the output list
		return Alist;
	}
   
   
	/**
	 * Runs the word frequency counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
   
		File file = new File(args[0]);
      //File file = new File("data/testB.txt");
		List<String> words = Utilities.tokenizeFile(file);        
      List<Frequency> frequencies = computeWordFrequencies(words);
		Utilities.printFrequencies(frequencies);
      
	}
}
