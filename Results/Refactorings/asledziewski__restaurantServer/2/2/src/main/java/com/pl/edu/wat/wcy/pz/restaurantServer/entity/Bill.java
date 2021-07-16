package com.pl.edu.wat.wcy.pz.restaurantServer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Bill {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "BILL_ID")
 private  Long billId;

@Column(name = "STATUS")
 private  String status;

@Column(name = "CREATION_DATE")
 private  Date creationDate;

@Column(name = "RTABLE_ID")
 private  Long rTableId;

@Column(name = "VALUE")
 private  double value;

@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "billId")
 private  List<BillPosition> billPositions;


public List<BillPosition> getBillPositions(){
    return billPositions;
}


public Long getRTableId(){
    return rTableId;
}


public void setBillPositions(List<BillPosition> billPositions){
    this.billPositions = billPositions;
}


public void setBillId(Long billId){
    this.billId = billId;
}


public String getStatus(){
    return status;
}


public void setStatus(String status){
    this.status = status;
}


public Date getCreationDate(){
    return creationDate;
}


public double getValue(){
    return value;
}


public void setCreationDate(Date creationDate){
    this.creationDate = creationDate;
}


public Long getBillId(){
    return billId;
}


public void setValue(double value){
    this.value = value;
}


public void setRTableId(Long rTableId){
    this.rTableId = rTableId;
}


public void changeValue(double value){
    this.value += value;
}


    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", rTableId=" + rTableId +
                ", value=" + value +
                ", billPositions=" + billPositions +
                '}';
    }
}