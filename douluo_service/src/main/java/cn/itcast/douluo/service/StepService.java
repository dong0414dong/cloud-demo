package cn.itcast.douluo.service;

import cn.itcast.douluo.bean.Result;
import cn.itcast.douluo.bean.Step;
import cn.itcast.douluo.mapper.StepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StepService {

    @Autowired
    private StepMapper stepMapper;

    private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

    public Result queryStepByDate(String dataDate) {

        String format = yyyyMMdd.format(new Date());
        // 1.查询订单
        List<Step> steps = stepMapper.findByDate(format);
        Result result = new Result();
        for (Step step : steps) {

            if (step.getType().equals(1)) {
                result.setIce(step.getStepNum().toString());
            }
            if (step.getType().equals(2)) {
                result.setFire(step.getStepNum().toString());
            }
        }
        return result;
    }
}
