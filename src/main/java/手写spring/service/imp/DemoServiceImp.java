package 手写spring.service.imp;

import 手写spring.annotation.Service;
import 手写spring.service.DemoService;

@Service
public class DemoServiceImp implements DemoService {
    @Override
    public String query(String name) {
        System.out.println("my name is " + name);
        return "my name is " + name;
    }
}
