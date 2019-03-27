package com.company.to.web;


import com.company.to.domain.Customer;
import com.company.to.domain.Trip;
import com.company.to.services.TravelOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@RestController
@EnableSwagger2
public class TravelOfficeController {

    TravelOfficeService travelOfficeService;

    @Autowired
    public TravelOfficeController(TravelOfficeService travelOfficeService) {
        this.travelOfficeService = travelOfficeService;
    }

    @GetMapping("/showTravel")
    public void getTrips() {
        this.travelOfficeService.showTrips();
    }

    @GetMapping("/showCustomer")
    public void getCustomers() {
        this.travelOfficeService.showCustomers();
    }

    @PostMapping("/postCustomer")
    public Customer postCutomer(String name, String street, String zip, String city) {
        return travelOfficeService.addCustomer(name, street, zip, city);
    }

    @PostMapping("/postDTrip")
    public Trip postDomesticTrip(LocalDate begDate, LocalDate endDate, String destination, int discount, int price) {
        return travelOfficeService.addDomesticTrip(begDate, endDate, destination, discount, price);
    }

    @PostMapping("/postATrip")
    public Trip postAbroadTrip(LocalDate begDate, LocalDate endDate, String destination, int insurence, int price) {
        return travelOfficeService.addAbroadTrip(begDate, endDate, destination, insurence, price);
    }

    @DeleteMapping("/deleteCustomer")
    public void deleteCustomer(String name) {
        travelOfficeService.removeCustomer(name);
    }

    @DeleteMapping("/deleteTrip")
    public void deletTrip(String destination) {
        travelOfficeService.removeTrip(destination);
    }

    @GetMapping("/tripToAssign")
    public Trip getTripToAssign(String destination){
        return travelOfficeService.getTripToAssign(destination);
    }

    @GetMapping("/custmerToAssign")
    public Customer getCustomerToAssign(String name){
        return travelOfficeService.getCustomerToAssign(name);
    }

    @PutMapping("/assing")
    public void assignTrip(Customer customer, Trip trip) {
        travelOfficeService.assign(customer, trip);
    }





    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /*
	 http://localhost:8080//v2/api-docs
	 http://localhost:8080/swagger-ui.html
	  */

}
