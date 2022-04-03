package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ComplaintController {

 private Complaint complaint;

 private Complaint complaint;


@PutMapping
("/setComplaintReplyUser")
public void setComplaintReplyUser(@RequestParam(name = "complaintReplyUser") String complaintReplyUser){
complaint.setComplaintReplyUser(complaintReplyUser);
}


@PutMapping
("/setComplaintReply")
public void setComplaintReply(@RequestParam(name = "complaintReply") String complaintReply){
complaint.setComplaintReply(complaintReply);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") String userId){
complaint.setUserId(userId);
}


}