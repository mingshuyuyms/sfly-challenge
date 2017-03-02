package src.main.model;

import java.util.Date;

/**
 * Created by mingshuyu on 2/28/17.
 */
public class ImageUpload extends Event{
    private String verb;
    private String image_id;
    private String customer_id;
    private String camera_make;
    private String camera_model;

    public ImageUpload(String key, Date eventTime, String type) {
        super(key, eventTime, type);
        this.image_id = this.getKey();
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    @Override
    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCamera_make() {
        return camera_make;
    }

    public void setCamera_make(String camera_make) {
        this.camera_make = camera_make;
    }

    public String getCamera_model() {
        return camera_model;
    }

    public void setCamera_model(String camera_model) {
        this.camera_model = camera_model;
    }


}
