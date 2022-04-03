package com.metservice.kanban.model;
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


public boolean isExcluded(){
    return excluded;
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


public void advance(LocalDate date){
    if (!type.hasPhaseAfter(currentPhase)) {
        throw new IllegalStateException(this + " cannot advance: it is already in its final phase");
    }
    // Set the start date of the next phase to date
    setDate(type.getPhaseAfter(currentPhase), date);
}


public int getWorstCaseEstimate(){
    return worstCaseEstimate;
}


public void setNotes(String notes){
    this.notes = notes;
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


public void addComment(WorkItemComment comment){
    Preconditions.checkNotNull(comment);
    comment.setParentId(id);
    this.comments.add(comment);
}


public void setExcluded(boolean excluded){
    this.excluded = excluded;
}


public void resetCommentsAndReplaceWith(List<WorkItemComment> newComments){
    this.comments.clear();
    if (newComments != null) {
        this.comments.addAll(newComments);
    }
}


public void setWorstCaseEstimate(int worstCaseEstimate){
    this.worstCaseEstimate = worstCaseEstimate;
}


public void stop(){
    blocked = !blocked;
}


public WorkItemType getType(){
    return type;
}


public LocalDate fillMissingPhaseDates(LocalDate previousDate){
    // fill missing dates before current phase
    String newCurrentPhase = determineCurrentPhase();
    for (ListIterator<String> i = getType().getPhases().listIterator(getType().getPhases().size()); i.hasPrevious(); ) {
        String phase = i.previous();
        if (getDate(phase) != null) {
            previousDate = getDate(phase);
        }
        if (this.getType().isPhaseBefore(phase, newCurrentPhase) && getDate(phase) == null) {
            setDate(phase, previousDate);
        }
    }
    return previousDate;
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


public void setName(String name){
    this.name = name;
}


public int getWorkingDaysOnCurrentPhase(){
    return WorkingDayUtils.getWorkingDaysBetween(getDate(getCurrentPhase()), new LocalDate());
}


@Override
public int compare(WorkItem o1,WorkItem o2){
    return o2.getLastPhaseDate().compareTo(o1.getLastPhaseDate());
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


public void setColour(String colour){
    if (colour != null && colour.length() > 0) {
        this.colour = new HtmlColour(colour);
    }
}


public boolean isBlocked(){
    return blocked;
}


public boolean isMustHave(){
    return mustHave;
}


public int getId(){
    return id;
}


public int getAverageCaseEstimate(){
    return averageCaseEstimate;
}


public boolean isBlockedComment(WorkItemComment c){
    return c.getCommentText().startsWith("Blocked:");
}


public List<String> getWorkStreams(){
    return workStreams;
}


public Map<String,LocalDate> getDatesByPhase(){
    return datesByPhase;
}


public void setDateAsString(String phase,String dateAsString){
    setDate(phase, parseIsoDate(dateAsString));
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


@Override
public int hashCode(){
    return id;
}


public LocalDate getDate(String phase){
    return datesByPhase.get(phase);
}


public void setBlocked(boolean blocked){
    this.blocked = blocked;
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


public boolean isCompleted(){
    return !type.hasPhaseAfter(currentPhase);
}


public void setImportance(int importance){
    this.importance = importance;
}


public void setWorkStreams(List<String> workStreams){
    this.workStreams = workStreams;
}


public boolean isTopLevel(){
    return parentId == ROOT_WORK_ITEM_ID;
}


public boolean hasDate(String phase){
    return datesByPhase.containsKey(phase);
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


public void setWorkStreamsAsString(String workStream){
    if (workStream == null) {
        this.workStreams = new ArrayList<String>();
    } else {
        this.workStreams = new ArrayList<String>();
        for (String ws : StringUtils.split(workStream, ',')) {
            this.workStreams.add(StringUtils.trim(ws));
        }
    }
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


@Override
public boolean equals(Object object){
    if (object == null) {
        return false;
    } else if (object instanceof WorkItem) {
        return ((WorkItem) object).id == id;
    } else {
        return false;
    }
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


public void setDate(String phase,LocalDate date){
    if (date == null) {
        datesByPhase.remove(phase);
    } else {
        datesByPhase.put(phase, date);
    }
    currentPhase = determineCurrentPhase();
}


public List<WorkItemComment> getCommentsInReverseOrder(){
    List<WorkItemComment> newList = new ArrayList<WorkItemComment>(comments);
    Collections.reverse(newList);
    return Collections.unmodifiableList(newList);
}


@Override
public String toString(){
    return "work item " + getId() + " (" + getName() + ")";
}


public void setMustHave(boolean mustHave){
    this.mustHave = mustHave;
}


}