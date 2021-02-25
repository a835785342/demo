import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonObject = JSONObject.parseObject("{\"1\":{\"00:00\":\"10000\",\"01:00\":\"11\",\"02:00\":\"111\",\"03:00\":\"1\",\"04:00\":\"11\",\"05:00\":\"111\",\"06:00\":\"1\",\"07:00\":\"11\",\"08:00\":\"1\",\"09:00\":\"1\",\"10:00\":\"1\",\"11:00\":\"1\",\"12:00\":\"1\",\"13:00\":\"1\",\"14:00\":\"1\",\"15:00\":\"1\",\"16:00\":\"1\",\"17:00\":\"1\",\"18:00\":\"1\",\"19:00\":\"1\",\"20:00\":\"1\",\"21:00\":\"1\",\"22:00\":\"1\",\"23:00\":\"1\"},\"2\":{\"00:00\":\"5\",\"01:00\":\"55\",\"02:00\":\"555\",\"03:00\":\"5\",\"04:00\":\"55\",\"05:00\":\"555\",\"06:00\":\"5\",\"07:00\":\"55\",\"08:00\":\"1\",\"09:00\":\"1\",\"10:00\":\"1\",\"11:00\":\"1\",\"12:00\":\"1\",\"13:00\":\"1\",\"14:00\":\"1\",\"15:00\":\"1\",\"16:00\":\"1\",\"17:00\":\"1\",\"18:00\":\"1\",\"19:00\":\"1\",\"20:00\":\"1\",\"21:00\":\"1\",\"22:00\":\"1\",\"23:00\":\"1\"},\"3\":{\"00:00\":\"3\",\"01:00\":\"33\",\"02:00\":\"333\",\"03:00\":\"3\",\"04:00\":\"33\",\"05:00\":\"333\",\"06:00\":\"3\",\"07:00\":\"33\",\"08:00\":\"1\",\"09:00\":\"1\",\"10:00\":\"1\",\"11:00\":\"1\",\"12:00\":\"1\",\"13:00\":\"1\",\"14:00\":\"1\",\"15:00\":\"1\",\"16:00\":\"1\",\"17:00\":\"1\",\"18:00\":\"1\",\"19:00\":\"1\",\"20:00\":\"1\",\"21:00\":\"1\",\"22:00\":\"1\",\"23:00\":\"1\"},\"4\":{\"00:00\":\"4\",\"01:00\":\"44\",\"02:00\":\"444\",\"03:00\":\"4\",\"04:00\":\"44\",\"05:00\":\"444\",\"06:00\":\"4\",\"07:00\":\"44\",\"08:00\":\"1\",\"09:00\":\"1\",\"10:00\":\"1\",\"11:00\":\"1\",\"12:00\":\"1\",\"13:00\":\"1\",\"14:00\":\"1\",\"15:00\":\"1\",\"16:00\":\"1\",\"17:00\":\"1\",\"18:00\":\"1\",\"19:00\":\"1\",\"20:00\":\"1\",\"21:00\":\"1\",\"22:00\":\"1\",\"23:00\":\"1\"},\"5\":{\"00:00\":\"2\",\"01:00\":\"22\",\"02:00\":\"222\",\"03:00\":\"2\",\"04:00\":\"22\",\"05:00\":\"222\",\"06:00\":\"2\",\"07:00\":\"22\",\"08:00\":\"1\",\"09:00\":\"1\",\"10:00\":\"1\",\"11:00\":\"1\",\"12:00\":\"1\",\"13:00\":\"1\",\"14:00\":\"1\",\"15:00\":\"1\",\"16:00\":\"1\",\"17:00\":\"1\",\"18:00\":\"1\",\"19:00\":\"1\",\"20:00\":\"1\",\"21:00\":\"1\",\"22:00\":\"1\",\"23:00\":\"1\"}}");
        fixDayTimeData(jsonObject);

//        Map<String, BigDecimal> map =new HashMap<String, BigDecimal>();
//        map.put("1",new BigDecimal("2874.5"));
//        map.put("11",new BigDecimal("2894.5"));
//        map.put("111",new BigDecimal("2844.5"));
//        map.put("1111",new BigDecimal("2854.5"));
//        String maxKey = map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        System.out.println(jsonObject);
    }

    private static void fixDayTimeData(JSONObject jsonObject) {
        Map<String,BigDecimal> dayTotalEnergyMap = new HashMap<>();
        for(int i = 1;i <= jsonObject.size();i++){
            JSONObject hourEnergyJson = jsonObject.getJSONObject("" + i);
            BigDecimal dayTotalEnergy = hourEnergyJson.values().stream().map((a) -> new BigDecimal(String.valueOf(a))).reduce(BigDecimal.ZERO, BigDecimal::add);
            dayTotalEnergyMap.put(""+i,dayTotalEnergy);
        }
        //天总电量最大的key
        String maxDayKey = dayTotalEnergyMap.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        String lastDayKey = "" + jsonObject.size();
        JSONObject maxHourEnergyJson = jsonObject.getJSONObject(maxDayKey);
        JSONObject lastDayHourEnergyJson = jsonObject.getJSONObject(lastDayKey);
        jsonObject.put(maxDayKey,lastDayHourEnergyJson);
        jsonObject.put(lastDayKey,maxHourEnergyJson);
    }
}
