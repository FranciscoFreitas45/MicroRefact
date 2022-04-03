package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class StudentAlreadyEnrolled extends BusinessLogicException{

public StudentAlreadyEnrolled() {
    super("Jesteś już zapisany do kusu. Jeśli nie masz dostępu, oznacza to, że oczekujesz na akceptację nauczyciela.");
}
}