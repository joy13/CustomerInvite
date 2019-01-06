package com.intercom.invite.main;
import com.intercom.invite.service.DistanceCalculationService;
import com.intercom.invite.service.InvitationService;
import com.intercom.invite.model.Customer;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DistanceCalculationService distanceService = new DistanceCalculationService();
        InvitationService invitationService = new InvitationService(distanceService);
        List<Customer> invitedCustomers = invitationService.getCustomersToInvite("customers.txt");
        for (Customer invitedCustomer : invitedCustomers) {
            System.out.println("UserId: " + invitedCustomer.getUserId() + " name: " + invitedCustomer.getName());
        }
    }
}

