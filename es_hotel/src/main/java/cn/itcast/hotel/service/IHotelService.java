package cn.itcast.hotel.service;

import cn.itcast.hotel.param.RequestParams;
import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.result.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

public interface IHotelService extends IService<Hotel> {


    public PageResult searchHotel(RequestParams param);
}
