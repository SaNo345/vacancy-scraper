package comparator;

import model.Job;

import java.util.Comparator;

/**
 * Created by sano on 12/2/18.
 */
public class WordMatchComparator implements Comparator<Job> {
    private String word = "";

    public WordMatchComparator(String word) {
        this.word = word.toLowerCase();
    }

    @Override
    public int compare(Job t0, Job t1) {
        String titleJob1 = t0.getTitle();
        String titleJob2 = t1.getTitle();

        int pos1 = titleJob1.indexOf(this.word);
        //if keyword in title with Upper Case
        if (pos1 == -1) {
            pos1 = titleJob1.indexOf(this.word.toUpperCase());
            if (pos1 == -1) {
                //ot keyword first letter is uppercase
                String s = this.word.substring(0, 1).toUpperCase() + this.word.substring(1);
                pos1 = titleJob1.indexOf(s);
            }
        }

        int pos2 = titleJob2.indexOf(this.word);

        if (pos2 == -1) {
            pos2 = titleJob2.indexOf(this.word.toUpperCase());
            if (pos2 == -1) {
                String s = this.word.substring(0, 1).toUpperCase() + this.word.substring(1);
                pos2 = titleJob2.indexOf(s);
            }
        }

        if (pos1 == -1 && pos2 == -1) {
            return 0;
        } else if (pos1 == -1) {
            return 1;
        } else if (pos2 == -1) {
            return -1;
        } else if (pos1 < pos2) {
            return -1;
        } else {
            return 1;
        }
    }
}
