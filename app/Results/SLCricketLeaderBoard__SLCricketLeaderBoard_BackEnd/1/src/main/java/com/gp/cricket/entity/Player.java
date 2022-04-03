package com.gp.cricket.entity;
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
@Entity
@Table(name = "player")
public class Player {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "player_id")
 private  Integer playerId;

@NotBlank
 private  Integer specialType;

@Transient
 private  User userId;

@Transient
 private  BallerType ballerTypeId;

@Transient
 private  BatmanType batmanTypeId;

@Transient
 private  Club clubId;

@Column(name = "userIdv2")
 private Integer userIdv2;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

@Column(name = "ballerTypeIdv2")
 private Integer ballerTypeIdv2;

@Transient
 private BallerTypeRequest ballertyperequest = new BallerTypeRequestImpl();;

@Column(name = "batmanTypeIdv2")
 private Integer batmanTypeIdv2;

@Transient
 private BatmanTypeRequest batmantyperequest = new BatmanTypeRequestImpl();;

@Column(name = "clubIdv2")
 private Integer clubIdv2;

@Transient
 private ClubRequest clubrequest = new ClubRequestImpl();;

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
public void setPlayerId(Integer playerId){
    this.playerId = playerId;
}


public Club getClubId(){
  this.clubId = clubrequest.getClubId(this.clubIdv2);
return this.clubId;
}


public void setSpecialType(Integer specialType){
    this.specialType = specialType;
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


public void setBallerTypeId(BallerType ballerTypeId){
 ballertyperequest.setBallerTypeId(ballerTypeId,this.ballerTypeIdv2);
}



public void setClubId(Club clubId){
 clubrequest.setClubId(clubId,this.clubIdv2);
}



@Override
public String toString(){
    return "Player [playerId=" + playerId + ", specialType=" + specialType + ", userId=" + userId + ", ballerTypeId=" + ballerTypeId + ", batmanTypeId=" + batmanTypeId + ", clubId=" + clubId + "]";
}


public User getUserId(){
  this.userId = userrequest.getUserId(this.userIdv2);
return this.userId;
}


public void setUserId(User userId){
 userrequest.setUserId(userId,this.userIdv2);
}



public void setBatmanTypeId(BatmanType batmanTypeId){
 batmantyperequest.setBatmanTypeId(batmanTypeId,this.batmanTypeIdv2);
}



}