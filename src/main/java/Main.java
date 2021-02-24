import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, BigDecimal> map =new HashMap<String, BigDecimal>();
        map.put("1",new BigDecimal("2874.5"));
        map.put("11",new BigDecimal("2894.5"));
        map.put("111",new BigDecimal("2844.5"));
        map.put("1111",new BigDecimal("2854.5"));
        String maxKey = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println(maxKey);
    }
}
