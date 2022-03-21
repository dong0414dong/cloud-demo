package cn.itcast.hotel;

import cn.itcast.hotel.constants.EsContstants;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/19 0019 12:13
 * @description： 这是描述
 * @modified By：
 */
public class HotelIndexTest {

    private RestHighLevelClient restHighLevelClient;


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
    void createHotelIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        request.source(EsContstants.HOTEL_MAPPING_2, XContentType.JSON);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void existsHotelIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("hotel");
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    @Test
    void deleteHotelIndex() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);

          System.out.println("删除结果:"+delete);
    }
}
