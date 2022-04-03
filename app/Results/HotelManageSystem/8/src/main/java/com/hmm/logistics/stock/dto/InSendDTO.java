package com.hmm.logistics.stock.dto;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import com.hmm.finance.logisticst.domain.InStorage;
public class InSendDTO {

 private  String inStorageId;

@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
 private  Date applyTime;

@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
 private  Date doDate;

 private  float amount;

 private  String sendWorker;


public Date getApplyTime(){
    return applyTime;
}


public void setApplyTime(Date applyTime){
    this.applyTime = applyTime;
}


public void setSendWorker(String sendWorker){
    this.sendWorker = sendWorker;
}


public String getInStorageId(){
    return inStorageId;
}


public void setDoDate(Date doDate){
    this.doDate = doDate;
}


public Date getDoDate(){
    return doDate;
}


public String getSendWorker(){
    return sendWorker;
}


@SuppressWarnings({ "serial" })
public Specification<InSendDTO> getWhereClause(InSendDTO inSendDTO){
    return new Specification<InSendDTO>() {

        @Override
        public Predicate toPredicate(Root<InSendDTO> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.get("applyTime").as(Date.class), inSendDTO.getApplyTime()));
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


public void setInStorageId(String inStorageId){
    this.inStorageId = inStorageId;
}


public void setAmount(float amount){
    this.amount = amount;
}


@Override
public Predicate toPredicate(Root<InSendDTO> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    predicate.add(criteriaBuilder.equal(root.get("applyTime").as(Date.class), inSendDTO.getApplyTime()));
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


public float getAmount(){
    return amount;
}


}