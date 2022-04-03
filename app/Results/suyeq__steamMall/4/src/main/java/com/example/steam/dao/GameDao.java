package com.example.steam.dao;
 import com.example.steam.entity.Game;
import org.apache.ibatis.annotations;
import org.springframework.stereotype.Repository;
import javax.annotation.security.PermitAll;
import java.util.List;
@Repository
public interface GameDao {


@Select("select * from game where id=#{id}")
public Game findGameById(long id)
;

@Select("select * from game limit 0,12")
public List<Game> findGamesFeatured()
;

@Select("select * from game where discount>0 limit 0,12")
public List<Game> findSpecialGames()
;

@Select("select * from game where gamename like concat('%',#{content},'%') or gameintroduction like concat('%',#{content},'%')")
public List<Game> findGamesBySearchContent(String content)
;

@Select("select * from game where issuedstatu=0 order by issueddate desc limit 0,10")
public List<Game> findUpComingGameToIndex()
;

@Update("update game set gamename=#{gameName},gameabout=#{gameAbout},gameprice=#{gamePrice}," + "posterimage=#{posterImage},sellnum=#{sellNum},discount=#{discount},issuedstatu=#{issuedStatu}," + "issueddate=#{issuedDate} where id=#{id}")
public int updateGame(Game game)
;

@Select("select count(*) from game")
public int gamesSum()
;

@Select("select * from game where issuedstatu>0 order by issueddate desc limit 0,10")
public List<Game> findNewReleaseGameToIndex()
;

@Select("select * from game where issuedstatu>0 order by sellnum desc limit 0,10")
public List<Game> findHotSellGameToIndex()
;

@Select("select * from game")
public List<Game> findAllGame()
;

@Insert("insert into game(gamename,gameintroduction,gameabout,issuedstatu,gameprice,issueddate," + "posterImage,lowestsystem,recommendsystem,sellnum,discount) value(#{gameName},#{gameIntroduction},#{gameAbout}," + "0,#{gamePrice},#{issuedDate},1,#{lowestSystem},#{recommendSystem},0,#{discount})")
@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
public int addGame(Game game)
;

@Select("select MAX(id) FROM game")
public int findMaxId()
;

@Delete("delete from game where id=#{id}")
public int deleteGame(long id)
;

}