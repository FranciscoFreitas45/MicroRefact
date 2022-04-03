package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.courses.Task;
import lombok.Value;
@Value
public class TaskBriefView {

 private Long id;

 private String name;


public TaskBriefView from(Task model){
    return new TaskBriefView(model.getId(), model.getName());
}


}