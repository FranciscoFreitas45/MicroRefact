package kielce.tu.weaii.telelearn.exceptions;
 public class ScheduleBeyondTheDay extends BusinessLogicException{

public ScheduleBeyondTheDay() {
    super("Wpis w planie wykraczałby poza dzień.");
}
}