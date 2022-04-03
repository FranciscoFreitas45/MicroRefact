package cn.gson.oasys.model.entity.process;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_detailsburse")
public class DetailsBurse {

@Id
@Column(name = "detailsburse_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long detailsburseId;

 private  Date produceTime;

 private  String subject;

 private  String descript;

 private  Integer invoices;

 private  double detailmoney;

@ManyToOne()
@JoinColumn(name = "bursment_id")
 private  Bursement burs;


public void setDetailmoney(double detailmoney){
    this.detailmoney = detailmoney;
}


public String getSubject(){
    return subject;
}


public void setSubject(String subject){
    this.subject = subject;
}


public String getDescript(){
    return descript;
}


public void setInvoices(Integer invoices){
    this.invoices = invoices;
}


public Date getProduceTime(){
    return produceTime;
}


public void setDescript(String descript){
    this.descript = descript;
}


public Integer getInvoices(){
    return invoices;
}


public void setProduceTime(Date produceTime){
    this.produceTime = produceTime;
}


public Bursement getBurs(){
    return burs;
}


public void setDetailsburseId(Long detailsburseId){
    this.detailsburseId = detailsburseId;
}


@Override
public String toString(){
    return "DetailsBurse [detailsburseId=" + detailsburseId + ", produceTime=" + produceTime + ", subject=" + subject + ", descript=" + descript + ", invoices=" + invoices + ", detailmoney=" + detailmoney + "]";
}


public void setBurs(Bursement burs){
    this.burs = burs;
}


public Long getDetailsburseId(){
    return detailsburseId;
}


public double getDetailmoney(){
    return detailmoney;
}


}