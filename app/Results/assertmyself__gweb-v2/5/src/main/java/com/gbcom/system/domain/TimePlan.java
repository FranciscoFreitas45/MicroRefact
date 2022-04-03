package com.gbcom.system.domain;
 import com.gbcom.system.domain.base.BaseTimePlan;
import java.util.Calendar;
public class TimePlan extends BaseTimePlan{

 public  int TIME_PLAN_OWNERTYPE_DB_BACKUP;

 public  int TIME_PLAN_TYPE_ONE;

 public  int TIME_PLAN_TYPE_DAY;

 public  int TIME_PLAN_TYPE_WEEK;

 public  int TIME_PLAN_TYPE_MONTH;

 public  int TIME_PLAN_TYPE_MINUTE;

/*[CONSTRUCTOR MARKER BEGIN]*/
public TimePlan() {
    super();
    this.setType(TimePlan.TIME_PLAN_TYPE_ONE);
    this.setOwnerType(TimePlan.TIME_PLAN_OWNERTYPE_DB_BACKUP);
    Calendar calendar = Calendar.getInstance();
    this.setBeginTime(new java.sql.Timestamp(calendar.getTimeInMillis()));
    this.setRepeatTime("00:00:00");
    this.setSelectWeek("");
    this.setSelectDay("");
    this.setIntervalTime(60);
    this.setState(true);
}/**
 * Constructor for primary key
 */
public TimePlan(Long id) {
    super(id);
}
}