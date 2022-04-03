package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.BusinessLogicException;
public class UpdateLearningTimeNotPossible extends BusinessLogicException{

public UpdateLearningTimeNotPossible() {
    super("Aktualizacja postępów może odbyć się tylko w zaplanowany dzień.");
}
}