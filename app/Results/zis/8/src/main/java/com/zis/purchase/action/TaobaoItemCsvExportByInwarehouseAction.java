package com.zis.purchase.action;
 import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.ShopItemInfo;
import com.zis.bookinfo.bean.ShopItemInfoShopName;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.bookinfo.util.YouLuNetDetailCapture;
import com.zis.common.actiontemplate.CommonCsvExportAction;
import com.zis.common.util.ZisUtils;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.biz.DoPurchaseService;
import com.zis.Interface.BookService;
import com.zis.Interface.DoPurchaseService;
import com.zis.DTO.Bookinfo;
import com.zis.DTO.InwarehouseDetail;
import com.zis.DTO.BookService;
@Deprecated
public class TaobaoItemCsvExportByInwarehouseAction extends CommonCsvExportAction<InwarehouseDetail>{

 private  String dataFmt;

 private  String describFmt;

 private  Map<String,InwarehouseDetail> uniqueIsbnDealt;

 private  Integer[] batchSelectedItem;

 private  BookService bookService;

 private  DoPurchaseService doPurchaseService;


@Override
public String getTableHeaders(){
    return "version 1.00\r\ntitle	cid	seller_cids	stuff_status	location_state	location_city	item_type	price	auction_increment	num	valid_thru	freight_payer	post_fee	ems_fee	express_fee	has_invoice	has_warranty	approve_status	has_showcase	list_time	description	cateProps	postage_id	has_discount	modified	upload_fail_msg	picture_status	auction_point	picture	video	skuProps	inputPids	inputValues	outer_id	propAlias	auto_fill	num_id	local_cid	navigation_type	user_name	syncStatus	is_lighting_consigment	is_xinpin	foodparame	features	buyareatype	global_stock_type	global_stock_country	sub_stock_type	item_size	item_weight	sell_promise	custom_design_flag	wireless_desc	barcode	sku_barcode	newprepay	subtitle\r\n宝贝名称	宝贝类目	店铺类目	新旧程度	省	城市	出售方式	宝贝价格	加价幅度	宝贝数量	有效期	运费承担	平邮	EMS	快递	发票	保修	放入仓库	橱窗推荐	开始时间	宝贝描述	宝贝属性	邮费模版ID	会员打折	修改时间	上传状态	图片状态	返点比例	新图片	视频	销售属性组合	用户输入ID串	用户输入名-值对	商家编码	销售属性别名	代充类型	数字ID	本地ID	宝贝分类	用户名称	宝贝状态	闪电发货	新品	食品专项	尺码库	采购地	库存类型	国家地区	库存计数	物流体积	物流重量	退换货承诺	定制工具	无线详情	商品条形码	sku 条形码	7天退货	宝贝卖点\r\n";
}


public String getBookEdition(Bookinfo book){
    String bookEdition = book.getBookEdition();
    if (("第一版".equals(bookEdition) || "第1版".equals(bookEdition)) && book.getIsNewEdition() == true) {
        return "";
    } else {
        return "(" + bookEdition + ")";
    }
}


public void setDoPurchaseService(DoPurchaseService doPurchaseService){
    this.doPurchaseService = doPurchaseService;
}


@Override
public Collection<InwarehouseDetail> transformList(List<InwarehouseDetail> list){
    for (InwarehouseDetail record : list) {
        Bookinfo book = this.bookService.findBookById(record.getBookId());
        if (book == null) {
            throw new RuntimeException("没有找到对应图书，bookId=" + record.getBookId());
        }
        // 如果淘宝网已上架，则跳过
        ShopItemInfo item = this.bookService.findShopItemByBookIdAndShopName(ShopItemInfoShopName.TB_ZAIJIAN, book.getId());
        if (item != null) {
            continue;
        }
        String uniqueIsbn = getUniqueIsbn(book);
        // 如果已经处理过，则跳过
        if (uniqueIsbnDealt.containsKey(uniqueIsbn)) {
            InwarehouseDetail exist = uniqueIsbnDealt.get(uniqueIsbn);
            exist.setAmount(exist.getAmount() + record.getAmount());
        } else {
            uniqueIsbnDealt.put(uniqueIsbn, record);
        }
    }
    return uniqueIsbnDealt.values();
}


public void setBookService(BookService bookService){
    this.bookService = bookService;
}


public Integer[] getBatchSelectedItem(){
    return batchSelectedItem;
}


@Override
public String setExportFileName(){
    return "淘宝数据包-" + ZisUtils.getDateString("yyyy-MM-dd") + ".csv";
}


public String getUniqueIsbn(Bookinfo book){
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    return book.getRepeatIsbn() ? book.getIsbn() + "-" + book.getId() : book.getIsbn();
}


public String getShortestBookAuthor(String bookAuthor){
    if (bookAuthor.length() <= 7) {
        return bookAuthor;
    }
    String splitChar = " ";
    String[] authors = bookAuthor.split(splitChar);
    if (authors.length <= 2) {
        return bookAuthor;
    }
    return authors[0] + " " + authors[1];
}


public void setBatchSelectedItem(Integer[] batchSelectedItem){
    this.batchSelectedItem = batchSelectedItem;
}


@Override
public String getRowDatas(InwarehouseDetail record){
    Bookinfo book = this.bookService.findBookById(record.getBookId());
    if (book == null) {
        throw new RuntimeException("没有找到对应图书，bookId=" + record.getBookId());
    }
    String bookIntro = "";
    String content = "";
    if (book.getOutId() != null) {
        YouLuNetDetailCapture capture = new YouLuNetDetailCapture();
        try {
            BookMetadata metadata = capture.getBookInfo(book.getOutId() + "");
            content = String.format(describFmt, metadata.getSummary(), metadata.getCatalog());
        } catch (Exception e) {
            logger.error("有路网抓取数据失败", e);
            content = getBookContentOffline(book);
        }
    } else {
        content = getBookContentOffline(book);
    }
    // \"46602357,46398806,2043189,2043183,122216620,1636953,2043193\"
    // \"%2$s,%3$s,%4$s,%2$s,%5$s,%6$s,%7$s\"
    // 书名，price，作者，书名，出版社，isbn，出版日
    // 标题使用 书名 (版次)+ 作者 + 条形码
    String fmt = "二手%s %s %s %s";
    String bookEdition = getBookEdition(book);
    String bookAuthor = getShortestBookAuthor(book.getBookAuthor());
    String title = String.format(fmt, book.getBookName(), bookEdition, bookAuthor, book.getIsbn());
    String pubDate = ZisUtils.getDateString("yyyy-MM-dd", book.getPublishDate());
    // 一码多书的，采用"条形码+bookId"作为唯一标识，正常的图书直接使用条形码
    String uniqueIsbn = getUniqueIsbn(book);
    String data = String.format(dataFmt, title, book.getBookName(), book.getBookPrice(), book.getBookAuthor(), book.getBookPublisher(), book.getIsbn(), pubDate, content, bookIntro, uniqueIsbn, record.getAmount());
    return data;
}


@Override
public List<InwarehouseDetail> queryExportData(){
    DetachedCriteria criteria = DetachedCriteria.forClass(InwarehouseDetail.class);
    criteria.add(Restrictions.in("inwarehouseId", batchSelectedItem));
    return this.doPurchaseService.findInwarehouseDetailByCriteria(criteria);
}


public String getBookContentOffline(Bookinfo book){
    StringBuilder builder = new StringBuilder();
    builder.append("ISBN：").append(book.getIsbn()).append("<br/>");
    builder.append("书名：").append(book.getBookName()).append("<br/>");
    builder.append("作者：").append(book.getBookAuthor()).append("<br/>");
    builder.append("版次：").append(book.getBookEdition()).append("<br/>");
    builder.append("出版社：").append(book.getBookPublisher()).append("<br/>");
    builder.append("出版日期：").append(ZisUtils.getDateString("yyyy年MM月", book.getPublishDate())).append("<br/>");
    return builder.toString();
}


}