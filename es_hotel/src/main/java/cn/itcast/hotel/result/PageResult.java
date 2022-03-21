package cn.itcast.hotel.result;

import cn.itcast.hotel.pojo.HotelDoc;
import lombok.Data;

import java.util.List;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/21 0021 20:23
 * @description： 这是描述
 * @modified By：
 */

@Data
public class PageResult {

    private Long total;
    private List<HotelDoc> hotels;

    public PageResult(Long total, List<HotelDoc> lists) {
        this.total = total;
        this.hotels = lists;
    }

    public PageResult() {

    }
}
