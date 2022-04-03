package io.delivery.controller;
 import io.delivery.by.belavia.webservices.ClientBelaviaAirlines;
import io.delivery.net.webservicex.ClientWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.xml.soap.SOAPException;
import java.io.IOException;
@Controller
@RequestMapping("/soap")
public class SoapController {

 final  ClientBelaviaAirlines clientBelaviaAirlines;

 final  ClientWeather clientWeather;

@Autowired
public SoapController(ClientBelaviaAirlines clientBelaviaAirlines, ClientWeather clientWeather) {
    this.clientBelaviaAirlines = clientBelaviaAirlines;
    this.clientWeather = clientWeather;
}
@RequestMapping(value = { "/globalweather/cities/{countryName}" }, method = RequestMethod.GET)
public ModelAndView findCitiesByCountry(String countryName){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("cities");
    modelAndView.addObject("cities", clientWeather.CitiesByCountry(countryName));
    return modelAndView;
}


@RequestMapping(value = { "/belavia/airports/{language}" }, method = RequestMethod.GET)
public ModelAndView getAirportsList(String language){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("airports");
    try {
        modelAndView.addObject("airports", clientBelaviaAirlines.getListOfAirports(language));
    } catch (IllegalArgumentException e) {
        modelAndView.addObject("airportIllegalExc", e.getMessage());
    } catch (SOAPException e) {
        modelAndView.addObject("airportSoapExc", e.getMessage());
    } catch (IOException e) {
        modelAndView.addObject("airportIoExc", e.getMessage());
    }
    return modelAndView;
}


@RequestMapping(value = { "/globalweather/weather/{countryName}/{cityName}" }, method = RequestMethod.GET)
public ModelAndView findWeather(String countryName,String cityName){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("weather");
    modelAndView.addObject("weather", clientWeather.Weather(countryName, cityName));
    return modelAndView;
}


@RequestMapping(value = { "/belavia/timetable" }, method = RequestMethod.GET)
public ModelAndView getListOfFlights(String airport,String type,String date){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("timetable");
    try {
        modelAndView.addObject("timetable", clientBelaviaAirlines.getListOfFlights(airport, type, date));
    } catch (IllegalArgumentException e) {
        modelAndView.addObject("timetableIllegalExc", e.getMessage());
    } catch (SOAPException e) {
        modelAndView.addObject("timetableSoapExc", e.getMessage());
    } catch (IOException e) {
        modelAndView.addObject("timetableIoExc", e.getMessage());
    }
    return modelAndView;
}


}