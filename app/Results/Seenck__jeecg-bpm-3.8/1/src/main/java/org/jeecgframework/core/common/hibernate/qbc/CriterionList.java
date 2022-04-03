package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.ArrayList;
import org.hibernate.criterion.Criterion;
public class CriterionList extends ArrayList<Object>{

 private  long serialVersionUID;


public Criterion getParas(int index){
    return (Criterion) super.get(index);
}


public void addPara(Criterion p){
    super.add(p);
}


public void removePara(int index){
    super.remove(index);
}


public int indexofPara(Criterion p){
    return super.indexOf(p);
}


}