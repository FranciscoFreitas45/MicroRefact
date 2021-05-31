import javax.persistence;
@Entity
@Table(name = "tbl_lvm_sumup")
public class LvmSumUp {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
 private  int id;

@Column(name = "name")
 private  String name;

@Column(name = "name_sd")
 private  String nameSd;

@Column(name = "is_used")
 private  String isUsed;

@Column(name = "ispermanent")
 private  int ispermanent;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getNameSd(){
    return nameSd;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public int getIspermanent(){
    return ispermanent;
}


public void setId(int id){
    this.id = id;
}


public int getId(){
    return id;
}


@Override
public String toString(){
    return "LvmSumUp [id=" + id + ", name=" + name + ", nameSd=" + nameSd + ", isUsed=" + isUsed + ", ispermanent=" + ispermanent + "]";
}


public String getIsUsed(){
    return isUsed;
}


public void setIspermanent(int ispermanent){
    this.ispermanent = ispermanent;
}


public void setIsUsed(String isUsed){
    this.isUsed = isUsed;
}


}