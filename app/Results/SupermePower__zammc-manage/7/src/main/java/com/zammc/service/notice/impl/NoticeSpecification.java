package com.zammc.service.notice.impl;
 import com.github.wenhao.jpa.Specifications;
import com.zammc.domain.notice.NoticeEntity;
import org.springframework.data.jpa.domain.Specification;
public class NoticeSpecification {


public Specification<NoticeEntity> where(NoticeEntity request){
    if (null != request.getNoticeName()) {
        // 全部
        return Specifications.<NoticeEntity>and().eq("dataStatus", "0").eq("noticeName", request.getNoticeName()).build();
    } else if (null == request.getNoticeName()) {
        return Specifications.<NoticeEntity>and().eq("dataStatus", "0").build();
    } else {
        return null;
    }
}


}