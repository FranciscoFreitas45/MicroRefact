package com.ukefu.webim.web.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_tableproperties")
@org.hibernate.annotations.Proxy(lazy = false)
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
public void setReftpname(String reftpname){
    this.reftpname = reftpname;
}


public boolean isPrivatefield(){
    return privatefield;
}


public void setViewtype(String viewtype){
    this.viewtype = viewtype;
}


public boolean isFieldstatus(){
    return fieldstatus;
}


public void setPhonememo(String phonememo){
    this.phonememo = phonememo;
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


public void setReftpid(String reftpid){
    this.reftpid = reftpid;
}


public void setDescorder(boolean descorder){
    this.descorder = descorder;
}


public void setPhonetype(String phonetype){
    this.phonetype = phonetype;
}


public String getCode(){
    return code;
}


public boolean isSeldata(){
    return seldata;
}


public boolean isDefaultsort(){
    return defaultsort;
}


public String getDefaultvaluetitle(){
    return defaultvaluetitle;
}


public void setCode(String code){
    this.code = code;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setIndexdatatype(String indexdatatype){
    this.indexdatatype = indexdatatype;
}


public String getDefaultvalue(){
    return defaultvalue;
}


public boolean isSysfield(){
    return sysfield;
}


public String getCascadetype(){
    return cascadetype;
}


public String getFieldname(){
    return fieldname != null ? fieldname.toLowerCase() : null;
}


public boolean isDescorder(){
    return descorder;
}


public boolean isTitle(){
    return title;
}


public void setInx(boolean inx){
    this.inx = inx;
}


public void setName(String name){
    this.name = name;
}


public void setStyletype(String styletype){
    this.styletype = styletype;
}


public void setSecdistype(String secdistype){
    this.secdistype = secdistype;
}


public boolean isModits(){
    return modits;
}


public void setUploadtype(String uploadtype){
    this.uploadtype = uploadtype;
}


public void setDefaultfieldvalue(String defaultfieldvalue){
    this.defaultfieldvalue = defaultfieldvalue;
}


public void setTokentype(String tokentype){
    this.tokentype = tokentype;
}


public String getTablename(){
    return tablename;
}


public void setTablename(String tablename){
    this.tablename = tablename;
}


public void setSeldatacode(String seldatacode){
    this.seldatacode = seldatacode;
}


public void setSysfield(boolean sysfield){
    this.sysfield = sysfield;
}


public void setReftbname(String reftbname){
    this.reftbname = reftbname;
}


public String getUserid(){
    return userid;
}


public void setUserid(String userid){
    this.userid = userid;
}


public String getUploadtype(){
    return uploadtype;
}


public String getReftbid(){
    return reftbid;
}


public void setDatatypecode(int datatypecode){
    this.datatypecode = datatypecode;
}


public void setSeldata(boolean seldata){
    this.seldata = seldata;
}


public String getReftpname(){
    return reftpname;
}


public boolean isSystemfield(){
    return systemfield;
}


public void setSystemfield(boolean systemfield){
    this.systemfield = systemfield;
}


public void setFieldname(String fieldname){
    this.fieldname = fieldname;
}


public void setToken(boolean token){
    this.token = token;
}


public String getPlugin(){
    return plugin;
}


public void setPk(boolean pk){
    this.pk = pk;
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


public void setSecfield(boolean secfield){
    this.secfield = secfield;
}


public String getIndexdatatype(){
    return indexdatatype;
}


public boolean isToken(){
    return token;
}


public String getSeldatakey(){
    return seldatakey;
}


public boolean isInx(){
    return inx;
}


public String getReftbname(){
    return reftbname;
}


public String getReftype(){
    return reftype;
}


public void setId(String id){
    this.id = id;
}


public void setPrivatefield(boolean privatefield){
    this.privatefield = privatefield;
}


public void setFieldstatus(boolean fieldstatus){
    this.fieldstatus = fieldstatus;
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


public void setGroupid(String groupid){
    this.groupid = groupid;
}


public void setDefaultsort(boolean defaultsort){
    this.defaultsort = defaultsort;
}


public String getStyletype(){
    return styletype;
}


public void setPlugin(String plugin){
    this.plugin = plugin;
}


public void setTitle(boolean title){
    this.title = title;
}


public void setReftbid(String reftbid){
    this.reftbid = reftbid;
}


public int getDatatypecode(){
    return datatypecode;
}


public String getSeldatacode(){
    return seldatacode;
}


public void setLength(int length){
    this.length = length;
}


public String getSecdistype(){
    return secdistype;
}


public void setReftptitlefield(String reftptitlefield){
    this.reftptitlefield = reftptitlefield;
}


public String getDbtableid(){
    return dbtableid;
}


public void setSeldatakey(String seldatakey){
    this.seldatakey = seldatakey;
}


public void setSeldatavalue(String seldatavalue){
    this.seldatavalue = seldatavalue;
}


public void setDatatypename(String datatypename){
    this.datatypename = datatypename;
}


public boolean isMultpartfile(){
    return multpartfile;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
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


public void setDefaultvaluetitle(String defaultvaluetitle){
    this.defaultvaluetitle = defaultvaluetitle;
}


public void setPhonenumber(boolean phonenumber){
    this.phonenumber = phonenumber;
}


public boolean isReffk(){
    return reffk;
}


public String getPhonememo(){
    return phonememo;
}


public void setImpfield(boolean impfield){
    this.impfield = impfield;
}


public String getPhonetype(){
    return phonetype;
}


public void setCascadetype(String cascadetype){
    this.cascadetype = cascadetype;
}


public boolean isPhonenumber(){
    return phonenumber;
}


public boolean isSecfield(){
    return secfield;
}


public String getViewtype(){
    return viewtype;
}


public void setDefaultvalue(String defaultvalue){
    this.defaultvalue = defaultvalue;
}


public String getSeldatavalue(){
    return seldatavalue;
}


public String getGroupid(){
    return groupid;
}


public boolean isPk(){
    return pk;
}


public String getReftpid(){
    return reftpid;
}


public void setReffk(boolean reffk){
    this.reffk = reffk;
}


public String getOrgi(){
    return orgi;
}


public TableProperties clone(){
    try {
        return (TableProperties) super.clone();
    } catch (CloneNotSupportedException e) {
        e.printStackTrace();
    }
    return null;
}


public String getReftptitlefield(){
    return reftptitlefield;
}


public int getLength(){
    return length;
}


public void setSeldatatype(String seldatatype){
    this.seldatatype = seldatatype;
}


public void setModits(boolean modits){
    this.modits = modits;
}


public boolean isImpfield(){
    return impfield;
}


}