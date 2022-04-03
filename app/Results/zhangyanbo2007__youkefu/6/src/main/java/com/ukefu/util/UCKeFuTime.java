package com.ukefu.util;
 import java.util.Arrays;
public class UCKeFuTime {

 public  int SECOND;

 public  int MINUTE;

 public  int HOUR;

 public  int DAY;

 private  int[] maxFields;

 private  int[] minFields;

 private  String timeSeparator;

 private  int[] fields;

/**
 * 无参构造，将各字段置为 0
 */
public UCKeFuTime() {
    this(0, 0, 0, 0);
}/**
 * 使用时、分构造一个时间
 * @param hour      小时
 * @param minute    分钟
 */
public UCKeFuTime(int hour, int minute) {
    this(0, hour, minute, 0);
}/**
 * 使用时、分、秒构造一个时间
 * @param hour      小时
 * @param minute    分钟
 * @param second    秒
 */
public UCKeFuTime(int hour, int minute, int second) {
    this(0, hour, minute, second);
}/**
 * 使用一个字符串构造时间<br/>
 * UCKeFuTime time = new UCKeFuTime("14:22:23");
 * @param time      字符串格式的时间，默认采用“:”作为分隔符
 */
public UCKeFuTime(String time) {
    this(time, null);
}/**
 * 使用天、时、分、秒构造时间，进行全字符的构造
 * @param day       天
 * @param hour      时
 * @param minute    分
 * @param second    秒
 */
public UCKeFuTime(int day, int hour, int minute, int second) {
    initialize(day, hour, minute, second);
}/**
 * 使用一个字符串构造时间，指定分隔符<br/>
 * UCKeFuTime time = new UCKeFuTime("14-22-23", "-");
 * @param time      字符串格式的时间
 */
public UCKeFuTime(String time, String timeSeparator) {
    if (timeSeparator != null) {
        setTimeSeparator(timeSeparator);
    }
    parseTime(time);
}
public void parseTimeField(String time,String t,int field){
    if (field < SECOND || t.length() < 1) {
        parseTimeException(time);
    }
    char[] chs = t.toCharArray();
    int n = 0;
    for (int i = 0; i < chs.length; i++) {
        if (chs[i] <= ' ') {
            continue;
        }
        if (chs[i] >= '0' && chs[i] <= '9') {
            n = n * 10 + chs[i] - '0';
            continue;
        }
        parseTimeException(time);
    }
    set(field, n);
}


public void set(int field,int value){
    if (value < minFields[field]) {
        throw new IllegalArgumentException(value + ", time value must be positive.");
    }
    fields[field] = value % (maxFields[field] + 1);
    // 进行进位计算
    int carry = value / (maxFields[field] + 1);
    if (carry > 0) {
        int upFieldValue = get(field + 1);
        set(field + 1, upFieldValue + carry);
    }
}


public void parseTimeException(String time){
    throw new IllegalArgumentException(time + ", time format error, HH" + this.timeSeparator + "mm" + this.timeSeparator + "ss");
}


public UCKeFuTime addTime(UCKeFuTime time){
    UCKeFuTime result = new UCKeFuTime();
    // 进位标志
    int up = 0;
    for (int i = 0; i < fields.length; i++) {
        int sum = fields[i] + time.fields[i] + up;
        up = sum / (maxFields[i] + 1);
        result.fields[i] = sum % (maxFields[i] + 1);
    }
    return result;
}


public StringBuilder buildString(StringBuilder sb,int field){
    if (fields[field] < 10) {
        sb.append('0');
    }
    return sb.append(fields[field]);
}


public UCKeFuTime subtractTime(UCKeFuTime time){
    UCKeFuTime result = new UCKeFuTime();
    // 退位标志
    int down = 0;
    for (int i = 0, k = fields.length - 1; i < k; i++) {
        int difference = fields[i] + down;
        if (difference >= time.fields[i]) {
            difference -= time.fields[i];
            down = 0;
        } else {
            difference += maxFields[i] + 1 - time.fields[i];
            down = -1;
        }
        result.fields[i] = difference;
    }
    result.fields[DAY] = fields[DAY] - time.fields[DAY] + down;
    return result;
}


public void setTimeSeparator(String timeSeparator){
    this.timeSeparator = timeSeparator;
}


public String getTimeSeparator(){
    return timeSeparator;
}


public int hashCode(){
    final int PRIME = 31;
    int result = 1;
    result = PRIME * result + Arrays.hashCode(fields);
    return result;
}


public int get(int field){
    if (field < 0 || field > fields.length - 1) {
        throw new IllegalArgumentException(field + ", field value is error.");
    }
    return fields[field];
}


public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    final UCKeFuTime other = (UCKeFuTime) obj;
    if (!Arrays.equals(fields, other.fields)) {
        return false;
    }
    return true;
}


public String toString(){
    StringBuilder sb = new StringBuilder(16);
    // 天
    // sb.append(fields[DAY]).append(',').append(' ');
    buildString(sb, HOUR).append(timeSeparator);
    buildString(sb, MINUTE).append(timeSeparator);
    buildString(sb, SECOND);
    return sb.toString();
}


public void initialize(int day,int hour,int minute,int second){
    set(DAY, day);
    set(HOUR, hour);
    set(MINUTE, minute);
    set(SECOND, second);
}


public void parseTime(String time){
    if (time == null) {
        initialize(0, 0, 0, 0);
        return;
    }
    String t = time;
    int field = DAY;
    set(field--, 0);
    int p = -1;
    while ((p = t.indexOf(timeSeparator)) > -1) {
        parseTimeField(time, t.substring(0, p), field--);
        t = t.substring(p + timeSeparator.length());
    }
    parseTimeField(time, t, field--);
}


}