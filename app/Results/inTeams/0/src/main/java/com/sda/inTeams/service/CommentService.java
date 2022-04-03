package com.sda.inTeams.service;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Task.Task;
import com.sda.inTeams.model.Task.TaskStatus;
import com.sda.inTeams.repository.CommentRepository;
import com.sda.inTeams.repository.TaskRepository;
import com.sda.inTeams.Interface.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import com.sda.inTeams.Interface.UserRepository;

@Service
@RequiredArgsConstructor
public class CommentService implements DatabaseManageable<Comment>{

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    public List<Comment> getAllByTask(long taskId) throws InvalidOperation {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new InvalidOperation("Task id:" + taskId + " not found!"));
        return commentRepository.findAllByTask(task);
    }

    @Override
    public Optional<Comment> getById(long commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public Comment getByIdOrThrow(long commentId) throws InvalidOperation {
        return getById(commentId).orElseThrow(() -> new InvalidOperation("Comment id:" + commentId + " not found!"));
    }

    @Override
    public Comment add(Comment entity) throws InvalidOperation {
        if (!Objects.isNull(entity)) {
            return saveToDatabase(entity);
        } else {
            throw new InvalidOperation("Cannot add comment - Object is null!");
        }
    }

    @Override
    public void delete(long commentId) throws InvalidOperation {
        Comment comment = getByIdOrThrow(commentId);
        Task task = taskRepository.findByCommentsContaining(comment).orElseThrow(() -> new InvalidOperation("Task not found!"));
        task.getComments().remove(comment);
        taskRepository.save(task);
        commentRepository.delete(comment);
    }

    @Override
    public Comment saveToDatabase(Comment entity) {
        return commentRepository.save(entity);
    }

    public List<Comment> getAllUserComments(long userId) throws InvalidOperation {
        return commentRepository.findAllByCreator(userRepository.findById(userId).orElseThrow(() -> new InvalidOperation("User not found")));
    }

}
