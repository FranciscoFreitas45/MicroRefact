package com.example.steam.Interface;
public interface UserGameService {

   public UserGame findOneUserGameByEmailAndGameId(String email,long gameId);
}