import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import scrapers.ResumeScraper;
import scrapers.StaffScraper;

import java.io.IOException;

/**
 * Created by sano on 12/14/18.
 */
@SpringBootApplication
@ComponentScan({"controller"})
public class JobScrapApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JobScrapApplication.class,args);
    }
}
