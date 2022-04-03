package com.hmm.finance.financeReportDaily.domain;
 import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
public class FinanceReportDailyQueryDTO {

 private  Date month;


public void setMonth(Date month){
    this.month = month;
}


@SuppressWarnings({ "serial" })
public Specification<FinanceReportDaily> getWhereClause(FinanceReportDailyQueryDTO financeReportDailyQueryDTO){
    return new Specification<FinanceReportDaily>() {

        @Override
        public Predicate toPredicate(Root<FinanceReportDaily> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicate = new ArrayList<>();
            Date monthDate = financeReportDailyQueryDTO.getMonth();
            if (null != monthDate) {
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date").as(Date.class), monthDate));
                Calendar cal = Calendar.getInstance();
                cal.setTime(monthDate);
                cal.add(Calendar.MONTH, 1);
                monthDate = cal.getTime();
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("date").as(Date.class), monthDate));
            } else {
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.set(Calendar.DATE, 1);
                cal.set(Calendar.HOUR, -12);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                date = cal.getTime();
                // System.out.println(date);
                predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date").as(Date.class), date));
                cal.setTime(date);
                cal.add(Calendar.MONTH, 1);
                date = cal.getTime();
                predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("date").as(Date.class), date));
            // System.out.println(date);
            }
            Predicate[] pre = new Predicate[predicate.size()];
            return query.where(predicate.toArray(pre)).getRestriction();
        }
    };
}


@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
public Date getMonth(){
    return month;
}


@Override
public Predicate toPredicate(Root<FinanceReportDaily> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicate = new ArrayList<>();
    Date monthDate = financeReportDailyQueryDTO.getMonth();
    if (null != monthDate) {
        predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date").as(Date.class), monthDate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(monthDate);
        cal.add(Calendar.MONTH, 1);
        monthDate = cal.getTime();
        predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("date").as(Date.class), monthDate));
    } else {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR, -12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        date = cal.getTime();
        // System.out.println(date);
        predicate.add(criteriaBuilder.greaterThanOrEqualTo(root.get("date").as(Date.class), date));
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        date = cal.getTime();
        predicate.add(criteriaBuilder.lessThanOrEqualTo(root.get("date").as(Date.class), date));
    // System.out.println(date);
    }
    Predicate[] pre = new Predicate[predicate.size()];
    return query.where(predicate.toArray(pre)).getRestriction();
}


}