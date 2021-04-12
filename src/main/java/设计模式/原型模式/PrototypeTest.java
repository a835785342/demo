package 设计模式.原型模式;

import java.io.*;
import java.util.ArrayList;

public class PrototypeTest {

    public static void main(String[] args) {
        ConcreatePrototypeA prototypeA = new ConcreatePrototypeA();
        prototypeA.setName("张三");
        prototypeA.setAge(24);
        prototypeA.setHobbies(new ArrayList());

        ConcreatePrototypeA clone = (ConcreatePrototypeA)prototypeA.clone();



    }
}
