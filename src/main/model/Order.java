package src.main.model;

import java.util.Date;

/**
 * Created by mingshuyu on 2/28/17.
 */
public class Order extends Event {

    private String verb;
    private transient String order_id;
    private String customer_id;
    private String total_amount;

    public Order(String key, Date eventTime, String type){
        super(key, eventTime, type);
        this.order_id = this.getKey();
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String getTotal_amount() {
        return this.total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

}
