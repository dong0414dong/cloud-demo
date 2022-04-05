package cn.itcast.douluo.web;

import cn.itcast.douluo.bean.Result;
import cn.itcast.douluo.service.StepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("step/")
public class StepController {

    @Autowired
    private StepService stepService;

    @GetMapping("/num")
    public ModelAndView queryOrderByUserId(ModelMap model) {
        // 根据id查询订单并返回
        Result result = stepService.queryStepByDate("");
        //model.addAttribute("result", result);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("result", result);

        modelAndView.addObject("url", "http://localhost:9999/image/9587e1db67780f320922da68f318b88.jpg");
        return modelAndView;

    }
}
