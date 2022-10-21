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
public class Venue {
    
  private  int venueID;
  private String address;
  private  String venuetype;
  private  float venueprice;

    void setVenue(int id,String add,String type,float price)
    {
        venueID=id;
        address=add;
        venuetype=type;
        venueprice=price;
    }
    int getVenueID(){
        return venueID;
    }
    String getVenuetype(){
        return venuetype;
    }
    float getVenueprice(){
        return venueprice;
    }
    String getVenueadd(){
        return address;
    }
}
