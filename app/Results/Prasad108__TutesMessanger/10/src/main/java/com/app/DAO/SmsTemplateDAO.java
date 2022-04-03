package com.app.DAO;
 import java.util.List;
import com.app.pojo.Institute;
import com.app.pojo.SmsTemplate;
public interface SmsTemplateDAO {


public List<SmsTemplate> getall()
;

public SmsTemplate edit(int id)
;

public SmsTemplate find(int id)
;

public void create(SmsTemplate smsTemplate)
;

public void update(SmsTemplate smsTemplate)
;

public void delet(int id)
;

public List<SmsTemplate> getallOfParticularInstitute(Institute id)
;

}