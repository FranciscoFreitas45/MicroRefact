package com.lingxiang2014.entity;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name = "lx_bSystemAccount")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "lx_bSystemAccount_sequence")
public class BSystemAccount extends BaseEntity{

 private  long serialVersionUID;

 private  String number;

 private  BSystemAccount leftChildren;

 private  BSystemAccount midChildren;

 private  BSystemAccount rightChildren;

 private  BSystemAccount parent;

 private  BSystemAccount top;

 private  Set<BSystemAccount> children;

 private  Integer myPeople;

 private  Boolean isLeaf;

 private  Member member;


public void init(){
    setChildren(new HashSet<BSystemAccount>());
    setIsLeaf(true);
    setLeftChildren(null);
    setMidChildren(null);
    setMyPeople(0);
    setRightChildren(null);
}


@ManyToOne(fetch = FetchType.LAZY)
public BSystemAccount getParent(){
    return parent;
}


@Column(nullable = false)
public Boolean getIsLeaf(){
    return isLeaf;
}


@Column(nullable = false)
public Integer getMyPeople(){
    return myPeople;
}


@OneToOne(fetch = FetchType.LAZY)
public BSystemAccount getMidChildren(){
    return midChildren;
}


@ManyToOne(fetch = FetchType.LAZY)
public Member getMember(){
    return member;
}


@Transient
public void removeAttributeValue(){
}


public void setNumber(String number){
    this.number = number;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getNumber(){
    return number;
}


public void setTop(BSystemAccount top){
    this.top = top;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<BSystemAccount> getChildren(){
    return children;
}


public void setMember(Member member){
    this.member = member;
}


@OneToOne(fetch = FetchType.LAZY)
public BSystemAccount getRightChildren(){
    return rightChildren;
}


@OneToOne(fetch = FetchType.LAZY)
public BSystemAccount getLeftChildren(){
    return leftChildren;
}


public void setRightChildren(BSystemAccount rightChildren){
    this.rightChildren = rightChildren;
}


public void setMyPeople(Integer myPeople){
    this.myPeople = myPeople;
}


@ManyToOne(fetch = FetchType.LAZY)
public BSystemAccount getTop(){
    return top;
}


public void setLeftChildren(BSystemAccount leftChildren){
    this.leftChildren = leftChildren;
}


@Override
public String toString(){
    return number;
}


public void setParent(BSystemAccount parent){
    this.parent = parent;
}


public void setIsLeaf(Boolean isLeaf){
    this.isLeaf = isLeaf;
}


public void setChildren(Set<BSystemAccount> children){
    this.children = children;
}


public void setMidChildren(BSystemAccount midChildren){
    this.midChildren = midChildren;
}


}