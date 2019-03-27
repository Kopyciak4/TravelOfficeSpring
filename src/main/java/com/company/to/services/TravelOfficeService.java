package com.company.to.services;

import com.company.to.domain.Customer;
import com.company.to.domain.Trip;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public interface TravelOfficeService {

    Customer addCustomer(String name, String street, String zip, String city);

    Trip addAbroadTrip(LocalDate begDate, LocalDate endDate, String destination, int insurence, int price);

    Trip addDomesticTrip(LocalDate begDate, LocalDate endDate, String destination, int discount, int price);

    void assign(Customer customer, Trip trip);

    boolean removeTrip(String destination);

    boolean removeCustomer(String name);

    void showTrips();

    Set<Customer> showCustomers();

    Trip getTripToAssign(String destination);

    Customer getCustomerToAssign(String name);

    //Trip addTrip();
}