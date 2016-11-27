/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs;

import dk.dtu.imm.fastmoney.HotelInformation;
import static java.lang.String.valueOf;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lucacambiaghi
 */
public class TestItineraryResource {
    
    Client client = ClientBuilder.newClient(); 
    WebTarget itTarget = client.target("http://localhost:8080/ws.pr/webresources/itineraries");
    WebTarget hotTarget = client.target("http://localhost:8080/ws.pr/webresources/hotels");
    
    @Before
    public void reset_itineraries(){
        itTarget.path("/reset")
                .request()
                .put(Entity.entity("reset", "application/json"));
    }
    
    @Test
    public void should_create_itinerary(){
        Itinerary newItinerary = itTarget
                    .request()
                    .accept("application/json")
                    .put(Entity.entity(new Itinerary(), "application/json"), Itinerary.class);
        assertNotNull(newItinerary);
    }
    
    @Test
    public void should_add_hotel(){
        Itinerary newItinerary = itTarget
                    .request()
                    .accept("application/json")
                    .put(Entity.entity(new Itinerary(), "application/json"), Itinerary.class);
        
        String itineraryID = newItinerary.getID();
        
        List<HotelInformation> hotels = hotTarget.path("/Copenhagen")
                    .queryParam("start", "25-11-2016")
                    .queryParam("end", "31-11-2016")
                    .request("application/json")
                    .accept("application/json")
                    .get(new GenericType<List<HotelInformation>>() {});
        
        HotelInformation hotel = hotels.get(0);
        String hotelID = valueOf(hotel.getBookingNumber());
        
        Itinerary updated = itTarget.path("/" + itineraryID + "/hotels/" + hotelID)
                    .request()
                    .accept("application/json")
                    .put(Entity.entity(new Itinerary(), "application/json"), Itinerary.class);
        
        assert(updated.getHotels().size()==1);
        
    }
    
}
