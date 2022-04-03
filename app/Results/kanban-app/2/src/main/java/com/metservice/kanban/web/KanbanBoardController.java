package com.metservice.kanban.web;
 import com.metservice.kanban.model.WorkItem.ROOT_WORK_ITEM_ID;
import com.metservice.kanban.utils.DateUtils.currentLocalDate;
import com.metservice.kanban.utils.DateUtils.parseConventionalNewZealandDate;
import java.lang.Integer.parseInt;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.metservice.kanban.EstimatesDao;
import com.metservice.kanban.KanbanService;
import com.metservice.kanban.charts.burnup.BurnUpChartGenerator;
import com.metservice.kanban.charts.burnup.DefaultBurnUpChartGenerator;
import com.metservice.kanban.charts.burnup.DefaultChartWriter;
import com.metservice.kanban.charts.cumulativeflow.CumulativeFlowChartBuilder;
import com.metservice.kanban.charts.cycletime.CycleTimeChartBuilder;
import com.metservice.kanban.model.BoardIdentifier;
import com.metservice.kanban.model.EstimatesProject;
import com.metservice.kanban.model.KanbanBoard;
import com.metservice.kanban.model.KanbanJournalItem;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemComment;
import com.metservice.kanban.model.WorkItemTree;
import com.metservice.kanban.model.WorkItemType;
import com.metservice.kanban.utils.DateUtils;
import com.metservice.kanban.utils.JsonLocalDateTimeConvertor;
import com.metservice.kanban.Interface.EstimatesDao;
import com.metservice.kanban.DTO.WorkItem;
import com.metservice.kanban.DTO.BurnUpChartGenerator;
import com.metservice.kanban.DTO.WorkItemType;
import com.metservice.kanban.DTO.CycleTimeChartBuilder;
import com.metservice.kanban.DTO.CumulativeFlowChartBuilder;
import com.metservice.kanban.DTO.EstimatesProject;
import com.metservice.kanban.DTO.BurnUpChartGenerator;
@Controller
@RequestMapping("{projectName}")
@SessionAttributes("workStreams")
public class KanbanBoardController {

 private  Logger LOGGER;

 private  int DEFAULT_CHART_WIDTH;

 private  int DEFAULT_CHART_HEIGHT;

 private  int MAX_PROJECT_NAME_LENGTH;

 private  String PROJECT_NAME_INVALID_CHARS;

 public  int DEFAULT_MONTHS_DISPLAY;

@Autowired
 private  KanbanService kanbanService;

@Autowired
 private EstimatesDao estimatesDao;

 private  Gson gson;

public KanbanBoardController() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new JsonLocalDateTimeConvertor());
    this.gson = gsonBuilder.create();
}
public String formatDate(LocalDate date){
    if (date == null) {
        return "";
    }
    return date.toString(DateUtils.DATE_FORMAT_STR);
}


@RequestMapping(value = "print-items")
public ModelAndView printItems(KanbanProject project,String projectName,String[] ids){
    List<WorkItem> items = new ArrayList<WorkItem>();
    for (String id : ids) {
        items.add(project.getWorkItemTree().getWorkItem(parseInteger(id, 0)));
    }
    return new ModelAndView("/printCards.jsp", "items", items);
}


@RequestMapping(value = "edit-column-action", produces = "application/json")
@ResponseBody
public JsonStatus editColumn(KanbanProject project,String projectName,String itemType,String columnName,String newColumnName,Integer wipLimit){
    if (StringUtils.isEmpty(newColumnName)) {
        return JsonStatus.createErrorStatus("New column name cannot be empty");
    }
    if (wipLimit != null && wipLimit <= 0) {
        return JsonStatus.createErrorStatus("WIP Limit should be empty or positive value");
    }
    LOGGER.info("Editing column {} to {}, WIP = {}", new Object[] { columnName, newColumnName, wipLimit });
    WorkItemType workItemtype = project.getWorkItemTypes().getByName(itemType);
    kanbanService.setColumnWipLimit(projectName, workItemtype, columnName, wipLimit);
    if (!columnName.equals(newColumnName)) {
        if (!kanbanService.renameColumn(projectName, workItemtype, columnName, newColumnName)) {
            return JsonStatus.createErrorStatus("Cannot rename column");
        }
    }
    return JsonStatus.createOkStatus("OK");
}


@RequestMapping(value = "{board}/edit-item/{id}/importance", method = RequestMethod.POST)
public ResponseEntity<String> updateItemImportance(KanbanProject project,String boardType,int id,String newValue){
    // why are these methods marked as synchronized?!?
    // Get the item which is being edited
    WorkItem workItem = project.getWorkItemTree().getWorkItem(id);
    workItem.setImportance(parseInteger(newValue, 0));
    project.save();
    // Go home.
    return new ResponseEntity<String>(String.format("Importance change successfully.  New importance: %s", workItem.getImportance()), HttpStatus.OK);
}


@RequestMapping("journal")
public ModelAndView journalBoard(KanbanProject project,String projectName,String scrollTop,Map<String,String> workStreams,String error){
    Map<String, Object> model = initBoard("journal", projectName, error, scrollTop);
    model.put("kanbanJournal", project.getJournal());
    return new ModelAndView("/journal.jsp", model);
}


@RequestMapping(value = "{board}/edit-item/{id}/size", method = RequestMethod.POST)
public ResponseEntity<String> updateItemSize(KanbanProject project,String boardType,int id,String newValue){
    // why are these methods marked as synchronized?!?
    // Get the item which is being edited
    WorkItem workItem = project.getWorkItemTree().getWorkItem(id);
    workItem.setAverageCaseEstimate(parseInteger(newValue, 0));
    project.save();
    // Go home.
    return new ResponseEntity<String>(String.format("Size change successfully.  New size: %s", workItem.getAverageCaseEstimate()), HttpStatus.OK);
}


public WorkItemComment createBlockedComment(boolean isBlockedReason,String comment,String userName){
    if (isBlockedReason) {
        return new WorkItemComment(userName, "Blocked: " + comment);
    } else {
        return new WorkItemComment(userName, "Unblocked: " + comment);
    }
}


@RequestMapping("{board}/move-item-action")
public RedirectView moveItemAction(KanbanProject project,String boardType,String id,String targetId,Boolean after,String scrollTop){
    int idAsInteger = parseInt(id);
    int targetIdAsInteger = parseInt(targetId);
    project.move(idAsInteger, targetIdAsInteger, after);
    project.save();
    return new RedirectView(includeScrollTopPosition(boardType, scrollTop, id));
}


@RequestMapping("{board}/set-work-stream")
public RedirectView setWorkStream(KanbanProject project,String projectName,String boardType,String selectedWorkStream,String chartName,String workItemTypeName,Map<String,String> workStreams){
    workStreams.put(projectName, selectedWorkStream);
    String params = "";
    if (!StringUtils.isEmpty(chartName) && !StringUtils.isEmpty(workItemTypeName)) {
        params = "?chartName=" + chartName + "&workItemTypeName=" + workItemTypeName;
    }
    return new RedirectView("/projects/" + projectName + "/" + boardType + params, true);
}


public int parseInteger(String sizeStr,int defaultValue){
    try {
        return Integer.parseInt(sizeStr);
    } catch (NumberFormatException nfe) {
        return defaultValue;
    }
}


@RequestMapping(value = "comment", method = RequestMethod.POST)
public ResponseEntity<String> addComment(KanbanProject project,int id,String userName,String comment){
    WorkItemComment workItemComment = new WorkItemComment(userName, comment);
    WorkItem workItem = project.getWorkItemById(id);
    workItem.addComment(workItemComment);
    project.save();
    String body = gson.toJson(workItemComment);
    return new ResponseEntity<String>(body, HttpStatus.OK);
}


@RequestMapping("{board}/add-column-action")
public RedirectView addColumn(KanbanProject project,String projectName,String boardType,String name){
    String orig = kanbanService.getProjectConfiguration(projectName).getKanbanPropertiesFile().getContentAsString();
    Scanner sc = new Scanner(orig);
    String temp = "";
    StringBuilder newContent = new StringBuilder();
    boolean addedCol = false;
    while (sc.hasNext() && !StringUtils.isEmpty(name)) {
        temp = sc.nextLine();
        if (temp.contains("phases") && !addedCol) {
            addedCol = true;
            String[] phases = temp.split(",");
            String last = phases[phases.length - 1];
            // FOr when cancel is hit on the add column button
            if (!name.equals("null")) {
                last = name + "," + last;
            }
            for (int i = 0; i < phases.length - 1; i++) {
                newContent.append(phases[i]).append(",");
            }
            newContent.append(last).append("\n");
        } else if (temp.contains("boards.wall")) {
            // FOr when cancel is hit on the add column button
            if (name.equals("null")) {
                newContent.append(temp).append("\n");
            } else {
                temp += "," + name;
                newContent.append(temp).append("\n");
            }
        } else {
            newContent.append(temp).append("\n");
        }
    }
    // For when Ok button is pressed and no input is entered
    if (newContent.length() < 10) {
        newContent = new StringBuilder(orig);
    }
    kanbanService.editProject(projectName, newContent.toString());
    return new RedirectView("/projects/" + projectName + "/" + boardType, true);
}


@RequestMapping("burn-up-chart.png")
public void burnUpChartPng(KanbanProject project,BurnUpChartGenerator chartGenerator,String startDate,String endDate,String workStream,OutputStream outputStream){
    WorkItemTree tree = project.getWorkItemTree();
    WorkItemType type = project.getWorkItemTypes().getRoot().getValue();
    List<WorkItem> topLevelWorkItems = tree.getWorkItemsOfType(type, workStream);
    LocalDate start;
    LocalDate end;
    start = DateUtils.parseDate(startDate, null);
    end = DateUtils.parseDate(endDate, null);
    chartGenerator.generateBurnUpChart(project, type, topLevelWorkItems, start, end, outputStream);
}


@RequestMapping(value = "{board}/edit-item/{id}/name", method = RequestMethod.POST)
public ResponseEntity<String> updateItemName(KanbanProject project,String boardType,int id,String newValue){
    // why are these methods marked as synchronized?!?
    // Get the item which is being edited
    WorkItem workItem = project.getWorkItemTree().getWorkItem(id);
    workItem.setName(newValue);
    project.save();
    // Go home.
    return new ResponseEntity<String>(String.format("Name change successfully.  New name: %s", workItem.getName()), HttpStatus.OK);
}


public Map<String,Object> buildModel(String projectName,String boardType){
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("projectName", projectName);
    model.put("boardType", boardType);
    return model;
}


@RequestMapping(value = "{board}/block-item-action")
public RedirectView stopItemAction(KanbanProject project,String boardType,String id,String comment,String userName){
    int itemId = parseInt(id);
    WorkItem wi = project.getWorkItemById(itemId);
    project.stop(itemId);
    if (comment == null) {
        comment = "";
    }
    wi.addComment(createBlockedComment(wi.isBlocked(), comment, userName));
    project.save();
    // Redirect
    return new RedirectView("../" + boardType);
}


@ModelAttribute("service")
public KanbanService populateService(){
    return kanbanService;
}


@RequestMapping("{board}/open-project")
public RedirectView openProject(String projectName,String boardType,String newProjectName,String chartName,String workItemTypeName){
    String params = "";
    if (!StringUtils.isEmpty(chartName) && !StringUtils.isEmpty(workItemTypeName)) {
        params = "?chartName=" + chartName + "&workItemTypeName=" + workItemTypeName;
    }
    return new RedirectView("/projects/" + newProjectName + "/" + boardType + params, true);
}


public Map<String,Object> initBoard(String boardType,String projectName,String error,String scrollTop){
    // boardType = cleanBoardType(boardType);
    Map<String, Object> model = buildModel(projectName, boardType);
    // TODO model used to have kanbanTransaction now it has kanban... need
    // to fix view
    model.put("scrollTop", scrollTop == null ? "0" : scrollTop);
    model.put("error", error);
    return model;
}


@RequestMapping("{board}/delete-item-action")
public RedirectView deleteWorkItem(KanbanProject project,int id,String board){
    // Delete the workitem, save and redirect.
    project.deleteWorkItem(id);
    project.save();
    return new RedirectView("../" + board);
}


@RequestMapping("create-project-action")
public RedirectView createProjectAction(KanbanProject project,String projectName,String newProjectName,String content){
    kanbanService.createProject(newProjectName, content);
    return openProject(projectName, "wall", newProjectName, null, null);
}


@RequestMapping("")
public RedirectView redirectToWall(String projectName){
    return new RedirectView("/projects/" + projectName + "/wall", true);
}


@RequestMapping("completed")
public ModelAndView completedBoard(KanbanProject project,String projectName,String scrollTop,Map<String,String> workStreams,String error){
    Map<String, Object> model = initBoard("completed", projectName, error, scrollTop);
    model.put("type", project.getWorkItemTypes().getRoot().getValue());
    model.put("phase", project.getWorkItemTypes().getRoot().getValue().getCompletedPhase());
    model.put("board", project.getCompleted(workStreams.get(projectName)));
    return new ModelAndView("/completed.jsp", model);
}


@RequestMapping("{board}/edit-item-action")
public RedirectView editItemAction(KanbanProject project,String boardType,int id,Integer parentId,String name,String averageCaseEstimateStr,String worstCaseEstimateStr,String importanceStr,String notes,String color,String excludedStr,String workStreams,String redirectTo,HttpServletRequest request){
    // Get the item which is being edited
    WorkItem workItem = project.getWorkItemTree().getWorkItem(id);
    @SuppressWarnings("unchecked")
    Map<String, String[]> parameters = request.getParameterMap();
    int averageCaseEstimate = parseInteger(averageCaseEstimateStr, 0);
    int worstCaseEstimate = parseInteger(worstCaseEstimateStr, 0);
    int importance = parseInteger(importanceStr, 0);
    boolean excluded = parseBoolean(excludedStr);
    // Save all the updates
    workItem.setName(name);
    workItem.setAverageCaseEstimate(averageCaseEstimate);
    workItem.setWorstCaseEstimate(worstCaseEstimate);
    workItem.setImportance(importance);
    workItem.setNotes(notes);
    workItem.setExcluded(excluded);
    workItem.setColour(color);
    workItem.setWorkStreamsAsString(workStreams);
    // TODO Figure this out
    for (String phase : workItem.getType().getPhases()) {
        String key = "date-" + phase;
        String[] valueArray = parameters.get(key);
        if (valueArray != null) {
            if (valueArray[0].trim().isEmpty()) {
                workItem.setDate(phase, null);
            } else {
                workItem.setDate(phase, parseConventionalNewZealandDate(valueArray[0]));
            }
        }
    }
    // If it's changed parent, reset the parent.
    if (workItem.getParentId() != parentId) {
        project.getWorkItemTree().reparent(id, parentId);
    }
    // Save the whole project
    project.save();
    if ("print".equals(redirectTo)) {
        return new RedirectView("../print-items?printSelection=" + id);
    } else {
        return new RedirectView("../" + boardType);
    }
}


@ModelAttribute("projectName")
public String populateProjectName(String board){
    return board;
}


@RequestMapping("{board}/add-waitingcolumn-action")
public RedirectView addWaitingColumn(KanbanProject project,String projectName,String boardType,String name){
    String orig = kanbanService.getProjectConfiguration(projectName).getKanbanPropertiesFile().getContentAsString();
    Scanner sc = new Scanner(orig);
    String temp = "";
    StringBuilder newContent = new StringBuilder();
    while (sc.hasNext() && !StringUtils.isEmpty(name)) {
        temp = sc.nextLine();
        if (temp.contains(name)) {
            String[] phases = temp.split(",|=");
            for (int i = 0; i < phases.length; i++) {
                if (phases[i].equals(name)) {
                    newContent.append("Pre - ").append(name).append(",");
                }
                if (phases[i].contains(".")) {
                    newContent.append(phases[i]).append("=");
                } else {
                    newContent.append(phases[i]).append(",");
                }
            }
            newContent.append("\n");
        } else if (temp.contains("boards.wall")) {
            // FOr when cancel is hit on the add column button
            if (name.equals("null")) {
                newContent.append(temp).append("\n");
            } else {
                temp += "," + name;
                newContent.append(temp).append("\n");
            }
        } else {
            newContent.append(temp).append("\n");
        }
    }
    if (newContent.length() < 10) {
        newContent = new StringBuilder(orig);
    }
    kanbanService.editProject(projectName, newContent.toString());
    return new RedirectView("/projects/" + projectName + "/" + boardType, true);
}


@RequestMapping("{board}/edit-item")
public ModelAndView editItem(KanbanProject project,String board,String projectName,Integer id){
    // Get a model ready to take some attributes
    Map<String, Object> model = new HashMap<String, Object>();
    // buildModel(projectName, boardType);
    // Fetch the item we want to edit
    WorkItem workItem = project.getWorkItemTree().getWorkItem(id);
    // Add some variables to the model hashmap
    model.put("workItem", workItem);
    model.put("children", project.getWorkItemTree().getChildren(id));
    model.put("parentAlternativesList", project.getWorkItemTree().getParentAlternatives(workItem));
    model.put("board", board);
    // TODO: Figure out what this does
    Map<String, String> map = new LinkedHashMap<String, String>();
    for (String phase : workItem.getType().getPhases()) {
        LocalDate date = workItem.getDate(phase);
        String dateString = "";
        if (date != null) {
            dateString = DateUtils.formatConventionalNewZealandDate(date);
        }
        map.put(phase, dateString);
    }
    model.put("phasesMap", map);
    // Pass the model to edit.jsp
    return new ModelAndView("/edit.jsp", model);
}


public boolean parseBoolean(String excludedStr){
    if (excludedStr == null) {
        return false;
    }
    if ("on".equals(excludedStr)) {
        return true;
    }
    return Boolean.parseBoolean(excludedStr);
}


@RequestMapping("{board}/reorder")
public RedirectView reorder(KanbanProject project,String boardType,Integer id,Integer[] ids,String scrollTop){
    project.reorder(id, ids);
    project.save();
    return new RedirectView(includeScrollTopPosition(boardType, scrollTop, null));
}


@ModelAttribute("chartGenerator")
public BurnUpChartGenerator populateChartGenerator(){
    return new DefaultBurnUpChartGenerator(new DefaultChartWriter());
}


@RequestMapping("cycle-time-chart.png")
public void cycleTimeChartPng(KanbanProject project,String startDate,String endDate,String workStream,String level,OutputStream outputStream){
    WorkItemType type = project.getWorkItemTypes().getByName(level);
    CycleTimeChartBuilder builder = new CycleTimeChartBuilder();
    List<WorkItem> workItemList = project.getWorkItemTree().getWorkItemsOfType(type, workStream);
    CategoryDataset dataset = builder.createDataset(builder.getCompletedWorkItemsInOrderOfCompletion(workItemList));
    JFreeChart chart = builder.createChart(dataset);
    int width = dataset.getColumnCount() * 15;
    ChartUtilities.writeChartAsPNG(outputStream, chart, width < DEFAULT_CHART_WIDTH ? DEFAULT_CHART_WIDTH : width, DEFAULT_CHART_HEIGHT);
}


@RequestMapping("cumulative-flow-chart.png")
public void cumulativeFlowChartPng(KanbanProject project,String startDate,String endDate,String level,String workStream,OutputStream outputStream){
    WorkItemType type;
    try {
        type = project.getWorkItemTypes().getByName(level);
    } catch (IllegalArgumentException e) {
        // TODO produce image with text from exception
        return;
    }
    List<WorkItem> workItemList = project.getWorkItemTree().getWorkItemsOfType(type, workStream);
    LocalDate start = DateUtils.parseDate(startDate, null);
    LocalDate end = DateUtils.parseDate(endDate, null);
    // add start and end date params here
    CumulativeFlowChartBuilder builder = new CumulativeFlowChartBuilder(start, end);
    CategoryDataset dataset = builder.createDataset(type.getPhases(), workItemList);
    JFreeChart chart = builder.createChart(dataset, project);
    int width = dataset.getColumnCount() * 15;
    ChartUtilities.writeChartAsPNG(outputStream, chart, width < DEFAULT_CHART_WIDTH ? DEFAULT_CHART_WIDTH : width, DEFAULT_CHART_HEIGHT);
}


public String includeScrollTopPosition(String boardType,String scrollTop,String id){
    String highlight = "";
    if (id != null) {
        highlight = "&highlight=" + id;
    }
    return "../" + boardType + "?scrollTop=" + scrollTop + highlight;
}


@RequestMapping("{board}/add-item")
public ModelAndView addItem(KanbanProject project,String projectName,int id){
    // Search for parent id
    WorkItem parent = project.getWorkItemById(id);
    WorkItemType type = project.getWorkItemTypes().getRoot().getValue();
    String parentName = "";
    int parentId = ROOT_WORK_ITEM_ID;
    String legend = "Add " + type;
    if (parent != null) {
        parentName = parent.getName();
        parentId = parent.getId();
        type = project.getChildType(parent.getType());
        legend = "Add a " + type + " to " + parentName;
    }
    Map<String, Object> model = new HashMap<String, Object>();
    model.put("legend", legend);
    model.put("parentId", parentId);
    model.put("type", type);
    model.put("topLevel", true);
    return new ModelAndView("/add.jsp", model);
}


@ModelAttribute("project")
public KanbanProject populateProject(String projectName){
    return kanbanService.getKanbanProject(projectName);
}


@ModelAttribute("workStreams")
public Map<String,String> populateWorkStreams(){
    return new HashMap<String, String>();
}


@RequestMapping("edit-project-action")
public RedirectView editProjectAction(KanbanProject project,String projectName,String newProjectName,String content){
    if (newProjectName != null && !newProjectName.equals(projectName)) {
        // edit project name
        String validProjectNameError = isProjectNameValid(newProjectName);
        if (!StringUtils.isEmpty(validProjectNameError)) {
            return new RedirectView("edit-project?createNewProject=false&error=" + URLEncoder.encode(validProjectNameError, "US-ASCII"));
        }
        kanbanService.renameProject(projectName, newProjectName);
    }
    // edit project
    kanbanService.editProject(newProjectName, content);
    return openProject(projectName, "wall", newProjectName, null, null);
}


@RequestMapping("wall")
public ModelAndView wallBoard(KanbanProject project,String projectName,String scrollTop,Map<String,String> workStreams,String highlight,String error,RedirectAttributes redirectAttributes){
    Map<String, Object> model = initBoard("wall", projectName, error, scrollTop);
    KanbanBoard board = project.getBoard(BoardIdentifier.WALL, workStreams.get(projectName));
    model.put("board", board);
    model.put("highlight", highlight);
    return new ModelAndView("/project.jsp", model);
}


@RequestMapping("edit-project")
public ModelAndView editProject(KanbanProject project,String projectName,boolean createNewProject,String error){
    Map<String, Object> model = buildModel(projectName, "wall");
    // Get the settings of this current project and pass it to the form.
    model.put("settings", kanbanService.getProjectConfiguration(projectName).getKanbanPropertiesFile().getContentAsString());
    model.put("error", error);
    // Create a new project if true
    if (createNewProject) {
        return new ModelAndView("/createProject.jsp", model);
    } else {
        // else edit the current project
        return new ModelAndView("/editProject.jsp", model);
    }
}


@RequestMapping("remove-journal-entry")
public RedirectView removeJournalEntry(KanbanProject project,Integer id){
    project.deleteJournalItem(id);
    return new RedirectView("journal");
}


public void setKanbanService(KanbanService kanbanService){
    this.kanbanService = kanbanService;
}


public String defaultStartDate(String endDate,LocalDate earliestDate){
    LocalDate endDateParsed;
    if (!StringUtils.isEmpty(endDate)) {
        try {
            endDateParsed = LocalDate.parse(endDate);
        } catch (RuntimeException e) {
            LOGGER.warn("Cannot parse date {}", endDate);
            LOGGER.warn("Got exception: ", e);
            endDateParsed = LocalDate.now();
        }
    } else {
        endDateParsed = LocalDate.fromCalendarFields(Calendar.getInstance());
    }
    LocalDate result = endDateParsed.minusMonths(DEFAULT_MONTHS_DISPLAY);
    if (earliestDate != null && result.isBefore(earliestDate)) {
        result = earliestDate;
    }
    return formatDate(result);
}


@RequestMapping("edit-wiplimit-action")
public ModelAndView editWIPLimitAction(KanbanProject project,String projectName,String columnName,String columnType,String wipLimit){
    String content = kanbanService.getProjectConfiguration(projectName).getKanbanPropertiesFile().getContentAsString();
    Scanner sc = new Scanner(content);
    String temp = "";
    StringBuilder newContent = new StringBuilder();
    // Keep track of how many columns there are
    int totalColumns = 0;
    // This is set once,
    int insertAfterComma = -1;
    String wipLine = "workItemTypes." + columnType + ".wipLimit=";
    while (sc.hasNext()) {
        temp = sc.nextLine();
        // Find out where the new wipLimit should go
        if (temp.contains("workItemTypes." + columnType + ".phases=")) {
            String columns = temp.split("=")[1];
            for (String column : columns.split(",")) {
                if (column.equals(columnName)) {
                    insertAfterComma = totalColumns;
                }
                totalColumns++;
            }
        }
        if (temp.contains(wipLine)) {
            String wipLimits = temp.split("=")[1];
            String[] limits = wipLimits.split(",");
            // limts.count should == totalColumns
            String newLimits = "";
            String limit = "";
            for (int i = 0; i < totalColumns; i++) {
                // Get the old limit
                try {
                    limit = limits[i];
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Set default if no limit
                    limit = "-1";
                }
                // Do we need to set it to a new limit?
                if (i == insertAfterComma) {
                    // Set the new limit
                    limit = wipLimit;
                }
                newLimits += limit + ",";
            }
            temp = wipLine + newLimits;
        }
        newContent.append(temp).append('\n');
    }
    kanbanService.editProject(projectName, newContent.toString());
    return new ModelAndView("/admin.jsp", null);
}


@RequestMapping("estimates-burn-down-chart.png")
public void estimatesBurnDownChartPng(EstimatesProject project,BurnUpChartGenerator chartGenerator,OutputStream outputStream){
    WorkItemTree tree = project.getKanbanProject().getWorkItemTree();
    WorkItemType type = project.getKanbanProject().getWorkItemTypes().getRoot().getValue();
    List<WorkItem> topLevelWorkItems = tree.getWorkItemsOfType(type, null);
    chartGenerator.generateEstimatesBurnUpChart(project, topLevelWorkItems, outputStream);
}


@RequestMapping(value = "{board}/advance-item-action")
public RedirectView advanceItemAction(KanbanProject project,String boardType,String id,String phase,Integer scrollTop,RedirectAttributes redirectAttributes){
    // check item hasn't already been advanced
    WorkItem workItem = project.getWorkItemById(Integer.parseInt(id));
    if (!workItem.getCurrentPhase().equals(phase)) {
        redirectAttributes.addFlashAttribute("error", "Your board view was out of date, your request has been canceled and the board has been updated. Please review the board now and apply your changes.");
        return new RedirectView("../" + boardType);
    }
    project.advance(parseInt(id), currentLocalDate());
    project.save();
    return new RedirectView("../" + boardType + "?scrollTop=" + scrollTop + "&highlight=" + id);
}


@RequestMapping("{board}/add-item-action")
public RedirectView addItemAction(KanbanProject project,String board,Integer parentId,String type,String name,String averageCaseEstimateStr,String worstCaseEstimateStr,String importanceStr,String notes,String color,String excludedStr,String redirectTo,String workStreams){
    WorkItemType typeAsWorkItemType = project.getWorkItemTypes().getByName(type);
    if (parentId == null) {
        parentId = WorkItem.ROOT_WORK_ITEM_ID;
    }
    int averageCaseEstimate = parseInteger(averageCaseEstimateStr, 0);
    int importance = parseInteger(importanceStr, 0);
    boolean excluded = parseBoolean(excludedStr);
    int worstCaseEstimate = parseInteger(worstCaseEstimateStr, 0);
    // Add it and save it
    int newId = project.addWorkItem(parentId, typeAsWorkItemType, name, averageCaseEstimate, worstCaseEstimate, importance, notes, color, excluded, workStreams, currentLocalDate());
    project.save();
    if ("print".equals(redirectTo)) {
        return new RedirectView("../print-items?printSelection=" + newId);
    } else {
        return new RedirectView("../" + board);
    }
}


@ModelAttribute("estimatesProject")
public EstimatesProject populateEstimatesProject(String projectName){
    EstimatesProject project = estimatesDao.loadProject(projectName);
    return project;
}


@RequestMapping(value = "add-journal-entry", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public KanbanJournalItem addJournalEntry(KanbanProject project,String projectName,String userName,String date,String journalText){
    KanbanJournalItem journalItem = new KanbanJournalItem(date, journalText, userName);
    project.addJournalItem(journalItem);
    return journalItem;
}


@RequestMapping("backlog")
public ModelAndView backlogBoard(KanbanProject project,String projectName,String scrollTop,Map<String,String> workStreams,String error){
    Map<String, Object> model = initBoard("backlog", projectName, error, scrollTop);
    model.put("kanbanBacklog", project.getBacklog(workStreams.get(projectName)));
    model.put("type", project.getWorkItemTypes().getRoot().getValue());
    model.put("phase", project.getWorkItemTypes().getRoot().getValue().getBacklogPhase());
    return new ModelAndView("/backlog.jsp", model);
}


@RequestMapping("chart")
public ModelAndView chart(KanbanProject project,String chartName,String workItemTypeName,String projectName,String startDate,String endDate,String error){
    if (StringUtils.isEmpty(startDate)) {
        startDate = defaultStartDate(endDate, project.getStartDate());
    }
    if (StringUtils.isEmpty(endDate)) {
        endDate = formatDate(LocalDate.now());
    }
    try {
        project.getWorkItemTypes().getByName(workItemTypeName);
    } catch (IllegalArgumentException exception) {
        workItemTypeName = project.getWorkItemTypes().getRoot().getValue().getName();
    }
    Map<String, Object> model = initBoard("chart", projectName, error, null);
    model.put("workItemTypeName", workItemTypeName);
    model.put("imageName", chartName + ".png");
    model.put("startDate", startDate);
    model.put("endDate", endDate);
    model.put("kanbanJournal", project.getJournal());
    model.put("chartName", chartName);
    model.put("projectStartDate", formatDate(project.getStartDate()));
    model.put("currentDate", formatDate(LocalDate.now()));
    model.put("projectedEndDate", formatDate(project.getProjectedEndDate(LocalDate.parse(startDate), LocalDate.parse(endDate))));
    return new ModelAndView("/chart.jsp", model);
}


public String isProjectNameValid(String newProjectName){
    if (StringUtils.containsAny(newProjectName, PROJECT_NAME_INVALID_CHARS)) {
        return String.format("Project name contains incorrect characters at least one of (%s)", PROJECT_NAME_INVALID_CHARS);
    }
    if (StringUtils.isEmpty(newProjectName.trim())) {
        return "Project name should not be empty";
    }
    if (newProjectName.length() > MAX_PROJECT_NAME_LENGTH) {
        return String.format("Project name is too long, maximum allowed length is %d charactes, but is %d", MAX_PROJECT_NAME_LENGTH, newProjectName.length());
    }
    return null;
}


@RequestMapping("{board}/delete-column-action")
public RedirectView deleteColumn(KanbanProject project,String projectName,String boardType,String name){
    String orig = kanbanService.getProjectConfiguration(projectName).getKanbanPropertiesFile().getContentAsString();
    Scanner sc = new Scanner(orig);
    String temp = "";
    StringBuilder newContent = new StringBuilder();
    while (sc.hasNext() && !StringUtils.isEmpty(name)) {
        temp = sc.nextLine();
        if (temp.contains(name)) {
            String[] phases = temp.split(",|=");
            for (int i = 0; i <= phases.length - 1; i++) {
                if (!phases[i].equals(name)) {
                    if (i == 0) {
                        newContent.append(phases[i]).append("=");
                    } else if (i == phases.length - 1) {
                        newContent.append(phases[i]);
                    } else if (i == phases.length - 2 && name.equals(phases[phases.length - 1])) {
                        newContent.append(phases[i]);
                    } else {
                        newContent.append(phases[i]).append(",");
                    }
                }
            }
            newContent.append("\n");
        } else {
            newContent.append(temp).append("\n");
        }
    }
    if (newContent.length() < 10) {
        newContent = new StringBuilder(orig);
    }
    kanbanService.editProject(projectName, newContent.toString());
    return new RedirectView("/projects/" + projectName + "/" + boardType, true);
}


}