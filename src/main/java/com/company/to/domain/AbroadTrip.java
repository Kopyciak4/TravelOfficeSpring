package com.company.to.domain;

import java.time.LocalDate;
import java.util.Date;

public class AbroadTrip extends Trip {

    private int insurance;

    public AbroadTrip(Date start, Date end, String destination) {
        setStart(start);
        setEnd(end);
        setDestination(destination);

    }


    public int getPrice(){
        return super.getPrice() + getInsurence();
    }

    public int getInsurence() {
        return insurance;
    }

    public void setInsurence(int insurence) {
        this.insurance = insurance;
    }
}
