/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three.b;

import ir.assignments.three.fileHelper;
import ir.assignments.three.a.Frequency;
import ir.assignments.three.a.Utilities;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
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
		
		
	  // the subdomians
	  List <String> empty = new ArrayList<String>();
	  empty.add("");
	  List<String> subdomainWords = Utilities.tokenizeFile(fileHelper.SD,empty,"/n");
	  List<Frequency> subdomainFrequencies = computeWordFrequencies(subdomainWords);
	  File subDomainFile = new File("data/subDomains.txt");
	  for (Frequency f:subdomainFrequencies){
		  String SDWord = f.getText();
		  String input = String.format("%-30s, %s",SDWord.substring(0,SDWord.length()-3)+".ics.uci.edu", f.getFrequency());
		  fileHelper.writeFile(subDomainFile, input, false);
	  }
	  
		
	  //the 500 most common word
      File stopWordFile = new File("data/stopWords.txt");
      List<String> stop = Utilities.stopW(stopWordFile);
      List<String> BigList = new ArrayList<String>();
      for (File file:fileHelper.textFolders.listFiles()){
		   List<String> words = Utilities.tokenizeFile(file, stop," ");           
         BigList.addAll(words);
		}
      List<Frequency> frequencies = computeWordFrequencies(BigList);
      Utilities.printFrequencies(frequencies);
      
	}
}
