package com.company.to.web;

import com.company.to.domain.AbroadTrip;
import com.company.to.domain.Customer;
import com.company.to.domain.Trip;
import com.company.to.services.TravelOfficeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class TravelOfficeControllerTest {

    private ObjectMapper mapper = new ObjectMapper();
    private Customer cl;
    private Trip tripD, tripA;


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TravelOfficeService travelOfficeService;

    @Before
    public void createObjects() {
        if (cl == null) {
            cl = travelOfficeService.addCustomer("bob", "wall", "213-12", "btm");
            tripA = travelOfficeService.addAbroadTrip(new Date(1234567890), new Date(2001, 05,02), "kongo", 500, 5000);
            tripD = travelOfficeService.addDomesticTrip(new Date(1234567890), new Date(2002, 05,02), "bankok", 500, 6500);
        }


    }

    @Test
    public void shouldReturnUserByName() throws Exception {
      String s1 = this.mockMvc.perform(get("/getCustomerToAssign").param("name", "bob"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
      String s2 = mapper.writeValueAsString(cl);
      assertEquals(s1 ,s2);
       // assertEquals(cl, mapper.readValue(s1, Customer.class));
    }

    @Test
    public void shouldReturnAbroadTripByDescription() throws Exception {
        String s1 = this.mockMvc.perform(get("/getTripToAssign").param("destination", "kongo"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Trip trip =  mapper.readValue(s1, AbroadTrip.class);
        assertEquals(tripA.getDestination(), trip.getDestination());
        assertEquals(tripA.getEnd(), trip.getEnd());
        assertEquals(tripA.getStart(), trip.getStart());
        assertEquals(tripA.getPrice(), trip.getPrice() - ((AbroadTrip) trip).getInsurance());
    }

}