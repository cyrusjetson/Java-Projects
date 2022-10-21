/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package venue.booking;

/**
 *
 * @author user
 */
public class Booking extends Customer{

    private int venID;

    private String date;

    void setBooking( int vid,String dt)
    {
        
        venID=vid;
 
        date=dt;
    }

    int getVenueID()
    {
        return venID;   
    }
 
    String getBookingdate()
    {
        return date;   
    }
    
}
