package org.live.dictionary.repository;
 import org.live.common.base.BaseRepository;
import org.live.dictionary.entity.Dictionary;
import org.live.dictionary.vo.DictionaryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
public interface DictRepository extends BaseRepository<Dictionary, String>{


public Page<DictionaryVo> find(PageRequest pageRequest,Map<String,Object> filter)
;

@Query("from Dictionary d where d.dictType.id = :dictTypeId")
public List<Dictionary> findByDictTypeId(String dictTypeId)
;

}