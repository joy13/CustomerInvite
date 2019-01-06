package com.intercom.invite.service;

import com.intercom.invite.model.Point;

/**
 * Returns distance between two points based on the first formula from https://en.wikipedia.org/wiki/Great-circle_distance
 */
public class DistanceCalculationService {
    
    private static final int EARTH_RADIUS_KILOMETER = 6371;
    
    public double getDistanceInKilometer(Point point1, Point point2) {
        
        double latitude1 = convertToRadian(point1.getLatitude());
        double latitude2 = convertToRadian(point2.getLatitude());
        
        double longitude1 = convertToRadian(point1.getLongitude());
        double longitude2 = convertToRadian(point2.getLongitude());
        
        double sinePhi1 = Math.sin(latitude1);
        double sinePhi2 = Math.sin(latitude2);
        
        double cosPhi1 = Math.cos(latitude1);
        double cosPhi2 = Math.cos(latitude2);
        
        double cosDeltaLambda = Math.cos(Math.abs(longitude1 - longitude2));
        
        double deltaSigma = Math.acos(sinePhi1*sinePhi2 + cosPhi1*cosPhi2*cosDeltaLambda);
        
        return deltaSigma*EARTH_RADIUS_KILOMETER;
    }
    
    private double convertToRadian(double degrees) {
        return degrees*(Math.PI/180);
    }
}
