package cn.itcast.order.service;

import cn.itcast.feignClient.feign.UserFeignClient;
//import cn.itcast.feignClient.pojo.User;
import cn.itcast.feignClient.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFeignClient userFeignClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        //请求用户信息 通过  restTemplate 调用
        //User user = restTemplate.getForObject("http://localhost:8081/user/" + order.getUserId(), User.class);

        //这种远程调用属于硬编码  不够优雅
        //User user = restTemplate.getForObject("http://user-service/user/" + order.getUserId(), User.class);

        //使用feign调用
        User user = userFeignClient.findUserById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }
}
