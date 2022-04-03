package org.jeecgframework.web.system.pojo.base;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
@Entity
@Table(name = "t_s_interrole_interface", schema = "")
@SuppressWarnings("serial")
public class InterroleInterfaceEntity {

 private  TSInterfaceEntity interfaceEntity;

 private  InterroleEntity interroleEntity;

 private  java.lang.String dataRule;

 private  java.lang.String id;


@Column(name = "DATA_RULE", length = 1000)
public java.lang.String getDataRule(){
    return this.dataRule;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "interface_id")
// update-begin-author:taoYan date:20180802 for:TASK #3043 【垃圾数据BUG处理】
@NotFound(action = NotFoundAction.IGNORE)
public TSInterfaceEntity getInterfaceEntity(){
    return this.interfaceEntity;
}


public void setInterfaceEntity(TSInterfaceEntity tSInterfaceEntity){
    this.interfaceEntity = tSInterfaceEntity;
}


public void setId(java.lang.String id){
    this.id = id;
}


@Id
@GeneratedValue(generator = "paymentableGenerator")
@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
@Column(name = "ID", nullable = false, length = 32)
public java.lang.String getId(){
    return this.id;
}


public void setInterroleEntity(InterroleEntity interroleEntity){
    this.interroleEntity = interroleEntity;
}


public void setDataRule(java.lang.String dataRule){
    this.dataRule = dataRule;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "interrole_id")
public InterroleEntity getInterroleEntity(){
    return this.interroleEntity;
}


}