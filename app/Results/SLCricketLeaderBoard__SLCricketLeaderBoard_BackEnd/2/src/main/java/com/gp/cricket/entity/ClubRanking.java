package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.sun.istack.NotNull;
import com.gp.cricket.Request.ClubRequest;
import com.gp.cricket.Request.Impl.ClubRequestImpl;
import com.gp.cricket.DTO.Club;
@Entity
@Table(name = "club_ranking")
public class ClubRanking {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "club_ranking_id")
 private  Integer clubRankingId;

@NotNull
 private  Double points;

@NotNull
 private  Double rating;

@NotNull
 private  Integer numMatch;

@Transient
 private  Club clubId;

@NotNull
@ManyToOne
@JoinColumn(name = "match_type_id", referencedColumnName = "match_type_id")
 private  MatchType matchTypeId;

@Column(name = "clubIdv2")
 private Integer clubIdv2;

@Transient
 private ClubRequest clubrequest = new ClubRequestImpl();;

public ClubRanking() {
    super();
}public ClubRanking(Integer clubRankingId, Double points, Double rating, Integer numMatch, Club clubId, MatchType matchTypeId) {
    super();
    this.clubRankingId = clubRankingId;
    this.points = points;
    this.rating = rating;
    this.numMatch = numMatch;
    this.clubId = clubId;
    this.matchTypeId = matchTypeId;
}
public Club getClubId(){
  this.clubId = clubrequest.getClubId(this.clubIdv2);
return this.clubId;
}


public void setClubRankingId(Integer clubRankingId){
    this.clubRankingId = clubRankingId;
}


public Integer getNumMatch(){
    return numMatch;
}


public void setClubId(Club clubId){
 clubrequest.setClubId(clubId,this.clubIdv2);
}



public Integer getClubRankingId(){
    return clubRankingId;
}


public void setNumMatch(Integer numMatch){
    this.numMatch = numMatch;
}


public MatchType getMatchTypeId(){
    return matchTypeId;
}


public Double getPoints(){
    return points;
}


@Override
public String toString(){
    return "ClubRanking [clubRankingId=" + clubRankingId + ", points=" + points + ", rating=" + rating + ", numMatch=" + numMatch + ", clubId=" + clubId + ", matchTypeId=" + matchTypeId + "]";
}


public void setMatchTypeId(MatchType matchTypeId){
    this.matchTypeId = matchTypeId;
}


public void setPoints(Double points){
    this.points = points;
}


public void setRating(Double rating){
    this.rating = rating;
}


public Double getRating(){
    return rating;
}


}