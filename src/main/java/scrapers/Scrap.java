package scrapers;

import java.util.List;

/**
 * Created by sano on 12/23/18.
 */
public abstract class Scrap {
    protected String keyword;
    protected List result;

    public Scrap(String keyword, List result) {
        this.keyword = keyword;
        this.result = result;
    }

    public String getKeyword() {
        return keyword;
    }

    public List getResult() {
        return result;
    }
}
