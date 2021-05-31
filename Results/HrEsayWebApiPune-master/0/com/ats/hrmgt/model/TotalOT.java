import javax.persistence;
@Entity
public class TotalOT {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  String id;

@Column(name = "depart_id")
 private  int departId;

@Column(name = "month")
 private  String month;

@Column(name = "ot")
 private  float ot;

@Column(name = "date_mo")
 private  String dateMo;

@Column(name = "name")
 private  String name;


public void setName(String name){
    this.name = name;
}


public float getOt(){
    return ot;
}


public void setMonth(String month){
    this.month = month;
}


public String getName(){
    return name;
}


public void setOt(float ot){
    this.ot = ot;
}


public String getId(){
    return id;
}


public void setDateMo(String dateMo){
    this.dateMo = dateMo;
}


public String getDateMo(){
    return dateMo;
}


public int getDepartId(){
    return departId;
}


public void setId(String id){
    this.id = id;
}


public void setDepartId(int departId){
    this.departId = departId;
}


@Override
public String toString(){
    return "TotalOT [id=" + id + ", departId=" + departId + ", month=" + month + ", ot=" + ot + ", dateMo=" + dateMo + ", name=" + name + "]";
}


public String getMonth(){
    return month;
}


}