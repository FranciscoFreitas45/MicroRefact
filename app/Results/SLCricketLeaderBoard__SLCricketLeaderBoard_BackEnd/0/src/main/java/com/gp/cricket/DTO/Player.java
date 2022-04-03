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
public class Player {

 private  Integer playerId;

 private  Integer specialType;

 private  User userId;

 private  BallerType ballerTypeId;

 private  BatmanType batmanTypeId;

 private  Club clubId;

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
    return clubId;
}


public BatmanType getBatmanTypeId(){
    return batmanTypeId;
}


public Integer getSpecialType(){
    return specialType;
}


public Integer getPlayerId(){
    return playerId;
}


public BallerType getBallerTypeId(){
    return ballerTypeId;
}


public User getUserId(){
    return userId;
}


}