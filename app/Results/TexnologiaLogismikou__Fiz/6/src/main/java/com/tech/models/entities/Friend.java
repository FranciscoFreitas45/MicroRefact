package com.tech.models.entities;
 import com.tech.models.entities.embeddedIds.FriendComposite;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@IdClass(FriendComposite.class)
@NamedQueries({ @NamedQuery(name = "Friend.findByUserid", query = "SELECT p FROM Friend p WHERE p.userid = ?1"), @NamedQuery(name = "Friend.findByDate", query = "SELECT p FROM Friend p WHERE p.tmstamp = ?1"), @NamedQuery(name = "Friend.findByUseridAndFriendid", query = "SELECT p FROM Friend p WHERE p.userid = ?1 and p.friendid=?2") })
// edw vazw oti query thelw
@Table(name = "friendlist")
public class Friend implements Serializable{

@Id
 private  Long userid;

@Id
 private  Long friendid;

@Column(name = "added_date")
 private  Date tmstamp;

public Friend() {
}public Friend(Long userid, Long friendid) {
    this.userid = userid;
    this.friendid = friendid;
    this.tmstamp = new Date();
}
public Date getTimestamp(){
    return tmstamp;
}


@JsonProperty
public Long getFriendid(){
    return friendid;
}


@JsonProperty
public Long getUserid(){
    return userid;
}


}