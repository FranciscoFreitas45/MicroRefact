package cn.gson.oasys.mappers;
 import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AddressMapper {


public List<Map<String,Object>> allDirector(Long userId,String pinyin,String outtype,String baseKey)
;

}