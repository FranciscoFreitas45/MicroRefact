package com.gbcom.system.timer.service;
 import com.gbcom.system.daoservice.TimePlanService;
import com.gbcom.system.domain.TimePlan;
import com.gbcom.system.timer.ITimerHandler;
import com.gbcom.system.timer.impl.DataFileTimeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util;
public class SysTimerService {

 private  Logger logger;

 private  long checkPeriod;

 private  Timer checkServer;

@Autowired
 private  TimePlanService timePlanService;

/**
 * 构造函数
 *
 * @param checkPeriod
 *            long
 */
public SysTimerService(long checkPeriod) {
    this.checkPeriod = checkPeriod;
    checkServer = new Timer();
}
public void schedule(int ownerType){
    if (TimePlan.TIME_PLAN_OWNERTYPE_DB_BACKUP == ownerType) {
        // 执行数据库备份
        ITimerHandler timerHandler = new DataFileTimeHandler();
        timerHandler.handle();
    }
}


public void start(){
    // 启动checkServer
    checkServer.schedule(new TimerTask() {

        @Override
        public void run() {
            // long currentTime = System.currentTimeMillis();
            // Date date = new Date();
            // SimpleDateFormat sdf = new
            // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // String beginDate = sdf.format(date);
            // String beginTime = beginDate.substring(11, 16);
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int seconds = calendar.get(Calendar.SECOND);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            // 检查定时任务
            List<TimePlan> list = timePlanService.findByProperty("state", true);
            if (list.size() > 0) {
                int i = 0;
                for (i = 0; i < list.size(); i++) {
                    TimePlan timePlan = list.get(i);
                    // 检查是否可用
                    // if (!timePlan.getState()) {
                    // continue;
                    // }
                    byte[] repeatBits = timePlan.getRepeatBits();
                    // 分钟
                    int interval = timePlan.getIntervalTime();
                    int index = 0;
                    int offset = 0;
                    int ownerType = timePlan.getOwnerType();
                    switch(timePlan.getType()) {
                        case TimePlan.TIME_PLAN_TYPE_ONE:
                            // 一次性任务
                            // Date = new Date(timePlan.getYear()-1900,
                            // timePlan.getMonth()-1, timePlan.getDay(),
                            // timePlan.getHour(), timePlan.getMin(),
                            // timePlan.getSec());
                            if (timePlan.getYear() == year && timePlan.getMin() == month && timePlan.getDay() == day && timePlan.getHour() == hour && timePlan.getMin() == minute) {
                                // 调度
                                schedule(ownerType);
                            } else {
                                continue;
                            }
                            break;
                        case TimePlan.TIME_PLAN_TYPE_DAY:
                            // 每天重复
                            if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                                // 调度
                                schedule(ownerType);
                            } else {
                                continue;
                            }
                            break;
                        case TimePlan.TIME_PLAN_TYPE_WEEK:
                            // 每周重复
                            index = 0;
                            offset = dayOfWeek - 1;
                            if (((repeatBits[index] >>> (7 - offset)) & 0x01) == 1) {
                                if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                                    // 调度
                                    schedule(ownerType);
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                            break;
                        case TimePlan.TIME_PLAN_TYPE_MONTH:
                            // 每月重复
                            if (dayOfMonth % 8 == 0) {
                                index = dayOfMonth / 8 - 1;
                                offset = 8 - 1;
                            } else {
                                index = dayOfMonth / 8;
                                offset = dayOfMonth % 8 - 1;
                            }
                            if (((repeatBits[index] >>> (7 - offset)) & 0x01) == 1) {
                                if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                                    // 调度
                                    schedule(ownerType);
                                } else {
                                    continue;
                                }
                            } else {
                                continue;
                            }
                            break;
                        case TimePlan.TIME_PLAN_TYPE_MINUTE:
                            // 每隔N分钟重复
                            calendar.set(Calendar.SECOND, 0);
                            long calendarTime = calendar.getTimeInMillis();
                            Calendar cal = Calendar.getInstance();
                            cal.set(timePlan.getYear(), timePlan.getMonth() - 1, timePlan.getDay(), timePlan.getHour(), timePlan.getMin(), 0);
                            long calTime = cal.getTimeInMillis();
                            if (calendarTime < calTime) {
                                continue;
                            }
                            long tabMinutes = (calendarTime - calTime) / (1000 * 60);
                            if (tabMinutes % interval == 0) {
                                // 调度
                                schedule(ownerType);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }, 1000, checkPeriod);
}


@Override
public void run(){
    // long currentTime = System.currentTimeMillis();
    // Date date = new Date();
    // SimpleDateFormat sdf = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // String beginDate = sdf.format(date);
    // String beginTime = beginDate.substring(11, 16);
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);
    int seconds = calendar.get(Calendar.SECOND);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    // 检查定时任务
    List<TimePlan> list = timePlanService.findByProperty("state", true);
    if (list.size() > 0) {
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            TimePlan timePlan = list.get(i);
            // 检查是否可用
            // if (!timePlan.getState()) {
            // continue;
            // }
            byte[] repeatBits = timePlan.getRepeatBits();
            // 分钟
            int interval = timePlan.getIntervalTime();
            int index = 0;
            int offset = 0;
            int ownerType = timePlan.getOwnerType();
            switch(timePlan.getType()) {
                case TimePlan.TIME_PLAN_TYPE_ONE:
                    // 一次性任务
                    // Date = new Date(timePlan.getYear()-1900,
                    // timePlan.getMonth()-1, timePlan.getDay(),
                    // timePlan.getHour(), timePlan.getMin(),
                    // timePlan.getSec());
                    if (timePlan.getYear() == year && timePlan.getMin() == month && timePlan.getDay() == day && timePlan.getHour() == hour && timePlan.getMin() == minute) {
                        // 调度
                        schedule(ownerType);
                    } else {
                        continue;
                    }
                    break;
                case TimePlan.TIME_PLAN_TYPE_DAY:
                    // 每天重复
                    if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                        // 调度
                        schedule(ownerType);
                    } else {
                        continue;
                    }
                    break;
                case TimePlan.TIME_PLAN_TYPE_WEEK:
                    // 每周重复
                    index = 0;
                    offset = dayOfWeek - 1;
                    if (((repeatBits[index] >>> (7 - offset)) & 0x01) == 1) {
                        if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                            // 调度
                            schedule(ownerType);
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    break;
                case TimePlan.TIME_PLAN_TYPE_MONTH:
                    // 每月重复
                    if (dayOfMonth % 8 == 0) {
                        index = dayOfMonth / 8 - 1;
                        offset = 8 - 1;
                    } else {
                        index = dayOfMonth / 8;
                        offset = dayOfMonth % 8 - 1;
                    }
                    if (((repeatBits[index] >>> (7 - offset)) & 0x01) == 1) {
                        if (timePlan.getHour() == hour && timePlan.getMin() == minute) {
                            // 调度
                            schedule(ownerType);
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                    break;
                case TimePlan.TIME_PLAN_TYPE_MINUTE:
                    // 每隔N分钟重复
                    calendar.set(Calendar.SECOND, 0);
                    long calendarTime = calendar.getTimeInMillis();
                    Calendar cal = Calendar.getInstance();
                    cal.set(timePlan.getYear(), timePlan.getMonth() - 1, timePlan.getDay(), timePlan.getHour(), timePlan.getMin(), 0);
                    long calTime = cal.getTimeInMillis();
                    if (calendarTime < calTime) {
                        continue;
                    }
                    long tabMinutes = (calendarTime - calTime) / (1000 * 60);
                    if (tabMinutes % interval == 0) {
                        // 调度
                        schedule(ownerType);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}


public void close(){
    checkServer.cancel();
}


}