package com.company.to.web;


import com.company.to.domain.Customer;
import com.company.to.domain.Trip;
import com.company.to.services.TravelOfficeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@RestController
public class TravelOfficeController {

    TravelOfficeService travelOfficeService;

    @Autowired
    public TravelOfficeController(TravelOfficeService travelOfficeService) {
        this.travelOfficeService = travelOfficeService;
    }

    @GetMapping("/getTrips")
    public Collection<Trip> getTrips() {
        return travelOfficeService.getTrips();
    }

    @GetMapping("/getCustomers")
    public Set<Customer> getCustomers() {
        return travelOfficeService.getCustomers();
    }

    @PostMapping("/createCustomer")
    public Customer createCutomer(@RequestParam String name, @RequestParam String street, @RequestParam String zip, @RequestParam String city) {
        return travelOfficeService.addCustomer(name, street, zip, city);
    }

    @PostMapping("/createDomesticTrip")
    public Trip createDomesticTrip(@RequestParam Date begDate, @RequestParam Date endDate, @RequestParam String destination, @RequestParam int discount, @RequestParam int price) {
        return travelOfficeService.addDomesticTrip(begDate, endDate, destination, discount, price);
    }

    @PostMapping("/createAbroadTrip")
    public Trip createAbroadTrip(@RequestParam Date begDate, @RequestParam Date endDate, @RequestParam String destination, @RequestParam int insurance, @RequestParam int price) {
        return travelOfficeService.addAbroadTrip(begDate, endDate, destination, insurance, price);
    }

    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(@RequestParam String name) {
        travelOfficeService.removeCustomer(name);
    }

    @DeleteMapping("/deleteTrip")
    public void deleteTrip(@RequestParam String destination) {
        travelOfficeService.removeTrip(destination);
    }

    @GetMapping("/getTripToAssign")
    public Trip getTripToAssign(@RequestParam String destination){
        return travelOfficeService.getTripToAssign(destination);
    }

    @GetMapping("/getCustomerToAssign")
    public Customer getCustomerToAssign(@RequestParam String name){
        return travelOfficeService.getCustomerToAssign(name);
    }

    @PutMapping("/assign")
    public void assignTrip(@RequestParam String name, @RequestParam String destination) {
        travelOfficeService.assign(name, destination);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }

}
