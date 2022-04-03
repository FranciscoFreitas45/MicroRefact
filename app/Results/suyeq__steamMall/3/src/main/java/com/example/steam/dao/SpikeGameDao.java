package com.example.steam.dao;
 import com.example.steam.entity.SpikeGame;
import org.apache.ibatis.annotations;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
@Repository
public interface SpikeGameDao {


@Select("select * from spikegame where id=#{id}")
public SpikeGame findOneById(long id)
;

@Select("select * from spikegame")
public List<SpikeGame> findAllSpikeGame()
;

@Insert("insert into spikegame(gameid,postergame,spikeprice,stockcount,starttime," + "endtime,gameprice) value(#{gameId},#{posterGame},#{spikePrice},#{stockCount}," + "#{startTime},#{endTime},#{gamePrice})")
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
public long addSpikeGame(SpikeGame spikeGame)
;

@Select("select * from spikegame where gameid=#{gameid}")
public SpikeGame findOneByGameId(long gameId)
;

@Delete("delete from spikegame where id=#{spikeId}")
public int deleteSpikeGame(long spikeId)
;

@Update("update spikegame set gameid=#{gameId},postergame=#{posterGame}," + "spikeprice=#{spikePrice},stockcount=#{stockCount},starttime=#{startTime}," + "endtime=#{endTime},gameprice=#{gamePrice} where id=#{id}")
public int updateOneSpikeGame(SpikeGame spikeGame)
;

}