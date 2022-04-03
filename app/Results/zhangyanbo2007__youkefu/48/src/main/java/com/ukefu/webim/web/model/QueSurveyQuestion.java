package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_que_survey_question")
@org.hibernate.annotations.Proxy(lazy = false)
public class QueSurveyQuestion {

 private  long serialVersionUID;

 private  String id;

 private  String title;

 private  String name;

 private  String sortindex;

 private  int quetype;

 private  String trans;

 private  boolean interrupt;

 private  int interrupttime;

 private  int maxspreak;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  int offsetx;

 private  int offsety;

 private  String description;

 private  String memo;

 private  int score;

 private  String processid;

 private  String wvtype;

 private  String quevoice;

 private  String confirmtype;

 private  String confirmword;

 private  String confirmvoice;

 private  String overtimetype;

 private  String overtimeword;

 private  String overtimevoice;

 private  String errortype;

 private  String errorword;

 private  String errorvoice;

 private  String replykeyword;

 private  String replytype;

 private  String replyword;

 private  String replyvoice;

 private  String replyrepeat;

 private  String replyoperate;

 private  String replytrans;

 private  String replytypeup;

 private  String replywordup;

 private  String replyvoiceup;

 private  String overtimerepeat;

 private  String overtimeoperate;

 private  String overtimetrans;

 private  String overtimetypeup;

 private  String overtimewordup;

 private  String overtimevoiceup;

 private  String errorepeat;

 private  String erroroperate;

 private  String errortrans;

 private  String errortypeup;

 private  String errorwordup;

 private  String errorvoiceup;


public void setErrorvoice(String errorvoice){
    this.errorvoice = errorvoice;
}


public String getQuevoice(){
    return quevoice;
}


public void setErrortypeup(String errortypeup){
    this.errortypeup = errortypeup;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public String getOvertimevoice(){
    return overtimevoice;
}


public String getErrortypeup(){
    return errortypeup;
}


public void setReplytype(String replytype){
    this.replytype = replytype;
}


public String getReplykeyword(){
    return replykeyword;
}


public void setOvertimerepeat(String overtimerepeat){
    this.overtimerepeat = overtimerepeat;
}


public String getOvertimerepeat(){
    return overtimerepeat;
}


public void setMaxspreak(int maxspreak){
    this.maxspreak = maxspreak;
}


public void setSortindex(String sortindex){
    this.sortindex = sortindex;
}


public String getErrorvoice(){
    return errorvoice;
}


public String getMemo(){
    return memo;
}


public void setReplykeyword(String replykeyword){
    this.replykeyword = replykeyword;
}


public String getProcessid(){
    return processid;
}


public String getErrorwordup(){
    return errorwordup;
}


public void setName(String name){
    this.name = name;
}


public int getMaxspreak(){
    return maxspreak;
}


public void setErrorwordup(String errorwordup){
    this.errorwordup = errorwordup;
}


public void setReplyword(String replyword){
    this.replyword = replyword;
}


public int getInterrupttime(){
    return interrupttime;
}


public void setOvertimetrans(String overtimetrans){
    this.overtimetrans = overtimetrans;
}


public void setDescription(String description){
    this.description = description;
}


public String getWvtype(){
    return wvtype;
}


public Date getCreatetime(){
    return createtime;
}


public void setOvertimevoiceup(String overtimevoiceup){
    this.overtimevoiceup = overtimevoiceup;
}


public void setReplyrepeat(String replyrepeat){
    this.replyrepeat = replyrepeat;
}


public String getReplytrans(){
    return replytrans;
}


public void setReplyvoice(String replyvoice){
    this.replyvoice = replyvoice;
}


public boolean isInterrupt(){
    return interrupt;
}


public void setReplytypeup(String replytypeup){
    this.replytypeup = replytypeup;
}


public void setReplyoperate(String replyoperate){
    this.replyoperate = replyoperate;
}


public void setOffsetx(int offsetx){
    this.offsetx = offsetx;
}


public String getOvertimeoperate(){
    return overtimeoperate;
}


public void setOvertimewordup(String overtimewordup){
    this.overtimewordup = overtimewordup;
}


public void setErrortype(String errortype){
    this.errortype = errortype;
}


public void setOffsety(int offsety){
    this.offsety = offsety;
}


public void setErroroperate(String erroroperate){
    this.erroroperate = erroroperate;
}


public String getOvertimewordup(){
    return overtimewordup;
}


public String getReplyrepeat(){
    return replyrepeat;
}


public String getErrortrans(){
    return errortrans;
}


public String getErrorword(){
    return errorword;
}


public String getSortindex(){
    return sortindex;
}


public void setOvertimetype(String overtimetype){
    this.overtimetype = overtimetype;
}


public String getName(){
    return name;
}


public void setErrorvoiceup(String errorvoiceup){
    this.errorvoiceup = errorvoiceup;
}


public void setWvtype(String wvtype){
    this.wvtype = wvtype;
}


public void setConfirmvoice(String confirmvoice){
    this.confirmvoice = confirmvoice;
}


public int getOffsetx(){
    return offsetx;
}


public String getOvertimevoiceup(){
    return overtimevoiceup;
}


public String getReplywordup(){
    return replywordup;
}


public int getOffsety(){
    return offsety;
}


public String getTitle(){
    return title;
}


public void setId(String id){
    this.id = id;
}


public String getReplyword(){
    return replyword;
}


public void setInterrupt(boolean interrupt){
    this.interrupt = interrupt;
}


public int getQuetype(){
    return quetype;
}


public String getErrorepeat(){
    return errorepeat;
}


public void setErrorword(String errorword){
    this.errorword = errorword;
}


public void setTrans(String trans){
    this.trans = trans;
}


public String getOvertimetrans(){
    return overtimetrans;
}


public Date getUpdatetime(){
    return updatetime;
}


public void setOvertimevoice(String overtimevoice){
    this.overtimevoice = overtimevoice;
}


public String getConfirmtype(){
    return confirmtype;
}


public void setTitle(String title){
    this.title = title;
}


public String getErrortype(){
    return errortype;
}


public void setQuevoice(String quevoice){
    this.quevoice = quevoice;
}


public String getReplyvoiceup(){
    return replyvoiceup;
}


public void setErrortrans(String errortrans){
    this.errortrans = errortrans;
}


public String getReplytypeup(){
    return replytypeup;
}


public String getOvertimeword(){
    return overtimeword;
}


public String getReplyvoice(){
    return replyvoice;
}


public void setCreater(String creater){
    this.creater = creater;
}


public String getErrorvoiceup(){
    return errorvoiceup;
}


public String getReplyoperate(){
    return replyoperate;
}


public String getOvertimetype(){
    return overtimetype;
}


public String getTrans(){
    return trans;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getReplytype(){
    return replytype;
}


public String getDescription(){
    return description;
}


public String getErroroperate(){
    return erroroperate;
}


public void setReplytrans(String replytrans){
    this.replytrans = replytrans;
}


public void setOvertimetypeup(String overtimetypeup){
    this.overtimetypeup = overtimetypeup;
}


public void setOvertimeoperate(String overtimeoperate){
    this.overtimeoperate = overtimeoperate;
}


public void setReplyvoiceup(String replyvoiceup){
    this.replyvoiceup = replyvoiceup;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setConfirmword(String confirmword){
    this.confirmword = confirmword;
}


public String getConfirmvoice(){
    return confirmvoice;
}


public String getOvertimetypeup(){
    return overtimetypeup;
}


public void setConfirmtype(String confirmtype){
    this.confirmtype = confirmtype;
}


public void setOvertimeword(String overtimeword){
    this.overtimeword = overtimeword;
}


public void setMemo(String memo){
    this.memo = memo;
}


public void setQuetype(int quetype){
    this.quetype = quetype;
}


public String getOrgi(){
    return orgi;
}


public String getConfirmword(){
    return confirmword;
}


public void setErrorepeat(String errorepeat){
    this.errorepeat = errorepeat;
}


public int getScore(){
    return score;
}


public void setReplywordup(String replywordup){
    this.replywordup = replywordup;
}


public void setInterrupttime(int interrupttime){
    this.interrupttime = interrupttime;
}


public void setScore(int score){
    this.score = score;
}


}