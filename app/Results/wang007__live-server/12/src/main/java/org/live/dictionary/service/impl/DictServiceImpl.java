package org.live.dictionary.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.dictionary.cache.CacheDict;
import org.live.dictionary.entity.DictType;
import org.live.dictionary.entity.Dictionary;
import org.live.dictionary.repository.DictTypeRepository;
import org.live.dictionary.vo.DictionaryVo;
import org.live.dictionary.repository.DictRepository;
import org.live.dictionary.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dictionary, String>implements DictService{

@Autowired
 private  DictRepository dictRepository;

@Autowired
 private  DictTypeRepository dictTypeRepository;


@Override
public String getDictKey(String dicVal){
    for (Map.Entry<String, String> entry : CacheDict.allDictMap.entrySet()) {
        if (dicVal.equals(entry.getValue())) {
            return entry.getKey();
        }
    }
    return null;
}


@Override
public Map<String,String> getDictMap(String dictTypeKey){
    return CacheDict.dictMap.get(dictTypeKey);
}


@Override
public void cacheDic(){
    List<DictType> dictTypes = dictTypeRepository.findAll();
    for (DictType dictType : dictTypes) {
        // 缓存所有字典类型
        CacheDict.allTypes.put(dictType.getDictTypeMark(), dictType);
        List<Dictionary> dictionaries = dictRepository.findByDictTypeId(dictType.getId());
        // 缓存所有字典类型及其对应的一组字典
        CacheDict.dictList.put(dictType.getDictTypeMark(), dictionaries);
        Map<String, String> map = new HashMap<String, String>();
        for (Dictionary dictionary : dictionaries) {
            map.put(dictionary.getDictMark(), dictionary.getDictValue());
        }
        // 缓存所有字典类型及其对应的一组字典键值对
        CacheDict.dictMap.put(dictType.getDictTypeMark(), map);
    }
    List<Dictionary> dictionaries = dictRepository.findAll();
    for (Dictionary dictionary : dictionaries) {
        // 缓存所有字典数据键值对
        CacheDict.allDictMap.put(dictionary.getDictMark(), dictionary.getDictValue());
    }
}


@Override
public BaseRepository<Dictionary,String> getRepository(){
    return dictRepository;
}


@Override
public String getDicVal(String dicKey){
    return CacheDict.allDictMap.get(dicKey);
}


@Override
public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = dictRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<DictionaryVo> page = dictRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


@Override
public List<Dictionary> getDictList(String dictTypeKey){
    return CacheDict.dictList.get(dictTypeKey);
}


}