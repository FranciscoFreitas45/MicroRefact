package de.metas.ui.web.view;
 import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.NonNull;
import lombok.Value;
@Value
public class ViewProfileId {

 public  ViewProfileId NULL;

 private  String id;


@JsonValue
public String toJson(){
    return id;
}


public boolean isNull(ViewProfileId profileId){
    return profileId == null || Objects.equals(profileId, NULL);
}


@JsonCreator
public ViewProfileId fromJson(String profileIdStr){
    if (profileIdStr == null) {
        return NULL;
    }
    final String profileIdStrNorm = profileIdStr.trim();
    if (profileIdStrNorm.isEmpty()) {
        return NULL;
    }
    return new ViewProfileId(profileIdStrNorm);
}


}