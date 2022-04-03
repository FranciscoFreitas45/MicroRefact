package org.gliderwiki.web.domain;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
@Table("WE_PROFILE")
@DataTransferObject
public class WeProfile extends BaseObjectBean{

 private  long serialVersionUID;

@Column(primaryKey = true, autoIncrement = false)
 private  Integer we_user_idx;

@Column(name = "we_user_email")
 private  String we_user_email;

@Column(name = "we_user_site")
 private  String we_user_site;

@Column(name = "we_cell_num1")
 private  String we_cell_num1;

@Column(name = "we_cell_num2")
 private  String we_cell_num2;

@Column(name = "we_cell_num3")
 private  String we_cell_num3;

@Column(name = "we_file_save_name")
 private  String we_file_save_name;

@Column(name = "we_file_real_name")
 private  String we_file_real_name;

@Column(name = "we_file_save_path")
 private  String we_file_save_path;

@Column(name = "we_thumb_path")
 private  String we_thumb_path;

@Column(name = "we_thumb_name")
 private  String we_thumb_name;

@Column(name = "we_away_yn")
 private  String we_away_yn;

@Column(name = "we_grade")
 private  Integer we_grade;

@Column(name = "we_tech_yn")
 private  String we_tech_yn;

@Column(name = "we_point")
 private  Integer we_point;

@Column(name = "we_visit_date")
 private  Date we_visit_date;

@Column(name = "we_ins_date")
 private  Date we_ins_date;

@Column(name = "we_upd_date")
 private  Date we_upd_date;

@Column(name = "we_noti_checked")
 private  String we_noti_checked;

// 조회용 생성자
public WeProfile(String we_tech_yn, Integer we_point) {
    this.we_tech_yn = we_tech_yn;
    this.we_point = we_point;
}public WeProfile() {
}
public Integer getWe_user_idx(){
    return we_user_idx;
}


public Integer getWe_point(){
    return we_point;
}


public String getWe_cell_num2(){
    return we_cell_num2;
}


public Integer getWe_grade(){
    return we_grade;
}


public String getWe_user_email(){
    return we_user_email;
}


public String getWe_cell_num3(){
    return we_cell_num3;
}


public String getWe_thumb_name(){
    return we_thumb_name;
}


public String getWe_file_save_name(){
    return we_file_save_name;
}


public String getWe_cell_num1(){
    return we_cell_num1;
}


public void setWe_tech_yn(String we_tech_yn){
    this.we_tech_yn = we_tech_yn;
}


public void setWe_cell_num3(String we_cell_num3){
    this.we_cell_num3 = we_cell_num3;
}


public void setWe_upd_date(Date we_upd_date){
    this.we_upd_date = we_upd_date;
}


public void setWe_cell_num2(String we_cell_num2){
    this.we_cell_num2 = we_cell_num2;
}


public void setWe_file_save_path(String we_file_save_path){
    this.we_file_save_path = we_file_save_path;
}


public void setWe_cell_num1(String we_cell_num1){
    this.we_cell_num1 = we_cell_num1;
}


public void setWe_user_site(String we_user_site){
    this.we_user_site = we_user_site;
}


public Date getWe_ins_date(){
    return we_ins_date;
}


public void setWe_file_real_name(String we_file_real_name){
    this.we_file_real_name = we_file_real_name;
}


public void setWe_noti_checked(String we_noti_checked){
    this.we_noti_checked = we_noti_checked;
}


public void setWe_user_idx(Integer we_user_idx){
    this.we_user_idx = we_user_idx;
}


public String getWe_file_real_name(){
    return we_file_real_name;
}


public String getWe_file_save_path(){
    return we_file_save_path;
}


public String getWe_away_yn(){
    return we_away_yn;
}


public String getWe_noti_checked(){
    return we_noti_checked;
}


public void setWe_visit_date(Date we_visit_date){
    this.we_visit_date = we_visit_date;
}


public void setWe_file_save_name(String we_file_save_name){
    this.we_file_save_name = we_file_save_name;
}


public String getWe_tech_yn(){
    return we_tech_yn;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public void setWe_thumb_name(String we_thumb_name){
    this.we_thumb_name = we_thumb_name;
}


public void setWe_away_yn(String we_away_yn){
    this.we_away_yn = we_away_yn;
}


public Date getWe_visit_date(){
    return we_visit_date;
}


public void setWe_user_email(String we_user_email){
    this.we_user_email = we_user_email;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public void setWe_point(Integer we_point){
    this.we_point = we_point;
}


public void setWe_ins_date(Date we_ins_date){
    this.we_ins_date = we_ins_date;
}


public void setWe_grade(Integer we_grade){
    this.we_grade = we_grade;
}


public void setWe_thumb_path(String we_thumb_path){
    this.we_thumb_path = we_thumb_path;
}


public String getWe_user_site(){
    return we_user_site;
}


}