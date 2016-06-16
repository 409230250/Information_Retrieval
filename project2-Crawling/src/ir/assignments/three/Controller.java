/* 
 * Assignment 2
 * Chen, Andy K : 45168779
 * Lin, Junjie : 25792830
 * Samtani, Chirag V: 63279154
 * Derian, Fransiskus : 82691258
 * 
 */


package ir.assignments.three;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
	public static String crawlStorageFolder = "data";
    public static String userAgentString = "UCI Inf141-CS121 crawler 45168779 25792830 63279154 82691258";
    public static String seedURL = "http://www.ics.uci.edu/";
    public static void main(String[] args) throws Exception {
    	
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
        
    }
    
    
    
}
