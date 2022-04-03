package kielce.tu.weaii.telelearn.exceptions.courses;
 import kielce.tu.weaii.telelearn.exceptions.NotFoundException;
public class StudentOnListNotFound extends NotFoundException{

public StudentOnListNotFound(Long courseId, Long studentId) {
    super(String.format("Student o id %s nie został znaleziony na liście kursu o id %s", studentId, courseId));
}
}