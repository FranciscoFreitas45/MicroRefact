package org.danyuan.application.softm.syslog.service;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.danyuan.application.softm.syslog.dao.SysComnLogsDao;
import org.danyuan.application.softm.syslog.dao.VSysComnLogsDao;
import org.danyuan.application.softm.syslog.po.SysComnLogs;
import org.danyuan.application.softm.syslog.po.VSysComnLogs;
import org.danyuan.application.softm.syslog.vo.SysComnLogsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.danyuan.application.Interface.VSysComnLogsDao;
import org.danyuan.application.Interface.VSysComnLogsDao;
@Service
public class SysComnLogsService {

@Autowired
 private SysComnLogsDao sysComnLoggersDao;

@Autowired
 private VSysComnLogsDao vSysComnLogsDao;

 private  long serialVersionUID;

 private  long serialVersionUID;

 private  long serialVersionUID;


public Page<VSysComnLogs> findAllZhcx(SysComnLogsVo vo){
    Example<VSysComnLogs> example = Example.of(new VSysComnLogs());
    Sort sort = Sort.by(new Order(Direction.DESC, "date1"), new Order(Direction.DESC, "time1"));
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<VSysComnLogs> sourceCodes = vSysComnLogsDao.findAll(example, request);
    return sourceCodes;
}


public Page<SysComnLogs> findAllLongtime(SysComnLogsVo vo){
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysComnLogs> sourceCodes = sysComnLoggersDao.findAll(new Specification<SysComnLogs>() {

        /**
         * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
         */
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<SysComnLogs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.gt((root.get("requestLong").as(Long.class)), 1000));
            // list.add(cb.equal((root.get("url").as(String.class)), "/zhcx/findAllTableRow"));
            // list.add(cb.equal((root.get("classMethod").as(String.class)), "findAllTableRow"));
            return cb.and(list.toArray(new Predicate[list.size()]));
        }
    }, request);
    return sourceCodes;
}


public Page<SysComnLogs> findAllError(SysComnLogsVo vo){
    // Example<SysComnLogs> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysComnLogs> sourceCodes = sysComnLoggersDao.findAll(new Specification<SysComnLogs>() {

        /**
         * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
         */
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<SysComnLogs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<>();
            list.add(cb.isNotNull(root.get("message").as(String.class)));
            return cb.and(list.toArray(new Predicate[list.size()]));
        }
    }, request);
    return sourceCodes;
}


@Override
public Predicate toPredicate(Root<SysComnLogs> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> list = new ArrayList<>();
    if (vo.getInfo().getCreateUser() != null) {
        list.add(cb.equal(root.get("createUser").as(String.class), vo.getInfo().getCreateUser()));
    }
    if (vo.getStrartCreateTime() != null && !"".equals(vo.getStrartCreateTime().trim())) {
        list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), vo.getStrartCreateTime()));
    }
    if (vo.getEndCreateTime() != null && !"".equals(vo.getEndCreateTime().trim())) {
        list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), vo.getEndCreateTime()));
    }
    return cb.and(list.toArray(new Predicate[list.size()]));
}


public Page<SysComnLogs> findAll(SysComnLogsVo vo){
    // Example<SysComnLogs> example = Example.of(vo.getInfo());
    Sort sort = Sort.by(new Order(Direction.DESC, "createTime"));
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysComnLogs> sourceCodes = sysComnLoggersDao.findAll(new Specification<SysComnLogs>() {

        /**
         * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
         */
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<SysComnLogs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<>();
            if (vo.getInfo().getCreateUser() != null) {
                list.add(cb.equal(root.get("createUser").as(String.class), vo.getInfo().getCreateUser()));
            }
            if (vo.getStrartCreateTime() != null && !"".equals(vo.getStrartCreateTime().trim())) {
                list.add(cb.greaterThanOrEqualTo(root.get("createTime").as(String.class), vo.getStrartCreateTime()));
            }
            if (vo.getEndCreateTime() != null && !"".equals(vo.getEndCreateTime().trim())) {
                list.add(cb.lessThanOrEqualTo(root.get("createTime").as(String.class), vo.getEndCreateTime()));
            }
            return cb.and(list.toArray(new Predicate[list.size()]));
        }
    }, request);
    return sourceCodes;
}


}