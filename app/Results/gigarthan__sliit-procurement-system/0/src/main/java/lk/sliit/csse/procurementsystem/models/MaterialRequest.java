package lk.sliit.csse.procurementsystem.models;
 import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
@Entity
@Data
public class MaterialRequest {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long reqOrderNo;

 private  String orderDate;

 private  Date endDate;

 private  int siteMangerId;

 private  int siteId;


}