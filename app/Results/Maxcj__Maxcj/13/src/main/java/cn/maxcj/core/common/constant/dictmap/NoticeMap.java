package cn.maxcj.core.common.constant.dictmap;
 import cn.maxcj.core.common.constant.dictmap.base.AbstractDictMap;
public class NoticeMap extends AbstractDictMap{


@Override
public void init(){
    put("title", "标题");
    put("content", "内容");
}


@Override
public void initBeWrapped(){
}


}