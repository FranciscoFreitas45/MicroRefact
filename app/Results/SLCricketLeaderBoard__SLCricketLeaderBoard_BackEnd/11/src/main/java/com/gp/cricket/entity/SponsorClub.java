package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.sun.istack.NotNull;
import com.gp.cricket.Request.ClubRequest;
import com.gp.cricket.Request.Impl.ClubRequestImpl;
import com.gp.cricket.DTO.Club;
@Entity
public class SponsorClub {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "sponsor_club_id")
 private  Integer sponsorClubId;

@Column(name = "status")
 private  Integer status;

@NotNull
@ManyToOne
@JoinColumn(name = "sponsor_id", referencedColumnName = "sponsor_id")
 private  Sponsor sponsorId;

@Transient
 private  Club clubId;

@Column(name = "clubIdv2")
 private Integer clubIdv2;

@Transient
 private ClubRequest clubrequest = new ClubRequestImpl();;

public SponsorClub() {
    super();
// TODO Auto-generated constructor stub
}public SponsorClub(Integer sponsorClubId, Integer status, Sponsor sponsorId, Club clubId) {
    super();
    this.sponsorClubId = sponsorClubId;
    this.status = status;
    this.sponsorId = sponsorId;
    this.clubId = clubId;
}
public Integer getSponsorClubId(){
    return sponsorClubId;
}


public void setSponsorId(Sponsor sponsorId){
    this.sponsorId = sponsorId;
}


public void setClubId(Club clubId){
 clubrequest.setClubId(clubId,this.clubIdv2);
}



public Club getClubId(){
  this.clubId = clubrequest.getClubId(this.clubIdv2);
return this.clubId;
}


public Integer getStatus(){
    return status;
}


public void setStatus(Integer status){
    this.status = status;
}


public void setSponsorClubId(Integer sponsorClubId){
    this.sponsorClubId = sponsorClubId;
}


public Sponsor getSponsorId(){
    return sponsorId;
}


}