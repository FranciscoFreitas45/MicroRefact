package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.requests.SendMessageRequest;
import kielce.tu.weaii.telelearn.services.ports.MessageService;
import kielce.tu.weaii.telelearn.views.ConversationInfoView;
import kielce.tu.weaii.telelearn.views.MessageView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/message")
public class MessageController {

 private  MessageService messageService;


@GetMapping(path = "/{id}")
public ResponseEntity<List<ConversationInfoView>> getConversations(Long id){
    return new ResponseEntity<>(messageService.getConversations(id).stream().map(ConversationInfoView::from).collect(Collectors.toList()), HttpStatus.OK);
}


@PutMapping
public ResponseEntity<Object> sendMessage(SendMessageRequest request){
    messageService.sendMessage(request);
    return ResponseEntity.noContent().build();
}


@GetMapping(path = "/{id1}/{id2}")
public ResponseEntity<List<MessageView>> getConversation(Long id1,Long id2){
    return new ResponseEntity<>(messageService.getConversation(id1, id2).stream().map(MessageView::from).collect(Collectors.toList()), HttpStatus.OK);
}


}