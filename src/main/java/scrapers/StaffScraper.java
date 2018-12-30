package scrapers;

import model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by sano on 11/11/18.
 */
public class StaffScraper extends Scrap implements JobScraper {

    private static String siteSearchLink = "https://staff.am/en/jobs?JobSearchForm%5Btype%5D=&JobSearchForm%5Btype%5D=1&JobSearchForm%5BkeyWord%5D=";

    public StaffScraper(String keyword, List result) {
        super(keyword, result);
    }

    public void getJobs() {
        Document staffpage = null;
        try {
            staffpage = Jsoup.connect(siteSearchLink + keyword).get();


            Elements jobList = staffpage.getElementsByAttributeValue("data-page", "1");

            for (Element sjob : jobList) {
                String logoLink = "https:" + sjob.getElementsByTag("img").first().attr("src");
                String title = sjob.select("p[class='font_bold']").first().text();
                String company = sjob.select("div.job-inner.job-item-title > p:nth-child(2)").first().text();
                String dateStr = sjob.select("div[class='job-inner job-list-deadline']").first().getElementsByTag("p").text();
               // System.out.println("date -" + dateStr);
                DateFormat format = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
                Date deadline = null;
                try {
                    deadline = format.parse(dateStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String link = "https://staff.am" + sjob.select("a.load-more").first().attr("href");

                Job job = new Job(0, title, "", company, link, null, deadline, logoLink);
                if(job.getCompanyLogo() == null){
                    job.setCompanyLogo("assets/img/job.png");
                }
                this.result.add(job);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        System.out.println("Run Staff scraper");
            getJobs();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
 /* public List<Job> getJobs() throws InterruptedException {
       WebDriver driver = ChromeDriverFactory.getChrome();
       driver.get("https://staff.am/en/jobs?JobSearchForm%5Btype%5D=&JobSearchForm%5Btype%5D=1&JobSearchForm%5BkeyWord%5D="+this.keyword);
        Thread.sleep(1000);

        WebElement searchResultList = driver.findElement(By.id("search_list_block"));
        List<WebElement> jobs = searchResultList.findElements(By.cssSelector("div[data-page='1']"));

        System.out.println ( "Staff job size - " +jobs.size());

        for(WebElement job : jobs){
            WebElement jobTitleElement = job.findElement(By.cssSelector("div[class='job-inner job-item-title']"));
            List<WebElement> pelementList = jobTitleElement.findElements(By.tagName("p"));
           // System.out.println("Job title - " +pelementList.get(0).getText());
           // System.out.println("Job Company - " +pelementList.get(1).getText());
            WebElement jobDedlineElement = job.findElement(By.cssSelector("div[class='job-inner job-list-deadline']"));
           // System.out.println("deadline - "+jobDedlineElement.getText());
            WebElement linkElement = job.findElement(By.cssSelector("a[class='load-more btn width100']"));
            //System.out.println(linkElement.getAttribute("href"));

            Job scrapJob = new Job();
            scrapJob.setTitle(pelementList.get(0).getText());
            scrapJob.setCompany(pelementList.get(1).getText());
            scrapJob.setLink(linkElement.getAttribute("href"));

            result.add(scrapJob);
        }

        driver.quit();

        return result;
    }*/