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

import java.io.File;
import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class Search {
					
	private String Index_term_doc_tfidf = "C:\\Users\\Junjie Lin\\Desktop\\ICS 45J\\CS122BWorkSpace\\CS121Project3SearchEngine\\Index_term_doc_tf.json";
	private String Index_UniqueWords = "C:\\Users\\Junjie Lin\\Desktop\\ICS 45J\\CS122BWorkSpace\\CS121Project3SearchEngine\\Index_UniqueWords.json"; 
	
	private String HTMLJsonFile = "C:/Users/Junjie Lin/Desktop/CompSci121/Project3/html_files.json";
	private String HTMLFolderPath = "C:/Users/Junjie Lin/Desktop/CompSci121/Project3/Html/";
	
	private JSONParser parser;
	private Object obj_uniqueWords;
	private Object obj_term_doc_tfidf;
	private Object obj_htmlJson;

	public ArrayList<String> lookUpIndex(ArrayList<String> inputList, Object Existing_obj_uniqueWords){
		ArrayList<String> indexList = new ArrayList<String>();
		try{
			
			if (Existing_obj_uniqueWords == null){
//				System.out.println("NullNullNullNullNullNull");
				parser = new JSONParser();
				obj_uniqueWords = parser.parse(new FileReader(Index_UniqueWords));
			}
			else{
//				System.out.println("Existing_obj_uniqueWords");
				obj_uniqueWords = Existing_obj_uniqueWords;
			}
//			System.out.println(obj_uniqueWords.toString());
			JSONObject jsonObject = (JSONObject) obj_uniqueWords;
//			Object name = jsonObject.get("cancel");
//			System.out.println("cancel: " + name.toString());
			for (int i=0; i<inputList.size(); i++){
				String index = jsonObject.get(inputList.get(i)).toString();
//				System.out.println(index.toString());
				indexList.add(index);
			}
        } catch (Exception e) {
            System.out.println("No results found for "+inputList.toString());
        }
		return indexList;
	}
	
	public ArrayList<String> lookUpTFIDF(ArrayList<String> indexList, Object Existing_obj_term_doc_tfidf){
		HashMap<String, Double> tfidfMap = new HashMap<String, Double>();
		try{
			if (Existing_obj_term_doc_tfidf == null){
//				System.out.println("NullNullNullNullNullNull");
				parser = new JSONParser();
				obj_term_doc_tfidf = parser.parse(new FileReader(Index_term_doc_tfidf));
			}
			else{
//				System.out.println("Existing_obj_term_doc_tfidf");
				obj_term_doc_tfidf = Existing_obj_term_doc_tfidf;
			}
			JSONObject jsonObject = (JSONObject) obj_term_doc_tfidf;
			for (int i=0; i<indexList.size(); i++){
				//String index = jsonObject.get(indexList.get(i)).toString();
				JSONObject tfidfObject = (JSONObject) jsonObject.get(indexList.get(i));
				for (Object k: tfidfObject.keySet()){
					Double tfidfValue = (Double) tfidfObject.get(k);
					if (!tfidfMap.containsKey(k)){
//						ArrayList<Double> tfidf = new ArrayList<Double>();
//						tfidf.add((Double) tfidfObject.get(k));
						tfidfMap.put((String) k, tfidfValue);
					}
					else{
						tfidfMap.put((String) k, tfidfMap.get(k)*tfidfValue);
					}										
				}
			}
						
        } catch (Exception e) {
            e.printStackTrace();
        }
		return Compare(tfidfMap);
	}
	
	public ArrayList<String> Compare(HashMap<String, Double> tfidfMap){
		//TODO:
		ArrayList<String> returnList = new ArrayList<String>();

		Set<Entry<String, Double>> set = tfidfMap.entrySet();
        List<Entry<String, Double>> list = new ArrayList<Entry<String, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        System.out.println(list.toString());
        for(Map.Entry<String, Double> entry:list){
        	if (returnList.size()<5){
        		returnList.add(entry.getKey());
        	}
        	else{break;}
        }
        System.out.println(returnList.toString());
		return returnList;

	}
	
	public ArrayList<DocInformation> lookUpDocs(ArrayList<String> tfidfList, Object Existing_obj_htmlJson){
		ArrayList<DocInformation> outputMap = new ArrayList<DocInformation>();
		try{
			if (Existing_obj_htmlJson == null){
//				System.out.println("NullNullNullNullNullNull");
				parser = new JSONParser();
				obj_htmlJson = parser.parse(new FileReader(HTMLJsonFile));
			}
			else{
//				System.out.println("Existing_obj_htmlJson");
				obj_htmlJson = Existing_obj_htmlJson;
			}
			
			JSONObject htmlObject = (JSONObject) obj_htmlJson;
			for (String docID: tfidfList){
				
				JSONObject tfidfObject = (JSONObject) htmlObject.get(docID);
				
				String htmlFile = (String) tfidfObject.get("file");
				String htmlURL = (String) tfidfObject.get("url");
				File input = new File(HTMLFolderPath + htmlFile);
				Document doc = Jsoup.parse(input, "UTF-8");
				String title = "";
				try{
					title = doc.select("title").first().text();
				} catch(Exception e){
					
				}
				String body = "";
                String bodyString = "";
				try{
					body = doc.select("body").first().text();
	                int endPoint= body.indexOf('.', body.indexOf('.')+1);
	                bodyString = body.substring(0, endPoint);
	                if (bodyString.length()>200){
	                	bodyString = body.substring(0, 250);
	                }
				} catch(Exception e){
									
				}


                outputMap.add(new DocInformation(title, htmlURL, bodyString+" ..."));
//                System.out.println(docID +" = "+ title);
//                outputMap.put(htmlFile, htmlURL);
                
			}
						
        } catch (Exception e) {
            e.printStackTrace();
        }
//		System.out.println(outputMap.size());
//		System.out.println(outputMap.toString());
		return outputMap;
	}

}
