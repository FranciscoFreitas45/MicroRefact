package com.metservice.kanban.jsptags;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.collections.ListUtils;
import com.metservice.kanban.charts.KanbanDrawingSupplier;
import com.metservice.kanban.model.BoardIdentifier;
import com.metservice.kanban.model.HtmlColour;
import com.metservice.kanban.model.KanbanBoardColumn;
import com.metservice.kanban.model.KanbanBoardColumnList;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemType;
import com.metservice.kanban.Interface.KanbanProject;
import com.metservice.kanban.DTO.KanbanBoardColumn;
public class PhasesLengthTag extends TagSupport{

 private  int PHASE_WIDTH_PIX_TO_DISPLAY_NUMBER;

 private  int PIXELS_PER_DAY;

 private  long serialVersionUID;

 private  WorkItem workItem;

 private  KanbanProject project;

 private  WorkItemType type;


public int doStartTag(){
    try {
        KanbanBoardColumnList wallColumns = project.getColumns(BoardIdentifier.WALL);
        List<String> wallPhases = new ArrayList<String>();
        for (KanbanBoardColumn column : wallColumns) {
            wallPhases.add(column.getPhase());
        }
        List<String> itemPhases = type.getPhases();
        @SuppressWarnings("unchecked")
        List<String> phases = ListUtils.retainAll(itemPhases, wallPhases);
        HtmlColour[] htmlColours = KanbanDrawingSupplier.getHtmlColours(phases.size());
        Map<String, Integer> phaseDurations = workItem.getPhaseDurations();
        Iterator<HtmlColour> colorIterator = Arrays.asList(htmlColours).iterator();
        for (String phase : phases) {
            HtmlColour currentColor = colorIterator.next();
            if (phaseDurations.containsKey(phase)) {
                int phaseDays = phaseDurations.get(phase);
                int phaseWidth = (int) Math.floor(phaseDays * PIXELS_PER_DAY);
                pageContext.getOut().write("<div class=\"age-item\" style=\"width:" + phaseWidth + "px; background-color: " + currentColor + ";\">\n\n");
                if (phaseWidth > PHASE_WIDTH_PIX_TO_DISPLAY_NUMBER) {
                    pageContext.getOut().write(Integer.toString(phaseDays));
                }
                pageContext.getOut().write("</div>\n");
            }
        }
        return EVAL_PAGE;
    } catch (IOException e) {
        throw new JspException(e);
    }
}


public void setWorkItem(WorkItem workItem){
    this.workItem = workItem;
}


public void setProject(KanbanProject project){
    this.project = project;
}


public void setType(WorkItemType type){
    this.type = type;
}


}