package cn.itcast.douluo.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author ：dongdong
 * @date ：Created in 2022/4/3 0003 12:59
 * @description： 这是描述
 * @modified By：
 */

@Data
public class Step {

    private Integer id;
    private Integer stepNum;
    private Integer type;
    private String dataDate;
    private Date createTime;
    private Date updateTime;


}
