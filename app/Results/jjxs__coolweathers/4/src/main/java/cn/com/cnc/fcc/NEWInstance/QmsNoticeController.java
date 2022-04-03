package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsNoticeController {

 private QmsNoticeRepository qmsnoticerepository;


@PutMapping
("/setNoticeRole/{id}")
public void setNoticeRole(@PathVariable(name = "id") Long id,@RequestParam(name = "noticeRole") String noticeRole){
 qmsnoticerepository.setNoticeRole(id,noticeRole);
}


@PutMapping
("/setNoticeUser/{id}")
public void setNoticeUser(@PathVariable(name = "id") Long id,@RequestParam(name = "noticeUser") String noticeUser){
 qmsnoticerepository.setNoticeUser(id,noticeUser);
}


@PutMapping
("/setNoticeInfo/{id}")
public void setNoticeInfo(@PathVariable(name = "id") Long id,@RequestParam(name = "noticeInfo") String noticeInfo){
 qmsnoticerepository.setNoticeInfo(id,noticeInfo);
}


@PutMapping
("/setReserveFirst/{id}")
public void setReserveFirst(@PathVariable(name = "id") Long id,@RequestParam(name = "reserveFirst") String reserveFirst){
 qmsnoticerepository.setReserveFirst(id,reserveFirst);
}


}