package com.intercom.invite.service;

import org.junit.Test;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import com.intercom.invite.model.Customer;

public class InvitationServiceTest {
    
    //Ideally DistanceCalculationService should be mocked with say, Mockito.
    InvitationService invitationService = new InvitationService(new DistanceCalculationService());
    
    @Test(expected = RuntimeException.class)
    public void testFilterCustomersFailsEmptyList() {
        List<Customer> customers = new ArrayList<>();
        invitationService.filterCustomers(customers);        
    }
    
    @Test(expected = RuntimeException.class)
    public void testFilterCustomersWithoutLocation() {
        List<Customer> customers = new ArrayList<>();
        Customer c1 = new Customer(11, "bb", null, "23.3");
        customers.add(c1);
        invitationService.filterCustomers(customers).size();        
    }
    
    // In real production environment there will be more tests to verify that filterCustomers() 
    // returns expected list of customers in sorted order. Similary, getCustomerList will be tested to verify 
    // JSONs are read properly. However, this requires using Mockito or the like and creating mock
    // objects, so skipping that now as it'll require investing more time :)
}
