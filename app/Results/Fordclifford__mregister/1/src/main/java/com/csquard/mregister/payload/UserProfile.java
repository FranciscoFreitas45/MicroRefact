package com.csquard.mregister.payload;
 import java.time.Instant;
public class UserProfile {

 private  Long id;

 private  String username;

 private  String name;

 private  Long agentCount;

 private  Instant joinedAt;

public UserProfile(Long id, String username, String name, Instant joinedAt, Long agentCount) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.joinedAt = joinedAt;
    this.agentCount = agentCount;
}
public void setName(String name){
    this.name = name;
}


public void setAgentCount(Long agentCount){
    this.agentCount = agentCount;
}


public String getName(){
    return name;
}


public void setJoinedAt(Instant joinedAt){
    this.joinedAt = joinedAt;
}


public void setUsername(String username){
    this.username = username;
}


public Instant getJoinedAt(){
    return joinedAt;
}


public Long getAgentCount(){
    return agentCount;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


public String getUsername(){
    return username;
}


}