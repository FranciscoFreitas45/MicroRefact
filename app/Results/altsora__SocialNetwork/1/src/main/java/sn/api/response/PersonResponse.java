package sn.api.response;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@Builder
@RequiredArgsConstructor
public class PersonResponse extends AbstractResponse{

@JsonProperty("id")
 private  long id;

@JsonProperty("first_name")
 private  String firstName;

@JsonProperty("last_name")
 private  String lastName;

@JsonProperty("reg_date")
 private  long regDate;

@JsonProperty("birth_date")
 private  long birthDate;

@JsonProperty("email")
 private  String email;

@JsonProperty("phone")
 private  String phone;

@JsonProperty("photo")
 private  String photo;

@JsonProperty("about")
 private  String about;

@JsonProperty("city")
 private  String city;

@JsonProperty("country")
 private  String country;

@JsonProperty("messages_permission")
 private  String messagesPermission;

@JsonProperty("last_online_time")
 private  long lastOnlineTime;

@JsonProperty("is_blocked")
 private  boolean isBlocked;


}