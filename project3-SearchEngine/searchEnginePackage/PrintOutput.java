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



public class PrintOutput {	
	public static void print(ArrayList<DocInformation> outputList, long startTime){
		long endTime  = System.currentTimeMillis();
		double totalTime = (endTime - startTime)/1000.0;
		int i = 1;
		System.out.println("\nTop "+outputList.size()+" results (" + totalTime +" seconds)");
		for (DocInformation doc: outputList){
//			System.out.println(""+i+". Doc Title: "+doc.docName);
//			System.out.println("URL: "+doc.docURL);
			System.out.println(doc.docURL);

			System.out.println();
			i++;
		}
	}
}
