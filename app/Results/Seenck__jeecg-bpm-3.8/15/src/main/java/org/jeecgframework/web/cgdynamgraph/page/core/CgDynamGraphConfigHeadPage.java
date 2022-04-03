package org.jeecgframework.web.cgdynamgraph.page.core;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigItemEntity;
import org.jeecgframework.web.cgdynamgraph.entity.core.CgDynamGraphConfigParamEntity;
@Entity
@Table(name = "jform_cgdynamgraph_head", schema = "")
@SuppressWarnings("serial")
public class CgDynamGraphConfigHeadPage {

 private  List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList;

 private  List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList;

 private  java.lang.String id;

 private  java.lang.String code;

 private  java.lang.String name;

 private  java.lang.String cgrSql;

 private  java.lang.String content;

 private  String graphType;

 private  String dataStructure;

 private  String isPagination;


public void setName(java.lang.String name){
    this.name = name;
}


public void setContent(java.lang.String content){
    this.content = content;
}


@Column(name = "NAME", nullable = false, length = 100)
public java.lang.String getName(){
    return this.name;
}


public void setCode(java.lang.String code){
    this.code = code;
}


@Column(name = "CONTENT", nullable = false, length = 1000)
public java.lang.String getContent(){
    return this.content;
}


public List<CgDynamGraphConfigItemEntity> getCgDynamGraphConfigItemList(){
    return cgDynamGraphConfigItemList;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 36)
public java.lang.String getId(){
    return this.id;
}


@Column(name = "graph_type", length = 36)
public String getGraphType(){
    return graphType;
}


public void setGraphType(String graphType){
    this.graphType = graphType;
}


@Column(name = "is_pagination", length = 2)
public String getIsPagination(){
    return isPagination;
}


@Column(name = "CGR_SQL", nullable = false, length = 2000)
public java.lang.String getCgrSql(){
    return this.cgrSql;
}


@Column(name = "data_structure", length = 36)
public String getDataStructure(){
    return dataStructure;
}


public void setCgDynamGraphConfigParamList(List<CgDynamGraphConfigParamEntity> cgDynamGraphConfigParamList){
    this.cgDynamGraphConfigParamList = cgDynamGraphConfigParamList;
}


public List<CgDynamGraphConfigParamEntity> getCgDynamGraphConfigParamList(){
    return cgDynamGraphConfigParamList;
}


public void setId(java.lang.String id){
    this.id = id;
}


public void setIsPagination(String isPagination){
    this.isPagination = isPagination;
}


@Column(name = "CODE", nullable = false, length = 36)
public java.lang.String getCode(){
    return this.code;
}


public void setCgrSql(java.lang.String cgrSql){
    this.cgrSql = cgrSql;
}


public void setCgDynamGraphConfigItemList(List<CgDynamGraphConfigItemEntity> cgDynamGraphConfigItemList){
    this.cgDynamGraphConfigItemList = cgDynamGraphConfigItemList;
}


public void setDataStructure(String dataStructure){
    this.dataStructure = dataStructure;
}


}