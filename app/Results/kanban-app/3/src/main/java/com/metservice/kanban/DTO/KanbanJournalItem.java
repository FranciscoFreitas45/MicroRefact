package com.metservice.kanban.DTO;
 import org.joda.time.LocalDateTime;
import com.metservice.kanban.utils.DateUtils;
public class KanbanJournalItem implements Comparable<KanbanJournalItem>{

 private  Integer id;

 private  String text;

 private  LocalDateTime date;

 private  String userName;

public KanbanJournalItem(String date, String text, String userName) {
    this(null, date, text, userName);
}public KanbanJournalItem(Integer id, String date, String text, String userName) {
    this.id = id;
    this.text = text;
    this.date = LocalDateTime.parse(date);
    this.userName = userName;
}
public String getText(){
    return text;
}


public String getDateStr(){
    return date.toString(DateUtils.DATE_FORMAT_STR);
}


public String getUserName(){
    return userName;
}


public LocalDateTime getDate(){
    return date;
}


public Integer getId(){
    return id;
}


}