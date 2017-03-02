package src.main;

import java.io.*;
import java.lang.reflect.Type;

import com.google.gson.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.gson.reflect.TypeToken;
import src.main.ltvData.LTVDataObject;
import src.main.ltvData.LTVDataSet;
import src.main.model.*;


/**
 * Created by mingshuyu on 3/1/17.
 */
public class Manipulate {

    public String fileNameIn;
    public String fileNameOut;
    public Manipulate(String fileNameIn, String fileNameOut){
        this.fileNameIn = fileNameIn;
        this.fileNameOut = fileNameOut;

    }

    public LTVDataSet ingest(Event event, LTVDataSet ltvDataSet){
        if (ltvDataSet == null){
            Date today = new Date();
            ltvDataSet = new LTVDataSet(today);
        }
        Map<String, LTVDataObject> map = ltvDataSet.getMap();
        String eventType = event.getType();
        String customer_id = event.getCustomer_id();
        Date event_time = event.getEventTime();
        if (customer_id != null) {

            //if no customer, add new data object
            if (! map.containsKey(customer_id)){
                LTVDataObject ltvDataObjectNew = new LTVDataObject(customer_id);
                map.put(customer_id,ltvDataObjectNew);
            }
            LTVDataObject ltvDataObject = map.get(customer_id);
            if (event_time.before(ltvDataObject.getFirstTime())){
                ltvDataObject.updateStartTime(event_time);
            } else if (event_time.after(ltvDataSet.getLastTime())){
                ltvDataSet.setLastTime(event_time);
            }

            switch (eventType) {
                case Event.CUSTOMER :
                    ltvDataObject.setLastName(event.getLast_name());
                    break;
                case Event.ORDER :
                    double newExp = trimExpAndToDouble(event.getTotal_amount());
                    ltvDataObject.updateTotalExp(newExp);
                    break;
                case Event.IMAGE :
                    break;
                case Event.SITE_VISIT :
                    ltvDataObject.increaseVisitNum();
                    break;
            }

            ltvDataObject.updateLifeTimeValue(ltvDataSet.getLastTime());
        }
        return ltvDataSet;
    }

    public Date getDateFrom(String dateString){
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date= null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public double trimExpAndToDouble(String str){
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length()-4);
        }
        double exp = Double.parseDouble(str);
        return exp;
    }

    public ArrayList<String> topXSimpleLTVCustomers(int x, LTVDataSet ltvDataSet){
        ArrayList<String> cList = new ArrayList<String>();

        String outputFile = this.fileNameOut;

        Queue<LTVDataObject> pq = new PriorityQueue<>();

        for (Map.Entry<String, LTVDataObject> entry : ltvDataSet.getMap().entrySet()) {
            LTVDataObject ltvDataObject = entry.getValue();
            ltvDataObject.updateLifeTimeValue(ltvDataSet.getLastTime());
            pq.offer(ltvDataObject);
        }
        int index = 1;

        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "utf-8"));
            writer.write("Top " + x + " from input listed here! \n \n");
            while (!pq.isEmpty() && x > 0) {
                x--;
                LTVDataObject top = pq.poll();
                writer.write("Top " + index + "\n");
                writer.write(top.toString() + "\n");
                index++;
            }
            if (x > 0) {
                int customerFound = index - 1;
                writer.write("Only found " + customerFound + " Customers");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {e.printStackTrace();}
        }


        return cList;
    }

    public  LTVDataSet readFromFile() throws FileNotFoundException {

        String fileName = this.fileNameIn;
        LTVDataSet ltvDataSet = new LTVDataSet(new Date());
        Gson gson = new Gson();
        JsonObject[] eventJsons = gson.fromJson(new FileReader(fileName), JsonObject[].class);

        for (int i = 0; i < eventJsons.length; i++){
            String eventType = eventJsons[i].get("type").getAsString();
            String eventTime = eventJsons[i].get("event_time").getAsString();
            Date event_time_date = getDateFrom(eventTime);
            Gson gson1 = new Gson();
            Type type = null;

            switch (eventType) {
                case Event.CUSTOMER :
                    type = new TypeToken<Customer>() {}.getType();
                    Customer customer = gson1.fromJson(eventJsons[i], type);
                    customer.setEventTime(event_time_date);
                    ingest(customer,ltvDataSet);
                    break;
                case Event.ORDER :
                    type = new TypeToken<Order>() {}.getType();
                    Order order = gson1.fromJson(eventJsons[i], type);
                    order.setEventTime(event_time_date);
                    ingest(order,ltvDataSet);
                    break;
                case Event.IMAGE :
                    type = new TypeToken<ImageUpload>() {}.getType();
                    ImageUpload im = gson1.fromJson(eventJsons[i], type);
                    im.setEventTime(event_time_date);
                    ingest(im,ltvDataSet);
                    break;
                case Event.SITE_VISIT :
                    type = new TypeToken<SiteVisit>() {}.getType();
                    SiteVisit sv = gson.fromJson(eventJsons[i], type);
                    sv.setEventTime(event_time_date);
                    ingest(sv,ltvDataSet);
                    break;
            }
        }
        return ltvDataSet;

    }


}
