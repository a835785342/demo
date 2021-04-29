package 手写spring.controller;

import 手写spring.annotation.AutoWired;
import 手写spring.annotation.Controller;
import 手写spring.annotation.RequestMapping;
import 手写spring.annotation.RequestParam;
import 手写spring.service.DemoService;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @AutoWired
    private DemoService demoService;

    @RequestMapping("/query")
    public String query(String name) {
        return demoService.query(name);
    }
}
