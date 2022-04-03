package com.example.steam.dao;
 import com.example.steam.entity.Comment;
import org.apache.ibatis.annotations;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface CommentDao {


@Select("select * from comment where email=#{email} order by commentdate desc limit #{start},#{end}")
public List<Comment> findComentsByEmailOrderByTimeDesc(long start,long end,String email)
;

@Select("select * from comment where id=#{id}")
public Comment findOneCommentById(long id)
;

@Delete("delete from comment where id=#{id}")
public int deleteComment(long id)
;

@Select("select count(*) from comment where email=#{email}")
public int commentSumByEmail(String email)
;

@Select("select max(id) from comment")
public long findLastCommentId()
;

@Select("select count(*) from comment")
public int commentSum()
;

@Insert("insert into comment(content,commentdate,email,gameid,zannum,cainum,recommendstatu)" + "value(#{content},NOW(),#{email},#{gameId},0,0,#{recommendStatu})")
@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
public long addComment(Comment comment)
;

@Update("update comment set zannum=#{zanNum},caiNum=#{caiNum},happy=#{happy},content=#{content} where id=#{id}")
public int updateComment(Comment comment)
;

}