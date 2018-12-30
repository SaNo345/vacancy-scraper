package controller;

import comparator.WordMatchComparator;
import model.Job;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import scrapers.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sano on 12/14/18.
 */
@RestController
@RequestMapping("/scrap")
public class MainController {

    @GetMapping("/job")
    public List<Job> getJobsByKeyword(@RequestParam("keyword") String keyword) {
        List<Job> dest = new CopyOnWriteArrayList<>();

        long start = System.nanoTime();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(new StaffScraper(keyword, dest));
        executorService.execute(new WorknetScraper(keyword, dest));
        executorService.execute(new HrScraper(keyword, dest));
        executorService.execute(new ResumeScraper(keyword,dest));
        executorService.execute(new ListScraper(keyword,dest));

        executorService.shutdown();

        while (!executorService.isTerminated()) {

        }
        System.out.println("\nFinished all threads");

        System.out.println("Scraped job size - " + dest.size());

        dest.sort(new WordMatchComparator(keyword));
        long end = System.nanoTime();
        double seconds = (double) (end - start) / 1_000_000_000.0;
        System.out.println("Work end with " + seconds + "  seconds");

        return dest;
    }
}
