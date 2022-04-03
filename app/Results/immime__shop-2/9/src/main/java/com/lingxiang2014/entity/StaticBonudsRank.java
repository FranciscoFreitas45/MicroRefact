package com.lingxiang2014.entity;
 import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "lx_staticBonudsRank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_staticBonudsRank_sequence")
public class StaticBonudsRank extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  BigDecimal scale;

 private  BigDecimal everyAmount;

 private  BigDecimal amount;

 private  Boolean isDefault;

 private  Set<Member> members;


public void setName(String name){
    this.name = name;
}


@NotEmpty
@Length(max = 100)
@Column(nullable = false, unique = true, length = 100)
public String getName(){
    return name;
}


public void setScale(BigDecimal scale){
    this.scale = scale;
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
public BigDecimal getScale(){
    return scale;
}


public void setEveryAmount(BigDecimal everyAmount){
    this.everyAmount = everyAmount;
}


public void setMembers(Set<Member> members){
    this.members = members;
}


public void setAmount(BigDecimal amount){
    this.amount = amount;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(unique = true, precision = 21, scale = 6)
public BigDecimal getEveryAmount(){
    return everyAmount;
}


@PreRemove
public void preRemove(){
    Set<Member> members = getMembers();
    if (members != null) {
        for (Member member : members) {
            member.setStaticBonudsRank(null);
        }
    }
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(unique = true, precision = 21, scale = 6)
public BigDecimal getAmount(){
    return amount;
}


@OneToMany(mappedBy = "staticBonudsRank", fetch = FetchType.LAZY)
public Set<Member> getMembers(){
    return members;
}


}