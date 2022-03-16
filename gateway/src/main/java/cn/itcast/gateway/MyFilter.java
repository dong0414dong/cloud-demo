package cn.itcast.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author ：dongdong
 * @date ：Created in 2022/3/16 0016 17:07
 * @description： 这是描述
 * @modified By：
 */

@Component
//值越小  优先级越高  执行过滤器的执行顺序
//@Order(-1)
public class MyFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getQueryParams().getFirst("authorization");
        if ("admin".equals(authorization)) {
            //放行的方法
            return chain.filter(exchange);
        }

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        //拦截
        return exchange.getResponse().setComplete();
    }

    //实现Ordered接口  同样可以实现  过滤器的执行排序
    @Override
    public int getOrder() {
        return -1;
    }


    //一共三种类型的过滤器   当前路由的过滤器   defaultFilters    globalFilter  三种
}
