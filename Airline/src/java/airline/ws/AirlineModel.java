package airline.ws;

import airline.ws.Flight;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AirlineModel {

    protected List <Flight> flightsDB;

    public AirlineModel(String dbFilePath) throws IOException{
        // startAirport;destinationAirport;departureDate;arrivalDate;airlineName;availableSeats
        // and add a flight from each line using the split(";") operation
        
        flightsDB = new ArrayList<>();
        AtomicInteger flightId = new AtomicInteger(0);
        URL url = getClass().getResource(dbFilePath);
        
        Files.lines(Paths.get(url.getPath()), StandardCharsets.UTF_8).forEach((line)-> {
                String[] flightInformationArray = line.split(";");
            
                String startAirport = flightInformationArray[0];
                String destinationAirport = flightInformationArray[1];
                String departureDateString[] = flightInformationArray[2].split("-");
                Date departureDate = new GregorianCalendar(Integer.parseInt(departureDateString[0]), Integer.parseInt(departureDateString[1]), Integer.parseInt(departureDateString[2])).getTime();
                String arrivalDateString[] = flightInformationArray[3].split("-");
                Date arrivalDate = new GregorianCalendar(Integer.parseInt(arrivalDateString[0]), Integer.parseInt(arrivalDateString[1]), Integer.parseInt(arrivalDateString[2])).getTime();
                String airlineName = flightInformationArray[4];
                int availableSeats = Integer.parseInt(flightInformationArray[5]);
                
                Flight newFlight = new Flight(flightId.incrementAndGet(), startAirport, destinationAirport, departureDate, arrivalDate, airlineName, availableSeats);
                flightsDB.add(newFlight);
            }
        );             
    }

    public List<FlightInformation> getFlights(String startAirport, String destinationAirport, Date departureDate) {

        List<FlightInformation> result = new ArrayList<>();

        for(Flight flight : flightsDB){
            if(flight.getStartAirport().equals(startAirport) &&
                    flight.getDestinationAirport().equals(destinationAirport) &&
                    flight.getDepartureDate().equals(departureDate) &&
                    flight.getAvailableSeats()>0){
                FlightInformation flightInfo = new FlightInformation(flight);
                result.add(flightInfo);
            }
        }
        return result;
    }
}
