package com.example.steam.dao;
 import com.example.steam.entity.User;
import org.apache.ibatis.annotations;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserDao {


@Select("select * from user")
public List<User> findAllUser()
;

@Insert("insert into user(nickname,salt,email,password,avatar,playtime,commentnum,buygames,isadmin,introduction,province) value(#{nickName},#{salt},#{email},#{password}" + ",#{avatar},#{playTime},#{commentNum},#{buyGames},#{isAdmin},#{introduction},#{province})")
public int addUser(User user)
;

@Delete("delete from user where email=#{email}")
public int deleteUser(String email)
;

@Update("update user set nickname=#{nickName},salt=#{salt},email=#{email},password=#{password}," + "playtime=#{playTime},commentnum=#{commentNum},buygames=#{buyGames},isadmin=#{isAdmin}, " + "avatar=#{avatar},country=#{country},province=#{province},introduction=#{introduction} where id=#{id}")
public int updateUser(User user)
;

@Select("select * from user where email=#{email}")
public User findUserByEmail(String email)
;

}