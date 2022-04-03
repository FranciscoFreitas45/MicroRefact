package com.bau.graduateprojects.qrstudentsattendance.servicies.integration;
 import com.bau.graduateprojects.qrstudentsattendance.entities;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import com.bau.graduateprojects.qrstudentsattendance.repositories.course.CourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.lecture.LectureRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.lectureAttendance.LectureAttendanceRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.lectureCourse.LectureCourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.student.StudentRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.studentCourse.StudentCourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.teacher.TeacherRepository;
import com.bau.graduateprojects.qrstudentsattendance.repositories.teacherCourse.TeacherCourseRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.CourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.TeacherRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.StudentCourseRepository;
import com.bau.graduateprojects.qrstudentsattendance.Interface.LectureAttendanceRepository;
@Service
public class IntegrationServiceImpl implements IntegrationService{

 private  StudentRepository studentRepository;

 private  CourseRepository courseRepository;

 private  TeacherRepository teacherRepository;

 private  LectureRepository lectureRepository;

 private  StudentCourseRepository studentCourseRepository;

 private  TeacherCourseRepository teacherCourseRepository;

 private  LectureCourseRepository lectureCourseRepository;

 private  LectureAttendanceRepository lectureAttendanceRepository;

public IntegrationServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, TeacherRepository teacherRepository, LectureRepository lectureRepository, StudentCourseRepository studentCourseRepository, TeacherCourseRepository teacherCourseRepository, LectureCourseRepository lectureCourseRepository, LectureAttendanceRepository lectureAttendanceRepository) {
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
    this.teacherRepository = teacherRepository;
    this.lectureRepository = lectureRepository;
    this.studentCourseRepository = studentCourseRepository;
    this.teacherCourseRepository = teacherCourseRepository;
    this.lectureCourseRepository = lectureCourseRepository;
    this.lectureAttendanceRepository = lectureAttendanceRepository;
}
@Override
public TeacherCourseEntity addCourseToTeacher(TeacherCourseEntity entity){
    checkExistence(entity);
    throwIfAlreadyExist(entity);
    CourseEntity courseEntity = courseRepository.getById(entity.getCourseId());
    TeacherEntity teacherEntity = teacherRepository.getById(entity.getTeacherId());
    courseEntity.setTeacherName(teacherEntity.getName());
    courseRepository.update(courseEntity);
    return teacherCourseRepository.insert(entity);
}


@Override
public List<AttendanceEntity> getAllAttendanceByLectureId(Long lId){
    return lectureAttendanceRepository.getAllAttendanceByLectureId(lId);
}


@Override
public List<LectureEntity> getAllLecturesByCourseId(Long cId){
    if (!courseRepository.existById(cId))
        throw new ResourceNotFoundException("Course not found with id = " + cId);
    List<LectureCourseEntity> allLecturesByCourseId = lectureCourseRepository.getAllLecturesByCourseId(cId);
    List<LectureEntity> list = new ArrayList<>();
    allLecturesByCourseId.forEach(record -> {
        list.add(lectureRepository.getById(record.getLectureId()));
    });
    return list;
}


public void checkExistence(TeacherCourseEntity entity){
    if (!teacherRepository.existById(entity.getTeacherId()))
        throw new ResourceNotFoundException("Teacher not found with id = " + entity.getCourseId());
    if (!courseRepository.existById(entity.getCourseId()))
        throw new ResourceNotFoundException("Course not found with id = " + entity.getCourseId());
}


@Override
public StudentCourseEntity addCourseToStudent(StudentCourseEntity entity){
    checkExistence(entity);
    throwIfAlreadyExist(entity);
    return studentCourseRepository.insert(entity);
}


@Override
public List<CourseEntity> getAllCoursesByStudentId(Long sId){
    if (!studentRepository.existById(sId))
        throw new ResourceNotFoundException("Student not found with id = " + sId);
    List<StudentCourseEntity> allCoursesByStudentId = studentCourseRepository.getAllCoursesByStudentId(sId);
    List<CourseEntity> list = new ArrayList<>();
    allCoursesByStudentId.forEach(record -> {
        list.add(courseRepository.getById(record.getCourseId()));
    });
    return list;
}


@Override
public LectureCourseEntity addLectureToCourse(LectureCourseEntity entity){
    checkExistence(entity);
    throwIfAlreadyExist(entity);
    return lectureCourseRepository.insert(entity);
}


@Override
public LectureAttendanceEntity addAttendanceToLecture(LectureAttendanceEntity entity){
    return lectureAttendanceRepository.addAttendanceToLecture(entity);
}


@Override
public AttendanceEntity updateStatusAttendanceId(Long attId){
    return lectureAttendanceRepository.updateStatusAttendanceId(attId);
}


@Override
public List<CourseEntity> getAllCoursesByTeacherId(Long tId){
    if (!teacherRepository.existById(tId))
        throw new ResourceNotFoundException("Teacher not found with id = " + tId);
    List<TeacherCourseEntity> allCoursesByTeacherId = teacherCourseRepository.getAllCoursesByTeacherId(tId);
    List<CourseEntity> list = new ArrayList<>();
    allCoursesByTeacherId.forEach(record -> {
        list.add(courseRepository.getById(record.getCourseId()));
    });
    return list;
}


@Override
public List<StudentEntity> getAllStudentsByCourseId(Long cId){
    if (!courseRepository.existById(cId))
        throw new ResourceNotFoundException("Course not found with id = " + cId);
    List<StudentCourseEntity> allStudentsByCourseId = studentCourseRepository.getAllStudentsByCourseId(cId);
    List<StudentEntity> list = new ArrayList<>();
    allStudentsByCourseId.forEach(record -> {
        list.add(studentRepository.getById(record.getStudentId()));
    });
    return list;
}


@Override
public void removeLectureFromCourse(Long lId,Long cId){
    LectureCourseEntity entity = new LectureCourseEntity();
    entity.setLectureId(lId);
    entity.setCourseId(cId);
    checkExistence(entity);
    LectureCourseEntity byLectureIdAndCourseId = lectureCourseRepository.getByLectureIdAndCourseId(lId, cId);
    lectureCourseRepository.removeById(byLectureIdAndCourseId.getId());
}


public void throwIfAlreadyExist(LectureCourseEntity entity){
    if (lectureCourseRepository.exist(entity.getLectureId(), entity.getCourseId()))
        throw new IllegalArgumentException("Lecture is already added to this course");
}


@Override
public void removeCourseFromTeacher(Long tId,Long cId){
    TeacherCourseEntity entity = new TeacherCourseEntity();
    entity.setTeacherId(tId);
    entity.setCourseId(cId);
    checkExistence(entity);
    TeacherCourseEntity byTeacherIdAndCourseId = teacherCourseRepository.getByTeacherIdAndCourseId(tId, cId);
    teacherCourseRepository.removeById(byTeacherIdAndCourseId.getId());
}


@Override
public void removeCourseFromStudent(Long sId,Long cId){
    StudentCourseEntity entity = new StudentCourseEntity();
    entity.setStudentId(sId);
    entity.setCourseId(cId);
    checkExistence(entity);
    StudentCourseEntity byStudentIdAndCourseId = studentCourseRepository.getByStudentIdAndCourseId(sId, cId);
    studentCourseRepository.removeById(byStudentIdAndCourseId.getId());
}


}