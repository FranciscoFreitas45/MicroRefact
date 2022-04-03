package com.lingxiang2014.DTO;
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
public class StaticBonudsRank extends BaseEntity{

 private  long serialVersionUID;

 private  String name;

 private  BigDecimal scale;

 private  BigDecimal everyAmount;

 private  BigDecimal amount;

 private  Boolean isDefault;

 private  Set<Member> members;


@NotEmpty
@Length(max = 100)
@Column(nullable = false, unique = true, length = 100)
public String getName(){
    return name;
}


@NotNull
@Column(nullable = false)
public Boolean getIsDefault(){
    return isDefault;
}


@NotNull
@Min(0)
@Digits(integer = 3, fraction = 3)
@Column(nullable = false, precision = 12, scale = 6)
public BigDecimal getScale(){
    return scale;
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(unique = true, precision = 21, scale = 6)
public BigDecimal getEveryAmount(){
    return everyAmount;
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