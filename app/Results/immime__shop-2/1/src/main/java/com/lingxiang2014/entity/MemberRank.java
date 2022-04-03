package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "lx_member_rank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_member_rank_sequence")
public class MemberRank extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  Double scale;

 private  BigDecimal amount;

 private  Boolean isDefault;

 private  Boolean isSpecial;

 private  Set<Member> members;

 private  BigDecimal jiandianRate;

 private  BigDecimal jianDianMoney;

 private  Boolean isJianDianRate;


public void setName(String name){
    this.name = name;
}


@NotEmpty
@Length(max = 100)
@Column(nullable = false, unique = true, length = 100)
public String getName(){
    return name;
}


public void setScale(Double scale){
    this.scale = scale;
}


public void setJiandianRate(BigDecimal jiandianRate){
    this.jiandianRate = jiandianRate;
}


@Min(0)
@Digits(integer = 3, fraction = 3)
@Column(precision = 12, scale = 6)
public BigDecimal getJianDianMoney(){
    return jianDianMoney;
}


@NotNull
@Column(nullable = false)
public Boolean getIsDefault(){
    return isDefault;
}


public void setIsDefault(Boolean isDefault){
    this.isDefault = isDefault;
}


@NotNull
@Min(0)
@Digits(integer = 3, fraction = 3)
@Column(nullable = false, precision = 12, scale = 6)
public Double getScale(){
    return scale;
}


public void setJianDianMoney(BigDecimal jianDianMoney){
    this.jianDianMoney = jianDianMoney;
}


public void setIsSpecial(Boolean isSpecial){
    this.isSpecial = isSpecial;
}


public void setIsJianDianRate(Boolean isJianDianRate){
    this.isJianDianRate = isJianDianRate;
}


@NotNull
@Column(nullable = false)
public Boolean getIsJianDianRate(){
    return isJianDianRate;
}


public void setMembers(Set<Member> members){
    this.members = members;
}


@Min(0)
@Digits(integer = 3, fraction = 3)
@Column(precision = 12, scale = 6)
public BigDecimal getJiandianRate(){
    return jiandianRate;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
}


@NotNull
@Column(nullable = false)
public Boolean getIsSpecial(){
    return isSpecial;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(unique = true, precision = 21, scale = 6)
public BigDecimal getAmount(){
    return amount;
}


@OneToMany(mappedBy = "memberRank", fetch = FetchType.LAZY)
public Set<Member> getMembers(){
    return members;
}


}