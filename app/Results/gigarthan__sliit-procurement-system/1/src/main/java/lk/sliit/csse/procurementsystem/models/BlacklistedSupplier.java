package lk.sliit.csse.procurementsystem.models;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class BlacklistedSupplier {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@OneToOne
 private  Supplier supplier;

 private  String reason;


}