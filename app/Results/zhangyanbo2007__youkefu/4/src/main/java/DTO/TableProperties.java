package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
public class TableProperties implements Cloneable{

 private  long serialVersionUID;

 private  String id;

 private  String tablename;

 private  String dbtableid;

 private  String name;

 private  String code;

 private  String fieldname;

 private  int datatypecode;

 private  String datatypename;

 private  String indexdatatype;

 private  String groupid;

 private  String userid;

 private  boolean pk;

 private  boolean modits;

 private  String orgi;

 private  String viewtype;

 private  int sortindex;

 private  boolean token;

 private  String tokentype;

 private  boolean inx;

 private  boolean title;

 private  boolean systemfield;

 private  int length;

 private  boolean fieldstatus;

 private  boolean secfield;

 private  String secdistype;

 private  boolean phonenumber;

 private  String phonetype;

 private  String phonememo;

 private  String styletype;

 private  boolean seldata;

 private  String seldatatype;

 private  String seldatacode;

 private  String seldatakey;

 private  String seldatavalue;

 private  String reftbid;

 private  String reftbname;

 private  String reftpid;

 private  String reftpname;

 private  String reftype;

 private  String reftptitlefield;

 private  boolean defaultsort;

 private  boolean descorder;

 private  String defaultvalue;

 private  String defaultvaluetitle;

 private  String defaultfieldvalue;

 private  boolean multpartfile;

 private  String uploadtype;

 private  String cascadetype;

 private  boolean impfield;

 private  boolean reffk;

 private  boolean privatefield;

 private  boolean sysfield;

 private  String plugin;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public TableProperties() {
}public TableProperties(String fieldname, String datatypename, int datatypecode, String tablename) {
    this(fieldname, datatypename, datatypecode, tablename, null, null, false);
}public TableProperties(String fieldname, String datatypename, int datatypecode, String tablename, String orgi, String tableid, boolean fieldstatus) {
    this(fieldname, fieldname, datatypename, datatypecode, tablename, orgi, tableid, fieldstatus);
}public TableProperties(String fieldname, String datatypename, int datatypecode, String tablename, String orgi, String tableid, boolean fieldstatus, boolean token, String tokentype) {
    this(fieldname, fieldname, datatypename, datatypecode, tablename, orgi, tableid, fieldstatus);
    this.token = token;
    this.tokentype = tokentype;
}public TableProperties(String title, String fieldname, String datatypename, int datatypecode, String tablename, String orgi, String tableid, boolean fieldstatus) {
    if (fieldname != null) {
        fieldname = fieldname.toLowerCase();
    }
    if (tablename != null) {
        tablename = tablename.toLowerCase();
    }
    this.fieldname = fieldname;
    this.name = title;
    this.datatypecode = datatypecode;
    this.datatypename = datatypename;
    this.tablename = tablename;
    this.dbtableid = tableid;
    this.fieldstatus = fieldstatus;
    this.orgi = orgi;
}
public boolean isPrivatefield(){
    return privatefield;
}


public boolean isFieldstatus(){
    return fieldstatus;
}


public String getSeldatatype(){
    return seldatatype != null ? seldatatype : "";
}


public String getDatatypename(){
    return datatypename;
}


public String getTokentype(){
    return tokentype;
}


public void setDescorder(boolean descorder){
    this.descorder = descorder;
}


public String getCode(){
    return code;
}


public boolean isDefaultsort(){
    return defaultsort;
}


public String getDefaultvaluetitle(){
    return defaultvaluetitle;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public String getDefaultvalue(){
    return defaultvalue;
}


public String getCascadetype(){
    return cascadetype;
}


public String getFieldname(){
    return fieldname != null ? fieldname.toLowerCase() : null;
}


public boolean isTitle(){
    return title;
}


public void setName(String name){
    this.name = name;
}


public void setSecdistype(String secdistype){
    this.secdistype = secdistype;
}


public void setUploadtype(String uploadtype){
    this.uploadtype = uploadtype;
}


public void setTokentype(String tokentype){
    this.tokentype = tokentype;
}


public String getTablename(){
    return tablename;
}


public void setSeldatacode(String seldatacode){
    this.seldatacode = seldatacode;
}


public void setReftbname(String reftbname){
    this.reftbname = reftbname;
}


public String getUserid(){
    return userid;
}


public String getUploadtype(){
    return uploadtype;
}


public String getReftbid(){
    return reftbid;
}


public void setSeldata(boolean seldata){
    this.seldata = seldata;
}


public String getReftpname(){
    return reftpname;
}


public void setSystemfield(boolean systemfield){
    this.systemfield = systemfield;
}


public void setToken(boolean token){
    this.token = token;
}


public String getPlugin(){
    return plugin;
}


public void setReftype(String reftype){
    this.reftype = reftype;
}


public int getSortindex(){
    return sortindex;
}


public String getName(){
    return name;
}


public String getIndexdatatype(){
    return indexdatatype;
}


public String getSeldatakey(){
    return seldatakey;
}


public String getReftbname(){
    return reftbname;
}


public String getReftype(){
    return reftype;
}


public void setPrivatefield(boolean privatefield){
    this.privatefield = privatefield;
}


public void setMultpartfile(boolean multpartfile){
    this.multpartfile = multpartfile;
}


public String getDefaultfieldvalue(){
    return defaultfieldvalue;
}


@Transient
public String getKey(){
    return UKTools.genIDByKey(this.id);
}


public void setDefaultsort(boolean defaultsort){
    this.defaultsort = defaultsort;
}


public String getStyletype(){
    return styletype;
}


public void setTitle(boolean title){
    this.title = title;
}


public int getDatatypecode(){
    return datatypecode;
}


public String getSeldatacode(){
    return seldatacode;
}


public String getSecdistype(){
    return secdistype;
}


public String getDbtableid(){
    return dbtableid;
}


public void setSeldatavalue(String seldatavalue){
    this.seldatavalue = seldatavalue;
}


public boolean isMultpartfile(){
    return multpartfile;
}


public void setDbtableid(String dbtableid){
    this.dbtableid = dbtableid;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public void setPhonenumber(boolean phonenumber){
    this.phonenumber = phonenumber;
}


public String getPhonememo(){
    return phonememo;
}


public String getPhonetype(){
    return phonetype;
}


public boolean isPhonenumber(){
    return phonenumber;
}


public String getViewtype(){
    return viewtype;
}


public String getSeldatavalue(){
    return seldatavalue;
}


public String getGroupid(){
    return groupid;
}


public String getReftpid(){
    return reftpid;
}


public String getOrgi(){
    return orgi;
}


public String getReftptitlefield(){
    return reftptitlefield;
}


public int getLength(){
    return length;
}


public void setModits(boolean modits){
    this.modits = modits;
}


public boolean isModits(){
    return modits;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isModits"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isSeldata(){
    return seldata;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isSeldata"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public boolean isReffk(){
    return reffk;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/isReffk"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}