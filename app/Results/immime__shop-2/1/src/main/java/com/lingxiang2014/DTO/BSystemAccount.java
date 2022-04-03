package com.lingxiang2014.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";


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


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getNumber(){
    return number;
}


@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
public Set<BSystemAccount> getChildren(){
    return children;
}


@OneToOne(fetch = FetchType.LAZY)
public BSystemAccount getRightChildren(){
    return rightChildren;
}


@OneToOne(fetch = FetchType.LAZY)
public BSystemAccount getLeftChildren(){
    return leftChildren;
}


@ManyToOne(fetch = FetchType.LAZY)
public BSystemAccount getTop(){
    return top;
}


public void init(){
    setChildren(new HashSet<BSystemAccount>());
    setIsLeaf(true);
    setLeftChildren(null);
    setMidChildren(null);
    setMyPeople(0);
    setRightChildren(null);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/init"))

;
restTemplate.put(builder.toUriString(),null);
}


public void setNumber(String number){
    this.number = number;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNumber"))

.queryParam("number",number)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMember(Member member){
    this.member = member;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setMember"))

.queryParam("member",member)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTop(BSystemAccount top){
    this.top = top;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setTop"))

.queryParam("top",top)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRightChildren(BSystemAccount rightChildren){
    this.rightChildren = rightChildren;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setRightChildren"))

.queryParam("rightChildren",rightChildren)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsLeaf(Boolean isLeaf){
    this.isLeaf = isLeaf;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsLeaf"))

.queryParam("isLeaf",isLeaf)
;
restTemplate.put(builder.toUriString(),null);
}


public void setLeftChildren(BSystemAccount leftChildren){
    this.leftChildren = leftChildren;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setLeftChildren"))

.queryParam("leftChildren",leftChildren)
;
restTemplate.put(builder.toUriString(),null);
}


}