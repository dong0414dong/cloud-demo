package cn.itcast.hotel.param;

import lombok.Data;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/21 0021 20:19
 * @description： 这是描述
 * @modified By：
 */
@Data
public class RequestParams {

    private String key;
    private Integer size;
    private Integer page;

    private String sortBy;

    private String brand;

    private String starName;

    private String city;

    private Double maxPrice;

    private Double minPrice;

    private String location;
}
