package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by sano on 11/11/18.
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    private long id=0;
    public String title;
    private String description;
    private String company;
    private String link;
    private Date postDate;
    private Date deadline;
    private String companyLogo;

    public Job(String title, String description, String link, String companyLogo) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.companyLogo = companyLogo;
    }
}
