package org.danyuan.application.share.file.service;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.share.file.dao.SysShareFileInfoDao;
import org.danyuan.application.share.file.po.SysShareFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.SysShareFileInfoDao;
@Service
public class SysShareFileInfoService extends BaseServiceImpl<SysShareFileInfo>implements BaseService<SysShareFileInfo>{

@Autowired
 private SysShareFileInfoDao sysShareFileInfoDao;

 private  long serialVersionUID;


public Page<SysShareFileInfo> search(Pagination<SysShareFileInfo> vo){
    // Example<SysShareFileInfo> example = Example.of(info.getInfo());
    Sort sort = Sort.by(new Order(Direction.DESC, "publishDate"), new Order(Direction.DESC, "updateTime"), new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysShareFileInfo> sourceCodes = sysShareFileInfoDao.findAll(new Specification<SysShareFileInfo>() {

        /**
         * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
         */
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<SysShareFileInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<>();
            if (vo.getInfo().getFileName() != null && !"".equals(vo.getInfo().getFileName())) {
                list.add(criteriaBuilder.or(criteriaBuilder.like(root.get("fileName"), "%" + vo.getInfo().getFileName() + "%"), criteriaBuilder.like(root.get("fileInstru"), "%" + vo.getInfo().getFileName() + "%")));
            }
            if (vo.getInfo().getFileType() != null && !"".equals(vo.getInfo().getFileType())) {
                list.add(criteriaBuilder.equal(root.get("fileType"), vo.getInfo().getFileType()));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        }
    }, request);
    return sourceCodes;
}


@Override
public Predicate toPredicate(Root<SysShareFileInfo> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> list = new ArrayList<>();
    if (vo.getInfo().getFileName() != null && !"".equals(vo.getInfo().getFileName())) {
        list.add(criteriaBuilder.or(criteriaBuilder.like(root.get("fileName"), "%" + vo.getInfo().getFileName() + "%"), criteriaBuilder.like(root.get("fileInstru"), "%" + vo.getInfo().getFileName() + "%")));
    }
    if (vo.getInfo().getFileType() != null && !"".equals(vo.getInfo().getFileType())) {
        list.add(criteriaBuilder.equal(root.get("fileType"), vo.getInfo().getFileType()));
    }
    return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
}


}