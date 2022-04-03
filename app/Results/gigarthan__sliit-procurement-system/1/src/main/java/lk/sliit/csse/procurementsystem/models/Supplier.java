package lk.sliit.csse.procurementsystem.models;
 import lombok.Data;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;
@Entity
@Data
public class Supplier extends User{

 private  String name;

 private  String accountNo;

 private  String phone;

 private  boolean availability;

 private  boolean blackListed;

@ElementCollection
 private  List<Items> items;

 private  Integer rating;


}