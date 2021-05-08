package 手写spring.controller;

import 手写spring.annotation.AutoWired;
import 手写spring.annotation.Controller;
import 手写spring.annotation.RequestMapping;
import 手写spring.annotation.RequestParam;
import 手写spring.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController {

    private int count = 0;

    @AutoWired
    private DemoService demoService;

    @RequestMapping("/query")
    public String query(String name) {
        System.out.println("count的值为"+(++count));
        return demoService.query(name);
    }
}
