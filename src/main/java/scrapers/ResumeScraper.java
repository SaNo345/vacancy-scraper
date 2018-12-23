package scrapers;

import model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by sano on 12/23/18.
 */
public class ResumeScraper extends Scrap implements JobScraper {

    public ResumeScraper(String keyword, List result) {
        super(keyword, result);
    }


    @Override
    public void getJobs() throws InterruptedException {
        try {
            Document resumePage = Jsoup.connect("https://rezume.am/search?w=" + keyword).get();
            Elements jobList = resumePage.select("div[class='job_item jobs_list']");

            for (Element sjob : jobList) {
                String link = sjob.getElementsByTag("a").first().attr("href");
                String logoLink = sjob.getElementsByTag("img").first().attr("src");
                String titleStr = sjob.select("p[class='add_name']").text();
                int index = titleStr.indexOf("/ ");
                String company=null;
                String title = null;
                if(index>0) {
                    company = titleStr.substring(index+2);
                    title = titleStr.substring(0,index);
                }
                String dedlineText = sjob.select("p[class*='add_datetime']").text();
                //  System.out.println(dedlineText);
                Job job = new Job();
                job.setLink(link);
                job.setTitle(title);
                job.setCompanyLogo(logoLink);
                job.setCompany(company);
                this.result.add(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            getJobs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void   getAllJobs(String s){
        try {
            Document resumePage = Jsoup.connect("https://rezume.am/search?w=" + s).get();
            Elements jobList = resumePage.select("div[class='job_item jobs_list']");

            for (Element sjob : jobList) {
                String link = sjob.getElementsByTag("a").first().attr("href");
                String logoLink = sjob.getElementsByTag("img").first().attr("src");
                String titleStr = sjob.select("p[class='add_name']").text();
                int index = titleStr.indexOf("/ ");
                String company=null;
                String title = null;
                if(index>0) {
                     company = titleStr.substring(index+2);
                    title = titleStr.substring(0,index);
                }
                String dedlineText = sjob.select("p[class*='add_datetime']").text();
              //  System.out.println(dedlineText);
                Job job = new Job();
                job.setLink(link);
                job.setTitle(title);
                job.setCompanyLogo(logoLink);
                job.setCompany(company);
                System.out.println(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
