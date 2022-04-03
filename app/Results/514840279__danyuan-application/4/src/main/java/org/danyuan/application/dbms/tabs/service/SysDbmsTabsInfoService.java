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
            logger.error(info.getTabsName() + "表不存在", SysDbmsTabsInfoService.class);
        } finally {
            sysDbmsTabsColsInfoDao.deleteByTabsUuid(info.getUuid());
            sysDbmsTabsInfoDao.delete(info);
        }
    }
}


public List<SysDbmsTabsInfo> findAllTable(SysDbmsTabsInfoVo vo){
    if (vo.getParamList() == null || vo.getParamList().size() == 0) {
        // 无条件查询
        return sysDbmsTabsInfoDao.findAllByUserIndexAndTypeUuid(vo.getUserindex(), vo.getTypeUuid());
    } else {
        // 多条件时循环查询并找出userindex都有的表
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
                // 多个userindex对比找到相同表
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
        // 多条件查询
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
    // 多条件时循环查询并找出userindex都有的表
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
            // 多个userindex对比找到相同表
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
    // 多条件查询
    return minusList;
}


public void change(SysDbmsTabsInfoVo vo){
    // 保存表配信息
    // 动态生成表
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
    // 保存表配信息
    sysDbmsTabsInfoDao.save(info);
    // 动态生成表
    StringBuilder sBuilder = new StringBuilder();
    sBuilder.append("CREATE TABLE ");
    sBuilder.append(info.getTabsName());
    sBuilder.append("(md5 varchar(36) comment 'url的md5值' )");
    jdbcTemplate.execute(sBuilder.toString());
    // 保存列的配置信息
    sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), info.getUuid(), "md5", "md5值", "VARCHAR", 0, 1, 1, "center", "middle", 150, true, true, 0));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 2000, "url地址", "url", 2, "VARCHAR", "url地址", info.getUuid()));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 50, "数据来源", "数据来源", 3, "VARCHAR", "数据来源", info.getUuid()));
    // sysDbmsTabsColsInfoDao.save(new SysDbmsTabsColsInfo(UUID.randomUUID().toString(), 6, "采集时间", "datetime", 4, "date", "采集时间", info.getUuid()));
    // 返回表配置信息
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
    // 多条件时循环查询并找出userindex都有的表
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
    // 多条件查询
    return tabsList;
}


public List<SysDbmsTabsInfo> findAllTableByTypeUuidAndUsername(SysDbmsTabsInfoVo vo){
    // 多条件时循环查询并找出userindex都有的表
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
    // 多条件查询
    return tabsList;
}


public SysDbmsTabsInfo findSysDbmsTabsInofByUuid(String uuid){
    return sysDbmsTabsInfoDao.findSysDbmsTabsInfoByUuid(uuid);
}


public List<SysDbmsTabsInfo> updateSysTableInfo(SysDbmsTabsInfoVo vo){
    // 旧数据
    SysDbmsTabsInfo info = vo.getOld();
    SysDbmsTabsInfo sysDbmsTabsInfo = vo.getNow();
    if (!info.getTabsName().equals(sysDbmsTabsInfo.getTabsName())) {
        // 修改表名
        String sql = "ALTER TABLE " + info.getTabsName() + "  RENAME TO  " + sysDbmsTabsInfo.getTabsName();
        jdbcTemplate.execute(sql);
    }
    if (sysDbmsTabsInfo.getTabsName() != null) {
        // 修改注释
        String sql = "ALTER TABLE " + sysDbmsTabsInfo.getTabsName() + "  COMMENT='" + sysDbmsTabsInfo.getTabsDesc() + "'";
        jdbcTemplate.execute(sql);
    }
    // 修改配置信息
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
            case "证件号码":
            case "证件号":
            case "身份证号":
            case "身份证":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("ID");
                }
                col.setUserIndex("SFZH");
                break;
            case "mobile":
            case "dhhm":
            case "电话号码":
            case "电话号":
            case "电话":
            case "手机号":
            case "联系电话":
            case "发件人电话":
            case "收件人电话":
            case "联系方式":
            case "手机号码":
            case "手机":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("电话");
                }
                col.setUserIndex("DHHM");
                break;
            case "name":
            case "姓名":
            case "名称":
            case "作者":
            case "联系人":
            case "收件人":
            case "发件人":
            case "关系人":
            case "rymc":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("姓名");
                }
                col.setUserIndex("RYXM");
                break;
            case "nick":
            case "昵称":
            case "网名":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("昵称");
                }
                col.setUserIndex("NIKE");
                break;
            case "email":
            case "dzyx":
            case "邮箱":
            case "电子邮箱":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("邮箱");
                }
                col.setUserIndex("DZYX");
                break;
            case "qq":
            case "qq号码":
            case "qq号":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("QQ");
                }
                col.setUserIndex("QQHM");
                break;
            case "企业名称":
            case "企业名":
            case "企业":
            case "公司名称":
            case "公司名":
            case "公司":
            case "单位名称":
            case "单位名":
            case "单位":
            case "店铺名称":
            case "店铺名":
            case "店铺":
            case "网店名称":
            case "网店":
            case "机构名称":
            case "机构名":
            case "机构":
            case "gsmc":
            case "qymc":
            case "jgmc":
            case "dwmc":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("企业名称");
                }
                col.setUserIndex("GSMC");
                break;
            case "url":
            case "连接地址":
            case "连接":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("连接");
                }
                break;
            case "md5":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("url值");
                }
                col.setUserIndex("MD5");
            case "title":
            case "标题":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("标题");
                }
                col.setUserIndex("TITLE");
                break;
            case "bookname":
            case "书名":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("书名");
                }
                col.setUserIndex("BOOKNAME");
                break;
            case "地址":
            case "联系地址":
            case "发件人地址":
            case "收件人地址":
            case "dz":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("地址");
                }
                col.setUserIndex("DZ");
                break;
            case "省":
            case "省份":
            case "province":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("省份");
                }
                col.setUserIndex("province");
                break;
            case "市":
            case "城市":
            case "city":
                if (col.getColsDesc() == null || "".equals(col.getColsDesc())) {
                    col.setColsDesc("省份");
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
         * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
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