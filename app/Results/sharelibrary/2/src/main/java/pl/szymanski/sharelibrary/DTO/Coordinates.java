package pl.szymanski.sharelibrary.DTO;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType.ALL;
public class Coordinates {

 private  Long id;

 private  Double latitude;

 private  Double longitude;

 private  List<Exchange> exchanges;


}