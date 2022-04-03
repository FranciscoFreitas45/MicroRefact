package pl.szymanski.sharelibrary.response;
 import lombok.AllArgsConstructor;
import lombok.Data;
import pl.szymanski.sharelibrary.entity.Coordinates;
@AllArgsConstructor
@Data
public class CoordinatesResponse {

 private  Long id;

 private  Double latitude;

 private  Double longitude;


public CoordinatesResponse of(Coordinates coordinates){
    return new CoordinatesResponse(coordinates.getId(), coordinates.getLatitude(), coordinates.getLongitude());
}


}