package com.example.steam.dao;
 import com.example.steam.entity.GameType;
import com.example.steam.entity.Type;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface TypeDao {


@Select("select * from type")
public List<Type> findAllTypes()
;

@Insert("insert into type(typename) value(#{typeName})")
public int addType(Type type)
;

@Select("select typename from type where id=#{id}")
public String findTypeNameById(long id)
;

@Select("select * from type where id=#{id}")
public Type findTypeById(long id)
;

@Select("select typename from type")
public List<String> findAllType()
;

@Insert("insert into game_type(gameid,typeid) value(#{gameId},#{typeId})")
public int addTypeToGame(GameType gameType)
;

@Select("select * from type where typename=#{typeName}")
public Type findTypeByTypeName(String typeName)
;

@Delete("delete from game_type where gameid=#{gameId}")
public int deleteGameTypeByGameId(long gameId)
;

@Select("select * from game_type where gameid=#{gameId}")
public List<GameType> findTypesByGameId(long gameId)
;

@Delete("delete from type where id=#{typeId}")
public int deleteTypeById(long typeId)
;

}