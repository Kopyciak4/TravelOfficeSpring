package com.company.to.services;

import com.company.to.dao.TravelOffice;
import com.company.to.domain.*;
import com.company.to.exceptions.NoSuchCustomerException;
import com.company.to.exceptions.NoSuchTripException;
import com.company.to.web.TravelOfficeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public class TravelOfficeServiceImpl implements TravelOfficeService {

    private TravelOffice travelOffice;


    @Autowired
    public TravelOfficeServiceImpl(TravelOffice travelOffice) {
        this.travelOffice = travelOffice;
    }

    @Override
    public Customer addCustomer(String name, String street, String zip, String city) {
        Address address = new Address(street, zip, city);
        Customer customer = new Customer(name);
        customer.setAddress(address);
        travelOffice.addCustomer(customer);

        return customer;
    }

    @Override
    public Trip addAbroadTrip(Date begDate, Date endDate, String destination, int insurance, int price){
        Trip trip = new AbroadTrip(begDate, endDate, destination);
        trip.setPrice(price);
        System.out.println("ubezpiecznie");
        ((AbroadTrip) trip).setInsurance(insurance);
        travelOffice.addTrip(destination, trip);
        return trip;
    }

    @Override
    public Trip addDomesticTrip(Date begDate, Date endDate, String destination, int discount, int price){
        Trip trip = new DomesticTrip(begDate, endDate, destination);
        trip.setPrice(price);
        System.out.println("znizka");
        ((DomesticTrip) trip).setOwnArrivalDiscount(discount);
        travelOffice.addTrip(destination, trip);
        return trip;
    }

    @Override
    public void assign(String name, String destination) {
        Customer customer = getCustomerToAssign(name);
        Trip trip = getTripToAssign(destination);
        customer.setTrip(trip);
    }

    @Override
    public Customer getCustomerToAssign(String name) {
        Customer customer;
        try {
            customer = travelOffice.findCustomerByName(name);
        } catch (NoSuchCustomerException e) {
          //  TravelOfficeController.log.warning("Brak podanego klienta: " + name);
            return null;
        }
        return customer;

    }

    @Override
    public Trip getTripToAssign(String destination) {
        Trip trip = travelOffice.getTrips().get(destination);
        if(trip == null)
            System.out.println("brak takiej wycieczki");
        return trip;
    }


    @Override
    public boolean removeTrip(String destination) {

        try {
            travelOffice.removeTrip(destination);
        } catch (NoSuchTripException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean removeCustomer(String name) {
        return travelOffice.getCustomers().removeIf(c -> c.getName().equals(name));
    }

    @Override
    public Set<Customer> getCustomers() {
       return travelOffice.getCustomers();
    }

    @Override
    public Collection<Trip> getTrips() {
       return travelOffice.getTrips().values();
    }
}
