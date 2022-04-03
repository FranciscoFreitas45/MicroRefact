package guru.springframework.domain;
 import java.util.List;
import javax.persistence;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "product")
public class Product {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Integer id;

@Version
 private  Integer version;

@Column(name = "mode")
 public  String mode;

@Column(name = "status")
 public  String status;

@Column(name = "user_name")
 public  String user;

@Column(name = "prepared")
 public  String prepared;

@Column(name = "rati")
 public  String rati;

@Column(name = "autho")
 public  String autho;

@Column(name = "gp_no")
 public  String gp_no;

@Column(name = "gp_date")
@DateTimeFormat(pattern = "dd/MM/yyyy")
 public  String gp_date;

@Column(name = "last_no")
 public  String last_no;

@Column(name = "dept")
 public  String dept;

@Column(name = "from_unit")
 public  String from_unit;

@Column(name = "no_of_items")
 public  String no_of_items;

@Column(name = "fetching")
 public  String fetch;

@Column(name = "G_status")
 public  String G_status;

@Column(name = "due_date")
 public  String due_date;

@Column(name = "trans_type")
 public  String Trans_type;

@Column(name = "vehicle_no")
 public  String vehicle_no;

@Column(name = "materialname")
 public  String materialname;

@Column(name = "packagetype")
 public  String packagetype;

@Column(name = "quantity")
 public  String quantity;

@Column(name = "grosswt")
 public  String grosswt;

@Column(name = "netwt")
 public  String netwt;

@Column(name = "purpose")
 public  String purpose;

@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
@Transient
 private  List<GateTransportDetails> gtt;


public void setFetch(String fetch){
    this.fetch = fetch;
}


public String getTrans_type(){
    return Trans_type;
}


public String getStatus(){
    return status;
}


public void setVehicle_no(String vehicle_no){
    this.vehicle_no = vehicle_no;
}


public void setDept(String dept){
    this.dept = dept;
}


public void setNetwt(String netwt){
    this.netwt = netwt;
}


public void setId(Integer id){
    this.id = id;
}


public void setMaterialname(String materialname){
    this.materialname = materialname;
}


public String getMode(){
    return mode;
}


public void setUser(String user){
    this.user = user;
}


public void setGp_no(String gp_no){
    this.gp_no = gp_no;
}


public void setDue_date(String due_date){
    this.due_date = due_date;
}


public String getGrosswt(){
    return grosswt;
}


public void setVersion(Integer version){
    this.version = version;
}


public void setG_status(String G_status){
    this.dept = G_status;
}


public String getPrepared(){
    return prepared;
}


public void setFrom_unit(String from_unit){
    this.from_unit = from_unit;
}


public void setRati(String rati){
    this.rati = rati;
}


public String getLast_no(){
    return last_no;
}


public String getNo_of_items(){
    return no_of_items;
}


public void setLast_no(String last_no){
    this.last_no = last_no;
}


public String getAutho(){
    return autho;
}


public void setAutho(String autho){
    this.autho = autho;
}


public String getVehicle_no(){
    return vehicle_no;
}


public String getPurpose(){
    return purpose;
}


public String getQuantity(){
    return quantity;
}


public String getDue_date(){
    return due_date;
}


public String getDept(){
    return dept;
}


public void setPurpose(String purpose){
    this.purpose = purpose;
}


public String getUser(){
    return user;
}


public void setPackagetype(String packagetype){
    this.packagetype = packagetype;
}


public Integer getId(){
    return id;
}


public String getMaterialname(){
    return materialname;
}


public String getPackagetype(){
    return packagetype;
}


public String getGp_no(){
    return gp_no;
}


public String getRati(){
    return rati;
}


public String getNetwt(){
    return netwt;
}


public void setTrans_type(String trans_type){
    Trans_type = trans_type;
}


public Integer getVersion(){
    return version;
}


public void setNo_of_items(String no_of_items){
    this.no_of_items = no_of_items;
}


public void setGrosswt(String grosswt){
    this.grosswt = grosswt;
}


public void setGtt(List<GateTransportDetails> gtt){
    this.gtt = gtt;
}


public void setMode(String mode){
    this.mode = mode;
}


public void setGp_date(String gp_date){
    this.gp_date = gp_date;
}


public String getFetch(){
    return fetch;
}


public void setStatus(String status){
    this.status = status;
}


public void setPrepared(String prepared){
    this.prepared = prepared;
}


public String getG_status(){
    return dept;
}


public void setQuantity(String quantity){
    this.quantity = quantity;
}


public List<GateTransportDetails> getGtt(){
    return gtt;
}


public String getGp_date(){
    return gp_date;
}


public String getFrom_unit(){
    return from_unit;
}


}