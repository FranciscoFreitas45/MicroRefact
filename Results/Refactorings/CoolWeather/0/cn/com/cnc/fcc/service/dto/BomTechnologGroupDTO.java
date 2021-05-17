import java.util.ArrayList;
import java.util.List;
import cn.com.cnc.fcc.domain.QmsBomTechnology;
import cn.com.cnc.fcc.domain.QmsEnclosure;
import cn.com.cnc.fcc.domain.QmsQualityControlDetails;
public class BomTechnologGroupDTO {

 private  QmsBomTechnology qmsBomTechnology;

 private  List<QmsQualityControlDetails> qmsQualityControlDetails;

 private  List<QmsPartsAssemblyRelationOwnerDTO> qmsPartsAssemblyRelation;

 private  List<QmsEnclosure> qmsEnclosure;


public void setQmsEnclosure(List<QmsEnclosure> qmsEnclosure){
    this.qmsEnclosure = qmsEnclosure;
}


public void setQmsQualityControlDetails(List<QmsQualityControlDetails> qmsQualityControlDetails){
    this.qmsQualityControlDetails = qmsQualityControlDetails;
}


public List<QmsQualityControlDetails> getQmsQualityControlDetails(){
    return qmsQualityControlDetails;
}


public List<QmsPartsAssemblyRelationOwnerDTO> getQmsPartsAssemblyRelation(){
    return qmsPartsAssemblyRelation;
}


public List<QmsEnclosure> getQmsEnclosure(){
    return qmsEnclosure;
}


public QmsBomTechnology getQmsBomTechnology(){
    return qmsBomTechnology;
}


public void setQmsBomTechnology(QmsBomTechnology qmsBomTechnology){
    this.qmsBomTechnology = qmsBomTechnology;
}


public void setQmsPartsAssemblyRelation(List<QmsPartsAssemblyRelationOwnerDTO> qmsPartsAssemblyRelation){
    this.qmsPartsAssemblyRelation = qmsPartsAssemblyRelation;
}


}