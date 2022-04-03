package com.tech.controllers;
 import com.tech.configurations.tools.Pair;
import com.tech.configurations.tools.Responses;
import com.tech.controllers.superclass.BaseController;
import com.tech.models.dtos.chatroom.ChatroomBlacklistDTO;
import com.tech.models.dtos.chatroom.ChatroomCheckInsideDTO;
import com.tech.models.dtos.chatroom.ChatroomConnectionMemberDTO;
import com.tech.models.dtos.chatroom.ChatroomCreationDTO;
import com.tech.models.dtos.chatroom.ChatroomDeleteDTO;
import com.tech.models.dtos.chatroom.ChatroomLocationDTO;
import com.tech.models.dtos.chatroom.ChatroomLocationUpdateDTO;
import com.tech.models.dtos.chatroom.ChatroomMemberDTO;
import com.tech.models.dtos.chatroom.ChatroomQuitMemberDTO;
import com.tech.models.dtos.chatroom.ChatroomUpdateDTO;
import com.tech.models.dtos.chatroom.ChatroomWhitelistDTO;
import com.tech.models.entities.chatroom.ChatroomBlacklist;
import com.tech.models.entities.chatroom.ChatroomEntities;
import com.tech.models.entities.chatroom.ChatroomLocation;
import com.tech.models.entities.chatroom.ChatroomMembers;
import com.tech.models.entities.chatroom.ChatroomPrivileges;
import com.tech.models.entities.chatroom.ChatroomWhitelist;
import com.tech.services.interfaces.IChatroomBlacklistService;
import com.tech.services.interfaces.IChatroomEntitiesService;
import com.tech.services.interfaces.ICRLocationService;
import com.tech.services.interfaces.IChatroomMembersService;
import com.tech.services.interfaces.IChatroomPrivilegesService;
import com.tech.services.interfaces.IChatroomWhitelistService;
import com.tech.services.interfaces.IUserService;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tech.Interface.IUserService;
import com.tech.Interface.IChatroomEntitiesService;
import com.tech.Interface.IChatroomBlacklistService;
import com.tech.Interface.IChatroomWhitelistService;
import com.tech.Interface.IChatroomPrivilegesService;
import com.tech.Interface.IChatroomMembersService;
import com.tech.Interface.ICRLocationService;
import com.tech.DTO.ChatroomEntities;
import com.tech.DTO.ChatroomPrivileges;
import com.tech.DTO.ChatroomBlacklist;
@RestController
@RequestMapping("/chatroom")
public class ChatroomController extends BaseController{

@Autowired
 private IUserService userService;

@Autowired
 private IChatroomEntitiesService chatroomEntitesService;

@Autowired
 private IChatroomBlacklistService chatroomBlacklistService;

@Autowired
 private IChatroomWhitelistService chatroomWhitelistService;

@Autowired
 private IChatroomPrivilegesService chatroomPrivilegesService;

@Autowired
 private IChatroomMembersService chatroomMembersService;

@Autowired
 private ICRLocationService chatroomLocationService;


@RequestMapping(value = "/newChatroom", method = RequestMethod.POST)
public HttpEntity<String> handleNewChatroom(ChatroomCreationDTO newChatroom){
    Pair<Boolean, ResponseEntity> response = newChatroom.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(newChatroom.getUsername())) {
        // validates if the user exists or not
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (chatroomEntitesService.validateRoomnameExistance(newChatroom.getRoom_name())) {
        // validates if the the name already exists or not
        // CONFLICT 8a eprepe na exei edw
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.FOUND);
    }
    Long userid = userService.getUserByUsername(newChatroom.getUsername()).getId();
    if (userService.getUserById(userid).hasRoom()) {
        // if he has room he cant make more
        return new ResponseEntity<>(Responses.ALREADY_HAS_A_ROOM.getData(), HttpStatus.FOUND);
    }
    userService.updateUserRoom(true, userid);
    ChatroomEntities CE = new ChatroomEntities(chatroomEntitesService.getNextID(), userid, newChatroom);
    ChatroomPrivileges CP = new ChatroomPrivileges(CE.getRoom_id(), newChatroom);
    ChatroomMembers CM = new ChatroomMembers(CE.getRoom_id(), userid);
    ChatroomLocation CL = new ChatroomLocation(CE.getRoom_id(), newChatroom);
    chatroomEntitesService.add(CE);
    chatroomMembersService.add(CM);
    chatroomPrivilegesService.add(CP);
    chatroomLocationService.add(CL);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/removeMember", method = RequestMethod.POST)
public HttpEntity<String> removeMember(ChatroomMemberDTO memberDTO){
    Pair<Boolean, ResponseEntity> response = memberDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!memberDTO.getMethod().equals("DELETE")) {
        return new ResponseEntity<>(Responses.ACCESS_METHOD_NOT_FOUND.getData(), HttpStatus.BAD_REQUEST);
    }
    if (!userService.checkUsername(memberDTO.getMember_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (chatroomEntitesService.getRoomByName(memberDTO.getRoom_name()) == null) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomId = chatroomEntitesService.getRoomByName(memberDTO.getRoom_name()).getRoom_id();
    Long userID = userService.getUserByUsername(memberDTO.getMember_name()).getId();
    ChatroomPrivileges CP = chatroomPrivilegesService.findByRoomId(roomId);
    if (!chatroomMembersService.checkIfMemberExistsInChatroom(userID, roomId)) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    ChatroomMembers CM = new ChatroomMembers(roomId, userID);
    if (CP.isRoom_password_protected()) {
        if (!CP.getRoom_password().equals(memberDTO.getPassword())) {
            return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
        }
    }
    chatroomMembersService.delete(CM);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/checkIfStillInside", method = RequestMethod.POST)
public HttpEntity<String> checkIfStillInside(ChatroomCheckInsideDTO myLocation){
    Pair<Boolean, ResponseEntity> response = myLocation.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(myLocation.getUser_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (!chatroomEntitesService.validateRoomnameExistance(myLocation.getRoom_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long userID = userService.getUserByUsername(myLocation.getUser_name()).getId();
    Long roomID = chatroomEntitesService.getRoomByName(myLocation.getRoom_name()).getRoom_id();
    if (!chatroomLocationService.checkIfStillInside(roomID, myLocation.getLng(), myLocation.getLat())) {
        if (chatroomMembersService.checkIfMemberExistsInChatroom(userID, roomID)) {
            ChatroomMembers CM = new ChatroomMembers(roomID, userID);
            chatroomMembersService.delete(CM);
        }
        return new ResponseEntity<>(Responses.OUTSIDE_RANGE.getData(), HttpStatus.GONE);
    } else {
        if (!chatroomMembersService.checkIfMemberExistsInChatroom(userID, roomID)) {
            return new ResponseEntity<>(Responses.NOT_CONNECTED_TO_THE_ROOM.getData(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
    }
}


@RequestMapping(value = "/deleteChatroom", method = RequestMethod.POST)
public HttpEntity<String> deleteChatroom(ChatroomDeleteDTO deleteRoom){
    Pair<Boolean, ResponseEntity> response = deleteRoom.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!chatroomEntitesService.validateRoomnameExistance(deleteRoom.getRoom_name())) {
        return new ResponseEntity<>(Responses.ROOM_NOT_FOUND.getData(), HttpStatus.NOT_FOUND);
    }
    ChatroomEntities CE = chatroomEntitesService.getRoomByName(deleteRoom.getRoom_name());
    Long creator_id = userService.getUserByUsername(deleteRoom.getCreator_name()).getId();
    if (!userService.getUserById(creator_id).hasRoom()) {
        return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
    }
    if (!Objects.equals(CE.getRoom_creator(), creator_id)) {
        // <- TODO  Verify
        return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
    }
    ChatroomPrivileges CP = chatroomPrivilegesService.findByRoomId(CE.getRoom_id());
    if (CP.isRoom_password_protected()) {
        if (!CP.getRoom_password().equals(deleteRoom.getRoom_password())) {
            return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
        }
    }
    chatroomEntitesService.delete(CE);
    userService.updateUserRoom(false, creator_id);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/banFromChatroom", method = RequestMethod.POST)
public HttpEntity<String> handleBans(ChatroomBlacklistDTO banDTO){
    Pair<Boolean, ResponseEntity> response = banDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(banDTO.getMember_name())) {
        // if user doesn't exist
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (!chatroomEntitesService.validateRoomnameExistance(banDTO.getRoom_name())) {
        return new ResponseEntity<>(Responses.ROOM_NOT_FOUND.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomID = chatroomEntitesService.getRoomByName(banDTO.getRoom_name()).getRoom_id();
    Long userID = userService.getUserByUsername(banDTO.getMember_name()).getId();
    ChatroomBlacklist CB = chatroomBlacklistService.findByRoomIDAndRoomMember(roomID, userID);
    if (CB != null) {
        if (banDTO.getExpiration_date().before(new Date())) {
            // if CB is before than Today
            chatroomBlacklistService.delete(CB);
            return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
        } else {
            CB.setRoom_expiration_time(banDTO.getExpiration_date());
            chatroomBlacklistService.setNewTime(roomID, userID, banDTO.getExpiration_date());
            return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
        }
    }
    CB = new ChatroomBlacklist(roomID, userID, banDTO);
    chatroomBlacklistService.add(CB);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/updateChatroomLocation", method = RequestMethod.POST)
public HttpEntity<String> updateLocation(ChatroomLocationUpdateDTO roomLocation){
    Pair<Boolean, ResponseEntity> response = roomLocation.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!chatroomEntitesService.validateRoomnameExistance(roomLocation.getRoom_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomID = chatroomEntitesService.getRoomByName(roomLocation.getRoom_name()).getRoom_id();
    chatroomLocationService.setNewLngLat(roomLocation.getLng(), roomLocation.getLat(), roomID);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/updateChatroom", method = RequestMethod.POST)
public HttpEntity<String> updateChatroom(ChatroomUpdateDTO updateDTO){
    Pair<Boolean, ResponseEntity> response = updateDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!chatroomEntitesService.validateRoomnameExistance(updateDTO.getRoom_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomID = chatroomEntitesService.getRoomByName(updateDTO.getRoom_name()).getRoom_id();
    chatroomEntitesService.setChatroomEntities(updateDTO.getNew_room_name(), roomID);
    chatroomPrivilegesService.setChatroomPrivileges(updateDTO.getRoom_privilege(), updateDTO.isPasswordProtection(), updateDTO.getPassword(), updateDTO.getAccess_method(), roomID);
    chatroomLocationService.setNewMaxRange(updateDTO.getMax_range(), roomID);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/quitChatroom", method = RequestMethod.POST)
public HttpEntity<String> quitChatroom(ChatroomQuitMemberDTO quitMember){
    Pair<Boolean, ResponseEntity> response = quitMember.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!chatroomEntitesService.validateRoomnameExistance(quitMember.getRoom_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (!userService.checkUsername(quitMember.getUser_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    ChatroomEntities CE = chatroomEntitesService.getRoomByName(quitMember.getRoom_name());
    Long roomID = CE.getRoom_id();
    Long userID = userService.getUserByUsername(quitMember.getUser_name()).getId();
    if (!chatroomMembersService.checkIfMemberExistsInChatroom(userID, roomID)) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    /* If Admin quits. QQ room*/
    if (Objects.equals(CE.getRoom_creator(), userID)) {
        chatroomEntitesService.delete(CE);
        return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
    }
    ChatroomMembers CM = new ChatroomMembers(roomID, userID);
    chatroomMembersService.delete(CM);
    return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
}


@RequestMapping(value = "/findAvailableChatrooms", method = RequestMethod.POST)
public HttpEntity<JSONObject> availableChatrooms(ChatroomLocationDTO myLocation){
    Pair<Boolean, ResponseEntity> response = myLocation.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    JSONArray ajson = new JSONArray();
    JSONObject json = new JSONObject();
    List<ChatroomLocation> CL = chatroomLocationService.findIfNear(myLocation.getLng(), myLocation.getLat());
    if (CL.size() <= 0) {
        json.put("size", 0);
        json.put("list", ajson);
        json.put("error", "no errors");
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    int i = 0;
    for (ChatroomLocation vLookUp : CL) {
        i++;
        json.put("chatroom_" + i, chatroomEntitesService.findByRoomID(vLookUp.getRoom_id()).getRoom_name());
    }
    json.put("size", i);
    json.put("error", "no errors");
    return new ResponseEntity<>(json, HttpStatus.OK);
}


@RequestMapping(value = "/connectChatroom", method = RequestMethod.POST)
public HttpEntity<String> connectToChatroom(ChatroomConnectionMemberDTO newMember){
    Pair<Boolean, ResponseEntity> response = newMember.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!newMember.getMode().equals("ADD")) {
        return new ResponseEntity<>(Responses.ACCESS_METHOD_NOT_FOUND.getData(), HttpStatus.BAD_REQUEST);
    }
    if (!userService.checkUsername(newMember.getMember_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (!chatroomEntitesService.validateRoomnameExistance(newMember.getRoom_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomId = chatroomEntitesService.getRoomByName(newMember.getRoom_name()).getRoom_id();
    Long userID = userService.getUserByUsername(newMember.getMember_name()).getId();
    if (!chatroomLocationService.checkIfStillInside(roomId, newMember.getLng(), newMember.getLat())) {
        return new ResponseEntity<>(Responses.OUTSIDE_RANGE.getData(), HttpStatus.GONE);
    }
    ChatroomPrivileges CP = chatroomPrivilegesService.findByRoomId(roomId);
    if (CP.isRoom_password_protected()) {
        if (!CP.getRoom_password().equals(newMember.getPassword())) {
            return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
        }
    }
    switch(CP.getRoom_access_method()) {
        case "blacklist":
            ChatroomBlacklist CB = chatroomBlacklistService.findByRoomIDAndRoomMember(roomId, userID);
            if (CB != null) {
                if (CB.getRoom_expiration_time().after(new Date())) {
                    // if CB is later than Today
                    return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
                }
                chatroomBlacklistService.delete(CB);
                chatroomMembersService.add(new ChatroomMembers(roomId, userID));
                return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
            } else {
                chatroomMembersService.add(new ChatroomMembers(roomId, userID));
                return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
            }
        case "whitelist":
            ChatroomWhitelist CW = chatroomWhitelistService.findByRoomIDAndRoomMember(roomId, userID);
            if (CW != null) {
                chatroomMembersService.add(new ChatroomMembers(roomId, userID));
                return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(Responses.NOT_AUTHORIZED.getData(), HttpStatus.UNAUTHORIZED);
            }
        default:
            return new ResponseEntity<>(Responses.ACCESS_METHOD_NOT_FOUND.getData(), HttpStatus.BAD_REQUEST);
    }
}


@RequestMapping(value = "/handleWhitelist", method = RequestMethod.POST)
public HttpEntity<String> handleWhitelist(ChatroomWhitelistDTO whiteDTO){
    Pair<Boolean, ResponseEntity> response = whiteDTO.validate();
    if (!response.getLeft()) {
        return response.getRight();
    }
    if (!userService.checkUsername(whiteDTO.getMember_name())) {
        return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
    }
    if (!chatroomEntitesService.validateRoomnameExistance(whiteDTO.getRoom_name())) {
        return new ResponseEntity<>(Responses.ROOM_NOT_FOUND.getData(), HttpStatus.NOT_FOUND);
    }
    Long roomID = chatroomEntitesService.getRoomByName(whiteDTO.getRoom_name()).getRoom_id();
    Long userID = userService.getUserByUsername(whiteDTO.getMember_name()).getId();
    ChatroomWhitelist CW;
    switch(whiteDTO.getMode()) {
        case "ADD":
            CW = new ChatroomWhitelist(roomID, userID);
            if (chatroomWhitelistService.findByRoomIDAndRoomMember(roomID, userID) != null) {
                return new ResponseEntity<>(Responses.ALREADY_EXISTS.getData(), HttpStatus.FOUND);
            }
            chatroomWhitelistService.add(CW);
            return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
        case "DELETE":
            CW = chatroomWhitelistService.findByRoomIDAndRoomMember(roomID, userID);
            if (CW != null) {
                chatroomWhitelistService.delete(CW);
                return new ResponseEntity<>(Responses.SUCCESS.getData(), HttpStatus.OK);
            }
            return new ResponseEntity<>(Responses.NOT_AVAILABLE.getData(), HttpStatus.NOT_FOUND);
        default:
            return new ResponseEntity<>(Responses.ACCESS_METHOD_NOT_FOUND.getData(), HttpStatus.BAD_REQUEST);
    }
}


}