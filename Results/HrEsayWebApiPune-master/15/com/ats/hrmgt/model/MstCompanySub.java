import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence;
import java.sql.Date;
@Entity
@Table(name = "tbl_mst_sub_company")
public class MstCompanySub {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "company_id")
 private  int companyId;

@Column(name = "company_name")
 private  String companyName;

@Column(name = "name_sd")
 private  String nameSd;

@Column(name = "logo")
 private  String logo;

@Column(name = "letter_head")
 private  String letterHead;

@Column(name = "report_header")
 private  String reportHeader;

@Column(name = "report_footer")
 private  String reportFooter;

@Column(name = "long_add_1")
 private  String longAdd1;

@Column(name = "long_add_2")
 private  String longAdd2;

@Column(name = "long_add_3")
 private  String longAdd3;

@Column(name = "short_address")
 private  String shortAddress;

@Column(name = "landline_1")
 private  String landline1;

@Column(name = "landline_2")
 private  String landline2;

@Column(name = "fax_no")
 private  String faxNo;

@Column(name = "pan_no")
 private  String panNo;

@Column(name = "tan_no")
 private  String tanNo;

@Column(name = "is_pf_applicable")
 private  String isPfApplicable;

@Column(name = "pf_no")
 private  String pfNo;

@Column(name = "pf_group_code")
 private  String pfGroupCode;

@Column(name = "pf_coverage_date")
 private  Date pfCoverageDate;

@Column(name = "pf_signatory_1")
 private  String pfSignatory1;

@Column(name = "pf_signatory_2")
 private  String pfSignatory2;

@Column(name = "pf_signatory_3")
 private  String pfSignatory3;

@Column(name = "is_esic_applicable")
 private  String isEsicApplicable;

@Column(name = "esic_no")
 private  String esicNo;

@Column(name = "esic_coverage_date")
 private  Date esicCoverageDate;

@Column(name = "esic_signatory_1")
 private  String esicSignatory1;

@Column(name = "esic_signatory_2")
 private  String esicSignatory2;

@Column(name = "esic_signatory_3")
 private  String esicSignatory3;

@Column(name = "pt_no")
 private  String ptNo;

@Column(name = "service_tax_no")
 private  String serviceTaxNo;

@Column(name = "vat_no")
 private  String vatNo;

@Column(name = "gst_no")
 private  String gstNo;

@Column(name = "cst_no")
 private  String cstNo;

@Column(name = "gst_number")
 private  String gstNumber;

@Column(name = "is_sealing_limit_applicable")
 private  String isSealingLimitApplicable;

@Column(name = "cp_name")
 private  String cpName;

@Column(name = "cp_designation")
 private  String cpDesignation;

@Column(name = "cp_mobile")
 private  String cpMobile;

@Column(name = "cmp_bank_account")
 private  String cmpBankAccount;

@Column(name = "cp_email_1")
 private  String cpEmail1;

@Column(name = "cp_email_2")
 private  String cpEmail2;

@Column(name = "manager_under_act")
 private  String managerUnderAct;

@Column(name = "manager_address")
 private  String managerAddress;

@Column(name = "professional_fees")
 private  String professionalFees;

@Column(name = "is_parent_company")
 private  String isParentCompany;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDdatetime;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public void setEsicNo(String esicNo){
    this.esicNo = esicNo;
}


public String getExVar2(){
    return exVar2;
}


public String getExVar1(){
    return exVar1;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setCpName(String cpName){
    this.cpName = cpName;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public String getReportFooter(){
    return reportFooter;
}


public void setIsSealingLimitApplicable(String isSealingLimitApplicable){
    this.isSealingLimitApplicable = isSealingLimitApplicable;
}


public String getCmpBankAccount(){
    return cmpBankAccount;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setCmpBankAccount(String cmpBankAccount){
    this.cmpBankAccount = cmpBankAccount;
}


public String getReportHeader(){
    return reportHeader;
}


public String getProfessionalFees(){
    return professionalFees;
}


public void setCpEmail1(String cpEmail1){
    this.cpEmail1 = cpEmail1;
}


public void setPtNo(String ptNo){
    this.ptNo = ptNo;
}


public void setCpEmail2(String cpEmail2){
    this.cpEmail2 = cpEmail2;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public void setLongAdd1(String longAdd1){
    this.longAdd1 = longAdd1;
}


public void setLongAdd2(String longAdd2){
    this.longAdd2 = longAdd2;
}


public void setProfessionalFees(String professionalFees){
    this.professionalFees = professionalFees;
}


public void setLongAdd3(String longAdd3){
    this.longAdd3 = longAdd3;
}


public String getManagerUnderAct(){
    return managerUnderAct;
}


public String getManagerAddress(){
    return managerAddress;
}


public void setDelStatus(int delStatus){
    this.delStatus = delStatus;
}


public void setShortAddress(String shortAddress){
    this.shortAddress = shortAddress;
}


public int getExInt2(){
    return exInt2;
}


public String getLetterHead(){
    return letterHead;
}


public int getExInt1(){
    return exInt1;
}


public String getServiceTaxNo(){
    return serviceTaxNo;
}


public String getGstNo(){
    return gstNo;
}


public String getPfNo(){
    return pfNo;
}


public void setVatNo(String vatNo){
    this.vatNo = vatNo;
}


public void setPfSignatory3(String pfSignatory3){
    this.pfSignatory3 = pfSignatory3;
}


public void setPfSignatory2(String pfSignatory2){
    this.pfSignatory2 = pfSignatory2;
}


public void setPfSignatory1(String pfSignatory1){
    this.pfSignatory1 = pfSignatory1;
}


public void setLetterHead(String letterHead){
    this.letterHead = letterHead;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public String getIsParentCompany(){
    return isParentCompany;
}


public void setCpDesignation(String cpDesignation){
    this.cpDesignation = cpDesignation;
}


public String getVatNo(){
    return vatNo;
}


public String getFaxNo(){
    return faxNo;
}


public void setIsEsicApplicable(String isEsicApplicable){
    this.isEsicApplicable = isEsicApplicable;
}


public void setLogo(String logo){
    this.logo = logo;
}


public String getIsPfApplicable(){
    return isPfApplicable;
}


public String getNameSd(){
    return nameSd;
}


public void setPfGroupCode(String pfGroupCode){
    this.pfGroupCode = pfGroupCode;
}


public String getCstNo(){
    return cstNo;
}


public int getIsActive(){
    return isActive;
}


public String getIsEsicApplicable(){
    return isEsicApplicable;
}


public int getDelStatus(){
    return delStatus;
}


public void setCstNo(String cstNo){
    this.cstNo = cstNo;
}


@Override
public String toString(){
    return "MstCompanySub [companyId=" + companyId + ", companyName=" + companyName + ", nameSd=" + nameSd + ", logo=" + logo + ", letterHead=" + letterHead + ", reportHeader=" + reportHeader + ", reportFooter=" + reportFooter + ", longAdd1=" + longAdd1 + ", longAdd2=" + longAdd2 + ", longAdd3=" + longAdd3 + ", shortAddress=" + shortAddress + ", landline1=" + landline1 + ", landline2=" + landline2 + ", faxNo=" + faxNo + ", panNo=" + panNo + ", tanNo=" + tanNo + ", isPfApplicable=" + isPfApplicable + ", pfNo=" + pfNo + ", pfGroupCode=" + pfGroupCode + ", pfCoverageDate=" + pfCoverageDate + ", pfSignatory1=" + pfSignatory1 + ", pfSignatory2=" + pfSignatory2 + ", pfSignatory3=" + pfSignatory3 + ", isEsicApplicable=" + isEsicApplicable + ", esicNo=" + esicNo + ", esicCoverageDate=" + esicCoverageDate + ", esicSignatory1=" + esicSignatory1 + ", esicSignatory2=" + esicSignatory2 + ", esicSignatory3=" + esicSignatory3 + ", ptNo=" + ptNo + ", serviceTaxNo=" + serviceTaxNo + ", vatNo=" + vatNo + ", gstNo=" + gstNo + ", cstNo=" + cstNo + ", gstNumber=" + gstNumber + ", isSealingLimitApplicable=" + isSealingLimitApplicable + ", cpName=" + cpName + ", cpDesignation=" + cpDesignation + ", cpMobile=" + cpMobile + ", cmpBankAccount=" + cmpBankAccount + ", cpEmail1=" + cpEmail1 + ", cpEmail2=" + cpEmail2 + ", managerUnderAct=" + managerUnderAct + ", managerAddress=" + managerAddress + ", professionalFees=" + professionalFees + ", isParentCompany=" + isParentCompany + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerEnterDdatetime=" + makerEnterDdatetime + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
}


public String getPanNo(){
    return panNo;
}


public void setManagerUnderAct(String managerUnderAct){
    this.managerUnderAct = managerUnderAct;
}


public void setPanNo(String panNo){
    this.panNo = panNo;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getPfCoverageDate(){
    return pfCoverageDate;
}


public void setFaxNo(String faxNo){
    this.faxNo = faxNo;
}


public void setIsPfApplicable(String isPfApplicable){
    this.isPfApplicable = isPfApplicable;
}


public void setPfCoverageDate(Date pfCoverageDate){
    this.pfCoverageDate = pfCoverageDate;
}


public String getGstNumber(){
    return gstNumber;
}


public void setPfNo(String pfNo){
    this.pfNo = pfNo;
}


public void setServiceTaxNo(String serviceTaxNo){
    this.serviceTaxNo = serviceTaxNo;
}


@JsonFormat(locale = "hi", timezone = "Asia/Kolkata", pattern = "dd-MM-yyyy")
public Date getEsicCoverageDate(){
    return esicCoverageDate;
}


public String getShortAddress(){
    return shortAddress;
}


public void setIsParentCompany(String isParentCompany){
    this.isParentCompany = isParentCompany;
}


public String getTanNo(){
    return tanNo;
}


public String getLogo(){
    return logo;
}


public void setEsicSignatory1(String esicSignatory1){
    this.esicSignatory1 = esicSignatory1;
}


public void setMakerEnterDdatetime(String makerEnterDdatetime){
    this.makerEnterDdatetime = makerEnterDdatetime;
}


public void setEsicSignatory3(String esicSignatory3){
    this.esicSignatory3 = esicSignatory3;
}


public void setGstNo(String gstNo){
    this.gstNo = gstNo;
}


public void setEsicSignatory2(String esicSignatory2){
    this.esicSignatory2 = esicSignatory2;
}


public void setEsicCoverageDate(Date esicCoverageDate){
    this.esicCoverageDate = esicCoverageDate;
}


public String getPfGroupCode(){
    return pfGroupCode;
}


public String getLandline2(){
    return landline2;
}


public void setGstNumber(String gstNumber){
    this.gstNumber = gstNumber;
}


public String getLandline1(){
    return landline1;
}


public void setNameSd(String nameSd){
    this.nameSd = nameSd;
}


public String getIsSealingLimitApplicable(){
    return isSealingLimitApplicable;
}


public void setTanNo(String tanNo){
    this.tanNo = tanNo;
}


public void setCompanyId(int companyId){
    this.companyId = companyId;
}


public String getCpMobile(){
    return cpMobile;
}


public void setIsActive(int isActive){
    this.isActive = isActive;
}


public void setManagerAddress(String managerAddress){
    this.managerAddress = managerAddress;
}


public String getMakerEnterDdatetime(){
    return makerEnterDdatetime;
}


public void setReportFooter(String reportFooter){
    this.reportFooter = reportFooter;
}


public String getEsicSignatory2(){
    return esicSignatory2;
}


public String getCpEmail1(){
    return cpEmail1;
}


public String getEsicSignatory3(){
    return esicSignatory3;
}


public String getCpEmail2(){
    return cpEmail2;
}


public String getEsicSignatory1(){
    return esicSignatory1;
}


public void setLandline2(String landline2){
    this.landline2 = landline2;
}


public void setLandline1(String landline1){
    this.landline1 = landline1;
}


public String getCpName(){
    return cpName;
}


public String getPtNo(){
    return ptNo;
}


public void setCpMobile(String cpMobile){
    this.cpMobile = cpMobile;
}


public String getLongAdd3(){
    return longAdd3;
}


public String getEsicNo(){
    return esicNo;
}


public String getLongAdd1(){
    return longAdd1;
}


public String getCpDesignation(){
    return cpDesignation;
}


public String getLongAdd2(){
    return longAdd2;
}


public void setReportHeader(String reportHeader){
    this.reportHeader = reportHeader;
}


public int getCompanyId(){
    return companyId;
}


public String getPfSignatory2(){
    return pfSignatory2;
}


public String getCompanyName(){
    return companyName;
}


public String getPfSignatory3(){
    return pfSignatory3;
}


public String getPfSignatory1(){
    return pfSignatory1;
}


}