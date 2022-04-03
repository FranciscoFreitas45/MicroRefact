package com.example.steam.dao;
 import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import org.apache.ibatis.annotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface LabelDao {


@Select("select * from game_label where gameid=#{gameId} and labelid=#{labelId}")
public Label findLabelByLabelIdAndGameId(long gameId,long labelId)
;

@Update("update game_label set hotnum=hotnum+1 where gameid=#{gameId} and labelid=#{labelId}")
public int labelHotNumIncr(long gameId,long labelId)
;

@Insert("insert into label(name,hotnum) value(#{name},#{hotNum})")
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
public int addLabel(Label label)
;

@Select("select name from label where id=#{id}")
public String findLabelNameById(long id)
;

@Select("select * from label where id=#{labelId}")
public Label findLabelByLabelId(long labelId)
;

@Select("select * from label")
public List<Label> findAllLabel()
;

@Delete("delete from label where id=#{labelId}")
public int deleteLabelByLabelId(long labelId)
;

@Delete("delete from game_label where gameid=#{gameId}")
public int deleteGameLabelByGameId(long gameId)
;

@Select("select * from label where name =#{labelName}")
public Label findLabelByLableName(String labelName)
;

@Insert("insert into game_label(gameid,labelid,hotnum) value(#{gameId},#{labelId},#{hotNum})")
public int addLabelInGame(GameLabel gameLabel)
;

@Select("select * from game_label where gameid=#{gameId}")
public List<GameLabel> findLabelsByGameId(long gameId)
;

}