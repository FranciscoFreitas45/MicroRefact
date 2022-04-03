package com.example.steam.dao;
 import com.example.steam.entity.SystemNeed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Repository
public interface SystemNeedDao {


@Insert("insert into systemneed(operatingsystem,cpu,ram,graphicscard,directx,network," + "rom,soundcard) value(#{operatingSystem},#{cpu},#{ram},#{graphicsCard}," + "#{directx},#{network},#{rom},#{soundCard})")
@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
public long addSystemNeed(SystemNeed systemNeed)
;

@Select("select * from systemneed where id=#{id}")
public SystemNeed findSystemNeedById(long id)
;

}