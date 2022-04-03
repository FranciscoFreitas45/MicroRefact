package com.wxcrm.goods;
 import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.wxcrm.common.dao.IHibernateDao;
import com.wxcrm.common.dao.IJdbcDao;
import com.wxcrm.util.Page;
import com.wxcrm.Interface.IJdbcDao;
import com.wxcrm.Interface.IHibernateDao;
import com.wxcrm.DTO.IJdbcDao;
@Service
@Transactional
public class GoodsService implements IGoodsService{

@Autowired
 private  IJdbcDao jdbcDao;

@Autowired
 private  IHibernateDao hibernateDao;


public void updGoodsFenlei(WcGoodsFenlei feilei){
    // TODO Auto-generated method stub
    hibernateDao.update(feilei);
}


public void addGoodsFenlei(WcGoodsFenlei fenlei){
    // TODO Auto-generated method stub
    hibernateDao.add(fenlei);
}


public Page queryGoodsFeilei(WcGoodsFenlei feilei){
    // TODO Auto-generated method stub
    Integer wcsId = feilei.getWgfWcsId();
    StringBuilder sql = new StringBuilder(" select " + " a.WGF_ID,a.WGF_NAME,a.WGF_WCS_ID,a.WGF_STATUS,a.WGF_REGISTOR,a.WGF_REGISTDATE  from WC_GOODS_FENLEI a " + " where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) from WC_GOODS_FENLEI a where 1=1 ");
    List<Object> paraList = new ArrayList<Object>();
    if (wcsId != null && wcsId > 0) {
        sql.append(" and a.WGF_WCS_ID = ? ");
        sqlCnt.append(" and a.WGF_WCS_ID = ? ");
        paraList.add(wcsId);
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), feilei.getCurrentPage(), feilei.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public void addGoods(WcGoods goods){
    // TODO Auto-generated method stub
    hibernateDao.add(goods);
}


public WcGoods getGoodsById(Integer wgsId){
    // TODO Auto-generated method stub
    return hibernateDao.get(WcGoods.class, wgsId);
}


public void updGoods(WcGoods goods){
    // TODO Auto-generated method stub
    hibernateDao.update(goods);
}


public void delGoods(WcGoods goods){
    // TODO Auto-generated method stub
    String[] wgsIds = goods.getWgsIds();
    String wgsIdStr = StringUtils.arrayToCommaDelimitedString(wgsIds);
    // �����������е���Ʒ��Ӧ����¼ɾ��
    String sql1 = "delete from WC_GOODS_IN where WGI_WGS_ID in (" + wgsIdStr + ")";
    // �����������е���Ʒ��Ӧ�����¼ɾ��
    String sql2 = "delete from WC_GOODS_OUT where WGO_WGS_ID in (" + wgsIdStr + ")";
    // ɾ��������Ʒ
    String sql = "delete from WC_GOODS where WGS_ID in (" + wgsIdStr + ")";
    jdbcDao.delete(sql1);
    jdbcDao.delete(sql2);
    jdbcDao.delete(sql);
}


public Page queryGoods(WcGoods goods){
    // TODO ��ѯ��Ʒ
    Integer wfgId = goods.getWgsWgfId();
    StringBuilder sql = new StringBuilder(" select " + " a.WGS_ID," + " a.WGS_NAME," + " a.WGS_WGF_ID," + " a.WGS_WCS_ID," + " a.WGS_BZ_PRICE," + " a.WGS_LS_PRICE," + " a.WGS_KUCUN," + " a.WGS_STATUS," + " a.WGS_REGISTOR," + " a.WGS_REGISTDATE," + " b.WGF_NAME " + " from WC_GOODS a " + " left join WC_GOODS_FENLEI b on a.WGS_WGF_ID = b.WGF_ID " + " where 1=1 ");
    StringBuilder sqlCnt = new StringBuilder(" select count(*) " + " from WC_GOODS a " + " left join WC_GOODS_FENLEI b on a.WGS_WGF_ID = b.WGF_ID " + " where 1=1 ");
    List<Object> paraList = new ArrayList<Object>();
    if (wfgId != null && wfgId > 0) {
        sql.append(" and a.WGS_WGF_ID = ? ");
        sqlCnt.append(" and a.WGS_WGF_ID = ? ");
        paraList.add(wfgId);
    }
    Page page = new Page(sql.toString(), sqlCnt.toString(), goods.getCurrentPage(), goods.getPageSize(), paraList.toArray());
    jdbcDao.queryForPage(page);
    return page;
}


public void delGoodsFenlei(WcGoodsFenlei fenlei){
    // TODO Auto-generated method stub
    for (int i = 0; i < fenlei.getWgfIds().length; i++) {
        String wfgId = fenlei.getWgfIds()[i];
        List<Map<String, Object>> listMap = this.jdbcDao.queryForList("select WGS_ID from WC_GOODS where WGS_WGF_ID = " + wfgId);
        List<String> strList = new ArrayList<String>();
        for (Map<String, Object> map : listMap) {
            strList.add(Integer.toString((Integer) map.get("WGS_ID")));
        }
        String[] wgsIds = strList.toArray(new String[0]);
        WcGoods goods = new WcGoods();
        goods.setWgsIds(wgsIds);
        this.delGoods(goods);
    }
    String wfgIdstr = StringUtils.arrayToCommaDelimitedString(fenlei.getWgfIds());
    // ��ѡ����ɾ��
    String sql4 = "delete from WC_GOODS_FENLEI where WGF_ID in (" + wfgIdstr + ")";
    jdbcDao.delete(sql4);
}


public WcGoodsFenlei getFeileiById(Integer id){
    // TODO Auto-generated method stub
    return hibernateDao.get(WcGoodsFenlei.class, id);
}


public void addGoodsIn(WcGoodsIn goodsIn){
    // TODO Auto-generated method stub
    // ���һ������¼
    hibernateDao.add(goodsIn);
    // ����Ӧ��Ʒ�Ŀ������������
    WcGoods goods = this.getGoodsById(goodsIn.getWgiWgsId());
    Double num = goods.getWgsKucun() + goodsIn.getWgiInNum();
    goods.setWgsKucun(num);
    hibernateDao.update(goodsIn);
}


public void addGoodsFeilei(WcGoodsFenlei feilei){
    // TODO Auto-generated method stub
    hibernateDao.add(feilei);
}


}