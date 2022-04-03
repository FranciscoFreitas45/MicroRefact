package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class TaskMustBeCompleted extends BusinessLogicException{

public TaskMustBeCompleted() {
    super("Aby oznaczyć zadanie do potórzenia, zadanie musi być ukończone.");
}
}