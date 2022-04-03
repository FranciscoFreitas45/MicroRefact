package com.ukefu.webim.web.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import com.ukefu.util.UKTools;
@Entity
@Table(name = "uk_columnproperties")
@org.hibernate.annotations.Proxy(lazy = false)
public class ColumnProperties {

 private  long serialVersionUID;

 private  String id;

 private  String modelid;

 private  String dataid;

 private  String dataname;

 private  String title;

 private  String colname;

 private  String border;

 private  String width;

 private  String format;

 private  String decimalcount;

 private  String sepsymbol;

 private  String font;

 private  String alignment;

 private  String fontstyle;

 private  String fontcolor;

 private  String cur;

 private  String timeformat;

 private  String hyp;

 private  String prefix;

 private  String suffix;

 private  String paramname;

 private  String orgi;

 private  int sortindex;

 private  String value;


public void setfontstyle(String fontstyle){
    this.fontstyle = fontstyle;
}


public String getBorder(){
    return border;
}


public void setAlignment(String alignment){
    this.alignment = alignment;
}


public String getHyp(){
    return hyp;
}


public String getCur(){
    return cur;
}


public String gettimeformat(){
    return timeformat;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getWidth(){
    return width;
}


public String getTitle(){
    return title;
}


public void setFormat(String format){
    this.format = format;
}


public String getSepsymbol(){
    return sepsymbol;
}


public void setdecimalcount(String decimalcount){
    this.decimalcount = decimalcount;
}


public void setId(String id){
    this.id = id;
}


public String getFormat(){
    return format;
}


public void setFont(String font){
    this.font = font;
}


public void setSepsymbol(String sepsymbol){
    this.sepsymbol = sepsymbol;
}


public void setColname(String colname){
    this.colname = colname;
}


public String getAlignment(){
    return alignment;
}


public String getfontstyle(){
    return fontstyle;
}


public String getModelid(){
    return modelid;
}


public void setModelid(String modelid){
    this.modelid = modelid;
}


public String getDataname(){
    return dataname;
}


public String getPrefix(){
    return prefix;
}


public void setparamname(String paramname){
    this.paramname = paramname;
}


public void settimeformat(String timeformat){
    this.timeformat = timeformat;
}


public void setSortindex(int sortindex){
    this.sortindex = sortindex;
}


public void setTitle(String title){
    this.title = title;
}


public void setWidth(String width){
    this.width = width;
}


public void setfontcolor(String fontcolor){
    this.fontcolor = fontcolor;
}


public String getColname(){
    return colname;
}


public String getValue(){
    return value;
}


public String getSuffix(){
    return suffix;
}


public String getDataid(){
    return dataid;
}


public void setPrefix(String prefix){
    this.prefix = prefix;
}


public void setDataname(String dataname){
    this.dataname = dataname;
}


public void setBorder(String border){
    this.border = border;
}


public String getfontcolor(){
    return fontcolor;
}


public void setValue(String value){
    this.value = value;
}


public String getOrgi(){
    return orgi;
}


public void setDataid(String dataid){
    this.dataid = dataid;
}


public String getdecimalcount(){
    return decimalcount;
}


public void setSuffix(String suffix){
    this.suffix = suffix;
}


public String getFont(){
    return font;
}


public void setCur(String cur){
    this.cur = cur;
}


public String getparamname(){
    return paramname;
}


public void setHyp(String hyp){
    this.hyp = hyp;
}


public int getSortindex(){
    return sortindex;
}


}