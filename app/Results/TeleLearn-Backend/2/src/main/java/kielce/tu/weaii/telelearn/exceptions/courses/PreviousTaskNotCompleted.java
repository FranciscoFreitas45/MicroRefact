package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class PreviousTaskNotCompleted extends BusinessLogicException{

public PreviousTaskNotCompleted() {
    super("Przed rozpoczęciem bądź zarejestrowaniem nauki zadania należy ukończyć zadania poprzedzające.");
}
}