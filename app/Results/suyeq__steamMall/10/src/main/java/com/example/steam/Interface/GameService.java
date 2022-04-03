package com.example.steam.Interface;
public interface GameService {

   public GameDetail findGameById(long id);
   public List<Game> findAllGame();
}