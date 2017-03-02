package src.main.model;


import java.util.Date;
import java.util.Map;

/**
 * Created by mingshuyu on 2/28/17.
 */
public class SiteVisit extends Event {
    private String verb;
    private String customer_id;
    private transient String page_id;
    private Map<String, String> tags;

    public SiteVisit(String key, Date eventTime, String type) {
        super(key, eventTime, type);
        this.page_id = this.getKey();
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
