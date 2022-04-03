package sn.controller;
 import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation;
import sn.api.response.NotificationResponse;
import sn.api.response.ServiceResponseDataList;
import sn.service.AccountService;
import sn.service.NotificationService;
import sn.Interface.AccountService;
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

 private  NotificationService notificationService;

 private  AccountService accountService;


@PutMapping
public ServiceResponseDataList<NotificationResponse> getNotificationByIdOrAll(Long id,boolean all){
    if (all) {
        return notificationService.readAllNotification(accountService.findCurrentUser());
    } else {
        return notificationService.readNotificationById(accountService.findCurrentUser(), id);
    }
}


@GetMapping
public ServiceResponseDataList<NotificationResponse> getNotificationList(int offset,int itemPerPage){
    return notificationService.getNotificationByPage(accountService.findCurrentUser(), offset, itemPerPage);
}


}