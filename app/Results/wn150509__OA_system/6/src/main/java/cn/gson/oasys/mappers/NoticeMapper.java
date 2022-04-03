package cn.gson.oasys.mappers;
 import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface NoticeMapper {


public List<Map<String,Object>> sortMyNotice(Long userId,String baseKey,Integer type,Integer status,Integer time)
;

public List<Map<String,Object>> findMyNotice(Long userId)
;

public List<Map<String,Object>> findMyNoticeLimit(Long userId)
;

}