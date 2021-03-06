package org.danyuan.application.dbms.tabs.service;
 import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import org.danyuan.application.common.base.BaseService;
import org.danyuan.application.common.base.BaseServiceImpl;
import org.danyuan.application.common.base.Pagination;
import org.danyuan.application.common.config.MultiDatasourceConfig;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsColsInfoDao;
import org.danyuan.application.dbms.tabs.dao.SysDbmsTabsInfoDao;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsColsInfo;
import org.danyuan.application.dbms.tabs.po.SysDbmsTabsInfo;
import org.danyuan.application.dbms.tabs.vo.MulteityParam;
import org.danyuan.application.dbms.tabs.vo.SysDbmsTabsInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
@Service("sysDbmsTabsInfoService")
public class SysDbmsTabsInfoService extends BaseServiceImpl<SysDbmsTabsInfo>implements BaseService<SysDbmsTabsInfo>{

@Autowired
 private MultiDatasourceConfig multiDatasourceConfig;

 private  Logger logger;

@Autowired
 private  SysDbmsTabsInfoDao sysDbmsTabsInfoDao;

@Autowired
 private  SysDbmsTabsColsInfoDao sysDbmsTabsColsInfoDao;

@Autowired
 private  SysDbmsTabsColsInfoService sysDbmsTabsColsInfoService;

@Autowired
 private JdbcTemplate jdbcTemplate;

 private  long serialVersionUID;


public void drop(List<SysDbmsTabsInfo> list){
    for (SysDbmsTabsInfo info : list) {
        try {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("drop table " + info.getTabsName());
            jdbcTemplate.execute(sBuilder.toString());
        } catch (Exception e) {
            logger.error(info.getTabsName() + "????????????", SysDbmsTabsInfoService.class);
        } finally {
            sysDbmsTabsColsInfoDao.deleteByTabsUuid(info.getUuid());
            sysDbmsTabsInfoDao.delete(info);
        }
    }
}


public List<SysDbmsTabsInfo> findAllTable(SysDbmsTabsInfoVo vo){
    if (vo.getParamList() == null || vo.getParamList().size() == 0) {
        // ???????????????
        return sysDbmsTabsInfoDao.findAllByUserIndexAndTypeUuid(vo.getUserindex(), vo.getTypeUuid());
    } else {
        // ?????????????????????????????????userindex????????????
        List<SysDbmsTabsInfo> minusList = null;
        for (MulteityParam val : vo.getParamList()) {
            List<SysDbmsTabsInfo> tabsList = sysDbmsTabsInfoDao.findAllByUserIndexAndTypeUuid(val.getUserIndex(), vo.getTypeUuid());
            if (tabsList == null) {
                return null;
            }
            if (minusList == null) {
                minusList = tabsList;
            } else {
                List<SysDbmsTabsInfo> existsList = new ArrayList<>();
                // ??????userindex?????????????????????
                for (SysDbmsTabsInfo sysZhcxTab : minusList) {
                    for (SysDbmsTabsInfo sysZhcxTab2 : tabsList) {
                        if (sysZhcxTab.getUuid().equals(sysZhcxTab2.getUuid())) {
                            existsList.add(sysZhcxTab);
                        }
                    }
                }
                minusList = existsList;
                if (minusList.size() == 0) {
                    return null;
                }
            }
        }
        // ???????????????
        return minusList;
    }
}


public List<String> findAllSchema(SysDbmsTabsInfo info) throws ClassNotFoundException{
    DataSource dataSource = multiDatasourceConfig.getDataSource(info.getJdbcUuid());
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    String databaseProductNameString = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
    List<String> list = new ArrayList<>();
    StringBuilder sBuilder = new StringBuilder();
    switch(databaseProductNameString.toLowerCase()) {
        case ("oracle"):
            sBuilder.append(" select username from all_users where username not like '%$%' " + " and username not in('SYS','SYSTEM','DBSNMP','CTXSYS','MDSYS'\r\n" + ",'FLOWS_020100','FLOWS_FILES','TSMSYS','XDB','OUTLN','APEX_030200','SYSMAN','ORDSYS','TOAD','ORDSYS'\r\n" + ",'ORDDATA','OLAPSYS','APPQOSSYS','OWBSYS') ");
            break;
        case ("mysql"):
            break;
    }
    logger.debug(sBuilder.toString(), SysDbmsTabsInfoService.class);
    list = jdbcTemplate.queryForList(sBuilder.toString(), String.class);
    return list;
}


public List<SysDbmsTabsInfo> findAllTableByUser(SysDbmsTabsInfoVo vo){
    // ?????????????????????????????????userindex????????????
    List<SysDbmsTabsInfo> minusList = null;
    for (MulteityParam val : vo.getParamList()) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select distinct * from sys_dbms_tabs_info a ");
        stringBuilder.append("  where a.uuid in ( ");
        stringBuilder.append("   select b.tabs_id from sys_roles_tabs_info b ");
        stringBuilder.append("    where b.role_id in (");
        stringBuilder.append("     select c.roles_id from sys_user_roles_info c");
        stringBuilder.append("      where c.user_id in ( ");
        stringBuilder.append("       select d.uuid from sys_user_base_info d");
        stringBuilder.append("        where d.user_name = '" + vo.getUsername() + "'");
        stringBuilder.append("      ) and c.checked = 1");
        stringBuilder.append("    ) ");
        stringBuilder.append("  ) and a.delete_flag = 0");
        stringBuilder.append("  and a.type_uuid='" + vo.getTypeUuid() + "' ");
        stringBuilder.append("  and a.uuid in  ( ");
        stringBuilder.append("  select c.tabs_uuid from sys_dbms_tabs_cols_info c ");
        stringBuilder.append("   where c.user_index='" + val.getUserIndex() + "' ");
        stringBuilder.append("   and c.delete_flag = 0  ");
        stringBuilder.append("  ) ");
        stringBuilder.append("  order by a.tabs_order ");
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        List<SysDbmsTabsInfo> tabsList = template.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
        // List<SysDbmsTabsInfo> tabsList = sysDbmsTabsInfoDao.findAllByUserIndexAndTypeUuid(val.getUserIndex(), vo.getTypeUuid());
        if (tabsList == null) {
            return null;
        }
        if (minusList == null) {
            minusList = tabsList;
        } else {
            List<SysDbmsTabsInfo> existsList = new ArrayList<>();
            // ??????userindex?????????????????????
            for (SysDbmsTabsInfo sysZhcxTab : minusList) {
                for (SysDbmsTabsInfo sysZhcxTab2 : tabsList) {
                    if (sysZhcxTab.getUuid().equals(sysZhcxTab2.getUuid())) {
                        existsList.add(sysZhcxTab);
                    }
                }
            }
            minusList = existsList;
            if (minusList.size() == 0) {
                return null;
            }
        }
    }
    // ???????????????
    return minusList;
}


public void change(SysDbmsTabsInfoVo vo){
    // ??????????????????
    // ???????????????
    try {
        SysDbmsTabsInfo info = vo.getOld();
        SysDbmsTabsInfo sysDbmsTabsInfo = vo.getNow();
        if (!info.getTabsName().equals(sysDbmsTabsInfo.getTabsName())) {
            StringBuilder sBuilder = new StringBuilder();
            sBuilder.append("ALTER TABLE ");
            sBuilder.append(vo.getOld().getTabsName());
            sBuilder.append(" RENAME TO ");
            sBuilder.append(vo.getNow().getTabsName());
            jdbcTemplate.execute(sBuilder.toString());
        }
        if (sysDbmsTabsInfo.getTabsName() != null) {
            sysDbmsTabsInfoDao.change(vo.getNow().getTabsName(), vo.getNow().getTabsDesc(), vo.getNow().getTypeUuid(), vo.getNow().getJdbcUuid(), vo.getNow().getUuid(), vo.getUsername());
        }
    } catch (Exception e) {
        logger.error(e.getMessage(), SysDbmsTabsInfoService.class);
    }
}


@Override
public SysDbmsTabsInfo save(SysDbmsTabsInfo info){
    // ??????????????????
    sysDbmsTabsInfoDao.save(info);
    // ???????????????
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("CREATE TABLE ");
    sBuilder.append(info.getTabsName());
    sBuilder.append("(md5 varchar(36) comment 'url???md5???' )");
    jdbcTemplate.execute(sBuilder.toString());
    // ????????????????????????
    sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), info.getUuid(), "md5", "md5???", "VARCHAR", 0, 1, 1, "center", "middle", 150, true, true, 0));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 2000, "url??????", "url", 2, "VARCHAR", "url??????", info.getUuid()));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 50, "????????????", "????????????", 3, "VARCHAR", "????????????", info.getUuid()));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 6, "????????????", "datetime", 4, "date", "????????????", info.getUuid()));
    // ?????????????????????
    return info;
}


@Override
public SysDbmsTabsInfo findOne(SysDbmsTabsInfo info){
    Example<SysDbmsTabsInfo> example = Example.of(info);
    Optional<SysDbmsTabsInfo> info2 = sysDbmsTabsInfoDao.findOne(example);
    if (info2.isPresent()) {
        return info2.get();
    }
    return null;
}


@Override
public void deleteAll(List<SysDbmsTabsInfo> list){
    for (SysDbmsTabsInfo info : list) {
        sysDbmsTabsColsInfoDao.deleteByTabsUuid(info.getUuid());
        sysDbmsTabsInfoDao.delete(info);
    }
}


@Override
public List<SysDbmsTabsInfo> findAll(SysDbmsTabsInfo info){
    Example<SysDbmsTabsInfo> example = Example.of(info);
    return sysDbmsTabsInfoDao.findAll(example);
}


@Override
public Predicate toPredicate(Root<SysDbmsTabsInfo> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder){
    List<Predicate> list = new ArrayList<>();
    if (vo.getInfo().getJdbcUuid() != null && !"".equals(vo.getInfo().getJdbcUuid())) {
        list.add(criteriaBuilder.equal(root.get("jdbcUuid"), vo.getInfo().getJdbcUuid()));
    }
    if (vo.getInfo().getTypeUuid() != null && !"".equals(vo.getInfo().getTypeUuid())) {
        list.add(criteriaBuilder.equal(root.get("typeUuid"), vo.getInfo().getTypeUuid()));
    }
    if (vo.getInfo().getTabsName() != null && !"".equals(vo.getInfo().getTabsName())) {
        list.add(criteriaBuilder.like(root.get("tabsName"), "%" + vo.getInfo().getTabsName() + "%"));
    }
    if (vo.getInfo().getTabsDesc() != null && !"".equals(vo.getInfo().getTabsDesc())) {
        list.add(criteriaBuilder.like(root.get("tabsDesc"), "%" + vo.getInfo().getTabsDesc() + "%"));
    }
    return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
}


public List<SysDbmsTabsInfo> findAllBySysTableInfoAndUsername(SysDbmsTabsInfo sysDbmsTabsInfo){
    // ?????????????????????????????????userindex????????????
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("select distinct * from sys_dbms_tabs_info a ");
    stringBuilder.append("  where a.uuid in ( ");
    stringBuilder.append("   select b.tabs_id from sys_roles_tabs_info b ");
    stringBuilder.append("    where b.role_id in (");
    stringBuilder.append("     select c.roles_id from sys_user_roles_info c");
    stringBuilder.append("      where c.user_id in ( ");
    stringBuilder.append("       select d.uuid from sys_user_base_info d");
    stringBuilder.append("        where d.user_name = '" + sysDbmsTabsInfo.getCreateUser() + "'");
    stringBuilder.append("      ) and c.checked = 1");
    stringBuilder.append("    ) ");
    stringBuilder.append("  ) and a.delete_flag = 0");
    if (sysDbmsTabsInfo.getTypeUuid() != null && !"".equals(sysDbmsTabsInfo.getTypeUuid())) {
        stringBuilder.append("  and a.type_uuid = '" + sysDbmsTabsInfo.getTypeUuid() + "' ");
    }
    if (sysDbmsTabsInfo.getJdbcUuid() != null && !"".equals(sysDbmsTabsInfo.getJdbcUuid())) {
        stringBuilder.append("  and a.jdbc_uuid = '" + sysDbmsTabsInfo.getJdbcUuid() + "' ");
    }
    stringBuilder.append("  order by a.tabs_order ");
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<SysDbmsTabsInfo> tabsList = template.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
    // ???????????????
    return tabsList;
}


public List<SysDbmsTabsInfo> findAllTableByTypeUuidAndUsername(SysDbmsTabsInfoVo vo){
    // ?????????????????????????????????userindex????????????
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("select distinct * from sys_dbms_tabs_info a ");
    stringBuilder.append("  where a.uuid in ( ");
    stringBuilder.append("   select b.tabs_id from sys_roles_tabs_info b ");
    stringBuilder.append("    where b.role_id in (");
    stringBuilder.append("     select c.roles_id from sys_user_roles_info c");
    stringBuilder.append("      where c.user_id in ( ");
    stringBuilder.append("       select d.uuid from sys_user_base_info d");
    stringBuilder.append("        where d.user_name = '" + vo.getUsername() + "'");
    stringBuilder.append("      ) and c.checked = 1");
    stringBuilder.append("    ) ");
    stringBuilder.append("  ) and a.delete_flag = 0");
    stringBuilder.append("  and a.type_uuid='" + vo.getInfo().getTypeUuid() + "' ");
    stringBuilder.append("  order by a.tabs_order ");
    NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
    List<SysDbmsTabsInfo> tabsList = template.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(SysDbmsTabsInfo.class));
    // ???????????????
    return tabsList;
}


public SysDbmsTabsInfo findSysDbmsTabsInofByUuid(String uuid){
    return sysDbmsTabsInfoDao.findSysDbmsTabsInfoByUuid(uuid);
}


public List<SysDbmsTabsInfo> updateSysTableInfo(SysDbmsTabsInfoVo vo){
    // ?????????
    SysDbmsTabsInfo info = vo.getOld();
    SysDbmsTabsInfo sysDbmsTabsInfo = vo.getNow();
    if (!info.getTabsName().equals(sysDbmsTabsInfo.getTabsName())) {
        // ????????????
        String sql = "ALTER TABLE " + info.getTabsName() + "  RENAME TO  " + sysDbmsTabsInfo.getTabsName();
        jdbcTemplate.execute(sql);
    }
    if (sysDbmsTabsInfo.getTabsName() != null) {
        // ????????????
        String sql = "ALTER TABLE " + sysDbmsTabsInfo.getTabsName() + "  COMMENT='" + sysDbmsTabsInfo.getTabsDesc() + "'";
        jdbcTemplate.execute(sql);
    }
    // ??????????????????
    sysDbmsTabsInfoDao.save(sysDbmsTabsInfo);
    return sysDbmsTabsInfoDao.findAll();
}


public void savev(SysDbmsTabsInfo info) throws SQLException{
    sysDbmsTabsInfoDao.save(info);
    // SysDbmsTabsColsInfo vCol = new SysDbmsTabsColsInfo();
    // vCol.setTabsUuid(info.getUuid());
    List<SysDbmsTabsColsInfo> list = sysDbmsTabsColsInfoService.pagev(info.getUuid());
    for (SysDbmsTabsColsInfo vSysZhxCol : list) {
        SysDbmsTabsColsInfo col = new SysDbmsTabsColsInfo();
        col.setUuid(UUID.randomUUID().toString());
        col.setTabsUuid(vSysZhxCol.getTabsUuid());
        col.setColsType(vSysZhxCol.getColsType());
        col.setColsLength(vSysZhxCol.getColsLength());
        col.setColsName(vSysZhxCol.getColsName());
        col.setColsOrder(vSysZhxCol.getColsOrder().intValue());
        col.setColsVisible(true);
        col.setCreateUser(info.getCreateUser());
        col.setUpdateUser(info.getCreateUser());
        col.setDeleteFlag(0);
        col.setColsAlign("left");
        col.setColsValign("middle");
        col.setColsSwitchable(true);
        col.setDimeFlag(true);
        col.setUseful(0);
        col.setColsDesc(vSysZhxCol.getColsDesc());
        switch(vSysZhxCol.getColsName().toLowerCase()) {
            case "id":
            case "sfzh":
            case "cerno":
            case "????????????":
            case "?????????":
            case "????????????":
            case "?????????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("ID");
                }
                col.setUserIndex("SFZH");
                break;
            case "mobile":
            case "dhhm":
            case "????????????":
            case "?????????":
            case "??????":
            case "?????????":
            case "????????????":
            case "???????????????":
            case "???????????????":
            case "????????????":
            case "????????????":
            case "??????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("DHHM");
                break;
            case "name":
            case "??????":
            case "??????":
            case "??????":
            case "?????????":
            case "?????????":
            case "?????????":
            case "?????????":
            case "rymc":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("RYXM");
                break;
            case "nick":
            case "??????":
            case "??????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("NIKE");
                break;
            case "email":
            case "dzyx":
            case "??????":
            case "????????????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("DZYX");
                break;
            case "qq":
            case "qq??????":
            case "qq???":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("QQ");
                }
                col.setUserIndex("QQHM");
                break;
            case "????????????":
            case "?????????":
            case "??????":
            case "????????????":
            case "?????????":
            case "??????":
            case "????????????":
            case "?????????":
            case "??????":
            case "????????????":
            case "?????????":
            case "??????":
            case "????????????":
            case "??????":
            case "????????????":
            case "?????????":
            case "??????":
            case "gsmc":
            case "qymc":
            case "jgmc":
            case "dwmc":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("????????????");
                }
                col.setUserIndex("GSMC");
                break;
            case "url":
            case "????????????":
            case "??????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                break;
            case "md5":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("url???");
                }
                col.setUserIndex("MD5");
            case "title":
            case "??????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("TITLE");
                break;
            case "bookname":
            case "??????":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("BOOKNAME");
                break;
            case "??????":
            case "????????????":
            case "???????????????":
            case "???????????????":
            case "dz":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("DZ");
                break;
            case "???":
            case "??????":
            case "province":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("province");
                break;
            case "???":
            case "??????":
            case "city":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("??????");
                }
                col.setUserIndex("city");
                break;
            default:
                break;
        }
        sysDbmsTabsColsInfoService.save(col);
    }
}


@Override
public Page<SysDbmsTabsInfo> page(Pagination<SysDbmsTabsInfo> vo){
    List<Order> orders = new ArrayList<>();
    if (vo.getSortName() != null) {
        Order order;
        if (vo.getSortOrder().equals("desc")) {
            order = Order.desc(vo.getSortName());
        } else {
            order = Order.asc(vo.getSortName());
        }
        orders.add(order);
    } else {
        Order order1 = new Order(Direction.ASC, "tabsOrder");
        orders.add(order1);
        Order order2 = new Order(Direction.DESC, "createTime");
        orders.add(order2);
    }
    if (vo.getInfo() == null) {
        vo.setInfo(new SysDbmsTabsInfo());
    }
    Sort sort = Sort.by(orders);
    PageRequest request = PageRequest.of(vo.getPageNumber() - 1, vo.getPageSize(), sort);
    Page<SysDbmsTabsInfo> page = sysDbmsTabsInfoDao.findAll(new Specification<SysDbmsTabsInfo>() {

        /**
         * @Fields serialVersionUID : TODO(??????????????????????????????????????????)
         */
        private static final long serialVersionUID = 1L;

        @Override
        public Predicate toPredicate(Root<SysDbmsTabsInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> list = new ArrayList<>();
            if (vo.getInfo().getJdbcUuid() != null && !"".equals(vo.getInfo().getJdbcUuid())) {
                list.add(criteriaBuilder.equal(root.get("jdbcUuid"), vo.getInfo().getJdbcUuid()));
            }
            if (vo.getInfo().getTypeUuid() != null && !"".equals(vo.getInfo().getTypeUuid())) {
                list.add(criteriaBuilder.equal(root.get("typeUuid"), vo.getInfo().getTypeUuid()));
            }
            if (vo.getInfo().getTabsName() != null && !"".equals(vo.getInfo().getTabsName())) {
                list.add(criteriaBuilder.like(root.get("tabsName"), "%" + vo.getInfo().getTabsName() + "%"));
            }
            if (vo.getInfo().getTabsDesc() != null && !"".equals(vo.getInfo().getTabsDesc())) {
                list.add(criteriaBuilder.like(root.get("tabsDesc"), "%" + vo.getInfo().getTabsDesc() + "%"));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
        }
    }, request);
    return page;
}


}