package io.delivery.net.webservicex;
 import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
public class ClientWeather {

 private  String ADDRESS;


public String Weather(String countryName,String cityName){
    URL url = new URL(ADDRESS);
    QName qName = new QName("http://www.webserviceX.NET", "GlobalWeather");
    Service service = Service.create(url, qName);
    GlobalWeatherSoap hello = service.getPort(GlobalWeatherSoap.class);
    String resultWeather = hello.getWeather(countryName, cityName);
    return resultWeather;
}


public String CitiesByCountry(String countryName){
    URL url = new URL(ADDRESS);
    QName qName = new QName("http://www.webserviceX.NET", "GlobalWeather");
    Service service = Service.create(url, qName);
    GlobalWeatherSoap hello = service.getPort(GlobalWeatherSoap.class);
    String resultCitiesByCountry = hello.getCitiesByCountry(countryName);
    return resultCitiesByCountry;
}


}