package cn.itcast.study.A08Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/5 0005 18:54
 * @description： 这是描述
 * @modified By：
 */
@RestController
public class MyController {

    @Lazy
    @Autowired
    private BeanForSession beanForSession;
    @Lazy
    @Autowired
    private BeanForRequest beanForRequest;
    @Lazy
    @Autowired
    private BeanForApplication beanForApplication;

    @GetMapping(value = "/test", produces = "text/html")
    public String test(HttpServletRequest request, HttpSession session) {
        ServletContext sc = request.getServletContext();
        String sb = "<ul>" +
                "<li>" + "request scope:" + beanForRequest + "</li>" +
                "<li>" + "session scope:" + beanForSession + "</li>" +
                "<li>" + "application scope:" + beanForApplication + "</li>" +
                "</ul>";
        return sb;
    }
}
