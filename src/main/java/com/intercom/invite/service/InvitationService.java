package com.intercom.invite.service;

import com.intercom.invite.model.Customer;
import com.intercom.invite.model.Point;
import java.util.List;
import java.util.ArrayList;
import com.intercom.invite.Utility;
import java.util.Collections;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

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
    
    public List<Customer> getCustomersToInvite(String filename) {
        List<Customer> invitedCustomers = filterCustomers(getCustomerList(filename));
        return invitedCustomers;
    }
    
    /**
     * Filters an input list of customers to return a subset of customers 
     * within 100 km of Intercom's Dublin office, sorted by User ID.
     */
    public List<Customer> filterCustomers(List<Customer> customers) {
        List<Customer> invitedCustomers = new ArrayList<>();
        Point officeLocation = new Point(OFFICE_LATTITUDE, OFFICE_LONGITUDE);
        if(customers.isEmpty()) {
            // Ideally should use custom exceptions for specific errors.
            throw new RuntimeException("Customer list is empty!");
        }
        customers.forEach(customer -> {
            if (customer.getLatitude() != null && customer.getLongitude() != null) {
                Point customerLocation = new Point(Double.parseDouble(customer.getLatitude()), Double.parseDouble(customer.getLongitude()));
                double distance = distanceService.getDistanceInKilometer(officeLocation, customerLocation);
                if(distance <= INVITE_DISTANCE_KM) {
                    invitedCustomers.add(customer);
                }
            } else {
                // Ideally should use custom exceptions for specific errors.
                throw new RuntimeException("Latitude or Longitude is null for userId: " + customer.getUserId());
            }
        });
        //Now sort based on userId
        Collections.sort(
                invitedCustomers,
                (customer1, customer2) -> customer1.getUserId() - customer2.getUserId());
        
        return invitedCustomers;
    }
    
    /**
     * Helper method to deserialize JSON into Customer objects.
     */
    private List<Customer> getCustomerList(String filename) {
        List<Customer> customers = new ArrayList<>();
        List<String> lines = Utility.readFile(filename);
        try {
            for(String line: lines) {
                Customer customer = mapper.readValue(line, Customer.class);
                customers.add(customer);
            } 
        } catch(IOException ex) {
            System.out.println("Error reading JSON to object"); 
            ex.printStackTrace();
        }

        return customers;
    }
}
