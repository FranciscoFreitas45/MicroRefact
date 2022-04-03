package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.exceptions.courses.PathWouldHaveCycle;
import kielce.tu.weaii.telelearn.exceptions.courses.TaskMustBeCompleted;
import kielce.tu.weaii.telelearn.exceptions.courses.TaskNotFound;
import kielce.tu.weaii.telelearn.models.Attachment;
import kielce.tu.weaii.telelearn.models.AttachmentData;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.models.courses.Task;
import kielce.tu.weaii.telelearn.models.courses.TaskScheduleRecord;
import kielce.tu.weaii.telelearn.models.courses.TaskStudent;
import kielce.tu.weaii.telelearn.repositories.ports.TaskRepository;
import kielce.tu.weaii.telelearn.requests.courses.TaskProgressPatchRequest;
import kielce.tu.weaii.telelearn.requests.courses.TaskRepeatPatchRequest;
import kielce.tu.weaii.telelearn.requests.courses.TaskRequest;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.servicedata.TaskStudentSummary;
import kielce.tu.weaii.telelearn.servicedata.TaskStudentSummaryRecord;
import kielce.tu.weaii.telelearn.services.ports.CourseService;
import kielce.tu.weaii.telelearn.services.ports.StudentService;
import kielce.tu.weaii.telelearn.services.ports.TaskService;
import kielce.tu.weaii.telelearn.services.ports.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import kielce.tu.weaii.telelearn.utilities.Constants.TASK_COMPLETED;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{

 private  TaskRepository taskRepository;

 private  UserServiceDetailsImpl userServiceDetails;

 private  UserService userService;

 private  CourseService courseService;

 private  StudentService studentService;


@Override
@Transactional(rollbackOn = IOException.class)
public Task add(TaskRequest request,List<MultipartFile> attachments){
    Task task = new Task();
    LocalDateTime now = LocalDateTime.now();
    BeanUtils.copyProperties(request, task);
    task.setCourse(courseService.getById(request.getCourseId()));
    task.setLearningTime(Duration.ofMinutes(request.getLearningTimeMinutes()).plus(Duration.ofHours(request.getLearningTimeHours())));
    task.setAttachments(prepareAttachments(attachments, now, task));
    task.setPreviousTasks(getPreviousTasks(request));
    checkTask(task);
    return taskRepository.save(task);
}


@Override
public TaskStudentSummary getStudentByTasksFromCurse(Long studentId,LocalDate today){
    if (!userService.isCurrentUserOrAdmin(studentId)) {
        throw new AuthorizationException("lista zadań użytkownika", userServiceDetails.getCurrentUser().getId(), studentId);
    }
    TaskStudentSummary taskStudentSummary = new TaskStudentSummary();
    List<Task> tasks = taskRepository.getStudentByTasksFromCurse(studentId);
    taskStudentSummary.setDelayedTasks(tasks.stream().filter(task -> task.getDueDate().isBefore(today) && (task.getStudentRecordOrNull(studentId) == null || task.getStudentRecordOrNull(studentId).getTaskCompletion() != TASK_COMPLETED)).map(task -> getTaskSchemeRecord(task, studentId, today)).collect(Collectors.toList()));
    taskStudentSummary.setTaskToRepeat(tasks.stream().filter(task -> task.getStudentRecordOrNull(studentId) != null && task.getStudentRecordOrNull(studentId).isToRepeat()).map(task -> getTaskSchemeRecord(task, studentId, today)).collect(Collectors.toList()));
    taskStudentSummary.setTasksForDay(tasks.stream().filter(task -> !task.getDueDate().isBefore(today)).filter(task -> task.getStudentRecordOrNull(studentId) == null || task.getStudentRecordOrNull(studentId).getTaskCompletion() != TASK_COMPLETED).map(task -> getTaskSchemeRecord(task, studentId, today)).collect(Collectors.groupingBy(record -> record.getTask().getDueDate())));
    return taskStudentSummary;
}


@Override
public Task updateProgress(Long id,TaskProgressPatchRequest request){
    if (!request.getStudentId().equals(userServiceDetails.getCurrentUser().getId())) {
        throw new AuthorizationException("Aktualizacja postępu zadania.", userServiceDetails.getCurrentUser().getId(), request.getStudentId());
    }
    Task task = getById(id);
    TaskStudent taskStudent = task.getStudentRecordOrNull(request.getStudentId());
    if (taskStudent != null) {
        taskStudent.setTaskCompletion(request.getProgress());
    } else {
        taskStudent = new TaskStudent();
        taskStudent.setTask(task);
        taskStudent.setStudent(studentService.getById(request.getStudentId()));
        taskStudent.setTaskCompletion(request.getProgress());
        task.getStudents().add(taskStudent);
    }
    if (taskStudent.getTaskCompletion() < 100) {
        taskStudent.setToRepeat(false);
    }
    return taskRepository.save(task);
}


public TaskStudentSummaryRecord getTaskSchemeRecord(Task task,Long studentId,LocalDate today){
    Duration totalLearningTime = task.getPlanRecords().stream().filter(record -> record.getStudent().getId().equals(studentId)).map(TaskScheduleRecord::getLearningTime).reduce(Duration.ZERO, Duration::plus);
    Duration totalPlannedLearningTime = task.getPlanRecords().stream().filter(record -> record.getStudent().getId().equals(studentId)).filter(record -> !record.getDate().isBefore(today)).map(TaskScheduleRecord::getPlannedTime).reduce(Duration.ZERO, Duration::plus);
    TaskStudentSummaryRecord record = new TaskStudentSummaryRecord();
    record.setTask(task);
    record.setTotalLearningTime(totalLearningTime);
    record.setTotalPlannedLearningTime(totalPlannedLearningTime);
    return record;
}


@Override
@Transactional(rollbackOn = IOException.class)
public Task update(Long id,TaskRequest request,List<MultipartFile> attachmentsToUpload){
    Task task = getById(id);
    BeanUtils.copyProperties(request, task);
    task.setLearningTime(Duration.ofMinutes(request.getLearningTimeMinutes()).plus(Duration.ofHours(request.getLearningTimeHours())));
    task.setPreviousTasks(getPreviousTasks(request));
    checkTask(task);
    deleteAttachments(request, task);
    task.getAttachments().addAll(prepareAttachments(attachmentsToUpload, LocalDateTime.now(), task));
    return taskRepository.save(task);
}


public void checkTask(Task task){
    if (checkCycle(task, task.getId())) {
        throw new PathWouldHaveCycle();
    }
}


public boolean checkCycle(Task currentTask,Long searchedTaskId){
    if (currentTask.getPreviousTasks().isEmpty()) {
        return false;
    }
    boolean hasCycle = false;
    for (Task previousTask : currentTask.getPreviousTasks()) {
        if (previousTask.getId().equals(searchedTaskId)) {
            return true;
        }
        hasCycle = hasCycle || checkCycle(previousTask, searchedTaskId);
    }
    return hasCycle;
}


@Override
@Transactional
public void delete(Long id){
    Task task = getById(id);
    for (Task pTask : task.getPreviousTasks()) {
        pTask.getNextTasks().remove(task);
    }
    for (Task nTask : task.getNextTasks()) {
        nTask.getPreviousTasks().remove(task);
    }
    taskRepository.delete(task);
}


@Override
public Task updateTaskRepeat(Long id,TaskRepeatPatchRequest request){
    if (!request.getStudentId().equals(userServiceDetails.getCurrentUser().getId())) {
        throw new AuthorizationException("Ustawanie zadania do powtórzenia.", userServiceDetails.getCurrentUser().getId(), request.getStudentId());
    }
    Task task = getById(id);
    TaskStudent taskStudent = task.getStudentRecordOrNull(request.getStudentId());
    if (taskStudent == null || taskStudent.getTaskCompletion() != TASK_COMPLETED) {
        throw new TaskMustBeCompleted();
    }
    taskStudent.setToRepeat(request.getToRepeat());
    return taskRepository.save(task);
}


public void deleteAttachments(TaskRequest request,Task task){
    if (request.getAttachmentIdsToDelete() != null) {
        for (long attachmentId : request.getAttachmentIdsToDelete()) {
            task.getAttachments().removeIf(attachment -> {
                if (attachment.getId() == null) {
                    return false;
                }
                return attachment.getId().equals(attachmentId);
            });
        }
    }
}


public List<Attachment> prepareAttachments(List<MultipartFile> attachments,LocalDateTime now,Task task){
    List<Attachment> attachmentList = new ArrayList<>();
    if (attachments != null) {
        for (MultipartFile file : attachments) {
            Attachment attachment = new Attachment();
            AttachmentData attachmentData = new AttachmentData();
            attachmentData.setData(file.getBytes());
            attachmentData.setAttachment(attachment);
            attachment.setAttachmentData(Arrays.asList(attachmentData));
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFileType(file.getContentType());
            attachment.setUploadTime(now);
            attachmentList.add(attachment);
            attachment.setTask(task);
        }
    }
    return attachmentList;
}


@Override
public Task getById(Long id){
    Task task = taskRepository.getById(id).orElseThrow(() -> new TaskNotFound(id));
    User currentUser = userServiceDetails.getCurrentUser();
    if ((currentUser.getUserRole().equals(UserRole.TEACHER) && !task.getCourse().getOwner().getId().equals(currentUser.getId())) || (currentUser.getUserRole().equals(UserRole.STUDENT) && task.getCourse().getStudents().stream().noneMatch(entry -> entry.getStudent().getId().equals(currentUser.getId())))) {
        throw new AuthorizationException("zadanie", currentUser.getId(), id);
    }
    return task;
}


public List<Task> getPreviousTasks(TaskRequest request){
    List<Task> previousTasks = new ArrayList<>();
    for (Long taskId : request.getPreviousTaskIds()) {
        previousTasks.add(getById(taskId));
    }
    return previousTasks;
}


}