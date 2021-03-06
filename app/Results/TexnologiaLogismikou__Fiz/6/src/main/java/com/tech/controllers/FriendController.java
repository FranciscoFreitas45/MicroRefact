package com.tech.controllers;
 import com.tech.configurations.tools.Host;
import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.models.dtos.FriendDTO;
import com.tech.controllers.superclass.BaseController;
import com.tech.models.entities.Friend;
import com.tech.services.interfaces.IFriendService;
import com.tech.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tech.Interface.IUserService;
import com.tech.DTO.FriendDTO;
@CrossOrigin(origins = Host.apache)
@RestController
@RequestMapping("/friendservice")
public class FriendController extends BaseController{

@Autowired
 private IFriendService friendService;

@Autowired
 private IUserService userService;


@RequestMapping(value = "/addfriend", method = RequestMethod.POST)
public HttpEntity<String> addFriend(FriendDTO friendDTO){
    Pair<Boolean, ResponseEntity> response = friendDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(friendDTO.getFriendname())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Friend friend = new Friend(userService.getUserByUsername(friendDTO.getUsername()).getId(), userService.getUserByUsername(friendDTO.getFriendname()).getId());
    if (friendService.checkFriendIfExists(friend.getUserid(), friend.getFriendid())) {
        return new ResponseEntity<>(Responses.FRIEND_ALREADY_EXIST.getData(), HttpStatus.FOUND);
    }
    friendService.addFriend(friend);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/deletefriend", method = RequestMethod.POST)
public HttpEntity<String> deleteFriend(FriendDTO friendDTO){
    Pair<Boolean, ResponseEntity> response = friendDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(friendDTO.getFriendname())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Friend friend = new Friend(userService.getUserByUsername(friendDTO.getUsername()).getId(), userService.getUserByUsername(friendDTO.getFriendname()).getId());
    if (!friendService.checkFriendIfExists(friend.getUserid(), friend.getFriendid())) {
        return new ResponseEntity<>(Responses.FRIEND_DOES_NOT_EXIST.getData(), HttpStatus.NOT_FOUND);
    }
    friendService.deleteFriend(friend);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


}