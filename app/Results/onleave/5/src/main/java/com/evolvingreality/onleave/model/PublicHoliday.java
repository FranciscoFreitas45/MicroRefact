package com.evolvingreality.onleave.model;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
@Entity
@Table(name = "PUBLIC_HOLIDAY")
public class PublicHoliday extends AbstractAuditingEntity{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@NotNull
@Column(name = "title", nullable = false)
 private  String title;

@Column(name = "description")
 private  String description;

@NotNull
@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
@Column(name = "holiday_date", nullable = false)
 private  DateTime holidayDate;

@ManyToOne
@JoinColumn(name = "holiday_calendar_id")
 private  HolidayCalendar holidayCalendar;


public void setHolidayDate(DateTime holidayDate){
    this.holidayDate = holidayDate;
}


public void setHolidayCalendar(HolidayCalendar holidayCalendar){
    this.holidayCalendar = holidayCalendar;
}


public String getTitle(){
    return title;
}


public void setId(Long id){
    this.id = id;
}


public void setTitle(String title){
    this.title = title;
}


public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public HolidayCalendar getHolidayCalendar(){
    return holidayCalendar;
}


public DateTime getHolidayDate(){
    return holidayDate;
}


}