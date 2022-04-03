package pl.szymanski.sharelibrary.utils.generator;
 import pl.szymanski.sharelibrary.entity.Coordinates;
import pl.szymanski.sharelibrary.requests.CoordinatesRequest;
import pl.szymanski.sharelibrary.utils.constant.CoordinatesConstant;
import java.util.ArrayList;
public class CoordinatesGenerator {


public Coordinates getCoordinates(){
    Coordinates coordinates = new Coordinates();
    coordinates.setLatitude(CoordinatesConstant.TEST_LATITUDE);
    coordinates.setLongitude(CoordinatesConstant.TEST_LONGITUDE);
    coordinates.setExchanges(new ArrayList<>());
    return coordinates;
}


public CoordinatesRequest getCoordinatesRequest(){
    return new CoordinatesRequest(CoordinatesConstant.TEST_LATITUDE, CoordinatesConstant.TEST_LONGITUDE);
}


}