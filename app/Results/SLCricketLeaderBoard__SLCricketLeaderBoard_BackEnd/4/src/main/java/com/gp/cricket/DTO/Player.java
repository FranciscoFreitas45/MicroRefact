package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.gp.cricket.Request.UserRequest;
import com.gp.cricket.Request.Impl.UserRequestImpl;
import com.gp.cricket.DTO.User;
import com.gp.cricket.Request.BallerTypeRequest;
import com.gp.cricket.Request.Impl.BallerTypeRequestImpl;
import com.gp.cricket.DTO.BallerType;
import com.gp.cricket.Request.BatmanTypeRequest;
import com.gp.cricket.Request.Impl.BatmanTypeRequestImpl;
import com.gp.cricket.DTO.BatmanType;
import com.gp.cricket.Request.ClubRequest;
import com.gp.cricket.Request.Impl.ClubRequestImpl;
import com.gp.cricket.DTO.Club;
public class Player {

 private  Integer playerId;

 private  Integer specialType;

 private  User userId;

 private  BallerType ballerTypeId;

 private  BatmanType batmanTypeId;

 private  Club clubId;

 private Integer userIdv2;

 private Integer ballerTypeIdv2;

 private Integer batmanTypeIdv2;

 private Integer clubIdv2;

public Player() {
}public Player(Integer playerId, @NotNull Integer specialType, @NotNull User userId, @NotNull BallerType ballerTypeId, @NotNull BatmanType batmanTypeId, @NotNull Club clubId) {
    super();
    this.playerId = playerId;
    this.specialType = specialType;
    this.userId = userId;
    this.ballerTypeId = ballerTypeId;
    this.batmanTypeId = batmanTypeId;
    this.clubId = clubId;
}
public Club getClubId(){
  this.clubId = clubrequest.getClubId(this.clubIdv2);
return this.clubId;
}


public BatmanType getBatmanTypeId(){
  this.batmanTypeId = batmantyperequest.getBatmanTypeId(this.batmanTypeIdv2);
return this.batmanTypeId;
}


public Integer getSpecialType(){
    return specialType;
}


public Integer getPlayerId(){
    return playerId;
}


public BallerType getBallerTypeId(){
  this.ballerTypeId = ballertyperequest.getBallerTypeId(this.ballerTypeIdv2);
return this.ballerTypeId;
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


}