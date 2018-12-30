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
public class WorknetScraper  implements JobScraper{
    private String keyword = "";
    private List<Job> result ;

    public WorknetScraper(String keyword, List<Job> destination) {
        this.keyword = keyword;
        this.result= destination;
    }

    /*public void getJobs() throws InterruptedException {
       /* WebDriver driver = ChromeDriverFactory.getChrome();

        driver.get("https://www.worknet.am/hy/jobs?query="+this.keyword);
        Thread.sleep(2000);
        WebElement jobElementsList = driver.findElement(By.cssSelector("div[class='listview listview--bordered job-box']"));
        List<WebElement> jobElementsListElements = jobElementsList.findElements(By.cssSelector("div[class='listview__item']"));

        //System.out.println ( "Worknet job size - "+jobElementsListElements.size());

        for(WebElement job:jobElementsListElements){
            WebElement jobHeading = job.findElement(By.cssSelector("div[class='listview__heading']"));
            WebElement jobLinkElement = jobHeading.findElement(By.tagName("a"));
          //  System.out.println("Job title - "+jobLinkElement.getText());
          //  System.out.println("Job link - "+jobLinkElement.getAttribute("href"));//listview__content

            WebElement jobContent = job.findElement(By.cssSelector("div[class='listview__content']"));
            String title = jobLinkElement.getText();
            String link = jobLinkElement.getAttribute("href");
            String description = jobContent.findElement(By.tagName("p")).getText();
            //System.out.println("Job description - "+jobContent.findElement(By.tagName("p")).getText());
            Job job1 = new Job();
            job1.setTitle(title);
            job1.setLink(link);
            job1.setDescription(description);
            result.add(job1);


        return  result;
    }*/

    public void getJobs(){
        try {
            Document worknetPage = Jsoup.connect("https://www.worknet.am/hy/jobs?query="+keyword).get();
            Elements jobList = worknetPage.select("div[class='listview__item']");
            int count = 0;
            for (Element sjob : jobList) {
                if(count == 20){
                    break;
                }
                Element titleElement =sjob.getElementsByTag("a").first();
                String title = titleElement.text();
                String imgSrc ="https://www.worknet.am"+ sjob.getElementsByTag("img").first().attr("src");
                String link = "https://www.worknet.am"+ titleElement.attr("href");
                String description = sjob.getElementsByTag("p").first().text();
                Job job = new Job(title,description,link,imgSrc);
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
        System.out.println("Run Worknet scraper");
        getJobs();

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
