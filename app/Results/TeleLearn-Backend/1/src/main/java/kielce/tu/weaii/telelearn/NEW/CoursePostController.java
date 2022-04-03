package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.models.courses.Course;
@RestController
@CrossOrigin
public class CoursePostController {

@Autowired
 private CoursePostService coursepostservice;


}