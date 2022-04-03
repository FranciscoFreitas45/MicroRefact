package pl.szymanski.sharelibrary.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Data
public class Coordinates {

   
    private Long id;
   
    private Double latitude;
  
    private Double longitude;

   
    private List<Exchange> exchanges;

    @Override
    public String toString() {
        return "Coordinates{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
