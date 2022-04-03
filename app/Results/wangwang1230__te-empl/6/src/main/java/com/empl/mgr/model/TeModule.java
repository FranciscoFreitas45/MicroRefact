package com.empl.mgr.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
@Entity
@Table(name = "te_module", uniqueConstraints = { @UniqueConstraint(columnNames = "modulePage"), @UniqueConstraint(columnNames = "moduleCode"), @UniqueConstraint(columnNames = "moduleName") })
public class TeModule {

 private  long serialVersionUID;

 private  long moduleId;

 private  Date timestamp;

 private  String moduleName;

 private  String moduleCode;

 private  String moduleSuperCode;

 private  String modulePage;

 private  Integer moduleLevel;

// Constructors
/**
 * default constructor
 */
public TeModule() {
}/**
 * full constructor
 */
public TeModule(String moduleName, String moduleCode, String moduleSuperCode, String modulePage, Integer moduleLevel) {
    this.moduleName = moduleName;
    this.moduleCode = moduleCode;
    this.moduleSuperCode = moduleSuperCode;
    this.modulePage = modulePage;
    this.moduleLevel = moduleLevel;
}
public void setModuleCode(String moduleCode){
    this.moduleCode = moduleCode;
}


@Column(name = "moduleSuperCode", length = 12)
public String getModuleSuperCode(){
    return this.moduleSuperCode;
}


public void setModuleId(long moduleId){
    this.moduleId = moduleId;
}


public void setModuleLevel(Integer moduleLevel){
    this.moduleLevel = moduleLevel;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "moduleId", unique = true, nullable = false)
public long getModuleId(){
    return this.moduleId;
}


public void setModuleSuperCode(String moduleSuperCode){
    this.moduleSuperCode = moduleSuperCode;
}


@Column(name = "moduleName", unique = true, length = 64)
public String getModuleName(){
    return this.moduleName;
}


@Column(name = "moduleLevel")
public Integer getModuleLevel(){
    return this.moduleLevel;
}


@Column(name = "modulePage", unique = true, length = 128)
public String getModulePage(){
    return this.modulePage;
}


@Column(name = "moduleCode", unique = true, length = 12)
public String getModuleCode(){
    return this.moduleCode;
}


@Version
@Column(name = "timestamp", nullable = false, length = 19)
public Date getTimestamp(){
    return this.timestamp;
}


public void setModulePage(String modulePage){
    this.modulePage = modulePage;
}


@Override
public String toString(){
    return "TeModule [moduleId:" + moduleId + ", timestamp:" + timestamp + ", moduleName:" + moduleName + ", moduleCode:" + moduleCode + ", moduleSuperCode:" + moduleSuperCode + ", modulePage:" + modulePage + ", moduleLevel:" + moduleLevel + "]";
}


public void setModuleName(String moduleName){
    this.moduleName = moduleName;
}


public void setTimestamp(Date timestamp){
    this.timestamp = timestamp;
}


}