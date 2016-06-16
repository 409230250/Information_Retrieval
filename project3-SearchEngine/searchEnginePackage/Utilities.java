package searchEnginePackage;

/* 
 * Assignment 3
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;


public class Utilities {
   private static String stopWordFilePath = "C:\\Users\\Junjie Lin\\Desktop\\ICS 45J\\CS122BWorkSpace\\CS121Project3SearchEngine\\stopWords.txt";

   public static ArrayList<String> stopW() {
      ArrayList<String> aList = new ArrayList<String>();
      File stopWordFile = new File(stopWordFilePath);

      Scanner scanner;                                             
      try {                                                       
         if (stopWordFile.exists()){							                    
   			scanner = new Scanner(stopWordFile);			                  
   		}
   		else{										                         
   			throw new IOException("Input file does not exist.");
         }
         String line;                                                
         String[] sArray = {};                                       
         
         while (scanner.hasNext()){                                  
            line = scanner.nextLine();                              
               sArray = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" ");   

               for (String s: sArray){                                     
                  if(s != null && !s.isEmpty()){                     
				         aList.add(s);                                   
                  }
			   }
		   }
         scanner.close();                                            
      } catch (IOException e) {                                      
					System.err.println("IOException: "+e.getMessage());
		} catch (Exception e)     {                                    
               System.err.println("Error: " + e.getMessage());
      } finally {return aList;}         
	}
   

	public static ArrayList<String> tokenizeFile(File input, List<String> stopWords, String split) {
		// TODO Write body!
      ArrayList<String> aList = new ArrayList<String>();             
      Scanner scanner;                                             
      try {                                                       
         if (input.exists()){							                    
   			scanner = new Scanner(input);			                    
   		}
   		else{										                           
   			throw new IOException("Input file does not exist.");
         }
         
         String line;                                                
         String[] sArray = {};                                       
         
         while (scanner.hasNext()){                                 
            line = scanner.nextLine();                               
               sArray = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(split);    
               
               for (String s: sArray){                                      
                  if(s != null && !s.isEmpty()){                    
                     if ((!stopWords.contains(s))){
                        //if (!isNumeric(s)){
                           aList.add(s);
                        //}
                     }
				                                           
                  }
			   }
		   }
         scanner.close();                                            
         
      } catch (IOException e) {                                         
					System.err.println("IOException: "+e.getMessage()+" - Skipping file: "+input);
		} catch (Exception e)     {                                   
               System.err.println("Error: " + e.getMessage());
      } finally {return aList;}         
	}
	
   /*
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
   */
   
	/**
	 * This is the one we use in Project 3, search engine.
	 * 
	 * @param line
	 * @return
	 */
	
   public static ArrayList<String> tokenizeFile(String line) {
		ArrayList<String> aList = new ArrayList<String>();            
    	List<String> stopWords = Utilities.stopW();
        String[] sArray = {};                                      
        sArray = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" ");    
//        line = line.toLowerCase();
//        String delims = "[^a-zA-Z0-9-_']+";
//        sArray = line.split(delims);
        
	      for (String s: sArray){                                     
	         if(s != null && !s.isEmpty()){                   
	            if ((!stopWords.contains(s))){
	               //if (!isNumeric(s)){
	                  aList.add(s);
	               //}
	            }
	         }
		   }
	      return aList;
	}
   
   	
	public static void writeFile(File file, String input, boolean overwrite){

		try (FileWriter fileWriter = new FileWriter (file, !overwrite)){
			fileWriter.write(input+System.lineSeparator());
		    fileWriter.close ();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
}
