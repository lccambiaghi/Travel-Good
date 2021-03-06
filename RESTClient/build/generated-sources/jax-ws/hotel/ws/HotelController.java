
package hotel.ws;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import dk.dtu.imm.fastmoney.HotelInformation;
import dk.dtu.imm.fastmoney.types.CreditCardInfoType;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HotelController", targetNamespace = "http://ws.hotel/")
@XmlSeeAlso({
    dk.dtu.imm.fastmoney.types.ObjectFactory.class,
    dk.dtu.imm.fastmoney.ObjectFactory.class,
    hotel.ws.ObjectFactory.class
})
public interface HotelController {


    /**
     * 
     * @param city
     * @param departureDate
     * @param arrivalDate
     * @return
     *     returns java.util.List<dk.dtu.imm.fastmoney.HotelInformation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getHotels", targetNamespace = "http://ws.hotel/", className = "hotel.ws.GetHotels")
    @ResponseWrapper(localName = "getHotelsResponse", targetNamespace = "http://ws.hotel/", className = "hotel.ws.GetHotelsResponse")
    @Action(input = "http://ws.hotel/HotelController/getHotelsRequest", output = "http://ws.hotel/HotelController/getHotelsResponse")
    public List<HotelInformation> getHotels(
        @WebParam(name = "city", targetNamespace = "")
        String city,
        @WebParam(name = "arrivalDate", targetNamespace = "")
        Object arrivalDate,
        @WebParam(name = "departureDate", targetNamespace = "")
        Object departureDate);

    /**
     * 
     * @param creditCardInformation
     * @param bookingNumber
     * @return
     *     returns boolean
     * @throws HotelBookException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "bookHotel", targetNamespace = "http://ws.hotel/", className = "hotel.ws.BookHotel")
    @ResponseWrapper(localName = "bookHotelResponse", targetNamespace = "http://ws.hotel/", className = "hotel.ws.BookHotelResponse")
    @Action(input = "http://ws.hotel/HotelController/bookHotelRequest", output = "http://ws.hotel/HotelController/bookHotelResponse", fault = {
        @FaultAction(className = HotelBookException_Exception.class, value = "http://ws.hotel/HotelController/bookHotel/Fault/HotelBookException")
    })
    public boolean bookHotel(
        @WebParam(name = "bookingNumber", targetNamespace = "")
        int bookingNumber,
        @WebParam(name = "CreditCardInformation", targetNamespace = "")
        CreditCardInfoType creditCardInformation)
        throws HotelBookException_Exception
    ;

    /**
     * 
     * @param bookingNumber
     * @throws HotelCancelException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "cancelHotel", targetNamespace = "http://ws.hotel/", className = "hotel.ws.CancelHotel")
    @ResponseWrapper(localName = "cancelHotelResponse", targetNamespace = "http://ws.hotel/", className = "hotel.ws.CancelHotelResponse")
    @Action(input = "http://ws.hotel/HotelController/cancelHotelRequest", output = "http://ws.hotel/HotelController/cancelHotelResponse", fault = {
        @FaultAction(className = HotelCancelException_Exception.class, value = "http://ws.hotel/HotelController/cancelHotel/Fault/HotelCancelException")
    })
    public void cancelHotel(
        @WebParam(name = "bookingNumber", targetNamespace = "")
        int bookingNumber)
        throws HotelCancelException_Exception
    ;

}
