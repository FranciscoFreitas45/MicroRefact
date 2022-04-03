package cn.gson.oasys.model.entity.discuss;
 import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "aoa_voteTitles")
public class VoteTitles {

@Id
@Column(name = "title_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long titleId;

 private  String title;

 private  String color;

@OneToMany(mappedBy = "voteTitles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 private  Set<VoteTitleUser> voteTitleUsers;

@ManyToOne
@JoinColumn(name = "vote_id")
 private  VoteList voteList;

public VoteTitles() {
    super();
}
public VoteList getVoteList(){
    return voteList;
}


public void setTitleId(Long titleId){
    this.titleId = titleId;
}


public String getTitle(){
    return title;
}


public void setColor(String color){
    this.color = color;
}


public void setTitle(String title){
    this.title = title;
}


public String getColor(){
    return color;
}


@Override
public String toString(){
    return "VoteTitles [titleId=" + titleId + ", title=" + title + ", color=" + color + "]";
}


public void setVoteList(VoteList voteList){
    this.voteList = voteList;
}


public Long getTitleId(){
    return titleId;
}


}