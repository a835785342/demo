import java.util.ArrayList;
import java.util.List;

public class A {

    private String name;

    class B{

        public void test(){
            String newName = A.this.name;
        }


    }


}
