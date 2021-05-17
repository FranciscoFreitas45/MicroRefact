import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_auth")
public class PhAuth implements Serializable{

 private  Long id;

 private  String unionid;

 private  String nickname;

 private  String headimgurl;

 private  String status;

 private  String phone;

 private  String companyName;

 private  String position;

 private  String realName;

 private  String idcardPositive;

 private  String idcardObverse;

 private  String inCert;

 private  Date createTime;

 private  String approver;

 private  Date approval;

 private  String approvalContent;

 private  String formId;

 private  String remark;

 private  Long codeId;


public void setUnionid(String unionid){
    this.unionid = unionid;
}


@Column(name = "phone", length = 11)
public String getPhone(){
    return phone;
}


public void setRealName(String realName){
    this.realName = realName;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "form_id", length = 200)
public String getFormId(){
    return formId;
}


@Column(name = "unionid", length = 200)
public String getUnionid(){
    return unionid;
}


@Column(name = "approver", length = 50)
public String getApprover(){
    return approver;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "approval")
public Date getApproval(){
    return approval;
}


public void setApprovalContent(String approvalContent){
    this.approvalContent = approvalContent;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Column(name = "idcard_obverse", length = 50)
public String getIdcardObverse(){
    return idcardObverse;
}


@Column(name = "status", length = 2)
public String getStatus(){
    return status;
}


public void setInCert(String inCert){
    this.inCert = inCert;
}


@Column(name = "code_id", length = 11)
public Long getCodeId(){
    return codeId;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setPosition(String position){
    this.position = position;
}


@Column(name = "idcard_positive", length = 50)
public String getIdcardPositive(){
    return idcardPositive;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "headimgurl", length = 500)
public String getHeadimgurl(){
    return headimgurl;
}


public void setIdcardObverse(String idcardObverse){
    this.idcardObverse = idcardObverse;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "approval_content", length = 200)
public String getApprovalContent(){
    return approvalContent;
}


public void setPhone(String phone){
    this.phone = phone;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


@Column(name = "real_name", length = 50)
public String getRealName(){
    return realName;
}


public void setIdcardPositive(String idcardPositive){
    this.idcardPositive = idcardPositive;
}


@Column(name = "in_cert", length = 50)
public String getInCert(){
    return inCert;
}


public void setFormId(String formId){
    this.formId = formId;
}


public void setCodeId(Long codeId){
    this.codeId = codeId;
}


public void setStatus(String status){
    this.status = status;
}


@Column(name = "position", length = 20)
public String getPosition(){
    return position;
}


@Column(name = "nickname", length = 200)
public String getNickname(){
    return nickname;
}


public void setHeadimgurl(String headimgurl){
    this.headimgurl = headimgurl;
}


@Column(name = "company_name", length = 50)
public String getCompanyName(){
    return companyName;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


public void setApproval(Date approval){
    this.approval = approval;
}


public void setApprover(String approver){
    this.approver = approver;
}


}