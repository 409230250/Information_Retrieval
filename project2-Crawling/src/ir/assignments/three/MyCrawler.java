/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
	
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g|ico" 
			+ "|png|tiff?|mid|mp2|mp3|mp4"
			+ "|wav|avi|mov|mpeg|ram|m4v|mkv|ogg|ogv|pdf" 
			+ "|ps|eps|tex|ppt|pptx|doc|docx|xls|xlsx|names"
			+ "|data|dat|exe|bz2|tar|msi|bin|7z|psd|dmg|iso|epub|dll|cnf|tgz|sha1" 
			+ "|thmx|mso|arff|rtf|jar|csv|webm"
			+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");

	
   // public static Collection<String> URLsVisted = new ArrayList<String>();
    //public static HashMap<String,Integer> urlTextMap =new HashMap<String,Integer> ();
    public static String currentdocID = fileHelper.getCurrentID(false); //true to reset
    
	
    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         String domain = url.getDomain().toLowerCase();
         String subDomain = url.getSubDomain().toLowerCase();
         return !FILTERS.matcher(href).matches()
        		&& domain.equals("uci.edu")
                && subDomain.endsWith(".ics")
         		&& !subDomain.startsWith("archive")
         		&& !subDomain.startsWith("calendar")
         		&& !subDomain.startsWith("duttgroup");
         
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         if (page.getParseData() instanceof HtmlParseData) {

             String url = page.getWebURL().getURL();
             String subDomain = page.getWebURL().getSubDomain();
        	 
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
             String text = htmlParseData.getText();
             currentdocID = String.valueOf(Integer.parseInt(currentdocID)+1);
             
             //writeFile(File file, String input,boolean overwrite){
             //record subdomain
             fileHelper.writeFile(fileHelper.SD, subDomain, false); 
             //record current DocID
             fileHelper.writeFile(fileHelper.currentDocID,currentdocID,true); 
             //record length of the parsed page
             fileHelper.writeFile(fileHelper.idTextLength,String.format("%s,%s", currentdocID, text.length()),false); 
             //record the url parsed
             fileHelper.writeFile(fileHelper.idURL,String.format("%s,%s", currentdocID, url),false); 
             //create a text file that contains the text of the parsed page
             fileHelper.createTextFile(currentdocID, text);
             
             System.out.println(currentdocID);

         }
      
     }
     

     
}
