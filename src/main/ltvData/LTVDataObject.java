package src.main.ltvData;

import java.util.Date;

/**
 * Created by mingshuyu on 3/2/17.
 */
public class LTVDataObject implements Comparable<LTVDataObject> {

    // Information need to calculate LTV
    private String customerId;
    private Date firstTime;
    private int totalVisitNumber;
    private double totalExp;
    private double lifeTimeValue;
    private String lastName;

    public LTVDataObject (String customerId) {
        this.customerId = customerId;
        this.totalVisitNumber = 0;
        this.totalExp = 0.0;
        this.lifeTimeValue = 0.0;
        this.firstTime = new Date();
    }


    public void updateStartTime(Date newTime) {
        if (firstTime == null || newTime.before(firstTime)) {
            firstTime = newTime;
        }
    }

    public void increaseVisitNum() {
        this.totalVisitNumber++;
    }

    public void updateTotalExp(double exp) {
        this.totalExp+= exp;
    }

    public void updateLifeTimeValue (Date lastTime) {
        if (totalVisitNumber == 0){
            this.lifeTimeValue = this.totalExp;
        } else {
            long timeDiffInMill = lastTime.getTime()  - firstTime.getTime();
            int numOfWeeks = (int)(timeDiffInMill / (1000 * 60 * 60 * 24 * 7)) + 1;
            this.lifeTimeValue = Math.round(100.0 * (this.totalExp / this.totalVisitNumber) *
                    ((double)this.totalVisitNumber / numOfWeeks) * 10 * 52) / 100.0;
        }
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public int getTotalVisitNumber() {
        return totalVisitNumber;
    }

    public void setTotalVisitNumber(int totalVisitNumber) {
        this.totalVisitNumber = totalVisitNumber;
    }

    public double getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(double totalExp) {
        this.totalExp = totalExp;
    }

    public double getLifeTimeValue() {
        return lifeTimeValue;
    }

    public void setLifeTimeValue(double lifeTimeValue) {
        this.lifeTimeValue = lifeTimeValue;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int compareTo(LTVDataObject otherOne) {
        if (otherOne.lifeTimeValue > this.lifeTimeValue) {
            return 1;
        } else if (otherOne.lifeTimeValue < this.lifeTimeValue) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return "Customer Last Name : " + lastName + "\n" +
                "customerId = " + customerId + "\n" +
                "firstTime = " + firstTime  + "\n" +
                "totalVisitNumber = " + totalVisitNumber  + "\n" +
                "totalExp = " + totalExp  + "\n" +
                "lifeTimeValue = " + lifeTimeValue  + "\n" ;
    }
}
