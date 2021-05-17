import com.ats.hrmgt.model.SummaryDailyAttendance;
import java.util.List;
public class CommonDash {

 private  PayRewardDedDash rewardDet;

 private  BirthHoliDash birth;

 private  GetNewHiresDash newHire;

 private  LeavePenDash lvDet;

 private  PreDayAttnDash attnDet;

 private  GetAllPendingMasterDet masterDet;

 private  List<DeptWiseWeekoffDash> deptwiseWkoff;

 private  List<DeptWiseWeekoffDash> perfListDept;

 private  List<DeptWiseWeekoffDash> deptWiseLvAbLList;

 private  List<DeptWiseWeekoffDash> deptWiseEmpCntList;

 private  GetNewHiresDash ageDiv;

 private  AgeDiversityDash ageDiversity;

 private  AgeDiversityDash expDiversity;

 private  AgeDiversityDash salDiversity;

 private  PayRewardDedDash dedDet;

 private  LoanAdvDashDet advDet;

 private  LoanAdvDashDet loanDet;

 private  PerformanceProdDash perfList;

 private  PerformanceProdDash prodList;

 private  IncentivesAmtDash icent;

 private  List<DeptWiseWeekoffDash> rewardWiseDedList;

 private  List<DeptWiseWeekoffDash> dedWiseDedList;

 private  List<GetLeaveHistForDash> lvApplList;

 private  SummaryDailyAttendance attnLastMon;


public LoanAdvDashDet getAdvDet(){
    return advDet;
}


public PayRewardDedDash getRewardDet(){
    return rewardDet;
}


public void setLoanDet(LoanAdvDashDet loanDet){
    this.loanDet = loanDet;
}


public List<DeptWiseWeekoffDash> getDedWiseDedList(){
    return dedWiseDedList;
}


public void setAgeDiversity(AgeDiversityDash ageDiversity){
    this.ageDiversity = ageDiversity;
}


public List<DeptWiseWeekoffDash> getDeptwiseWkoff(){
    return deptwiseWkoff;
}


public void setExpDiversity(AgeDiversityDash expDiversity){
    this.expDiversity = expDiversity;
}


public void setPerfList(PerformanceProdDash perfList){
    this.perfList = perfList;
}


public GetAllPendingMasterDet getMasterDet(){
    return masterDet;
}


public PreDayAttnDash getAttnDet(){
    return attnDet;
}


public void setPerfListDept(List<DeptWiseWeekoffDash> perfListDept){
    this.perfListDept = perfListDept;
}


public void setAttnDet(PreDayAttnDash attnDet){
    this.attnDet = attnDet;
}


public List<DeptWiseWeekoffDash> getRewardWiseDedList(){
    return rewardWiseDedList;
}


public void setDedWiseDedList(List<DeptWiseWeekoffDash> dedWiseDedList){
    this.dedWiseDedList = dedWiseDedList;
}


public List<GetLeaveHistForDash> getLvApplList(){
    return lvApplList;
}


public AgeDiversityDash getAgeDiversity(){
    return ageDiversity;
}


public LoanAdvDashDet getLoanDet(){
    return loanDet;
}


public List<DeptWiseWeekoffDash> getDeptWiseEmpCntList(){
    return deptWiseEmpCntList;
}


public List<DeptWiseWeekoffDash> getDeptWiseLvAbLList(){
    return deptWiseLvAbLList;
}


public void setRewardDet(PayRewardDedDash rewardDet){
    this.rewardDet = rewardDet;
}


public void setLvDet(LeavePenDash lvDet){
    this.lvDet = lvDet;
}


public LeavePenDash getLvDet(){
    return lvDet;
}


public void setSalDiversity(AgeDiversityDash salDiversity){
    this.salDiversity = salDiversity;
}


public void setAgeDiv(GetNewHiresDash ageDiv){
    this.ageDiv = ageDiv;
}


public void setDeptWiseLvAbLList(List<DeptWiseWeekoffDash> deptWiseLvAbLList){
    this.deptWiseLvAbLList = deptWiseLvAbLList;
}


public void setDedDet(PayRewardDedDash dedDet){
    this.dedDet = dedDet;
}


public void setProdList(PerformanceProdDash prodList){
    this.prodList = prodList;
}


public BirthHoliDash getBirth(){
    return birth;
}


public GetNewHiresDash getNewHire(){
    return newHire;
}


public void setBirth(BirthHoliDash birth){
    this.birth = birth;
}


public SummaryDailyAttendance getAttnLastMon(){
    return attnLastMon;
}


public void setNewHire(GetNewHiresDash newHire){
    this.newHire = newHire;
}


public IncentivesAmtDash getIcent(){
    return icent;
}


public void setDeptwiseWkoff(List<DeptWiseWeekoffDash> deptwiseWkoff){
    this.deptwiseWkoff = deptwiseWkoff;
}


public void setLvApplList(List<GetLeaveHistForDash> lvApplList){
    this.lvApplList = lvApplList;
}


public void setMasterDet(GetAllPendingMasterDet masterDet){
    this.masterDet = masterDet;
}


public void setRewardWiseDedList(List<DeptWiseWeekoffDash> rewardWiseDedList){
    this.rewardWiseDedList = rewardWiseDedList;
}


public PerformanceProdDash getPerfList(){
    return perfList;
}


public List<DeptWiseWeekoffDash> getPerfListDept(){
    return perfListDept;
}


public void setAdvDet(LoanAdvDashDet advDet){
    this.advDet = advDet;
}


public AgeDiversityDash getExpDiversity(){
    return expDiversity;
}


public void setIcent(IncentivesAmtDash icent){
    this.icent = icent;
}


public void setDeptWiseEmpCntList(List<DeptWiseWeekoffDash> deptWiseEmpCntList){
    this.deptWiseEmpCntList = deptWiseEmpCntList;
}


public AgeDiversityDash getSalDiversity(){
    return salDiversity;
}


public PerformanceProdDash getProdList(){
    return prodList;
}


@Override
public String toString(){
    return "CommonDash [rewardDet=" + rewardDet + ", birth=" + birth + ", newHire=" + newHire + ", lvDet=" + lvDet + ", attnDet=" + attnDet + ", masterDet=" + masterDet + ", deptwiseWkoff=" + deptwiseWkoff + ", perfListDept=" + perfListDept + ", deptWiseLvAbLList=" + deptWiseLvAbLList + ", deptWiseEmpCntList=" + deptWiseEmpCntList + ", ageDiv=" + ageDiv + ", ageDiversity=" + ageDiversity + ", expDiversity=" + expDiversity + ", salDiversity=" + salDiversity + ", dedDet=" + dedDet + ", advDet=" + advDet + ", loanDet=" + loanDet + ", perfList=" + perfList + ", prodList=" + prodList + ", icent=" + icent + ", rewardWiseDedList=" + rewardWiseDedList + ", dedWiseDedList=" + dedWiseDedList + ", lvApplList=" + lvApplList + ", attnLastMon=" + attnLastMon + "]";
}


public GetNewHiresDash getAgeDiv(){
    return ageDiv;
}


public PayRewardDedDash getDedDet(){
    return dedDet;
}


public void setAttnLastMon(SummaryDailyAttendance attnLastMon){
    this.attnLastMon = attnLastMon;
}


}