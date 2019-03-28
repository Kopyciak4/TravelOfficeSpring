package com.company.to.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class AbroadTrip extends Trip implements Serializable {

    private int insurance;

    public AbroadTrip() {}

    public AbroadTrip(Date start, Date end, String destination) {
        setStart(start);
        setEnd(end);
        setDestination(destination);
    }


    public int getPrice(){
        return super.getPrice() + getInsurance();
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }
}
