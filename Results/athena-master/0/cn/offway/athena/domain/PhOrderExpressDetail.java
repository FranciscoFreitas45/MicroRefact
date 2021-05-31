import java.io.Serializable;
import javax.persistence;
import java.util.Date;
@Entity
@Table(name = "ph_order_express_detail")
public class PhOrderExpressDetail implements Serializable{

 private  Long id;

 private  String acceptTime;

 private  String expressOrderNo;

 private  String mailNo;

 private  String content;

 private  Date createTime;

 private  String acceptAddress;

 private  String opCode;

 private  String remark;


@Column(name = "express_order_no", length = 50)
public String getExpressOrderNo(){
    return expressOrderNo;
}


public void setContent(String content){
    this.content = content;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "create_time")
public Date getCreateTime(){
    return createTime;
}


@Column(name = "content", length = 200)
public String getContent(){
    return content;
}


public void setAcceptAddress(String acceptAddress){
    this.acceptAddress = acceptAddress;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


public void setCreateTime(Date createTime){
    this.createTime = createTime;
}


public void setExpressOrderNo(String expressOrderNo){
    this.expressOrderNo = expressOrderNo;
}


public void setMailNo(String mailNo){
    this.mailNo = mailNo;
}


@Column(name = "op_code", length = 20)
public String getOpCode(){
    return opCode;
}


public void setOpCode(String opCode){
    this.opCode = opCode;
}


@Column(name = "mail_no", length = 50)
public String getMailNo(){
    return mailNo;
}


@Column(name = "accept_time", length = 50)
public String getAcceptTime(){
    return acceptTime;
}


public void setAcceptTime(String acceptTime){
    this.acceptTime = acceptTime;
}


public void setRemark(String remark){
    this.remark = remark;
}


@Column(name = "remark", length = 200)
public String getRemark(){
    return remark;
}


public void setId(Long id){
    this.id = id;
}


@Column(name = "accept_address", length = 100)
public String getAcceptAddress(){
    return acceptAddress;
}


}