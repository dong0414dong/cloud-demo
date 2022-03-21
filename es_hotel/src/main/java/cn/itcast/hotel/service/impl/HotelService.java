package cn.itcast.hotel.service.impl;

import cn.itcast.hotel.mapper.HotelMapper;
import cn.itcast.hotel.param.RequestParams;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.result.PageResult;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HotelService extends ServiceImpl<HotelMapper, Hotel> implements IHotelService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public PageResult searchHotel(RequestParams param) {
        param.setLocation("31.03463,121.61245");

        try {
            SearchRequest request = new SearchRequest("hotel");
            String key = param.getKey();
            Integer pageSize = param.getSize();
            Integer pageNum = param.getPage();
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
            //关键字搜索

            if (StringUtils.isNotBlank(key)) {
                boolQuery.must(QueryBuilders.matchQuery("all", key));
            } else {
                boolQuery.must(QueryBuilders.matchAllQuery());
            }
            if (StringUtils.isNotBlank(param.getCity())) {
                boolQuery.filter(QueryBuilders.termQuery("city", param.getCity()));
            }
            if (StringUtils.isNotBlank(param.getBrand())) {
                boolQuery.filter(QueryBuilders.termQuery("brand", param.getBrand()));
            }
            if (StringUtils.isNotBlank(param.getStarName())) {
                boolQuery.filter(QueryBuilders.termQuery("starName", param.getStarName()));
            }

            if (param.getMinPrice() != null && param.getMaxPrice() != null) {
                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(param.getMinPrice()).lte(param.getMaxPrice()));
            }
            request.source().query(boolQuery);
            //价格排序
            String location = param.getLocation();
            if (StringUtils.isNotBlank(location)) {
                request.source().sort(SortBuilders
                        .geoDistanceSort("location", new GeoPoint(location))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.KILOMETERS));
            }
            request.source().from((pageSize - 1) * pageNum).size(pageSize);
            SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
            return handler(search);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private PageResult handler(SearchResponse search) {
        SearchHits searchHits = search.getHits();
        long count = searchHits.getTotalHits().value;
        SearchHit[] hits = searchHits.getHits();
        List<HotelDoc> list = new ArrayList<>();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            HotelDoc doc = JSON.parseObject(sourceAsString, HotelDoc.class);

            //获取距离排序的值
            final Object[] sortValues = hit.getSortValues();
            if (sortValues.length > 0) {
                Object sortValue = sortValues[0];
                doc.setDistance(sortValue);
            }
            list.add(doc);
        }

        PageResult pageResult = new PageResult(count, list);
        return pageResult;
    }

}
