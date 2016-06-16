package searchEnginePackage;

/* 
 * Assignment 3
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

import java.io.FileReader;
import java.util.ArrayList;


import org.json.simple.parser.JSONParser;


public class SearchManager {
	public ArrayList<DocInformation> output = new ArrayList<DocInformation>();

	/**
	 * @param args
	 */
	public SearchManager(String inputString) {
		// TODO Auto-generated method stub
		
		InputSearch inputing = new InputSearch();
		inputing.generateInput(inputString);
		
		long startTime = System.currentTimeMillis();
		
		System.out.println("Searching \"" + inputString + "\"");
		System.out.println("inputList: " + inputing.getInputStrings().toString());
		
		String Index_term_doc_tfidf = "C:\\Users\\Junjie Lin\\Desktop\\ICS 45J\\CS122BWorkSpace\\CS121Project3SearchEngine\\Index_term_doc_tf.json";
		String Index_UniqueWords = "C:\\Users\\Junjie Lin\\Desktop\\ICS 45J\\CS122BWorkSpace\\CS121Project3SearchEngine\\Index_UniqueWords.json"; 
		
		String HTMLJsonFile = "C:/Users/Junjie Lin/Desktop/CompSci121/Project3/html_files.json";
		String HTMLFolderPath = "C:/Users/Junjie Lin/Desktop/CompSci121/Project3/Html/";
		
		JSONParser parser;
		Object obj_uniqueWords = null;
		Object obj_term_doc_tfidf = null;
		Object obj_htmlJson = null;
		
		try{
			parser = new JSONParser();
			obj_uniqueWords = parser.parse(new FileReader(Index_UniqueWords));
        } catch (Exception e) {
            e.printStackTrace();
        }
		try{
			parser = new JSONParser();
			obj_term_doc_tfidf = parser.parse(new FileReader(Index_term_doc_tfidf));
        } catch (Exception e) {
            e.printStackTrace();
        }
		try{
			parser = new JSONParser();
			obj_htmlJson = parser.parse(new FileReader(HTMLJsonFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		Search search = new Search();
		ArrayList<String> indexList = search.lookUpIndex(inputing.getInputStrings(), obj_uniqueWords);
		if (indexList.size() == 0){return;}
		System.out.println("inputList Index: " + indexList.toString());
		
		ArrayList<String> tfidfList = search.lookUpTFIDF(indexList, obj_term_doc_tfidf);
		System.out.println("Top tfidf List: " + tfidfList.toString());
		

		output = search.lookUpDocs(tfidfList, obj_htmlJson);
//		System.out.println("==================");
//		System.out.println(output.size());
//		System.out.println(output.toString());
//		System.out.println("==================");
		PrintOutput.print(output, startTime);
		
		
	}

}
