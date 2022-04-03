package com.zammc.service.activity;
 import com.zammc.domain.activity.ActivityEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
public interface ActivityService {


public void queryActivityPage(ActivityEntity activityEntity,PageBean pageBean)
;

public Message deleteActivity(ActivityEntity activityEntity)
;

public Message forbidden(ActivityEntity activityEntity)
;

public Message addActivity(ActivityEntity activityEntity)
;

public Message startUsing(ActivityEntity activityEntity)
;

}