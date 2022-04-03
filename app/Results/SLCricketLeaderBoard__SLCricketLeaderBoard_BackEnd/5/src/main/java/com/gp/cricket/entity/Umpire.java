package com.gp.cricket.entity;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
@Entity
public class Umpire {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "umpire_id")
 private  Integer umpireId;

@Column(name = "no_match")
 private  Integer noMatch;

@Column(name = "correct_decision")
 private  Integer correctDecision;

@Column(name = "wrong_decision")
 private  Integer wrongDecision;

@NotNull
@OneToOne
@JoinColumn(name = "user_id", referencedColumnName = "user_id")
 private  User userId;

public Umpire() {
    super();
}public Umpire(Integer umpireId, @NotNull User userId) {
    super();
    this.umpireId = umpireId;
    this.noMatch = 0;
    this.correctDecision = 0;
    this.wrongDecision = 0;
    this.userId = userId;
}
public Integer getNoMatch(){
    return noMatch;
}


public Integer getCorrectDecision(){
    return correctDecision;
}


public void setNoMatch(Integer noMatch){
    this.noMatch = noMatch;
}


public Integer getUmpireId(){
    return umpireId;
}


public void setCorrectDecision(Integer correctDecision){
    this.correctDecision = correctDecision;
}


public Integer getWrongDecision(){
    return wrongDecision;
}


public void setWrongDecision(Integer wrongDecision){
    this.wrongDecision = wrongDecision;
}


@Override
public String toString(){
    return "Umpire [umpireId=" + umpireId + ", userId=" + userId + "]";
}


public User getUserId(){
    return userId;
}


public void setUmpireId(Integer umpireId){
    this.umpireId = umpireId;
}


public void setUserId(User userId){
    this.userId = userId;
}


}