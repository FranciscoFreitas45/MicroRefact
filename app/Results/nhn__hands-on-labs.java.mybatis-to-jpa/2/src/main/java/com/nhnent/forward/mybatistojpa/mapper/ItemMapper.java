package com.nhnent.forward.mybatistojpa.mapper;
 import com.nhnent.forward.mybatistojpa.model.Item;
import org.apache.ibatis.annotations.Param;
import java.util.List;
public interface ItemMapper {


public List<Item> getItems(int offset,int limit)
;

public int deleteItem(Long itemId)
;

public int getItemCount()
;

public Item getItem(Long itemId)
;

public int updateItem(Item item)
;

public int insertItem(Item item)
;

}