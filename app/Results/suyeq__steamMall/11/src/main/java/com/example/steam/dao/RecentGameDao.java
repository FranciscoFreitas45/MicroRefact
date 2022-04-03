package com.example.steam.dao;
 import com.example.steam.entity.RecentGame;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RecentGameDao {


@Select("select * from recentgame where email=#{email} order by lastplay desc")
public List<RecentGame> findRecentGameListByEmail(String email)
;

}