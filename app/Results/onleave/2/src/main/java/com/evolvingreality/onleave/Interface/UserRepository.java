package com.evolvingreality.onleave.Interface;
public interface UserRepository {

   public List<User> findAllByGroupMembers_SecurityGroupGroupNameIn(Collection<String> groupNames);
}