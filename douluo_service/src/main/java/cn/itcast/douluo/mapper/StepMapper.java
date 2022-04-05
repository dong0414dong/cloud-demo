package cn.itcast.douluo.mapper;


import cn.itcast.douluo.bean.Step;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StepMapper {

    @Select("select * from douluo_step where id = #{id}")
    List<Step> findById(Long id);


    @Select("select * from douluo_step where data_date = #{dataDate}")
    List<Step> findByDate(String dataDate);
}
