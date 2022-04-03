package pl.szymanski.sharelibrary.controllers;
 import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation;
import pl.szymanski.sharelibrary.response.ChatMessageResponse;
import pl.szymanski.sharelibrary.response.ChatRoomResponse;
import pl.szymanski.sharelibrary.services.ports.ChatRoomService;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

 private  ChatRoomService chatRoomService;


@GetMapping("rooms")
public ResponseEntity<ChatRoomResponse> getRoom(Long senderId,Long recipientId){
    return new ResponseEntity<>(ChatRoomResponse.of(chatRoomService.getRoomBySenderIdAndRecipientId(senderId, recipientId)), HttpStatus.OK);
}


@GetMapping("rooms/{user}")
public ResponseEntity<List<ChatRoomResponse>> getUserRooms(Long userId){
    return new ResponseEntity<>(chatRoomService.getRoomByUserId(userId).stream().map(ChatRoomResponse::of).collect(Collectors.toList()), HttpStatus.OK);
}


@GetMapping("rooms/{id}/messages")
public ResponseEntity<List<ChatMessageResponse>> getRoomMessages(Long roomId){
    return new ResponseEntity<>(chatRoomService.getMessageFromRoom(roomId).stream().map(ChatMessageResponse::of).collect(Collectors.toList()), HttpStatus.OK);
}


}