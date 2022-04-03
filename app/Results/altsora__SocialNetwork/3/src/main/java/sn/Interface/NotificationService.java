package sn.Interface;
public interface NotificationService {

   public ServiceResponse<ResponseDataMessage> saveNotificationSettings(Person person,NotificationSettingRequest request);
}