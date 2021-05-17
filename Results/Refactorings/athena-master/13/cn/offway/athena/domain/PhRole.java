import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_role")
public class PhRole implements Serializable{

 private  Long id;

 private  Date createdtime;

 private  String name;


public void setName(String name){
    this.name = name;
}


@Column(name = "name", length = 20)
public String getName(){
    return name;
}


public void setCreatedtime(Date createdtime){
    this.createdtime = createdtime;
}


public void setId(Long id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "createdtime")
public Date getCreatedtime(){
    return createdtime;
}


}