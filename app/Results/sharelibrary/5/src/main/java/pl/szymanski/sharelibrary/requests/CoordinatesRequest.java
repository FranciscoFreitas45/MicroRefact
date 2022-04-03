package pl.szymanski.sharelibrary.requests;
 import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
@Data
public class CoordinatesRequest {

 private  Double latitude;

 private  Double longitude;

@JsonCreator
public CoordinatesRequest(@JsonProperty("latitude") Double latitude, @JsonProperty("longitude") Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
}
}