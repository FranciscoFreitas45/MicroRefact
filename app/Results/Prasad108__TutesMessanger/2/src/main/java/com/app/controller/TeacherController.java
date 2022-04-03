package com.app.controller;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.app.pojo.Branch;
import com.app.pojo.Classes;
import com.app.pojo.Division;
import com.app.pojo.Exam;
import com.app.pojo.ExamMode;
import com.app.pojo.ExamSubjectStudentCompositTable;
import com.app.pojo.ExamType;
import com.app.pojo.Institute;
import com.app.pojo.Login;
import com.app.pojo.Parent;
import com.app.pojo.Permissions;
import com.app.pojo.Schedule;
import com.app.pojo.Student;
import com.app.pojo.Subject;
import com.app.pojo.SubjectDivComposit;
import com.app.pojo.Teacher;
import com.app.service.BranchService;
import com.app.service.ClassesService;
import com.app.service.DivisionService;
import com.app.service.ExamModeService;
import com.app.service.ExamService;
import com.app.service.ExamSubjectStudentCompositTableService;
import com.app.service.ExamTypeService;
import com.app.service.InstituteService;
import com.app.service.LoginService;
import com.app.service.ParentService;
import com.app.service.PermissionsService;
import com.app.service.ResultService;
import com.app.service.ScheduleService;
import com.app.service.StringUtil;
import com.app.service.StudentService;
import com.app.service.SubjectDivCompositService;
import com.app.service.SubjectService;
import com.app.service.TeacherService;
import com.app.service.impl.SnsService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.app.Interface.BranchService;
import com.app.Interface.TeacherService;
import com.app.Interface.ClassesService;
import com.app.Interface.DivisionService;
import com.app.Interface.InstituteService;
import com.app.Interface.ScheduleService;
import com.app.Interface.LoginService;
import com.app.Interface.PermissionsService;
import com.app.Interface.StudentService;
import com.app.Interface.ParentService;
import com.app.Interface.SubjectService;
import com.app.Interface.SubjectDivCompositService;
import com.app.Interface.ExamSubjectStudentCompositTableService;
import com.app.Interface.ResultService;
import com.app.DTO.Login;
import com.app.DTO.Institute;
import com.app.DTO.ExamSubjectStudentCompositTable;
import com.app.DTO.Schedule;
import com.app.DTO.SubjectDivComposit;
import com.app.DTO.Parent;
@Controller
@SessionAttributes({ "teacher", "appAdmin", "institute", "login", "permissions", "teacherJSON" })
@RequestMapping("/Teacher")
public class TeacherController {

 private StringUtil stringUtil;

@Autowired
 private SnsService snsService;

@Autowired
 private BranchService branchService;

@Autowired
 private TeacherService teacherService;

@Autowired
 private ClassesService classesService;

@Autowired
 private DivisionService divisionService;

@Autowired
 private InstituteService instituteService;

@Autowired
 private ScheduleService scheduleService;

@Autowired
 private LoginService loginService;

@Autowired
 private PermissionsService permissionsService;

@Autowired
 private StudentService StudentService;

@Autowired
 private ParentService parentService;

@Autowired
 private ExamService examService;

@Autowired
 private ExamModeService examModeService;

@Autowired
 private ExamTypeService examTypeService;

@Autowired
 private SubjectService subjectService;

@Autowired
 private SubjectDivCompositService subjectDivCompositService;

@Autowired
 private ExamSubjectStudentCompositTableService examSubStudCompService;

@Autowired
 private ResultService resultService;

 private Gson gson;

 private  Logger LOGGER;


@RequestMapping(value = "/RenameClass/{className}/{branchId}", method = RequestMethod.POST)
@ResponseBody
public String RenameClass(String className,int branchId,Teacher teacher,Classes classes){
    LOGGER.info("**********inside RenameClass controller**********");
    String output;
    int flag = 0;
    try {
        Branch branch = branchService.find(branchId);
        List<Classes> classlist = classesService.getallOfParticularBranch(branch);
        for (Classes c : classlist) {
            if (c.getName().equals(className)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to rename class \",\"status\":\"classExist\"}";
        } else {
            classes.setName(className);
            classes.setBranch(branch);
            classesService.update(classes);
            output = "{\"action\":\"Selected Class is renamed sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to rename selected class \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/deleteStudentApprovalRequest/{id}", method = RequestMethod.GET)
@ResponseBody
public String deleteStudentApprovalRequest(int id){
    LOGGER.info("**********from /deleteStudentApprovalRequest/{id} controller**********");
    String result = "";
    LOGGER.info("student to be deleted is with id" + id);
    try {
        Student s = StudentService.find(id);
        LOGGER.info("first name of the student is " + s.getFname());
        Login login = loginService.find(s.getLogin().getId());
        LOGGER.info("login id is " + login.getId());
        loginService.delet(login);
        LOGGER.info("Student is deleted with the id " + id);
        result = "{\"message\":\"Student with id " + id + " is deleted \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        e.printStackTrace();
        LOGGER.warn("error in deletion with Student id : " + id);
        result = "{\"message\":\"ERROR...!! student with id " + id + " not deleted\",\"status\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/deleteSubjectFromInstitute/{subId}/{instId}", method = RequestMethod.POST)
@ResponseBody
public String deleteSubjectFromInstitute(int subId,int instId){
    LOGGER.info("**********from deleteSubjectFromInstitute controller**********");
    String subjectListOfInstituteAfterDeletJSON = "";
    LOGGER.info("subject to be deleted is with id" + subId);
    try {
        Subject s = subjectService.find(subId);
        LOGGER.info("name of the subject is " + s.getName());
        subjectService.deleteFromInstitute(subId);
        List<Subject> allSubjectOfInstituteList = subjectService.getallOfInstitute(instId);
        subjectListOfInstituteAfterDeletJSON = gson.toJson(allSubjectOfInstituteList);
        LOGGER.info(subjectListOfInstituteAfterDeletJSON);
        LOGGER.info("Subject is deleted with the id " + subId);
    // subjectListOfInstituteAfterDeletJSON="{\"message\":\"Subject with id
    // "+subId+" is deleted \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        e.printStackTrace();
        LOGGER.warn("error in deletion with Subject id : " + subId);
    // subjectListOfInstituteAfterDeletJSON="{\"message\":\"ERROR...!! subject with
    // id "+subId+" not deleted\",\"status\":\"fail\"}";
    }
    LOGGER.info(subjectListOfInstituteAfterDeletJSON);
    return subjectListOfInstituteAfterDeletJSON;
}


@RequestMapping(value = "/ExamSubjectStudentResult", method = RequestMethod.GET)
@ResponseBody
public String resultStudentList(int examId,int subDivId){
    LOGGER.info("**********from resultStudentList controller**********");
    String result = examSubStudCompService.examSubjectStudentResult(examId, subDivId);
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/StudentRequestApprover/{id}", method = RequestMethod.GET)
@ResponseBody
public String StudentRequestApprover(int id){
    LOGGER.info("**********from /StudentRequestApprover/{id} controller**********");
    String result = "";
    LOGGER.info("studnet to be updated is with id" + id);
    try {
        // find teacher
        Student s = StudentService.find(id);
        // get login of teacher
        Login l = loginService.find(s.getLogin().getId());
        // enable the institute flag
        l.setEnableInstitute(true);
        // update login
        loginService.update(l);
        LOGGER.info("Student is updated with the id " + id);
        result = "{\"message\":\"Studen with id " + id + " is updated \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        LOGGER.warn(e);
        LOGGER.warn("error in updation with Student id : " + id);
        result = "{\"message\":\"ERROR...!! Student with id " + id + " not updated\",\"c\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/addSubjectToInstitute/{subName}/{subDescript}/{instId}", method = RequestMethod.POST)
@ResponseBody
public String addSubjectToInstitute(String subName,String subDescript,int instId){
    LOGGER.info("**********from addSubjectToInstitute controller**********");
    String result = "";
    LOGGER.info("subject to be added is with name " + subName + " in institute with id " + instId);
    try {
        Institute inst = instituteService.find(instId);
        LOGGER.info(inst.getName());
        Subject sub = new Subject(inst, subName, subDescript);
        subjectService.create(sub);
        LOGGER.info("Subject is added with the name " + subName);
        result = "{\"message\":\"Subject with name " + subName + " is added \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        e.printStackTrace();
        LOGGER.warn("error in add with Subject name : " + subName);
        result = "{\"message\":\"ERROR...!! subject with name " + subName + " not added\",\"status\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/GetExamsType", method = RequestMethod.POST)
@ResponseBody
public String GetExamsType(){
    LOGGER.info("**********inside GetExamsType controller**********");
    List<ExamType> examTypeList = examTypeService.getAll();
    String JSONexamTypeList = gson.toJson(examTypeList);
    // LOGGER.info(JSONexamTypeList);
    return JSONexamTypeList;
}


@RequestMapping(value = "/StudentRequestManagerList", method = RequestMethod.GET)
@ResponseBody
public String StudentRequestManagerList(Teacher teacher){
    LOGGER.info("**********inside StudentRequestManagerList controller**********");
    LOGGER.info("teachers insitute id is :" + teacher.getInstitute().getId());
    List<Student> studnetList = instituteService.getallPendingStudentForApproval(instituteService.find(teacher.getInstitute().getId()));
    for (Student s : studnetList) {
        LOGGER.info(s.getFname());
    }
    String studentListJSON = gson.toJson(studnetList);
    return studentListJSON;
}


@RequestMapping(value = "/AddNewBranch/{branchName}", method = RequestMethod.POST)
@ResponseBody
public String AddNewBranch(String branchName,Teacher teacher){
    LOGGER.info("**********inside AddNewBranch controller**********");
    String output = "";
    int flag = 0;
    try {
        Institute inst = teacherService.GetInstitute(teacher.getId());
        List<Branch> branchlist = branchService.getallOfParticularInstitute(inst);
        for (Branch b : branchlist) {
            if (b.getName().equalsIgnoreCase(branchName)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to add new branch \",\"status\":\"branchExist\"}";
        } else {
            Branch branch = new Branch();
            branch.setName(branchName);
            branch.setInstitute(inst);
            branchService.create(branch);
            output = "{\"action\":\"New Branch is added sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to add new branch \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/GetSubjectTreeStruct/{id}", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectTreeStruct(int InstituteId){
    LOGGER.info("**********inside GetSubjectTreeStruct controller**********");
    String JSON = instituteService.GetSubjectTree(InstituteId);
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/DeleteSelectedStudentFromDivision", method = RequestMethod.POST)
@ResponseBody
public String DeleteSelectedStudentFromDivision(ArrayList<Student> studentList){
    LOGGER.info("**********inside DeleteSelectedStudentFromDivision controller**********");
    LOGGER.info(studentList);
    for (Student s : studentList) {
        LOGGER.info(s.getFname() + " " + s.getId());
        StudentService.deleteSelectedFromDiv(s.getId());
    }
    /*
		 * LOGGER.info(); ObjectMapper mapper = new ObjectMapper(); JSONObject
		 * jsonObj = new JSONObject(json);
		 */
    return "";
}


@RequestMapping(value = "/UpdateExam/{id}", method = RequestMethod.POST)
@ResponseBody
public String UpdateExam(Exam e,int InstituteId){
    LOGGER.info("**********inside UpdateExam controller**********");
    e.setInstitute(instituteService.find(InstituteId));
    examService.update(e);
    String JSON = "";
    JSON += "{";
    JSON += "\"id\":" + e.getId() + ",";
    JSON += "\"examMode\":";
    JSON += "{";
    JSON += "\"id\":" + e.getExamMode().getId() + "},";
    JSON += "\"examType\":";
    JSON += "{";
    JSON += "\"id\":" + e.getExamType().getId() + "},";
    JSON += "\"discription\":\"" + e.getDiscription() + "\",";
    JSON += "\"outOf\":" + e.getOutOf() + ",";
    JSON += "\"passingMarks\":" + e.getPassingMarks() + ",";
    JSON += "\"regular\":" + e.getRegular() + "";
    JSON += "}";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/DeleteClass", method = RequestMethod.POST)
@ResponseBody
public String DeleteClass(Teacher teacher,Classes classes){
    LOGGER.info("**********inside DeleteClass controller**********");
    String output;
    try {
        classesService.delet(classes.getId());
        output = "{\"action\":\"Selected Class is deleted sucessfully \",\"status\":\"success\"}";
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to delete selected class \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/GetClassesList/{id}", method = RequestMethod.GET)
@ResponseBody
public String GetClassesList(int id){
    LOGGER.info("**********from GetClassesList controller**********");
    List<Classes> classList = classesService.getallOfParticularBranch(branchService.find(id));
    String JSON = "[{\"value\":0,\"name\":\"--- Select Class---\"},";
    for (Classes c : classList) {
        JSON += "{";
        JSON += "\"value\":" + c.getId() + ",";
        JSON += "\"name\":\"" + c.getName() + "\"";
        JSON += "},";
        LOGGER.info(c);
    }
    JSON = JSON.substring(0, JSON.length() - 1);
    JSON += "]";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/GetDivisionList/{id}", method = RequestMethod.GET)
@ResponseBody
public String GetDivisionList(int id){
    LOGGER.info("**********from GetDivisionList controller**********");
    List<Division> divisionList = divisionService.getallOfParticularClass(classesService.find(id));
    // List<Classes>
    // classList=classesService.getallOfParticularBranch(branchService.find(id));
    String JSON = "[{\"value\":0,\"name\":\"--- Select Division---\"},";
    for (Division div : divisionList) {
        JSON += "{";
        JSON += "\"value\":" + div.getId() + ",";
        JSON += "\"name\":\"" + div.getName() + "\"";
        JSON += "},";
        LOGGER.info(div);
    }
    JSON = JSON.substring(0, JSON.length() - 1);
    JSON += "]";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/SaveExam/{id}", method = RequestMethod.POST)
@ResponseBody
public String SaveExam(Exam e,int InstituteId){
    LOGGER.info("**********inside SaveExam controller**********");
    e.setInstitute(instituteService.find(InstituteId));
    examService.create(e);
    String JSON = "";
    JSON += "{";
    JSON += "\"id\":" + e.getId() + ",";
    JSON += "\"examMode\":";
    JSON += "{";
    JSON += "\"id\":" + e.getExamMode().getId() + "},";
    JSON += "\"examType\":";
    JSON += "{";
    JSON += "\"id\":" + e.getExamType().getId() + "},";
    JSON += "\"discription\":\"" + e.getDiscription() + "\",";
    JSON += "\"outOf\":" + e.getOutOf() + ",";
    JSON += "\"passingMarks\":" + e.getPassingMarks() + ",";
    JSON += "\"regular\":" + e.getRegular() + "";
    JSON += "}";
    return JSON;
}


@RequestMapping(value = "/GetSubjectOfDivisionInJSON", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectOfDivisionInJSON(Division division){
    LOGGER.info("**********inside GetSubjectOfDivisionInJSON controller**********");
    LOGGER.info(division);
    String subjectListJSON = "";
    try {
        LOGGER.info("in try");
        List<Subject> subjectList = subjectDivCompositService.findByDivId(division.getId());
        for (Subject s : subjectList) {
            LOGGER.info(s.getName());
        }
        subjectListJSON = gson.toJson(subjectList);
        if (subjectList.isEmpty()) {
            subjectListJSON = "{\"ErrorMessage\":\"Selected division does not contain any subject\"}";
        } else {
            subjectListJSON = gson.toJson(subjectList);
        }
    } catch (Exception e) {
        subjectListJSON = "{\"ErrorMessage\":\"Selected division does not contain any subject\"}";
        e.printStackTrace();
    }
    LOGGER.info(subjectListJSON);
    return subjectListJSON;
}


@RequestMapping(value = "/addSelectedStudentToExam/{subDivId}/{examId}/{divId}", method = RequestMethod.POST)
@ResponseBody
public String AddStudentInExam(int subDivId,int examId,int divId,ArrayList<Student> studentList){
    LOGGER.info("**********inside AddStudentInExam controller**********");
    String studentListNotExamJSON = "";
    List<Student> studentNotInExamList = new ArrayList<Student>();
    try {
        ExamSubjectStudentCompositTable examStudSubObj = examSubStudCompService.findByExamSubDivId(examId, subDivId);
        for (Student stud : studentList) {
            examStudSubObj.setStudent(stud);
            examSubStudCompService.create(examStudSubObj);
        }
        List<Student> allStudentOfDivisionList = StudentService.findByDivId(divId);
        List<Student> studentOfExamList = examSubStudCompService.findByExamId(examId, subDivId);
        String match = "";
        for (Student allStudent : allStudentOfDivisionList) {
            match = "false";
            for (Student examStudent : studentOfExamList) {
                if (allStudent.getId() == examStudent.getId()) {
                    match = "true";
                    break;
                }
            }
            if (match.equals("false")) {
                studentNotInExamList.add(allStudent);
            }
        }
        studentListNotExamJSON = gson.toJson(studentNotInExamList);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return studentListNotExamJSON;
}


@RequestMapping(value = "/DeleteBranch", method = RequestMethod.POST)
@ResponseBody
public String DeleteBranch(Teacher teacher,Branch branch){
    LOGGER.info("**********inside DeleteBranch controller**********");
    String output;
    try {
        branchService.delet(branch.getId());
        output = "{\"action\":\"Selected Branch is deleted sucessfully \",\"status\":\"success\"}";
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to delete selected branch \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/GetExamsMode", method = RequestMethod.POST)
@ResponseBody
public String GetExamsMode(){
    LOGGER.info("**********inside GetExamsMode controller**********");
    List<ExamMode> examModeList = examModeService.getAll();
    String JSONexamModeList = gson.toJson(examModeList);
    // LOGGER.info(JSONexamModeList);
    return JSONexamModeList;
}


@RequestMapping(value = "/changeTPassword", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
@ResponseBody
public String changeTPassword(Teacher teacher,HashMap<String,String> requestData,Model model){
    LOGGER.info("**********inside changeTPassword controller**********");
    String response = "";
    String CurrentPassword = requestData.get("CurrentPassword");
    String NewPassword = requestData.get("NewPassword");
    String RePassword = requestData.get("RePassword");
    String existPassword = teacher.getLogin().getPassword();
    LOGGER.info(CurrentPassword);
    LOGGER.info(NewPassword);
    LOGGER.info(RePassword);
    LOGGER.info(existPassword);
    if (existPassword.equals(CurrentPassword)) {
        LOGGER.info("existPassword equals CurrentPassword");
        teacherService.changePassword(NewPassword, teacher.getLogin());
        teacher.getLogin().setPassword(NewPassword);
        model.addAttribute("teacher", teacher);
        return response = "{\"status\":\"Success\"}";
    } else {
        LOGGER.warn("existPassword not equals CurrentPassword");
        response = "{\"status\":\"failed\",\"cause\":\"not_valid_current_Password\"}";
        LOGGER.warn(response);
        return response;
    }
// return response = "{\"status\":\"failed\"}";
}


@RequestMapping(value = "/UpdateSubject/{instId}", method = RequestMethod.POST)
@ResponseBody
public String UpdateSubject(Subject sub,int InstituteId){
    LOGGER.info("**********inside UpdateSubject controller**********");
    sub.setInstitute(instituteService.find(InstituteId));
    subjectService.update(sub);
    String JSON = "";
    JSON += "{";
    JSON += "\"id\":" + sub.getId() + ",";
    JSON += "\"name\":\"" + sub.getName() + "\",";
    JSON += "\"discription\":\"" + sub.getDiscription() + "\"";
    JSON += "}";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/TeacherRequestForApprovalListJSON", method = RequestMethod.GET)
@ResponseBody
public String TeacherRequestForApprovalListJSON(Teacher teacher){
    LOGGER.info("**********inside TeacherRequestForApprovalListJSON controller**********");
    LOGGER.info("teachers insitute id is :" + teacher.getInstitute().getId());
    List<Teacher> teacherList = instituteService.getallPendingTeacherForApproval(instituteService.find(teacher.getInstitute().getId()));
    String teacherListJSON = gson.toJson(teacherList);
    return teacherListJSON;
}


@RequestMapping(value = "/GetSubjectNotInDivisionInJSON", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectNotInDivisionInJSON(Division division,Teacher teacher){
    LOGGER.info("**********inside Get Subject Not In Division controller**********");
    Institute inst = teacherService.GetInstitute(teacher.getId());
    int instituteId = inst.getId();
    LOGGER.info(division);
    LOGGER.info(instituteId);
    String subjectListNotInDivJSON = "";
    List<Subject> subjectNotInDivList = new ArrayList<Subject>();
    try {
        List<Subject> subjectList = subjectDivCompositService.findByDivId(division.getId());
        List<Subject> allSubjectOfInstituteList = subjectService.getallOfInstitute(instituteId);
        String match = "";
        for (Subject allSubject : allSubjectOfInstituteList) {
            match = "false";
            for (Subject divSubject : subjectList) {
                if (allSubject.getId() == divSubject.getId()) {
                    match = "true";
                    break;
                }
            }
            if (match.equals("false")) {
                subjectNotInDivList.add(allSubject);
            }
        }
        subjectListNotInDivJSON = gson.toJson(subjectNotInDivList);
        if (subjectNotInDivList.isEmpty()) {
            subjectListNotInDivJSON = "{\"ErrorMessage\":\"Selected division having all subjects\"}";
        } else {
            subjectListNotInDivJSON = gson.toJson(subjectNotInDivList);
        }
    } catch (Exception e) {
        subjectListNotInDivJSON = "{\"ErrorMessage\":\"Selected division having all subjects\"}";
        e.printStackTrace();
    }
    LOGGER.info(subjectListNotInDivJSON);
    return subjectListNotInDivJSON;
}


@RequestMapping(value = "/addSubjectToDivision/{subId}/{divId}/{instId}", method = RequestMethod.POST)
@ResponseBody
public String addSubjectToDivision(int subId,int divId,int instId){
    LOGGER.info("**********from addSubjectToDivision controller**********");
    LOGGER.info("subject to be added is with id" + subId);
    String subjectListNotInDivJSON = "";
    try {
        Subject sub = subjectService.find(subId);
        LOGGER.info("name of the subject is " + sub.getName());
        Division div = divisionService.find(divId);
        LOGGER.info("name of the division is " + div.getName());
        SubjectDivComposit sdc = new SubjectDivComposit(div, sub);
        subjectDivCompositService.create(sdc);
        List<Subject> subjectListOfDiv = subjectDivCompositService.findByDivId(divId);
        List<Subject> allSubjectOfInstituteList = subjectService.getallOfInstitute(instId);
        List<Subject> subjectNotInDivList = new ArrayList<Subject>();
        for (Subject s : subjectListOfDiv) {
            LOGGER.info(s.getName());
        }
        LOGGER.info("\nInstitute subject");
        for (Subject s : allSubjectOfInstituteList) {
            LOGGER.info(s.getName());
        }
        String match = "";
        for (Subject allSubject : allSubjectOfInstituteList) {
            match = "false";
            for (Subject divSubject : subjectListOfDiv) {
                if (allSubject.getId() == divSubject.getId()) {
                    match = "true";
                    break;
                }
            }
            if (match.equals("false")) {
                subjectNotInDivList.add(allSubject);
            }
        }
        LOGGER.info("\nSubject not in division");
        for (Subject s : subjectNotInDivList) {
            LOGGER.info(s.getName());
        }
        LOGGER.info("Subject is added with the id " + subId);
        subjectListNotInDivJSON = gson.toJson(subjectNotInDivList);
    // result="{\"message\":\"Subject with id "+subId+" is added
    // \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
        LOGGER.info("error in adding with Subject id : " + subId);
    // result="{\"message\":\"ERROR...!! subject with id "+subId+" not
    // added\",\"status\":\"fail\"}";
    }
    LOGGER.info(subjectListNotInDivJSON);
    return subjectListNotInDivJSON;
}


@RequestMapping(value = "/TreeStructureSujectsOfExam/{InstId}/{ExamId}", method = RequestMethod.POST)
@ResponseBody
public String TreeStructureSujectsOfExam(int InstId,int ExamId){
    LOGGER.info("**********inside TreeStructureSujectsOfExam/{InstId}/{ExamId} controller**********");
    String JSON = instituteService.TreeStructureSujectsOFExam(InstId, ExamId);
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/GetCalender/{divId}", method = RequestMethod.POST)
@ResponseBody
public String GetCalender(int id,Teacher teacher){
    LOGGER.info("**********from GetCalender/{divId} controller and division id is :" + id + "**********");
    Division d = divisionService.find(id);
    LOGGER.info("division is " + d);
    String result = "";
    try {
        Schedule schedule = scheduleService.fordivision(id);
        LOGGER.info(schedule);
        result = "{\"schedule\":\" " + schedule.getString() + " \",\"status\":\"success\"}";
    } catch (Exception e) {
        result = "{\"status\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/TeacherRequestApprover/{id}", method = RequestMethod.GET)
@ResponseBody
public String approveTeacherApprovalRequest(int id,boolean authoriseStudent,boolean authoriseTeacher,boolean fillAttendance,boolean fillSchedule,boolean mailParent,boolean mailStudent,boolean mailTeacher,boolean msgParent,boolean msgStudent,boolean msgTeacher,boolean setExam,boolean updateResults,boolean alterInstituteStructure){
    LOGGER.info("**********from /approveTeacherApprovalRequest/{id} controller**********");
    String result = "";
    LOGGER.info("authoriseTeacher" + authoriseTeacher + "fillAttendance:" + fillAttendance);
    Permissions p = new Permissions(authoriseStudent, authoriseTeacher, fillAttendance, fillSchedule, mailParent, mailStudent, mailTeacher, msgParent, msgStudent, msgTeacher, setExam, updateResults, alterInstituteStructure);
    LOGGER.info("permssions are :" + p);
    LOGGER.info("teacher to be updated is with id" + id);
    try {
        // find teacher
        Teacher t = teacherService.find(id);
        // create permissions
        permissionsService.create(p);
        // attach permissions to teacher
        t.setPermissions(p);
        // update teacher
        teacherService.update(t);
        // get login of teacher
        Login l = loginService.find(t.getLogin().getId());
        // enable the institute flag
        l.setEnableInstitute(true);
        // update login
        loginService.update(l);
        LOGGER.info("Teacher is updated with the id " + id);
        result = "{\"message\":\"Teacher with id " + id + " is updated \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        LOGGER.warn(e);
        LOGGER.warn("error in updation with teacher id : " + id);
        result = "{\"message\":\"ERROR...!! Teacher with id " + id + " not updated\",\"c\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/sendSMS/{contactNo}", method = RequestMethod.GET)
@ResponseBody
public String sendSMS(String contactNo,String text){
    LOGGER.info("**********from sendSMS/{contactNo} controller**********");
    String result = "";
    try {
        String pubId = snsService.sendSMSMessage(text, contactNo);
        result = "{\"status\":\"success\",\"pubid\":\"" + pubId + "\"}";
    } catch (Exception e) {
        result = "{\"status\":\"fail\",\"message\":\"failed to send sms\"}";
        e.printStackTrace();
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/smsSubjectStudentResult", method = RequestMethod.GET)
@ResponseBody
public String smsSubjectStudentResult(int examId,int subDivId){
    LOGGER.info("**********from smsSubjectStudentResult controller**********");
    String result = resultService.smsSubjectStudentResult(examId, subDivId);
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/AddNewClass/{className}", method = RequestMethod.POST)
@ResponseBody
public String AddNewClass(String className,Teacher teacher,Branch branch){
    LOGGER.info("**********inside AddNewClass controller**********");
    String output = "";
    int flag = 0;
    try {
        Institute inst = teacherService.GetInstitute(teacher.getId());
        List<Classes> classlist = classesService.getallOfParticularBranch(branch);
        for (Classes c : classlist) {
            if (c.getName().equalsIgnoreCase(className)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to add new class \",\"status\":\"classExist\"}";
        } else {
            Classes classes = new Classes();
            classes.setName(className);
            classes.setBranch(branch);
            classesService.create(classes);
            output = "{\"action\":\"New Class is added sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to add new class \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/AddStudentToDivisionGetStuendNotInAnyDivision", method = RequestMethod.GET)
@ResponseBody
public String AddStudentToDivisionGetStuendNotInAnyDivision(Model model,Teacher teacher){
    LOGGER.info("**********inside AddStudentToDivisionGetStuendNotInAnyDivision controller**********");
    LOGGER.info("teachers insitute id is :" + teacher.getInstitute().getId());
    List<Student> studnetList = instituteService.getallStudentWhoAreNotInAnyDivision(instituteService.find(teacher.getInstitute().getId()));
    for (Student s : studnetList) {
        LOGGER.info(s.getFname());
    }
    String studentListJSON = gson.toJson(studnetList);
    return studentListJSON;
}


@RequestMapping(value = "/RenameDivision/{divisionName}/{classId}", method = RequestMethod.POST)
@ResponseBody
public String RenameDivision(String divisionName,int classId,Teacher teacher,Division division){
    LOGGER.info("**********inside RenameDivision controller**********");
    String output;
    int flag = 0;
    try {
        Classes classes = classesService.find(classId);
        List<Division> divisionlist = divisionService.getallOfParticularClass(classes);
        for (Division d : divisionlist) {
            if (d.getName().equalsIgnoreCase(divisionName)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to rename division \",\"status\":\"divisionExist\"}";
        } else {
            division.setName(divisionName);
            division.setClasses(classes);
            divisionService.update(division);
            output = "{\"action\":\"Selected Division is renamed sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to rename selected division \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/DeleteDivision", method = RequestMethod.POST)
@ResponseBody
public String DeleteDivision(Teacher teacher,Division division){
    LOGGER.info("**********inside DeleteDivision controller**********");
    String output;
    try {
        divisionService.delet(division.getId());
        output = "{\"action\":\"Selected Division is deleted sucessfully \",\"status\":\"success\"}";
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to delete selected division \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/deleteSubjectFromDivision/{subId}/{divId}", method = RequestMethod.GET)
@ResponseBody
public String deleteSubjectFromDivision(int subId,int divId){
    LOGGER.info("**********from deleteSubjectFromDivision controller**********");
    String result = "";
    LOGGER.info("subject to be deleted is with id" + subId);
    try {
        Subject s = subjectService.find(subId);
        LOGGER.info("name of the subject is " + s.getName());
        subjectDivCompositService.deleteByDivId(subId, divId);
        LOGGER.info("Subject is deleted with the id " + subId);
        result = "{\"message\":\"Subject with id " + subId + " is deleted \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.info(e.getMessage());
        e.printStackTrace();
        LOGGER.info("error in deletion with Subject id : " + subId);
        result = "{\"message\":\"ERROR...!! subject with id " + subId + " not deleted\",\"status\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/RenameBranch/{branchName}", method = RequestMethod.POST)
@ResponseBody
public String RenameBranch(String branchName,Teacher teacher,Branch branch){
    LOGGER.info("**********inside RenameBranch controller**********");
    String output;
    int flag = 0;
    try {
        Institute inst = teacherService.GetInstitute(teacher.getId());
        List<Branch> branchlist = branchService.getallOfParticularInstitute(inst);
        for (Branch b : branchlist) {
            if (b.getName().equalsIgnoreCase(branchName)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to rename branch \",\"status\":\"branchExist\"}";
        } else {
            branch.setName(branchName);
            branch.setInstitute(inst);
            branchService.update(branch);
            output = "{\"action\":\"Selected Branch is renamed sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to rename selected branch \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/", method = RequestMethod.GET)
public String IndexController(Model model,Branch branch1,Teacher teacher){
    LOGGER.info("**********this is IndexController controller**********");
    return "Teacher/index";
}


@RequestMapping(value = "/GetSubjectDivCompIDResult/{examId}", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectDivCompIDResult(int examId){
    LOGGER.info("**********inside GetSubjectDivCompID controller**********");
    List<SubjectDivComposit> subjectDivCompList = examSubStudCompService.findByExamId(examId);
    String JSON = "[";
    for (SubjectDivComposit s : subjectDivCompList) {
        LOGGER.info("\n SubDivID : " + s.getId());
    }
    for (SubjectDivComposit s : subjectDivCompList) {
        JSON += "{";
        JSON += "\"id\":" + s.getId();
        JSON += "},";
    }
    JSON = JSON.substring(0, JSON.length() - 1);
    JSON += "]";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/GetDivisionListInJSON/{id}", method = RequestMethod.GET)
@ResponseBody
public String GetDivisionListInJSON(int id){
    LOGGER.info("**********from GetDivisionListInJSON controller**********");
    LOGGER.info(id);
    List<Division> divisionList = divisionService.getallOfParticularClass(classesService.find(id));
    String divisionListJSON = gson.toJson(divisionList);
    LOGGER.info(divisionListJSON);
    return divisionListJSON;
}


@RequestMapping(value = "/GetStudentOfDivisionInJSON", method = RequestMethod.POST)
@ResponseBody
public String GetStudentOfDivisionInJSON(Division division){
    LOGGER.info("**********inside GetStudentOfDivisionInJSON controller**********");
    LOGGER.info(division);
    String studentListJSON = "";
    try {
        LOGGER.info("in try");
        List<Student> studentList = StudentService.findByDivId(division.getId());
        for (Student s : studentList) {
            LOGGER.info(s.getFname() + " " + s.getLname());
        }
        studentListJSON = gson.toJson(studentList);
        if (studentList.isEmpty()) {
            studentListJSON = "{\"ErrorMessage\":\"Selected division does not contain any student\"}";
        } else {
            studentListJSON = gson.toJson(studentList);
        }
    } catch (Exception e) {
        studentListJSON = "{\"ErrorMessage\":\"Selected division does not contain any student\"}";
        e.printStackTrace();
    }
    LOGGER.info(studentListJSON);
    return studentListJSON;
}


@RequestMapping(value = "/TreeStructureSujectsNotInExam/{InstId}/{ExamId}", method = RequestMethod.POST)
@ResponseBody
public String TreeStructureSujectsNotInExam(int InstId,int ExamId){
    LOGGER.info("**********inside TreeStructureSujectsNotInExam/{InstId}/{ExamId} controller**********");
    String JSON = instituteService.TreeStructureSujectsNotInExam(InstId, ExamId);
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "updateDivisionScheduleMethod", method = RequestMethod.POST)
@ResponseBody
public String updateDivisionScheduleMethod(Model model,Schedule schedule,Teacher teacher){
    String output;
    LOGGER.info("**********this is updateDivisionScheduleMethod controller**********");
    LOGGER.info(schedule);
    String string = schedule.getString();
    LOGGER.info("before updating string is" + schedule.getString());
    String result = string.replace("\"", "\'");
    LOGGER.info(result);
    schedule.setString(result);
    // if the schedule already exist update it
    try {
        // get the id of the existing schedule
        // LOGGER.info("division is is"+schedule.getDivision().getId());
        Schedule s1 = scheduleService.fordivision(schedule.getId());
        s1.setString(schedule.getString());
        LOGGER.info("after updating string is" + schedule.getString());
        LOGGER.info("we are updating the record :" + s1);
        // update the variable for the calender string
        scheduleService.update(s1);
        LOGGER.info("------------------------schedule is updated------------------------- ");
        // model.addAttribute("SuccessMessage", "Schedule Updated for the division");
        output = "{\"action\":\"Schedule is updated for the division \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.info("shcedule do not exist for this division creating new ");
        try {
            LOGGER.info("finding the division of the schedule with the id :" + schedule.getId());
            Division d = divisionService.find(schedule.getId());
            LOGGER.info("------division is :" + d);
            schedule.setDivision(d);
            LOGGER.info("we are going to create the new shcedule");
            LOGGER.info("we aer about to save the :" + schedule);
            scheduleService.create(schedule);
            LOGGER.info("------------------------schedule is saved------------------------- ");
            // model.addAttribute("SuccessMessage", "Schedule created for the division");
            output = "{\"action\":\"Failed to created/update Schedule for the division \",\"status\":\"fail\"}";
        } catch (Exception e1) {
            // model.addAttribute("ErrorMessage", "error in saving Schedule for the
            // division");
            LOGGER.warn("------------------------error in schedule creation------------------------- ");
            output = "{\"action\":\"Schedule is created for the division \",\"status\":\"success\"}";
        }
    }
    return output;
}


@RequestMapping(value = "/GetStudentOfDivision/{id}", method = RequestMethod.GET)
@ResponseBody
public String GetStudentOfDivision(int DivID){
    LOGGER.info("**********inside GetStudentOfDivision controller**********");
    LOGGER.info(DivID);
    String studentListJSON = "";
    try {
        LOGGER.info("in try");
        List<Student> studentList = StudentService.findByDivId(DivID);
        for (Student s : studentList) {
            LOGGER.info(s.getFname() + " " + s.getLname());
        }
        studentListJSON = gson.toJson(studentList);
        if (studentList.isEmpty()) {
            studentListJSON = "{\"ErrorMessage\":\"Selected division does not contain any student\"}";
        } else {
            studentListJSON = gson.toJson(studentList);
        }
    } catch (Exception e) {
        studentListJSON = "{\"ErrorMessage\":\"Selected division does not contain any student\"}";
        e.printStackTrace();
    }
    LOGGER.info(studentListJSON);
    return studentListJSON;
}


@RequestMapping(value = "/deleteSelectedStudentFromExam/{subDivId1}/{examId1}/{divId1}", method = RequestMethod.POST)
@ResponseBody
public String deleteSelectedStudentFromExam(int subDivId1,int examId1,int divId1,ArrayList<Student> studentList){
    LOGGER.info("**********inside deleteSelectedStudentFromExam controller**********");
    LOGGER.info("sub_divComposit id : " + subDivId1);
    LOGGER.info("div id : " + divId1);
    LOGGER.info("exam id : " + examId1);
    String studentListJSON = gson.toJson(studentList);
    LOGGER.info(studentListJSON);
    try {
        for (Student examStudent : studentList) {
            LOGGER.info("student id: " + examStudent.getId());
            examSubStudCompService.deletStudentFromExam(examStudent.getId(), subDivId1, examId1);
        }
        List<Student> studentInExamList = examSubStudCompService.findByExamId(examId1, subDivId1);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "";
}


@RequestMapping(value = "/sendSMSSubjectResult", method = RequestMethod.POST)
@ResponseBody
public String sendSMSSubjectResult(String text){
    LOGGER.info("**********from sendSMSSubjectResult controller**********");
    String result = "";
    LOGGER.info(text);
    JsonElement element = gson.fromJson(text, JsonElement.class);
    JsonArray jarray = element.getAsJsonArray();
    for (Object o : jarray) {
        JsonObject jsonLineItem = (JsonObject) o;
        String studentName = null;
        String obtainedMarks = null;
        String outOfMarks = null;
        String remarks = null;
        String examName = null;
        String subjectName = null;
        String contactNo = null;
        String message = null;
        if (jsonLineItem.get("studentSelected") != null) {
            JsonObject student = (JsonObject) jsonLineItem.get("student");
            JsonObject studentResult = (JsonObject) jsonLineItem.get("result");
            JsonObject exam = (JsonObject) jsonLineItem.get("exam");
            JsonObject examSubjectStudentComp = (JsonObject) jsonLineItem.get("examSubjectStudentCompositTable");
            JsonObject SubDivComp = (JsonObject) jsonLineItem.get("SubDivComp");
            studentName = stringUtil.removeFirstLastChar(student.get("fname").toString());
            obtainedMarks = studentResult.get("obtainedMarks").toString();
            outOfMarks = examSubjectStudentComp.get("outOfMarks").toString();
            remarks = stringUtil.removeFirstLastChar(studentResult.get("remarks").toString());
            examName = stringUtil.removeFirstLastChar(exam.get("discription").toString());
            String subDivId = SubDivComp.get("id").toString();
            subjectName = subjectDivCompositService.findSubjectName(Integer.parseInt(subDivId));
            contactNo = "+91" + stringUtil.removeFirstLastChar(student.get("contactno").toString());
            message = "Hi " + studentName + ", You got " + obtainedMarks + " out of " + outOfMarks + " marks in " + subjectName + " subject for " + examName + ". Remark : you " + remarks + ".";
            try {
                String pubId = snsService.sendSMSMessage(message, contactNo);
                result = "{\"status\":\"success\",\"pubid\":\"" + pubId + "\"}";
            } catch (Exception e) {
                result = "{\"status\":\"fail\",\"message\":\"failed to send sms\"}";
                e.printStackTrace();
            }
            LOGGER.info(message);
            LOGGER.info(contactNo);
        }
        if (jsonLineItem.get("parentSelected") != null) {
            JsonObject student = (JsonObject) jsonLineItem.get("student");
            JsonObject studentResult = (JsonObject) jsonLineItem.get("result");
            JsonObject exam = (JsonObject) jsonLineItem.get("exam");
            JsonObject examSubjectStudentComp = (JsonObject) jsonLineItem.get("examSubjectStudentCompositTable");
            JsonObject SubDivComp = (JsonObject) jsonLineItem.get("SubDivComp");
            String studId = student.get("id").toString();
            Parent p = parentService.findByStudentId(Integer.parseInt(studId));
            studentName = stringUtil.removeFirstLastChar(student.get("fname").toString());
            obtainedMarks = studentResult.get("obtainedMarks").toString();
            outOfMarks = examSubjectStudentComp.get("outOfMarks").toString();
            remarks = stringUtil.removeFirstLastChar(studentResult.get("remarks").toString());
            examName = stringUtil.removeFirstLastChar(exam.get("discription").toString());
            String subDivId = SubDivComp.get("id").toString();
            subjectName = subjectDivCompositService.findSubjectName(Integer.parseInt(subDivId));
            contactNo = "+91" + p.getContactno();
            message = "Hi " + p.getFname() + " " + p.getLname() + ",Your child " + studentName + " got " + obtainedMarks + " out of " + outOfMarks + " marks in " + subjectName + " subject for " + examName + ". Remark : He is " + remarks + ".";
            try {
                String pubId = snsService.sendSMSMessage(message, contactNo);
                result = "{\"status\":\"success\",\"pubid\":\"" + pubId + "\"}";
            } catch (Exception e) {
                result = "{\"status\":\"fail\",\"message\":\"failed to send sms\"}";
                e.printStackTrace();
            }
            LOGGER.info(message);
            LOGGER.info(contactNo);
        // parentSelected=jsonLineItem.get("parentSelected").toString();
        }
    /*	 LOGGER.info(studentSelected);
			 LOGGER.info(parentSelected);
			 
			 JsonElement examSubjectStudentCompositTable =jsonLineItem.get("examSubjectStudentCompositTable");
			 JsonElement Result =jsonLineItem.get("result");
			 JsonElement student =jsonLineItem.get("student");
			 LOGGER.info(examSubjectStudentCompositTable.toString());
			 LOGGER.info(Result.toString());
			 LOGGER.info(student.toString());*/
    }
    return result;
}


@RequestMapping(value = "/GetClassesListInJSON/{id}", method = RequestMethod.GET)
@ResponseBody
public String GetClassesListINJSON(int id){
    LOGGER.info("**********from GetClassesListINJSON controller**********");
    List<Classes> classList = classesService.getallOfParticularBranch(branchService.find(id));
    String classesListJSON = gson.toJson(classList);
    LOGGER.info(classesListJSON);
    return classesListJSON;
}


@RequestMapping(value = "/DeleteSubjectsFromExam/{ExamId}", method = RequestMethod.POST)
@ResponseBody
public String DeleteSubjectsFromExam(List<Integer> subdivIds,int ExamId){
    LOGGER.info("**********inside DeleteSubjectsFromExam/{ExamId} controller**********");
    Integer count = 0;
    StringBuffer sb = new StringBuffer();
    sb.append("\"FailedToDelet\":[");
    for (Integer subDivId : subdivIds) {
        try {
            examSubStudCompService.deletSubjectFromExam(ExamId, subDivId);
            count++;
        } catch (Exception e) {
            sb.append(subDivId + ",");
        }
    }
    sb.append("]");
    if (sb.lastIndexOf(",") != -1) {
        sb.deleteCharAt(sb.lastIndexOf(","));
    }
    ;
    String JSON = "{\"status\":\"success\",\"SuccessCount\":" + count + "," + sb.toString() + "}";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/AddNewDivision/{divisionName}", method = RequestMethod.POST)
@ResponseBody
public String AddNewDivision(String divisionName,Teacher teacher,Classes classes){
    LOGGER.info("**********inside AddNewDivision controller**********");
    String output = "";
    int flag = 0;
    try {
        Institute inst = teacherService.GetInstitute(teacher.getId());
        List<Division> divisionlist = divisionService.getallOfParticularClass(classes);
        for (Division d : divisionlist) {
            if (d.getName().equalsIgnoreCase(divisionName)) {
                flag = 2;
                break;
            }
        }
        if (flag == 2) {
            output = "{\"action\":\"Failed to add new division \",\"status\":\"divisionExist\"}";
        } else {
            Division division = new Division();
            division.setName(divisionName);
            division.setClasses(classes);
            divisionService.create(division);
            output = "{\"action\":\"New Division is added sucessfully \",\"status\":\"success\"}";
        }
        return output;
    } catch (Exception e) {
        output = "{\"action\":\"Failed to add new division \",\"status\":\"fail\"}";
        return output;
    }
}


@RequestMapping(value = "/updateTeacher", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
@ResponseBody
public String updateTeacher(Teacher teacher1,Teacher teacher2,Model model){
    LOGGER.info("**********this is updateTeacher controller suraj**********");
    teacher1.setFname(teacher2.getFname());
    teacher1.setLname(teacher2.getLname());
    teacher1.setEmail(teacher2.getEmail());
    teacher1.setContactno(teacher2.getContactno());
    String response = "";
    try {
        teacherService.update(teacher1);
        response = "{\"status\":\"success\",\"teacher\":" + gson.toJson(teacher1) + "}";
        model.addAttribute("teacher", teacher1);
        model.addAttribute("teacherJSON", gson.toJson(teacher1));
    } catch (Exception e) {
        response = "{\"status\":\"fail\"}";
    }
    LOGGER.info(response);
    return response;
}


@RequestMapping(value = "/UpdateResult", method = RequestMethod.POST)
@ResponseBody
public String UpdateResult(List<HashMap<String,String>> StudResultList){
    LOGGER.info("**********inside UpdateResult controller**********");
    resultService.updateResult(StudResultList);
    String JSON = "";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/GetSubjectListofInstituteInJSON/{instId}", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectListofInstituteInJSON(int instId){
    LOGGER.info("**********inside Get Subject of particular institute controller**********");
    LOGGER.info(instId);
    String subjectListOfInstituteJSON = "";
    try {
        List<Subject> allSubjectOfInstituteList = subjectService.getallOfInstitute(instId);
        subjectListOfInstituteJSON = gson.toJson(allSubjectOfInstituteList);
        if (allSubjectOfInstituteList.isEmpty()) {
            subjectListOfInstituteJSON = "{\"ErrorMessage\":\"Selected Institute does not contain any subjects\"}";
        } else {
            subjectListOfInstituteJSON = gson.toJson(allSubjectOfInstituteList);
        }
    } catch (Exception e) {
        subjectListOfInstituteJSON = "{\"ErrorMessage\":\"Selected Institute does not contain any subjects\"}";
        e.printStackTrace();
    }
    LOGGER.info(subjectListOfInstituteJSON);
    return subjectListOfInstituteJSON;
}


@RequestMapping(value = "/AddSelectedStudentToDivision/{id}", method = RequestMethod.POST)
@ResponseBody
public String AddSelectedStudentToDivision(int id,ArrayList<Student> studentList){
    LOGGER.info("**********inside AddSelectedStudentToDivision controller**********");
    Division division = divisionService.find(id);
    LOGGER.info("division name is " + division.getName());
    for (Student s : studentList) {
        LOGGER.info(s.getFname());
        StudentService.SetDivisionId(s.getId(), id);
    }
    LOGGER.info(division.getName());
    return gson.toJson(division);
}


@RequestMapping(value = "/deleteTeacherApprovalRequest/{id}", method = RequestMethod.GET)
@ResponseBody
public String deleteTeacherApprovalRequest(int id){
    LOGGER.info("**********from /deleteTeacherApprovalRequest/{id} controller**********");
    String result = "";
    LOGGER.info("teacher to be deleted is with id" + id);
    try {
        Teacher t = teacherService.find(id);
        loginService.delet(loginService.find(t.getLogin().getId()));
        LOGGER.info("Teacher is deleted with the id " + id);
        result = "{\"message\":\"Teacher with id " + id + " is deleted \",\"status\":\"success\"}";
    } catch (Exception e) {
        LOGGER.warn(e.getMessage());
        LOGGER.warn(e);
        LOGGER.warn("error in deletion with teacher id : " + id);
        result = "{\"message\":\"ERROR...!! Teacher with id " + id + " not deleted\",\"status\":\"fail\"}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/GetInstituteTreeStruct/{id}", method = RequestMethod.POST)
@ResponseBody
public String GetInstituteTreeStruct(int InstituteId){
    LOGGER.info("**********inside GetInstituteTreeStruct controller**********");
    String JSON = instituteService.GetInstituteTree(InstituteId);
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/DeleteExam/{id}", method = RequestMethod.POST)
@ResponseBody
public String DeleteExam(int ExamId){
    LOGGER.info("**********inside DeleteExam controller**********");
    String result = "";
    Exam e = examService.find(ExamId);
    result = "{";
    result += "\"message\":\"success\"";
    result += "}";
    try {
        examService.delet(e);
    } catch (Exception exception) {
        result = "{";
        result += "\"message\":\"failed\"";
        result += "}";
    }
    LOGGER.info(result);
    return result;
}


@RequestMapping(value = "/GetStudentListOfExamJSON/{subDivId1}/{examId1}", method = RequestMethod.POST)
@ResponseBody
public String GetStudentListOfExamJSON(int subDivId1,int examId1){
    LOGGER.info("**********inside GetStudentListOfExamJSON controller**********");
    String studentListOfExamJSON = "";
    LOGGER.info("sub_divComposit id : " + subDivId1);
    LOGGER.info("exam id : " + examId1);
    try {
        List<Student> studentInExamList = examSubStudCompService.findByExamId(examId1, subDivId1);
        for (Student examStudent : studentInExamList) {
            LOGGER.info("student id: " + examStudent.getId());
        }
        studentListOfExamJSON = gson.toJson(studentInExamList);
        if (studentInExamList.isEmpty()) {
            studentListOfExamJSON = "{\"ErrorMessage\":\"Selected exam doesn't contain students for selected subject \"}";
        } else {
            studentListOfExamJSON = gson.toJson(studentInExamList);
        }
    } catch (Exception e) {
        studentListOfExamJSON = "{\"ErrorMessage\":\"Selected exam doesn't contain students for selected subject catch\"}";
        e.printStackTrace();
    }
    LOGGER.info(studentListOfExamJSON);
    return studentListOfExamJSON;
}


@RequestMapping(value = "/GetSubjectDivCompID/{examId}", method = RequestMethod.POST)
@ResponseBody
public String GetSubjectDivCompID(int examId){
    LOGGER.info("**********inside GetSubjectDivCompID controller**********");
    List<SubjectDivComposit> subjectDivCompList = examSubStudCompService.findByExamId(examId);
    String JSON = "[";
    for (SubjectDivComposit s : subjectDivCompList) {
        LOGGER.info("\n SubDivID : " + s.getId());
    }
    for (SubjectDivComposit s : subjectDivCompList) {
        JSON += "{";
        JSON += "\"id\":" + s.getId();
        JSON += "},";
    }
    JSON = JSON.substring(0, JSON.length() - 1);
    JSON += "]";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/AddSubjectToExam", method = RequestMethod.POST)
@ResponseBody
public String AddSubjectToExam(List<ExamSubjectStudentCompositTable> examSubjectStudentSubList){
    LOGGER.info("**********inside AddSubjectToExam controller**********");
    String JSON = "";
    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentTime = sdf.format(dt);
    try {
        for (ExamSubjectStudentCompositTable examSubjectStudentSub : examSubjectStudentSubList) {
            /*
				 * LOGGER.info("examsubdivcomp "+examSubjectStudentSub+"exam id:"
				 * +examSubjectStudentSub.getExam().getId()+" subdiv id "+examSubjectStudentSub.
				 * getSubjectDivComposit().getId());
				 */
            LOGGER.info(examSubjectStudentSub);
            LOGGER.info(examSubjectStudentSub.getExam().getId());
            LOGGER.info(examSubjectStudentSub.getSubjectDivComposit().getId());
            examSubStudCompService.create(examSubjectStudentSub);
        }
        JSON = "{\"message\":\"success\"}";
    } catch (Exception e) {
        JSON = "{\"message\":\"failed to save subjects to exam\"}";
        e.printStackTrace();
    }
    return JSON;
}


@RequestMapping(value = "/GetExamsOFInstitute/{id}", method = RequestMethod.POST)
@ResponseBody
public String GetExamsOFInstitute(int InstituteId){
    LOGGER.info("**********inside GetExamsOFInstitute controller**********");
    List<Exam> examList = examService.getallOfParticularInstitute(InstituteId);
    String JSON = "[";
    for (Exam e : examList) {
        JSON += "{";
        JSON += "\"id\":" + e.getId() + ",";
        JSON += "\"examMode\":";
        JSON += "{";
        JSON += "\"id\":" + e.getExamMode().getId() + "},";
        JSON += "\"examType\":";
        JSON += "{";
        JSON += "\"id\":" + e.getExamType().getId() + "},";
        JSON += "\"discription\":\"" + e.getDiscription() + "\",";
        JSON += "\"outOf\":" + e.getOutOf() + ",";
        JSON += "\"passingMarks\":" + e.getPassingMarks() + ",";
        JSON += "\"regular\":" + e.getRegular() + "";
        JSON += "},";
    }
    JSON = JSON.substring(0, JSON.length() - 1);
    JSON += "]";
    LOGGER.info(JSON);
    return JSON;
}


@RequestMapping(value = "/getSessionVariables", method = RequestMethod.GET)
@ResponseBody
public String getSessionVariables(Teacher teacher,String teacherJson,String instituteJson,String permissionsJson){
    LOGGER.info("**********from getSessionVariables controller***************** ");
    String response = "{\"teacherJson\":" + teacherJson + ",\"instituteJson\":" + instituteJson + ",\"permissionsJson\":" + permissionsJson + "}";
    return response;
}


@RequestMapping(value = "/BranchListOfInstitute", method = RequestMethod.GET)
@ResponseBody
public String BranchListOfInstitute(Teacher teacher){
    LOGGER.info("**********inside BranchListOfInstitute controller**********");
    LOGGER.info("teachers insitute id is :" + teacher.getInstitute().getId());
    Institute inst = teacherService.GetInstitute(teacher.getId());
    LOGGER.info("institute is :" + inst);
    List<Branch> branchlist = branchService.getallOfParticularInstitute(inst);
    LOGGER.info("we are going to print the branches of current isntitute :");
    for (Branch b : branchlist) {
        LOGGER.info(b);
    }
    String branchListJSON = gson.toJson(branchlist);
    LOGGER.info(branchListJSON);
    return branchListJSON;
}


@RequestMapping(value = "/changeTUsername", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
@ResponseBody
public String changeTUsername(Teacher teacher,HashMap<String,String> requestData,Model model){
    LOGGER.info("**********inside changeTUsername controller**********");
    String response = "";
    String currentUsername = requestData.get("currentUsername");
    String NewUserName = requestData.get("NewUsername");
    String existName = teacher.getLogin().getUsername();
    LOGGER.info("response created");
    try {
        if (existName.equals(currentUsername)) {
            teacherService.changeUserName(NewUserName, teacher.getLogin());
            LOGGER.info("success name changed");
            response = "{\"status\":\"success\",\"teacher\":" + gson.toJson(teacher) + "}";
            LOGGER.info("work fine");
            teacher.getLogin().setUsername(NewUserName);
            model.addAttribute("teacher", teacher);
            LOGGER.info(response.toString());
            return response = "{\"status\":\"Success\"}";
        } else {
            response = "{\"status\":\"failed\",\"cause\":\"not_valid_current_username\"}";
        }
    } catch (Exception e) {
        LOGGER.warn(e.getStackTrace());
        response = "{\"status\":\"failed\"}";
    }
    LOGGER.info("works Fine:" + response);
    return response;
}


@RequestMapping(value = "/GetStudentListNotInExamJSON/{subDivId}/{divId}/{examId}", method = RequestMethod.POST)
@ResponseBody
public String GetStudentListNotInExamJSON(int subDivId,int divId,int examId){
    LOGGER.info("**********inside GetStudentListNotInExamJSON controller**********");
    String studentListNotExamJSON = "";
    LOGGER.info("sub_divComposit id : " + subDivId);
    LOGGER.info("div id : " + divId);
    LOGGER.info("exam id : " + examId);
    List<Student> studentNotInExamList = new ArrayList<Student>();
    try {
        List<Student> allStudentOfDivisionList = StudentService.findByDivId(divId);
        List<Student> studentOfExamList = examSubStudCompService.findByExamId(examId, subDivId);
        String match = "";
        for (Student examStudent : studentOfExamList) {
            LOGGER.info("student id: " + examStudent.getId());
        }
        for (Student allStudent : allStudentOfDivisionList) {
            match = "false";
            for (Student examStudent : studentOfExamList) {
                if (allStudent.getId() == examStudent.getId()) {
                    match = "true";
                    break;
                }
            }
            if (match.equals("false")) {
                studentNotInExamList.add(allStudent);
            }
        }
        studentListNotExamJSON = gson.toJson(studentNotInExamList);
        if (studentNotInExamList.isEmpty()) {
            studentListNotExamJSON = "{\"ErrorMessage\":\"Selected subject having all students\"}";
        } else {
            studentListNotExamJSON = gson.toJson(studentNotInExamList);
        }
    } catch (Exception e) {
        studentListNotExamJSON = "{\"ErrorMessage\":\"Selected subject having all students\"}";
        e.printStackTrace();
    }
    LOGGER.info(studentListNotExamJSON);
    return studentListNotExamJSON;
}


}