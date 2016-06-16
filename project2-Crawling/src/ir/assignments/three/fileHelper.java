/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class fileHelper {
	
	static String parentFolderPath = Controller.crawlStorageFolder;
	static File parentPath = new File(parentFolderPath);
	public static File currentDocID = new File(parentFolderPath+"/currentDocID.txt");
	public static File idTextLength = new File(parentFolderPath+"/idTextLength.txt");
	public static File idURL = new File(parentFolderPath+"/idURL.txt");
	public static File SD = new File(parentFolderPath+"/SD.txt");
	public static File textFolders = new File (parentFolderPath+"/textFolder");
	
	static HashSet<String> subDomainSet = new HashSet<String>();
	
	/**
	 * 
	 * @param parentFolderPath
	 */
	public static void setDirectories(String parentFolderPath){
		
		try{
			System.out.println(parentFolderPath);
			//data
			parentPath.mkdir();
			//currentDocID
			currentDocID.createNewFile();
			//ID + URL
			idURL.createNewFile();
			
			//Subdomain
			SD.createNewFile();
			
			//ID + textLenth
			idTextLength.createNewFile();
			
			//Text Files Folder
			textFolders.mkdirs();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * get a string of the current Doc ID from the currentDocID file
	 * @param reset (to reset the currentID back to 0)
	 * @return the current ID (String)
	 */
	public static String getCurrentID(boolean reset){
		try (Scanner scanner = new Scanner(currentDocID)){
			if (scanner.hasNext()&&!reset){
				return scanner.next();
			}
			
		} catch (Exception e) {
			System.out.println("Error Reading CurrentID File:"+e);
		}
		return "0";
		
		
	}
	
	/**
	 * write the content to file
	 * @param file
	 * @param input
	 * @param overwrite (overwrite the existing file)
	 */
	public static void writeFile(File file, String input,boolean overwrite){

		try (FileWriter fileWriter = new FileWriter (file, !overwrite)){
			fileWriter.write(input+System.lineSeparator());
		    fileWriter.close ();  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	/**
	 * create text file to the text folder
	 * @param docID
	 * @param textInput
	 */
	public static void createTextFile(String docID,String textInput){
		File file = new File(textFolders.getPath()+"/"+docID+".txt");
		try {
			file.createNewFile();
			writeFile(file, textInput,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}


	

}
