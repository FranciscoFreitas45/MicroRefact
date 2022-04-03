package sn.service;
 import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.api.requests.NotificationSettingRequest;
import sn.api.response.NotificationResponse;
import sn.api.response.ResponseDataMessage;
import sn.api.response.ServiceResponse;
import sn.api.response.ServiceResponseDataList;
import sn.model.Notification;
import sn.model.NotificationSettings;
import sn.model.NotificationType;
import sn.model.Person;
import sn.model.enums.NotificationTypeCode;
import sn.repositories.NotificationRepository;
import sn.repositories.NotificationSettingsRepository;
import sn.repositories.NotificationTypeRepository;
import sn.utils.TimeUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import sn.DTO.ResponseDataMessage;
@Service
@RequiredArgsConstructor
public class NotificationService {

 private  String DATE_FILED_SORT;

 private  ConcurrentHashMap<NotificationTypeCode,NotificationType> notificationTypeMap;

 private  NotificationRepository notificationRepository;

 private  NotificationSettingsRepository notificationSettingsRepository;

 private  NotificationTypeRepository notificationTypeRepository;


public NotificationResponse convertNotificationToNotificationResponse(Notification notification){
    return NotificationResponse.builder().id(notification.getId()).typeId(notification.getType().getId()).sentTime(TimeUtil.getTimestampFromLocalDateTime(notification.getSentTime())).entityId(notification.getEntityId()).info(notification.getInfo()).build();
}


@Transactional
public ServiceResponseDataList<NotificationResponse> readNotificationById(Person person,Long id){
    Optional<Notification> notificationOptional = notificationRepository.findById(id);
    if (notificationOptional.isEmpty()) {
        return new ServiceResponseDataList<>(String.format("Для пользователя [%s] не найдено уведомление c кодом [%s]", person.getEmail(), id));
    }
    if (notificationOptional.get().isReaded()) {
        return new ServiceResponseDataList<>(String.format("Уведомление c кодом [%s] уже прочитанно", id));
    }
    List<NotificationResponse> notificationResponseList = new ArrayList<>();
    Notification notification = notificationOptional.get();
    notification.setReaded(true);
    notificationRepository.save(notification);
    notificationResponseList.add(convertNotificationToNotificationResponse(notification));
    return new ServiceResponseDataList<>(notificationResponseList);
}


public ServiceResponse<ResponseDataMessage> getAllNotificationType(){
    for (NotificationType type : notificationTypeRepository.findAll()) {
        if (!notificationTypeMap.containsKey(type.getCode())) {
            notificationTypeMap.put(type.getCode(), type);
        }
    }
    return new ServiceResponse<>(ResponseDataMessage.ok());
}


public List<NotificationResponse> convertNotificationListToNotificationResponseList(List<Notification> listNotification){
    return listNotification.parallelStream().map(this::convertNotificationToNotificationResponse).collect(Collectors.toList());
}


@Transactional
public ServiceResponse<ResponseDataMessage> saveNotificationSettings(Person person,NotificationSettingRequest request){
    // ЕСЛИ есть где это список будет заполняться разом, тогда можно это закомментировать
    if (!notificationTypeMap.containsKey(request.getNotificationType())) {
        notificationTypeMap.put(request.getNotificationType(), notificationTypeRepository.findByCode(request.getNotificationType()).orElseThrow());
    }
    NotificationSettings setting = notificationSettingsRepository.findByOwnerAndType(person, request.getNotificationType()).orElse(NotificationSettings.builder().owner(person).type(notificationTypeMap.get(request.getNotificationType())).build());
    setting.setEnable(request.isEnable());
    notificationSettingsRepository.save(setting);
    return new ServiceResponse<>(ResponseDataMessage.ok());
}


public ServiceResponseDataList<NotificationResponse> getNotificationByPage(Person person,int offset,int perPage){
    Pageable pageable = PageRequest.of(offset, perPage, Sort.Direction.DESC, DATE_FILED_SORT);
    Page<Notification> pageResponse = notificationRepository.findByToWhomAndIsReadedFalse(person, pageable);
    int total = (int) pageResponse.getTotalElements();
    List<NotificationResponse> response = convertNotificationListToNotificationResponseList(pageResponse.getContent());
    return new ServiceResponseDataList<>(total, offset, perPage, response);
}


@Transactional
public ServiceResponseDataList<NotificationResponse> readAllNotification(Person person){
    List<Notification> notificationList = notificationRepository.findAllByToWhomAndIsReadedFalse(person);
    if (!notificationList.isEmpty()) {
        notificationList.parallelStream().forEach(notification -> notification.setReaded(true));
        notificationRepository.saveAll(notificationList);
    }
    return new ServiceResponseDataList<>(convertNotificationListToNotificationResponseList(notificationList));
}


}