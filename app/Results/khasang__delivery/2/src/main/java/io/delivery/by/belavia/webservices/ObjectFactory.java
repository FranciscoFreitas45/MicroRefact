package io.delivery.by.belavia.webservices;
 import javax.xml.bind.annotation.XmlRegistry;
@XmlRegistry
public class ObjectFactory {

/**
 * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: by.belavia.webservices
 */
public ObjectFactory() {
}
public GetTimeTable createGetTimeTable(){
    return new GetTimeTable();
}


public Airport createAirport(){
    return new Airport();
}


public GetAirportsList createGetAirportsList(){
    return new GetAirportsList();
}


public ArrayOfFlight createArrayOfFlight(){
    return new ArrayOfFlight();
}


public GetTimeTableResponse createGetTimeTableResponse(){
    return new GetTimeTableResponse();
}


public AirportsResponse createAirportsResponse(){
    return new AirportsResponse();
}


public Flight createFlight(){
    return new Flight();
}


public TimeTableResponce createTimeTableResponce(){
    return new TimeTableResponce();
}


public GetAirportsListResponse createGetAirportsListResponse(){
    return new GetAirportsListResponse();
}


}