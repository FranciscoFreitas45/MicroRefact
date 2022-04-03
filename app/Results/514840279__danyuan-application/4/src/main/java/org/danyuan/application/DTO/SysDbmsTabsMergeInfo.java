package org.danyuan.application.DTO;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
public class SysDbmsTabsMergeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

 private  String colsName2;

 private  String colsUuid2;

 private  String tableUuid2;

 private  String colsDesc2;

 private  String colsName1;

 private  String colsDesc1;

 private  String colsUuid1;

 private  String tableUuid1;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysDbmsTabsMergeInfo() {
    super();
}
public String getColsName2(){
    return colsName2;
}


public String getColsName1(){
    return colsName1;
}


public String getColsDesc1(){
    return colsDesc1;
}


public String getColsUuid1(){
    return colsUuid1;
}


public String getColsUuid2(){
    return colsUuid2;
}


public String getColsDesc2(){
    return colsDesc2;
}


public String getTableUuid1(){
    return tableUuid1;
}


public String getTableUuid2(){
    return tableUuid2;
}


public void setTableUuid1(String tableUuid1){
    this.tableUuid1 = tableUuid1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setTableUuid1"))

.queryParam("tableUuid1",tableUuid1)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsName1(String colsName1){
    this.colsName1 = colsName1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsName1"))

.queryParam("colsName1",colsName1)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsDesc1(String colsDesc1){
    this.colsDesc1 = colsDesc1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsDesc1"))

.queryParam("colsDesc1",colsDesc1)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsUuid1(String colsUuid1){
    this.colsUuid1 = colsUuid1;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsUuid1"))

.queryParam("colsUuid1",colsUuid1)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTableUuid2(String tableUuid2){
    this.tableUuid2 = tableUuid2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setTableUuid2"))

.queryParam("tableUuid2",tableUuid2)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsName2(String colsName2){
    this.colsName2 = colsName2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsName2"))

.queryParam("colsName2",colsName2)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsDesc2(String colsDesc2){
    this.colsDesc2 = colsDesc2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsDesc2"))

.queryParam("colsDesc2",colsDesc2)
;
restTemplate.put(builder.toUriString(),null);
}


public void setColsUuid2(String colsUuid2){
    this.colsUuid2 = colsUuid2;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ uuid).concat("/setColsUuid2"))

.queryParam("colsUuid2",colsUuid2)
;
restTemplate.put(builder.toUriString(),null);
}


}