package scrapers;

/**
 * Created by sano on 12/2/18.
 */
public interface JobScraper extends Runnable{
     void getJobs() throws InterruptedException;
}
