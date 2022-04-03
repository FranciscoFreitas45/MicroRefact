package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class MovePostNotPossible extends BusinessLogicException{

public MovePostNotPossible() {
    super("Przenoszenie postów do innego kursu nie jest możliwe.");
}
}