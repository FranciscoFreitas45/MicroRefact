package com.example.steam.dao;
 import com.example.steam.entity.UserGame;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
@Repository
public interface UserGameDao {


@Insert("insert into user_game(email,gameid,playtime,lastplay) value(#{email},#{gameId},0,NOW())")
public int addGameToUser(UserGame userGame)
;

@Select("select * from user_game where email=#{email} and gameid=#{gameId}")
public UserGame findGameByEmailAndGameId(String email,long gameId)
;

@Select("select * from user_game where email=#{email}")
public List<UserGame> findGamesByEmail(String email)
;

@Select("select * from user_game where email=#{email} order by lastplay desc")
public List<UserGame> findGamesByEmailOrderByLastPlayDesc(String email)
;

}