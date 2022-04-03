package sn.service;
 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sn.api.response.ResponseDataMessage;
import sn.api.response.ServiceResponse;
@Service
public class FeedService {


public ResponseEntity<ServiceResponse> getFeeds(String name,int offset,int itemPerPage){
    // TODO сменить, когда появится проверка авторизации
    boolean isAuthorized = true;
    // TODO сменить, успешное получение списка новостей
    boolean feedsIsOk = true;
    if (!isAuthorized) {
        return new ResponseEntity<>(new ServiceResponse<>("invalid_request", new ResponseDataMessage("User isn`t authorized")), HttpStatus.UNAUTHORIZED);
    }
    return feedsIsOk ? new ResponseEntity<>(new ServiceResponse(), HttpStatus.OK) : new ResponseEntity<>(new ServiceResponse<>("invalid_request", new ResponseDataMessage("Unable to get feeds")), HttpStatus.BAD_REQUEST);
}


}