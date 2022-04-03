package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class ScheduleRecordNotFound extends NotFoundException{

public ScheduleRecordNotFound() {
    super("Nie znaleziono planu.");
}
}