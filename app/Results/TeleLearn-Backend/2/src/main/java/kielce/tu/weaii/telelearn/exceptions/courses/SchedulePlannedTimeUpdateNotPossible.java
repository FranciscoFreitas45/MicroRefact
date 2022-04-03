package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class SchedulePlannedTimeUpdateNotPossible extends BusinessLogicException{

public SchedulePlannedTimeUpdateNotPossible() {
    super("Nie można zaaktualizować czasu planowanego na wykonanie dla planów z przeszłości.");
}
}