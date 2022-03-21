package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/19 0019 13:34
 * @description： 这是描述
 * @modified By：
 */

@SpringBootTest
public class HotelIndexDocument {

    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private IHotelService hotelService;

    @BeforeEach
    void setUp() {
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create("http://192.168.120.101:9200")
                )
        );
    }

    @Test
    void initClient() {
        System.out.println(restHighLevelClient);
    }


    @AfterEach
    void down() throws IOException {
        restHighLevelClient.close();
    }

    @Test
    void indexDocument() throws IOException {

        Hotel hotel = hotelService.getById(36934L);
        //拼装数据
        HotelDoc doc = new HotelDoc(hotel);
        IndexRequest request = new IndexRequest("hotel").id(String.valueOf(doc.getId()));
        request.source(JSON.toJSONString(doc), XContentType.JSON);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    @Test
    void getDocument() throws IOException {
        GetRequest request = new GetRequest("hotel").id("36934");
        GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        HotelDoc doc = JSON.parseObject(documentFields.getSourceAsString(), HotelDoc.class);

        System.out.println(doc);
    }


    @Test
    void updateDocument() throws IOException {
        //GetRequest request = new GetRequest("hotel").id("36934");
        UpdateRequest request = new UpdateRequest("hotel", "36934");
        request.doc("price", 999);
        restHighLevelClient.update(request, RequestOptions.DEFAULT);
    }

    @Test
    void deleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", "36934");
        restHighLevelClient.delete(request, RequestOptions.DEFAULT);
    }


    @Test
    void batchDocument() throws IOException {
        BulkRequest request = new BulkRequest("hotel");
        List<Hotel> list = hotelService.list();
        for (Hotel hotel : list) {
            HotelDoc doc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotel").id(doc.getId().toString()).source(JSON.toJSONString(doc), XContentType.JSON));

        }
        restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
    }
}
