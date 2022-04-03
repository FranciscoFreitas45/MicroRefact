package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttachmentController {

 private Attachment attachment;

 private Attachment attachment;


@PutMapping
("/setUser_id")
public void setUser_id(@RequestParam(name = "user_id") String user_id){
attachment.setUser_id(user_id);
}


@PutMapping
("/setBoard_no")
public void setBoard_no(@RequestParam(name = "board_no") Integer board_no){
attachment.setBoard_no(board_no);
}


@PutMapping
("/setFile_name")
public void setFile_name(@RequestParam(name = "file_name") String file_name){
attachment.setFile_name(file_name);
}


@PutMapping
("/setExtension")
public void setExtension(@RequestParam(name = "extension") String extension){
attachment.setExtension(extension);
}


@PutMapping
("/setCreated_date")
public void setCreated_date(@RequestParam(name = "created_date") Date created_date){
attachment.setCreated_date(created_date);
}


}