package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class CourseNotFoundException extends NotFoundException{

public CourseNotFoundException(Long id) {
    super("kurs", id);
}
}