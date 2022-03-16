package cn.itcast.feignClient.feign;

import cn.itcast.feignClient.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/16 0016 16:11
 * @description： 这是描述
 * @modified By：
 */

@FeignClient("user-service")
public interface UserFeignClient {

    @GetMapping("user/{id}")
    User findUserById(@PathVariable("id") Long id);
}
