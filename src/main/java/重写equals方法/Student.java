package 重写equals方法;

import java.util.Objects;

public class Student {

    private String name;
    private String id;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(id, student.id);
    }

}
