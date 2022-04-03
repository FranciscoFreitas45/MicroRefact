package DTO;
 import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.jeecgframework.core.common.entity.IdEntity;
public class TSDataRule extends IdEntityimplements Serializable{

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

 private  TSFunction TSFunction;

 private  Date updateDate;


@Column(name = "rule_name", nullable = false, length = 32)
public String getRuleName(){
    return ruleName;
}


@Column(name = "create_name", nullable = false, length = 32)
public String getCreateName(){
    return createName;
}


@Column(name = "rule_value", nullable = false, length = 100)
public String getRuleValue(){
    return ruleValue;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "functionId")
public TSFunction getTSFunction(){
    return TSFunction;
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


@Column(name = "update_date", nullable = false)
public Date getUpdateDate(){
    return updateDate;
}


@Column(name = "update_by", nullable = false, length = 32)
public String getUpdateBy(){
    return updateBy;
}


@Column(name = "rule_column", nullable = false, length = 100)
public String getRuleColumn(){
    return ruleColumn;
}


}