package com.ec.survey.service.mapping;
 import com.ec.survey.model.Paging;
import com.ec.survey.model.SqlPagination;
import org.springframework.stereotype.Component;
@Component
public class PaginationMapper {


public SqlPagination toSqlPagination(Paging paging){
    return new SqlPagination(paging.getCurrentPage(), paging.getItemsPerPage());
}


}