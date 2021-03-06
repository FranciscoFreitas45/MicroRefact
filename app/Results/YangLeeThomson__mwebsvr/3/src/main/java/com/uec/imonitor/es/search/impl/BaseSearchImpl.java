package com.uec.imonitor.es.search.impl;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder.Operator;
import org.elasticsearch.index.query.MatchQueryBuilder.Type;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.uec.imonitor.common.util.ESConstantUtil;
import com.uec.imonitor.es.bean.EsClient;
import com.uec.imonitor.es.bean.params.AggsHistogramParams;
import com.uec.imonitor.es.bean.params.AggsTermParams;
import com.uec.imonitor.es.bean.params.ExistParams;
import com.uec.imonitor.es.bean.params.MatchParams;
import com.uec.imonitor.es.bean.params.QueryParams;
import com.uec.imonitor.es.bean.params.RangeParams;
import com.uec.imonitor.es.bean.params.SortParams;
import com.uec.imonitor.es.search.IBaseSearch;
import com.uec.imonitor.Interface.EsClient;
@Service(value = "baseSearch")
public class BaseSearchImpl implements IBaseSearch{

@Autowired
 private  EsClient esClient;

 private  Logger logger;


public BoolQueryBuilder transMatchParamsToPhraseQueryBuilder(MatchParams mp){
    BoolQueryBuilder mpbqb = QueryBuilders.boolQuery();
    List<String> fieldList = mp.getFieldList();
    List<String> valueList = mp.getValueList();
    // matchParams????????????????????????????????????????????????and???or
    String in_opt = mp.getInOpt();
    // field?????????????????????????????????????????????????????????????????????value???????????????and???or
    String field_opt = mp.getFieldOpt();
    // ?????????????????????
    // field??????????????????????????????and??????
    Operator fieldOpt = Operator.AND;
    if (ESConstantUtil.OPT_OR.equals(field_opt)) {
        // field??????????????????????????????
        fieldOpt = Operator.OR;
    }
    for (int i = 0; fieldList != null && i < fieldList.size(); i++) {
        String field = fieldList.get(i);
        BoolQueryBuilder fieldqb = QueryBuilders.boolQuery();
        for (int j = 0; valueList != null && j < valueList.size(); j++) {
            String value = valueList.get(j);
            String analyzer = mp.getAnaylzer();
            QueryBuilder qb;
            if (ESConstantUtil.ANALYZER_IK_SMART.equals(analyzer)) {
                if (mp.getType() == null) {
                    qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt).analyzer(analyzer);
                } else {
                    qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
                }
            } else if (analyzer != null && analyzer.equals(ESConstantUtil.ANALYZER_NOT)) {
                qb = QueryBuilders.matchQuery(field, value);
            } else {
                if (mp.getType() == null) {
                    qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt).analyzer(ESConstantUtil.ANALYZER_IK_MAX);
                } else {
                    qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
                }
            }
            if (Operator.OR.equals(fieldOpt)) {
                fieldqb.should(qb);
            } else {
                // ??????and?????????????????????????????????value???????????????and???or
                fieldqb.must(qb);
            }
        }
        // matchParams????????????????????????????????????????????????and???or
        if (ESConstantUtil.OPT_OR.equals(in_opt)) {
            mpbqb.should(fieldqb);
        } else if (ESConstantUtil.OPT_NOT.equals(in_opt)) {
            mpbqb.mustNot(fieldqb);
        } else if (ESConstantUtil.OPT_FILTER.equals(in_opt)) {
            // ????????????
            mpbqb.filter(fieldqb);
        } else {
            // ??????or??????
            mpbqb.should(fieldqb);
        }
    }
    return mpbqb;
}


@Override
public SearchResponse textSearcher(QueryParams<T> queryParams){
    // ??????????????????
    long startTime = System.currentTimeMillis();
    SearchRequestBuilder srb = this.getSearchRequestBuilder(queryParams);
    SearchResponse response = srb.execute().actionGet();
    // ??????????????????
    long endTime = System.currentTimeMillis();
    logger.info("??????????????????????????? " + (endTime - startTime) + "ms");
    return response;
}


public SearchResponse phraseSearcher(QueryParams<T> queryParams){
    // ??????????????????
    long startTime = System.currentTimeMillis();
    SearchRequestBuilder srb = this.getPhraseRequestBuilder(queryParams);
    SearchResponse response = srb.execute().actionGet();
    // ??????????????????
    long endTime = System.currentTimeMillis();
    logger.info("??????????????????????????? " + (endTime - startTime) + "ms");
    return response;
}


@Override
public SearchRequestBuilder getSearchRequestBuilder(QueryParams<T> queryParams){
    String[] indexArray = queryParams.getIndexArray();
    String[] typeArray = queryParams.getTypeArray();
    Client client = esClient.getTransportClient();
    SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
    // 
    List<MatchParams> matchList = queryParams.getMatchList();
    // srb.addFields(queryParams.getIndexArray());
    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
    // ??????????????????????????????????????????
    if (!CollectionUtils.isEmpty(matchList)) {
        for (MatchParams mp : matchList) {
            // ??????matchParams??????????????????and???or???not???filter
            String out_opt = mp.getOutOpt();
            BoolQueryBuilder mpbqb = transMatchParamsToQueryBuilder(mp);
            // ?????????????????????
            if (ESConstantUtil.OPT_OR.equals(out_opt)) {
                bqb.should(mpbqb);
            } else if (ESConstantUtil.OPT_NOT.equals(out_opt)) {
                bqb.mustNot(mpbqb);
            } else if (ESConstantUtil.OPT_FILTER.equals(out_opt)) {
                // ????????????
                bqb.filter(mpbqb);
            } else {
                // ??????and??????
                bqb.must(mpbqb);
            }
        }
    }
    // ????????????????????????
    ExistParams exitParams = queryParams.getExistParams();
    if (null != exitParams) {
        List<String> fieldList = exitParams.getFieldList();
        if (!CollectionUtils.isEmpty(fieldList)) {
            for (String field : fieldList) {
                QueryBuilder qb = QueryBuilders.existsQuery(field);
                bqb.must(qb);
            }
        }
    }
    // ????????????
    List<RangeParams> rangeList = queryParams.getRangeList();
    if (!CollectionUtils.isEmpty(rangeList)) {
        for (RangeParams rp : rangeList) {
            QueryBuilder qb = QueryBuilders.rangeQuery(rp.getField()).from(rp.getStart()).to(rp.getEnd());
            bqb.must(qb);
        }
    }
    srb.setQuery(bqb);
    // id????????????
    String[] idsList = queryParams.getIds();
    if (null != idsList && idsList.length > 0) {
        QueryBuilder qb = QueryBuilders.idsQuery().addIds(idsList);
        bqb.must(qb);
    }
    // ???????????????
    // String[] returnFieldsArray = queryParams.getReturnFieldsArray();
    // if(returnFieldsArray != null && returnFieldsArray.length >0){
    // srb.addFields(returnFieldsArray);
    // }
    // ???????????????--???????????????????????????????????????10000
    if (queryParams.getStart() >= 0 && queryParams.getSize() > 0 && (queryParams.getStart() < 10000)) {
        if (queryParams.getStart() + queryParams.getSize() <= 10000) {
            srb.setFrom(queryParams.getStart()).setSize(queryParams.getSize());
        } else {
            srb.setFrom(queryParams.getStart()).setSize(10000 - queryParams.getStart());
        }
    }
    // ????????????
    AggsTermParams aggsParam = queryParams.getAggsParams();
    AggsHistogramParams histogram = queryParams.getHistogramParams();
    if (null != aggsParam) {
        // term????????????
        String aggs_name = aggsParam.getAggName();
        String field = aggsParam.getField();
        Integer size = aggsParam.getSize();
        String sort = aggsParam.getSort();
        if (null != size && size > 0) {
            if (ESConstantUtil.SORT_ASC.equals(sort.toLowerCase())) {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(true));
                srb.addAggregation(aggs);
            } else {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(false));
                srb.addAggregation(aggs);
            }
        } else {
            if (ESConstantUtil.SORT_ASC.equals(sort.toLowerCase())) {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(true));
                srb.addAggregation(aggs);
            } else {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(false));
                srb.addAggregation(aggs);
            }
        }
    } else if (null != histogram) {
        // histogram ????????????
        String aggs_name2 = histogram.getAggsName();
        String field2 = histogram.getAggsField();
        String timeZone = histogram.getTimeZone();
        String format = histogram.getFormat();
        Date minDate = histogram.getMinDate();
        Date maxDate = histogram.getMaxDate();
        Long min = null, max = null;
        if (null != minDate) {
            min = minDate.getTime();
        }
        if (null != maxDate) {
            max = maxDate.getTime();
        }
        DateHistogramInterval interval = histogram.getInterval();
        @SuppressWarnings("rawtypes")
        AggregationBuilder aggregation = AggregationBuilders.dateHistogram(aggs_name2).field(field2).timeZone(timeZone).format(format).extendedBounds(min, max).interval(interval);
        srb.addAggregation(aggregation);
    }
    // ????????????
    List<SortParams> sortList = queryParams.getSortList();
    if (null != sortList) {
        for (SortParams sp : sortList) {
            String sortField = sp.getSortField();
            String sort = sp.getSort();
            if (ESConstantUtil.SORT_ASC.equals(sort)) {
                srb.addSort(sortField, SortOrder.ASC);
            } else {
                srb.addSort(sortField, SortOrder.DESC);
            }
        }
    }
    return srb.setExplain(false);
}


@Override
public SearchResponse matchAllSearch(QueryParams<T> queryParams){
    String[] indexArray = queryParams.getIndexArray();
    String[] typeArray = queryParams.getTypeArray();
    Client client = esClient.getTransportClient();
    SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
    QueryBuilder qb = QueryBuilders.matchAllQuery();
    srb.setQuery(qb);
    srb.setExplain(false);
    SearchResponse response = srb.execute().actionGet();
    return response;
}


public BoolQueryBuilder transMatchParamsToQueryBuilder(MatchParams mp){
    BoolQueryBuilder mpbqb = QueryBuilders.boolQuery();
    List<String> fieldList = mp.getFieldList();
    List<String> valueList = mp.getValueList();
    // matchParams?????????????????????,??????value???????????????and???or
    String in_opt = mp.getInOpt();
    // field?????????????????????????????????????????????and???or???not
    String field_opt = mp.getFieldOpt();
    // ?????????????????????
    // field??????????????????????????????and??????
    Operator fieldOpt = Operator.AND;
    if (ESConstantUtil.OPT_OR.equals(field_opt)) {
        // field??????????????????????????????
        fieldOpt = Operator.OR;
    }
    for (int i = 0; fieldList != null && i < fieldList.size(); i++) {
        String field = fieldList.get(i);
        for (int j = 0; valueList != null && j < valueList.size(); j++) {
            String value = valueList.get(j);
            String analyzer = mp.getAnaylzer();
            QueryBuilder qb;
            if (ESConstantUtil.ANALYZER_IK_SMART.equals(analyzer)) {
                if (mp.getType() == null) {
                    qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt).analyzer(analyzer);
                } else {
                    qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
                }
            } else if (analyzer != null && analyzer.equals(ESConstantUtil.ANALYZER_NOT)) {
                qb = QueryBuilders.matchQuery(field, value);
            } else {
                if (mp.getType() == null) {
                    qb = QueryBuilders.matchQuery(field, value).operator(fieldOpt).analyzer(ESConstantUtil.ANALYZER_IK_MAX);
                } else {
                    qb = QueryBuilders.matchQuery(field, value).type(mp.getType()).analyzer(analyzer);
                }
            }
            // matchParams?????????????????????,??????value???????????????and???or
            if (ESConstantUtil.OPT_OR.equals(in_opt)) {
                mpbqb.should(qb);
            } else if (ESConstantUtil.OPT_NOT.equals(in_opt)) {
                mpbqb.mustNot(qb);
            } else if (ESConstantUtil.OPT_FILTER.equals(in_opt)) {
                // ????????????
                mpbqb.filter(qb);
            } else {
                // ??????and??????
                mpbqb.must(qb);
            }
        }
    }
    return mpbqb;
}


public SearchRequestBuilder getPhraseRequestBuilder(QueryParams<T> queryParams){
    String[] indexArray = queryParams.getIndexArray();
    String[] typeArray = queryParams.getTypeArray();
    Client client = esClient.getTransportClient();
    SearchRequestBuilder srb = client.prepareSearch(indexArray).setTypes(typeArray);
    // 
    List<MatchParams> matchList = queryParams.getMatchList();
    // srb.addFields(queryParams.getIndexArray());
    BoolQueryBuilder bqb = QueryBuilders.boolQuery();
    // ??????????????????????????????????????????
    if (!CollectionUtils.isEmpty(matchList)) {
        List<MatchParams> phraseList = new ArrayList<MatchParams>();
        List<MatchParams> filterList = new ArrayList<>();
        for (MatchParams mp : matchList) {
            // ?????????????????????
            Type type = mp.getType();
            if (Type.PHRASE.equals(type)) {
                // ??????????????????
                phraseList.add(mp);
            } else {
                // ??????
                filterList.add(mp);
            }
        }
        // ???????????????????????????
        BoolQueryBuilder mpbqbPhrase = new BoolQueryBuilder();
        for (MatchParams mp : phraseList) {
            // matchParams????????????????????????????????????????????????and???or
            String in_opt = mp.getInOpt();
            BoolQueryBuilder mpbqb = transMatchParamsToPhraseQueryBuilder(mp);
            // ?????????????????????
            if (ESConstantUtil.OPT_OR.equals(in_opt)) {
                mpbqbPhrase.should(mpbqb);
            } else if (ESConstantUtil.OPT_NOT.equals(in_opt)) {
                mpbqbPhrase.mustNot(mpbqb);
            } else {
                // ??????and??????
                mpbqbPhrase.must(mpbqb);
            }
        }
        bqb.must(mpbqbPhrase);
        // ?????????????????????
        for (MatchParams mp : filterList) {
            // ??????matchParams??????????????????and???or???not???filter
            String out_opt = mp.getOutOpt();
            BoolQueryBuilder mpbqb = transMatchParamsToQueryBuilder(mp);
            // ?????????????????????
            if (ESConstantUtil.OPT_OR.equals(out_opt)) {
                bqb.should(mpbqb);
            } else if (ESConstantUtil.OPT_NOT.equals(out_opt)) {
                bqb.mustNot(mpbqb);
            } else if (ESConstantUtil.OPT_FILTER.equals(out_opt)) {
                // ????????????
                bqb.filter(mpbqb);
            } else {
                // ??????and??????
                bqb.must(mpbqb);
            }
        }
    }
    // ????????????????????????
    ExistParams exitParams = queryParams.getExistParams();
    if (null != exitParams) {
        List<String> fieldList = exitParams.getFieldList();
        if (!CollectionUtils.isEmpty(fieldList)) {
            for (String field : fieldList) {
                QueryBuilder qb = QueryBuilders.existsQuery(field);
                bqb.must(qb);
            }
        }
    }
    // ????????????
    List<RangeParams> rangeList = queryParams.getRangeList();
    if (!CollectionUtils.isEmpty(rangeList)) {
        for (RangeParams rp : rangeList) {
            QueryBuilder qb = QueryBuilders.rangeQuery(rp.getField()).from(rp.getStart()).to(rp.getEnd());
            bqb.must(qb);
        }
    }
    srb.setQuery(bqb);
    // id????????????
    String[] idsList = queryParams.getIds();
    if (null != idsList && idsList.length > 0) {
        QueryBuilder qb = QueryBuilders.idsQuery().addIds(idsList);
        bqb.must(qb);
    }
    // ???????????????
    // String[] returnFieldsArray = queryParams.getReturnFieldsArray();
    // if(returnFieldsArray != null && returnFieldsArray.length >0){
    // srb.addFields(returnFieldsArray);
    // }
    // ???????????????--???????????????????????????????????????10000
    if (queryParams.getStart() >= 0 && queryParams.getSize() > 0 && (queryParams.getStart() < 10000)) {
        if (queryParams.getStart() + queryParams.getSize() <= 10000) {
            srb.setFrom(queryParams.getStart()).setSize(queryParams.getSize());
        } else {
            srb.setFrom(queryParams.getStart()).setSize(10000 - queryParams.getStart());
        }
    }
    // ????????????
    AggsTermParams aggsParam = queryParams.getAggsParams();
    AggsHistogramParams histogram = queryParams.getHistogramParams();
    if (null != aggsParam) {
        // term????????????
        String aggs_name = aggsParam.getAggName();
        String field = aggsParam.getField();
        Integer size = aggsParam.getSize();
        String sort = aggsParam.getSort();
        if (null != size && size > 0) {
            if (ESConstantUtil.SORT_ASC.equals(sort)) {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(true));
                srb.addAggregation(aggs);
            } else {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).size(size).order(Terms.Order.count(false));
                srb.addAggregation(aggs);
            }
        } else {
            if (ESConstantUtil.SORT_ASC.equals(sort)) {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(true));
                srb.addAggregation(aggs);
            } else {
                TermsBuilder aggs = AggregationBuilders.terms(aggs_name).field(field).order(Terms.Order.count(false));
                srb.addAggregation(aggs);
            }
        }
    } else if (null != histogram) {
        // histogram ????????????
        String aggs_name2 = histogram.getAggsName();
        String field2 = histogram.getAggsField();
        String timeZone = histogram.getTimeZone();
        String format = histogram.getFormat();
        Date minDate = histogram.getMinDate();
        Date maxDate = histogram.getMaxDate();
        Long min = null, max = null;
        if (null != minDate) {
            min = minDate.getTime();
        }
        if (null != maxDate) {
            max = maxDate.getTime();
        }
        DateHistogramInterval interval = histogram.getInterval();
        @SuppressWarnings("rawtypes")
        AggregationBuilder aggregation = AggregationBuilders.dateHistogram(aggs_name2).field(field2).timeZone(timeZone).format(format).extendedBounds(min, max).interval(interval);
        srb.addAggregation(aggregation);
    }
    // ????????????
    List<SortParams> sortList = queryParams.getSortList();
    if (null != sortList) {
        for (SortParams sp : sortList) {
            String sortField = sp.getSortField();
            String sort = sp.getSort();
            if (ESConstantUtil.SORT_ASC.equals(sort)) {
                srb.addSort(sortField, SortOrder.ASC);
            } else {
                srb.addSort(sortField, SortOrder.DESC);
            }
        }
    }
    return srb.setExplain(false);
}


}