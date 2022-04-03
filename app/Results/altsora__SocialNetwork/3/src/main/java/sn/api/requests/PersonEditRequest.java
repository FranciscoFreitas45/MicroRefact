package sn.api.requests;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonEditRequest {

@NonNull
@JsonProperty("first_name")
 private  String firstName;

@NonNull
@JsonProperty("last_name")
 private  String lastName;

@JsonProperty("birth_date")
 private  String birthDate;

@NonNull
 private  String phone;

@NonNull
@JsonProperty("photo_id")
 private  String photo;

 private  String about;

@NonNull
 private  String city;

@NonNull
 private  String country;

@JsonProperty("messages_permission")
 private  String messagesPermission;


}