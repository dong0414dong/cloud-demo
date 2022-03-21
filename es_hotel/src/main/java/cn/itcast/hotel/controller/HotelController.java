package cn.itcast.hotel.controller;

import cn.itcast.hotel.param.RequestParams;
import cn.itcast.hotel.result.PageResult;
import cn.itcast.hotel.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/21 0021 20:21
 * @description： 这是描述
 * @modified By：
 */

@RestController
@RequestMapping(value = "/hotel")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @PostMapping("list")
    public PageResult getHotel(@RequestBody RequestParams param) {

        return hotelService.searchHotel(param);
    }
}
