package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
public class Request extends DomainEntity{

 private  Status status;

 private  Integer rowNumber;

 private  Integer columnNumber;

 private  String reasonDescription;

 private  Member member;

 private  Parade parade;


@Min(1)
public Integer getRowNumber(){
    return this.rowNumber;
}


public void setRowNumber(Integer rowNumber){
    this.rowNumber = rowNumber;
}


public void setReasonDescription(String reasonDescription){
    this.reasonDescription = reasonDescription;
}


public void setMember(Member member){
    this.member = member;
}


public void setColumnNumber(Integer columnNumber){
    this.columnNumber = columnNumber;
}


public void setParade(Parade parade){
    this.parade = parade;
}


@ManyToOne(optional = true)
public Parade getParade(){
    return this.parade;
}


@Min(1)
public Integer getColumnNumber(){
    return this.columnNumber;
}


@ManyToOne(optional = true)
public Member getMember(){
    return this.member;
}


@Valid
@Enumerated(EnumType.STRING)
public Status getStatus(){
    return this.status;
}


@Valid
public String getReasonDescription(){
    return this.reasonDescription;
}


public void setStatus(Status status){
    this.status = status;
}


}