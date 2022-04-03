package sn.controller.DialogController;
 import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import sn.api.requests.MessageSendRequest;
import sn.api.response.AbstractResponse;
import sn.api.response.DialogResponse;
import sn.api.response.ServiceResponse;
import sn.service.DialogService;
import java.util.List;
@Data
@NoArgsConstructor
public class UserIdsRequest {

@JsonProperty("user_ids")
 private List<Long> userIds;


}