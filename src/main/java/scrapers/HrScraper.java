package scrapers;

import model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by sano on 12/2/18.
 */
public class HrScraper extends Scrap implements JobScraper{

    public HrScraper(String keyword, List result) {
        super(keyword, result);
    }
    @Override
    public void run() {
        System.out.println("Run HR scraper");
        try {
            getJobs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getJobs() throws InterruptedException {
        try {
            Document hrpage = Jsoup.connect("http://hr.am/search/vacancy/").data("keyword", keyword).post();
            Elements allResult = hrpage.getElementsByClass("search-item");

            for(Element aElem:allResult){
                Job job1 = new Job();
                job1.setTitle(aElem.getElementsByTag("h3").first().text());
                job1.setCompany(aElem.getElementsByTag("p").first().text());
                job1.setLink("http://hr.am"+aElem.attr("href"));
                this.result.add(job1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   /* public List<Job> getJobs() throws InterruptedException {
        WebDriver driver = ChromeDriverFactory.getChrome();
        driver.get("http://hr.am/");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("div[class='grid searchbtn mobile-menu']")).click();
        Thread.sleep(200);
        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys(keyword);
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        List<WebElement> jobList = driver.findElements(By.cssSelector("a[class='search-item']"));
        System.out.println ( "HR job size - "+jobList.size());
        for (WebElement job:jobList){
            WebElement h3 = job.findElement(By.tagName("h3"));
            if(h3.getText() == null || h3.getText().isEmpty() ){
                break ;
            }
            WebElement companyName = job.findElements(By.tagName("p")).get(0);

          //  System.out.println("Job Title - "+h3.getText());
          //  System.out.println("Job Company - "+companyName.getText());
          //  System.out.println("Job Link - "+job.getAttribute("href"));

            Job scrapJob = new Job();
            scrapJob.setTitle(h3.getText());
            scrapJob.setCompany(companyName.getText());
            scrapJob.setLink(job.getAttribute("href"));
            result.add(scrapJob);

        }
        driver.quit();


        return result;
    }*/


}
