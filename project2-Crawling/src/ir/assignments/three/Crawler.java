/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */

package ir.assignments.three;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Crawler {

	/**
	 * This method is for testing purposes only. It does not need to be used
	 * to answer any of the questions in the assignment. However, it must
	 * function as specified so that your crawler can be verified programatically.
	 * 
	 * This methods performs a crawl starting at the specified seed URL. Returns a
	 * collection containing all URLs visited during the crawl.
	 * @throws Exception 
	 */
	public static Collection<String> crawl(String seedURL) throws Exception {
		Collection<String> returnCollection = new ArrayList<String>();
		String crawlStorageFolder = "data";
	    String userAgentString = "UCI Inf141-CS121 crawler 45168779 25792830 63279154 82691258";
	    	
	    	fileHelper.setDirectories(crawlStorageFolder);
	    	// from the sample of crawler4j on Github
	        
	        int politenessDelay = 2000;
	        int numberOfCrawlers = 50;

	        CrawlConfig config = new CrawlConfig();
	        config.setCrawlStorageFolder(crawlStorageFolder);
	        config.setUserAgentString(userAgentString);
	        config.setPolitenessDelay(politenessDelay);
	        config.setResumableCrawling(false);
	        config.setMaxDownloadSize(200000);
	        
	        

	        /*
	         * Instantiate the controller for this crawl.
	         */
	        PageFetcher pageFetcher = new PageFetcher(config);
	        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
	        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

	        /*
	         * For each crawl, you need to add some seed urls. These are the first
	         * URLs that are fetched and then the crawler starts following links
	         * which are found in these pages
	         */
	        controller.addSeed(seedURL);

	        /*
	         * Start the crawl. This is a blocking operation, meaning that your code
	         * will reach the line after this only when crawling is finished.
	         */
	        
	        controller.start(MyCrawler.class, numberOfCrawlers);
	        
	        try (Scanner scanner = new Scanner(fileHelper.idURL)){
	        	if (scanner.hasNext()){
	        		returnCollection.add(scanner.nextLine().split(",")[1]);
	        	}
	        	
	        }
	        
		return returnCollection;
	}
	
	public static void main(String[] args) throws Exception{
		for(String url: Crawler.crawl("http://www.ics.uci.edu/")){
			System.out.println(url);
		}

	}
}
