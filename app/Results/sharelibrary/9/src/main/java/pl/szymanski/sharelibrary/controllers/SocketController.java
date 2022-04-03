package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import pl.szymanski.sharelibrary.entity.ChatMessage;
import pl.szymanski.sharelibrary.requests.ChatMessageRequest;
import pl.szymanski.sharelibrary.response.ChatMessageResponse;
import pl.szymanski.sharelibrary.services.ports.ChatMessageService;
@Controller
@RequiredArgsConstructor
public class SocketController {

 private  ChatMessageService chatMessageService;

 private  SimpMessagingTemplate simpMessagingTemplate;


@MessageMapping("/chat")
public void processMessage(ChatMessageRequest chatMessage){
    ChatMessage message = chatMessageService.saveMessage(chatMessage);
    simpMessagingTemplate.convertAndSendToUser(message.getRecipient().getId().toString(), "/queue/messages", ChatMessageResponse.of(message));
    simpMessagingTemplate.convertAndSendToUser(message.getSender().getId().toString(), "/queue/messages", ChatMessageResponse.of(message));
}


}