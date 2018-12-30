package scrapers;

import model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

/**
 * Created by sano on 12/30/18.
 */
public class ListScraper extends Scrap implements JobScraper {
    private static String searchLink="https://www.list.am/category?q=";
    public ListScraper(String keyword, List result) {
        super(keyword, result);
    }

    public void getJobs() throws InterruptedException {
        try {
            Document listPage = Jsoup.connect(searchLink+"&c=29").get();
            Elements resultList = listPage.getElementsByClass("t");
            System.out.println( " List size "+resultList.size());
            int count =0;
            for (Element res : resultList) {
                if(count==10){break;}
                Element a = res.getElementsByTag("a").first();
                count++;
                if(a == null){
                    continue;
                }

                String jobTitle = a.text();
                String link = "https://www.list.am/"+a.attr("href");

                Job job = new Job();
                job.setCompanyLogo("assets/img/job.png");
                job.setTitle(jobTitle);
                job.setLink(link);
                this.result.add(job);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Run List scrapper");
        try {
            getJobs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
