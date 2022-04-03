package pl.szymanski.sharelibrary.entity;
 import lombok.Data;
import javax.persistence;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import pl.szymanski.sharelibrary.Request.ExchangeRequest;
import pl.szymanski.sharelibrary.Request.Impl.ExchangeRequestImpl;
import pl.szymanski.sharelibrary.DTO.Exchange;
@Entity
@Data
@Table(name = "coordinates")
public class Coordinates {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  Double latitude;

@Column(nullable = false)
 private  Double longitude;

@Transient
 private  List<Exchange> exchanges;

@Transient
 private ExchangeRequest exchangerequest = new ExchangeRequestImpl();;


@Override
public String toString(){
    return "Coordinates{" + "id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + '}';
}


}