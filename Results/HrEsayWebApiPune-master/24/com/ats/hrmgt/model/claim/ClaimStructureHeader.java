import javax.persistence;
import java.util.List;
@Entity
@Table(name = "claim_structure_header")
public class ClaimStructureHeader {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "clm_struct_head_id")
 private  int clmStructHeadId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "claim_struct_name")
 private  String claimStructName;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id ")
 private  int makerUserId;

@Column(name = "maker_datetime")
 private  String makerDatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;

@Transient
 private List<ClaimStructureDetail> detailList;


public void setMakerDatetime(String makerDatetime){
    this.makerDatetime = makerDatetime;
}


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public String getExVar1(){
    return exVar1;
}


public void setDetailList(List<ClaimStructureDetail> detailList){
    this.detailList = detailList;
}


public String getClaimStructName(){
    return claimStructName;
}


public int getClmStructHeadId(){
    return clmStructHeadId;
}


public void setClaimStructName(String claimStructName){
    this.claimStructName = claimStructName;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public int getMakerUserId(){
    return makerUserId;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public String getMakerDatetime(){
    return makerDatetime;
}


public int getIsActive(){
    return isActive;
}


public void setClmStructHeadId(int clmStructHeadId){
    this.clmStructHeadId = clmStructHeadId;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


@Override
public String toString(){
    return "ClaimStructureHeader [clmStructHeadId=" + clmStructHeadId + ", companyId=" + companyId + ", claimStructName=" + claimStructName + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerDatetime=" + makerDatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + ", detailList=" + detailList + "]";
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public List<ClaimStructureDetail> getDetailList(){
    return detailList;
}


}