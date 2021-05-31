import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class GetAllPendingMasterDet {

@Id
 private  String uniKey;

 private  int companyCount;

 private  int emptypeCount;

 private  int locCount;

 private  int desnCount;

 private  int deptCount;

 private  int hoCount;

 private  int hocatCount;

 private  int wocatCount;

 private  int shiftCount;

 private  int lvtypeCount;

 private  int lvstructCount;

 private  int compPending;

 private  int typePending;

 private  int deptPending;

 private  int desnPending;

 private  int shiftPending;

 private  int locPending;

 private  int hocatPending;

 private  int wocatPending;

 private  int lvStruvtPending;

 private  int lvAuthPending;


public void setLocCount(int locCount){
    this.locCount = locCount;
}


public void setDesnCount(int desnCount){
    this.desnCount = desnCount;
}


public void setDesnPending(int desnPending){
    this.desnPending = desnPending;
}


public void setLvstructCount(int lvstructCount){
    this.lvstructCount = lvstructCount;
}


public int getDesnPending(){
    return desnPending;
}


public int getWocatCount(){
    return wocatCount;
}


public void setLvtypeCount(int lvtypeCount){
    this.lvtypeCount = lvtypeCount;
}


public int getEmptypeCount(){
    return emptypeCount;
}


public void setShiftPending(int shiftPending){
    this.shiftPending = shiftPending;
}


public void setHoCount(int hoCount){
    this.hoCount = hoCount;
}


public void setLocPending(int locPending){
    this.locPending = locPending;
}


public void setEmptypeCount(int emptypeCount){
    this.emptypeCount = emptypeCount;
}


public int getDesnCount(){
    return desnCount;
}


public void setTypePending(int typePending){
    this.typePending = typePending;
}


public int getLvStruvtPending(){
    return lvStruvtPending;
}


public int getShiftCount(){
    return shiftCount;
}


public int getDeptCount(){
    return deptCount;
}


public void setHocatPending(int hocatPending){
    this.hocatPending = hocatPending;
}


public int getCompPending(){
    return compPending;
}


public void setDeptPending(int deptPending){
    this.deptPending = deptPending;
}


public int getLvAuthPending(){
    return lvAuthPending;
}


public int getLvtypeCount(){
    return lvtypeCount;
}


public void setUniKey(String uniKey){
    this.uniKey = uniKey;
}


public int getLocCount(){
    return locCount;
}


public void setWocatPending(int wocatPending){
    this.wocatPending = wocatPending;
}


public void setLvAuthPending(int lvAuthPending){
    this.lvAuthPending = lvAuthPending;
}


public int getCompanyCount(){
    return companyCount;
}


public void setLvStruvtPending(int lvStruvtPending){
    this.lvStruvtPending = lvStruvtPending;
}


public void setDeptCount(int deptCount){
    this.deptCount = deptCount;
}


public int getLocPending(){
    return locPending;
}


public void setHocatCount(int hocatCount){
    this.hocatCount = hocatCount;
}


public void setWocatCount(int wocatCount){
    this.wocatCount = wocatCount;
}


public int getHoCount(){
    return hoCount;
}


public void setShiftCount(int shiftCount){
    this.shiftCount = shiftCount;
}


public void setCompPending(int compPending){
    this.compPending = compPending;
}


public int getHocatPending(){
    return hocatPending;
}


public int getLvstructCount(){
    return lvstructCount;
}


public int getShiftPending(){
    return shiftPending;
}


public String getUniKey(){
    return uniKey;
}


public int getTypePending(){
    return typePending;
}


public int getWocatPending(){
    return wocatPending;
}


@Override
public String toString(){
    return "GetAllPendingMasterDet [uniKey=" + uniKey + ", companyCount=" + companyCount + ", emptypeCount=" + emptypeCount + ", locCount=" + locCount + ", desnCount=" + desnCount + ", deptCount=" + deptCount + ", hoCount=" + hoCount + ", hocatCount=" + hocatCount + ", wocatCount=" + wocatCount + ", shiftCount=" + shiftCount + ", lvtypeCount=" + lvtypeCount + ", lvstructCount=" + lvstructCount + ", compPending=" + compPending + ", typePending=" + typePending + ", deptPending=" + deptPending + ", desnPending=" + desnPending + ", shiftPending=" + shiftPending + ", locPending=" + locPending + ", hocatPending=" + hocatPending + ", wocatPending=" + wocatPending + ", lvStruvtPending=" + lvStruvtPending + ", lvAuthPending=" + lvAuthPending + "]";
}


public int getDeptPending(){
    return deptPending;
}


public int getHocatCount(){
    return hocatCount;
}


public void setCompanyCount(int companyCount){
    this.companyCount = companyCount;
}


}