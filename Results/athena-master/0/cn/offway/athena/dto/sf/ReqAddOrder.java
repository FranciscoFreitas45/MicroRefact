import java.io.Serializable;
public class ReqAddOrder implements Serializable{

 private  String j_contact;

 private  String j_tel;

 private  String j_province;

 private  String j_city;

 private  String j_county;

 private  String j_address;

 private  String d_contact;

 private  String d_tel;

 private  String d_province;

 private  String d_city;

 private  String d_county;

 private  String d_address;

 private  String sendstarttime;

 private  String order_source;

 private  String remark;

 private  String order_id;

 private  String pay_method;


public void setD_contact(String d_contact){
    this.d_contact = d_contact;
}


public String getD_tel(){
    return d_tel;
}


public void setPay_method(String pay_method){
    this.pay_method = pay_method;
}


public void setJ_county(String j_county){
    this.j_county = j_county;
}


public String getJ_address(){
    return j_address;
}


public void setOrder_id(String order_id){
    this.order_id = order_id;
}


public String getOrder_id(){
    return order_id;
}


public void setJ_contact(String j_contact){
    this.j_contact = j_contact;
}


public void setRemark(String remark){
    this.remark = remark;
}


public String getRemark(){
    return remark;
}


public String getD_province(){
    return d_province;
}


public String getSendstarttime(){
    return sendstarttime;
}


public String getJ_contact(){
    return j_contact;
}


public String getD_contact(){
    return d_contact;
}


public void setJ_address(String j_address){
    this.j_address = j_address;
}


public void setSendstarttime(String sendstarttime){
    this.sendstarttime = sendstarttime;
}


public String getOrder_source(){
    return order_source;
}


public void setD_tel(String d_tel){
    this.d_tel = d_tel;
}


public String getJ_province(){
    return j_province;
}


public void setJ_tel(String j_tel){
    this.j_tel = j_tel;
}


public String getJ_tel(){
    return j_tel;
}


public void setJ_province(String j_province){
    this.j_province = j_province;
}


public void setD_city(String d_city){
    this.d_city = d_city;
}


public void setD_province(String d_province){
    this.d_province = d_province;
}


public String getD_address(){
    return d_address;
}


public String getJ_city(){
    return j_city;
}


public void setD_address(String d_address){
    this.d_address = d_address;
}


public void setJ_city(String j_city){
    this.j_city = j_city;
}


public String getD_city(){
    return d_city;
}


public String getD_county(){
    return d_county;
}


public String getPay_method(){
    return pay_method;
}


public String getJ_county(){
    return j_county;
}


public void setD_county(String d_county){
    this.d_county = d_county;
}


public void setOrder_source(String order_source){
    this.order_source = order_source;
}


}