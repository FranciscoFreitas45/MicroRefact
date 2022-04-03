package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.views.TimeVew;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Getter
public class TaskView {

 private  Long id;

 private  String name;

 private  String description;

 private  TimeVew learningTime;

 private  LocalDate dueDate;

 private  Long courseId;

 private  List<TaskBriefView> previousTasks;

 private  List<TaskBriefView> nextTasks;

 private  List<AttachmentView> attachments;


public TaskView from(Task model){
    return new TaskView(model.getId(), model.getName(), model.getDescription(), TimeVew.form(model.getLearningTime()), model.getDueDate(), model.getCourse().getId(), model.getPreviousTasks().stream().map(TaskBriefView::from).collect(Collectors.toList()), model.getNextTasks().stream().map(TaskBriefView::from).collect(Collectors.toList()), model.getAttachments().stream().map(AttachmentView::form).collect(Collectors.toList()));
}


}