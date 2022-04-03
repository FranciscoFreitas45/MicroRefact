package org.jeecgframework.web.system.pojo.base;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
@Entity
@Table(name = "t_s_interface_datarule")
public class TSInterfaceDdataRuleEntity extends IdEntityimplements Serializable{

 private  long serialVersionUID;

 private  String ruleName;

 private  String ruleColumn;

 private  String ruleConditions;

 private  String ruleValue;

 private  String createBy;

 private  String createName;

 private  Date createDate;

 private  String updateBy;

 private  String updateName;

 private  TSInterfaceEntity TSInterface;

 private  Date updateDate;


public void setRuleConditions(String ruleConditions){
    this.ruleConditions = ruleConditions;
}


public void setUpdateDate(Date updateDate){
    this.updateDate = updateDate;
}


@Column(name = "rule_name", nullable = false, length = 32)
public String getRuleName(){
    return ruleName;
}


public void setUpdateBy(String updateBy){
    this.updateBy = updateBy;
}


public void setCreateBy(String createBy){
    this.createBy = createBy;
}


@Column(name = "create_name", nullable = false, length = 32)
public String getCreateName(){
    return createName;
}


public void setUpdateName(String updateName){
    this.updateName = updateName;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "interface_id")
public TSInterfaceEntity getTSInterface(){
    return TSInterface;
}


@Column(name = "rule_value", nullable = false, length = 100)
public String getRuleValue(){
    return ruleValue;
}


public void setTSInterface(TSInterfaceEntity tSInterface){
    TSInterface = tSInterface;
}


public void setRuleColumn(String ruleColumn){
    this.ruleColumn = ruleColumn;
}


@Column(name = "update_name", nullable = false, length = 32)
public String getUpdateName(){
    return updateName;
}


@Column(name = "create_by", nullable = false, length = 32)
public String getCreateBy(){
    return createBy;
}


@Column(name = "rule_conditions", nullable = false, length = 100)
public String getRuleConditions(){
    return ruleConditions;
}


@Column(name = "create_date", nullable = false)
public Date getCreateDate(){
    return createDate;
}


public void setCreateDate(Date createDate){
    this.createDate = createDate;
}


public void setCreateName(String createName){
    this.createName = createName;
}


public void setRuleName(String ruleName){
    this.ruleName = ruleName;
}


@Column(name = "update_date", nullable = false)
public Date getUpdateDate(){
    return updateDate;
}


@Column(name = "update_by", nullable = false, length = 32)
public String getUpdateBy(){
    return updateBy;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("TSInterfaceDdataRuleEntity [ruleName=");
    builder.append(ruleName);
    builder.append(", ruleColumn=");
    builder.append(ruleColumn);
    builder.append(", ruleConditions=");
    builder.append(ruleConditions);
    builder.append(", ruleValue=");
    builder.append(ruleValue);
    builder.append(", createBy=");
    builder.append(createBy);
    builder.append(", createName=");
    builder.append(createName);
    builder.append(", createDate=");
    builder.append(createDate);
    builder.append(", updateBy=");
    builder.append(updateBy);
    builder.append(", updateName=");
    builder.append(updateName);
    builder.append(", TSInterface=");
    builder.append(TSInterface);
    builder.append(", updateDate=");
    builder.append(updateDate);
    builder.append("]");
    return builder.toString();
}


@Column(name = "rule_column", nullable = false, length = 100)
public String getRuleColumn(){
    return ruleColumn;
}


public void setRuleValue(String ruleValue){
    this.ruleValue = ruleValue;
}


}