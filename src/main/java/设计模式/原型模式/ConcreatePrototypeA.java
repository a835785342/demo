package 设计模式.原型模式;

import java.util.List;

public class ConcreatePrototypeA implements ProtoType {

    private String name;
    private Integer age;
    private List hobbies;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public ProtoType clone() {
        ConcreatePrototypeA prototype = new ConcreatePrototypeA();
        prototype.setAge(this.age);
        prototype.setName(this.name);
        prototype.setHobbies(this.hobbies);
        return prototype;
    }
}
