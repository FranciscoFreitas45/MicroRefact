package com.metservice.kanban.model.WorkItem;
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
public class LastPhaseDateComparator implements Comparator<WorkItem>{


@Override
public int compare(WorkItem o1,WorkItem o2){
    return o2.getLastPhaseDate().compareTo(o1.getLastPhaseDate());
}


}