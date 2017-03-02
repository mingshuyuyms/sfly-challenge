package src.main.ltvData;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mingshuyu on 3/2/17.
 */
public class LTVDataSet {
    private Map<String, LTVDataObject>  map;
    private Date lastTime;

    public LTVDataSet(Date lastTime) {
        this.map = new HashMap<>();
        this.lastTime = lastTime;
    }

    public Map<String, LTVDataObject> getMap() {
        return map;
    }

    public void setMap(Map<String, LTVDataObject> map) {
        this.map = map;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
