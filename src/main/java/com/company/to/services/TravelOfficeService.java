package com.company.to.services;

import com.company.to.domain.Customer;
import com.company.to.domain.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Service
public interface TravelOfficeService {

    Customer addCustomer(String name, String street, String zip, String city);

    Trip addAbroadTrip(Date begDate, Date endDate, String destination, int insurance, int price);

    Trip addDomesticTrip(Date begDate, Date endDate, String destination, int discount, int price);

    void assign(String name , String destination);

    boolean removeTrip(String destination);

    boolean removeCustomer(String name);

    Collection<Trip> getTrips();

    Set<Customer> getCustomers();

    Trip getTripToAssign(String destination);

    Customer getCustomerToAssign(String name);

    //Trip addTrip();
}