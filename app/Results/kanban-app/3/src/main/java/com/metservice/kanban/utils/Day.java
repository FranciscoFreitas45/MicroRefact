package com.metservice.kanban.utils;
 import org.joda.time.LocalDate;
public class Day implements Comparable<Day>{

 private  LocalDate date;

public Day(LocalDate date) {
    this.date = date;
}
@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    Day other = (Day) obj;
    if (date == null) {
        if (other.date != null) {
            return false;
        }
    } else if (!date.equals(other.date)) {
        return false;
    }
    return true;
}


public String toString(){
    return DateUtils.formatConventionalNewZealandDate(date);
}


public LocalDate getDate(){
    return date;
}


@Override
public int compareTo(Day o){
    return this.date.compareTo(o.date);
}


}