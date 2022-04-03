package org.gliderwiki.DTO;
 import java.util.Date;
import org.directwebremoting.annotations.DataTransferObject;
import org.gliderwiki.framework.orm.sql.annotation.Column;
import org.gliderwiki.framework.orm.sql.annotation.Table;
import org.gliderwiki.web.vo.BaseObjectBean;
public class WeProfile extends BaseObjectBean{

 private  long serialVersionUID;

 private  Integer we_user_idx;

 private  String we_user_email;

 private  String we_user_site;

 private  String we_cell_num1;

 private  String we_cell_num2;

 private  String we_cell_num3;

 private  String we_file_save_name;

 private  String we_file_real_name;

 private  String we_file_save_path;

 private  String we_thumb_path;

 private  String we_thumb_name;

 private  String we_away_yn;

 private  Integer we_grade;

 private  String we_tech_yn;

 private  Integer we_point;

 private  Date we_visit_date;

 private  Date we_ins_date;

 private  Date we_upd_date;

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


public Date getWe_ins_date(){
    return we_ins_date;
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


public String getWe_tech_yn(){
    return we_tech_yn;
}


public String getWe_thumb_path(){
    return we_thumb_path;
}


public Date getWe_visit_date(){
    return we_visit_date;
}


public Date getWe_upd_date(){
    return we_upd_date;
}


public String getWe_user_site(){
    return we_user_site;
}


}