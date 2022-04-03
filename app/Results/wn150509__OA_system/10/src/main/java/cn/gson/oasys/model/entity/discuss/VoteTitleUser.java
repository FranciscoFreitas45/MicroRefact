package cn.gson.oasys.model.entity.discuss;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import cn.gson.oasys.model.entity.user.User;
import cn.gson.oasys.Request.UserRequest;
import cn.gson.oasys.Request.Impl.UserRequestImpl;
import cn.gson.oasys.DTO.User;
@Entity
@Table(name = "aoa_vote_title_user")
public class VoteTitleUser {

@Id
@Column(name = "vote_title_user_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long voteTitleUserId;

// 投票id
@Column(name = "vote_id")
 private  Long voteId;

@ManyToOne
@JoinColumn(name = "title_id")
 private  VoteTitles voteTitles;

@Transient
 private  User user;

@Column(name = "userId")
 private Long userId;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public VoteTitleUser() {
    super();
// TODO Auto-generated constructor stub
}public VoteTitleUser(Long voteId, VoteTitles voteTitles, User user) {
    super();
    this.voteId = voteId;
    this.voteTitles = voteTitles;
    this.user = user;
}
public VoteTitles getVoteTitles(){
    return voteTitles;
}


public User getUser(){
  this.user = userrequest.getUser(this.userId);
return this.user;
}


public void setVoteTitleUserId(Long voteTitleUserId){
    this.voteTitleUserId = voteTitleUserId;
}


public void setVoteTitles(VoteTitles voteTitles){
    this.voteTitles = voteTitles;
}


@Override
public String toString(){
    return "VoteTitleUser [voteTitleUserId=" + voteTitleUserId + ", voteId=" + voteId + "]";
}


public Long getVoteTitleUserId(){
    return voteTitleUserId;
}


public void setUser(User user){
 userrequest.setUser(user,this.userId);
}



public void setVoteId(Long voteId){
    this.voteId = voteId;
}


public Long getVoteId(){
    return voteId;
}


}