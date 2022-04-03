package cn.gson.oasys.model.entity.discuss;
 import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_voteList")
public class VoteList {

@Id
@Column(name = "vote_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long voteId;

// 投票开始时间
@Column(name = "start_time")
 private  Date startTime;

// 投票结束时间
@Column(name = "end_time")
 private  Date endTime;

 private  Integer selectone;

@OneToOne(mappedBy = "voteList")
 private  Discuss discuss;

@OneToMany(mappedBy = "voteList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 private  Set<VoteTitles> voteTitles;

public VoteList() {
    super();
}
public Set<VoteTitles> getVoteTitles(){
    return voteTitles;
}


public void setDiscuss(Discuss discuss){
    this.discuss = discuss;
}


public Discuss getDiscuss(){
    return discuss;
}


public Integer getSelectone(){
    return selectone;
}


public void setSelectone(Integer selectone){
    this.selectone = selectone;
}


public Date getEndTime(){
    return endTime;
}


public void setEndTime(Date endTime){
    this.endTime = endTime;
}


public void setVoteTitles(Set<VoteTitles> voteTitles){
    this.voteTitles = voteTitles;
}


@Override
public String toString(){
    return "VoteList [voteId=" + voteId + ", startTime=" + startTime + ", endTime=" + endTime + ", selectone=" + selectone + "]";
}


public Date getStartTime(){
    return startTime;
}


public void setStartTime(Date startTime){
    this.startTime = startTime;
}


public void setVoteId(Long voteId){
    this.voteId = voteId;
}


public Long getVoteId(){
    return voteId;
}


}