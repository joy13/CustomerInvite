package com.intercom.invite.service;

import org.junit.Test;
import org.junit.Assert;
import com.intercom.invite.model.Point;

public class DistanceCalculationServiceTest {
    
    DistanceCalculationService distanceService = new DistanceCalculationService();
    
    @Test
    public void testGetDistanceInKilometer() {
        Point p1 = new Point(53.339428, -6.257664);
        Point p2 = new Point(53.008769, -6.1056711);
        Assert.assertEquals(38.14, distanceService.getDistanceInKilometer(p1, p2), 0.01);
    }
}
