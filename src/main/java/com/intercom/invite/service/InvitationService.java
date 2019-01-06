package com.intercom.invite.service;

import com.intercom.invite.model.Customer;
import com.intercom.invite.model.Point;
import java.util.List;
import java.util.ArrayList;
import com.intercom.invite.Utility;
import java.util.Collections;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InvitationService {
    
    private static final double OFFICE_LATTITUDE = 53.339428;
    private static final double OFFICE_LONGITUDE = -6.257664;
    private static final double INVITE_DISTANCE_KM = 100;
    
    // Given more time, I'd use a dependency injection framework
    private DistanceCalculationService distanceService;
    private ObjectMapper mapper = new ObjectMapper();
    
    public InvitationService(DistanceCalculationService distanceService) {
        this.distanceService = distanceService;
    }
    
    /**
     * Filters an input list of customers to return a subset of customers within 100 km of Intercom's Dublin office.
     */
    public List<Customer> getCustomersToInvite(String filename) {
        List<Customer> customers = getCustomerList(filename);
        List<Customer> invitedCustomers = new ArrayList<>();
        Point officeLocation = new Point(OFFICE_LATTITUDE, OFFICE_LONGITUDE);
        customers.forEach(customer -> {
            Point customerLocation = new Point(Double.parseDouble(customer.getLatitude()), Double.parseDouble(customer.getLongitude()));
            double distance = distanceService.getDistanceInKilometer(officeLocation, customerLocation);
            if(distance <= INVITE_DISTANCE_KM) {
                invitedCustomers.add(customer);
            }
        });
        
        //Now sort based on userId
        Collections.sort(
                invitedCustomers,
                (customer1, customer2) -> customer1.getUserId() - customer2.getUserId());
        
        return invitedCustomers;
    }
    
    private List<Customer> getCustomerList(String filename) {
        List<Customer> customers = new ArrayList<>();
        List<String> lines = Utility.readFile(filename);
        lines.forEach(line -> {
            Customer customer = mapper.readValue(line, Customer.class);
            customers.add(customer);
        });
        return customers;
    }
}
