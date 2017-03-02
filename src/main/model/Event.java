package src.main.model;

import java.util.Date;

/**
 * Created by mingshuyu on 2/28/17.
 */
public abstract class Event {

    public static final String CUSTOMER = "CUSTOMER";
    public static final String SITE_VISIT = "SITE_VISIT";
    public static final String IMAGE = "IMAGE";
    public static final String ORDER = "ORDER";
    private String key;
    private Date eventTime;
    private String type;

    public Event(String key, Date eventTime, String type){
        this.key = key;
        this.eventTime = eventTime;
        this.type = type;
    }
    public Event(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer_id(){
        return null;
    }

    public String getTotal_amount() {
        return "0.0 USD";
    }

    public String getLast_name() { return "no record now"; }

}
