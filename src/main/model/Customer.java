package src.main.model;

import java.util.Date;

/**
 * Created by mingshuyu on 2/28/17.
 */
public class Customer extends Event{

    private String verb;
    private String last_name;
    private String adr_city;
    private String adr_state;
    private transient String customer_id;

    public Customer(String key, Date eventTime, String type) {
        super(key,eventTime, type);
        this.customer_id = this.getKey();
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAdr_city() {
        return adr_city;
    }

    public void setAdr_city(String adr_city) {
        this.adr_city = adr_city;
    }

    public String getAdr_state() {
        return adr_state;
    }

    public void setAdr_state(String adr_state) {
        this.adr_state = adr_state;
    }

    @Override
    public String getCustomer_id() { return getKey(); }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }


}
