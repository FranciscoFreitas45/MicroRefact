package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class TaskNotFound extends NotFoundException{

public TaskNotFound(Long id) {
    super("zadanie", id);
}
}