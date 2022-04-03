package com.qidian.hcm.common.utils;
 import com.qidian.hcm.common.config.HCMConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class IDGeneratorUtil {

@Autowired
 private  HCMConfig hcmConfig;

 private  long START_TIME_STAMP;

 private  long sequence;

 private  long lastTimeStamp;

 private  long SEQUENCE_BIT;

 private  long MACHINE_BIT;

 private  long DATACENTER_BIT;

 private  long MAX_DATACENTER_NUM;

 private  long MAX_MACHINE_NUM;

 private  long MAX_SEQUENCE;

 private  long MACHINE_LEFT;

 private  long DATACENTER_LEFT;

 private  long TIMESTMP_LEFT;


public long getNewTimpStamp(){
    return System.currentTimeMillis();
}


public long getNextId(){
    if (hcmConfig.getSnowFlakeDatacenterId() > MAX_DATACENTER_NUM || hcmConfig.getSnowFlakeDatacenterId() < 0) {
        throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
    }
    if (hcmConfig.getSnowFlakeMachineId() > MAX_MACHINE_NUM || hcmConfig.getSnowFlakeMachineId() < 0) {
        throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
    }
    long currStmp = getNewTimpStamp();
    if (currStmp < lastTimeStamp) {
        throw new IllegalArgumentException("Clock moved backwards.  Refusing to generate id");
    }
    if (currStmp == lastTimeStamp) {
        // 相同毫秒内，序列号自增
        sequence = (sequence + 1) & MAX_SEQUENCE;
        // 同一毫秒的序列数已经达到最大
        if (sequence == 0L) {
            currStmp = getNextMill();
        }
    } else {
        // 不同毫秒内，序列号置为0
        sequence = 0L;
    }
    lastTimeStamp = currStmp;
    return // 时间戳部分
    (currStmp - START_TIME_STAMP) << TIMESTMP_LEFT | // 数据中心部分
    hcmConfig.getSnowFlakeDatacenterId() << DATACENTER_LEFT | // 机器标识部分
    hcmConfig.getSnowFlakeMachineId() << MACHINE_LEFT | // 序列号部分
    sequence;
}


public long getNextMill(){
    long mill = getNewTimpStamp();
    while (mill <= lastTimeStamp) {
        mill = getNewTimpStamp();
    }
    return mill;
}


}