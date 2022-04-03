package org.danyuan.application.dbms.code.po;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.danyuan.application.common.base.BaseEntity;
@Entity
@Table(name = "sys_dbms_generate_code_info")
@NamedQuery(name = "SysDbmsGenerateCodeInfo.findAll", query = "SELECT s FROM SysDbmsGenerateCodeInfo s")
public class SysDbmsGenerateCodeInfo extends BaseEntityimplements Serializable{

 private  long serialVersionUID;

@Column(name = "generate_js", precision = 3)
 private  Integer generateJs;

@Column(name = "generate_doc", precision = 10)
 private  Integer generateDoc;

@Column(name = "class_name")
 private  String className;

@Column(name = "generate_html", precision = 3)
 private  Integer generateHtml;

@Column(name = "generate_dao", precision = 3)
 private  Integer generateDao;

@Column(name = "class_path")
 private  String classPath;

@Column(name = "type_uuid")
 private  String typeUuid;

@Column(name = "generate_service", precision = 3)
 private  Integer generateService;

@Column(name = "generate_sql", precision = 3)
 private  Integer generateSql;

@Column(name = "generate_detail", precision = 10)
 private  Integer generateDetail;

@Column(name = "generate_controller", precision = 3)
 private  Integer generateController;

@Column(name = "generate_entity", precision = 3)
 private  Integer generateEntity;

@Column(name = "jdbc_uuid")
 private  String jdbcUuid;

/**
 * 构造方法：
 * 描 述： 默认构造函数
 * 参 数：
 * 作 者 ： test
 * @throws
 */
public SysDbmsGenerateCodeInfo() {
    super();
}
public String getTypeUuid(){
    return typeUuid;
}


public void setGenerateSql(Integer generateSql){
    this.generateSql = generateSql;
}


public Integer getGenerateService(){
    return generateService;
}


public void setClassName(String className){
    this.className = className;
}


public void setJdbcUuid(String jdbcUuid){
    this.jdbcUuid = jdbcUuid;
}


public void setGenerateDao(Integer generateDao){
    this.generateDao = generateDao;
}


public String getClassPath(){
    return classPath;
}


public void setClassPath(String classPath){
    this.classPath = classPath;
}


public String getJdbcUuid(){
    return jdbcUuid;
}


public Integer getGenerateSql(){
    return generateSql;
}


public void setGenerateDoc(Integer generateDoc){
    this.generateDoc = generateDoc;
}


public void setTypeUuid(String typeUuid){
    this.typeUuid = typeUuid;
}


public Integer getGenerateDao(){
    return generateDao;
}


public void setGenerateService(Integer generateService){
    this.generateService = generateService;
}


public void setGenerateController(Integer generateController){
    this.generateController = generateController;
}


public Integer getGenerateController(){
    return generateController;
}


public void setGenerateHtml(Integer generateHtml){
    this.generateHtml = generateHtml;
}


public Integer getGenerateJs(){
    return generateJs;
}


public Integer getGenerateHtml(){
    return generateHtml;
}


public void setGenerateDetail(Integer generateDetail){
    this.generateDetail = generateDetail;
}


public void setGenerateEntity(Integer generateEntity){
    this.generateEntity = generateEntity;
}


public Integer getGenerateEntity(){
    return generateEntity;
}


public Integer getGenerateDetail(){
    return generateDetail;
}


public String getClassName(){
    return className;
}


public void setGenerateJs(Integer generateJs){
    this.generateJs = generateJs;
}


public Integer getGenerateDoc(){
    return generateDoc;
}


}