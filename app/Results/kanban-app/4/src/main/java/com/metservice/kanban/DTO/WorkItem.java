package com.metservice.kanban.DTO;
 import com.metservice.kanban.utils.DateUtils.parseIsoDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDate;
import com.google.common.base.Preconditions;
import com.metservice.kanban.utils.DateUtils;
import com.metservice.kanban.utils.WorkingDayUtils;
public class WorkItem {

 public  Comparator<WorkItem> LAST_PHASE_DATE_COMPARATOR;

 public  int ROOT_WORK_ITEM_ID;

 private  String NEWLINE;

 private  String NO_ITEM;

 private  int id;

 private  int parentId;

 private  WorkItemType type;

 private  String name;

 private  int averageCaseEstimate;

 private  int worstCaseEstimate;

 private  int importance;

 private  String notes;

 private  boolean excluded;

 private  boolean blocked;

 private  HtmlColour colour;

 private  Map<String,LocalDate> datesByPhase;

 private  String currentPhase;

 private  boolean mustHave;

 private  List<String> workStreams;

 private  List<WorkItemComment> comments;

public WorkItem(int id, WorkItemType type, String advanceToPhase) {
    this(id, ROOT_WORK_ITEM_ID, type, advanceToPhase);
}public WorkItem(int id, int parentId, WorkItemType type, String advanceToPhase) {
    this(id, parentId, type);
    int targetPhaseIndex = type.getPhases().indexOf(advanceToPhase);
    if (targetPhaseIndex == -1) {
        throw new IllegalArgumentException("cannot advance; named phase does not exist: " + advanceToPhase);
    }
    for (int i = 0; i < targetPhaseIndex + 1; i++) {
        advance(parseIsoDate("1970-01-01"));
    }
}public WorkItem(int id, WorkItemType workItemType) {
    this(id, ROOT_WORK_ITEM_ID, workItemType);
}/**
 * Default constructor for WorkItem
 * @param id - id of the item we are creating
 * @param parentId - parent item's id
 * @param type - type of WorkItem
 */
public WorkItem(int id, int parentId, WorkItemType type) {
    this.id = id;
    this.parentId = parentId;
    this.type = type;
    this.name = "";
    this.averageCaseEstimate = 0;
    this.importance = 0;
    this.notes = "";
    this.excluded = false;
    this.blocked = false;
    this.colour = new HtmlColour("FFFFFF");
    this.worstCaseEstimate = 0;
    this.mustHave = false;
}
public String getName(){
    return name;
}


public void setAverageCaseEstimate(int averageCaseEstimate){
    this.averageCaseEstimate = averageCaseEstimate;
}


public Map<String,Integer> getPhaseDurations(){
    Map<String, Integer> phaseDurations = new HashMap<String, Integer>();
    LocalDate previousDate = null;
    String previousPhase = null;
    LocalDate today = new LocalDate();
    previousDate = fillMissingPhaseDates(previousDate);
    for (String phase : this.getType().getPhases()) {
        LocalDate date = this.getDate(phase);
        if (date == null) {
            date = today;
        }
        if (previousDate != null && previousPhase != null) {
            int diffInDays = WorkingDayUtils.getWorkingDaysBetween(previousDate, date);
            phaseDurations.put(previousPhase, diffInDays);
        }
        previousDate = date;
        previousPhase = phase;
    }
    // Last item will always have a duration between it's start date and now.
    if (previousDate != null && previousPhase != null) {
        int diffInDays = WorkingDayUtils.getWorkingDaysBetween(previousDate, today);
        phaseDurations.put(previousPhase, diffInDays);
    }
    return phaseDurations;
}


public int getWorstCaseEstimate(){
    return worstCaseEstimate;
}


public HtmlColour getColour(){
    return colour;
}


public List<WorkItemComment> getComments(){
    return Collections.unmodifiableList(this.comments);
}


public int getImportance(){
    return importance;
}


public LocalDate getLastPhaseDate(){
    return getDate(currentPhase);
}


public void setExcluded(boolean excluded){
    this.excluded = excluded;
}


public void setWorstCaseEstimate(int worstCaseEstimate){
    this.worstCaseEstimate = worstCaseEstimate;
}


public WorkItemType getType(){
    return type;
}


public int getVariance(){
    int deviation = getWorstCaseEstimate() - getAverageCaseEstimate();
    return deviation * deviation;
}


public String getTruncatedName(){
    if (name == null) {
        return null;
    }
    return name.substring(0, Math.min(name.length(), 40));
}


public int getParentId(){
    return parentId;
}


public String getNotesAndBlock(){
    if (isBlocked()) {
        return StringUtils.defaultIfEmpty(getNotes(), "") + NEWLINE + getLastBlockedComment();
    } else {
        return getNotes();
    }
}


public String getWorkStreamsAsString(){
    return StringUtils.join(workStreams, ',');
}


public int getWorkingDaysOnCurrentPhase(){
    return WorkingDayUtils.getWorkingDaysBetween(getDate(getCurrentPhase()), new LocalDate());
}


public WorkItem withNewParent(int newParentId){
    WorkItem workItem = new WorkItem(id, newParentId, type);
    workItem.name = name;
    workItem.averageCaseEstimate = averageCaseEstimate;
    workItem.importance = importance;
    workItem.notes = notes;
    workItem.datesByPhase.putAll(datesByPhase);
    workItem.currentPhase = currentPhase;
    workItem.excluded = excluded;
    workItem.colour = colour;
    workItem.blocked = blocked;
    workItem.comments.addAll(comments);
    return workItem;
}


public boolean isBlocked(){
    return blocked;
}


public int getId(){
    return id;
}


public int getAverageCaseEstimate(){
    return averageCaseEstimate;
}


public List<String> getWorkStreams(){
    return workStreams;
}


public Map<String,LocalDate> getDatesByPhase(){
    return datesByPhase;
}


public String getNotes(){
    return notes;
}


public String getLastBlockedComment(){
    WorkItemComment lastComment = null;
    for (WorkItemComment c : getComments()) {
        if (isBlockedComment(c) && (lastComment == null || lastComment.getWhenAdded().isBefore(c.getWhenAdded()))) {
            lastComment = c;
        }
    }
    if (lastComment == null) {
        return "";
    }
    return lastComment.getCommentText() + " [" + lastComment.getAddedBy() + "]";
}


public LocalDate getDate(String phase){
    return datesByPhase.get(phase);
}


public boolean isInWorkStream(String workStream){
    // TODO this should rather ask the parent for work stream
    if (!isTopLevel()) {
        return true;
    }
    if (workStream == null || "".equals(workStream)) {
        return true;
    }
    return workStreams.contains(workStream);
}


public String getCurrentPhase(){
    if (currentPhase == null) {
        throw new IllegalStateException("work item is not yet in any phase");
    }
    return currentPhase;
}


public void setImportance(int importance){
    this.importance = importance;
}


public boolean isTopLevel(){
    return parentId == ROOT_WORK_ITEM_ID;
}


public String determineCurrentPhase(){
    String newCurrentPhase = null;
    for (String phase : type.getPhases()) {
        if (hasDate(phase)) {
            newCurrentPhase = phase;
        }
    }
    return newCurrentPhase;
}


public String getPhaseOnDate(LocalDate date){
    String phaseOnDate = null;
    for (String phase : type.getPhases()) {
        if (hasDate(phase) && !this.getDate(phase).isAfter(date)) {
            phaseOnDate = phase;
        }
    }
    return phaseOnDate;
}


public String getQuickOverview(){
    StringBuilder overview = new StringBuilder();
    overview.append("Importance : ").append(getImportance()).append(NEWLINE);
    overview.append("Streams : ");
    if (getWorkStreams().isEmpty()) {
        overview.append(NO_ITEM);
    } else {
        for (String s : getWorkStreams()) {
            overview.append(" ").append(s);
        }
    }
    overview.append(NEWLINE);
    final String notes = getNotes();
    overview.append("Notes : ").append((notes != null ? notes : NO_ITEM)).append(NEWLINE);
    final String lastComment = getLastComment();
    overview.append("Last comment : ").append("".equals(lastComment) ? NO_ITEM : lastComment).append(NEWLINE);
    return overview.toString();
}


public String getLastComment(){
    WorkItemComment lastComment = null;
    for (WorkItemComment c : getComments()) {
        if (lastComment == null || lastComment.getWhenAdded().isBefore(c.getWhenAdded())) {
            lastComment = c;
        }
    }
    if (lastComment == null) {
        return "";
    }
    return lastComment.getCommentText() + " [" + lastComment.getAddedBy() + " @ " + lastComment.getWhenAdded().toString(DateUtils.DATE_FORMAT_STR) + "]";
}


public List<WorkItemComment> getCommentsInReverseOrder(){
    List<WorkItemComment> newList = new ArrayList<WorkItemComment>(comments);
    Collections.reverse(newList);
    return Collections.unmodifiableList(newList);
}


public void setMustHave(boolean mustHave){
    this.mustHave = mustHave;
}


}