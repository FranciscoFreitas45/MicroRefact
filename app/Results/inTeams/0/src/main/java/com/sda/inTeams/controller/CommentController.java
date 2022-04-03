package com.sda.inTeams.controller;
 import com.sda.inTeams.exception.InvalidOperation;
import com.sda.inTeams.model.Comment.Comment;
import com.sda.inTeams.model.Comment.CommentType;
import com.sda.inTeams.DTO.User;
import com.sda.inTeams.repository.TaskRepository;
import com.sda.inTeams.Interface.UserRepository;
import com.sda.inTeams.Interface.AuthorizationService;
import com.sda.inTeams.service.CommentService;
import com.sda.inTeams.service.TaskService;
import com.sda.inTeams.Interface.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import com.sda.inTeams.Interface.UserService;
import com.sda.inTeams.Interface.AuthorizationService;
@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

 private  CommentService commentService;

 private  TaskService taskService;

 private  UserService userService;

 private  AuthorizationService authorizationService;


@GetMapping("/add")
public String addCommentForm(Model model,Principal principal,long taskId){
    try {
        User user = authorizationService.getUserCredentials(principal).orElseThrow();
        taskService.getByIdOrThrow(taskId);
        model.addAttribute("newComment", new Comment());
        model.addAttribute("ownerId", taskId);
        model.addAttribute("creatorId", user.getId());
        model.addAttribute("commentTypes", CommentType.values());
        return "comment-add-form";
    } catch (InvalidOperation operation) {
        operation.printStackTrace();
    }
    return "redirect:/";
}


@GetMapping("/delete/{id}")
public String deleteComment(Principal principal,long commentId){
    try {
        Comment comment = commentService.getByIdOrThrow(commentId);
        if (authorizationService.isUserEligibleToDeleteComment(principal, comment)) {
            long taskId = comment.getTask().getId();
            commentService.delete(commentId);
            return "redirect:/task/" + taskId;
        } else {
        // unauthorized access
        }
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@GetMapping("/edit/{id}")
public String editComment(Model model,Principal principal,long commentId){
    try {
        Comment commentToEdit = commentService.getByIdOrThrow(commentId);
        if (authorizationService.isUserEligibleToEditComment(principal, commentToEdit)) {
            model.addAttribute("newComment", commentToEdit);
            model.addAttribute("ownerId", commentToEdit.getTask().getId());
            model.addAttribute("creatorId", commentToEdit.getCreator().getId());
            model.addAttribute("commentTypes", CommentType.values());
            return "comment-add-form";
        } else {
        // unauthorized access
        }
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
    }
    return "redirect:/";
}


@PostMapping("/add")
public String addComment(Comment comment,long taskId,long userId){
    try {
        comment.setCreator(userService.getByIdOrThrow(userId));
        comment.setTask(taskService.getByIdOrThrow(taskId));
        commentService.add(comment);
        return "redirect:/task/" + taskId;
    } catch (InvalidOperation invalidOperation) {
        invalidOperation.printStackTrace();
        return "redirect:/";
    }
}


}