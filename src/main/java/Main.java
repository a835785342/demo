import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int a = 4;
        int b = 2;

        a = a ^ b;
        b = b ^ a;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

    }

    private static void fixDayTimeData(JSONObject jsonObject) {

    }
}
