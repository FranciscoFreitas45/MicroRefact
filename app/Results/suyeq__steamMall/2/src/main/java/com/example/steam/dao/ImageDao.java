package com.example.steam.dao;
 import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import org.apache.ibatis.annotations;
import org.springframework.stereotype.Repository;
@Repository
public interface ImageDao {


@Select("select url from image where id=#{id}")
public String findImageUrlById(long id)
;

@Select("select * from game_image where gameid=#{gameId}")
public GameImage findImagesByGameId(long gameId)
;

@Insert("insert into image(url,gamename,type) value(#{url},#{gameName},#{type})")
@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
public long addImage(Image image)
;

@Update("update game_image set image1=#{image1},image2=#{image2},image3=#{image3}," + "image4=#{image4},image5=#{image5} where gameid=#{gameId}")
public long updateImageToGame(GameImage gameImage)
;

@Select("select * from image where id=#{id}")
public Image findImageById(long id)
;

@Insert("insert into game_image(gameid,image1,image2,image3,image4,image5) value(" + "#{gameId},#{image1},#{image2},#{image3},#{image4},#{image5})")
public long addImageToGame(GameImage gameImage)
;

}