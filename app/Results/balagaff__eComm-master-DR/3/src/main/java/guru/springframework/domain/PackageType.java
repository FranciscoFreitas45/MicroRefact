package guru.springframework.domain;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "package_type")
public class PackageType {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "ID")
 private  int id;

@Column(name = "package_type")
 private  String packagetype;


public String getPackagetype(){
    return packagetype;
}


public void setId(int id){
    this.id = id;
}


public void setPackagetype(String packagetype){
    this.packagetype = packagetype;
}


public int getId(){
    return id;
}


}