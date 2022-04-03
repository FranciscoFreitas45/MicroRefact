package com.zammc.service.dining;
 import com.zammc.domain.table.DiningTableEntity;
import com.zammc.page.PageBean;
import com.zammc.response.Message;
import java.util.List;
public interface DiningTableService {


public void deleteDiningTable(DiningTableEntity diningTableEntity)
;

public Message addDiningTable(DiningTableEntity diningTableEntity)
;

public void queryDiningTablePage(DiningTableEntity diningTableEntity,PageBean pageBean)
;

public Message editDiningTable(DiningTableEntity diningTableEntity)
;

public List<DiningTableEntity> indexDiningTableData()
;

public Long queryFreeTableCount()
;

public DiningTableEntity queryDiningTableById(DiningTableEntity diningTableEntity)
;

}