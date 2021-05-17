import javax.persistence;
@Entity
@Table(name = "dm_emp_doctype")
public class EmpDoctype {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "doctype_id")
 private  int doctypeId;

@Column(name = "company_id")
 private  int companyId;

@Column(name = "doctype_key")
 private  String doctypeKey;

@Column(name = "doctype_name")
 private  String doctypeName;

@Column(name = "doctype_note")
 private  String doctypeNote;

@Column(name = "is_value")
 private  int isValue;

@Column(name = "is_image")
 private  int isImage;

@Column(name = "image_size_width")
 private  int imageSizeWidth;

@Column(name = "image_size_length")
 private  int imageSizeLength;

@Column(name = "is_remarks")
 private  int isRemarks;

@Column(name = "del_status")
 private  int delStatus;

@Column(name = "is_active")
 private  int isActive;

@Column(name = "maker_user_id")
 private  int makerUserId;

@Column(name = "maker_enter_datetime")
 private  String makerEnterDatetime;

@Column(name = "is_required")
 private  int isRequired;

@Column(name = "order_by")
 private  int orderBy;

@Column(name = "ex_int1")
 private  int exInt1;

@Column(name = "ex_int2")
 private  int exInt2;

@Column(name = "ex_var1")
 private  String exVar1;

@Column(name = "ex_var2")
 private  String exVar2;


public String getExVar2(){
    return exVar2;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt1(){
    return exInt1;
}


public void setIsRequired(int isRequired){
    this.isRequired = isRequired;
}


public String getExVar1(){
    return exVar1;
}


public void setOrderBy(int orderBy){
    this.orderBy = orderBy;
}


public void setDoctypeNote(String doctypeNote){
    this.doctypeNote = doctypeNote;
}


public String getDoctypeName(){
    return doctypeName;
}


public int getIsValue(){
    return isValue;
}


public int getDoctypeId(){
    return doctypeId;
}


public String getDoctypeKey(){
    return doctypeKey;
}


public void setExInt1(int exInt1){
    this.exInt1 = exInt1;
}


public int getOrderBy(){
    return orderBy;
}


public void setDoctypeName(String doctypeName){
    this.doctypeName = doctypeName;
}


public int getIsRequired(){
    return isRequired;
}


public void setExVar1(String exVar1){
    this.exVar1 = exVar1;
}


public void setImageSizeWidth(int imageSizeWidth){
    this.imageSizeWidth = imageSizeWidth;
}


public void setExInt2(int exInt2){
    this.exInt2 = exInt2;
}


public void setDoctypeId(int doctypeId){
    this.doctypeId = doctypeId;
}


public void setMakerEnterDatetime(String makerEnterDatetime){
    this.makerEnterDatetime = makerEnterDatetime;
}


public int getImageSizeWidth(){
    return imageSizeWidth;
}


public int getIsRemarks(){
    return isRemarks;
}


public void setMakerUserId(int makerUserId){
    this.makerUserId = makerUserId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public String getDoctypeNote(){
    return doctypeNote;
}


public void setIsRemarks(int isRemarks){
    this.isRemarks = isRemarks;
}


public void setExVar2(String exVar2){
    this.exVar2 = exVar2;
}


public int getIsImage(){
    return isImage;
}


public int getImageSizeLength(){
    return imageSizeLength;
}


public void setIsImage(int isImage){
    this.isImage = isImage;
}


public int getIsActive(){
    return isActive;
}


public void setDoctypeKey(String doctypeKey){
    this.doctypeKey = doctypeKey;
}


public int getDelStatus(){
    return delStatus;
}


public int getCompanyId(){
    return companyId;
}


public void setIsValue(int isValue){
    this.isValue = isValue;
}


public void setImageSizeLength(int imageSizeLength){
    this.imageSizeLength = imageSizeLength;
}


@Override
public String toString(){
    return "EmpDoctype [doctypeId=" + doctypeId + ", companyId=" + companyId + ", doctypeKey=" + doctypeKey + ", doctypeName=" + doctypeName + ", doctypeNote=" + doctypeNote + ", isValue=" + isValue + ", isImage=" + isImage + ", imageSizeWidth=" + imageSizeWidth + ", imageSizeLength=" + imageSizeLength + ", isRemarks=" + isRemarks + ", delStatus=" + delStatus + ", isActive=" + isActive + ", makerUserId=" + makerUserId + ", makerEnterDatetime=" + makerEnterDatetime + ", isRequired=" + isRequired + ", orderBy=" + orderBy + ", exInt1=" + exInt1 + ", exInt2=" + exInt2 + ", exVar1=" + exVar1 + ", exVar2=" + exVar2 + "]";
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


}